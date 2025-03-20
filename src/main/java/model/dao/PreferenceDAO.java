package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.common.JDBCUtil;
import model.dto.PreferenceDTO;

public class PreferenceDAO {

	// 사용자의 선호키, 체형, 나이 전체 출력해줘
	final String SELECTALL = "SELECT PREFERENCE_HEIGHT, PREFERENCE_BODY, PREFERENCE_AGE" + "FROM PREFERENCE";

	// 00 유저의 선호 키, 체형, 나이 출력해줘
	final String SELECTONE = "SELECT PREFERENCE_HEIGHT, PREFERENCE_BODY, PREFERENCE_AGE" + "FROM PREFERENCE"
			+ "WHERE PREFERENCE_USER_EMAIL = ?";

	// 쿼리야, 선호키, 체형, 나이 추가해줘(이메일 필수)
	final String INSERT = "INSERT INTO PREFERENCE (PREFERENCE_USER_EMAIL, PREFERENCE_HEIGHT, PREFERENCE_BODY, PREFERENCE_AGE) VALUES (?, ?, ?, ?)";

	// 쿼리야, 00 유저의 선호키, 체형, 나이 수정해줘
	final String UPDATE = "UPDATE PREFERENCE SET PREFERENCE_HEIGHT = ?, PREFERENCE_BODY = ?, PREFERENCE_AGE = ? WHERE PREFERENCE_USER_EMAIL = ?";

	final String DELETE = "";

	public ArrayList<PreferenceDTO> selectAll(PreferenceDTO preferenceDTO) {
		ArrayList<PreferenceDTO> datas = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = JDBCUtil.connect();
			pstmt = conn.prepareStatement(SELECTALL);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				PreferenceDTO data = new PreferenceDTO();
				data.setPreferenceHeight(rs.getInt("PREFERENCE_HEIGHT"));
				data.setPreferenceBody(rs.getString("PREFERENCE_BODY"));
				data.setPreferenceAge(rs.getString("PREFERENCE_AGE"));
				datas.add(data);
			}

			return datas;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			JDBCUtil.disconnect(conn, pstmt);
		}
	}

	public PreferenceDTO selectOne(PreferenceDTO preferenceDTO) {
		PreferenceDTO data = null;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = JDBCUtil.connect();
			pstmt = conn.prepareStatement(SELECTONE);
			pstmt.setString(1, preferenceDTO.getUserEmail());
			
			ResultSet rs = pstmt.executeQuery();// set결과 =쿼리실행
			if (rs.next()) {
				data = new PreferenceDTO();
				data.setPreferenceHeight(rs.getInt("PREFERENCE_HEIGHT"));
				data.setPreferenceBody(rs.getString("PREFERENCE_BODY"));
				data.setPreferenceAge(rs.getString("PREFERENCE_AGE"));
				
			 }
	      }catch(Exception e) {
	         e.printStackTrace();
	      }finally {
	         JDBCUtil.disconnect(conn, pstmt);
	      }
	      return data;
	   }
				


	public boolean insert(PreferenceDTO preferenceDTO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = JDBCUtil.connect();

			pstmt = conn.prepareStatement(INSERT);
			pstmt.setString(1, preferenceDTO.getUserEmail());
			pstmt.setInt(2, preferenceDTO.getPreferenceHeight());
			pstmt.setString(3, preferenceDTO.getPreferenceBody());
			pstmt.setString(4, preferenceDTO.getPreferenceAge());
			pstmt.executeUpdate();

			return true; // 수정 성공 (수정할 내용이 없어도 성공으로 간주)
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			JDBCUtil.disconnect(conn, pstmt);
		}
	}

	public boolean update(PreferenceDTO preferenceDTO) {
	    Connection conn = null;
	    PreparedStatement pstmt = null;

	    try {
	        conn = JDBCUtil.connect();
	        pstmt = conn.prepareStatement(UPDATE);

	        pstmt.setInt(1, preferenceDTO.getPreferenceHeight());
	        pstmt.setString(2, preferenceDTO.getPreferenceBody());
	        pstmt.setString(3, preferenceDTO.getPreferenceAge());
	        pstmt.setString(4, preferenceDTO.getUserEmail()); // WHERE 조건 값 설정

	        int result = pstmt.executeUpdate(); // 실행 후 변경된 행 수 반환
	        return result > 0; // 성공 시 true, 실패 시 false 반환

	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    } finally {
	        JDBCUtil.disconnect(conn, pstmt);
	    }
	}


	public boolean delete(PreferenceDTO preferenceDTO) {
		return false;
	}

}
