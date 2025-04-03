package controller.action;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.webapp.model.dao.*;
import org.example.webapp.model.dto.*;


import java.util.ArrayList;

public class MyPageAction implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request) {
        System.out.println("CTRL 로그: MyPageAction");
        ActionForward forward = new ActionForward();

        HttpSession session = request.getSession();
        String userEmail = (String) session.getAttribute("userEmail");

        if (userEmail == null) {
            // 로그인되지 않은 경우
            System.out.println("Mypage Action Log: userEmail is null");
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

        PaymentDTO paymentDTO = new PaymentDTO();
        PaymentDAO paymentDAO = new PaymentDAO();
        paymentDTO.setUserEmail(userEmail);
        paymentDTO.setCondition("SELECTALL_PRODUCTLIST");

        ParticipantDTO participantDTO = new ParticipantDTO();
        ParticipantDAO participantDAO = new ParticipantDAO();
        participantDTO.setParticipantUserEmail(userEmail);
        participantDTO.setCondition("SELECTALL_EVENTPRINT");

        UserDTO user = userDAO.selectOne(userDTO);
        PreferenceDTO preference = preferenceDAO.selectOne(preferenceDTO);
        ArrayList<PaymentDTO> paymentList = paymentDAO.selectAll(paymentDTO);
        ArrayList<ParticipantDTO> participantList = participantDAO.selectAll(participantDTO);
        for(ParticipantDTO p : participantList) {
            System.out.println(p);
        }

        if (user != null) {
            request.setAttribute("paymentList", paymentList);
            request.setAttribute("userDTO", user); // 첫 번째 사용자 정보
            request.setAttribute("preferenceDTO", preference);
            request.setAttribute("participantList", participantList);
            forward.setPath("/Metronic-Shop-UI-master/theme/MyPage.jsp");
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