package org.example.webapp.model.dao;

import org.example.webapp.model.common.JDBCUtil;
import org.example.webapp.model.dto.UserDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UserDAO {
    // 아이디 중복 검사
    final String SELECTONE_CHECK = "SELECT USER_EMAIL FROM USER WHERE USER_EMAIL = ?";
    // 로그인
    final String SELECTONE = "SELECT USER_EMAIL, USER_PASSWORD FROM USER WHERE USER_EMAIL = ? AND USER_PASSWORD = ?";
    // 유저 전체 정보 불러오기
    final String SELCETALL = "SELECT * FROM USER WHERE USER_EMAIL = ?";
    // 유저 선호 취향 정보 불러오기
    final String SELCETALL_FAVORITE = "SELECT * FROM PREFERENCE P LEFT JOIN USER U ON P.PREFERENCE_USER_EMAIL = U.USER_EMAIL WHERE U.USER_EMAIL = ?";
    // 참가 중인 이벤트 목록 불러오기
    final String SELCETALL_EVENT = "SELECT * FROM PARTICIPANT P LEFT JOIN USER U ON P.PARTICIPANT_USER_EMAIL = U.USER_EMAIL WHERE U.USER_EMAIL = ?";
    // 토큰 잔액 정보 불러오기
    final String SELCETALL_TOKEN = "SELECT USER_TOEKN FROM USER WHERE USER_EMAIL = ?";
    // 결제한 상품 목록 불러오기
    final String SELCETALL_PRODUCT = "SELECT * FROM PAYMENT P LEFT JOIN USER U ON P.PAYMENT_USER_EMAIL = U.USER_EMAIL WHERE U.USER_EMAIL = ?";
    // 회원가입(정보 다 입력)
    final String INSERT = "INSERT INTO USER (USER_EMAIL, USER_PASSWORD, USER_NICKNAME, USER_PHONE, USER_GENDER, USER_BIRTH, USER_HEIGHT, USER_BODY, USER_MBTI," +
            "USER_PROFILE, USER_EDUCATION, USER_RELIGEION, USER_DRINK, USER_SMOKE, USER_JOB, USER_REGION, USER_DESCRIPTION, USER_NAME) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    // 회원가입(소개 뺴고, 프로필만 입력)
    final String INSERT_PROFILE = "INSERT INTO USER (USER_EMAIL, USER_PASSWORD, USER_NICKNAME, USER_PHONE, USER_GENDER, USER_BIRTH, USER_HEIGHT, USER_BODY, USER_MBTI," +
            "USER_PROFILE, USER_EDUCATION, USER_RELIGEION, USER_DRINK, USER_SMOKE, USER_JOB, USER_REGION, USER_NAME) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    // 회원가입(프로필 빼고, 소개만 입력)
    final String INSERT_DESCRIPTION = "INSERT INTO USER (USER_EMAIL, USER_PASSWORD, USER_NICKNAME, USER_PHONE, USER_GENDER, USER_BIRTH, USER_HEIGHT, USER_BODY, USER_MBTI," +
            "USER_EDUCATION, USER_RELIGEION, USER_DRINK, USER_SMOKE, USER_JOB, USER_REGION, USER_DESCRIPTION, USER_NAME) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    // 회원 정보 수정(효율성을 위해 쿼리문은 메서드 내에 사용)

    // 회원 탈퇴
    final String DELETE = "DELETE FROM USER WHERE USER_EMAIL = ?";

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    // 유저 전체 정보 불러오기
    // 유저 선호 취향 정보 불러오기
    // 참가 중인 이벤트 목록 불러오기
    // 토큰 잔액 정보 불러오기
    // 결제한 상품 목록 불러오기
    public ArrayList<UserDTO> selectAll(UserDTO userDTO) {
        ArrayList<UserDTO> datas = new ArrayList<>();
        try {
            conn = JDBCUtil.connect();
            if (userDTO.getCondition() != null && userDTO.getCondition().equals("SELCETALL")) {
                pstmt = conn.prepareStatement(SELCETALL);
                pstmt.setString(1, userDTO.getUserEmail());
            }
            // 유저 선호 취향 정보 불러오기
            else if (userDTO.getCondition() != null && userDTO.getCondition().equals("SELCETALL_FAVORITE")) {
                pstmt = conn.prepareStatement(SELCETALL_FAVORITE);
                pstmt.setString(1, userDTO.getUserEmail());
            }
            // 참가 중인 이벤트 목록 불러오기
            else if (userDTO.getCondition() != null && userDTO.getCondition().equals("SELCETALL_EVENT")) {
                pstmt = conn.prepareStatement(SELCETALL_EVENT);
                pstmt.setString(1, userDTO.getUserEmail());
            }
            // 토큰 잔액 정보 불러오기
            else if (userDTO.getCondition() != null && userDTO.getCondition().equals("SELCETALL_TOKEN")) {
                pstmt = conn.prepareStatement(SELCETALL_TOKEN);
                pstmt.setString(1, userDTO.getUserEmail());
            }
            // 결제한 상품 목록 불러오기
            else if (userDTO.getCondition() != null && userDTO.getCondition().equals("SELCETALL_PRODUCT")) {
                pstmt = conn.prepareStatement(SELCETALL_PRODUCT);
                pstmt.setString(1, userDTO.getUserEmail());
            }
            rs = pstmt.executeQuery();
            while (rs.next()) {
                UserDTO data = new UserDTO();
                data.setUserEmail(rs.getString("USER_EMAIL"));
                data.setUserPassword(rs.getString("USER_PASSWORD"));
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
            // 아이디 중복 검사
            if (userDTO.getCondition() != null && userDTO.getCondition().equals("SELECTONE_CHECK")) {
                pstmt = conn.prepareStatement(SELECTONE_CHECK);
                pstmt.setString(1, userDTO.getUserEmail());
            }
            // 로그인
            else if (userDTO.getCondition() != null && userDTO.getCondition().equals("SELECTONE")) {
                pstmt = conn.prepareStatement(SELECTONE);
                pstmt.setString(1, userDTO.getUserEmail());
                pstmt.setString(2, userDTO.getUserPassword());
            }
            rs = pstmt.executeQuery();
            if (rs.next()) {
                data = new UserDTO();
                data.setUserEmail(rs.getString("USER_EMAIL"));
                data.setUserPassword(rs.getString("USER_PASSWORD"));
            }
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
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
                pstmt.setString(17, userDTO.getUserDescription());
                pstmt.setString(18, userDTO.getUserName());
            } else if (userDTO.getCondition() != null && userDTO.getCondition().equals("INSERT_PROFILE")) {
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
                pstmt.setString(17, userDTO.getUserName());
            } else if (userDTO.getCondition() != null && userDTO.getCondition().equals("INSERT_DESCRIPTION")) {
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
                pstmt.setString(16, userDTO.getUserDescription());
                pstmt.setString(17, userDTO.getUserName());
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
    // 정보변경
    public boolean update(UserDTO userDTO) {
        try {
            conn = JDBCUtil.connect();

            StringBuilder queryBuilder = new StringBuilder("UPDATE USER SET ");
            ArrayList<Object> parameters = new ArrayList<>();
            boolean isFirstParam = true;

            // 비밀번호
            if (userDTO.getUserPassword() != null) {
                queryBuilder.append(isFirstParam ? "" : ", ").append("USER_PASSWORD=?");
                parameters.add(userDTO.getUserPassword());
                isFirstParam = false;
            }

            // 닉네임
            if (userDTO.getUserNickname() != null) {
                queryBuilder.append(isFirstParam ? "" : ", ").append("USER_NICKNAME=?");
                parameters.add(userDTO.getUserNickname());
                isFirstParam = false;
            }

            // 전화번호
            if (userDTO.getUserPhone() != null) {
                queryBuilder.append(isFirstParam ? "" : ", ").append("USER_PHONE=?");
                parameters.add(userDTO.getUserPhone());
                isFirstParam = false;
            }

            // 성별 (플래그 변수 사용)
            if (userDTO.isUserGenderChanged()) {
                queryBuilder.append(isFirstParam ? "" : ", ").append("USER_GENDER=?");
                parameters.add(userDTO.getUserGender());
                isFirstParam = false;
            }

            // 생년월일
            if (userDTO.getUserBirth() != null) {
                queryBuilder.append(isFirstParam ? "" : ", ").append("USER_BIRTH=?");
                parameters.add(userDTO.getUserBirth());
                isFirstParam = false;
            }

            // 키
            if (userDTO.getUserHeight() != 0) {
                queryBuilder.append(isFirstParam ? "" : ", ").append("USER_HEIGHT=?");
                parameters.add(userDTO.getUserHeight());
                isFirstParam = false;
            }

            // 체형
            if (userDTO.getUserBody() != null) {
                queryBuilder.append(isFirstParam ? "" : ", ").append("USER_BODY=?");
                parameters.add(userDTO.getUserBody());
                isFirstParam = false;
            }

            // MBTI
            if (userDTO.getUserMbti() != null) {
                queryBuilder.append(isFirstParam ? "" : ", ").append("USER_MBTI=?");
                parameters.add(userDTO.getUserMbti());
                isFirstParam = false;
            }

            // 프로필 사진
            if (userDTO.getUserProfile() != null) {
                queryBuilder.append(isFirstParam ? "" : ", ").append("USER_PROFILE=?");
                parameters.add(userDTO.getUserProfile());
                isFirstParam = false;
            }

            // 학력
            if (userDTO.getUserEducation() != null) {
                queryBuilder.append(isFirstParam ? "" : ", ").append("USER_EDUCATION=?");
                parameters.add(userDTO.getUserEducation());
                isFirstParam = false;
            }

            // 종교
            if (userDTO.getUserReligion() != null) {
                queryBuilder.append(isFirstParam ? "" : ", ").append("USER_RELIGEION=?");
                parameters.add(userDTO.getUserReligion());
                isFirstParam = false;
            }

            // 음주
            if (userDTO.getUserDrink() != 0) {
                queryBuilder.append(isFirstParam ? "" : ", ").append("USER_DRINK=?");
                parameters.add(userDTO.getUserDrink());
                isFirstParam = false;
            }

            // 흡연 상태 업데이트
            if (userDTO.isUserSmokeChanged()) {
                queryBuilder.append(isFirstParam ? "" : ", ").append("USER_SMOKE=?");
                parameters.add(userDTO.isUserSmoke() ? 1 : 0);  // boolean을 int로 변환
                isFirstParam = false;
            }

            // 직업
            if (userDTO.getUserJob() != null) {
                queryBuilder.append(isFirstParam ? "" : ", ").append("USER_JOB=?");
                parameters.add(userDTO.getUserJob());
                isFirstParam = false;
            }

            // 지역
            if (userDTO.getUserRegion() != null) {
                queryBuilder.append(isFirstParam ? "" : ", ").append("USER_REGION=?");
                parameters.add(userDTO.getUserRegion());
                isFirstParam = false;
            }

            // 자기소개
            if (userDTO.getUserDescription() != null) {
                queryBuilder.append(isFirstParam ? "" : ", ").append("USER_DESCRIPTION=?");
                parameters.add(userDTO.getUserDescription());
                isFirstParam = false;
            }

            // 이름
            if (userDTO.getUserName() != null) {
                queryBuilder.append(isFirstParam ? "" : ", ").append("USER_NAME=?");
                parameters.add(userDTO.getUserName());
                isFirstParam = false;
            }

            // 변경할 내용이 없으면 true 반환
            if (isFirstParam) {
                return true;
            }

            // WHERE 조건 추가
            queryBuilder.append(" WHERE USER_EMAIL=?");
            parameters.add(userDTO.getUserEmail());

            pstmt = conn.prepareStatement(queryBuilder.toString());

            // 파라미터 설정
            for (int i = 0; i < parameters.size(); i++) {
                pstmt.setObject(i + 1, parameters.get(i));
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

    // 회원탈퇴
    public boolean delete(UserDTO userDTO) {
        try {
            conn = JDBCUtil.connect();
            pstmt = conn.prepareStatement(DELETE);
            pstmt.setString(1, userDTO.getUserEmail());
            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            JDBCUtil.disconnect(conn, pstmt);
        }
    }
}
