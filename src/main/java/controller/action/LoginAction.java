package controller.action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.webapp.controller.common.Action;
import org.example.webapp.controller.common.ActionForward;
import org.example.webapp.model.dao.UserDAO;
import org.example.webapp.model.dto.UserDTO;


public class LoginAction implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request) {
		System.out.println("CONT 로그: LOGIN ACTION 도착");
        ActionForward actionForward = new ActionForward();
        UserDTO userDTO = new UserDTO();
        userDTO.setUserEmail(request.getParameter("userEmail"));
        userDTO.setUserPassword(request.getParameter("userPassword"));
        UserDAO userDAO = new UserDAO();
        HttpSession session = request.getSession();
        // 컨디션"로그인"
        userDTO.setCondition("SELECTONE");
        userDTO = userDAO.selectOne(userDTO);
        System.out.println("로그인 로그: " + userDTO);
        if (userDTO != null) {
            // session에 userId, userName, role저장
            session.setAttribute("userEmail", userDTO.getUserEmail());
            session.setAttribute("userRole", userDTO.getUserRole());

            // url, flag, msg 요청단위 저장
            // alert.jsp에 url, true, msg 보내기
            request.setAttribute("msg", "로그인 성공!");
            request.setAttribute("flag", true);
            if (userDTO.getUserRole() == 1) { // 관리자
                request.setAttribute("url", "adminPage.do");
            } else if (userDTO.getUserRole() == 2) { // 블랙
                request.setAttribute("msg", "블랙당한 계정입니다");
                request.setAttribute("flag", false);
            } else if (userDTO.getUserRole() == 3) { // 탈퇴
                request.setAttribute("msg", "탈퇴한 계정입니다");
                request.setAttribute("flag", false);
            } else { //유저
                request.setAttribute("url", "mainPage.do");
            }
        } else {
            // url, flag, msg 요청단위 저장
            // alert.jsp에 url, false, msg 보내기
            request.setAttribute("msg", "로그인정보가 틀렸습니다");
            request.setAttribute("flag", false);
        }
        actionForward.setPath("alert.jsp");
        actionForward.setRedirect(false);
        return actionForward;
    }
}
