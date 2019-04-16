package com.ssm.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ssm.model.Datadic;
import com.ssm.model.PageBean;
import com.ssm.model.Pay;
import com.ssm.model.User;
import com.ssm.service.DatadicService;
import com.ssm.service.PayService;
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
 * 支出Controller层
 * 
 * @author dell
 * @date 2019/04/02
 *
 */
@Controller
public class PayController {

	private static final Logger logger= LoggerFactory.getLogger(UserController.class);

	@Autowired
	private PayService payService;
	@Autowired
	private DatadicService datadicService;
	@Autowired
	private UserService userService;
	

	/**
	 * 支出信息管理页面
	 */
	@RequestMapping("/payManage")
	public String payManage(ModelMap map, HttpServletRequest request) {
		List<Datadic> list = datadicService.getDatadicPay();
		map.addAttribute("pays", list);
		HttpSession session = request.getSession();
		User curuser = (User)session.getAttribute(Constants.CURRENT_USER_SESSION_KEY);
		Map<String, Object> userMap = new HashMap<String, Object>();
		userMap.put("userid", curuser.getId());
		userMap.put("roleid", curuser.getRoleid());
		List<User> userlist = userService.getAllUser(userMap);
		map.addAttribute("allUsers", userlist);
		return "payManage";
	}

	/**
	 * 查询用户收入集合
	 * 
	 * @param page
	 * @param rows
	 * @param s_pay
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/paylist")
	public String list(@RequestParam(value = "page", required = false) String page,
					   @RequestParam(value = "rows", required = false) String rows, Pay s_pay, HttpServletResponse response)
			throws Exception {
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("payer", StringUtil.formatLike(s_pay.getPayer()));
		map.put("tword", StringUtil.formatLike(s_pay.getTword()));
		map.put("dataid", s_pay.getDataid());
		map.put("starttime", s_pay.getStarttime());
		map.put("endtime", s_pay.getEndtime());
		map.put("roleid", s_pay.getRoleid());
		map.put("userid", s_pay.getUserid());
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		List<Pay> payList = payService.findPay(map);
		Long total = payService.getTotalPay(map);
		JSONObject result = new JSONObject();
		JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(payList));
		result.put("rows", jsonArray);
		result.put("total", total);
		ResponseUtil.write(response, result);
		return null;
	}

	/**
	 * 添加与修改支出
	 * 
	 * @param pay
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/paysave")
	public String save(Pay pay, HttpServletResponse response) throws Exception {
		int resultTotal = 0; // 操作的记录条数
		JSONObject result = new JSONObject();
		
		if (pay.getId() == null) {
			resultTotal = payService.addPay(pay);
		} else {
			resultTotal = payService.updatePay(pay);
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
	@RequestMapping("/paydelete")
	public String delete(@RequestParam(value = "ids") String ids, HttpServletResponse response) throws Exception {
		JSONObject result = new JSONObject();
		String[] idsStr = ids.split(",");
		for (int i = 0; i < idsStr.length; i++) {
			payService.deletePay((Integer.parseInt(idsStr[i])));
		}
		result.put("errres", true);
		result.put("errmsg", "数据删除成功！");
		ResponseUtil.write(response, result);
		return null;
	}
	

}
