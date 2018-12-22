package com.kirck.service.impl;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.kirck.service.IInitService;

@Service("initService")
public class InitServiceImpl extends AbstractService implements IInitService{
	
	@Resource
    RedisTemplate<String,Object> redisTemplate;

	/**
	 * @Description 初始化系统配置到redis
	 * 
	 * @author ye
	 * 2018年12月4日
	 */
	@Override
	public void initSysConfig() {
		logger.info("初始化系统配置数据缓存【开始】... ...");
		/*
		 * List<Map<String, Object>> list = this.dalClient.queryForList("Init.getSysConfigList", null);
			for (Map<String, Object> scp : list) {
				redisTemplate.opsForValue().set((String) scp.get("keyName"), (String) scp.get("value"));
			logger.info(scp.get("keyName") + ":" + (String) scp.get("value"));
			}
		 */
		
		logger.info("初始化系统配置数据缓存【结束】,共" + 0 + "条");
	}
}
