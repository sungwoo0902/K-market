package kr.co.kmarket.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.co.kmarket.dao.ProductDAO;
import kr.co.kmarket.dto.ProductDTO;

public enum ProductService {

	INSTANCE;
	
	private ProductDAO dao = new ProductDAO();
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void insertProduct(ProductDTO product) {
		dao.insertProduct(product);
	}
	public ProductDTO selectProduct(String cate1, String cate2, String prodNo) {
		return dao.selectProduct(cate1, cate2, prodNo);
	}
	public List<ProductDTO> selectProductsByCate1(String cate1, int start) {
		return dao.selectProductsByCate1(cate1, start);
	}
	public List<ProductDTO> selectProductsByCate2(String cate1, String cate2, int start, String type) {
		return dao.selectProductsByCate2(cate1, cate2, start, type);
	}
	public List<ProductDTO> selectProductsAll(int start, String seller) {
		return dao.selectProductsAll(start, seller);
	}
	public void updateProduct(ProductDTO dto) {
		dao.updateProduct(dto);
	}
	public void deleteProduct(String uid, String no) {
		dao.deleteProduct(uid, no);
	}
	public int selectCountProductsAll(String seller) {
		return dao.selectCountProductsAll(seller);
	}
	public int selectCountProductsByCate1(String cate1) {
		return dao.selectCountProductsByCate1(cate1);
	}
	public int selectCountProductsByCate2(String cate1, String cate2) {
		return dao.selectCountProductsByCate2(cate1, cate2);
	}
	
	
	
	/************************* 베스트/히트/추천 상품 불러오기 *************************/
	public List<ProductDTO> selectPopularProducts(String type) {
		return dao.selectPopularProducts(type);
	}

	/**************************** File Upload ****************************/
	// 업로드 경로 구하기
	public String getFilePath(HttpServletRequest req) {
		// 파일 업로드 경로 구하기 
		ServletContext ctx = req.getServletContext();
		String path = ctx.getRealPath("/thumb");
		return path;
	}
	
	// 파일명 수정
	public String renameToFile(HttpServletRequest req, String oName) {
		
		String path = getFilePath(req);
		
		int i = oName.lastIndexOf(".");
		String ext = oName.substring(i);
		
		String uuid  = UUID.randomUUID().toString();
		String sName = uuid + ext;
		
		File f1 = new File(path+"/"+oName);
		File f2 = new File(path+"/"+sName);
		
		f1.renameTo(f2);
		
		return sName;
	}
	
	
	// 파일 업로드
	public MultipartRequest uploadFile(HttpServletRequest req) {
		// 파일 경로 구하기
		String path = getFilePath(req);
		
		// 최대 업로드 파일 크기
		int maxSize = 1024 * 1024 * 10;
		
		// 파일 업로드 및 Multipart 객체 생성
		MultipartRequest mr = null;
		
		try {
			mr = new MultipartRequest(req, 
									  path, 
									  maxSize, 
									  "UTF-8", 
									  new DefaultFileRenamePolicy());
		} catch (IOException e) {
			logger.error("uploadFile : " + e.getMessage());
		}
		
		return mr;
	}
	/*
	// 파일 다운로드
	public void downloadFile(HttpServletRequest req, HttpServletResponse resp, FileDTO dto) throws IOException {
		// response 파일 다운로드 헤더 수정
		resp.setContentType("application/octet-stream");
		resp.setHeader("Content-Disposition", "attachment; filename="+URLEncoder.encode(dto.getOfile(), "utf-8"));
		resp.setHeader("Content-Transfer-Encoding", "binary");
		resp.setHeader("Pragma", "no-cache");
		resp.setHeader("Cache-Control", "private");
		
		// response 파일 스트림 작업
		String path = getFilePath(req);
		
		File file = new File(path+"/"+dto.getSfile());
		
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
		BufferedOutputStream bos = new BufferedOutputStream(resp.getOutputStream());
				
		while(true){
			
			int data = bis.read();
			if(data == -1){
				break;
			}
			
			bos.write(data);
		}
		
		bos.close();
		bis.close();
	}
	*/
	
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
}
