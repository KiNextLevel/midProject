package controller.action;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class IndexPageAction implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request) {
        System.out.println("IndexPageAction log: 도착");
        ActionForward forward = new ActionForward(); // ActionForward 초기화
        HttpSession session = request.getSession();
        session.invalidate(); // 세션 비우기
        // FC로 ActionForward 반환
        forward.setPath("IndexPage.jsp");
        forward.setRedirect(true);
        return forward;
    }
}
