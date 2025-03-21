package controller.action;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;
import org.example.webapp.model.dao.AlertDAO;
import org.example.webapp.model.dao.UserDAO;
import org.example.webapp.model.dto.AlertDTO;
import org.example.webapp.model.dto.UserDTO;

public class AdminSendWarningAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request) {
		ActionForward forward = new ActionForward();
		UserDTO userDTO = new UserDTO();
		UserDAO userDAO = new UserDAO();
		AlertDTO alertDTO = new AlertDTO();
		AlertDAO alertDAO = new AlertDAO();
		
		// userDTO.setUserEmail(request.getParameter("reportedUser"));
		alertDTO.setUserEmail(request.getParameter("reportedUser"));	//신고당한 회원 이메일

		if(alertDAO.insert(alertDTO)) {
			request.setAttribute("msg", "경고 보내기 완료");
			request.setAttribute("flag", true);
			request.setAttribute("url", "adminReportPage.do");
		}
		else {
			request.setAttribute("msg", "경고 보내기 실패");
			request.setAttribute("flag", false);
		}
		forward.setPath("/mywebapp/theme/alert.jsp");
		forward.setRedirect(false);
		return forward;
	}
}
