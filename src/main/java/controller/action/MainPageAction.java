package controller.action;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.webapp.model.dao.AlertDAO;
import org.example.webapp.model.dto.AlertDTO;

import java.util.ArrayList;

public class MainPageAction implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request) {
		System.out.println("CONT 로그: MAINPAGE ACTION 도착");
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
        //넘어가야하는 정보: 비동기로 처리할듯
        //1. 알람
	    //2. 메시지(최프)
	    AlertDAO alertDAO = new AlertDAO();
	    AlertDTO alertDTO = new AlertDTO();
	    alertDTO.setUserEmail((String) session.getAttribute("userEmail"));
	    ArrayList<AlertDTO> alertDatas = alertDAO.selectAll(alertDTO);
	    if (alertDatas != null) {
	    	//알람이 있음
	    	for(AlertDTO data : alertDatas) {
	    		if (!data.isAlertIsWatch()) { // 열람한적 없는 데이터가 있다면
	    			request.setAttribute("unread", true);
	    		}
	    	}
	    }
	    forward.setPath("mainPage.do");
		forward.setRedirect(true);
		return forward;
    }
}
