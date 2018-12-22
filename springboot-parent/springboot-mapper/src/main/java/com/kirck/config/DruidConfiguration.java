package com.kirck.config;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

@Configuration
@ConditionalOnClass(com.alibaba.druid.pool.DruidDataSource.class)
@ConditionalOnProperty(name = "spring.datasource.type", havingValue = "com.alibaba.druid.pool.DruidDataSource", matchIfMissing = true)
public class DruidConfiguration {

	 @Value("${spring.datasource.dynamic.datasource.master.type}")
	 private Class<? extends DataSource> dataSourceType;

	 private Logger logger = LoggerFactory.getLogger(DruidConfiguration.class);
	 
	 private static final String DB_PREFIX_MASTER = "spring.datasource.dynamic.datasource.master";

	 private static final String DB_PREFIX_SLAVE = "spring.datasource.dynamic.datasource.slave";

	/**
	     * 注册一个StatViewServlet
	     * @return
	     */
	    @Bean
	    public ServletRegistrationBean DruidStatViewServle2(){
	        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),"/druid2/*");
	 
	        //添加初始化参数：initParams
	        /** 白名单，如果不配置或value为空，则允许所有 */
	        servletRegistrationBean.addInitParameter("allow","127.0.0.1,192.0.0.1");
	        /** 黑名单，与白名单存在相同IP时，优先于白名单 */
	        servletRegistrationBean.addInitParameter("deny","192.0.0.1");
	        /** 用户名 */
	        servletRegistrationBean.addInitParameter("loginUsername","admin");
	        /** 密码 */
	        servletRegistrationBean.addInitParameter("loginPassword","admin");
	        /** 禁用页面上的“Reset All”功能 */
	        servletRegistrationBean.addInitParameter("resetEnable","false");
	        return servletRegistrationBean;
	    }
	 
	    /**
	     * 注册一个：WebStatFilter
	     * @return
	     */
	    @Bean
	    public FilterRegistrationBean druidStatFilter2(){
	        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
	 
	        /** 过滤规则 */
	        filterRegistrationBean.addUrlPatterns("/*");
	        /** 忽略资源 */
	        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid2/*");
	        return filterRegistrationBean;
	    }
	    
	    
	    /**
	     * @Bean 声明，DataSource 对象为 Spring 容器所管理;
	     * @Primary 表示这里定义的DataSource将覆盖其他来源的DataSource。
	     * StatFilter，用于统计监控信息。StatFilter的别名是stat。
	     * 统计SQL信息，合并统计。mergeStat是的MergeStatFilter缩写。
	     *     通过 DataSource 的属性<property name="filters" value="mergeStat" /> 或者
	     *                          connectProperties属性来打开mergeSql功能 <property name="connectionProperties" value="druid.stat.mergeSql=true" />
	     * StatFilter属性slowSqlMillis用来配置SQL慢的标准
	     *
	     * @return
	     */

	@Bean(name = "writeDataSource")
	@Primary
	@ConfigurationProperties(prefix = DB_PREFIX_MASTER)
	public DataSource writeDataSource() {
		logger.info("-------------------- writeDataSource init ---------------------");
		return DataSourceBuilder.create().type(dataSourceType).build();
	}

	/**
	 * 有多少个从库就要配置多少个
	 *
	 * @return
	 */
	@Bean(name = "readDataSource1")
	@ConfigurationProperties(prefix = DB_PREFIX_SLAVE)
	public DataSource readDataSourceOne() {
		logger.info("-------------------- readDataSourceOne init ---------------------");
		return DataSourceBuilder.create().type(dataSourceType).build();
	}

	@Bean("readDataSources")
	public List<DataSource> readDataSources() {
		List<DataSource> dataSources = new ArrayList<>();
		dataSources.add(readDataSourceOne());
		return dataSources;
	}


}
