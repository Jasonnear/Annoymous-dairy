package app.config;

public class DailyJsonObject {

	//0:失败 1:成功
	private int success;
	
	//提示的信息,上传成功或上传失败及错误信息等
	private String message;
	
	//图片地址
	private String url;

	public int getSuccess() {
		return success;
	}

	public void setSuccess(int success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
