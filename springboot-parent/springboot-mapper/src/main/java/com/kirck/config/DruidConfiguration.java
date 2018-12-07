package com.kirck.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

@ConditionalOnClass(com.alibaba.druid.pool.DruidDataSource.class)
@ConditionalOnProperty(name = "spring.datasource.type", havingValue = "com.alibaba.druid.pool.DruidDataSource", matchIfMissing = true)
public class DruidConfiguration {

	 private Logger logger = LoggerFactory.getLogger(DruidConfiguration.class);

	    @Value("${spring.datasource.url}")
	    private String dbUrl;

	    @Value("${spring.datasource.username}")
	    private String username;

	    @Value("${spring.datasource.password}")
	    private String password;

	    @Value("${spring.datasource.driver-class-name}")
	    private String driverClassName;

	    @Value("${spring.datasource.initialSize}")
	    private int initialSize;

	    @Value("${spring.datasource.minIdle}")
	    private int minIdle;

	    @Value("${spring.datasource.maxActive}")
	    private int maxActive;

	    @Value("${spring.datasource.maxWait}")
	    private int maxWait;

	    @Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
	    private int timeBetweenEvictionRunsMillis;

	    @Value("${spring.datasource.minEvictableIdleTimeMillis}")
	    private int minEvictableIdleTimeMillis;

	    @Value("${spring.datasource.validationQuery}")
	    private String validationQuery;

	    @Value("${spring.datasource.testWhileIdle}")
	    private boolean testWhileIdle;

	    @Value("${spring.datasource.testOnBorrow}")
	    private boolean testOnBorrow;

	    @Value("${spring.datasource.testOnReturn}")
	    private boolean testOnReturn;

	    @Value("${spring.datasource.poolPreparedStatements}")
	    private boolean poolPreparedStatements;

	    @Value("${spring.datasource.maxPoolPreparedStatementPerConnectionSize}")
	    private int maxPoolPreparedStatementPerConnectionSize;

	    @Value("${spring.datasource.filters}")
	    private String filters;

	    @Value("{spring.datasource.connectionProperties}")
	    private String connectionProperties;

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
	    @Bean
	    @ConfigurationProperties("spring.datasource.druid")
	    public DataSource dataSource() {
	        DruidDataSource datasource = new DruidDataSource();
	        datasource.setUrl(dbUrl);
	        datasource.setDriverClassName(driverClassName);
	        datasource.setUsername(username);
	        datasource.setPassword(password);

	        //configuration
	        datasource.setInitialSize(initialSize);
	        datasource.setMinIdle(minIdle);
	        datasource.setMaxActive(maxActive);
	        datasource.setMaxWait(maxWait);
	        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
	        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
	        datasource.setValidationQuery(validationQuery);
	        datasource.setTestWhileIdle(testWhileIdle);
	        datasource.setTestOnBorrow(testOnBorrow);
	        datasource.setTestOnReturn(testOnReturn);
	        datasource.setPoolPreparedStatements(poolPreparedStatements);
	        datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
	        try {
	            /**
	             * 设置StatFilter，用于统计监控信息。
	             * StatFilter的别名是stat
	             *
	             */
	            datasource.setFilters(filters);
	        } catch (SQLException e) {
	            logger.error("druid configuration initialization filter : {0}",e);
	        }
	        datasource.setConnectionProperties(connectionProperties);

	        return datasource;
	    }
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
}
