package cn.edu.tju.rico.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.tju.rico.annotation.IgnoreSecurity;
import cn.edu.tju.rico.authorization.TokenManager;
import cn.edu.tju.rico.response.ResponseEntity;
import cn.edu.tju.rico.response.StatusCode;
import cn.edu.tju.rico.service.UserService;
import cn.edu.tju.rico.utils.Constants;
  
/**        
 * Title: Token的管理    
 * Description: 处理用户的登录、登出操作
 * @author rico       
 * @created 2017年7月4日 下午4:52:58    
 */      
@RestController
@RequestMapping("/tokens")
public class TokenController {
	@Autowired
	private UserService userService;
	@Autowired
	private TokenManager tokenManager;
	private static final Logger log = Logger.getLogger(TokenController.class);
	
	/**     
	 * @description 登录处理
	 * @author rico       
	 * @created 2017年7月4日 下午4:53:58     
	 */
	@RequestMapping(method = RequestMethod.POST)
	@IgnoreSecurity
	public ResponseEntity login(@RequestParam("uname") String uname,
			@RequestParam("passwd") String passwd) {
		boolean flag = userService.login(uname, passwd);
		ResponseEntity response = new ResponseEntity();
		response.setCode(StatusCode.FAILER.getCode());
		response.setMessage("Login Failure...");
		if (flag) {
			String token = tokenManager.createToken(uname);
			log.debug("Token: " + token);
			response.setCode(StatusCode.SUCCESS.getCode());
			response.setMessage("Logout Success...");
			Map<String,String> map = new HashMap<>();
			map.put("X-Token", token);
			response.setData(map);
		}
		return response;
	}

	/**     
	 * @description 登出处理
	 * @author rico       
	 * @created 2017年7月4日 下午4:53:58     
	 */
	@RequestMapping(method = RequestMethod.DELETE)
	@IgnoreSecurity
	public ResponseEntity logout(HttpServletRequest request) {
		String token = request.getHeader(Constants.DEFAULT_TOKEN_NAME);
		tokenManager.deleteToken(token);
		log.debug("Logout Success...");
		ResponseEntity response = new ResponseEntity();
		response.setCode(StatusCode.SUCCESS.getCode());
		response.setMessage("Logout Success...");
		return response;
	}
}
