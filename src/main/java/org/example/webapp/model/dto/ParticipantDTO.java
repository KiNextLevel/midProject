package org.example.webapp.model.dto;

public class ParticipantDTO {
    // 참가한 유저의 이벤트 번호
    private int participantBoardNum;
    // 참가한 유저의 이메일
    private String participantUserEmail;

    // getter, setter 메서드
    public int getParticipantBoardNum() {
        return participantBoardNum;
    }

    public void setParticipantBoardNum(int participantBoardNum) {
        this.participantBoardNum = participantBoardNum;
    }

    public String getParticipantUserEmail() {
        return participantUserEmail;
    }

    public void setParticipantUserEmail(String participantUserEmail) {
        this.participantUserEmail = participantUserEmail;
    }

    // toString 메서드
    @Override
    public String toString() {
        return "ParticipantDTO{" +
                "PARTICIPANT_BOARD_NUM=" + participantBoardNum +
                ", PARTICIPANT_USER_EMAIL='" + participantUserEmail + '\'' +
                '}';
    }
}
