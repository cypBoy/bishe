package com.ssm.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ssm.model.PageBean;
import com.ssm.model.Role;
import com.ssm.model.User;
import com.ssm.service.RoleService;
import com.ssm.service.UserService;
import com.ssm.util.Base64Util;
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
 * 用户Controller层
 * 
 * @author dell
 * @date 2019/03/31
 *
 */
@Controller
public class UserController {

	private static final Logger logger= LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;

	/**
	 * 用户登录页面
	 */
	@RequestMapping("/index")
	public String index(ModelMap map) {
		List<Role> list = roleService.getRoles();
		map.addAttribute("roles", list);
		return "login";
	}

	/**
	 * 用户登录
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("/login")
	public String login(User user, HttpServletRequest request, HttpServletResponse response) {
		JSONObject result = new JSONObject();
		User resultUsername = userService.loginUsername(user);
		if (resultUsername == null) {
			result.put("errres", 101);
			result.put("errmsg", "用户名不存在！");
			result.put("inputfocus", "inputUsername");
		} else {
			User resultPassword = userService.loginPassword(user);
			if (resultPassword == null){
				result.put("errres", 102);
				result.put("errmsg", "密码不正确！");
				result.put("inputfocus", "inputPassword");
			} else {
				User resultRolename = userService.loginRolename(user);
				if (resultRolename == null) {
					result.put("errres", 103);
					result.put("errmsg", "用户角色不匹配！");
					result.put("inputfocus", "rolename");
				} else {
					resultRolename.setPassword(Base64Util.decodeStr(resultRolename.getPassword(),"UTF-8"));
					HttpSession session = request.getSession();
					session.setAttribute(Constants.CURRENT_USER_SESSION_KEY, resultRolename);
					result.put("errres", 200);
					logger.info("失败");
				}
			}
		}
		ResponseUtil.write(response, result);
		return null;
	}
	
	/**
	 * 用户注册页面
	 */
	@RequestMapping("/sign")
	public String sign() {
		return "sign";
	}
	
	/**
	 * 注册用户操作
	 */
	@RequestMapping("/gosign")
	public String gosign(User user, HttpServletResponse response) throws Exception {
		int resultTotal = 0; // 操作的记录条数
		int resultTotalother = 0; // 操作的记录条数
		JSONObject result = new JSONObject();
		if (isUserExists(user)) {
			result.put("errres", false);
			result.put("errmsg", "用户名已经被使用！");
			result.put("inputfocus", "inputUsername");
			ResponseUtil.write(response, result);
			return null;
		}
		resultTotal = userService.addSign(user);
		resultTotalother = userService.addUserRole(user);

		if ((resultTotal > 0)&&(resultTotalother>0)) { // 执行成功
			result.put("errres", true);
			result.put("errmsg", "注册成功，请返回登录！");
		} else {
			result.put("errres", false);
			result.put("errmsg", "注册失败");
			result.put("inputfocus", "inputUsername");
		}
		ResponseUtil.write(response, result);
		return null;
	}

	/**
	 * 进入主页面
	 */
	@RequestMapping("/main")
	public String main(ModelMap map, HttpServletRequest request) {
		HttpSession session = request.getSession();
		User usersession = (User)session.getAttribute(Constants.CURRENT_USER_SESSION_KEY);
		List<Role> list = roleService.getRoles();
		map.addAttribute("roles", list);
		User usermessage = userService.getUserById(usersession.getId());
		usermessage.setPassword(Base64Util.decodeStr(usermessage.getPassword(),"UTF-8"));
		map.addAttribute("usermessage", usermessage);
		return "main";
	}

	/**
	 * 用户信息管理页面
	 */
	@RequestMapping("/userManage")
	public String userManage(ModelMap map) {
		List<Role> list = roleService.getRoles();
		map.addAttribute("roles", list);
		return "userManage";
	}

	/**
	 * 修改用户密码
	 * 
	 * @param user
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/modifyPassword")
	public String modifyPassword(User user, HttpServletResponse response) throws Exception {
		int resultTotal = userService.updateUser(user);
		JSONObject result = new JSONObject();
		if (resultTotal > 0) { // 执行成功
			result.put("success", true);
		} else {
			result.put("success", false);
		}
		ResponseUtil.write(response, result);
		return null;
	}

	/**
	 * 用户注销
	 * 
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/logout")
	public String logout(HttpSession session) throws Exception {
		session.removeAttribute(Constants.CURRENT_USER_SESSION_KEY);
		return "redirect:/index.do";
	}

	/**
	 * 查询用户集合
	 * 
	 * @param page
	 * @param rows
	 * @param s_user
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/userlist")
	public String list(@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "rows", required = false) String rows, User s_user, HttpServletResponse response)
			throws Exception {
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", StringUtil.formatLike(s_user.getUsername()));
		map.put("truename", StringUtil.formatLike(s_user.getTruename()));
		map.put("appellation", StringUtil.formatLike(s_user.getAppellation()));
		map.put("sex", s_user.getSex());
		map.put("roleid", s_user.getRoleid());
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		List<User> userList = userService.findUser(map);
		for(int i = 0; i < userList.size(); i++) {
			userList.get(i).setPassword(Base64Util.decodeStr(userList.get(i).getPassword(),"UTF-8"));
        }
		Long total = userService.getTotalUser(map);
		JSONObject result = new JSONObject();
		JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(userList));
		result.put("rows", jsonArray);
		result.put("total", total);
		ResponseUtil.write(response, result);
		return null;
	}

	/**
	 * 判断指定的用户是否已经存在
	 * @param user
	 * @return
	 * @throws Exception
	 */

	private boolean isUserExists(User user) throws Exception {
		long resultTotal = 0; // 操作的记录条数
		resultTotal = userService.getUserIsExists(user);
		boolean rs = false;
		if (resultTotal > 0) { // 执行成功
			rs = true;
		}
		return rs;
	}

	/**
	 * 添加与修改用户
	 * 
	 * @param user
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/usersave")
	public String save(User user, HttpServletResponse response) throws Exception {
		int resultTotal = 0; // 操作的记录条数
		JSONObject result = new JSONObject();
		if (isUserExists(user)) {
			result.put("errres", false);
			result.put("errmsg", "用户名已经被使用！");
			ResponseUtil.write(response, result);
			return null;
		}
		if (user.getId() == null) {
			resultTotal = userService.addUser(user);
			userService.addUserRole(user);
		} else {
			resultTotal = userService.updateUser(user);
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
	 * 删除用户
	 * 
	 * @param ids
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/userdelete")
	public String delete(@RequestParam(value = "ids") String ids, HttpServletResponse response) throws Exception {
		JSONObject result = new JSONObject();
		String[] idsStr = ids.split(",");
		for (int i = 0; i < idsStr.length; i++) {
			userService.deleteUser(Integer.parseInt(idsStr[i]));
		}
		result.put("errres", true);
		result.put("errmsg", "数据删除成功！");
		ResponseUtil.write(response, result);
		return null;
	}
	
	/**
	 * 更改主题
	 * @param currentTheme
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/changeTheme")
	public String changeTheme(
			@RequestParam(value = "currentTheme") String currentTheme,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JSONObject result = new JSONObject();
		HttpSession session = request.getSession();
		session.setAttribute("currentTheme", currentTheme);
		result.put("errres", true);
		result.put("errmsg", "主题切换成功！");
		ResponseUtil.write(response, result);
		return null;
	}
}
