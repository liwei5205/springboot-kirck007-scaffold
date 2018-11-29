package com.kirck007.service.impl;

import com.kirck007.entity.User;
import com.kirck007.mapper.UserMapper;
import com.kirck007.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
