package org.example.webapp.model.dao;

import org.example.webapp.model.common.JDBCUtil;
import org.example.webapp.model.dto.ReportDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ReportDAO {

    // (관리자용) 00유저 신고자, 신고사유, 신고날짜, 00유저 피신고자, 신고 설명 전체 출력하기
    private final String SELECTALL = "SELECT REPORT_NUM, REPORT_REPORTER, REPORT_REASON, REPORT_DATE, REPORT_REPORTED, REPORT_DESCRIPTION "
            + "FROM REPORT";

    // (유저용) - 마이페이지 상품명, 결제일, 결제 금액
    private final String SELECTONE = "SELECT REPORT_REPORTER FROM REPORT WHERE REPORT_REPORTER = ? AND REPORT_REPORTED = ?";

    // (유저용) 사용자가 또 다른 사용자를 신고하는 쿼리문(신고자, 신고이유, 신고날짜, 피신고자, 신고설명)
    private final String INSERT = "INSERT INTO REPORT (REPORT_REPORTER, REPORT_REASON, REPORT_DATE, REPORT_REPORTED, REPORT_DESCRIPTION) " +
            "VALUES (?, ?, CURRENT_DATE, ?, ?)";


    private final String UPDATE = "";

    // (관리자용) 블랙리스트된 00 유저 삭제하기
    private final String DELETE = "DELETE FROM REPORT WHERE REPORT_REPORTED = ?";
    //(관리자용) 경고 보내면 그 신고건 하나만 삭제하기
    private final String DELETE_ONE = "DELETE FROM REPORT WHERE REPORT_NUM = ?";

    public ArrayList<ReportDTO> selectAll(ReportDTO reportDTO) {
        ArrayList<ReportDTO> datas = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtil.connect();
            pstmt = conn.prepareStatement(SELECTALL);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                ReportDTO data = new ReportDTO();
                data.setReportNumber(rs.getInt("REPORT_NUM"));
                data.setReportReported(rs.getString("REPORT_REPORTED"));
                data.setReportReason(rs.getString("REPORT_REASON"));
                data.setReportDate(rs.getDate("REPORT_DATE"));
                data.setReportReporter(rs.getString("REPORT_REPORTER"));
                data.setReportDescription(rs.getString("REPORT_DESCRIPTION"));
                datas.add(data);
            }
            return datas;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            JDBCUtil.disconnect(conn, pstmt);
        }
    }

    public ReportDTO selectOne(ReportDTO reportDTO) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ReportDTO data = null;
        try {
            conn = JDBCUtil.connect();
            pstmt = conn.prepareStatement(SELECTONE);
            pstmt.setString(1, reportDTO.getReportReporter());
            pstmt.setString(2, reportDTO.getReportReported());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                data = new ReportDTO();
                data.setReportReported(rs.getString("REPORT_REPORTER"));
            }
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return data;
        } finally {
            JDBCUtil.disconnect(conn, pstmt);
        }
    }

    public boolean insert(ReportDTO reportDTO) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = JDBCUtil.connect();
            pstmt = conn.prepareStatement(INSERT);
            pstmt.setString(1, reportDTO.getReportReporter()); // 신고자
            pstmt.setString(2, reportDTO.getReportReason()); // 신고 이유
            pstmt.setString(3, reportDTO.getReportReported()); // 피신고자
            pstmt.setString(4, reportDTO.getReportDescription()); // 신고 상세 설명

            int result = pstmt.executeUpdate();
            return result > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            JDBCUtil.disconnect(conn, pstmt);
        }
    }


    private boolean update(ReportDTO reportDTO) {
        return false;
    }

    public boolean delete(ReportDTO reportDTO) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = JDBCUtil.connect();
            if(reportDTO.getCondition().equals("DELETE")) {
                pstmt = conn.prepareStatement(DELETE);
                pstmt.setString(1, reportDTO.getReportReported());
            }
            if(reportDTO.getCondition().equals("DELETE_ONE")){
                pstmt = conn.prepareStatement(DELETE_ONE);
                pstmt.setInt(1, reportDTO.getReportNumber());
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
}

