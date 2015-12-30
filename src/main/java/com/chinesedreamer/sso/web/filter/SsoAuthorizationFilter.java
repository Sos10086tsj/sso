package com.chinesedreamer.sso.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.chinesedreamer.sso.authorization.exception.AppCodeOrKeyMissingExcpetion;
import com.chinesedreamer.sso.authorization.exception.AppNotAuthorizedExcpetion;
import com.chinesedreamer.sso.authorization.service.AuthorizationService;

/**
 * 授权过滤器，检查访问的app是否已经被授权
 * @author Paris
 *
 */
public class SsoAuthorizationFilter implements Filter{
	private Logger logger = LoggerFactory.getLogger(SsoAuthorizationFilter.class);
	
	private String[] whiteList;
	
	
	public String[] getWhiteList() {
		return whiteList;
	}

	public void setWhiteList(String[] whiteList) {
		this.whiteList = whiteList;
	}

	@Autowired
	private AuthorizationService authorizationService;
	

	public AuthorizationService getAuthorizationService() {
		return authorizationService;
	}

	public void setAuthorizationService(AuthorizationService authorizationService) {
		this.authorizationService = authorizationService;
	}

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (isWihteList((HttpServletRequest)request)) {
			chain.doFilter(request, response);
			return;
		}
		
		String appCode = request.getParameter("applicationCode");
		String appKey = request.getParameter("applicationKey");
		if (StringUtils.isEmpty(appCode) || StringUtils.isEmpty(appKey) ) {
			logger.error("",new AppCodeOrKeyMissingExcpetion("Missing appCode or appKey. appCode:" + appCode + ", appKey:" + appKey));
			return;
		}
		
		if (!this.authorizationService.applicationAuthorized(appCode, appKey)) {
			logger.error("", new AppNotAuthorizedExcpetion("Application:" + appCode + " is not authorized or key incorrect. Key:" + appKey));
			return;
			
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
	}
	
	private boolean isWihteList(HttpServletRequest request) {
		String ctx = request.getContextPath();
		
		String uri = request.getRequestURI();
		//url过滤掉ctx部分
		if (StringUtils.isNotEmpty(ctx)) {
			int index = uri.indexOf(ctx);
			uri = uri.substring(index + ctx.length());
		}
		
		if(null != whiteList && whiteList.length>=1){
			for(String whiteUrl : whiteList) {    
				if (uri.equals(whiteUrl) || 
					(whiteUrl.endsWith("/*") && uri.indexOf(whiteUrl.substring(0, whiteUrl.length() - 1)) != -1)
						) {
					return true;
				}
	         } 
		}
		
		return false;
	}

}
