package controller.action;

import java.util.ArrayList;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import model.dto.ReportDTO;

public class AdminReportPageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request) {
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		ReportDTO reportDTO = new ReportDTO();
		ReportDAO reportDAO = new ReportDAO();
		
		ArrayList<ReportDTO> datas = reportDAO.selectAll(reportDTO);
		
		if(session.getAttribute("userRole").equals("ADMIN")) {
			request.setAttribute("reportDTO", reportDTO);
			forward.setPath("/mywebapp/theme/AdminReportPage.jsp");
			forward.setRedirect(false);
		}	
		else {
			forward.setPath("/mywewbapp/theme/Main.jsp");
			forward.setRedirect(true);
		}
		return forward;
	}
}
