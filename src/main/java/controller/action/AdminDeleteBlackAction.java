package controller.action;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;
import org.example.webapp.model.dao.UserDAO;
import org.example.webapp.model.dto.UserDTO;

public class AdminDeleteBlackAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request) {
		System.out.println("adminDeleteBlackAcion 로그: 도착");
		ActionForward forward = new ActionForward();
		String reportedUserEmail = request.getParameter("REPORT_REPORTED");
		System.out.println("REPORT_REPORTED: ["+reportedUserEmail+"+]");
		UserDTO userDTO = new UserDTO();
		UserDAO userDAO = new UserDAO();

		userDTO.setUserEmail(reportedUserEmail);
		userDTO.setCondition("UPDATE_ROLE");
		userDTO.setUserRole(0);
		if(userDAO.update(userDTO)) {
			request.setAttribute("msg", "사용자를 블랙 해제했습니다");
			request.setAttribute("flag", true);
			request.setAttribute("url", "adminReportPage.do");
		}
		else {
			request.setAttribute("msg", "블랙 해제 실패");
			request.setAttribute("flag", false);
		}
		forward.setPath("/Metronic-Shop-UI-master/theme/alert.jsp");
		forward.setRedirect(false);
		return forward;

	}
}