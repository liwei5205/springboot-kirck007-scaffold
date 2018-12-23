package com.kirck.config.db;

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

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

@Configuration
@ConditionalOnClass(com.alibaba.druid.pool.DruidDataSource.class)
@ConditionalOnProperty(name = "spring.datasource.master.type", havingValue = "com.alibaba.druid.pool.DruidDataSource", matchIfMissing = true)
public class DruidConfiguration {
	 @Value("${spring.datasource.master.type}")
	 private Class<? extends DataSource> dataSourceType;

	/**
	     * 注册一个StatViewServlet
	     * @return
	     */
	    @Bean
	    public ServletRegistrationBean DruidStatViewServle(){
	        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),"/druid2/*");
	 
	        //添加初始化参数：initParams
	        /** 白名单，如果不配置或value为空，则允许所有 */
	        servletRegistrationBean.addInitParameter("allow","127.0.0.1,192.0.0.1");
	        /** 黑名单，与白名单存在相同IP时，优先于白名单 */
	        servletRegistrationBean.addInitParameter("deny","192.0.0.1");
	        /** 用户名 */
	        servletRegistrationBean.addInitParameter("loginUsername","root");
	        /** 密码 */
	        servletRegistrationBean.addInitParameter("loginPassword","123456");
	        /** 禁用页面上的“Reset All”功能 */
	        servletRegistrationBean.addInitParameter("resetEnable","false");
	        return servletRegistrationBean;
	    }
	 
	    /**
	     * 注册一个：WebStatFilter
	     * @return
	     */
	    @Bean
	    public FilterRegistrationBean druidStatFilter(){
	        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
	 
	        /** 过滤规则 */
	        filterRegistrationBean.addUrlPatterns("/*");
	        /** 忽略资源 */
	        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid2/*");
	        return filterRegistrationBean;
	    }


}
