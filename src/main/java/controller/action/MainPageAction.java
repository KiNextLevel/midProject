package controller.action;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.webapp.model.dao.AlertDAO;
import org.example.webapp.model.dao.UserDAO;
import org.example.webapp.model.dto.AlertDTO;
import org.example.webapp.model.dto.UserDTO;

import java.util.ArrayList;

public class MainPageAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request) {
		System.out.println("CONT 로그: MAINPAGE ACTION 도착");
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		String userEmail = (String) session.getAttribute("userEmail");

		// 알림 가져오기
		AlertDAO alertDAO = new AlertDAO();
		AlertDTO alertDTO = new AlertDTO();
		alertDTO.setUserEmail(userEmail);
		ArrayList<AlertDTO> alertDatas = alertDAO.selectAll(alertDTO);

		// 알림에 대한 확인
		boolean hasUnreadAlerts = false;
		if (alertDatas != null) {
			for (AlertDTO data : alertDatas) {
				if (!data.isAlertIsWatch()) {
					hasUnreadAlerts = true;  // 읽지 않은 알림이 있을 경우
					break;
				}
			}
		}

		// 읽지 않은 알림 여부를 request 속성으로 전달
		request.setAttribute("hasUnreadAlerts", hasUnreadAlerts);
		request.setAttribute("alertDatas", alertDatas);

		// 사용자 정보 가져오기
		UserDAO userDAO = new UserDAO();
		UserDTO userDTO = new UserDTO();
		userDTO.setUserEmail(userEmail);
		userDTO.setCondition("SELECTALL");
		ArrayList<UserDTO> userDatas = userDAO.selectAll(userDTO);
		session.setAttribute("userDatas", userDatas);

		// 페이지 이동
		forward.setPath("MainPage.jsp");
		forward.setRedirect(false);
		return forward;
	}
}
