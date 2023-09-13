package kr.co.kmarket.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.db.DBHelper;
import kr.co.kmarket.db.SQL;
import kr.co.kmarket.dto.CategoryDTO;

public class CategoryDAO extends DBHelper{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	
	public CategoryDTO selectCate1(String cate1){
		
		CategoryDTO dto = null;
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_CATE1S);
			psmt.setString(1, cate1);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto = new CategoryDTO();
				dto.setCate1(rs.getString(1));
				dto.setC1Name(rs.getString(2));
			}
			close();
			
		} catch (Exception e) {
			logger.error("selectCate1s() error : "+ e.getMessage());
		}
		return dto;
	}
	
	public List<CategoryDTO> selectCate1s(String cate1){
		
		List<CategoryDTO> cate1s = null;
		
		return null;
	}
	
	public List<CategoryDTO> selectCate2s(String cate2){
		
		try {
			conn = getConnection();
			
			
			
		} catch (Exception e) {
			logger.error("selectCate2s() error : "+ e.getMessage());
		}
		
		return null;
	}
}
