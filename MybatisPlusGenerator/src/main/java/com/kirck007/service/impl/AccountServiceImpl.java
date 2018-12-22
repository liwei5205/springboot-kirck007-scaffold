package com.kirck007.service.impl;

import com.kirck007.entity.Account;
import com.kirck007.mapper.AccountMapper;
import com.kirck007.service.AccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 账号表 服务实现类
 * </p>
 *
 * @author kirck007
 * @since 2018-12-22
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

}
