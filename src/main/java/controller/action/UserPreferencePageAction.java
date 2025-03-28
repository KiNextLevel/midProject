package controller.action;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class UserPreferencePageAction implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request) {
        System.out.println("CONT 로그: UserPreferencePageAction 도착");
        ActionForward forward = new ActionForward();

        // 세션에서 사용자 이메일 가져오기
        HttpSession session = request.getSession();
        String userEmail = (String) session.getAttribute("userEmail");

        // request에 사용자 이메일 저장
        request.setAttribute("userEmail", userEmail);

        forward.setPath("/Metronic-Shop-UI-master/theme/UserPreference.jsp");
        forward.setRedirect(false); // 리다이렉트 대신 포워드 사용
        return forward;
    }
}
