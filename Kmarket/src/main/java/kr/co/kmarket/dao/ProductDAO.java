package kr.co.kmarket.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.db.DBHelper;
import kr.co.kmarket.db.SQL;
import kr.co.kmarket.dto.ProductDTO;

public class ProductDAO extends DBHelper{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void insertProduct(ProductDTO product) {
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.INSERT_PRODUCT);
			
		} catch (Exception e) {
			logger.error("insertProduct() error :"+e.getMessage());
		}
	}
	public ProductDTO selectProduct(String uid) {
		return null;
	}
	public List<ProductDTO> selectProducts() {
		return null;
	}
	public void updateProduct(ProductDTO dto) {}
	public void deleteProduct(String uid) {}
	
}
