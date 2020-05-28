/**
 * 2019 东方金信
 *
 *
 *
 *
 */

package io.dfjinxin.common.exception;

import io.dfjinxin.common.utils.R;
//import org.apache.shiro.authz.AuthorizationException;
//import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

/**
 * 异常处理器
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestControllerAdvice
public class RRExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 处理自定义异常
	 */
	@ExceptionHandler(RRException.class)
	public R handleRRException(RRException e, HttpServletRequest request){
		logger.error("url: {}, message: {}, e: {}", request.getRequestURL(), e.getMessage(), e);
		R r = new R();
		r.put("code", e.getCode());
		r.put("msg", e.getMessage());

		return r;
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public R handlerNoFoundException(Exception e, HttpServletRequest request) {
		logger.error("url: {}, message: {}, e: {}", request.getRequestURL(), e.getMessage(), e);
		return R.error(404, "路径不存在，请检查路径是否正确");
	}

	@ExceptionHandler(DuplicateKeyException.class)
	public R handleDuplicateKeyException(DuplicateKeyException e, HttpServletRequest request){
		logger.error("url: {}, message: {}, e: {}", request.getRequestURL(), e.getMessage(), e);
		return R.error("数据库中已存在该记录");
	}

//	@ExceptionHandler(AuthorizationException.class)
//	public R handleAuthorizationException(AuthorizationException e){
//		logger.error(e.getMessage(), e);
//		return R.error("没有权限，请联系管理员授权");
//	}
//
//	@ExceptionHandler(UnauthorizedException.class)
//	public R handleAuthorizationException(UnauthorizedException e){
//		logger.error(e.getMessage(), e);
//		return R.error(e.getMessage());
//	}

	@ExceptionHandler(Exception.class)
	public R handleException(Exception e, HttpServletRequest request){
		logger.error("url: {}, message: {}, e: {}", request.getRequestURL(), e.getMessage(), e);
		logger.error(e.getMessage(), e);
		return R.error(e.getMessage());
	}

	/**
	 * 处理自定义异常，未登录或者登陆失效
	 */
	@ExceptionHandler(LoginException.class)
	public R handleRRException(LoginException e, HttpServletRequest request){
//		logger.error("url: {}, message: {}, e: {}", request.getRequestURL(), e.getMessage(), e);
		logger.error("url: {}, message: {}", request.getRequestURL(), e.getMessage());

		R r = new R();
		r.put("code", e.getCode());
		r.put("msg", e.getMessage());
		return r;
	}
}
