package kr.co.kmarket.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.db.DBHelper;
import kr.co.kmarket.db.SQL;
import kr.co.kmarket.dto.CategoryDTO;

public class CategoryDAO extends DBHelper{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	
	public CategoryDTO selectCate1(String cate1){
		logger.debug("selectCate1()...");
		CategoryDTO dto = null;
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_CATE1);
			psmt.setString(1, cate1);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto = new CategoryDTO();
				dto.setCate1No(rs.getString(1));
				dto.setC1Name(rs.getString(2));
				dto.setCate1Icon(rs.getString(3));
			}
			close();
			
		} catch (Exception e) {
			logger.error("selectCate1s() error : "+ e.getMessage());
		}
		return dto;
	}
	
	public List<CategoryDTO> selectCate1s(){
		
		List<CategoryDTO> cate1s = new ArrayList<>();
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL.SELECT_CATE1S);
			
			while(rs.next()) {
				CategoryDTO dto = new CategoryDTO();
				dto.setCate1No(rs.getString(1));
				dto.setC1Name(rs.getString(2));
				dto.setCate1Icon(rs.getString(3));
				cate1s.add(dto);
			}
			close();
			
		} catch (Exception e) {
			logger.error("selectCate1s() error : "+ e.getMessage());
		}
		return cate1s;
	}
	
	
	public CategoryDTO selectCate2(String cate2){
		
		CategoryDTO dto = null;
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_CATE2);
			psmt.setString(1, cate2);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto = new CategoryDTO();
				dto.setCate1No(rs.getString(1));
				dto.setCate2No(rs.getString(2));
				dto.setC2Name(rs.getString(3));
			}
			close();
			
		} catch (Exception e) {
			logger.error("selectCate2() error : "+ e.getMessage());
		}
		return dto;
	}
	
	
	public List<CategoryDTO> selectCate2s(String cate1){
		
		List<CategoryDTO> cate2s = new ArrayList<>();
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_CATE2S);
			psmt.setString(1, cate1);
			rs = psmt.executeQuery();
			
			System.out.println("cate1 : "+cate1);
			
			while(rs.next()) {
				CategoryDTO dto = new CategoryDTO();
				dto.setCate1No(rs.getString(1));
				dto.setCate2No(rs.getString(2));
				dto.setC2Name(rs.getString(3));
				cate2s.add(dto);
			}
			close();
			
		} catch (Exception e) {
			logger.error("selectCate2s() error : "+ e.getMessage());
		}
		return cate2s;
	}
	
	
	public List<CategoryDTO> selectAllCate() {
		List<CategoryDTO> allCate = new ArrayList<>();
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL.SELECT_ALL_CATE);
			
			while(rs.next()) {
				CategoryDTO dto = new CategoryDTO();
				dto.setCate1No(rs.getString(1));
				dto.setC1Name(rs.getString(2));
				dto.setCate1Icon(rs.getString(3));
				dto.setCate2No(rs.getString(4));
				dto.setC2Name(rs.getString(5));
				allCate.add(dto);
			}
			close();
			
		} catch (Exception e) {
			logger.error("selectAllCate() error : " + e.getMessage());
		}
		return allCate;
	}
	
	public CategoryDTO selectCate(String cate1, String cate2) {
		logger.debug("selectCate()...");
		CategoryDTO dto = null;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_CATE);
			psmt.setString(1, cate1);
			psmt.setString(2, cate1);
			psmt.setString(3, cate2);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto = new CategoryDTO();
				dto.setCate1No(rs.getString(1));
				dto.setC1Name(rs.getString(2));
				dto.setCate1Icon(rs.getString(3));
				dto.setCate2No(rs.getString(4));
				dto.setC2Name(rs.getString(5));
			}
			close();
			
		} catch (Exception e) {
			logger.error("selectCate : " + e.getMessage());
		}
		return dto;
	}
}
