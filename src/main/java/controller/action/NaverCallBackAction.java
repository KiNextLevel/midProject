package controller.action;

import controller.asyn.RandomPassword;
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
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.UUID;

public class NaverCallBackAction implements Action {
    private static final String CLIENT_ID = "HPtl9HdFUiGzoDPAPQ4a";
    private static final String CLIENT_SECRET = "7H_NxuX3oo";
    private static final String REDIRECT_URI = "http://localhost:8088/Metronic-Shop-UI-master/theme/naverCallback.do";

    @Override
    public ActionForward execute(HttpServletRequest request) {
        ActionForward forward = new ActionForward();

        try {
            String code = request.getParameter("code");
            String state = request.getParameter("state");

            // 접근 토큰 발급 요청
            String tokenUrl = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code"
                    + "&client_id=" + CLIENT_ID
                    + "&client_secret=" + CLIENT_SECRET
                    + "&redirect_uri=" + URLEncoder.encode(REDIRECT_URI, "UTF-8")
                    + "&code=" + code
                    + "&state=" + state;

            URL url = new URL(tokenUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            JSONParser parser = new JSONParser();
            JSONObject tokenResponse = (JSONObject) parser.parse(sb.toString());
            String accessToken = (String) tokenResponse.get("access_token");

            // 접근 토큰을 이용하여 사용자 정보 가져오기
            String apiURL = "https://openapi.naver.com/v1/nid/me";
            URL userInfoUrl = new URL(apiURL);
            HttpURLConnection userInfoConn = (HttpURLConnection) userInfoUrl.openConnection();
            userInfoConn.setRequestMethod("GET");
            userInfoConn.setRequestProperty("Authorization", "Bearer " + accessToken);

            br = new BufferedReader(new InputStreamReader(userInfoConn.getInputStream()));
            sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            JSONObject userInfoResponse = (JSONObject) parser.parse(sb.toString());
            JSONObject userInfo = (JSONObject) userInfoResponse.get("response");
            System.out.println(userInfoResponse + " : userInfoResponse");
            System.out.println(userInfo + " : userInfo");

            // 네이버에서 받은 정보
            String email = (String) userInfo.get("email");
            String name = (String) userInfo.get("name");
            System.out.println(email + " email");
            System.out.println(name + " name");

            // UserDAO를 이용하여 회원 정보 조회
            UserDAO userDAO = new UserDAO();

            // 이메일로 사용자 조회
            UserDTO searchDTO = new UserDTO();
            searchDTO.setUserEmail(email);
            searchDTO.setCondition("SELECTONE_CHECK");

            UserDTO user = userDAO.selectOne(searchDTO);

            if (user == null) {
                // 회원 정보가 없으면 회원가입 진행
                // 세션에 네이버에서 받은 정보 저장 (JoinAction과 동일한 방식으로)
                HttpSession session = request.getSession();
                session.setAttribute("userEmail", email);
                session.setAttribute("userName", name);
                // 비밀번호는 랜덤하게 생성 (소셜 로그인은 비밀번호가 필요 없지만 DB 구조상 필요할 수 있음)
                String randomPassword = RandomPassword.generateRandomPassword();
                session.setAttribute("userPassword", randomPassword);
                // 소셜 로그인 타입 저장
                session.setAttribute("socialType", "naver");

                // 회원가입 페이지로 리다이렉트
                request.setAttribute("msg", "네이버 계정으로 회원가입을 진행합니다.");
                request.setAttribute("flag", true);
                request.setAttribute("url", "/Metronic-Shop-UI-master/theme/JoinPage.jsp");
                forward.setPath("/Metronic-Shop-UI-master/theme/alert.jsp");
                forward.setRedirect(false);
            } else {
                // 기존 회원이면 로그인 처리
                searchDTO.setCondition("SELECTONE_USERINFO");
                user = userDAO.selectOne(searchDTO);
                if (user.getUserRole() == 0 || user.getUserRole() == 1) {
                    // 세션에 로그인 정보 저장
                    HttpSession session = request.getSession();
                    session.setAttribute("userEmail", user.getUserEmail());
                    session.setAttribute("userRole", user.getUserRole());
                    session.setAttribute("userPremium", user.isUserPreminum());

                    // 로그인 성공 메시지 및 메인 페이지로 리다이렉트
                    request.setAttribute("msg", "네이버 계정으로 로그인되었습니다.");
                    request.setAttribute("flag", true);
                    request.setAttribute("url", "mainPage.do");
                    forward.setPath("/Metronic-Shop-UI-master/theme/alert.jsp");
                    forward.setRedirect(false);
                } else { //블랙 or 탈퇴한 회원이면 로그인 불가능
                    // 로그인 페이지로 리다이렉트
                    request.setAttribute("msg", "블랙 계정이나 탈퇴한 계정은 로그인 할 수 없습니다.");
                    request.setAttribute("flag", true);
                    request.setAttribute("url", "loginPage.do");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "네이버 로그인 중 오류가 발생했습니다.");
            request.setAttribute("flag", false);
            forward.setPath("/Metronic-Shop-UI-master/theme/alert.jsp");
            forward.setRedirect(false);
        }

        return forward;
    }
}