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
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

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

            // 네이버에서 받은 정보
            String naverId = (String) userInfo.get("id");
            String email = (String) userInfo.get("email");
            String nickname = (String) userInfo.get("nickname");
            String name = (String) userInfo.get("name");

            // UserDAO를 이용하여 회원 정보 조회
            UserDAO userDAO = new UserDAO();

            // 이메일로 사용자 조회
            UserDTO searchDTO = new UserDTO();
            searchDTO.setUserEmail(email);
            searchDTO.setCondition("SELECTONE_CHECK");

            UserDTO user = userDAO.selectOne(searchDTO);

            if (user == null) {
                // 회원 정보가 없으면 회원가입 처리
                UserDTO newUser = new UserDTO();
                newUser.setUserEmail(email);
                // 임시 비밀번호 생성
                String tempPassword = "naver_" + System.currentTimeMillis();
                newUser.setUserPassword(tempPassword);
                newUser.setUserNickname(nickname != null ? nickname : "네이버사용자");
                newUser.setUserName(name != null ? name : "네이버사용자");

                // 기본값 설정
                newUser.setUserPhone("");
                newUser.setUserGender(false);
                newUser.setUserBirth("");
                newUser.setUserHeight(0);
                newUser.setUserBody("");
                newUser.setUserMbti("");
                newUser.setUserProfile("");
                newUser.setUserEducation("");
                newUser.setUserReligion("");
                newUser.setUserDrink(0);
                newUser.setUserSmoke(false);
                newUser.setUserJob("");
                newUser.setUserRegion("");
                newUser.setUserDescription("");

                // 조건 설정 (기본 회원가입)
                newUser.setCondition("INSERT_PROFILE");

                // 회원가입 처리
                userDAO.insert(newUser);

                // 새로 가입된 사용자 정보 다시 조회
                searchDTO.setCondition("SELECTONE_USERINFO");
                user = userDAO.selectOne(searchDTO);
            } else {
                // 기존 회원이면 전체 정보 조회
                searchDTO.setCondition("SELECTONE_USERINFO");
                user = userDAO.selectOne(searchDTO);
            }

            // 세션에 로그인 정보 저장
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            // 로그인 성공 후 메인 페이지로 리다이렉트
            forward.setRedirect(true);
            forward.setPath("mainPage.do");

        } catch (Exception e) {
            e.printStackTrace();
            forward.setRedirect(true);
            forward.setPath("loginPage.do?error=naver");
        }

        return forward;
    }
}