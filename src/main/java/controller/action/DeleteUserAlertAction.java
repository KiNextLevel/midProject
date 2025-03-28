package controller.action;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class DeleteUserAlertAction implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request) {
        System.out.println("CONT 로그: DeleteUserAlertAction 도착");
        ActionForward forward = new ActionForward();
        HttpSession session = request.getSession();
        // session에서 id,닉네임, role remove
        session.removeAttribute("userEmail");
        session.removeAttribute("userRole");

        // url, flag, msg 요청단위 저장
        // alert.jsp에 url, true, msg 보내기
        request.setAttribute("flag", true);
        request.setAttribute("url", "loginPage.do");
        forward.setPath("/Metronic-Shop-UI-master/theme/alert.jsp");
        forward.setRedirect(false);
        return forward;
    }
}
