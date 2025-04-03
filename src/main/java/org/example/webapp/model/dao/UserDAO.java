package org.example.webapp.model.dao;

import org.example.webapp.model.common.JDBCUtil;
import org.example.webapp.model.dto.UserDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UserDAO {
    // 아이디 중복 검사(네이버, 카카오 등 같은 이메일이더라도 소셜 타입 다르면 회원가입 가능)
    private final String SELECTONE_CHECK = "SELECT USER_EMAIL FROM USER WHERE USER_EMAIL = ? AND SOCIAL_TYPE = ?";
    
    // 소셜 로그인
    private final String SELECTONE = "SELECT USER_EMAIL, USER_PASSWORD, USER_ROLE, USER_PREMIUM FROM USER WHERE USER_EMAIL = ? AND USER_PASSWORD = ? AND SOCIAL_TYPE = ?";

    // 일반 로그인
    private final String SELECTONE_NONSOCIAL = "SELECT USER_EMAIL, USER_PASSWORD, USER_ROLE, USER_PREMIUM FROM USER WHERE USER_EMAIL = ? AND USER_PASSWORD = ?";

    // 해당 유저 전체 정보 불러오기
    private final String SELECTONE_USERINFO = "SELECT * FROM USER WHERE USER_EMAIL = ?";

    // 유저 위도, 경도 정보 불러오기
    private final String SELECTONE_LOCATION = "SELECT USER_EMAIL, USER_LATITUDE, USER_LONGITUDE FROM USER WHERE USER_EMAIL = ?";

    // 유저 전체 정보 불러오기
    private final String SELECTALL = "SELECT * FROM USER ORDER BY USER_REGDATE DESC";

    // 유저 선호 취향 정보 불러오기
    private final String SELCETALL_FAVORITE = "SELECT * FROM PREFERENCE P LEFT JOIN USER U ON P.PREFERENCE_USER_EMAIL = U.USER_EMAIL WHERE U.USER_EMAIL = ?";

    // 사용자가 참가 중인 이벤트 목록 불러오기
    private final String SELECTALL_EVENT = "SELECT * FROM PARTICIPANT P LEFT JOIN USER U ON P.PARTICIPANT_USER_EMAIL = U.USER_EMAIL WHERE U.USER_EMAIL = ?";

    // 유저의 토큰 잔액 정보 불러오기
    private final String SELECTALL_TOKEN = "SELECT USER_TOEKN FROM USER WHERE USER_EMAIL = ?";

    // 유저의 결제한 상품 목록 불러오기
    private final String SELECTALL_PRODUCT = "SELECT * FROM PAYMENT P LEFT JOIN USER U ON P.PAYMENT_USER_EMAIL = U.USER_EMAIL WHERE U.USER_EMAIL = ?";

    // 블랙리스트 유저 불러오기(관리자용)
    private final String SELECTALL_BLACK = "SELECT * FROM USER WHERE USER_ROLE = 2";

    // 회원가입(정보 다 입력) - social_type 컬럼 추가, USER_LATITUDE, USER_LONGITUDE 컬럼 추가
    private final String INSERT = "INSERT INTO USER (USER_EMAIL, USER_PASSWORD, USER_NICKNAME, USER_PHONE, USER_GENDER, USER_BIRTH, USER_HEIGHT, USER_BODY, USER_MBTI," +
            "USER_PROFILE, USER_EDUCATION, USER_RELIGEION, USER_DRINK, USER_SMOKE, USER_JOB, USER_REGION, USER_LATITUDE, USER_LONGITUDE, USER_DESCRIPTION, USER_NAME, SOCIAL_TYPE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";


    // 회원가입(소개 뺴고, 프로필만 입력) - social_type 컬럼 추가, USER_LATITUDE, USER_LONGITUDE 컬럼 추가
    private final String INSERT_PROFILE = "INSERT INTO USER (USER_EMAIL, USER_PASSWORD, USER_NICKNAME, USER_PHONE, USER_GENDER, USER_BIRTH, USER_HEIGHT, USER_BODY, USER_MBTI," +
            "USER_PROFILE, USER_EDUCATION, USER_RELIGEION, USER_DRINK, USER_SMOKE, USER_JOB, USER_REGION, USER_LATITUDE, USER_LONGITUDE, USER_NAME, SOCIAL_TYPE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";


    // 회원가입(프로필 빼고, 소개만 입력) - social_type 컬럼 추가
    private final String INSERT_DESCRIPTION = "INSERT INTO USER (USER_EMAIL, USER_PASSWORD, USER_NICKNAME, USER_PHONE, USER_GENDER, USER_BIRTH, USER_HEIGHT, USER_BODY, USER_MBTI," +
            "USER_EDUCATION, USER_RELIGEION, USER_DRINK, USER_SMOKE, USER_JOB, USER_REGION, USER_DESCRIPTION, USER_NAME, SOCIAL_TYPE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    // 회원 정보 수정 - USER_LATITUDE, USER_LONGITUDE 컬럼 추가
    private final String UPDATE =
            "UPDATE USER SET USER_DESCRIPTION = ?,USER_NICKNAME = ?, USER_HEIGHT = ?, USER_BODY = ?, USER_EDUCATION = ?, USER_JOB = ?, USER_RELIGEION = ?, " +
                    "USER_REGION = ?, USER_LATITUDE = ?, USER_LONGITUDE = ?, USER_MBTI = ?, USER_DRINK = ?, USER_SMOKE = ? WHERE USER_EMAIL = ?";

    // 회원 ROLE 변경(사용자, 블랙, 탈퇴 등)
    private final String UPDATE_ROLE = "UPDATE USER SET USER_ROLE = ? WHERE USER_EMAIL = ?";

    //토큰 추가
    private final String UPDATE_ADD_TOKEN = "UPDATE USER SET USER_TOKEN = ? WHERE USER_EMAIL =?";

    // 회원 프로필사진 변경
    private final String UPDATE_PROFILE_IMAGE = "UPDATE USER SET USER_PROFILE = ? WHERE USER_EMAIL = ?";

    // 회원 프리미엄 변경
    private final String UPDATE_PREMIUM = "UPDATE USER SET USER_PREMIUM = 1 WHERE USER_EMAIL = ?";

    // 회원 탈퇴(유저 role 변경으로 바꿨지만 추후 유지보수 혹은 기능 추가를 위해 남겨둠)
    private final String DELETE = "DELETE FROM USER WHERE USER_EMAIL = ?";

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    // 유저 전체 정보 불러오기
    // 유저 선호 취향 정보 불러오기
    // 참가 중인 이벤트 목록 불러오기
    // 토큰 잔액 정보 불러오기
    // 결제한 상품 목록 불러오기
    // 블랙리스트 유저 불러오기
    public ArrayList<UserDTO> selectAll(UserDTO userDTO) {
        ArrayList<UserDTO> datas = new ArrayList<>();
        try {
            conn = JDBCUtil.connect();
            // 유저 선호 취향 정보 불러오기
            if (userDTO.getCondition().equals("SELECTALL")) {
                pstmt = conn.prepareStatement(SELECTALL);
            }
            // 유저 선호 취향 정보 불러오기
            else if (userDTO.getCondition() != null && userDTO.getCondition().equals("SELECTALL_FAVORITE")) {
                pstmt = conn.prepareStatement(SELCETALL_FAVORITE);
                pstmt.setString(1, userDTO.getUserEmail());
            }
            // 사용자가 참가 중인 이벤트 목록 불러오기
            else if (userDTO.getCondition() != null && userDTO.getCondition().equals("SELECTALL_EVENT")) {
                pstmt = conn.prepareStatement(SELECTALL_EVENT);
                pstmt.setString(1, userDTO.getUserEmail());
            }
            // 토큰 잔액 정보 불러오기
            else if (userDTO.getCondition() != null && userDTO.getCondition().equals("SELECTALL_TOKEN")) {
                pstmt = conn.prepareStatement(SELECTALL_TOKEN);
                pstmt.setString(1, userDTO.getUserEmail());
            }
            // 결제한 상품 목록 불러오기
            else if (userDTO.getCondition() != null && userDTO.getCondition().equals("SELECTALL_PRODUCT")) {
                pstmt = conn.prepareStatement(SELECTALL_PRODUCT);
                pstmt.setString(1, userDTO.getUserEmail());
            }
            // 블랙리스트 유저 불러오기
            else if (userDTO.getCondition() != null && userDTO.getCondition().equals("SELECTALL_BLACK")) {
                pstmt = conn.prepareStatement(SELECTALL_BLACK);
            }

            rs = pstmt.executeQuery();
                while (rs.next()) {
                    UserDTO data = new UserDTO();
                    data.setUserEmail(rs.getString("USER_EMAIL"));
                    data.setUserPassword(rs.getString("USER_PASSWORD"));
                    data.setUserNickname(rs.getString("USER_NICKNAME"));
                    data.setUserPhone(rs.getString("USER_PHONE"));
                    data.setUserRegdate(rs.getDate("USER_REGDATE"));
                    data.setUserGender(rs.getInt("USER_GENDER") == 1);
                    data.setUserBirth(rs.getString("USER_BIRTH"));
                    data.setUserHeight(rs.getInt("USER_HEIGHT"));
                    data.setUserBody(rs.getString("USER_BODY"));
                    data.setUserMbti(rs.getString("USER_MBTI"));
                    data.setUserProfile(rs.getString("USER_PROFILE"));
                    data.setUserEducation(rs.getString("USER_EDUCATION"));
                    data.setUserReligion(rs.getString("USER_RELIGEION"));
                    data.setUserLatitude(rs.getDouble("USER_LATITUDE"));  // 위도 추가
                    data.setUserLongitude(rs.getDouble("USER_LONGITUDE")); //경도 추가
                    data.setUserDrink(rs.getInt("USER_DRINK"));
                    data.setUserSmoke(rs.getInt("USER_SMOKE") == 1);
                    data.setUserJob(rs.getString("USER_JOB"));
                    data.setUserRole(rs.getInt("USER_ROLE"));
                    data.setUserPreminum(rs.getInt("USER_PREMIUM") == 1);
                    data.setUserToken(rs.getInt("USER_TOKEN"));
                    data.setUserRegion(rs.getString("USER_REGION"));
                    data.setUserDescription(rs.getString("USER_DESCRIPTION"));
                    data.setUserName(rs.getString("USER_NAME"));
                datas.add(data);
            }
            return datas;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            JDBCUtil.disconnect(conn, pstmt);
        }
    }

    // 로그인
    public UserDTO selectOne(UserDTO userDTO) {
        UserDTO data = null;
        try {
            conn = JDBCUtil.connect();

            // 조건 확인 및 적절한 쿼리 준비
            if (userDTO.getCondition() != null) {
                // 아이디 중복 검사(네이버, 카카오 등 같은 이메일이더라도 소셜 타입 다르면 회원가입 가능)
                if (userDTO.getCondition().equals("SELECTONE_CHECK")) {
                    pstmt = conn.prepareStatement(SELECTONE_CHECK);
                    pstmt.setString(1, userDTO.getUserEmail());
                    pstmt.setString(2, userDTO.getSocialType());
                    rs = pstmt.executeQuery();
                    if (rs.next()) {
                        // 이메일이 존재하는 경우
                        data = new UserDTO();
                        data.setUserEmail(rs.getString("USER_EMAIL"));
                        System.out.println("이메일 중복 확인: 이미 존재하는 이메일입니다 - " + rs.getString("USER_EMAIL"));
                    } else {
                        // 이메일이 존재하지 않는 경우
                        System.out.println("이메일 중복 확인: 사용 가능한 이메일입니다.");
                        return null; // 중복된 이메일이 없으면 null 반환
                    }
                }
                // 소셜 로그인
                else if (userDTO.getCondition().equals("SELECTONE")) {
                    pstmt = conn.prepareStatement(SELECTONE);
                    pstmt.setString(1, userDTO.getUserEmail());
                    pstmt.setString(2, userDTO.getUserPassword());
                    pstmt.setString(3, userDTO.getSocialType());

                    rs = pstmt.executeQuery();
                    if (rs.next()) {
                        data = new UserDTO();
                        data.setUserEmail(rs.getString("USER_EMAIL"));
                        data.setUserPassword(rs.getString("USER_PASSWORD"));
                        data.setUserRole(rs.getInt("USER_ROLE"));
                        data.setUserPreminum(rs.getInt("USER_PREMIUM") == 1);
                    }
                }
                // 일반 로그인
                else if (userDTO.getCondition().equals("SELECTONE_NONSOCIAL")) {
                    pstmt = conn.prepareStatement(SELECTONE_NONSOCIAL);
                    pstmt.setString(1, userDTO.getUserEmail());
                    pstmt.setString(2, userDTO.getUserPassword());

                    rs = pstmt.executeQuery();
                    if (rs.next()) {
                        data = new UserDTO();
                        data.setUserEmail(rs.getString("USER_EMAIL"));
                        data.setUserPassword(rs.getString("USER_PASSWORD"));
                        data.setUserRole(rs.getInt("USER_ROLE"));
                        data.setUserPreminum(rs.getInt("USER_PREMIUM") == 1);
                    }
                }
                // 해당 유저 전체 정보 불러오기
                else if (userDTO.getCondition().equals("SELECTONE_USERINFO")) {
                    pstmt = conn.prepareStatement(SELECTONE_USERINFO);
                    pstmt.setString(1, userDTO.getUserEmail());

                    rs = pstmt.executeQuery();
                    if (rs.next()) {
                        data = new UserDTO();
                        data.setUserEmail(rs.getString("USER_EMAIL"));
                        data.setUserPassword(rs.getString("USER_PASSWORD"));
                        data.setUserName(rs.getString("USER_NAME"));
                        data.setUserNickname(rs.getString("USER_NICKNAME"));
                        data.setUserPhone(rs.getString("USER_PHONE"));
                        data.setUserRegdate(rs.getDate("USER_REGDATE"));
                        data.setUserGender(rs.getInt("USER_GENDER") == 1);
                        data.setUserBirth(rs.getString("USER_BIRTH"));
                        data.setUserHeight(rs.getInt("USER_HEIGHT"));
                        data.setUserBody(rs.getString("USER_BODY"));
                        data.setUserMbti(rs.getString("USER_MBTI"));
                        data.setUserProfile(rs.getString("USER_PROFILE"));
                        data.setUserEducation(rs.getString("USER_EDUCATION"));
                        data.setUserReligion(rs.getString("USER_RELIGEION"));
                        data.setUserLatitude(rs.getDouble("USER_LATITUDE"));
                        data.setUserLongitude(rs.getDouble("USER_LONGITUDE"));
                        data.setUserDrink(rs.getInt("USER_DRINK"));
                        data.setUserSmoke(rs.getInt("USER_SMOKE") == 1);
                        data.setUserJob(rs.getString("USER_JOB"));
                        data.setUserRole(rs.getInt("USER_ROLE"));
                        data.setUserPreminum(rs.getInt("USER_PREMIUM") == 1);
                        data.setUserToken(rs.getInt("USER_TOKEN"));
                        data.setUserRegion(rs.getString("USER_REGION"));
                        data.setUserDescription(rs.getString("USER_DESCRIPTION"));

                        // social_type 필드 추가
                        try {
                            data.setSocialType(rs.getString("SOCIAL_TYPE"));
                        } catch (Exception e) {
                            // SOCIAL_TYPE 컬럼이 없는 경우 예외 처리
                            data.setSocialType(null);
                        }
                    }
                }
                // 유저 위도, 경도 정보 불러오기
                else if (userDTO.getCondition().equals("SELECTONE_LOCATION")) {
                    pstmt = conn.prepareStatement(SELECTONE_LOCATION);
                    pstmt.setString(1, userDTO.getUserEmail());

                    rs = pstmt.executeQuery();
                    if (rs.next()) {
                        data = new UserDTO();
                        data.setUserEmail(rs.getString("USER_EMAIL"));
                        data.setUserLatitude(rs.getDouble("USER_LATITUDE"));
                        data.setUserLongitude(rs.getDouble("USER_LONGITUDE"));
                    }
                }
                // 알 수 없는 조건인 경우 로그 출력 및 null 반환
                else {
                    System.out.println("알 수 없는 조건: " + userDTO.getCondition());
                    return null;
                }
            }
            // 조건이 null인 경우 로그 출력
            else {
                System.out.println("조건이 null입니다.");
            }
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("selectOne 메소드 예외 발생: " + e.getMessage());
            return null;
        } finally {
            // 리소스 정리
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            JDBCUtil.disconnect(conn, pstmt);
        }
    }



    // 회원가입
    public boolean insert(UserDTO userDTO) {
        try {
            conn = JDBCUtil.connect();
            // 회원가입 정보 전부 다 입력
            if (userDTO.getCondition() != null && userDTO.getCondition().equals("INSERT")) {
                pstmt = conn.prepareStatement(INSERT);
                pstmt.setString(1, userDTO.getUserEmail());
                pstmt.setString(2, userDTO.getUserPassword());
                pstmt.setString(3, userDTO.getUserNickname());
                pstmt.setString(4, userDTO.getUserPhone());
                pstmt.setInt(5, userDTO.getUserGender() ? 1 : 0);
                pstmt.setString(6, userDTO.getUserBirth());
                pstmt.setInt(7, userDTO.getUserHeight());
                pstmt.setString(8, userDTO.getUserBody());
                pstmt.setString(9, userDTO.getUserMbti());
                pstmt.setString(10, userDTO.getUserProfile());
                pstmt.setString(11, userDTO.getUserEducation());
                pstmt.setString(12, userDTO.getUserReligion());
                pstmt.setInt(13, userDTO.getUserDrink());
                pstmt.setInt(14, userDTO.isUserSmoke() ? 1 : 0);
                pstmt.setString(15, userDTO.getUserJob());
                pstmt.setString(16, userDTO.getUserRegion());
                pstmt.setDouble(17, userDTO.getUserLatitude());
                pstmt.setDouble(18, userDTO.getUserLongitude());
                pstmt.setString(19, userDTO.getUserDescription());
                pstmt.setString(20, userDTO.getUserName());
                pstmt.setString(21, userDTO.getSocialType());

            }
            // 나머지 정보와 선택사항에서 프로필사진만 입력한 경우
            else if (userDTO.getCondition() != null && userDTO.getCondition().equals("INSERT_PROFILE")) {
                pstmt = conn.prepareStatement(INSERT_PROFILE);
                pstmt.setString(1, userDTO.getUserEmail());
                pstmt.setString(2, userDTO.getUserPassword());
                pstmt.setString(3, userDTO.getUserNickname());
                pstmt.setString(4, userDTO.getUserPhone());
                pstmt.setInt(5, userDTO.getUserGender() ? 1 : 0);
                pstmt.setString(6, userDTO.getUserBirth());
                pstmt.setInt(7, userDTO.getUserHeight());
                pstmt.setString(8, userDTO.getUserBody());
                pstmt.setString(9, userDTO.getUserMbti());
                pstmt.setString(10, userDTO.getUserProfile());
                pstmt.setString(11, userDTO.getUserEducation());
                pstmt.setString(12, userDTO.getUserReligion());
                pstmt.setInt(13, userDTO.getUserDrink());
                pstmt.setInt(14, userDTO.isUserSmoke() ? 1 : 0);
                pstmt.setString(15, userDTO.getUserJob());
                pstmt.setString(16, userDTO.getUserRegion());
                pstmt.setDouble(17, userDTO.getUserLatitude());
                pstmt.setDouble(18, userDTO.getUserLongitude());
                pstmt.setString(19, userDTO.getUserName());
                pstmt.setString(20, userDTO.getSocialType());
            }
            // 나머지 정보와 선택사항에서 설명만 입력한 경우
            else if (userDTO.getCondition() != null && userDTO.getCondition().equals("INSERT_DESCRIPTION")) {
                pstmt = conn.prepareStatement(INSERT_DESCRIPTION);
                pstmt.setString(1, userDTO.getUserEmail());
                pstmt.setString(2, userDTO.getUserPassword());
                pstmt.setString(3, userDTO.getUserNickname());
                pstmt.setString(4, userDTO.getUserPhone());
                pstmt.setInt(5, userDTO.getUserGender() ? 1 : 0);
                pstmt.setString(6, userDTO.getUserBirth());
                pstmt.setInt(7, userDTO.getUserHeight());
                pstmt.setString(8, userDTO.getUserBody());
                pstmt.setString(9, userDTO.getUserMbti());
                pstmt.setString(10, userDTO.getUserEducation());
                pstmt.setString(11, userDTO.getUserReligion());
                pstmt.setInt(12, userDTO.getUserDrink());
                pstmt.setInt(13, userDTO.isUserSmoke() ? 1 : 0);
                pstmt.setString(14, userDTO.getUserJob());
                pstmt.setString(15, userDTO.getUserRegion());
                pstmt.setDouble(16, userDTO.getUserLatitude());
                pstmt.setDouble(17, userDTO.getUserLongitude());
                pstmt.setString(18, userDTO.getUserDescription());
                pstmt.setString(19, userDTO.getUserName());
                pstmt.setString(20, userDTO.getSocialType());
            }
            int result = pstmt.executeUpdate();
            System.out.println("insert 로그:" + result);
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            JDBCUtil.disconnect(conn, pstmt);
        }
    }

    // 정보변경
    public boolean update(UserDTO userDTO) {
        try {
            conn = JDBCUtil.connect();
            // 회원정보 변경
            if (userDTO.getCondition() != null && userDTO.getCondition().equals("UPDATE")) {
                pstmt = conn.prepareStatement(UPDATE);
                pstmt.setString(1, userDTO.getUserDescription());
                pstmt.setString(2, userDTO.getUserNickname());
                pstmt.setInt(3, userDTO.getUserHeight());
                pstmt.setString(4, userDTO.getUserBody());
                pstmt.setString(5, userDTO.getUserEducation());
                pstmt.setString(6, userDTO.getUserJob());
                pstmt.setString(7, userDTO.getUserReligion());
                pstmt.setString(8, userDTO.getUserRegion());
                pstmt.setDouble(9, userDTO.getUserLatitude());
                pstmt.setDouble(10, userDTO.getUserLongitude());
                pstmt.setString(11, userDTO.getUserMbti());
                pstmt.setInt(12, userDTO.getUserDrink());
                pstmt.setBoolean(13, userDTO.isUserSmoke());
                pstmt.setString(14, userDTO.getUserEmail());
            }
            // ROLE 변경
            else if (userDTO.getCondition() != null && userDTO.getCondition().equals("UPDATE_ROLE")) {
                pstmt = conn.prepareStatement(UPDATE_ROLE);
                pstmt.setInt(1, userDTO.getUserRole());
                pstmt.setString(2, userDTO.getUserEmail());
            }
            // 프로필사진 변경
            else if(userDTO.getCondition() != null && userDTO.getCondition().equals("UPDATE_PROFILE_IMAGE")) {
                pstmt = conn.prepareStatement(UPDATE_PROFILE_IMAGE);
                pstmt.setString(1, userDTO.getUserProfile());
                pstmt.setString(2, userDTO.getUserEmail());
            }
            // 프리미엄 변경
            else if(userDTO.getCondition() != null && userDTO.getCondition().equals("UPDATE_PREMIUM")){
                pstmt = conn.prepareStatement(UPDATE_PREMIUM);
                pstmt.setString(1, userDTO.getUserEmail());
            }
            // 토큰 결제시 토큰 수 변경
            else if(userDTO.getCondition().equals("UPDATE_ADD_TOKEN")){
                pstmt = conn.prepareStatement(UPDATE_ADD_TOKEN);
                pstmt.setInt(1, userDTO.getUserToken());
                pstmt.setString(2, userDTO.getUserEmail());
            }
            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            JDBCUtil.disconnect(conn, pstmt);
        }
    }

    // x
    public boolean delete(UserDTO userDTO) {
        return false;
    }
}
