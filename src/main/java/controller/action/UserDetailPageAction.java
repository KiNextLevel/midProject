package controller.action;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;
import org.example.webapp.model.dao.PreferenceDAO;
import org.example.webapp.model.dao.UserDAO;
import org.example.webapp.model.dto.PreferenceDTO;
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

		PreferenceDTO preferenceDTO = new PreferenceDTO();
		PreferenceDAO preferenceDAO = new PreferenceDAO();
		preferenceDTO.setCondition("SELECTONE");
		preferenceDTO.setUserEmail(request.getParameter("userEmail"));

		System.out.println("CONT 로그: 선호 정보 조회 시작 - " + request.getParameter("userEmail"));
		preferenceDTO = preferenceDAO.selectOne(preferenceDTO);
		System.out.println("CONT 로그: 선호 정보 조회 결과 - " + preferenceDTO);

		if (preferenceDTO == null) {
			preferenceDTO = new PreferenceDTO(); // 기본 객체 생성
			System.out.println("CONT 로그: preferenceDTO가 null이어서 새 객체 생성");
		}

		request.setAttribute("preferenceDTO", preferenceDTO);

		if (userDTO == null) {
			request.setAttribute("msg", "존재하지 않는 회원입니다");
			request.setAttribute("flag", false);
			forward.setPath("/Metronic-Shop-UI-master/theme/alert.jsp");
			forward.setRedirect(true);
		} else {
			System.out.println(userDTO);
			request.setAttribute("userDTO",userDTO);
			request.setAttribute("preferenceDTO",preferenceDTO);
			forward.setPath("/Metronic-Shop-UI-master/theme/UserDetail.jsp");
			forward.setRedirect(false);
		}
		return forward;
	}
}
