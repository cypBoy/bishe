package com.ssm.service;

import com.ssm.model.Trade;

import java.util.List;
import java.util.Map;

/**
 * @author dell
 * @date 2019/03/30
 */
public interface TradeService {
	/**
	 * 查询流水账
	 * @param map
	 * @return
	 */
	public List<Trade> findTrade(Map<String, Object> map);
	
	/**
	 * 获取流水账记录数
	 * @param map
	 * @return
	 */
	public Long getTotalTrade(Map<String, Object> map);
	
	/**
	 * 更新用户
	 * @param trade
	 * @return
	 */
	public int updateTrade(Trade trade);
	
	/**
	 * 添加流水账
	 * @param trade
	 * @return
	 */
	public int addTrade(Trade trade);
	
	
	/**
	 * 删除流水账
	 * @param id
	 * @return
	 */
	public int deleteTrade(Integer id);


}
