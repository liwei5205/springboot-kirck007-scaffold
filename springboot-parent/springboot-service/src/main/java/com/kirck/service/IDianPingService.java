package com.kirck.service;

import java.util.List;

import com.kirck.entity.MerchantDeal;

public interface IDianPingService {
	
	/**
	 * 保存团购信息
	 * @param merchantDeals
	 */
	void saveOrUpdate(List<MerchantDeal> merchantDeals);

	/**
	 * 获取最近一条记录的url_id
	 * @return
	 */
	MerchantDeal getTheLastDeal();
	
	/**
	 * 更新
	 * @param lastDeal
	 */
	void update(MerchantDeal lastDeal);
}
