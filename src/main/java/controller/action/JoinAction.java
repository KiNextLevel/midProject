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

		// 사용자 입력 정보 설정
		userDTO.setUserEmail(request.getParameter("userEmail"));
		userDTO.setUserPassword(request.getParameter("userPassword"));
		userDTO.setUserName(request.getParameter("userName"));
		userDTO.setCondition("SELECTONE_CHECK");  // 이메일 중복 확인을 위한 조건 설정

		System.out.println("JOIN 로그 가져온 ID[" + userDTO.getUserEmail() + "]");
		System.out.println("JOIN 로그 가져온 pass[" + userDTO.getUserPassword() + "]");
		System.out.println("JOIN 로그 가져온 name[" + userDTO.getUserName() + "]");

		// 이메일 중복 확인
		UserDTO existingUser = userDAO.selectOne(userDTO);

		// 중복 확인 결과 로깅
		System.out.println("check email: " + (existingUser == null ? "no" : "yes"));

		// 중복이 없으면 회원가입 진행
		if (existingUser == null) {
			HttpSession session = request.getSession();
			session.setAttribute("userEmail", request.getParameter("userEmail"));
			session.setAttribute("userPassword", request.getParameter("userPassword"));
			session.setAttribute("userName", request.getParameter("userName"));
			session.setAttribute("socialType", request.getParameter("socialType"));
			session.setAttribute("condition", "join");

			request.setAttribute("userEmail", request.getParameter("userEmail"));
			request.setAttribute("userPassword", request.getParameter("userPassword"));
			request.setAttribute("userName", request.getParameter("userName"));
			request.setAttribute("socialType", request.getParameter("socialType"));

			request.setAttribute("msg", "회원가입 진행");
			request.setAttribute("flag", true);
			request.setAttribute("url", "/Metronic-Shop-UI-master/theme/JoinPage.jsp");
		} else {
			// 중복된 이메일이 있는 경우
			request.setAttribute("msg", "이미 등록된 이메일입니다. 회원가입 실패!");
			request.setAttribute("flag", false);
		}

		forward.setPath("/Metronic-Shop-UI-master/theme/alert.jsp");
		forward.setRedirect(false);
		return forward;
	}
}