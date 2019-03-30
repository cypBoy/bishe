package com.ssm.service.impl;

import com.ssm.dao.SecurityDao;
import com.ssm.model.Security;
import com.ssm.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.DateUtil;

import java.util.List;
import java.util.Map;

/**
 * 收入Service实现类
 * @author dell
 * @date 2019/03/30
 */
@Service
public class SecurityServiceImpl implements SecurityService {
	@Autowired
	private SecurityDao securityDao;

	@Override
	public List<Security> findSecurity(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return securityDao.findSecurity(map);
	}

	@Override
	public Long getTotalSecurity(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return securityDao.getTotalSecurity(map);
	}

	@Override
	public int updateSecurity(Security security) {
		// TODO Auto-generated method stub
		security.setUpdatetime(DateUtil.getCurrentDateStr());
		return securityDao.updateSecurity(security);
	}

	@Override
	public int addSecurity(Security security) {
		// TODO Auto-generated method stub
		security.setCreatetime(DateUtil.getCurrentDateStr());
		return securityDao.addSecurity(security);
	}

	@Override
	public int deleteSecurity(Integer id) {
		// TODO Auto-generated method stub
		return securityDao.deleteSecurity(id);
	}

	@Override
	public List<Security> getAllSecurity() {
		// TODO Auto-generated method stub
		return securityDao.getAllSecurity();
	}

}
