package controller.action;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;
import org.example.webapp.model.dao.UserDAO;
import org.example.webapp.model.dto.UserDTO;

public class ReportPageAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request) {
		System.out.println("CONT 로그: REPORTPAGE ACTION 도착");
		ActionForward forward = new ActionForward();

		try {
			// 요청 인코딩 설정
			request.setCharacterEncoding("UTF-8");

			// userEmail 파라미터 확인
			String userEmail = request.getParameter("userEmail");
			System.out.println("ReportPageAction에서 받은 userEmail: " + userEmail);

			// 사용자 정보 조회
			UserDTO userDTO = new UserDTO();
			UserDAO userDAO = new UserDAO();

			userDTO.setUserEmail(userEmail);
			userDTO.setCondition("SELECTONE_USERINFO");

			System.out.println("사용자 정보 조회 시작: " + userEmail);
			UserDTO userData = userDAO.selectOne(userDTO);
			System.out.println("사용자 정보 조회 결과: " + userData);

			// 닉네임 설정
			String userNickname = "알 수 없음";
			if (userData != null && userData.getUserNickname() != null) {
				userNickname = userData.getUserNickname();
			}

			System.out.println("설정할 닉네임: " + userNickname);

			// request에 속성 설정
			request.setAttribute("reportedUserEmail", userEmail);
			request.setAttribute("reportedUserNickname", userNickname);

		} catch (Exception e) {
			System.out.println("ReportPageAction 실행 중 오류 발생: " + e.getMessage());
			e.printStackTrace();
		}

		// Report.jsp로 포워딩
		forward.setPath("/Metronic-Shop-UI-master/theme/Report.jsp");
		forward.setRedirect(false);
		return forward;
	}
}