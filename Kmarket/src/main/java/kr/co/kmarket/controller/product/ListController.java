package kr.co.kmarket.controller.product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.dto.CategoryDTO;
import kr.co.kmarket.dto.ProductDTO;
import kr.co.kmarket.service.CategoryService;
import kr.co.kmarket.service.ProductService;

@WebServlet("/product/list.do")
public class ListController extends HttpServlet{

	private static final long serialVersionUID = -2740152939344565075L;
	private ProductService  prodService = ProductService.INSTANCE;
	private CategoryService cateService = CategoryService.INSTANCE;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String success = req.getParameter("success");
		String cate1 = req.getParameter("cate1");
		String cate2 = req.getParameter("cate2");
		String type = req.getParameter("type");
		String pg = req.getParameter("pg");
		
		if(type==null || type.equals("")) {
			type = null;
		}
		
		// 최근 본 상품 ****************************************************
        Cookie[] cookies = req.getCookies();
		List<String> prodDatas = new ArrayList<>();
		
		// 최근 본 상품 불러오기 
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals("lateView")) {
					String value = cookie.getValue();
					String[] prods = value.split("DONTWORRYBEHAPPY");
					prodDatas.addAll(Arrays.asList(prods));
				}
			}
		}
		
		// toolBar 출력 처리
		List<ProductDTO> latelyProduct = new ArrayList<>(); 
		logger.debug("prodDatas.size() : " + prodDatas.size());
		for(int i=0 ; i < prodDatas.size() ; i+=2) {
			logger.debug("for ..." + i);
			ProductDTO dto = new ProductDTO();
			dto.setProdNo(prodDatas.get(i));
			logger.debug("index (" + i + ") : " + prodDatas.get(i));
			dto.setThumb1(prodDatas.get(i+1));
			logger.debug("index (" + i + ") : " + prodDatas.get(i+1));
			latelyProduct.add(dto);
		}
		
		
		// 사이드바 영역 ****************************************************
		// 사이드 카테고리(cate1) 불러오기
		List<CategoryDTO> category1 = cateService.selectCate1s();
		req.setAttribute("category1", category1);
		
		// 사이드 카테고리(cate2) 불러오기
		List<CategoryDTO> allCate = cateService.selectAllCate();
		req.setAttribute("allCate", allCate);
		
		
		// 페이징 처리 영역 *************************************************
		int total = 0;
		
		// 현재 페이지 번호
		int currentPage = prodService.getCurrentPage(pg);
		
		// 전체 게시물 갯수
		if(cate1 == null || cate1.equals("")) {
			total = prodService.selectCountProductsAll(null, 7);
			
		}else if(cate2 == null || cate2.equals("")) {
			total = prodService.selectCountProductsByCate1(cate1);
			
		}else {
			total = prodService.selectCountProductsByCate2(cate1, cate2);
		}
		logger.debug("total : " + total);
		
		// 마지막 페이지 번호
		int lastPageNum = prodService.getLastPageNum(total);
		
		// 페이지 그룹 start, end 번호
		int[] result = prodService.getPageGroupNum(currentPage, lastPageNum);
		
		// 페이지 시작번호
		int pageStartNum = prodService.getPageStartNum(total, currentPage);
		
		// 시작 인덱스
		int start = prodService.getStartNum(currentPage);
		
		
		// 현재 페이지 게시물 조회 ***********************************************
		List<ProductDTO> products = new ArrayList<>();
		if(cate1 == null || cate1.equals("")) {
			products = prodService.selectProductsAllWithType(type, start);
			
		}else if(cate2 == null || cate2.equals("")) {
			products = prodService.selectProductsByCate1(cate1, start, type);
			
		}else {
			products = prodService.selectProductsByCate2(cate1, cate2, start, type);
		}
		
		
		// 현재 페이지 카테고리 **************************************************
		CategoryDTO cate = null;
		if(cate2 == null || cate2.equals("")) {
			cate = cateService.selectCate1(cate1);
		}else {
			cate = cateService.selectCate(cate1, cate2);
		}
		req.setAttribute("cate", cate);
		logger.debug("cate : " + cate);
		
		
		// ******************************************************************
		req.setAttribute("success", success);
		req.setAttribute("products", products);
		req.setAttribute("total", total);
		req.setAttribute("cate1", cate1);
		req.setAttribute("cate2", cate2);
		req.setAttribute("type", type);
		
		req.setAttribute("currentPage", currentPage);
		req.setAttribute("lastPageNum", lastPageNum);
		req.setAttribute("pageGroupStart", result[0]);
		req.setAttribute("pageGroupEnd", result[1]);
		req.setAttribute("pageStartNum", pageStartNum+1);
		
		req.setAttribute("latelyProduct", latelyProduct);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/product/list.jsp?pg="+pg);
		dispatcher.forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
	}
	
}
