package com.chinesedreamer.sso.web.filter;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinesedreamer.sso.authorization.service.AuthorizationService;
import com.chinesedreamer.sso.exception.authorization.AppCodeOrKeyMissingExcpetion;
import com.chinesedreamer.sso.exception.authorization.AppNotAuthorizedExcpetion;

/**
 * 授权过滤器，检查访问的app是否已经被授权
 * @author Paris
 *
 */
public class SsoAuthorizationFilter implements Filter{
	private Logger logger = LoggerFactory.getLogger(SsoAuthorizationFilter.class);
	
	@Resource
	private AuthorizationService authorizationService;

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String appCode = request.getParameter("appCode");
		String appKey = request.getParameter("appKey");
		if (StringUtils.isEmpty(appCode) || StringUtils.isEmpty(appKey) ) {
			logger.error("{}",new AppCodeOrKeyMissingExcpetion("Missing appCode or appKey. appCode:" + appCode + ", appKey:" + appKey));
			return;
		}
		
		if (!this.authorizationService.applicationAuthorized(appCode, appKey)) {
			logger.error("{}", new AppNotAuthorizedExcpetion("Application:" + appCode + " is not authorized or key incorrect. Key:" + appKey));
			return;
			
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
	}

}
