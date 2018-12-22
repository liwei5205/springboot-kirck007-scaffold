package com.kirck.service.impl;


import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kirck.commen.constants.RedisConstants;
import com.kirck.entity.Account;
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
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

	@Resource
    RedisTemplate<String,User> redisTemplate;
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	@DS("slave")
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
		User selectByIdMaster = this.selectByIdMaster(id);
		System.out.println(selectByIdMaster.getUsername());
		User selectByIdSlave = this.selectByIdSlave(id);
		System.out.println(selectByIdSlave.getUsername());
		return user;
	}
	
	
	@DS("slave")
	public User selectByIdSlave(String id) {
		return this.getById(id);
	}
	
	public User selectByIdMaster(String id) {
		return this.getById(id);
	}

}
