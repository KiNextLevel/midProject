package controller.action;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.webapp.model.dao.PreferenceDAO;
import org.example.webapp.model.dao.UserDAO;
import org.example.webapp.model.dto.PreferenceDTO;
import org.example.webapp.model.dto.UserDTO;

public class UpdateProfilePageAction implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request) {
        System.out.println("CTRL 로그: UpdateProfilePageAction");
        ActionForward forward = new ActionForward();
        HttpSession session = request.getSession();
        String userEmail = (String) session.getAttribute("userEmail");

        if (userEmail == null) {
            // 로그인되지 않은 경우
            System.out.println("UpdateProfilePage Action Log: userEmail is null");
            forward.setPath("loginPage.do");
            forward.setRedirect(true);
            return forward;
        }

        UserDTO userDTO = new UserDTO();
        UserDAO userDAO = new UserDAO();
        userDTO.setUserEmail(userEmail);
        userDTO.setCondition("SELECTONE_USERINFO"); // 조건 설정 추가

        PreferenceDTO preferenceDTO = new PreferenceDTO();
        PreferenceDAO preferenceDAO = new PreferenceDAO();
        preferenceDTO.setUserEmail(userEmail);
        preferenceDTO.setCondition("SELECTONE");

        UserDTO user = userDAO.selectOne(userDTO);
        PreferenceDTO preference = preferenceDAO.selectOne(preferenceDTO);

        if (user != null) {
            request.setAttribute("userDTO", user); // 첫 번째 사용자 정보
            request.setAttribute("preferenceDTO", preference);
            forward.setPath("/Metronic-Shop-UI-master/theme/MyPageEdit.jsp");
            forward.setRedirect(false);
        } else {
            System.out.println("마이페이지 로그[사용자 정보 없음]");
            // 사용자 정보가 없을 때 처리
            forward.setPath("loginPage.do");
            forward.setRedirect(true);
        }
        return forward;
    }
}
