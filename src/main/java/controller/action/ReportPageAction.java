package controller.action;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;

public class ReportPageAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request) {
		System.out.println("CONT 로그: REPORTPAGE ACTION 도착");
		ActionForward forward = new ActionForward();

		// userEmail 파라미터 확인 (디버깅용)
		String userEmail = request.getParameter("userEmail");
		System.out.println("ReportPageAction에서 받은 userEmail: " + userEmail);

		// userEmail을 request에 설정
		request.setAttribute("userEmail", userEmail);

		// Report.jsp로 직접 포워딩
		forward.setPath("/Metronic-Shop-UI-master/theme/Report.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
