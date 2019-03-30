package com.ssm.service.impl;

import com.ssm.dao.PayDao;
import com.ssm.model.Pay;
import com.ssm.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.DateUtil;

import java.util.List;
import java.util.Map;

/**
 * 支出Service实现类
 *
 * @author dell
 * @date 2019/03/30
 */
@Service
public class PayServiceImpl implements PayService {
	@Autowired
	private PayDao payDao;

	@Override
	public List<Pay> findPay(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return payDao.findPay(map);
	}
	
	@Override
	public List<Pay> getPayLine(Map<String,Object> map){
		return payDao.getPayLine(map);
	}

	@Override
	public long getTotalPay(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return payDao.getTotalPay(map);
	}

	@Override
	public int updatePay(Pay pay) {
		// TODO Auto-generated method stub
		pay.setUpdatetime(DateUtil.getCurrentDateStr());
		return payDao.updatePay(pay);
	}

	@Override
	public int addPay(Pay pay) {
		// TODO Auto-generated method stub
		pay.setCreatetime(DateUtil.getCurrentDateStr());;	
		return payDao.addPay(pay);
	}

	@Override
	public int deletePay(Integer id) {
		// TODO Auto-generated method stub
		return payDao.deletePay(id);
	}
	
	@Override
	public List<Pay> getPayer(){
		return payDao.getPayer();
	}

}
