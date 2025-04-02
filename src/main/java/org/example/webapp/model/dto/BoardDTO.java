package org.example.webapp.model.dto;

import java.util.Date;

public class BoardDTO {
    // 이벤트 번호
    private int boardNumber;
    // 이벤트 제목
    private String boardTitle;
    // 이벤트 내용
    private String boardContent;
    // 작성 날짜
    private Date boardDate;
    // 최대 참여인원
    private int boardLimit;
    // 다양한 메서드를 위한 컨디션
    private String condition;
    // 검색키워드
    private String searchKeyword;
    // 이벤트 참여자
    private int boardParticipant;
    // 참여자 목록
    private int participant;
    // 사용자 이메일 추가
    private String userEmail;

    public int getBoardNumber() {
        return boardNumber;
    }

    public void setBoardNumber(int boardNumber) {
        this.boardNumber = boardNumber;
    }

    public String getBoardTitle() {
        return boardTitle;
    }

    public void setBoardTitle(String boardTitle) {
        this.boardTitle = boardTitle;
    }

    public String getBoardContent() {
        return boardContent;
    }

    public void setBoardContent(String boardContent) {
        this.boardContent = boardContent;
    }

    public Date getBoardDate() {
        return boardDate;
    }

    public void setBoardDate(Date boardDate) {
        this.boardDate = boardDate;
    }

    public int getBoardLimit() {
        return boardLimit;
    }

    public void setBoardLimit(int boardLimit) {
        this.boardLimit = boardLimit;
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

    public int getBoardParticipant() {
        return boardParticipant;
    }

    public void setBoardParticipant(int boardParticipant) {
        this.boardParticipant = boardParticipant;
    }

    public int getParticipant() {
        return participant;
    }

    public void setParticipant(int participant) {
        this.participant = participant;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public String toString() {
        return "BoardDTO{" +
                "boardNumber=" + boardNumber +
                ", boardTitle='" + boardTitle + '\'' +
                ", boardContent='" + boardContent + '\'' +
                ", boardDate=" + boardDate +
                ", boardLimit=" + boardLimit +
                ", condition='" + condition + '\'' +
                ", searchKeyword='" + searchKeyword + '\'' +
                ", boardParticipant=" + boardParticipant +
                ", participant=" + participant +
                ", userEmail='" + userEmail + '\'' +
                '}';
    }
}
