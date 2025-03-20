package org.example.webapp.controller.action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.webapp.controller.common.Action;
import org.example.webapp.controller.common.ActionForward;

public class LogoutAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request) {
		System.out.println("CONT 로그: LOGOUT ACTION 도착");
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		// session에서 id,닉네임, role remove
	    session.removeAttribute("userEmail");
	    session.removeAttribute("userNickname");
	    session.removeAttribute("userRole");

	    // url, flag, msg 요청단위 저장
	    // alert.jsp에 url, true, msg 보내기
	    request.setAttribute("msg", "로그아웃 성공!");
	    request.setAttribute("flag", true);
	    request.setAttribute("url", "mainPage.do");
	    forward.setPath("alert.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
