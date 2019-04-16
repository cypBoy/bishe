package com.ssm.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ssm.model.Datadic;
import com.ssm.model.PageBean;
import com.ssm.model.Security;
import com.ssm.model.User;
import com.ssm.service.DatadicService;
import com.ssm.service.SecurityService;
import com.ssm.service.UserService;
import com.ssm.util.Constants;
import com.ssm.util.ResponseUtil;
import com.ssm.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 证券Controller层
 * 
 * @author dell
 * @date 2019/04/02
 *
 */
@Controller
public class SecurityController {

	private static final Logger logger= LoggerFactory.getLogger(UserController.class);

	@Autowired
	private SecurityService securityService;
	@Autowired
	private DatadicService datadicService;
    
	@Autowired
	private UserService userService;


	/**
	 * 证券信息管理页面
	 */
	@RequestMapping("/securityManage")
	public String securityManage(ModelMap map, HttpServletRequest request) {
		List<Datadic> list = datadicService.getDatadicSecurity();
		map.addAttribute("securitys", list);
		
		HttpSession session = request.getSession();
		User curuser = (User)session.getAttribute(Constants.CURRENT_USER_SESSION_KEY);
		Map<String, Object> userMap = new HashMap<String, Object>();
		userMap.put("userid", curuser.getId());
		userMap.put("roleid", curuser.getRoleid());
		List<User> userlist = userService.getAllUser(userMap);
		map.addAttribute("allUsers", userlist);
		return "securityManage";
	}

	/**
	 * 查询证券信息集合
	 * 
	 * @param page
	 * @param rows
	 * @param s_security
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/securitylist")
	public String list(@RequestParam(value = "page", required = false) String page,
					   @RequestParam(value = "rows", required = false) String rows, Security s_security, HttpServletResponse response)
			throws Exception {
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", StringUtil.formatLike(s_security.getUsername()));
		map.put("company", StringUtil.formatLike(s_security.getCompany()));
		map.put("dataid", s_security.getDataid());
		map.put("searchStarttime", s_security.getSearchStarttime());
		map.put("searchEndtime", s_security.getSearchEndtime());
		map.put("roleid", s_security.getRoleid());
		map.put("userid", s_security.getUserid());
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		List<Security> securityList = securityService.findSecurity(map);
		Long total = securityService.getTotalSecurity(map);
		JSONObject result = new JSONObject();
		JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(securityList));
		result.put("rows", jsonArray);
		result.put("total", total);
		ResponseUtil.write(response, result);
		return null;
	}

	/**
	 * 添加与修改证券信息
	 * 
	 * @param security
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/securitysave")
	public String save(Security security, HttpServletResponse response) throws Exception {
		int resultTotal = 0; // 操作的记录条数
		JSONObject result = new JSONObject();
		
		if (security.getId() == null) {
			resultTotal = securityService.addSecurity(security);
		} else {
			resultTotal = securityService.updateSecurity(security);
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
	 * 删除证券信息
	 * 
	 * @param ids
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/securitydelete")
	public String delete(@RequestParam(value = "ids") String ids, HttpServletResponse response) throws Exception {
		JSONObject result = new JSONObject();
		String[] idsStr = ids.split(",");
		for (int i = 0; i < idsStr.length; i++) {
			securityService.deleteSecurity(Integer.parseInt(idsStr[i]));
		}
		result.put("errres", true);
		result.put("errmsg", "数据删除成功！");
		ResponseUtil.write(response, result);
		return null;
	}

}
