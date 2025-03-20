package controller.action;

import java.util.ArrayList;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import model.dao.ReportDAO;
import model.dao.UserDAO;
import model.dto.ReportDTO;
import model.dto.UserDTO;

public class AdminReportPageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request) {
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		UserDTO userDTO = new UserDTO();
		UserDAO userDAO = new UserDAO();
		ReportDTO reportDTO = new ReportDTO();
		ReportDAO reportDAO = new ReportDAO();
		
		ArrayList<ReportDTO> reported = reportDAO.selectAll(reportDTO);//신고회원 전체
		
		userDTO.setCondition("SELECTALL_BLACK");	//블랙리스트 전체
		ArrayList<UserDTO> blacks = userDAO.selectAll(userDTO);
				
		if(Integer.parseInt((String)session.getAttribute("userRole"))==1) {
			request.setAttribute("blacks", blacks);
			request.setAttribute("reported", reported);
			forward.setPath("/mywebapp/theme/AdminReportPage.jsp");
			forward.setRedirect(false);
		}	
		else {
			request.setAttribute("msg", "관리자만 이동 가능합니다");
			request.setAttribute("flag", false);
			forward.setPath("/mywebapp/theme/alert.jsp");
			forward.setRedirect(false);
		}
		return forward;
	}
}
