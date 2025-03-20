package org.example.webapp.controller.action;

import java.util.ArrayList;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.webapp.controller.common.Action;
import org.example.webapp.controller.common.ActionForward;

public class MainPageAction implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request) {
		System.out.println("CONT 로그: MAINPAGE ACTION 도착");
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
        //넘어가야하는 정보: 비동기로 처리할듯
        //1. 알람
	    //2. 메시지(최프)
	    AlertDAO alerDAO = new AlertDAO();
	    AlertDTO alertDTO = new AlertDTO();
	    alertDTO.setUserEmail(session.removeAttribute("userEmail"));
	    ArrayList<AlertDTO> alertDatas = alertDAO.selectAll(alertDTO);
	    if (alertDatas != null) {
	    	//알람이 있음
	    	for(AlertDTO data : alertDatas) {
	    		if (!data.getAlertIsWatch()) { // 열람한적 없는 데이터가 있다면
	    			request.setAttribute("unread", true);
	    		}
	    	}
	    }
	    forward.setPath("MainPage.jsp");
		forward.setRedirect(true);
		return forward;
    }
}
