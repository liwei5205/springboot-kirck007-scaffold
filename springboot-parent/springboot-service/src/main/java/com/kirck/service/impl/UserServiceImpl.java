package com.kirck.service.impl;


import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kirck.commen.RedisConstants;
import com.kirck.entity.User;
import com.kirck.mapper.UserMapper;
import com.kirck.service.UserService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kirck007
 * @since 2018-11-28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

	@Resource
    RedisTemplate<String,User> redisTemplate;
	
	@Override
	public User selectById(String id) {
		String cacheKey = RedisConstants.KEYPRE.KIRCK007+RedisConstants.OBJTYPE.USER+id;
		User user = redisTemplate.opsForValue().get(cacheKey);
		if(user == null) {
			user = this.getById(id);
			if(user==null) {
					return null;
			}else {
				redisTemplate.opsForValue().set(cacheKey, user);
			}
		}
		return user;
	}

}
