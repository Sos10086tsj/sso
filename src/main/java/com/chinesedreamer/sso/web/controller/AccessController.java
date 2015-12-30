package com.chinesedreamer.sso.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinesedreamer.sso.access.service.AccessService;
import com.chinesedreamer.sso.api.ApiResult;
import com.chinesedreamer.sso.util.IpUtil;

@Controller
@RequestMapping(value = "access")
public class AccessController {
	
	@Resource
	private AccessService accessService;
	
	@ResponseBody
	@RequestMapping(value = "getUserSession", method = RequestMethod.POST)
	public ApiResult getUserSession(Model model, HttpServletRequest request){
		String applicationCode = request.getParameter("applicationCode");
		String username = request.getParameter("username");
		return this.accessService.getSsoUserSession(applicationCode, username, request.getSession().getId(), IpUtil.getIpAddr(request));
	}
	
	@ResponseBody
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ApiResult login(Model model, HttpServletRequest request){
		String applicationCode = request.getParameter("applicationCode");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//String sessionId = request.getParameter("sessionId"); 
		String applicationSessionId = request.getParameter("applicationSessionId");
		String ip = request.getParameter("ip");
		return this.accessService.loginSso(applicationCode, username, password, applicationSessionId, ip);
	}
}
