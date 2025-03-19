package controller.action;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class LogoutAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request) {
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		// session에서 id,닉네임, role, 장바구니 remove
	    session.removeAttribute("userId") ;
	    session.removeAttribute("userName") ;
	    session.removeAttribute("role") ;
	    session.removeAttribute("cart") ;
	   
	    // url, flag, msg 요청단위 저장
	    // alert.jsp에 url, true, msg 보내기
	    request.setAttribute("msg", "로그아웃 성공!");
	    request.setAttribute("flag", true);
	    request.setAttribute("url", "controller.jsp?action=MAINPAGE");
	    //pageContext.forward("alert.jsp");
	    forward.setPath("alert.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
