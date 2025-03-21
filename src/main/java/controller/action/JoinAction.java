package controller.action;


import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
		userDTO.setUserName(request.getParameter("userName"));
		userDTO.setCondition("SELECTONE_CHECK");
		System.out.println("JOIN 로그 가져온 ID[" + userDTO.getUserEmail() + "]");
		System.out.println("JOIN 로그 가져온 pass[" +userDTO.getUserPassword() + "]");
		System.out.println("JOIN 로그 가져온 name[" + userDTO.getUserName() + "]");
		// 중복이 없으면 회원가입 진행
		if (userDAO.selectOne(userDTO) == null) {
			HttpSession session = request.getSession();
			session.setAttribute("userEmail", request.getParameter("userEmail"));
			session.setAttribute("userPassword", request.getParameter("userPassword"));
			session.setAttribute("userName", request.getParameter("userName"));

			request.setAttribute("msg", "회원가입 진행");
			request.setAttribute("flag", true);
			request.setAttribute("url", "JoinPage.jsp");
		} else {
			request.setAttribute("msg", "회원가입 실패!");
			request.setAttribute("flag", false);
		}
		forward.setPath("alert.jsp");
		forward.setRedirect(false);
		return forward;
	}
}