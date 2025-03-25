package controller.action;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.webapp.model.dao.ReportDAO;
import org.example.webapp.model.dao.UserDAO;
import org.example.webapp.model.dto.ReportDTO;
import org.example.webapp.model.dto.UserDTO;

public class ReportAction implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request) {
        System.out.println("CONT 로그: REPORT ACTION 도착");
        ActionForward forward = new ActionForward();
        HttpSession session = request.getSession();

        // 디버깅: 모든 파라미터 출력
        System.out.println("모든 파라미터 목록:");
        java.util.Enumeration<String> paramNames = request.getParameterNames();
        while(paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            System.out.println(paramName + ": " + request.getParameter(paramName));
        }

        // getParameter()로 폼 데이터 가져오기
        String reportedUserEmail = request.getParameter("userEmail");

        // 체크박스 다중 선택 처리
        String[] reasons = request.getParameterValues("reason");
        String combinedReasons = "";

        if (reasons != null) {
            for (int i = 0; i < reasons.length; i++) {
                combinedReasons += reasons[i];
                if (i < reasons.length - 1) {
                    combinedReasons += ", ";
                }
            }
        }

        String description = request.getParameter("description");

        UserDAO userDAO = new UserDAO();
        ReportDTO reportDTO = new ReportDTO();
        ReportDAO reportDAO = new ReportDAO();

        // 세션에서 현재 로그인한 사용자 이메일 가져오기
        String reporterEmail = (String) session.getAttribute("userEmail");

        reportDTO.setReportReported(reportedUserEmail);
        reportDTO.setReportReporter(reporterEmail);
        reportDTO.setReportReason(combinedReasons);
        reportDTO.setReportDescription(description);

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

                // 실패 원인 디버깅
                System.out.println("신고 실패 원인:");
                System.out.println("reportedUserEmail: " + reportedUserEmail);
                System.out.println("reporterEmail: " + reporterEmail);
                System.out.println("combinedReasons: " + combinedReasons);
                System.out.println("description: " + description);
            }
        }

        forward.setPath("/Metronic-Shop-UI-master/theme/alert.jsp");
        forward.setRedirect(false);
        return forward;
    }
}