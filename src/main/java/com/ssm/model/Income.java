package com.ssm.model;

/**
 * @author dell
 * @date 2019/03/30
 */
public class Income {
	private Integer id; // 编号
	private Integer userid; // 创建人ID
	private Integer roleid; // 创建人角色
	private String incomer; // 收入人
	private String source; // 来源
	private Integer money; // 金额
	private Integer dataid; // 类型
	private String content; // 备注
	private String incometime; // 收入时间
	private String createtime; // 创建时间
	private String updatetime; // 更新时间

	private String datadicname; // 数据字典名字
	private String datadicvalue; // 数据字典值
	private String username;// 记录人
	
	private String starttime;//搜索起始时间
	private String endtime;//搜索截止时间
	
	
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public Integer getRoleid() {
		return roleid;
	}
	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}
	public String getIncomer() {
		return incomer;
	}
	public void setIncomer(String incomer) {
		this.incomer = incomer;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public Integer getMoney() {
		return money;
	}
	public void setMoney(Integer money) {
		this.money = money;
	}
	public Integer getDataid() {
		return dataid;
	}
	public void setDataid(Integer dataid) {
		this.dataid = dataid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getIncometime() {
		return incometime;
	}
	public void setIncometime(String incometime) {
		this.incometime = incometime;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	public String getDatadicname() {
		return datadicname;
	}
	public void setDatadicname(String datadicname) {
		this.datadicname = datadicname;
	}
	public String getDatadicvalue() {
		return datadicvalue;
	}
	public void setDatadicvalue(String datadicvalue) {
		this.datadicvalue = datadicvalue;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

}
