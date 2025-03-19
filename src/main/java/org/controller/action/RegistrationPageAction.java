package controller.action;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;

public class RegistrationPageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request) {
		ActionForward forward = new ActionForward();
//	    response.sendRedirect("signup.jsp");
		forward.setPath("signup.jsp");
		forward.setRedirect(true);
		return forward;
	}

}
