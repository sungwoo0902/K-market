package kr.co.kmarket.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.dao.ReviewDAO;
import kr.co.kmarket.dto.ReviewDTO;

public enum ReviewService {
	
	INSTANCE;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private ReviewDAO dao = new ReviewDAO();
	
	public List<ReviewDTO> selectReviews(String prodNo, int start) {
		return dao.selectReviews(prodNo, start);
	}
	
	public int selectReivewCount(String prodNo) {
		return dao.selectReivewCount(prodNo);
	}
	
	
	
	/* page ************************************************************/
	// 마지막 페이지 번호
	public int getLastPageNum(int total) {
		
		int lastPageNum = 0;
		
		if(total % 5 == 0){
			lastPageNum = total / 5;
		}else{
			lastPageNum = total / 5 + 1;
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
		return (currentPage - 1) * 5;
	}
}