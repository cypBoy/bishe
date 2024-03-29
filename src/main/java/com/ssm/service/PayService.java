package com.ssm.service;

import com.ssm.model.Pay;

import java.util.List;
import java.util.Map;

/**
 * @author dell
 * @date 2019/03/30
 */
public interface PayService {
	/**
	 * 查询支出
	 * @param map
	 * @return
	 */
	public List<Pay> findPay(Map<String, Object> map);
	
	/**
	 * 报表生成获得支出曲线数据
	 * @param map
	 * @return
	 */
	public List<Pay> getPayLine(Map<String, Object> map);
	
	/**
	 * 获取支出记录数
	 * @param map
	 * @return
	 */
	public long getTotalPay(Map<String, Object> map);
	
	/**
	 * 更新支出
	 * @param pay
	 * @return
	 */
	public int updatePay(Pay pay);
	
	/**
	 * 添加支出
	 * @param pay
	 * @return
	 */
	public int addPay(Pay pay);
	
	/**
	 * 删除支出
	 * @param id
	 * @return
	 */
	public int deletePay(Integer id);
	
	/**
	 * 获得所有支出人
	 * @return
	 */
	public List<Pay> getPayer();

}
