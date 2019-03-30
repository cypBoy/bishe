package com.ssm.dao;

import com.ssm.model.Role;
import java.util.List;
import java.util.Map;


/**
 * 角色dao层
 * @author dell
 * @date 2019/03/30
 *
 */
public interface RoleDao {
	/**
	 * 获得所有角色
	 * @return
	 */
	public List<Role> getRoles();
	
	/**
	 * 查询角色
	 * @param map
	 * @return
	 */
	public List<Role> findRole(Map<String, Object> map);
	
	/**
	 * 获取角色记录数
	 * @param map
	 * @return
	 */
	public Long getTotalRole(Map<String, Object> map);
	
	/**
	 * 更新角色
	 * @param role
	 * @return
	 */
	public int updateRole(Role role);
	
	/**
	 * 添加角色
	 * @param role
	 * @return
	 */
	public int addRole(Role role);
	
	/**
	 * 删除角色
	 * @param id
	 * @return
	 */
	public int deleteRole(Integer id);
}
