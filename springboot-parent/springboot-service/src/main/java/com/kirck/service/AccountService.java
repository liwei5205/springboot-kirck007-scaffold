package com.kirck.service;

import com.kirck.entity.Account;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kirck.commen.result.JsonResult;

/**
 * <p>
 * 账号表 服务类
 * </p>
 *
 * @author kirck007
 * @since 2018-12-22
 */
public interface AccountService extends IService<Account> {

	JsonResult<Account> login(Account account, String vcode);

}
