package kr.co.kmarket.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.dao.CsDAO;
import kr.co.kmarket.dto.CsDTO;

public enum CsService {
	
	INSTANCE;

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private CsDAO dao = new CsDAO();
	
	
	public int insertBoard(CsDTO dto) {
		return dao.insertBoard(dto);
	}
	
	public CsDTO selectBoard(String no) {
		return dao.selectBoard(no);
	}
	
	public List<CsDTO> selectBoards(String group, String cate1, String cate2, int start) {
		return dao.selectBoards(group, cate1, cate2, start);
	}
	
	public void updateBoard(CsDTO dto) {
		dao.updateBoard(dto);
	}
	
	public void deleteBoard(String no) {
		dao.deleteBoard(no);
	}
	
	
	
	// 리스트 페이징 구현 ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
	public int selectCountBoard(String group, String cate1) {
		return dao.selectCountBoard(group, cate1);
	}
	
	public int getLastPageNum(int total) {
		int lastPageNum = 0;
		
		if(total % 10 == 0){
			lastPageNum = total / 10;
			
		}else{
			lastPageNum = total / 10 + 1;
		}
		return lastPageNum;
	}
	
	// 페이지 그룹
	public int[] getPageGroupNum(int currentPage, int lastPageNum) {
		int currentPageGroup = (int)Math.ceil(currentPage / 10.0);
		int pageGroupStart = (currentPageGroup - 1) * 10 + 1;
		int pageGroupEnd = currentPageGroup * 10;
		
		if(pageGroupEnd > lastPageNum){
			pageGroupEnd = lastPageNum;
		}
		
		int[] result = {pageGroupStart, pageGroupEnd};
		
		return result;
	}
	
	// 페이지 시작번호
		public int getPageStartNum(int total, int currentPage) {
			int start = (currentPage - 1) * 10;
			return total - start;
		}
	
	// 현재 페이지 번호
	public int getCurrentPage(String pg) {
		int currentPage = 1;
		
		if(pg != null){
			currentPage = Integer.parseInt(pg);	
		}
		return currentPage;
	}

	
	// Limit 시작번호
		public int getStartNum(int currentPage) {
			return (currentPage - 1) * 10;
		}

	
	
	// 카테고리 상세 선택 ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
	public List<CsDTO> selectGroupListWhenGroupChoose(String group) {
		return dao.selectGroupListWhenGroupChoose(group);
	}
	
	public List<CsDTO> selectCate1ListWhenCate1Choose(String cate1) {
		return dao.selectCate3ListWhenCate2Choose(cate1);
	}
	
	public List<CsDTO> selectCate2ListWhenCate2Choose(String cate2) {
		return dao.selectCate3ListWhenCate2Choose(cate2);
	}

}
