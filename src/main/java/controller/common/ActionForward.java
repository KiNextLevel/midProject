package controller.common;

public class ActionForward {
	private String path; //경로 정보
	private boolean redirect; //응답 방식(redirect, forward)
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public boolean isRedirect() {
		return redirect;
	}
	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}
}
