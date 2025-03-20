package controller.action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.webapp.controller.common.Action;
import org.example.webapp.controller.common.ActionForward;
import org.example.webapp.model.dao.UserDAO;
import org.example.webapp.model.dto.ReportDTO;
import org.example.webapp.model.dto.UserDTO;

public class ReportAction implements Action {
    @Override
	public ActionForward execute(HttpServletRequest request) {
		System.out.println("CONT 로그: REPORT ACTION 도착");
		ActionForward forward = new ActionForward();
        HttpSession session = request.getSession();
		UserDAO userDAO = new UserDAO();
		UserDTO userDTO = new UserDTO();
        ReportDTO reportDTO = new ReportDTO();
        ReportDAO reportDAO = new ReportDAO();
        reportDTO.setReportReported((String) request.getAttribute("userEmail"));
        reportDTO.setReportReporter((String) session.getAttribute("userEmail"));
        reportDTO.setReportReason((String) request.getAttribute("reason"));
        reportDTO.setReportDescription((String) request.getAttribute("description"));
        reportDTO.setCondition("SELECTONE");
        if (reportDAO.selectOne(reportDTO) != null) {
            request.setAttribute("msg", "해당 회원은 이미 신고하셨습니다");
			request.setAttribute("flag", false);
			request.setAttribute("url", "mainPage.do");
        } else {
            reportDTO.setCondition("INSERT");
            if (reportDAO.insert(reportDTO)) {
                request.setAttribute("msg", "신고완료. 직원이 검토 후 처리 됩니다.");
                request.setAttribute("flag", true);
                request.setAttribute("url", "mainPage.do");
            } else {
                request.setAttribute("msg", "실패");
                request.setAttribute("flag", false);
            }
        }
		forward.setPath("alert.jsp");
		forward.setRedirect(false);
		return forward;
	}
}
