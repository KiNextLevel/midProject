package controller.action;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.webapp.model.dao.PreferenceDAO;
import org.example.webapp.model.dto.PreferenceDTO;

// 사용자의 취향을 선택하게 하고 DB에 저장하는 Action
public class UserPreferenceAction implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request) {
        // 시스템 로그
        System.out.println("CONT 로그: UserPreferenceAction 도착");
        ActionForward forward = new ActionForward();
        HttpSession session = request.getSession();
        PreferenceDAO preferenceDAO = new PreferenceDAO();

        try {
            // 폼에서 전송된 데이터 받기
            // 사용자 이메일(식별용)
            String userEmail = request.getParameter("userEmail");
            // 선호 키
            String preferenceHeightStr = request.getParameter("preferenceHeight");
            // 선호 체형
            String preferenceBody = request.getParameter("preferenceBody");
            // 선호 나이
            String preferenceAge = request.getParameter("preferenceAge");

            // 시스템 로그
            System.out.println("userEmail: " + userEmail);
            System.out.println("preferenceHeightStr: " + preferenceHeightStr);
            System.out.println("preferenceBody: " + preferenceBody);
            System.out.println("preferenceAge: " + preferenceAge);

            // userEmail이 없으면 세션에서 가져오기
            if (userEmail == null || userEmail.isEmpty()) {
                userEmail = (String) session.getAttribute("userEmail");
            }

            // 데이터 유효성 검사
            // 모든 필드가 다 입력되어있는지 확인
            if (userEmail == null || preferenceHeightStr == null || preferenceBody == null || preferenceAge == null) {
                request.setAttribute("msg", "필수 정보가 누락되었습니다!");
                request.setAttribute("flag", false);
                forward.setPath("/Metronic-Shop-UI-master/theme/Alert.jsp");
                forward.setRedirect(false);
                return forward;
            }

            // 키 값을 정수로 변환
            // 정수형이 아니면 예외처리
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

            // 선호정보를 담을 DTO 객체 생성 및 설정
            PreferenceDTO preferenceDTO = new PreferenceDTO();
            preferenceDTO.setUserEmail(userEmail);
            preferenceDTO.setPreferenceHeight(preferenceHeight);
            preferenceDTO.setPreferenceBody(preferenceBody);
            preferenceDTO.setPreferenceAge(preferenceAge);

            // 선택적 필드 설정
            // 필수가 아니므로 값이 있을 때만 설정
            String condition = request.getParameter("condition");
            String searchKeyword = request.getParameter("searchKeyword");

            // condition 값이 있으면 DTO에 설정
            if (condition != null && !condition.isEmpty()) {
                preferenceDTO.setCondition(condition);
            }

            // searchKeyword 값이 있으면 DTO에 설정
            if (searchKeyword != null && !searchKeyword.isEmpty()) {
                preferenceDTO.setSearchKeyword(searchKeyword);
            }

            // 시스템 로그
            System.out.println("preferenceDTO[" + preferenceDTO + "]");

            // DB에 선호도 정보 저장
            boolean result = preferenceDAO.insert(preferenceDTO);
            // 저장 성공 시
            if (result) {
                // 세션에서 기존에 받았던 기본 정보 제거
                session.removeAttribute("userEmail");
                session.removeAttribute("preferenceHeight");
                session.removeAttribute("preferenceBody");
                session.removeAttribute("preferenceAge");

                // 선호 입력 성공
                request.setAttribute("preferenceDTO", preferenceDTO);
                request.setAttribute("msg", "선호 내용 입력 성공!");
                request.setAttribute("flag", true);
                request.setAttribute("url", "loginPage.do");
            }
            // 저장 실패 시
            else {
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