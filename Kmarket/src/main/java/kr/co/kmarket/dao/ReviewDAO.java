package kr.co.kmarket.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.db.DBHelper;
import kr.co.kmarket.db.SQL;
import kr.co.kmarket.dto.ReviewDTO;

public class ReviewDAO extends DBHelper {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public List<ReviewDTO> selectReviews(String prodNo, int start) {
		List<ReviewDTO> reviews = new ArrayList<>();
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_REVIEWS);
			psmt.setString(1, prodNo);
			psmt.setInt(2, start);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				ReviewDTO dto = new ReviewDTO();
				dto.setRevNo(rs.getString(1));
				dto.setProdNo(rs.getString(2));
				dto.setContent(rs.getString(3));
				dto.setUid(rs.getString(4));
				dto.setRating(rs.getString(5));
				dto.setRegip(rs.getString(6));
				dto.setRdate(rs.getString(7));
				dto.setProdName(rs.getString(8));
				reviews.add(dto);
			}
			close();
			
		} catch (Exception e) {
			logger.error("selectReviews : " + e.getMessage());
		}
		return reviews;
	}
	
	public int selectReivewCount(String prodNo) {
		int result = 0;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_REVIEW_COUNT);
			psmt.setString(1, prodNo);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			close();
			
		} catch (Exception e) {
			logger.error("selectReviewCount : " + e.getMessage());
		}
		return result;
	}
}