package com.ssm.service.impl;

import java.util.List;
import java.util.Map;
import com.ssm.dao.TradeDao;
import com.ssm.model.Trade;
import com.ssm.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.DateUtil;

/**
 * 收入Service实现类
 * @author dell
 * @date 2019/03/30
 */
@Service
public class TradeServiceImpl implements TradeService {

	@Autowired
	private TradeDao tradeDao;
	
	@Override
	public List<Trade> findTrade(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return tradeDao.findTrade(map);
	}

	@Override
	public Long getTotalTrade(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return tradeDao.getTotalTrade(map);
	}

	
	@Override
	public int addTrade(Trade trade) {
		// TODO Auto-generated method stub
		trade.setMoney(trade.getPrice()*trade.getNumber());
		trade.setCreatetime(DateUtil.getCurrentDateStr());
		return tradeDao.addTrade(trade);
	}

	@Override
	public int updateTrade(Trade trade) {
		// TODO Auto-generated method stub
		trade.setMoney(trade.getPrice()*trade.getNumber());
		trade.setUpdatetime(DateUtil.getCurrentDateStr());
		return tradeDao.updateTrade(trade);
	}

	@Override
	public int deleteTrade(Integer id) {
		// TODO Auto-generated method stub
		return tradeDao.deleteTrade(id);
	}


	

}
