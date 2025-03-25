package controller.action;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.webapp.model.dao.PreferenceDAO;
import org.example.webapp.model.dao.UserDAO;
import org.example.webapp.model.dto.PreferenceDTO;
import org.example.webapp.model.dto.UserDTO;

public class UpdateProfileAction implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request) {
        System.out.println("CTRL 로그: UpdateProfileAction");
        ActionForward forward = new ActionForward();
        HttpSession session = request.getSession();
        String userEmail = (String) session.getAttribute("userEmail");

        // 로그인 체크
        if (userEmail == null) {
            System.out.println("UpdateProfile Action 로그: userEmail is null");
            forward.setPath("loginPage.do");
            forward.setRedirect(true);
            return forward;
        }

        try {
            // 폼에서 전송된 데이터 받기
            // 사용자 정보
            String userDescription = request.getParameter("introduction"); // 자기소개
            String userHeightStr = request.getParameter("userHeight"); // 키
            String userBody = request.getParameter("userBody"); // 체형
            String userEducation = request.getParameter("userEducation"); // 학력
            String userJob = request.getParameter("userJob"); // 직업
            String userReligion = request.getParameter("userReligion"); // 종교
            String userRegion = request.getParameter("userRegion"); // 지역
            String userMbti = request.getParameter("userMbti"); // MBTI
            String userDrinkStr = request.getParameter("userDrink"); // 음주
            String userSmokeStr = request.getParameter("userSmoke"); // 흡연



            // 선호 정보
            Integer preferenceHeight = Integer.valueOf(request.getParameter("preferenceHeight"));
            String preferenceBody = request.getParameter("preferenceBody");
            String preferenceAge = request.getParameter("preferenceAge");


            // 폼에서 전송된 데이터 받기 부분 바로 아래에 추가
            System.out.println("폼 데이터 로그: userDescription=" + userDescription);
            System.out.println("폼 데이터 로그: userHeight=" + userHeightStr);
            System.out.println("폼 데이터 로그: userBody=" + userBody);
            System.out.println("폼 데이터 로그: userEducation=" + userEducation);
            System.out.println("폼 데이터 로그: userJob=" + userJob);
            System.out.println("폼 데이터 로그: userReligion=" + userReligion);
            System.out.println("폼 데이터 로그: userRegion=" + userRegion);
            System.out.println("폼 데이터 로그: userMbti=" + userMbti);
            System.out.println("폼 데이터 로그: userDrink=" + userDrinkStr);
            System.out.println("폼 데이터 로그: userSmoke=" + userSmokeStr);
            System.out.println("폼 데이터 로그: preferenceHeight=" + request.getParameter("preferenceHeight"));
            System.out.println("폼 데이터 로그: preferenceBody=" + preferenceBody);
            System.out.println("폼 데이터 로그: preferenceAge=" + preferenceAge);

            // UserDTO 객체 생성 및 설정
            UserDTO userDTO = new UserDTO();
            userDTO.setUserEmail(userEmail);
            userDTO.setUserDescription(userDescription); // 자기소개 설정

            // 숫자형 데이터 변환 처리 (키)
            if (userHeightStr != null && !userHeightStr.isEmpty()) {
                try {
                    int userHeight = Integer.parseInt(userHeightStr);
                    userDTO.setUserHeight(userHeight);
                } catch (NumberFormatException e) {
                    System.out.println("UpdateProfile Action 로그: 키 변환 오류 - " + e.getMessage());
                }
            }

            userDTO.setUserBody(userBody);
            userDTO.setUserEducation(userEducation);
            userDTO.setUserJob(userJob);
            userDTO.setUserReligion(userReligion);
            userDTO.setUserRegion(userRegion);
            userDTO.setUserMbti(userMbti);

            // 음주 정보 처리
            if (userDrinkStr != null) {
                int userDrink = 0; // 기본값: 전혀 안 함
                if (userDrinkStr.equals("가끔 마심")) {
                    userDrink = 1;
                } else if (userDrinkStr.equals("자주 마심")) {
                    userDrink = 2;
                }
                userDTO.setUserDrink(userDrink);
            }

            // 흡연 정보 처리
            if (userSmokeStr != null) {
                boolean userSmoke = userSmokeStr.equals("yes");
                userDTO.setUserSmoke(userSmoke);
            }

            userDTO.setCondition("UPDATE_PROFILE");

            // PreferenceDTO 객체 생성 및 설정
            PreferenceDTO preferenceDTO = new PreferenceDTO();
            preferenceDTO.setUserEmail(userEmail);
            preferenceDTO.setPreferenceHeight(preferenceHeight);
            preferenceDTO.setPreferenceBody(preferenceBody);
            preferenceDTO.setPreferenceAge(preferenceAge);
            preferenceDTO.setCondition("UPDATE");

            // DAO 객체 생성
            UserDAO userDAO = new UserDAO();
            PreferenceDAO preferenceDAO = new PreferenceDAO();

            // 데이터베이스 업데이트
            boolean userUpdateResult = userDAO.update(userDTO);

            // 선호 정보 존재 여부 확인
            PreferenceDTO existingPreference = new PreferenceDTO();
            existingPreference.setUserEmail(userEmail);
            existingPreference.setCondition("SELECTONE");
            existingPreference = preferenceDAO.selectOne(existingPreference);

            boolean preferenceResult = true; // 기본값 설정

            if (existingPreference == null) {
                // 선호 정보가 없으면 새로 생성
                System.out.println("UpdateProfile Action 로그: 선호 정보 신규 생성");
                preferenceResult = preferenceDAO.insert(preferenceDTO);
            } else {
                // 선호 정보가 있으면 업데이트
                System.out.println("UpdateProfile Action 로그: 선호 정보 업데이트");
                preferenceResult = preferenceDAO.update(preferenceDTO);
            }

            if (userUpdateResult && preferenceResult) {
                System.out.println("UpdateProfile Action 로그: 사용자 정보 및 선호 정보 업데이트 성공");

                // 세션에 성공 메시지 저장
                session.setAttribute("updateMessage", "프로필 정보가 성공적으로 업데이트되었습니다.");

                // 마이페이지로 리다이렉트
                forward.setPath("myPage.do");
                forward.setRedirect(true);
            } else {
                System.out.println("UpdateProfile Action 로그: 사용자 정보 또는 선호 정보 업데이트 실패");

                // 업데이트 실패 시 에러 메시지와 함께 수정 페이지로 돌아감
                request.setAttribute("errorMessage", "프로필 정보 업데이트에 실패했습니다. 다시 시도해주세요.");

                // 기존 입력 정보 유지를 위해 다시 설정
                request.setAttribute("userDTO", userDTO);
                request.setAttribute("preferenceDTO", preferenceDTO);

                forward.setPath("/Metronic-Shop-UI-master/theme/MyPageEdit.jsp");
                forward.setRedirect(false);
            }

        } catch (Exception e) {
            System.out.println("UpdateProfile Action 로그: 예외 발생 - " + e.getMessage());
            e.printStackTrace();

            // 예외 발생 시 에러 메시지와 함께 수정 페이지로 돌아감
            request.setAttribute("errorMessage", "프로필 정보 처리 중 오류가 발생했습니다: " + e.getMessage());
            forward.setPath("/Metronic-Shop-UI-master/theme/MyPageEdit.jsp");
            forward.setRedirect(false);
        }

        return forward;
    }
}