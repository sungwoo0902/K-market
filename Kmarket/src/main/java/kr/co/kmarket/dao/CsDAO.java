package kr.co.kmarket.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.db.DBHelper;
import kr.co.kmarket.db.SQL;
import kr.co.kmarket.dto.CsDTO;

public class CsDAO extends DBHelper {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// 기본 CRUD 메서드
	public void insertBoard(CsDTO dto) {
		
	}
	
	public CsDTO selectBoard(String no) {
		return null;
	}
	
	public List<CsDTO> selectBoards() {
		return null;
	}
	
	public void updateBoard(CsDTO dto) {
		
	}
	
	public void deleteBoard(String no) {
		
	}
	
	// 페이징을 위한 메서드
	public int selectCountBoard(String boardCate1, String boardCate2) {
		int total = 0;
		try {
			conn = getConnection();
			
			if(boardCate2 == null) {
				psmt = conn.prepareStatement(SQL.SELECT_COUNT_MAIN_CATE);
				psmt.setString(1, boardCate1);
			
			}else {
				psmt = conn.prepareStatement(SQL.SELECT_COUNT_MIDDLE_CATE);
				psmt.setString(1, boardCate1);
				psmt.setString(2, boardCate2);
			}
			rs = psmt.executeQuery();
			if(rs.next()) {
				total = rs.getInt(1);
			}
			close();	
			
		}catch (Exception e) {
			logger.error("selectCountMainCate() ERROR : " + e.getMessage());
		}
		return total;
	}
}
