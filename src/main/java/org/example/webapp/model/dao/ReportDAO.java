package org.example.webapp.model.dao;

import org.example.webapp.model.dto.ReportDTO;

import java.util.ArrayList;

public class ReportDAO {
    final String SELECTONE = "";
    // (관리자용) 00유저 신고자, 신고사유, 신고날짜, 00유저 피신고자, 신고 설명 전체 출력하기
    final String SELECTALL = "SELECT REPORT_REPORTED, REPORT_REASON, REPORT_DATE, REPORT_REPORTER, REPORT_DESCRIPTION "
            + "FROM REPORT";

    // (유저용) 사용자가 또 다른 사용자를 신고하는 쿼리문(신고자, 신고이유, 신고날짜, 피신고자, 신고설명)
    final String INSERT = "INSERT INTO REPORT"
            + "(REPORT_REPORTED, REPORT_REASON, REPORT_DATE, REPORT_REPORTER, REPORT_DESCRIPTION) "
            + "VALUES (?, ?, CURRENT_DATE, ?, ?)";


    final String UPDATE = "";
    final String DELETE = "";

    public ArrayList<ReportDTO> selectAll(ReportDTO reportDTO) {
        return null;

    }

    public ReportDTO selectOne(ReportDTO reportDTO) {
        return null;

    }

    public boolean insert(ReportDTO reportDTO) {
        return false;

    }

    public boolean update(ReportDTO reportDTO) {
        return false;
    }

    public boolean delete(ReportDTO reportDTO) {
        return false;
    }

}
