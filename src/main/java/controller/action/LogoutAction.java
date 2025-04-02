package controller.action;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class LogoutAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request) {
		System.out.println("CONT 로그: LOGOUT ACTION 도착");
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		// session 비우기
		session.invalidate();

	    // url, flag, msg 요청단위 저장
	    // alert.jsp에 url, true, msg 보내기
	    request.setAttribute("msg", "로그아웃 성공!");
	    request.setAttribute("flag", true);
	    request.setAttribute("url", "loginPage.do");
	    forward.setPath("/Metronic-Shop-UI-master/theme/Alert.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
