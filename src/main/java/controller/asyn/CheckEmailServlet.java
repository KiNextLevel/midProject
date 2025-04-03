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
public class CheckEmailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("CheckEmailDuplicateServlet 실행");

        // 요청 및 응답 인코딩 설정
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");

        // 이메일 파라미터 받기
        String userEmail = request.getParameter("userEmail");

        // 소셜 타입 파라미터 받기 (선택적)
        String socialType = request.getParameter("socialType");
        if (socialType == null || socialType.isEmpty()) {
            socialType = "normal"; // 기본값을 빈 문자열이 아닌 "normal"로 설정
        }

        System.out.println("체크할 이메일: " + userEmail + ", 소셜 타입: " + socialType);

        // JSON 응답 객체 생성
        JSONObject jsonResponse = new JSONObject();

        try {
            // 파라미터 유효성 검사
            if (userEmail == null || userEmail.trim().isEmpty()) {
                jsonResponse.put("isDuplicate", true);
                jsonResponse.put("message", "이메일을 입력해주세요.");
                sendResponse(response, jsonResponse);
                return;
            }

            // UserDTO 객체 생성 및 설정
            UserDTO userDTO = new UserDTO();
            userDTO.setUserEmail(userEmail);
            userDTO.setSocialType(socialType);
            userDTO.setCondition("SELECTONE_CHECK"); // 이메일 중복 체크 조건 설정

            // UserDAO를 통한 이메일 중복 체크
            UserDAO userDAO = new UserDAO();

            // 디버깅을 위한 로그 추가
            System.out.println("데이터베이스 조회 시작 - 이메일: " + userEmail);

            UserDTO result = userDAO.selectOne(userDTO);

            // 디버깅을 위한 결과 로그 추가
            System.out.println("select db: " + (result != null ? "EMAIL YET" : "YOU CAN USE"));

            if (result != null) {
                // 사용자 정보가 있으면 중복된 이메일
                System.out.println("중복된 이메일 발견: " + userEmail);
                jsonResponse.put("isDuplicate", true);
                jsonResponse.put("message", "이미 사용 중인 이메일입니다.");
            } else {
                // 사용자 정보가 없으면 사용 가능한 이메일
                System.out.println("사용 가능한 이메일: " + userEmail);
                jsonResponse.put("isDuplicate", false);
                jsonResponse.put("message", "사용 가능한 이메일입니다.");
            }

        } catch (Exception e) {
            // 예외 처리 로직 추가
            System.err.println("이메일 중복 체크 중 오류 발생: " + e.getMessage());
            e.printStackTrace();

            jsonResponse.put("isDuplicate", true); // 오류 발생 시 안전하게 중복으로 처리
            jsonResponse.put("message", "서버 오류가 발생했습니다. 다시 시도해주세요.");
        }

        // 응답 전송
        sendResponse(response, jsonResponse);
        System.out.println("CheckEmailDuplicateServlet 종료");
    }

    // 응답 전송을 위한 별도 메소드
    private void sendResponse(HttpServletResponse response, JSONObject jsonResponse) throws IOException {
        PrintWriter out = response.getWriter();
        out.print(jsonResponse.toString());
        out.flush();
    }
}
