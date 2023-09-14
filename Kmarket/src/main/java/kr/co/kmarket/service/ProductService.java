package kr.co.kmarket.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.dao.ProductDAO;
import kr.co.kmarket.dto.ProductDTO;

public enum ProductService {

	INSTANCE;
	
	private ProductDAO dao = new ProductDAO();
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void insertProduct(ProductDTO product) {
		dao.insertProduct(product);
	}
	public ProductDTO selectProduct(String uid) {
		return dao.selectProduct(uid);
	}
	public List<ProductDTO> selectProducts() {
		return dao.selectProducts();
	}
	public void updateProduct(ProductDTO dto) {
		dao.updateProduct(dto);
	}
	public void deleteProduct(String uid) {
		dao.deleteProduct(uid);
	}
}
