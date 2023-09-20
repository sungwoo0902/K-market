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
}