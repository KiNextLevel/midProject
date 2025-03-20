package model.dao;

import model.common.JDBCUtil;
import model.dto.ParticipantDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ParticipantDAO {
    final String SELECTALL = "SELECT B.* FROM BOARD B JOIN PARTICIPANT P ON B.BOARD_NUM = P.PARTICIPANT_BOARD_NUM WHERE P.PARTICIPANT_USER_EMAIL = ? ORDER BY B.BOARD_NUM DESC";
    final String SELECTONE = "SELECT COUNT(P.PARTICIPANT_USER_EMAIL) FROM PARTICIPANT P JOIN BOARD B ON P.PARTICIPANT_BOARD_NUM = B.BOARD_NUM WHERE B.BOARD_NUM = ?";
    final String INSERT = "INSERT INTO PARTICIPANT (PARTICIPANT_BOARD_NUM, PARTICIPANT_USER_EMAIL) VALUES (?, ?)";
    final String DELETE = "DELETE FROM PARTICIPANT WHERE PARTICIPANT_BOARD_NUM = ? AND PARTICIPANT_USER_EMAIL = ?";
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    // 사용자가 참여한 이벤트 목록
    public ArrayList<ParticipantDTO> selectAll(ParticipantDTO participantDTO){
        ArrayList<ParticipantDTO> list = new ArrayList<>();
        try {
            conn = JDBCUtil.connect();
            pstmt = conn.prepareStatement(SELECTALL);
            pstmt.setString(1, participantDTO.getParticipantUserEmail());
            rs = pstmt.executeQuery();
            while(rs.next()){
                ParticipantDTO dto = new ParticipantDTO();
                dto.setParticipantBoardNumber(rs.getInt("PARTICIPANT_BOARD_NUM"));
                dto.setParticipantUserEmail(rs.getString("PARTICIPANT_USER_EMAIL"));
                list.add(dto);
            }
            return list;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        } finally {
            JDBCUtil.disconnect(conn, pstmt);
        }
    }

    // 현재 참여중인 회원 수(COUNT)
    public ParticipantDTO selectOne(ParticipantDTO participantDTO){
        ParticipantDTO list = null;
        try {
            conn = JDBCUtil.connect();
            pstmt = conn.prepareStatement(SELECTONE);
            pstmt.setInt(1, participantDTO.getParticipantBoardNumber());
            rs = pstmt.executeQuery();
            if(rs.next()){
                list = new ParticipantDTO();
                list.setParticipantBoardNumber(rs.getInt("PARTICIPANT_BOARD_NUM"));
            }
            return list;
        } catch (Exception e){
            e.printStackTrace();
            return null;
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
            pstmt = conn.prepareStatement(DELETE);
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
}