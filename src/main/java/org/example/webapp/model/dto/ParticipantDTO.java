package org.example.webapp.model.dto;

public class ParticipantDTO {
    // 참가한 유저의 이벤트 번호
    private int  PARTICIPANT_BOARD_NUM;
    // 참가한 유저의 이메일
    private String PARTICIPANT_USER_EMAIL;

    // getter, setter 메서드
    public int getPARTICIPANT_BOARD_NUM() {
        return PARTICIPANT_BOARD_NUM;
    }

    public void setPARTICIPANT_BOARD_NUM(int PARTICIPANT_BOARD_NUM) {
        this.PARTICIPANT_BOARD_NUM = PARTICIPANT_BOARD_NUM;
    }

    public String getPARTICIPANT_USER_EMAIL() {
        return PARTICIPANT_USER_EMAIL;
    }

    public void setPARTICIPANT_USER_EMAIL(String PARTICIPANT_USER_EMAIL) {
        this.PARTICIPANT_USER_EMAIL = PARTICIPANT_USER_EMAIL;
    }

    // toString 메서드
    @Override
    public String toString() {
        return "ParticipantDTO{" +
                "PARTICIPANT_BOARD_NUM=" + PARTICIPANT_BOARD_NUM +
                ", PARTICIPANT_USER_EMAIL='" + PARTICIPANT_USER_EMAIL + '\'' +
                '}';
    }
}
