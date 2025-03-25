package org.example.webapp.model.dao;

import org.example.webapp.model.common.JDBCUtil;
import org.example.webapp.model.dto.AlertDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AlertDAO {
    // 00유저의 알림내용, 알림 날짜, 알림여부 불러오기
    // 사용자의 알림이 여러개가 있을 수 있으니까 SELECTALL
    final String SELECTALL = "SELECT ALERT_CONTENT, ALERT_DATE, ALERT_ISWATCH FROM ALERT WHERE ALERT_USER_EMAIL = ?";
    final String SELECTONE = ""; // 기능 없음
    final String INSERT = ""; // 기능 필요 관리자가 유저한테 알림 보내기
    final String UPDATE = ""; // 기능 없음
    final String DELETE = ""; // 기능 없음

    public ArrayList<AlertDTO> selectAll(AlertDTO alertDTO) {
        ArrayList<AlertDTO> datas = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.connect();
            pstmt = conn.prepareStatement(SELECTALL);
            pstmt.setString(1, alertDTO.getUserEmail());
            rs = pstmt.executeQuery();
            System.out.println("SELECT ALERT_CONTENT");
            while (rs.next()) {
                AlertDTO data = new AlertDTO();
                data.setAlertContent(rs.getString("ALERT_CONTENT"));
                data.setAlertDate(rs.getDate("ALERT_DATE"));
                data.setAlertIsWatch(rs.getBoolean("ALERT_ISWATCH"));
                System.out.println(data);
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

    public AlertDTO selectOne(AlertDTO alertDTO) {
        return null;

    }

    public boolean insert(AlertDTO alertDTO) {
        return false;

    }

    public boolean update(AlertDTO alertDTO) {
        return false;
    }

    public boolean delete(AlertDTO alertDTO) {
        return false;
    }
}
