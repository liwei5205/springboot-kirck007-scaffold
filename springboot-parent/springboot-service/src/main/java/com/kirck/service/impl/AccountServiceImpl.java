package com.kirck.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kirck.commen.constants.ResultStatus;
import com.kirck.commen.result.JsonResult;
import com.kirck.commen.utils.RegexUtil;
import com.kirck.entity.Account;
import com.kirck.mapper.AccountMapper;
import com.kirck.service.AccountService;
import com.kirck.service.UserService;

/**
 * <p>
 * 账号表 服务实现类
 * </p>
 *
 * @author kirck007
 * @since 2018-12-22
 */
@Service("accountService")
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService{

    protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private AccountMapper accountMapper;
	
	@Override
	public JsonResult<Account> login(Account account, String vcode) {
		JsonResult<Account> result = new JsonResult<>();
		Account mem = null;
		//先校验登录名
		if (RegexUtil.isEmail(account.getLoginId())) {
			mem = getAccountByEmail(account.getLoginId());
		} else if (RegexUtil.isMobile(account.getLoginId())) {
			mem = getAccountByPhone(account.getLoginId());
		} else if (RegexUtil.isLoginname(account.getLoginId())) {
			mem = getAccountrByLoginName(account.getLoginId());
		} else {
			result.setCode(ResultStatus.FAIL);
			result.setMsg("账号不存在");
			logger.info("----账号不存在");
			return result;
		}
		return null;
	}
	
	/**
	 * 通过登录名查询Account
	 * @param loginName
	 * @return
	 */
	private Account getAccountrByLoginName(String loginName) {
        QueryWrapper<Account> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("login_id", loginName);
		return accountMapper.selectOne(queryWrapper);
	}

	/**
	 * 通过电话号码查询Account
	 * @param phone
	 * @return
	 */
	private Account getAccountByPhone(String phone) {
        QueryWrapper<Account> queryWrapper = new QueryWrapper<>();
		return accountMapper.getAccountByPhone(phone);
	}
	
	/**
	 * 通过邮箱查询Account
	 * @param email
	 * @return
	 */
	private Account getAccountByEmail(String email) {
		return accountMapper.getAccountByEmail(email);
	}

}
