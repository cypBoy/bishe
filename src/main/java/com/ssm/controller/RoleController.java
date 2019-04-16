package com.ssm.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ssm.model.PageBean;
import com.ssm.model.Role;
import com.ssm.service.RoleService;
import com.ssm.util.ResponseUtil;
import com.ssm.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 角色Controller层
 * 
 * @author dell
 * @date 2019/04/02
 *
 */
@Controller
public class RoleController {

	private static final Logger logger= LoggerFactory.getLogger(UserController.class);

	@Autowired
	private RoleService roleService;
	
	/**
	 * 角色信息页面
	 */
	@RequestMapping("/roleManage")
	public String roleManage(ModelMap map) {
		return "roleManage";
	}
	
	/**
	 * 查询角色集合
	 * 
	 * @param page
	 * @param rows
	 * @param s_role
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/rolelist")
	public String list(@RequestParam(value = "page", required = false) String page,
					   @RequestParam(value = "rows", required = false) String rows, Role s_role, HttpServletResponse response)
			throws Exception {
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rolename", StringUtil.formatLike(s_role.getRolename()));
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		List<Role> roleList = roleService.findRole(map);
		Long total = roleService.getTotalRole(map);
		JSONObject result = new JSONObject();
		JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(roleList));
		result.put("rows", jsonArray);
		result.put("total", total);
		ResponseUtil.write(response, result);
		return null;
	}
	
	/**
	 * 添加与修改角色
	 * 
	 * @param role
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/rolesave")
	public String save(Role role, HttpServletResponse response) throws Exception {
		int resultTotal = 0; // 操作的记录条数
		JSONObject result = new JSONObject();
		if (role.getId() == null) {
			resultTotal = roleService.addRole(role);
		} else {
			resultTotal = roleService.updateRole(role);
		}

		if (resultTotal > 0) { // 执行成功
			result.put("errres", true);
			result.put("errmsg", "数据保存成功！");
		} else {
			result.put("errres", false);
			result.put("errmsg", "数据保存失败");
		}
		ResponseUtil.write(response, result);
		return null;
	}

	/**
	 * 删除角色
	 * 
	 * @param ids
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/roledelete")
	public String delete(@RequestParam(value = "ids") String ids, HttpServletResponse response) throws Exception {
		JSONObject result = new JSONObject();
		String[] idsStr = ids.split(",");
		for (int i = 0; i < idsStr.length; i++) {
			roleService.deleteRole(Integer.parseInt(idsStr[i]));
		}
		result.put("errres", true);
		result.put("errmsg", "数据删除成功！");
		ResponseUtil.write(response, result);
		return null;
	}
}
