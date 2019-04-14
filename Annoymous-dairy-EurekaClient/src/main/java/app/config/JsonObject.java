package app.config;

public class JsonObject {

	/**
	 * 1 = true , 0 = false
	 */
	private int code = 0;
	
	/**
	 * correct = not null , error = null
	 */
	private Object object = null;
	
	/**
	 * output message
	 */
	private String msg;
	
	/**
	 * 
	 */
	private int page = 10;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
	
}
