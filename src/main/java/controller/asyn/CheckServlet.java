package controller.asyn;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.webapp.model.dao.UserDAO;
import org.example.webapp.model.dto.UserDTO;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/checkEmailDuplicate.do")
public class CheckServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("servlet");
        // 응답 설정
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // 클라이언트에서 전송한 이메일 가져오기
        String email = request.getParameter("userEmail");

        // JSON 응답 객체 생성
        JSONObject jsonResponse = new JSONObject();

        try {
            // DAO와 DTO 객체 생성
            UserDAO userDAO = new UserDAO();
            UserDTO userDTO = new UserDTO();

            // 이메일 설정 및 조회 조건 설정
            userDTO.setUserEmail(email);
            userDTO.setCondition("SELECTONE_CHECK");

            // 이메일 중복 확인
            UserDTO existingUser = userDAO.selectOne(userDTO);

            // 결과에 따라 JSON 응답 설정
            if (existingUser == null) {
                // 중복된 이메일이 없는 경우
                jsonResponse.put("available", true);
                jsonResponse.put("message", "사용 가능한 이메일입니다.");
            } else {
                // 중복된 이메일이 있는 경우
                jsonResponse.put("available", false);
                jsonResponse.put("message", "이미 사용 중인 이메일입니다.");
            }

        } catch (Exception e) {
            // 오류 발생 시
            jsonResponse.put("available", false);
            jsonResponse.put("message", "서버 오류가 발생했습니다.");
            e.printStackTrace();
        }

        // JSON 응답 전송
        PrintWriter out = response.getWriter();
        out.print(jsonResponse.toJSONString());
        out.flush();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // GET 요청도 POST와 동일하게 처리
        doPost(request, response);
    }
}
