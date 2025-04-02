package controller.action;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;
import org.example.webapp.model.dao.AlertDAO;
import org.example.webapp.model.dao.ReportDAO;
import org.example.webapp.model.dao.UserDAO;
import org.example.webapp.model.dto.AlertDTO;
import org.example.webapp.model.dto.ReportDTO;
import org.example.webapp.model.dto.UserDTO;

public class AdminSendWarningAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request) {
		System.out.println("SendAlertWarningAction log");
		ActionForward forward = new ActionForward();
		ReportDTO reportDTO = new ReportDTO();
		ReportDAO reportDAO = new ReportDAO();
		AlertDTO alertDTO = new AlertDTO();
		AlertDAO alertDAO = new AlertDAO();

		// userDTO.setUserEmail(request.getParameter("reportedUser"));
		//insert 할 때 유저 이메일, 신고사유
		String reportedUser = request.getParameter("reportedUser");
		String reason = request.getParameter("reason");
		alertDTO.setUserEmail(reportedUser);	//신고당한 회원 이메일
		alertDTO.setAlertContent(reason);		//신고 사유
		System.out.println("reportNum: ["+request.getParameter("reportNum")+"]");
		reportDTO.setReportNumber(Integer.parseInt(request.getParameter("reportNum")));	//해당 신고 번호
		reportDTO.setCondition("DELETE_ONE");
		System.out.println("reportedUser: "+reportedUser);
		System.out.println("reason"+reason);

		if(alertDAO.insert(alertDTO) && reportDAO.delete(reportDTO)) {
			//여기서 경고 보낸 사람 신고 리스트에서 삭제
			request.setAttribute("msg", "경고 보내기 완료");
			request.setAttribute("flag", true);
			request.setAttribute("url", "adminReportPage.do");
		}
		else {
			request.setAttribute("msg", "경고 보내기 실패");
			request.setAttribute("flag", false);
		}
		forward.setPath("/Metronic-Shop-UI-master/theme/Alert.jsp");
		forward.setRedirect(false);
		return forward;
	}
}