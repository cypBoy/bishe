package com.ssm.service.impl;

import com.ssm.dao.IncomeDao;
import com.ssm.model.Income;
import com.ssm.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ssm.util.DateUtil;

import java.util.List;
import java.util.Map;

/**
 * 收入Service实现类
 * 
 * @author dell
 * @date 2019/03/30
 */
@Service
public class IncomeServiceImpl implements IncomeService {

	@Autowired
	private IncomeDao incomeDao;
	
	@Override
	public List<Income> findIncome(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return incomeDao.findIncome(map);
	}
	
	@Override
	public List<Income> getIncomeLine(Map<String,Object> map){
		return incomeDao.getIncomeLine(map);
	}

	@Override
	public Long getTotalIncome(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return incomeDao.getTotalIncome(map);
	}

	
	@Override
	public int addIncome(Income income) {
		// TODO Auto-generated method stub
		income.setCreatetime(DateUtil.getCurrentDateStr());
		return incomeDao.addIncome(income);
	}

	@Override
	public int updateIncome(Income income) {
		// TODO Auto-generated method stub
		income.setUpdatetime(DateUtil.getCurrentDateStr());
		return incomeDao.updateIncome(income);
	}

	@Override
	public int deleteIncome(Integer id) {
		// TODO Auto-generated method stub
		return incomeDao.deleteIncome(id);
	}
	
	@Override
	public List<Income> getIncomer(){
		return incomeDao.getIncomer();
	}

}
