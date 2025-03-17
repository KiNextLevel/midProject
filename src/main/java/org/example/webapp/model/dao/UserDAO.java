package org.example.webapp.model.dao;

import org.example.webapp.model.common.JDBCUtil;
import org.example.webapp.model.dto.UserDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UserDAO {
    final String SELECTONE_CHECK = "SELECT USER_EMAIL FROM USER WHERE USER_EMAIL = ?";
    final String SELECTONE = "SELECT USER_EMAIL, USER_PASSWORD FROM USER WHERE USER_EMAIL = ? AND USER_PASSWORD = ?";

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    // 유저 전체 정보 불러오기
    // 유저 선호 취향 정보 불러오기
    // 참가 중인 이벤트 목록 불러오기
    // 토큰 잔액 정보 불러오기
    // 결제한 상품 목록 불러오기
    public ArrayList<UserDTO> selectAll(UserDTO userDTO){
        return null;
    }

    // 로그인
    public UserDTO selectOne(UserDTO userDTO){
        UserDTO data = null;
        try {
            conn = JDBCUtil.connect();
            // 아이디 중복 검사
            if(userDTO.getCondition() != null && userDTO.getCondition().equals("SELECTONE_CHECK")){
                pstmt = conn.prepareStatement(SELECTONE_CHECK);
                pstmt.setString(1, userDTO.getUSER_EMAIL());
            }
            // 로그인
            else if(userDTO.getCondition() != null && userDTO.getCondition().equals("SELECTONE")){
                pstmt = conn.prepareStatement(SELECTONE);
                pstmt.setString(1, userDTO.getUSER_EMAIL());
                pstmt.setString(2, userDTO.getUSER_PASSWORD());
            }
            rs = pstmt.executeQuery();
            if(rs.next()){
                data = new UserDTO();
                data.setUSER_EMAIL(rs.getString("USER_EMAIL"));
                data.setUSER_PASSWORD(rs.getString("USER_PASSWORD"));
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
    public boolean insert(UserDTO userDTO){
        return false;
    }

    // 정보변경
    public boolean update(UserDTO userDTO){
        return false;
    }

    // 회원탈퇴
    public boolean delete(UserDTO userDTO){
        return false;
    }
}
