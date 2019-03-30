package com.ssm.service.impl;

import com.ssm.dao.RoleDao;
import com.ssm.model.Role;
import com.ssm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
/**
 * 角色Service实现类
 * @author dell
 * @date 2019/03/30
 */
@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleDao roleDao;
	
	@Override
	public List<Role> getRoles() {
		return roleDao.getRoles();
	}
	
	@Override
	public List<Role> findRole(Map<String,Object> map){
		return roleDao.findRole(map);
	}
	
	@Override
	public Long getTotalRole(Map<String,Object> map){
		return roleDao.getTotalRole(map);
	}
	
	@Override
	public int updateRole(Role role){
		return roleDao.updateRole(role);
	}
	
	@Override
	public int addRole(Role role){
		return roleDao.addRole(role);
	}
	
	@Override
	public int deleteRole(Integer id){
		return roleDao.deleteRole(id);
	}
}
