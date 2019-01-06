package com.kirck.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kirck.entity.MerchantDeal;
import com.kirck.mapper.MerchantDealMapper;
import com.kirck.service.IDianPingService;

@Service("dianPingService")
public class DianPingServiceImpl extends AbstractService implements IDianPingService {
	@Autowired
	private MerchantDealMapper merchantDealMapper;

	@Override
	public void saveOrUpdate(List<MerchantDeal> merchantDeals) {
		for (MerchantDeal merchantDeal : merchantDeals) {
			merchantDealMapper.insert(merchantDeal);
		}
	}
	
}
