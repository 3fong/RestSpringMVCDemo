package cn.edu.tju.rico.response;

/**
 * @Description: 状态枚举
 * @author liulei
 * @date 2018年4月19日 下午7:38:39
 */
public enum StatusCode {
	SUCCESS(1000, "请求成功"), FAILER(2000, "请求失败");

	private Integer code;
	private String msg;
	private StatusCode(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
