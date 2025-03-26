package controller.action;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;
import org.example.webapp.model.dao.UserDAO;
import org.example.webapp.model.dto.UserDTO;

public class AdminAddBlackAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request) {
		System.out.println("adminAddBlackAcion 로그: 도착");
		ActionForward forward = new ActionForward();

		UserDTO userDTO = new UserDTO();
		UserDAO userDAO = new UserDAO();

		String reportedUserEmail = request.getParameter("reportedUser");	//요청으로 신고받은사람 이메일 받음
		System.out.println("AddBlackAction 로그["+reportedUserEmail+"]");
		userDTO.setCondition("SELECTONE_USERINFO");
		userDTO.setUserEmail(reportedUserEmail);
		userDTO = userDAO.selectOne(userDTO);	//신고 받은 유저
		System.out.println("AddBlackAction log userDTO: "+userDTO);

		if(userDTO == null) {	//신고 받은 유저 못 찾으면
			System.out.println("userRole null");
			request.setAttribute("msg", "사용자를 찾을 수 없습니다");
			request.setAttribute("flag", false);
			forward.setPath("/Metronic-Shop-UI-master/theme/alert.jsp");
			forward.setRedirect(false);
			return forward;
		}

		if(userDTO.getUserRole() == 2) {	//신고 받은 유저가 이미 블랙이면
			System.out.println("already black");
			request.setAttribute("msg", "이미 블랙 처리 된 사용자입니다");
			request.setAttribute("flag", false);
			forward.setPath("/Metronic-Shop-UI-master/theme/alert.jsp");
			forward.setRedirect(false);
			return forward;
		}
		userDTO.setCondition("UPDATE_ROLE");
		userDTO.setUserRole(2);
		if(userDAO.update(userDTO)) {
			request.setAttribute("msg", "사용자를 블랙 처리 했습니다");
			request.setAttribute("flag", true);
			request.setAttribute("url", "adminReportPage.do");
			forward.setPath("/Metronic-Shop-UI-master/theme/alert.jsp");
			forward.setRedirect(false);
		}
		else{
			request.setAttribute("msg", "사용자 블랙 실패");
			request.setAttribute("flag", false);
		}
		return forward;
	}
}