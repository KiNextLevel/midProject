package controller.action;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import model.dao.UserDAO;
import model.dto.UserDTO;

public class MyPageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request) {
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		String userEmail = (String)session.getAttribute("userEmail");
		
		UserDTO userDTO = new UserDTO();
		UserDAO userDAO = new UserDAO();
		userDTO.setUserEmail(userEmail);
		userDTO = userDAO.selectOne(userDTO);
		if(userDTO != null) {
			request.setAttribute("userDTO", userDTO);
			forward.setPath("/mywebapp/theme/UserDetailPage.jsp");
			forward.setRedirect(false);
		}
		else {
			System.out.println("마이페이지 로그["+userDTO+"]");
		}
		return forward;
	}

}
