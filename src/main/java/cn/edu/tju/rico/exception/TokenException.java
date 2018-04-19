package cn.edu.tju.rico.exception;

/**
 * Title: 自定义的RuntimeException
 * Description:Token过期时抛出
 * @author rico
 * @created 2017年7月4日 下午4:56:44
 */
public class TokenException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public TokenException(String msg) {
		super(msg);
	}

}
