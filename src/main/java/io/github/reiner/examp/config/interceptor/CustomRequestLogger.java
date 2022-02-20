package io.github.reiner.examp.config.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import io.github.reinershir.auth.core.security.reqlog.RequestLogger;
import io.github.reinershir.auth.entity.RequestLog;

@Component
public class CustomRequestLogger implements RequestLogger{
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 复写框架自带的拦截器，可在此处将请求信息保存到日志中
	 */
	@Override
	public void processRequestLog(HttpServletRequest request, RequestLog requestLog) {
		logger.info("请求接口名称:{} \t 请求地址:{} \t 请求用户的id:{} \t  请求用户IP:{} \n  请求参数内容:{}",
				requestLog.getRequestName(),requestLog.getRequestUri(),requestLog.getUserId(),requestLog.getRequestIp(),
				StringUtils.isEmpty(requestLog.getRequestBody())?request.getQueryString():requestLog.getRequestBody());
		//TODO 保存请求信息到日志表
	}

	@Override
	public void processResponseLog(HttpServletResponse response) {
	}

}
