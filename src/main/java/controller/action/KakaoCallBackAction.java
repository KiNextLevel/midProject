package controller.action;

import controller.common.Action;
import controller.common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.webapp.model.dao.UserDAO;
import org.example.webapp.model.dto.UserDTO;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

public class KakaoCallBackAction implements Action {
    public ActionForward execute(HttpServletRequest request) {
        ActionForward forward = new ActionForward();
        String code = request.getParameter("code");  // 카카오에서 전달한 인가 코드
        System.out.println("kakaoCallBack log - code = [" + code+"]");

        String accessToken = null;
        try {
            // 액세스 토큰을 얻기 위한 POST 요청
            accessToken = getAccessToken(code);
            System.out.println("kakaoCallBack log - accessToken = [" + accessToken+"]");

            // 액세스 토큰으로 사용자 정보 가져오기
            String userInfo = getUserInfo(accessToken);
            System.out.println("kakaoCallBack log - userInfo: ["+userInfo+"]");

            // JSONParser를 사용하여 사용자 정보에서 이메일과 닉네임 추출
            JSONParser parser = new JSONParser();
            JSONObject jsonResponse = (JSONObject) parser.parse(userInfo);
            // kakao_account 객체 추출
            JSONObject kakaoAccount = (JSONObject) jsonResponse.get("kakao_account");
            // 이메일 추출
            String email = (String) kakaoAccount.get("email");
            // 이름 추출 (이름이 있을 경우만)
            String name = (String) kakaoAccount.get("name");
            System.out.println("kakaologin log: email: [" + email + "], name: [" + name + "]");


            // DB에 사용자 정보 저장
            // UserDAO를 이용하여 회원 정보 조회
            UserDAO userDAO = new UserDAO();

            // 이메일로 사용자 조회
            UserDTO searchDTO = new UserDTO();
            searchDTO.setUserEmail(email);
            searchDTO.setCondition("SELECTONE_CHECK");

            UserDTO user = userDAO.selectOne(searchDTO);
            if (user == null) { // 회원가입 진행
                HttpSession session = request.getSession();
                session.setAttribute("userEmail", email);
                System.out.println("KakaoLogin Log: userEmail: [" + email+"]");
                session.setAttribute("userName", name);
                // 비밀번호는 랜덤하게 생성 (소셜 로그인은 비밀번호가 필요 없지만 DB 구조상 필요할 수 있음)
                String randomPassword = generateRandomPassword();
                session.setAttribute("userPassword", randomPassword);
                // 소셜 로그인 타입 저장
                session.setAttribute("socialType", "kakao");

                // 회원가입 페이지로 리다이렉트
                request.setAttribute("msg", "카카오 계정으로 회원가입을 진행합니다.");
                request.setAttribute("flag", true);
                request.setAttribute("url", "JoinPage.jsp");
            } else { // 기존 회원이면 로그인 처리
                searchDTO.setCondition("SELECTONE_USERINFO");
                user = userDAO.selectOne(searchDTO);
                // 회원이나 관리자일 경우만 로그인
                if (user.getUserRole() == 0 || user.getUserRole() == 1) {
                    // 세션에 로그인 정보 저장
                    HttpSession session = request.getSession();
                    session.setAttribute("userEmail", user.getUserEmail());

                    // 로그인 성공 메시지 및 메인 페이지로 리다이렉트
                    request.setAttribute("msg", "카카오 계정으로 로그인되었습니다.");
                    request.setAttribute("flag", true);
                    request.setAttribute("url", "mainPage.do");
                } else { //블랙 or 탈퇴한 회원이면 로그인 불가능
                    // 로그인 페이지로 리다이렉트
                    request.setAttribute("msg", "블랙 계정이나 탈퇴한 계정은 로그인 할 수 없습니다.");
                    request.setAttribute("flag", true);
                    request.setAttribute("url", "loginPage.do");
                }
            }
            forward.setPath("alert.jsp");
            forward.setRedirect(false);
        } catch (IOException | org.json.simple.parser.ParseException e) {
            e.printStackTrace(); // 예외 로그
            request.setAttribute("msg", "카카오 로그인 중 오류가 발생했습니다.");
            request.setAttribute("flag", false);
            forward.setPath("alert.jsp");
            forward.setRedirect(false);
        }
        return forward;
    }

    private String getAccessToken(String code) {
        String tokenUrl = "https://kauth.kakao.com/oauth/token";
        String redirectUri = "http://localhost:8088/Metronic-Shop-UI-master/theme/kakaoCallBack.do";  // 설정한 Redirect URI

        String params = "grant_type=authorization_code&client_id=cb9656ab4895e6ee319e89e74f28a308&redirect_uri=" + redirectUri + "&code=" + code;

        String accessToken = null;  // 액세스 토큰을 저장할 변수

        try {
            // URL 연결 설정
            URL url = new URL(tokenUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.getOutputStream().write(params.getBytes("UTF-8"));

            // 서버 응답 받기
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer responseStr = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                responseStr.append(inputLine);
            }
            in.close();

            // JSON으로 응답받음 (Access Token 포함)
            JSONParser parser = new JSONParser();
            JSONObject jsonResponse = (JSONObject) parser.parse(responseStr.toString());

            // access_token 추출
            accessToken = (String) jsonResponse.get("access_token");
        } catch (IOException e) {
            // 네트워크 오류 처리
            e.printStackTrace();
            System.err.println("네트워크 오류 발생: " + e.getMessage());
        } catch (org.json.simple.parser.ParseException e) {
            // JSON 파싱 오류 처리
            e.printStackTrace();
            System.err.println("JSON 파싱 오류 발생: " + e.getMessage());
        } catch (Exception e) {
            // 기타 예기치 않은 오류 처리
            e.printStackTrace();
            System.err.println("알 수 없는 오류 발생: " + e.getMessage());
        }

        return accessToken;
    }

    // Access Token을 이용해 사용자 정보 가져오기
    private String getUserInfo(String accessToken) throws IOException {
        String apiUrl = "https://kapi.kakao.com/v2/user/me";
        URL url = new URL(apiUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Authorization", "Bearer " + accessToken);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer responseStr = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            responseStr.append(inputLine);
        }
        in.close();

        return responseStr.toString();
    }

    // 랜덤 비밀번호 생성 메서드
    private String generateRandomPassword() {
        return UUID.randomUUID().toString().substring(0, 10);
    }
}