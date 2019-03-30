package com.ssm.dao;


import com.ssm.model.Trade;
import java.util.List;
import java.util.Map;

/**
 * @author dell
 * @date 2019/03/30
 */
public interface TradeDao {
	
	/**
	 * 查询收入
	 * @param map
	 * @return
	 */
	public List<Trade> findTrade(Map<String, Object> map);
	
	/**
	 * 获取收入记录数
	 * @param map
	 * @return
	 */
	public Long getTotalTrade(Map<String, Object> map);
	
	/**
	 * 更新收入
	 * @param trade
	 * @return
	 */
	public int updateTrade(Trade trade);
	
	/**
	 * 添加收入
	 * @param trade
	 * @return
	 */
	public int addTrade(Trade trade);
	
	
	/**
	 * 删除收入
	 * @param id
	 * @return
	 */
	public int deleteTrade(Integer id);

}
