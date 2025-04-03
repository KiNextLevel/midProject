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

        // 사용자 닉네임 조회 로직 추가
        UserDTO userDTO = new UserDTO();
        UserDAO userDAO = new UserDAO();
        userDTO.setUserEmail(reportedUserEmail);
        userDTO.setCondition("SELECTONE_USERINFO");
        UserDTO userData = userDAO.selectOne(userDTO);

        String userNickname = "알 수 없음";
        if (userData != null && userData.getUserNickname() != null) {
            userNickname = userData.getUserNickname();
        }

        // 닉네임을 request에 저장
        request.setAttribute("reportedUserNickname", userNickname);

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

        ReportDTO reportDTO = new ReportDTO();
        ReportDAO reportDAO = new ReportDAO();

        // 세션에서 현재 로그인한 사용자 이메일 가져오기
        String reporterEmail = (String) session.getAttribute("userEmail");

        // 필요한 정보 설정
        reportDTO.setReportReported(reportedUserEmail); // 피신고자
        reportDTO.setReportReporter(reporterEmail);     // 신고자
        reportDTO.setReportReason(combinedReasons);     // 신고 이유
        reportDTO.setReportDescription(description);    // 신고 상세 설명

        if (reportDAO.selectOne(reportDTO) != null) { // 신고자가 이미 피 신고자를 신고한 적이 있으며, 처리 대기중이다
            //신고 못하게 함
            request.setAttribute("msg", "해당 유저는 이미 신고하셨습니다.");
            request.setAttribute("flag", false);
            request.setAttribute("url", "mainPage.do");

        } else if (reportDAO.insert(reportDTO)) {// 신고 데이터 삽입 시도
            // 신고 성공
            request.setAttribute("msg", "신고가 완료되었습니다. 직원이 검토 후 처리됩니다.");
            request.setAttribute("flag", true);
            request.setAttribute("url", "mainPage.do");
        } else {
            // 신고 실패
            request.setAttribute("msg", "신고 처리 중 오류가 발생했습니다. 다시 시도해주세요.");
            request.setAttribute("flag", false);
            request.setAttribute("url", "mainPage.do");

            // 실패 원인 디버깅
            System.out.println("신고 실패 원인:");
            System.out.println("reportedUserEmail: " + reportedUserEmail);
            System.out.println("reporterEmail: " + reporterEmail);
            System.out.println("combinedReasons: " + combinedReasons);
            System.out.println("description: " + description);
        }

        forward.setPath("/Metronic-Shop-UI-master/theme/alert.jsp");
        forward.setRedirect(false);
        return forward;
    }
}