package com.ssm.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ssm.model.Datadic;
import com.ssm.model.Income;
import com.ssm.model.PageBean;
import com.ssm.model.User;
import com.ssm.service.DatadicService;
import com.ssm.service.IncomeService;
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
 * 收入Controller层
 * 
 * @author dell
 * @date 2019/04/02
 *
 */
@Controller
public class IncomeController {
	private static final Logger logger= LoggerFactory.getLogger(UserController.class);

	@Autowired
	private IncomeService incomeService;
	@Autowired
	private DatadicService datadicService;
	@Autowired
	private UserService userService;


	/**
	 * 收入信息管理页面
	 */
	@RequestMapping("/incomeManage")
	public String incomeManage(ModelMap map, HttpServletRequest request) {
		List<Datadic> list = datadicService.getDatadicIncome();
		map.addAttribute("incomes", list);
		
		HttpSession session = request.getSession();
		User curuser = (User)session.getAttribute(Constants.CURRENT_USER_SESSION_KEY);
		Map<String, Object> userMap = new HashMap<String, Object>();
		userMap.put("userid", curuser.getId());
		userMap.put("roleid", curuser.getRoleid());
		List<User> userlist = userService.getAllUser(userMap);
		map.addAttribute("allUsers", userlist);
		return "incomeManage";
	}

	/**
	 * 查询用户收入集合
	 * 
	 * @param page
	 * @param rows
	 * @param s_income
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/incomelist")
	public String list(@RequestParam(value = "page", required = false) String page,
					   @RequestParam(value = "rows", required = false) String rows, Income s_income, HttpServletResponse response)
			throws Exception {
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("incomer", StringUtil.formatLike(s_income.getIncomer()));
		map.put("source", StringUtil.formatLike(s_income.getSource()));
		map.put("dataid", s_income.getDataid());
		map.put("starttime", s_income.getStarttime());
		map.put("endtime", s_income.getEndtime());
		map.put("roleid", s_income.getRoleid());
		map.put("userid", s_income.getUserid());
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		List<Income> incomeList = incomeService.findIncome(map);
		Long total = incomeService.getTotalIncome(map);
		JSONObject result = new JSONObject();
		JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(incomeList));
		result.put("rows", jsonArray);
		result.put("total", total);
		ResponseUtil.write(response, result);
		return null;
	}

	/**
	 * 添加与修改用户
	 * 
	 * @param income
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/incomesave")
	public String save(Income income, HttpServletResponse response) throws Exception {
		int resultTotal = 0; // 操作的记录条数
		JSONObject result = new JSONObject();
		
		if (income.getId() == null) {
			resultTotal = incomeService.addIncome(income);
		} else {
			resultTotal = incomeService.updateIncome(income);
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
	@RequestMapping("/incomedelete")
	public String delete(@RequestParam(value = "ids") String ids, HttpServletResponse response) throws Exception {
		JSONObject result = new JSONObject();
		String[] idsStr = ids.split(",");
		for (int i = 0; i < idsStr.length; i++) {
			incomeService.deleteIncome(Integer.parseInt(idsStr[i]));
		}
		result.put("errres", true);
		result.put("errmsg", "数据删除成功！");
		ResponseUtil.write(response, result);
		return null;
	}

}
