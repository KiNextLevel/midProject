package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.example.webapp.model.common.JDBCUtil;

import model.dto.BoardDTO;

public class BoardDAO {
    private String SELECTALL = "SELECT * FROM BOARD";
    private String SELECTONE = "SELECT * FROM BOARD WHERE BOARD_NUM = ?";
    private String INSERT = "INSERT INTO BOARD (BOARD_TITLE, BOARD_CONTENT, BOARD_LIMIT) VALUES (?, ?, ?)";
    private String UPDATE_TITLE = "UPDATE BOARD SET BOARD_TITLE = ? WHERE BOARD_NUM = ?";
    private String UPDATE_CONTENT = "UPDATE BOARD SET BOARD_CONTENT = ? WHERE BOARD_NUM = ?";
    private String UPDATE_LIMIT = "UPDATE BOARD SET BOARD_LIMIT = ? WHERE BOARD_NUM = ?";
    private String DELETE = "DELETE FROM BOARD WHERE BOARD_NUM = ?";

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    // 이벤트 내용 전부 다 불러오기
    public ArrayList<BoardDTO> selectAll(BoardDTO boardDTO){
        ArrayList<BoardDTO> datas = new ArrayList<>();
        try {
            conn = JDBCUtil.connect();
            if (conn != null) {	//연결 확인
                System.out.println("DB 연결 성공!");
            } else {
                System.out.println("DB 연결 실패!");
            }
            pstmt = conn.prepareStatement(SELECTALL);
            rs = pstmt.executeQuery();
            while(rs.next()){
                BoardDTO data = new BoardDTO();
                data.setBoardNumber(rs.getInt("BOARD_NUMBER"));  //이거 추가했습니다
                data.setBoardTitle(rs.getString("BOARD_TITLE"));
                data.setBoardContent(rs.getString("BOARD_CONTENT"));
                data.setBoardLimit(rs.getInt("BOARD_LIMIT"));
                datas.add(data);
            }
            return datas;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        } finally {
            JDBCUtil.disconnect(conn, pstmt);
        }
    }

    // 이벤트 게시판 하나 들어가기
    public BoardDTO selectOne(BoardDTO boardDTO){
        BoardDTO datas = null;  // BoardDAO -> BoardDTO로 변경
        try {
            conn = JDBCUtil.connect();
            pstmt = conn.prepareStatement(SELECTONE);
            pstmt.setInt(1, boardDTO.getBoardNumber());
            rs = pstmt.executeQuery();
            if(rs.next()){
                datas = new BoardDTO();  // 이제 올바른 타입으로 객체 생성
                datas.setBoardTitle(rs.getString("BOARD_TITLE"));
                datas.setBoardContent(rs.getString("BOARD_CONTENT"));
                datas.setBoardLimit(rs.getInt("BOARD_LIMIT"));
            }
            return datas;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        } finally {
            JDBCUtil.disconnect(conn, pstmt);
        }
    }

    // 이벤트 게시판 글 작성
    public boolean insert(BoardDTO boardDTO){
        try {
            conn = JDBCUtil.connect();
            pstmt = conn.prepareStatement(INSERT);
            pstmt.setString(1, boardDTO.getBoardTitle());
            pstmt.setString(2, boardDTO.getBoardContent());
            pstmt.setInt(3, boardDTO.getBoardLimit());
            int result = pstmt.executeUpdate();
            if(result > 0){
                return true;
            }
            return false;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        } finally {
            JDBCUtil.disconnect(conn, pstmt);
        }
    }


    // 이벤트 게시판 글 수정
    public boolean update(BoardDTO boardDTO){
        try {
            conn = JDBCUtil.connect();

            // 제목 수정이 필요한 경우
            if(boardDTO.getBoardTitle() != null && !boardDTO.getBoardTitle().equals("UPDATE_TITLE")){
                pstmt = conn.prepareStatement(UPDATE_TITLE);
                pstmt.setString(1, boardDTO.getBoardTitle());
                pstmt.setInt(2, boardDTO.getBoardNumber());
                pstmt.executeUpdate();
                pstmt.close();
            }

            // 내용 수정이 필요한 경우
            if(boardDTO.getBoardContent() != null && !boardDTO.getBoardContent().equals("UPDATE_CONTENT")){
                pstmt = conn.prepareStatement(UPDATE_CONTENT);
                pstmt.setString(1, boardDTO.getBoardContent());
                pstmt.setInt(2, boardDTO.getBoardNumber());
                pstmt.executeUpdate();
                pstmt.close();
            }

            // 제한 수정이 필요한 경우
            if(boardDTO.getBoardLimit() != 0 && boardDTO.getBoardLimit() != boardDTO.getBoardLimit()){
                pstmt = conn.prepareStatement(UPDATE_LIMIT);
                pstmt.setInt(1, boardDTO.getBoardLimit());
                pstmt.setInt(2, boardDTO.getBoardNumber());
                pstmt.executeUpdate();
                pstmt.close();
            }
            return true; // 수정 성공 (수정할 내용이 없어도 성공으로 간주)
        } catch (Exception e){
            e.printStackTrace();
            return false;
        } finally {
            JDBCUtil.disconnect(conn, pstmt);
        }
    }

    // 이벤트 게시판 글 삭제
    public boolean delete(BoardDTO boardDTO){
        try {
            conn = JDBCUtil.connect();
            pstmt = conn.prepareStatement(DELETE);
            pstmt.setInt(1, boardDTO.getBoardNumber());

            int result = pstmt.executeUpdate();

            if(result > 0){
                return true;
            }
            return false;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        } finally {
            JDBCUtil.disconnect(conn, pstmt);
        }
    }

}