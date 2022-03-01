package com.dream.mvc.controller;

import com.dream.mvc.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/test")
public class TestController {

	@RequestMapping(
		value = {"/index", "/list"},
		method = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT}
	)
	public String index(HttpServletRequest request) {
		HttpSession session = request.getSession();
		return "test";
	}


	@GetMapping(value = "/goods", params = {"username", "password"})
	//public String goods(HttpServletRequest request) {
	//	System.out.println("username:" + request.getParameter("username"));
	//	System.out.println("password:" + request.getParameter("password"));
	//	return "test";
	//}
	public String goods(
		@RequestParam(value = "username", required = false, defaultValue = "hehe") String username,
		String password,
		@RequestHeader String host,
		@RequestHeader(value = "dream", required = false) String dream,
		@CookieValue(value = "token", required = false) String token,
		@CookieValue(value = "JSESSIONID", required = false) String cookie
	) {
		System.out.println("username:" + username);
		System.out.println("password:" + password);
		System.out.println("host:" + host);
		System.out.println("dream:" + dream);
		System.out.println("token:" + token);
		System.out.println("cookie:" + cookie);
		return "test";
	}


	@RequestMapping("/news/{id}")
	public String news(@PathVariable String id) {
		System.out.println("id:" + id);
		return "test";
	}

	// 如果客户端传的参数过多，建议用这种方式
	@RequestMapping("/moreParam")
	public String moreParam(User user) {
		System.out.println("username:" + user.getUsername());
		System.out.println("password:" + user.getPassword());
		return "test";
	}


	//HttpServletRequest向请求域共享数据
	@RequestMapping("/shareData1")
	public String shareData(HttpServletRequest request) {
		request.setAttribute("testData", "HttpServletRequest测试数据共享");
		return "test";
	}

	//ModelAndView向请求域共享数据
	@RequestMapping("/shareData2")
	public ModelAndView shareData2() {
		/**
		 * Modelandview有 Model和view的功能
		 * Model主要用于向请求域共享数据
		 * view主要用于设置视图,实现页面跳转
		 */
		ModelAndView mode = new ModelAndView();
		// 向请求域共享数据
		mode.addObject("testData", "ModelAndView共享数据");
		// 设置视图，实现页面跳转
		mode.setViewName("test");
		return mode;
	}

	//ModelAndView向请求域共享数据
	@RequestMapping("/shareData3")
	public String shareData3(Model model) {
		model.addAttribute("testData", "Mode 共享数据");
		return "test";
	}

	// java接口1 响应
	@RequestMapping("/list")
	public void user(HttpServletResponse response) {
		try {
			response.getWriter().print("[{name:'dream',age:18},{name:'七琪',age:19}]");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// java接口2 响应
	@RequestMapping("/obj")
	@ResponseBody
	public String obj(){
		return "{name: 'dream',age:18}";
	}

	// java接口3 响应
	@RequestMapping("/login")
	@ResponseBody
	public String login(){
		return "登录成功";
	}
}
