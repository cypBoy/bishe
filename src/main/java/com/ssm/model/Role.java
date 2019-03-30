package com.ssm.model;
/**
 * 角色实体
 * @author dell
 * @date 2019/03/30
 *
 */
public class Role {
	private Integer id; // 编号
	private String rolename; // 角色名
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
}
