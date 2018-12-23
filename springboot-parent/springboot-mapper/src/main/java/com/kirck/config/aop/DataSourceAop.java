package com.kirck.config.aop;

import com.kirck.config.datasource.DataSourceContextHolder;
import com.kirck.config.annotation.DS;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(-1)
public class DataSourceAop {
	 private static Logger log = LoggerFactory.getLogger(DataSourceAop.class);

	@Pointcut("@within(com.kirck.config.annotation.DS) || @annotation(com.kirck.config.annotation.DS)")
	public void pointCut(){
	}

	@Before("@annotation(ds)")
	public void setWriteDataSourceType(DS ds) {
		String dataType = ds.value();
		if(dataType.isEmpty()||dataType.startsWith("master")){
			DataSourceContextHolder.read();
			log.info("dataSource切换到："+ (StringUtils.isBlank(dataType)?"master":dataType));
		}else {
			DataSourceContextHolder.write();
			log.info("dataSource切换到："+dataType);
		}
	}

	@After("pointCut()")
	public void doAfter(){
		DataSourceContextHolder.clear();
	}
}
