package org.example.webapp.model.dto;

public class ParticipantDTO {
    // 참가한 유저의 이벤트 번호
    private int participantBoardNumber;
    // 참가한 유저의 이메일
    private String participantUserEmail;
    private String boardTitle;
    private String condition; // 컨디션
    private String searchKeyword; // SearchKeyword

    // getter, setter 메서드
    public int getParticipantBoardNumber() {
        return participantBoardNumber;
    }

    public void setParticipantBoardNumber(int participantBoardNumber) {
        this.participantBoardNumber = participantBoardNumber;
    }

    public String getParticipantUserEmail() {
        return participantUserEmail;
    }

    public void setParticipantUserEmail(String participantUserEmail) {
        this.participantUserEmail = participantUserEmail;
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

    public String getBoardTitle() {
        return boardTitle;
    }

    public void setBoardTitle(String boardTitle) {
        this.boardTitle = boardTitle;
    }

// toString 메서드


    @Override
    public String toString() {
        return "ParticipantDTO{" +
                "participantBoardNumber=" + participantBoardNumber +
                ", participantUserEmail='" + participantUserEmail + '\'' +
                ", boardTitle='" + boardTitle + '\'' +
                ", condition='" + condition + '\'' +
                ", searchKeyword='" + searchKeyword + '\'' +
                '}';
    }
}
