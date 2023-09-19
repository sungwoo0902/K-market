package kr.co.kmarket.dao;


import java.util.ArrayList;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.db.DBHelper;
import kr.co.kmarket.db.SQL;
import kr.co.kmarket.dto.CsDTO;

public class CsDAO extends DBHelper {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// 기본 CRUD 메서드
	public int insertBoard(CsDTO dto) {
		int result = 0;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.INSERT_BOARD);
			psmt.setInt(1, dto.getBoardCate1());
			psmt.setInt(2, dto.getBoardCate2());
			psmt.setInt(3, dto.getBoardCate3());
			psmt.setString(4, dto.getUid());
			psmt.setString(5, dto.getTitle());
			psmt.setString(6, dto.getContent());
			result = psmt.executeUpdate();
			close();
			
		} catch (Exception e) {
			logger.error("insertBoard : " + e.getMessage());
		}
		return result;
	}
	
	public CsDTO selectBoard(String no) {
		CsDTO board = null;

		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_BOARD);
			psmt.setString(1, no);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				board = new CsDTO();
				board.setNo(rs.getInt(1));
				board.setBoardCate1(rs.getInt(2));
				board.setBoardCate2(rs.getInt(3));
				board.setBoardCate3(rs.getInt(4));
				board.setUid(rs.getString(5));
				board.setTitle(rs.getString(6));
				board.setContent(rs.getString(7));
				board.setrDate(rs.getString(8));
			}
			close();
		} catch (Exception e) {
			logger.error("selectBoard() - "+e.getMessage());
		}
		return board;
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
	
	// 카테고리 상세 선택 ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
	public CsDTO selectCate2ListWhenCate1Choose(String boardCate1) {
		CsDTO dto = null;
		try {
			conn = getConnection();
		} catch (Exception e) {
			logger.error("selectCate2List()... : " + e.getMessage());
		}
		return dto;
	}
	
	public CsDTO selectCate3ListWhenCate2Choose(String boardCate2) {
		CsDTO dto = null;
		try {
			
		} catch (Exception e) {
			logger.error("selectCate3List()... : " + e.getMessage());
		}
		return dto;
	}

}
