package kr.co.kmarket.controller;

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
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.dto.CategoryDTO;
import kr.co.kmarket.dto.MemberDTO;
import kr.co.kmarket.dto.ProductDTO;
import kr.co.kmarket.service.CategoryService;
import kr.co.kmarket.service.ProductService;

/**
 *	작업시작일 : 2023/09/19
 *	작업종료일 : 2023/09/24
 *	작업자 : 한상민
 *  내용 : 인덱스 페이지 구현
 *   - 전체, cate1구분, cate2구분으로 하여 상품보기 구현
 *   - 위 상품을 기준에 따라 8가지 방법으로 정렬하기 구현
 *   - 별점 및 리뷰가 작성된 상품에 별점 구현
 *   - 슬라이드 배너 설정(bxSilder) 및 일정 시간마다 자동 슬라이드
 *   - 카테고리(view aside 및 list aside 포함) 영역 db를 기준으로 출력(아이콘 포함)
 */

@WebServlet(value= {"", "/index.do"})
public class IndexController extends HttpServlet{

	private static final long serialVersionUID = 5783589879373746478L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private CategoryService cService = CategoryService.INSTANCE;
	private ProductService  pService = ProductService.INSTANCE;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("doGet()...");
		String success = req.getParameter("success");
		req.setAttribute("success", success);
		
		HttpSession session = req.getSession();
		MemberDTO sessUser = (MemberDTO) session.getAttribute("sessUser");
		req.setAttribute("sessUser", sessUser);
		
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
		
		// 사이드 카테고리(cate1) 불러오기
		List<CategoryDTO> category1 = cService.selectCate1s();
		req.setAttribute("category1", category1);
		
		// 사이드 카테고리(cate2) 불러오기
		List<CategoryDTO> allCate = cService.selectAllCate();
		req.setAttribute("allCate", allCate);
		
		// 베스트, 히트, 추천 상품 불러오기 
		List<ProductDTO> bestItems    = pService.selectPopularProducts("best");
		List<ProductDTO> hitItems     = pService.selectPopularProducts("hit");
		List<ProductDTO> rcmdItems    = pService.selectPopularProducts("recommend");
		List<ProductDTO> currentItems = pService.selectPopularProducts("current");
		List<ProductDTO> discntItems  = pService.selectPopularProducts("discount");
		req.setAttribute("bestItems",    bestItems);
		req.setAttribute("hitItems",     hitItems);
		req.setAttribute("rcmdItems",    rcmdItems);
		req.setAttribute("currentItems", currentItems);
		req.setAttribute("discntItems",  discntItems);
		
		req.setAttribute("latelyProduct", latelyProduct);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
		dispatcher.forward(req, resp);
	}
}