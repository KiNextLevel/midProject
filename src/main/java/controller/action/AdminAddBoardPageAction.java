package controller.action;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class AdminAddBoardPageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request) {
		System.out.println("AdminAddBoardPageAction 로그: 도착");
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();

		if((Integer)session.getAttribute("userRole")==1) {	//관리자만 이동 가능
			forward.setPath("/Metronic-Shop-UI-master/theme/AdminAddBoardPage.jsp");
			forward.setRedirect(true);
		}
		else {
			request.setAttribute("msg", "관리자만 가능합니다");
			request.setAttribute("flag", false);
			forward.setPath("/Metronic-Shop-UI-master/theme/Alert.jsp");
			forward.setRedirect(false);
		}
		return forward;
	}
}
