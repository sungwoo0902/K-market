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
			psmt.setInt(1, dto.getGroup());
			psmt.setInt(2, dto.getCate1());
			psmt.setInt(3, dto.getCate2());
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
	
	public void insertComment(CsDTO dto) {
		
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.INSERT_COMMENT);
			psmt.setInt(1, dto.getGroup());
			psmt.setInt(2, dto.getParent());
			psmt.setInt(3, dto.getCate1());
			psmt.setInt(4, dto.getCate2());
			psmt.setString(5, dto.getUid());
			psmt.setString(6, dto.getTitle());
			psmt.setString(7, dto.getContent());
			psmt.executeUpdate();
			close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public CsDTO selectBoard(String no) {
		CsDTO dto = null;

		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_BOARD);
			psmt.setString(1, no);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto = new CsDTO();
				dto.setNo(rs.getString(1));
				dto.setParent(rs.getString(2));
				dto.setGroup(rs.getString(3));
				dto.setCate1(rs.getString(4));
				dto.setCate2(rs.getString(5));
				dto.setUid(rs.getString(6));
				dto.setTitle(rs.getString(7));
				dto.setContent(rs.getString(8));
				dto.setRdate(rs.getString(9));
				dto.setGroup_name(rs.getString(10));
				dto.setCate1_name(rs.getString(11));
				dto.setCate2_name(rs.getString(12));
			}
			close();
			
		} catch (Exception e) {
			logger.error("selectBoard : " + e.getMessage());
		}
		return dto;
	}
	
	public List<CsDTO> selectLatests(int group, int size) {
		
		List<CsDTO> latests = new ArrayList<>();
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_LATESTS);
			psmt.setInt(1, group);
			logger.debug("group : " + group);
			psmt.setInt(2, size);
			logger.debug("size :" + size);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				CsDTO dto = new CsDTO();
				dto.setNo(rs.getString(1));
				dto.setParent(rs.getString(2));
				dto.setGroup(rs.getString(3));
				dto.setCate1(rs.getString(4));
				dto.setCate2(rs.getString(5));
				dto.setUid(rs.getString(6));
				dto.setTitle(rs.getString(7));
				dto.setContent(rs.getString(8));
				dto.setRdate(rs.getString(9));
				latests.add(dto);
				
				logger.debug("dto : " + dto);
			}
			close();
		}catch (Exception e) {
			logger.error("selectLatests : " + e.getMessage());
		}

		return latests;
		
	}
	
	public CsDTO selectAnswer(String no) {
		CsDTO dto = null;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_ANSWER);
			psmt.setString(1, no);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto = new CsDTO();
				dto.setNo(rs.getString(1));
				dto.setParent(rs.getString(2));
				dto.setGroup(rs.getString(3));
				dto.setCate1(rs.getString(4));
				dto.setCate2(rs.getString(5));
				dto.setUid(rs.getString(6));
				dto.setTitle(rs.getString(7));
				dto.setContent(rs.getString(8));
				dto.setRdate(rs.getString(9));
			}
			close();
			
		} catch (Exception e) {
			logger.error("selectAnswer : " + e.getMessage());
		}
		return dto;
	}
	

	public List<CsDTO> selectBoards(String group, String cate1, String cate2, int start) {
			List<CsDTO> board = new ArrayList<>();
			
			try {
				conn = getConnection();
				
			if(cate2 != null) {
				psmt = conn.prepareStatement(SQL.SELECT_BOARDS_SUB_CATE);
				logger.debug("selectBoards SUB");
				psmt.setString(1, group);
				psmt.setString(2, cate1);
				psmt.setString(3, cate2);	
				psmt.setInt(4, start);
				
			}else if(cate1 != null) {
				psmt = conn.prepareStatement(SQL.SELECT_BOARDS_MIDDLE_CATE);
				logger.debug("selectBoards MIDDLE");
				psmt.setString(1, group);
				psmt.setString(2, cate1);
				psmt.setInt(3, start);
				
			}else{
				psmt = conn.prepareStatement(SQL.SELECT_BOARDS_MAIN_CATE);
				logger.debug("selectBoards MAIN");
				psmt.setString(1, group);
				psmt.setInt(2, start);
			}
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				CsDTO dto = new CsDTO();
				dto.setNo(rs.getString(1));
				dto.setParent(rs.getString(2));
				dto.setGroup(rs.getString(3));
				dto.setCate1(rs.getString(4));
				dto.setCate2(rs.getString(5));
				dto.setUid(rs.getString(6));
				dto.setTitle(rs.getString(7));
				dto.setContent(rs.getString(8));
				dto.setRdate(rs.getString(9));
				dto.setGroup_name(rs.getString(10));
				dto.setCate1_name(rs.getString(11));
				dto.setCate2_name(rs.getString(12));
				dto.setAnswer(rs.getString(13));
				board.add(dto);	
			}

			close();
			
		} catch (Exception e) {
			logger.error("selectBoards() ERROR : " + e.getMessage());
		}
		return board;
	}
	
	public List<CsDTO> selectBoardsAll(String group, String cate1) {
		List<CsDTO> board = new ArrayList<>();
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_BOARDS_MIDDLE_CATE_ALL);
			logger.debug("selectBoards MIDDLE");
			psmt.setString(1, group);
			psmt.setString(2, cate1);

			rs = psmt.executeQuery();
			
			while(rs.next()) {
				CsDTO dto = new CsDTO();
				dto.setNo(rs.getString(1));
				dto.setParent(rs.getString(2));
				dto.setGroup(rs.getString(3));
				dto.setCate1(rs.getString(4));
				dto.setCate2(rs.getString(5));
				dto.setUid(rs.getString(6));
				dto.setTitle(rs.getString(7));
				dto.setContent(rs.getString(8));
				dto.setRdate(rs.getString(9));
				dto.setGroup_name(rs.getString(10));
				dto.setCate1_name(rs.getString(11));
				dto.setCate2_name(rs.getString(12));
				dto.setAnswer(rs.getString(13));
				board.add(dto);	
			}
			close();
			
		} catch (Exception e) {
			logger.error("selectBoards() ERROR : " + e.getMessage());
		}
		return board;
	}
	
	// cate1 이름, 설명 출력
	public CsDTO selectBoard_list(String group, String cate1) {
		
		CsDTO dto = null;	
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_BOARD_CATE1_NAME_DISCRIPTION);
			psmt.setString(1, group);
			psmt.setString(2, cate1);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto = new CsDTO();
				dto.setGroup(rs.getString(1));
				dto.setCate1(rs.getString(2));
				dto.setCate1_name(rs.getString(3));
				dto.setCate1_discription(rs.getString(4));
			}
		close();
		
		} catch (Exception e) {
			logger.error("selectBoards() ERROR : " + e.getMessage());
		}
	return dto;
	}
	
	
	// 공지 답변 유무 출력
	public CsDTO selectBoard_parent(String group, String cate1, int start) {
		
		CsDTO dto = null;
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_QNA_PARENT);
			psmt.setString(1, group);
			psmt.setString(2, group);
			psmt.setInt(3, start);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				dto = new CsDTO();
				dto.setNo(rs.getString(1));
				dto.setParent(rs.getString(2));
				dto.setGroup(rs.getString(3));
				dto.setCate1(rs.getString(4));
				dto.setCate2(rs.getString(5));
				dto.setUid(rs.getString(6));
				dto.setTitle(rs.getString(7));
				dto.setContent(rs.getString(8));
				dto.setRdate(rs.getString(9));
				dto.setGroup_name(rs.getString(10));
				dto.setCate1_name(rs.getString(11));
				dto.setCate2_name(rs.getString(12));
				dto.setAnswer(rs.getString(13));
			}
			close();
		}catch (Exception e) {
			logger.error("selectBoard_parent() ERROR : " +e.getMessage());
		}
		
		return null;
		
	}
	
	public void updateNotice(CsDTO dto) {
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.UPDATE_BOARD_NOTICE);
			psmt.setInt(1, dto.getCate1());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContent());
			psmt.setInt(4, dto.getNo());
			psmt.executeUpdate();
			close();
			
		} catch (Exception e) {
			logger.error("deleteBoard() ERROR : " + e.getMessage());
		}
	}
	
	public void updateFaq(CsDTO dto) {
		
		 try {
				conn = getConnection();
				psmt = conn.prepareStatement(SQL.UPDATE_BOARD);
				psmt.setInt(1, dto.getCate1());
				psmt.setInt(2, dto.getCate2());
				psmt.setString(3, dto.getTitle());
				psmt.setString(4, dto.getContent());
				psmt.setInt(5, dto.getNo());
				psmt.executeUpdate();
				close();
				
		} catch (Exception e) {
			logger.error("deleteBoard() ERROR : " + e.getMessage());
			
		}
	}
	public int updateBoard(CsDTO dto) {
		int result = 0;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.UPDATE_BOARD);
			psmt.setInt(1, dto.getCate1());
			psmt.setInt(2, dto.getCate2());
			psmt.setString(3, dto.getTitle());
			psmt.setString(4, dto.getContent());
			psmt.setInt(5, dto.getNo());
			result = psmt.executeUpdate();
			close();
			
		} catch (Exception e) {
			logger.error("updateBoard() ERROR : " + e.getMessage());
		}
		return result;
	}
	
	public void deleteBoard(String no) {
		
		try {
			
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.DELETE_BOARD);
			psmt.setString(1, no);
			psmt.executeUpdate();
			
			close();
	
		} catch (Exception e) {
			logger.error("deleteBoard() ERROR : " + e.getMessage());
		}
	}
	
	// 페이징을 위한 메서드
	public int selectCountBoard(String group, String cate1, String cate2) {
		int total = 0;
		try {
			conn = getConnection();
			
			if(cate2 != null) {
				psmt = conn.prepareStatement(SQL.SELECT_COUNT_SUB_CATE);
				psmt.setString(1, group);
				psmt.setString(2, cate1);
				psmt.setString(3, cate2);
				
			}else if(cate1 != null) {
				psmt = conn.prepareStatement(SQL.SELECT_COUNT_MIDDLE_CATE);
				psmt.setString(1, group);
				psmt.setString(2, cate1);
				
			}else if(group != null) {
				psmt = conn.prepareStatement(SQL.SELECT_COUNT_MAIN_CATE);
				psmt.setString(1, group);
			}
			
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				total = rs.getInt(1);
				logger.debug("total : " +total);
			}
			close();	
			
		}catch (Exception e) {
			logger.error("selectCountMainCate() ERROR : " + e.getMessage());
		}
		return total;
	}
	
	// 카테고리 상세 선택 ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

	public List<CsDTO> selectCate1ListWhenGroupChoose(String group) {
		List<CsDTO> cate1List = new ArrayList<>();
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_CATE1_LIST_WHEN_GROUP_CHOOSE);
			psmt.setString(1, group);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				CsDTO cate = new CsDTO();
				cate.setGroup(rs.getString(1));
				cate.setCate1(rs.getString(2));
				cate.setCate1_name(rs.getString(3));
				cate.setCate1_discription(rs.getString(4));
				cate1List.add(cate);
			}
			close();
			
		} catch (Exception e) {
			logger.error("selectCate1List()... : " + e.getMessage());
		}
		return cate1List;
	}
	
	public List<CsDTO> selectCate2ListWhenCate1Choose(String cate1) {
		List<CsDTO> cate2List = new ArrayList<>();
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_CATE2_LIST_WHEN_CATE1_CHOOSE);
			psmt.setString(1, cate1);
			logger.debug("selectCate2.....() cate1 : " + cate1);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				CsDTO cate = new CsDTO();
				cate.setCate1(rs.getString(1));
				cate.setCate2(rs.getString(2));
				cate.setCate2_name(rs.getString(3));
				cate2List.add(cate);
			}
			close();
		} catch (Exception e) {
			logger.error("selectCate2List()... : " + e.getMessage());
		}
		return cate2List;
	}
}
