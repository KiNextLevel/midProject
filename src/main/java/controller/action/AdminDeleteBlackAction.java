package controller.action;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;
import model.dao.UserDAO;
import model.dto.UserDTO;

public class AdminDeleteBlackAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request) {
		ActionForward forward = new ActionForward();
		String user = request.getParameter("reportedUser");
		UserDTO userDTO = new UserDTO();
		UserDAO userDAO = new UserDAO();
		userDTO.setUserEmail(user);
		userDTO = userDAO.selectOne(userDTO);
		
		if(userDTO.getUserRole().equals("BLACK")) {
		userDTO.setUserRole("USER");
		
		request.setAttribute("msg", "사용자를 블랙 해제했습니다");
		request.setAttribute("flag", true);
		request.setAttribute("url", "/mywebapp/theme/controller.jsp?command=ADMINREPORTPAGE");

		}
		else {
			request.setAttribute("msg", "블랙리스트에 존재하는 사용자가 아닙니다");
			request.setAttribute("flag",false);
		}
		forward.setPath("alert.jsp");
		forward.setRedirect(false);
		return forward;

	}
}
