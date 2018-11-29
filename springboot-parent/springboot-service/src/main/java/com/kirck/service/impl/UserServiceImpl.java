package com.kirck.service.impl;


import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

}
