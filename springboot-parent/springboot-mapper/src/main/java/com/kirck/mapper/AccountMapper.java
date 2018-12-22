package com.kirck.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kirck.entity.Account;

/**
 * <p>
 * 账号表 Mapper 接口
 * </p>
 *
 * @author kirck007
 * @since 2018-12-22
 */
@Mapper
public interface AccountMapper extends BaseMapper<Account> {

	Account getAccountByPhone(String phone);

	Account getAccountByEmail(String email);

}
