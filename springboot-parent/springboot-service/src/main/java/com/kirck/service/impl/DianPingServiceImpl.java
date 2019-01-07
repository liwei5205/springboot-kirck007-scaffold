package com.kirck.service.impl;

import java.time.LocalDateTime;
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
			merchantDeal.setCreateDate(LocalDateTime.now());
			merchantDealMapper.insert(merchantDeal);
		}
	}

	@Override
	public MerchantDeal getTheLastDeal() {
		QueryWrapper<MerchantDeal> merchantDealWrapper = new QueryWrapper<MerchantDeal>();
		merchantDealWrapper.orderByDesc("create_date");
		merchantDealWrapper.last("limit 1");
		return merchantDealMapper.selectOne(merchantDealWrapper);
	}

	@Override
	public void update(MerchantDeal lastDeal) {
		merchantDealMapper.updateById(lastDeal);
	}

}
