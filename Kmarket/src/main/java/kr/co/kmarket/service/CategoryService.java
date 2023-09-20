package kr.co.kmarket.service;

import java.util.List;

import kr.co.kmarket.dao.CategoryDAO;
import kr.co.kmarket.dto.CategoryDTO;

public enum CategoryService {

	INSTANCE;
	
	private CategoryDAO dao = new CategoryDAO();
	
	public CategoryDTO selectCate1(String cate1){
		return dao.selectCate1(cate1);
	}
	
	public List<CategoryDTO> selectCate1s(){
		return dao.selectCate1s();
	}
	
	public CategoryDTO selectCate2(String cate2){
		return dao.selectCate1(cate2);
	}
	
	public List<CategoryDTO> selectCate2s(String cate1){
		return dao.selectCate2s(cate1);
	}
	
	public List<CategoryDTO> selectAllCate(){
		return dao.selectAllCate();
	}
	
	public CategoryDTO selectCate(String cate1, String cate2) {
		return dao.selectCate(cate1, cate2);
	}
}
