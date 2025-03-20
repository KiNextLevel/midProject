package org.example.webapp.controller.action;

import jakarta.servlet.http.HttpServletRequest;
import org.example.webapp.controller.common.Action;
import org.example.webapp.controller.common.ActionForward;

public class LoginPageAction implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request) {
        System.out.println("CONT 로그: LoginPage ACTION 도착");
        ActionForward forward = new ActionForward();
        request.setAttribute("userEmail",request.getParameter("userEmail"));
        forward.setPath("loginPage.do");
        forward.setRedirect(false);
        return forward;
    }
}
