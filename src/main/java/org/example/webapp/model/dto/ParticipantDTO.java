package org.example.webapp.model.dto;

public class ParticipantDTO {
    // 참가한 유저의 이벤트 번호
    private int participantBoardNumber;
    // 참가한 유저의 이메일
    private String participantUserEmail;
    private String condition;

    // getter, setter 메서드

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

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

    // toString 메서드
    @Override
    public String toString() {
        return "ParticipantDTO{" +
                "participantBoardNumber=" + participantBoardNumber +
                ", participantUserEmail='" + participantUserEmail + '\'' +
                ", condition='" + condition + '\'' +
                '}';
    }
}
