package controller.action;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;
import org.example.webapp.model.dao.UserDAO;
import org.example.webapp.model.dto.UserDTO;

public class AdminAddBlackAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request) {
		ActionForward forward = new ActionForward();

		UserDTO userDTO = new UserDTO();
		UserDAO userDAO = new UserDAO();

		String reportedUserEmail = request.getParameter("reportedUser");	//요청으로 신고받은사람 이메일 받음
		System.out.println("AddBlackAction 로그["+reportedUserEmail+"]");
		userDTO.setUserEmail(reportedUserEmail);
		userDTO = userDAO.selectOne(userDTO);	//신고 받은 유저 

		if(userDTO == null) {	//신고 받은 유저 못 찾으면
			request.setAttribute("msg", "사용자를 찾을 수 없습니다");
			request.setAttribute("flag", false);
			return forward;
		}

		if(userDTO.getUserRole() == 2) {	//신고 받은 유저가 이미 블랙이면
			request.setAttribute("msg", "이미 블랙 처리 된 사용자입니다");
			request.setAttribute("flag", false);
			return forward;
		}
		userDTO.setCondition("UPDATE_ROLE");
		if(userDAO.update(userDTO)) {
			request.setAttribute("msg", "사용자를 블랙 처리 했습니다");
			request.setAttribute("flag", true);
			request.setAttribute("url", "adminReportPage.do");

			forward.setPath("/mywebapp/theme/alert.jsp");
			forward.setRedirect(false);
		}
		return forward;
	}
}
