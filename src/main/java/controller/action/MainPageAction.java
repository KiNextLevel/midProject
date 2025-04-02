package controller.action;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.webapp.model.dao.AlertDAO;
import org.example.webapp.model.dao.UserDAO;
import org.example.webapp.model.dto.AlertDTO;
import org.example.webapp.model.dto.UserDTO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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

		// 디버깅용 출력
		for (AlertDTO alertDTO1 : alertDatas) {
			System.out.println(alertDTO1);
		}

		// JSON 변환
		JSONArray jsonArray = new JSONArray();
		boolean hasUnreadAlerts = false;

		if (alertDatas != null) {
			for (AlertDTO data : alertDatas) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("alertNumber", data.getAlertNumber());
				jsonObject.put("userEmail", data.getUserEmail());
				jsonObject.put("alertContent", data.getAlertContent());
				jsonObject.put("alertDate", data.getAlertDate().toString()); // Date를 문자열로 변환
				jsonObject.put("alertIsWatch", data.isAlertIsWatch());
				jsonArray.add(jsonObject);

				// 읽지 않은 알림 체크
				if (!data.isAlertIsWatch()) {
					hasUnreadAlerts = true;
				}
			}
		}
		// 세션에 JSON 문자열 저장
		session.setAttribute("alertDatasJson", jsonArray.toJSONString());
		request.setAttribute("hasUnreadAlerts", hasUnreadAlerts);

		// 기존 alertDatas도 유지 (임시)
		session.setAttribute("alertDatas", alertDatas);
		
		UserDAO userDAO = new UserDAO();
		UserDTO userDTO = new UserDTO();
		userDTO.setUserEmail(userEmail);
		userDTO.setCondition("SELECTALL");
		ArrayList<UserDTO> userDatas = userDAO.selectAll(userDTO);

		JSONArray userDatasJsonArray = new JSONArray();
		for (UserDTO user : userDatas) {
			JSONObject userJson = new JSONObject();
			userJson.put("userEmail", user.getUserEmail());
			userJson.put("userNickname", user.getUserNickname());
			userJson.put("userRegdate", user.getUserRegdate() != null ? user.getUserRegdate().toString() : null);
			userJson.put("userGender", user.getUserGender()); // boolean
			userJson.put("userBirth", user.getUserBirth()); // String
			userJson.put("userHeight", user.getUserHeight()); // int
			userJson.put("userProfile", user.getUserProfile());
			userJson.put("userReligion", user.getUserReligion());
			userJson.put("userSmoke", user.isUserSmoke()); // boolean
			userJson.put("userRole", user.getUserRole());
			userJson.put("userRegion", user.getUserRegion());
			userJson.put("userDescription", user.getUserDescription());
			userJson.put("userName", user.getUserName());
			userJson.put("userLatitude", user.getUserLatitude()); // double
			userJson.put("userLongitude", user.getUserLongitude()); // double
			userDatasJsonArray.add(userJson);
		}

		String jsonUserDatas = userDatasJsonArray.toJSONString();
		request.setAttribute("userDatas", jsonUserDatas);

		forward.setPath("/Metronic-Shop-UI-master/theme/MainPage.jsp");
		forward.setRedirect(false);
		return forward;
	}
}
