package org.example.webapp.controller.action;


import jakarta.servlet.http.HttpServletRequest;
import org.example.webapp.controller.common.Action;
import org.example.webapp.controller.common.ActionForward;
import org.example.webapp.model.dao.UserDAO;
import org.example.webapp.model.dto.UserDTO;

public class JoinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request) {
		System.out.println("CONT 로그: JOIN ACTION 도착");
		ActionForward forward = new ActionForward();
		UserDAO userDAO = new UserDAO();
		UserDTO userDTO = new UserDTO();
		userDTO.setUserEmail(request.getParameter("userEmail"));
		userDTO.setUserPassword(request.getParameter("userPassword"));
		userDTO.setUserNickname(request.getParameter("userNickname"));
		System.out.println("JOIN 로그 가져온 ID[" + userDTO.getUserEmail() + "]");
		// 중복이 없으면 회원가입 진행
		if (userDAO.insert(userDTO)) {
			request.setAttribute("userEmail", request.getParameter("userEmail"));
			request.setAttribute("msg", "회원가입 성공!");
			request.setAttribute("flag", true);
			request.setAttribute("url", "controller.jsp?action=MYPAGEVIEWPAGE");
		} else {
			request.setAttribute("msg", "회원가입 실패!");
			request.setAttribute("flag", false);
		}
		forward.setPath("alert.jsp");
		forward.setRedirect(false);
		return forward;
	}
}
