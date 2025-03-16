package org.example.webapp.model.dto;

import java.util.Date;

public class BoardDTO {
    // 이벤트 번호
    private int BOARD_NUM;
    // 이벤트 제목
    private String BOARD_TITLE;
    // 이벤트 내용
    private String BOARD_CONTENT;
    // 작성 날짜
    private Date BOARD_DATE;
    // 최대 참여인원
    private int BOARD_LIMIT;

    // getter, setter 메서드
    public int getBOARD_NUM() {
        return BOARD_NUM;
    }

    public void setBOARD_NUM(int BOARD_NUM) {
        this.BOARD_NUM = BOARD_NUM;
    }

    public String getBOARD_TITLE() {
        return BOARD_TITLE;
    }

    public void setBOARD_TITLE(String BOARD_TITLE) {
        this.BOARD_TITLE = BOARD_TITLE;
    }

    public String getBOARD_CONTENT() {
        return BOARD_CONTENT;
    }

    public void setBOARD_CONTENT(String BOARD_CONTENT) {
        this.BOARD_CONTENT = BOARD_CONTENT;
    }

    public Date getBOARD_DATE() {
        return BOARD_DATE;
    }

    public void setBOARD_DATE(Date BOARD_DATE) {
        this.BOARD_DATE = BOARD_DATE;
    }

    public int getBOARD_LIMIT() {
        return BOARD_LIMIT;
    }

    public void setBOARD_LIMIT(int BOARD_LIMIT) {
        this.BOARD_LIMIT = BOARD_LIMIT;
    }

    // toString 메서드
    @Override
    public String toString() {
        return "BoardDTO{" +
                "BOARD_NUM=" + BOARD_NUM +
                ", BOARD_TITLE='" + BOARD_TITLE + '\'' +
                ", BOARD_CONTENT='" + BOARD_CONTENT + '\'' +
                ", BOARD_DATE=" + BOARD_DATE +
                ", BOARD_LIMIT=" + BOARD_LIMIT +
                '}';
    }
}
