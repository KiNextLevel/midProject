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

    // getter, setter 메서드
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

    // toString 메서드
    @Override
    public String toString() {
        return "BoardDTO{" +
                "BOARD_NUM=" + boardNumber +
                ", BOARD_TITLE='" + boardTitle + '\'' +
                ", BOARD_CONTENT='" + boardContent + '\'' +
                ", BOARD_DATE=" + boardDate +
                ", BOARD_LIMIT=" + boardLimit +
                '}';
    }
}
