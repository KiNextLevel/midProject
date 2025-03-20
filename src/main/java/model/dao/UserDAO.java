package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


import model.dto.UserDTO;
import org.example.webapp.model.common.JDBCUtil;

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
    final String SELECTALL_EVENT = "SELECT * FROM PARTICIPANT P LEFT JOIN USER U ON P.PARTICIPANT_USER_EMAIL = U.USER_EMAIL WHERE U.USER_EMAIL = ?";
    // 토큰 잔액 정보 불러오기
    final String SELECTALL_TOKEN = "SELECT USER_TOEKN FROM USER WHERE USER_EMAIL = ?";
    // 결제한 상품 목록 불러오기
    final String SELECTALL_PRODUCT = "SELECT * FROM PAYMENT P LEFT JOIN USER U ON P.PAYMENT_USER_EMAIL = U.USER_EMAIL WHERE U.USER_EMAIL = ?";
    // 블랙리스트 유저 불러오기
    final String SELECTALL_BLACK = "SELECT * FROM USER WHERE USER_ROLE = 2";
    // 회원가입(정보 다 입력)
    final String INSERT = "INSERT INTO USER (USER_EMAIL, USER_PASSWORD, USER_NICKNAME, USER_PHONE, USER_GENDER, USER_BIRTH, USER_HEIGHT, USER_BODY, USER_MBTI," +
            "USER_PROFILE, USER_EDUCATION, USER_RELIGEION, USER_DRINK, USER_SMOKE, USER_JOB, USER_REGION, USER_DESCRIPTION, USER_NAME) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    // 회원가입(소개 뺴고, 프로필만 입력)
    final String INSERT_PROFILE = "INSERT INTO USER (USER_EMAIL, USER_PASSWORD, USER_NICKNAME, USER_PHONE, USER_GENDER, USER_BIRTH, USER_HEIGHT, USER_BODY, USER_MBTI," +
            "USER_PROFILE, USER_EDUCATION, USER_RELIGEION, USER_DRINK, USER_SMOKE, USER_JOB, USER_REGION, USER_NAME) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    // 회원가입(프로필 빼고, 소개만 입력)
    final String INSERT_DESCRIPTION = "INSERT INTO USER (USER_EMAIL, USER_PASSWORD, USER_NICKNAME, USER_PHONE, USER_GENDER, USER_BIRTH, USER_HEIGHT, USER_BODY, USER_MBTI," +
            "USER_EDUCATION, USER_RELIGEION, USER_DRINK, USER_SMOKE, USER_JOB, USER_REGION, USER_DESCRIPTION, USER_NAME) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    // 회원 정보 수정
    final String UPDATE = "UPDATE USER SET USER_NICKNAME = ?, USER_PHONE = ?, USER_GENDER = ?, USER_BIRTH = ?, USER_HEIGHT = ?, USER_BODY = ?, USER_MBTI = ?, " +
            "USER_PROFILE = ?, USER_EDUCATION = ?, USER_RELIGEION = ?, USER_DRINK = ?, USER_SMOKE = ?, USER_JOB = ?, USER_REGION = ?, USER_DESCRIPTION = ?, USER_NAME = ? " +
            "WHERE USER_EMAIL = ?";
    // 회원 ROLE 변경
    final String UPDATE_ROLE = "UPDATE USER SET USER_ROLE = ? WHERE USER_EMAIL = ?";

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
    // 블랙리스트 유저 불러오기
    public ArrayList<UserDTO> selectAll(UserDTO userDTO) {
        ArrayList<UserDTO> datas = new ArrayList<>();
        try {
            conn = JDBCUtil.connect();
            if (userDTO.getCondition() != null && userDTO.getCondition().equals("SELECTALL")) {
                pstmt = conn.prepareStatement(SELCETALL);
                pstmt.setString(1, userDTO.getUserEmail());
            }
            // 유저 선호 취향 정보 불러오기
            else if (userDTO.getCondition() != null && userDTO.getCondition().equals("SELECTALL_FAVORITE")) {
                pstmt = conn.prepareStatement(SELCETALL_FAVORITE);
                pstmt.setString(1, userDTO.getUserEmail());
            }
            // 참가 중인 이벤트 목록 불러오기
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
            else if(userDTO.getCondition() != null && userDTO.getCondition().equals("SELECTALL_BLACK")) {
                pstmt = conn.prepareStatement(SELECTALL_BLACK);
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
    public boolean update(UserDTO userDTO) {
        try {
            conn = JDBCUtil.connect();
            if(userDTO.getCondition() != null && userDTO.getCondition().equals("UPDATE")){
                pstmt = conn.prepareStatement(UPDATE);
                pstmt.setString(1, userDTO.getUserNickname());
                pstmt.setString(2, userDTO.getUserPhone());
                pstmt.setBoolean(3, userDTO.getUserGender());
                pstmt.setString(4, userDTO.getUserBirth());
                pstmt.setInt(5, userDTO.getUserHeight());
                pstmt.setString(6, userDTO.getUserBody());
                pstmt.setString(7, userDTO.getUserMbti());
                pstmt.setString(8, userDTO.getUserProfile());
                pstmt.setString(9, userDTO.getUserEducation());
                pstmt.setString(10, userDTO.getUserReligion());
                pstmt.setInt(11, userDTO.getUserDrink());
                pstmt.setBoolean(12,userDTO.isUserSmoke());
                pstmt.setString(13, userDTO.getUserJob());
                pstmt.setString(14, userDTO.getUserRegion());
                pstmt.setString(15, userDTO.getUserDescription());
                pstmt.setString(16, userDTO.getUserName());
                pstmt.setString(17, userDTO.getUserEmail());
            }
            else if(userDTO.getCondition() != null && userDTO.getCondition().equals("UPDATE_ROLE")){
                pstmt = conn.prepareStatement(UPDATE_ROLE);
                pstmt.setInt(1, userDTO.getUserRole());
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