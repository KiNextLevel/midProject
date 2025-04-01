package controller.action;


import controller.common.Action;
import controller.common.ActionForward;
import controller.logic.SendEmail;
import controller.logic.SendMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.webapp.model.dao.UserDAO;
import org.example.webapp.model.dto.UserDTO;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;


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
        userDTO.setCondition("SELECTONE_NONSOCIAL");
        userDTO = userDAO.selectOne(userDTO);
        if (userDTO != null) {
            // session에 userId, userName, role저장
            session.setAttribute("userEmail", userDTO.getUserEmail());
            session.setAttribute("userRole", userDTO.getUserRole());
            session.setAttribute("userPremium", userDTO.isUserPreminum());
            System.out.println("userROle = "+userDTO.getUserRole());

            // url, flag, msg 요청단위 저장
            // alert.jsp에 url, true, msg 보내기
            if (userDTO.getUserRole() == 0) { //유저
                request.setAttribute("msg", "로그인 성공!");
                request.setAttribute("url", "mainPage.do");
                request.setAttribute("flag", true);
                setSession(userDTO, request); // 세션에 정보 저장
            } else if (userDTO.getUserRole() == 1) { // 관리자
                request.setAttribute("msg", "관리자 로그인 성공!");
                request.setAttribute("url", "adminPage.do");
                request.setAttribute("flag", true);
                setSession(userDTO, request); // 세션에 정보 저장
            } else if (userDTO.getUserRole() == 2) { // 블랙
                request.setAttribute("msg", "블랙당한 계정입니다");
                request.setAttribute("flag", false);
            } else if (userDTO.getUserRole() == 3) { // 탈퇴
                request.setAttribute("msg", "탈퇴한 계정입니다");
                request.setAttribute("flag", false);
            } else { //유저
                request.setAttribute("msg", "로그인 성공!");
                request.setAttribute("url", "mainPage.do");
                request.setAttribute("flag", true);
            }
        } else {
            // url, flag, msg 요청단위 저장
            // alert.jsp에 url, false, msg 보내기
            request.setAttribute("msg", "로그인정보가 틀렸습니다");
            request.setAttribute("flag", false);
        }
        actionForward.setPath("/Metronic-Shop-UI-master/theme/alert.jsp");
        actionForward.setRedirect(false);
        return actionForward;
    }

    public void setSession(UserDTO userDTO, HttpServletRequest request) {
        HttpSession session = request.getSession();
        // session에 userId, userName, role저장
        session.setAttribute("userEmail", userDTO.getUserEmail());
        session.setAttribute("userRole", userDTO.getUserRole());
        session.setAttribute("userPremium", userDTO.isUserPreminum());
        System.out.println("userRole = "+userDTO.getUserRole());
    }
}
