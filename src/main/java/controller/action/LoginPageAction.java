package controller.action;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;

public class LoginPageAction implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request) {
        System.out.println("CONT 로그: LoginPage ACTION 도착");
        ActionForward forward = new ActionForward();
        request.setAttribute("userEmail",request.getParameter("userEmail"));
        forward.setPath("LoginPage.jsp");
        forward.setRedirect(false);
        return forward;
    }
}
