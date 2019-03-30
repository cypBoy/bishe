package com.ssm.service.impl;

import com.ssm.dao.DatadicDao;
import com.ssm.model.Datadic;
import com.ssm.service.DatadicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
/**
 * 数据字典Service接口实现类
 * @author dell
 * @date 2019/03/30
 *
 */
@Service
public class DatadicServiceImpl implements DatadicService {
	@Autowired
	private DatadicDao datadicDao;

	@Override
	public List<Datadic> getDatadicIncome() {
		// TODO Auto-generated method stub
		return datadicDao.getDatadicIncome();
	}

	@Override
	public List<Datadic> getDatadicPay() {
		// TODO Auto-generated method stub
		return datadicDao.getDatadicPay();
	}

	@Override
	public List<Datadic> getDatadicSecurity() {
		// TODO Auto-generated method stub
		return datadicDao.getDatadicSecurity();
	}

	@Override
	public List<Datadic> findDatadic(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return datadicDao.findDatadic(map);
	}

	@Override
	public Long getTotalDatadic(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return datadicDao.getTotalDatadic(map);
	}

	@Override
	public int updateDatadic(Datadic datadic) {
		// TODO Auto-generated method stub
		return datadicDao.updateDatadic(datadic);
	}

	@Override
	public int addDatadic(Datadic datadic) {
		// TODO Auto-generated method stub
		return datadicDao.addDatadic(datadic);
	}

	@Override
	public int deleteDatadic(Integer id) {
		// TODO Auto-generated method stub
		return datadicDao.deleteDatadic(id);
	}
	@Override
	public List<Datadic> getDatadicname(){
		return datadicDao.getDatadicname();
	}

	@Override
	public List<Datadic> getDatadicTrade() {
		// TODO Auto-generated method stub
		return datadicDao.getDatadicTrade();
	}
	
}
