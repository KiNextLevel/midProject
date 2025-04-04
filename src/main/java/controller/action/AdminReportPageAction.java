package controller.action;

import java.util.ArrayList;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.webapp.model.dao.ReportDAO;
import org.example.webapp.model.dao.UserDAO;
import org.example.webapp.model.dto.ReportDTO;
import org.example.webapp.model.dto.UserDTO;


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
		System.out.println("AdminReportPageAction 로그: "+reported);
		System.out.println("ReportPageAction log reported: "+reported);

		userDTO.setCondition("SELECTALL_BLACK");
		ArrayList<UserDTO> blacks = userDAO.selectAll(userDTO);	//블랙리스트 전체
		System.out.println("ReportPageAction log blacks: "+blacks);

		if(((Integer)session.getAttribute("userRole"))==1) {	//관리자만 이동 가능
			request.setAttribute("blacks", blacks);
			request.setAttribute("reported", reported);
			forward.setPath("/Metronic-Shop-UI-master/theme/AdminReportPage.jsp");
			forward.setRedirect(false);
		}
		else {
			request.setAttribute("msg", "관리자만 이동 가능합니다");
			request.setAttribute("flag", false);
			forward.setPath("/Metronic-Shop-UI-master/theme/Alert.jsp");
			forward.setRedirect(false);
		}
		return forward;
	}
}