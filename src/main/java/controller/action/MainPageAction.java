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
        //넘어가야하는 정보: 비동기로 처리할듯
        //1. 알람
	    //2. 메시지(최프)
	    AlertDAO alertDAO = new AlertDAO();
	    AlertDTO alertDTO = new AlertDTO();
	    alertDTO.setUserEmail(userEmail);
	    ArrayList<AlertDTO> alertDatas = alertDAO.selectAll(alertDTO);
		for(AlertDTO alertDTO1 : alertDatas) {
			System.out.println(alertDTO1);
		}
		session.setAttribute("alertDatas", alertDatas);
	    if (alertDatas != null) {
	    	//알람이 있음
	    	for(AlertDTO data : alertDatas) {
	    		if (!data.isAlertIsWatch()) { // 열람한적 없는 데이터가 있다면
	    			request.setAttribute("hasUnreadAlerts", true);
					break;
	    		}
	    	}
	    }
		UserDAO userDAO = new UserDAO();
		UserDTO userDTO = new UserDTO();
		userDTO.setUserEmail(userEmail);
		userDTO.setCondition("SELECTALL");
		ArrayList<UserDTO> userDatas = userDAO.selectAll(userDTO);
		session.setAttribute("userDatas", userDatas);

		// 현재 로그인한 사용자의 정보를 가져옴
		UserDTO currentUserDTO = new UserDTO();
		currentUserDTO.setUserEmail(userEmail);
		currentUserDTO.setCondition("SELECTONE_USERINFO");
		UserDTO userData = userDAO.selectOne(currentUserDTO);

		// 세션에 현재 사용자 정보 저장
		session.setAttribute("userDTO", userData);

		// 디버깅용 로그 출력
		if (userData != null) {
			System.out.println("사용자 이메일: " + userData.getUserEmail());
			System.out.println("프리미엄 상태: " + userData.isUserPreminum());
		} else {
			System.out.println("사용자 정보를 가져오지 못했습니다.");
		}

		forward.setPath("/Metronic-Shop-UI-master/theme/MainPage.jsp");
		forward.setRedirect(false);
		return forward;
    }
}
