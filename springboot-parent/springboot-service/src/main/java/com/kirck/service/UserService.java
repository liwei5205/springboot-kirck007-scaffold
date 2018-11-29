package com.kirck.service;

import com.kirck.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kirck007
 * @since 2018-11-28
 */
public interface UserService extends IService<User> {

	User selectById(String id);

}
