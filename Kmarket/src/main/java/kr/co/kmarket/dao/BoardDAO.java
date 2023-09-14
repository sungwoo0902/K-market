package kr.co.kmarket.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.db.DBHelper;
import kr.co.kmarket.db.SQL;
import kr.co.kmarket.dto.BoardDTO;


public class BoardDAO extends DBHelper{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public int insertBoard(BoardDTO dto) {
		
		int no = 0;
		
		try {
			conn = getConnection();
			conn.setAutoCommit(false); // Transaction 시작
			
			stmt = conn.createStatement();
			psmt = conn.prepareStatement(SQL.INSERT_BOARD);
			psmt.setInt(1, dto.getBoardCate1());
			psmt.setInt(2, dto.getBoardCate2());
			psmt.setString(3, dto.getUid());
			psmt.setString(4, dto.getTitle());
			psmt.setString(5, dto.getContent());
			psmt.setString(6, dto.getrDate());
			psmt.executeUpdate();
			conn.commit();
			
			if(rs.next()) {
				no = rs.getInt(1);
			}
			close();
		} catch (Exception e) {
			logger.error("insertBoard() - "+e.getMessage());
		}
		return no;
	}
	
	public BoardDTO selectBoard(String no) {
		BoardDTO board = null;
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_BOARD);
			psmt.setString(1, no);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				board = new BoardDTO();
				board.setNo(rs.getInt(1));
				board.setBoardCate1(rs.getInt(2));
				board.setBoardCate2(rs.getInt(3));
				board.setUid(rs.getString(4));
				board.setTitle(rs.getString(5));
				board.setContent(rs.getString(6));
				board.setrDate(rs.getString(7));
			}
			close();
		} catch (Exception e) {
			logger.error("selectBoard() "+e.getMessage());
		}
		return board;
	}
	
	public List<BoardDTO> selectBoards(int start, String search){
		
		List<BoardDTO> boards = new ArrayList<>();
		
		try {
			conn = getConnection();
			if(search == null) {
				psmt = conn.prepareStatement(SQL.SELECT_BOARDS);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return boards;
	}
	
	public void updateBoard(BoardDTO dto) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.UPDATE_BOARD);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setInt(3, dto.getNo());
			psmt.executeUpdate();
			close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteBoard(String no) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.DELETE_BOARD);
			psmt.setString(1, no);
			psmt.executeUpdate();
			close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 추가
	public int selectCountTotal(String search) {
		
		int total = 0;
		
		try {
			conn = getConnection();
			
			if(search == null) {
				psmt = conn.prepareStatement(SQL.SELECT_COUNT_TOTAL);
			}else {
				psmt = conn.prepareStatement(SQL.SELECT_COUNT_TOTAL_FOR_SEARCH);
				psmt.setString(1, "%"+search+"%");
			}
			
			rs = psmt.executeQuery();
			if(rs.next()) {
				total = rs.getInt(1);
			}
			close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return total;
	}
}
