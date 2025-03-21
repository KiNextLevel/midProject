package controller.action;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;
import org.example.webapp.model.dao.UserDAO;
import org.example.webapp.model.dto.UserDTO;

public class UserDetailPageAction implements Action {
    @Override
	public ActionForward execute(HttpServletRequest request) {
		System.out.println("CONT 로그: USERDETAILPAGE ACTION 도착");
		ActionForward forward = new ActionForward();
		request.setAttribute("userEmail",request.getParameter("userEmail"));
		UserDTO userDTO = new UserDTO();
		UserDAO userDAO = new UserDAO();
		userDTO.setCondition("SELECTONE_USERINFO");
		userDTO.setUserEmail(request.getParameter("userEmail"));
		userDTO = userDAO.selectOne(userDTO);
		if (userDTO == null) {
			request.setAttribute("msg", "존재하지 않는 회원입니다");
			request.setAttribute("flag", false);
			forward.setPath("alert.jsp");
		} else {
			System.out.println(userDTO);
			request.setAttribute("userDTO",userDTO);
			forward.setPath("UserDetail.jsp");
		}
		forward.setRedirect(false);
		return forward;
	}
}
