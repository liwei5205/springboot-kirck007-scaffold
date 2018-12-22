package com.kirck.controller;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kirck.service.IInitService;

import io.swagger.annotations.Api;

/**
 * 初始化系统相关的操作conturoller
 */
@Controller
@RequestMapping("/init")
@Api(tags = { "init Conturoller" })
public class Init extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(Init.class);

	@Autowired
	private IInitService initService;

	@PostConstruct
	public void init() {
		logger.info("初始化系统配置...");
		initService.initSysConfig();
		logger.info("初始化系统配置结束...");
	}

}
