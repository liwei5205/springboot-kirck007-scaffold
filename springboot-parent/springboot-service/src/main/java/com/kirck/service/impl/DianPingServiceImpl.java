package com.kirck.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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

	@Override
	public String getTheLastDeal() {
		QueryWrapper<MerchantDeal> merchantDealWrapper = new QueryWrapper<MerchantDeal>();
		merchantDealWrapper.orderByDesc("create_date");
		MerchantDeal merchantDeal = merchantDealMapper.selectOne(merchantDealWrapper);
		return merchantDeal==null?"-1":merchantDeal.getDianpingUrlId();
	}

}
