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
