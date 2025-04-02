package controller.action;

import controller.common.Action;
import controller.common.ActionForward;
import controller.common.AjaxAction;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.checkerframework.checker.units.qual.A;
import org.example.webapp.model.dao.UserDAO;
import org.example.webapp.model.dto.UserDTO;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;

public class CheckEmailAction implements Action, AjaxAction {
    @Override
    public ActionForward execute(HttpServletRequest request) {
        System.out.println("CheckEmailAction의 execute 메소드 호출됨 - 이 메소드는 사용되지 않습니다.");
        return null; // 이 메소드는 실제로 사용되지 않음
    }

    @Override
    public void executeAjax(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("CheckEmailAction의 executeAjax 메소드 실행");

        // 이메일 파라미터 받기
        String userEmail = request.getParameter("userEmail");
        System.out.println("checkUserEmail: " + userEmail);

        // socialType 파라미터 받기 (소셜 로그인 타입)
        String socialType = request.getParameter("socialType");
        if (socialType == null || socialType.trim().isEmpty()) {
            socialType = "noraml"; // 기본값 설정
        }
        System.out.println("Social Type: " + socialType);

        // UserDAO를 통해 이메일 중복 체크
        UserDAO userDAO = new UserDAO();
        UserDTO userDTO = new UserDTO();
        userDTO.setUserEmail(userEmail);
        userDTO.setSocialType(socialType);
        userDTO.setCondition("SELECTONE_CHECK");

        UserDTO result = userDAO.selectOne(userDTO);
        boolean isAvailable = (result == null); // result가 null이면 중복되지 않은 이메일
        System.out.println("checkEmail Duplicate: " + (isAvailable ? "OK" : "USE"));

        // JSON 응답 생성
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("available", isAvailable);

        if (isAvailable) {
            jsonResponse.put("message", "사용 가능한 이메일입니다.");
        } else {
            jsonResponse.put("message", "이미 사용 중인 이메일입니다.");
        }
        System.out.println("응답 JSON: " + jsonResponse.toJSONString());

        // JSON 응답 전송
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(jsonResponse.toJSONString());
        out.flush();
        System.out.println("JSON OK");
    }

}