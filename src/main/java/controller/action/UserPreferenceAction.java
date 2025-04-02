package controller.action;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.webapp.model.dao.PreferenceDAO;
import org.example.webapp.model.dto.PreferenceDTO;

public class UserPreferenceAction implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request) {
        System.out.println("CONT 로그: UserPreferenceAction 도착");
        ActionForward forward = new ActionForward();
        HttpSession session = request.getSession();
        PreferenceDAO preferenceDAO = new PreferenceDAO();

        try {
            // 폼에서 전송된 데이터 받기
            String userEmail = request.getParameter("userEmail");
            String preferenceHeightStr = request.getParameter("preferenceHeight");
            String preferenceBody = request.getParameter("preferenceBody");
            String preferenceAge = request.getParameter("preferenceAge");

            System.out.println("userEmail: " + userEmail);
            System.out.println("preferenceHeightStr: " + preferenceHeightStr);
            System.out.println("preferenceBody: " + preferenceBody);
            System.out.println("preferenceAge: " + preferenceAge);

            // userEmail이 없으면 세션에서 가져오기
            if (userEmail == null || userEmail.isEmpty()) {
                userEmail = (String) session.getAttribute("userEmail");
            }

            // 데이터 유효성 검사
            if (userEmail == null || preferenceHeightStr == null || preferenceBody == null || preferenceAge == null) {
                request.setAttribute("msg", "필수 정보가 누락되었습니다!");
                request.setAttribute("flag", false);
                forward.setPath("/Metronic-Shop-UI-master/theme/Alert.jsp");
                forward.setRedirect(false);
                return forward;
            }

            // 키 값을 정수로 변환
            int preferenceHeight;
            try {
                preferenceHeight = Integer.parseInt(preferenceHeightStr);
            } catch (NumberFormatException e) {
                request.setAttribute("msg", "키 값은 숫자여야 합니다!");
                request.setAttribute("flag", false);
                forward.setPath("/Metronic-Shop-UI-master/theme/Alert.jsp");
                forward.setRedirect(false);
                return forward;
            }

            // DTO 객체 생성 및 설정
            PreferenceDTO preferenceDTO = new PreferenceDTO();
            preferenceDTO.setUserEmail(userEmail);
            preferenceDTO.setPreferenceHeight(preferenceHeight);
            preferenceDTO.setPreferenceBody(preferenceBody);
            preferenceDTO.setPreferenceAge(preferenceAge);

            // 선택적 필드 설정
            String condition = request.getParameter("condition");
            String searchKeyword = request.getParameter("searchKeyword");

            if (condition != null && !condition.isEmpty()) {
                preferenceDTO.setCondition(condition);
            }

            if (searchKeyword != null && !searchKeyword.isEmpty()) {
                preferenceDTO.setSearchKeyword(searchKeyword);
            }

            System.out.println("preferenceDTO[" + preferenceDTO + "]");

            boolean result = preferenceDAO.insert(preferenceDTO);
            if (result) {
                // 세션에서 기본 정보 제거
                session.removeAttribute("userEmail");
                session.removeAttribute("preferenceHeight");
                session.removeAttribute("preferenceBody");
                session.removeAttribute("preferenceAge");

                // 선호 입력 성공
                request.setAttribute("preferenceDTO", preferenceDTO);
                request.setAttribute("msg", "선호 내용 입력 성공!");
                request.setAttribute("flag", true);
                request.setAttribute("url", "loginPage.do");
            } else {
                // 선호 입력 실패
                request.setAttribute("msg", "선호 내용 입력 실패!");
                request.setAttribute("flag", false);
            }
            forward.setPath("/Metronic-Shop-UI-master/theme/Alert.jsp");
            forward.setRedirect(false);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "선호 입력 중 오류가 발생했습니다!");
            request.setAttribute("flag", false);
            forward.setPath("/Metronic-Shop-UI-master/theme/Alert.jsp");
            forward.setRedirect(false);
        }

        return forward;
    }
}