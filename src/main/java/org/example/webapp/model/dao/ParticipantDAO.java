package org.example.webapp.model.dao;

import org.example.webapp.model.common.JDBCUtil;
import org.example.webapp.model.dto.ParticipantDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

// 이벤트 참여 관련 기능
public class ParticipantDAO {
    // 사용자가 참여한 이벤트 목록
    final String SELECTALL = "SELECT B.* , P.PARTICIPANT_USER_EMAIL FROM BOARD B JOIN PARTICIPANT P ON B.BOARD_NUM = P.PARTICIPANT_BOARD_NUM WHERE P.PARTICIPANT_USER_EMAIL = ? ORDER BY B.BOARD_NUM DESC";
    // 유저 마이페이지 - 참가한 이벤트 목록 출력하기
    final String SELECTALL_EVENTPRINT =
            "SELECT B.BOARD_TITLE " +
                    "FROM PARTICIPANT P " +
                    "JOIN BOARD B ON P.PARTICIPANT_BOARD_NUM = B.BOARD_NUM " +
                    "WHERE P.PARTICIPANT_USER_EMAIL = ?";
    // 이벤트에 참여중인 사용자 수
    final String SELECTONE = "SELECT COUNT(P.PARTICIPANT_USER_EMAIL) FROM PARTICIPANT P JOIN BOARD B ON P.PARTICIPANT_BOARD_NUM = B.BOARD_NUM WHERE B.BOARD_NUM = ?";
    // 이벤트 참여하기
    final String INSERT = "INSERT INTO PARTICIPANT (PARTICIPANT_BOARD_NUM, PARTICIPANT_USER_EMAIL) VALUES (?, ?)";
    // 참여한 이벤트 취소하기
    final String DELETE = "DELETE FROM PARTICIPANT WHERE PARTICIPANT_BOARD_NUM = ? AND PARTICIPANT_USER_EMAIL = ?";
    // 이벤트 삭제
    final String DELETE_BOARD_NUM = "DELETE FROM PARTICIPANT WHERE PARTICIPANT_BOARD_NUM = ?";

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    // 사용자가 참여한 이벤트 목록
    public ArrayList<ParticipantDTO> selectAll(ParticipantDTO participantDTO) {
        ArrayList<ParticipantDTO> list = new ArrayList<>();
        try {
            conn = JDBCUtil.connect();

            if (participantDTO.getCondition().equals("SELECTALL")) {
                pstmt = conn.prepareStatement(SELECTALL);
                pstmt.setString(1, participantDTO.getParticipantUserEmail());
            } else if (participantDTO.getCondition().equals("SELECTALL_EVENTPRINT")) {
                pstmt = conn.prepareStatement(SELECTALL_EVENTPRINT);
                pstmt.setString(1, participantDTO.getParticipantUserEmail());
            }
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ParticipantDTO dto = new ParticipantDTO();
                if ("SELECTALL".equals(participantDTO.getCondition())) {
                    dto.setParticipantBoardNumber(rs.getInt("BOARD_NUM"));
                    dto.setParticipantUserEmail(rs.getString("PARTICIPANT_USER_EMAIL"));
                }   if ("SELECTALL_EVENTPRINT".equals(participantDTO.getCondition())) {
                        dto.setBoardTitle(rs.getString("BOARD_TITLE"));
                    }
                list.add(dto);
                }

                return list;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            } finally {
                JDBCUtil.disconnect(conn, pstmt);
            }
        }

    // 현재 참여중인 회원 수(COUNT)
    public ParticipantDTO selectOne(ParticipantDTO participantDTO){
        ParticipantDTO list = new ParticipantDTO(); // null이 아닌 새 객체 생성
        try {
            conn = JDBCUtil.connect();
            pstmt = conn.prepareStatement(SELECTONE);
            pstmt.setInt(1, participantDTO.getParticipantBoardNumber());
            rs = pstmt.executeQuery();
            if(rs.next()){
                // COUNT 결과를 ParticipantBoardNumber에 저장, 참가한 USER_EMAIL도 저장
                list.setParticipantBoardNumber(rs.getInt(1));
            } else {
                // 결과가 없으면 0으로 설정
                list.setParticipantBoardNumber(0);
            }
            return list;
        } catch (Exception e){
            e.printStackTrace();
            // 오류 발생 시에도 기본값을 가진 객체 반환
            ParticipantDTO errorDto = new ParticipantDTO();
            errorDto.setParticipantBoardNumber(0);
            return errorDto;
        } finally {
            JDBCUtil.disconnect(conn, pstmt);
        }
    }

    // 참가하기
    public boolean insert(ParticipantDTO participantDTO){
        ParticipantDAO list = null;
        try {
            conn = JDBCUtil.connect();
            pstmt = conn.prepareStatement(INSERT);
            pstmt.setInt(1, participantDTO.getParticipantBoardNumber());
            pstmt.setString(2, participantDTO.getParticipantUserEmail());
            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        } finally {
            JDBCUtil.disconnect(conn, pstmt);
        }
    }

    // x
    public boolean update(ParticipantDTO participantDTO){
        return false;
    }

    // 참가 취소
    public boolean delete(ParticipantDTO participantDTO){
        try {
            conn = JDBCUtil.connect();
            //이벤트 삭제했을 때
            if(participantDTO.getCondition().equals("DELETE_BOARD_NUM")) {
                pstmt = conn.prepareStatement(DELETE_BOARD_NUM);
                pstmt.setInt(1, participantDTO.getParticipantBoardNumber());
            }
            //참가 취소했을 때
            if(participantDTO.getCondition().equals("DELETE")){
                pstmt = conn.prepareStatement(DELETE);
                pstmt.setInt(1, participantDTO.getParticipantBoardNumber());
                pstmt.setString(2, participantDTO.getParticipantUserEmail());
            }
            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        } finally {
            JDBCUtil.disconnect(conn, pstmt);
        }
    }
}
