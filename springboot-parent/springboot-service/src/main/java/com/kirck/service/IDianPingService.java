package com.kirck.service;

import java.util.List;

import com.kirck.entity.MerchantDeal;

public interface IDianPingService {
	
	/**
	 * 保存团购信息
	 * @param merchantDeals
	 */
	void saveOrUpdate(List<MerchantDeal> merchantDeals);

}
