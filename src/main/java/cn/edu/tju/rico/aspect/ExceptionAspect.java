package cn.edu.tju.rico.aspect;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import cn.edu.tju.rico.exception.TokenException;
import cn.edu.tju.rico.response.ResponseEntity;
import cn.edu.tju.rico.response.StatusCode;
/**
 * Title: 全局异常处理切面 Description: 利用 @ControllerAdvice + @ExceptionHandler
 * 组合处理Controller层RuntimeException异常
 * 
 * @author rico
 * @created 2017年7月4日 下午4:29:07
 */
@ControllerAdvice   // 控制器增强
@ResponseBody
public class ExceptionAspect {

	/** Log4j日志处理(@author: rico) */
	private static final Logger log = Logger.getLogger(ExceptionAspect.class);

	/**
	 * 400 - Bad Request
	 */
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity handleHttpMessageNotReadableException(
			HttpMessageNotReadableException e) {
		log.error("could_not_read_json..."+e.getMessage());
		ResponseEntity response = new ResponseEntity();
		response.setCode(StatusCode.FAILER.getCode());
		response.setMessage("could_not_read_json");
		return response;
	}

	/**
	 * 400 - Bad Request
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({MethodArgumentNotValidException.class})
	public ResponseEntity handleValidationException(MethodArgumentNotValidException e) {
		log.error("parameter_validation_exception..."+ e.getMessage());
		ResponseEntity response = new ResponseEntity();
		response.setCode(StatusCode.FAILER.getCode());
		response.setMessage("parameter_validation_exception");
		return response;
	}

	/**
	 * 405 - Method Not Allowed。HttpRequestMethodNotSupportedException
	 * 是ServletException的子类,需要Servlet API支持
	 */
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity handleHttpRequestMethodNotSupportedException(
			HttpRequestMethodNotSupportedException e) {
		log.error("request_method_not_supported..."+ e.getMessage());
		ResponseEntity response = new ResponseEntity();
		response.setCode(StatusCode.FAILER.getCode());
		response.setMessage("request_method_not_supported");
		return response;
	}

	/**
	 * 415 - Unsupported Media Type。HttpMediaTypeNotSupportedException
	 * 是ServletException的子类,需要Servlet API支持
	 */
	@ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
	@ExceptionHandler({ HttpMediaTypeNotSupportedException.class })
	public ResponseEntity handleHttpMediaTypeNotSupportedException(Exception e) {
		log.error("content_type_not_supported..."+ e.getMessage());
		ResponseEntity response = new ResponseEntity();
		response.setCode(StatusCode.FAILER.getCode());
		response.setMessage("content_type_not_supported");
		return response;
	}

	/**
	 * 500 - Internal Server Error
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(TokenException.class)
	public ResponseEntity handleTokenException(Exception e) {
		log.error("Token is invaild..."+ e.getMessage());
		ResponseEntity response = new ResponseEntity();
		response.setCode(StatusCode.FAILER.getCode());
		response.setMessage(e.getMessage());
		return response;
	}

	/**
	 * 500 - Internal Server Error
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public ResponseEntity handleException(Exception e) {
		log.error("Internal Server Error..."+ e.getMessage());
		ResponseEntity response = new ResponseEntity();
		response.setCode(StatusCode.FAILER.getCode());
		response.setMessage(e.getMessage());
		return response;
	}
}
