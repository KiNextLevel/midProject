package model.dto;

public class ParticipantDTO {
    // 참가한 유저의 이벤트 번호
    private int participantBoardNumber;
    // 참가한 유저의 이메일
    private String participantUserEmail;

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

    // toString 메서드
    @Override
    public String toString() {
        return "ParticipantDTO{" +
                "PARTICIPANT_BOARD_NUM=" + participantBoardNumber +
                ", PARTICIPANT_USER_EMAIL='" + participantUserEmail + '\'' +
                '}';
    }
}