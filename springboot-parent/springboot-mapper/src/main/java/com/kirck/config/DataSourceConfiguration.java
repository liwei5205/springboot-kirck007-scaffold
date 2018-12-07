package com.kirck.config;

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
@ConditionalOnProperty(name = "spring.master.type", havingValue = "com.alibaba.druid.pool.DruidDataSource", matchIfMissing = true)
public class DataSourceConfiguration {
	
	 	private static Logger log = LoggerFactory.getLogger(DataSourceConfiguration.class);
	 
	 	@Value("${spring.master.type}")
		private Class<? extends DataSource> dataSourceType;
		@Value("${druid.init.prefix}")
		private String prefix;
		@Value("${druid.init.allow}")
		private String allow;
		@Value("${druid.init.deny}")
		private String deny;
		@Value("${druid.init.loginUsername}")
		private String loginUsername;
		@Value("${druid.init.loginPassword}")
		private String loginPassword;
		@Value("${druid.init.resetEnable}")
		private String resetEnable;
		
		@Bean
		public ServletRegistrationBean druidServlet() {
			log.info("init Druid Servlet Configuration ");
			ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), prefix);
			// IP白名单
			servletRegistrationBean.addInitParameter("allow", allow);
			// IP黑名单(共同存在时，deny优先于allow)
			servletRegistrationBean.addInitParameter("deny", deny);
			// 控制台管理用户
			servletRegistrationBean.addInitParameter("loginUsername", loginUsername);
			servletRegistrationBean.addInitParameter("loginPassword", loginPassword);
			// 是否能够重置数据 禁用HTML页面上的“Reset All”功能
			servletRegistrationBean.addInitParameter("resetEnable", resetEnable);
			return servletRegistrationBean;
	 
		}
	 
		@Bean
		public FilterRegistrationBean filterRegistrationBean() {
			FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
			filterRegistrationBean.addUrlPatterns("/*");
			filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico," + prefix);
			return filterRegistrationBean;
		}
	 
		@Bean(name = "writeDataSource", destroyMethod = "close", initMethod = "init")
		@Primary
		@ConfigurationProperties(prefix = "spring.master")
		public DataSource writeDataSource() {
			log.info("-------------------- writeDataSource init ---------------------");
			return DataSourceBuilder.create().type(dataSourceType).build();
		}
	 
		/**
		 * 有多少个从库就要配置多少个
		 * 
		 * @return
		 */
		@Bean(name = "readDataSource1")
		@ConfigurationProperties(prefix = "spring.slave")
		public DataSource readDataSourceOne() {
			log.info("-------------------- readDataSourceOne init ---------------------");
			return DataSourceBuilder.create().type(dataSourceType).build();
		}
	 
		@Bean("readDataSources")
		public List<DataSource> readDataSources() {
			List<DataSource> dataSources = new ArrayList<>();
			dataSources.add(readDataSourceOne());
			return dataSources;
		}


}
