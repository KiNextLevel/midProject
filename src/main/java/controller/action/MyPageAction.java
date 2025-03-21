package controller.action;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.webapp.model.dao.UserDAO;
import org.example.webapp.model.dto.UserDTO;

import java.util.ArrayList;

public class MyPageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request) {
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		String userEmail = (String)session.getAttribute("userEmail");

		if(userEmail == null) {
			// 로그인되지 않은 경우
			forward.setPath("loginPage.do");
			forward.setRedirect(true);
			return forward;
		}

		UserDTO userDTO = new UserDTO();
		UserDAO userDAO = new UserDAO();
		userDTO.setUserEmail(userEmail);
		userDTO.setCondition("SELECTALL"); // 조건 설정 추가

		ArrayList<UserDTO> userList = userDAO.selectAll(userDTO);

		if(userList != null && !userList.isEmpty()) {
			request.setAttribute("data", userList.get(0)); // 첫 번째 사용자 정보
			forward.setPath("myPage.do");
			forward.setRedirect(false);
		}
		else {
			System.out.println("마이페이지 로그[사용자 정보 없음]");
			// 사용자 정보가 없을 때 처리
			forward.setPath("loginPage.do");
			forward.setRedirect(true);
		}
		return forward;
	}
	}