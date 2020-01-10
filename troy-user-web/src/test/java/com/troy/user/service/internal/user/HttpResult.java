package com.troy.user.service.internal.user;

/**
 * @ClassName: HttpResult
 * @Description: httpclient请求返回
 */
public class HttpResult {
	private int status;
	private String resposeString;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getResposeString() {
		return resposeString;
	}
	public void setResposeString(String resposeString) {
		this.resposeString = resposeString;
	}
	
	
	 
	@Override
	public String toString() {
		
		return "status = " + status + "\tresposeString = " + resposeString;
	}
	
}
