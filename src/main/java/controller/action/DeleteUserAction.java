package controller.action;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.webapp.model.dao.UserDAO;
import org.example.webapp.model.dto.UserDTO;

public class DeleteUserAction implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request) {
        System.out.println("CONT 로그: DELETE USER ACTION 도착");
        ActionForward forward = new ActionForward();

        // 세션에서 사용자 이메일 정보 가져오기
        HttpSession session = request.getSession();
        String userEmail = (String) session.getAttribute("userEmail");

        // 사용자 이메일이 없으면 로그인 페이지로 리다이렉트
        if (userEmail == null || userEmail.isEmpty()) {
            request.setAttribute("msg", "로그인이 필요한 서비스입니다.");
            request.setAttribute("flag", false);
            request.setAttribute("url", "/Metronic-Shop-UI-master/theme/LoginPage.jsp");
            forward.setPath("/Metronic-Shop-UI-master/theme/Alert.jsp");
            forward.setRedirect(false);
            return forward;
        }

        // 사용자 정보 설정
        UserDAO userDAO = new UserDAO();
        UserDTO userDTO = new UserDTO();
        userDTO.setUserEmail(userEmail);
        userDTO.setUserRole(3); // 회원 탈퇴 상태를 나타내는 ROLE 3으로 설정
        userDTO.setCondition("UPDATE_ROLE"); // UPDATE_ROLE 쿼리 사용 설정

        System.out.println("DELETE USER 로그: 탈퇴할 사용자 이메일 [" + userDTO.getUserEmail() + "]");
        System.out.println("DELETE USER 로그: 변경할 ROLE [" + userDTO.getUserRole() + "]");

        // 회원 상태 변경 (ROLE을 3으로 변경)
        if (userDAO.update(userDTO)) {
            // 탈퇴 성공 시 세션 무효화
            session.invalidate();

            request.setAttribute("msg", "회원 탈퇴가 완료되었습니다. 그동안 서비스를 이용해주셔서 감사합니다.");
            request.setAttribute("flag", true);
            request.setAttribute("url", "deleteUserAlert.do");
        } else {
            // 탈퇴 실패
            request.setAttribute("msg", "회원 탈퇴 처리 중 오류가 발생했습니다. 다시 시도해주세요.");
            request.setAttribute("flag", false);
            request.setAttribute("url", "/Metronic-Shop-UI-master/theme/AccountDelete.jsp");
        }

        forward.setPath("/Metronic-Shop-UI-master/theme/Alert.jsp");
        forward.setRedirect(false);
        return forward;
    }
}
