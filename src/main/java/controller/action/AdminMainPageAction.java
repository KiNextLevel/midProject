package controller.action;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class AdminMainPageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request) {
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		System.out.println("AdminMainPageAction 로그: 도착");	
		
		try {
		if(Integer.parseInt((String)session.getAttribute("userRole")) == 1) {
			forward.setPath("/mywebapp/target-free-admin-template/AdminMainPage.jsp");
			forward.setRedirect(true);
		}
		else{
			request.setAttribute("msg", "관리자만 접근 가능합니다");
			request.setAttribute("flag", false);
			forward.setPath("alert.jsp");
			forward.setRedirect(false);
		}
		}catch(Exception e) {		//session이 null인거 막기위해서
			request.setAttribute("msg", "로그인 해주세요");
			request.setAttribute("flag", false);
			forward.setPath("alert.jsp");
			forward.setRedirect(false);
			System.out.println(e);
		}
		return forward;
	}
}
