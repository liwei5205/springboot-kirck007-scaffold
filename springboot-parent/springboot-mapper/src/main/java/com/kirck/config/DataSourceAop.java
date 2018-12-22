package com.kirck.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataSourceAop {
	 private static Logger log = LoggerFactory.getLogger(DataSourceAop.class);

	@Before("execution(* com.kirck.mapper.*.select*(..)) || execution(* com.kirck.mapper.*.count*(..))")
	public void setReadDataSourceType() {
		DataSourceContextHolder.read();
		log.info("dataSource切换到：Read");
	}

	@Before("execution(* com.kirck.mapper.*.insert*(..)) || execution(* com.kirck.mapper.*.update*(..)) || execution(* com.kirck.mapper.*.delete*(..))")
	public void setWriteDataSourceType() {
		DataSourceContextHolder.write();
		log.info("dataSource切换到：write");
	}

}
