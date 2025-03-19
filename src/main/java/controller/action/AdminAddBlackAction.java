package controller.action;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;
import model.dao.UserDAO;
import model.dto.UserDTO;

public class AdminAddBlackAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request) {
		ActionForward forward = new ActionForward();

		UserDTO userDTO = new UserDTO();
		UserDAO userDAO = new UserDAO();

		String reportedUser = request.getParameter("reportedUser");
		System.out.println("AddBlackAction 로그["+reportedUser+"]");
		userDTO.setUserEmail(reportedUser);

		if(reportedUser == null) {
			request.setAttribute("msg", "사용자를 찾을 수 없습니다");
			request.setAttribute("flag", false);
			return forward;
		}
		userDTO = userDAO.selectOne(userDTO);
		if(userDTO.getUserRole().equals("BLACK")) {
			request.setAttribute("msg", "이미 블랙 처리 된 사용자입니다");
			request.setAttribute("flag", false);
			return forward;
		}

		userDTO.setUserRole("BLACK");
		request.setAttribute("msg", "사용자를 블랙 처리 했습니다!");
		request.setAttribute("flag", true);
		request.setAttribute("url", "/mywebapp/theme/controller.jsp?command=ADMINREPORTPAGE");

		forward.setPath("alert.jsp");
		forward.setRedirect(false);
		return forward;
	}
}
