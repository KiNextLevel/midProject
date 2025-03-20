package org.example.webapp.controller.action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.webapp.controller.common.Action;
import org.example.webapp.controller.common.ActionForward;
import org.example.webapp.model.dao.UserDAO;
import org.example.webapp.model.dto.UserDTO;

public class JoinNextAction implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request) {
        System.out.println("CONT 로그: JoinNextAction 도착");
        ActionForward forward = new ActionForward();
        HttpSession session = request.getSession();
        UserDAO userDAO = new UserDAO();
        UserDTO userDTO = (UserDTO) session.getAttribute("userDTO");
        userDTO.setUserNickname(request.getParameter("userNickname"));
        userDTO.setUserRegion(request.getParameter("userRegion"));
        userDTO.setUserReligion(request.getParameter("userReligion"));
        userDTO.setUserBirth(request.getParameter("userBirth"));
        userDTO.setUserBody(request.getParameter("userBody"));
        userDTO.setUserDescription(request.getParameter("userDescription"));
        userDTO.setUserDrink(Integer.parseInt(request.getParameter("userDrink")));
        userDTO.setUserEducation(request.getParameter("userEducation"));
        userDTO.setUserGender(Integer.parseInt(request.getParameter("userGender")) == 0);
        userDTO.setUserHeight(Integer.parseInt(request.getParameter("userHeight")));
        userDTO.setUserJob(request.getParameter("userJob"));
        userDTO.setUserMbti(request.getParameter("userMbti"));
        userDTO.setUserPhone(request.getParameter("userPhone"));
        userDTO.setUserProfile(request.getParameter("userProfile"));
        userDTO.setUserSmoke(Integer.parseInt(request.getParameter("userSmoke")) == 0);
        System.out.println("JOIN 로그 가져온 ID[" + userDTO.getUserEmail() + "]");
        // 중복이 없으면 회원가입 진행
        if (userDAO.insert(userDTO)) {
            request.setAttribute("userDTO", userDTO);
            request.setAttribute("msg", "회원가입 성공!");
            request.setAttribute("flag", true);
            request.setAttribute("url", "loginPage.do");
        } else {
            request.setAttribute("msg", "회원가입 실패!");
            request.setAttribute("flag", false);
        }
        forward.setPath("alert.jsp");
        forward.setRedirect(false);
        return forward;
    }
}
