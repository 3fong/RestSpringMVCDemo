package cn.edu.tju.rico.response;

/**
 * Title: 统一响应结构 
 * @author rico
 * @created 2017年7月4日 下午5:06:00
 */
public class ResponseEntity {

	private Integer code;
	private String message;
	private Object data;
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
}