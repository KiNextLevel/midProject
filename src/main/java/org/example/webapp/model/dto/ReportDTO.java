package org.example.webapp.model.dto;

import java.util.Date;

public class ReportDTO {
    private int reportNumber; // 신고 번호 (PK)
    private String reportReported; // 피신고자 (FK)
    private String reportReporter; // 신고자 (FK)
    private String reportReason; // 신고 사유
    private String reportDescription; //신고 설명
    private Date reportDate; // 신고 날짜
    private String condition; // 컨디션
    private String searchKeyword; // SearchKeyword

    public int getReportNumber() {
        return reportNumber;
    }

    public void setReportNumber(int reportNumber) {
        this.reportNumber = reportNumber;
    }

    public String getReportReported() {
        return reportReported;
    }

    public void setReportReported(String reportReported) {
        this.reportReported = reportReported;
    }

    public String getReportReporter() {
        return reportReporter;
    }

    public void setReportReporter(String reportReporter) {
        this.reportReporter = reportReporter;
    }

    public String getReportReason() {
        return reportReason;
    }

    public void setReportReason(String reportReason) {
        this.reportReason = reportReason;
    }


    public String getReportDescription() {
        return reportDescription;
    }

    public void setReportDescription(String reportDescription) {
        this.reportDescription = reportDescription;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    @Override
    public String toString() {
        return "ReportDTO [reportNumber=" + reportNumber + ", reportReported=" + reportReported + ", reportReporter="
                + reportReporter + ", reportReason=" + reportReason + ", reportDescription=" + reportDescription
                + ", reportDate=" + reportDate + ", condition=" + condition + ", searchKeyword=" + searchKeyword + "]";
    }

}
