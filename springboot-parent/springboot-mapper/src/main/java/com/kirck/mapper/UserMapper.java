package com.kirck.mapper;

import com.kirck.entity.User;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author kirck007
 * @since 2018-11-28
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
