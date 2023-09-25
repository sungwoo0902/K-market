package kr.co.kmarket.controller.product;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
import kr.co.kmarket.dto.ReviewDTO;
import kr.co.kmarket.service.CategoryService;
import kr.co.kmarket.service.ProductService;
import kr.co.kmarket.service.ReviewService;

/**
 *	작업시작일 : 2023/09/19
 *	작업종료일 : 2023/09/24
 *  작업자 : 강윤수
 *  내용 : 
 *   - view 페이지 출력 및 내용 출력 구현
 *   - (+), (-) 클릭시 새로고침 없이 count 및 가격 변동 구현
 *   - 장바구니, 구매하기 버튼으로 submit 기능 구현
 *   
 *	작업자 : 한상민
 *  내용 : 
 *   - 상품평 보기 클릭시 상품평이 있을 경우 상품평으로 자동 스크롤
 *   - 현재 날짜를 기준으로 2일 후 배송 예정 안내메시지 출력(배송일이 '금','토', 도착일이 '일'인 경우 3일 후로 출력)
 *   - 리뷰 있을 경우 리뷰 5개씩 10페이지 기준 출력
 *   - 상품 view.do를 doGet시, 조회수(hit) ++; 
 *   - 최근 본 상품 쿠키에 저장하여 5개 출력 (5개를 초과시 가장 오래된 쿠키 삭제)
 */

@WebServlet("/product/view.do")
public class ViewController extends HttpServlet{

	private static final long serialVersionUID = -7245036682184264436L;
	private ProductService  prodService = ProductService.INSTANCE;
	private CategoryService cateService = CategoryService.INSTANCE;
	private ReviewService reviewService = ReviewService.INSTANCE;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String cate1  = req.getParameter("cate1");
		String cate2  = req.getParameter("cate2");
		String prodNo = req.getParameter("prodNo");
		String pg     = req.getParameter("pg");
		
		
		// 사이드바 영역 ****************************************************
		// 사이드 카테고리(cate1) 불러오기
		List<CategoryDTO> category1 = cateService.selectCate1s();
		req.setAttribute("category1", category1);
		
		// 사이드 카테고리(cate2) 불러오기
		List<CategoryDTO> allCate = cateService.selectAllCate();
		req.setAttribute("allCate", allCate);
		
		
		// 조회수 count++ **************************************************
		prodService.updateProductHit(prodNo);
		
		
		// 페이징 처리 영역 *************************************************
		int total = 0;
		
		// 현재 페이지 번호
		int currentPage = reviewService.getCurrentPage(pg);
		
		// 전체 게시물 갯수
		total = reviewService.selectReivewCount(prodNo);
		
		// 마지막 페이지 번호
		int lastPageNum = reviewService.getLastPageNum(total);
		
		// 페이지 그룹 start, end 번호
		int[] result = reviewService.getPageGroupNum(currentPage, lastPageNum);
		
		// 시작 인덱스
		int start = reviewService.getStartNum(currentPage);
		
		
		// 선택한 상품 불러오기 / 선택한 상품 카테고리 NAV 설정 ****************
		ProductDTO  prod = prodService.selectProduct(prodNo);
		CategoryDTO cate = null;
		if(cate2 == null || cate2.equals("")) {
			cate = cateService.selectCate1(cate1);
		}else {
			cate = cateService.selectCate(cate1, cate2);
		}
		logger.debug("prod : " + prod);
		logger.debug("cate : " + cate);
		
		
		// 선택 상품 리뷰 불러오기 *******************************************
		List<ReviewDTO> review = reviewService.selectReviews(prodNo, start);
		logger.debug("review : " + review);
		logger.debug("currentPage : " + currentPage);
		
		
		// 현재 날짜 불러오기 ************************************************
		// 현재 날짜
		boolean rolex = true;
		int time = 2;
		String twoDaysLaterFormatted = null;
		String twoDaysLaterDayOfWeek = null;
		while(rolex) {
			Calendar calendar = Calendar.getInstance();
	        Date currentDate = calendar.getTime();

	        // 현재 날짜+2
	        calendar.add(Calendar.DAY_OF_MONTH, time);
	        Date twoDaysLater = calendar.getTime();

	        // SimpleDateFormat(MM/dd)
	        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd");
	        twoDaysLaterFormatted = dateFormat.format(twoDaysLater);

	        // SimpleDateFormat(E)
	        SimpleDateFormat dayOfWeekFormat = new SimpleDateFormat("E", Locale.KOREAN);
	        twoDaysLaterDayOfWeek = dayOfWeekFormat.format(twoDaysLater);
	        
	        // 도착일이 일요일이면 하루 지연
	        if(twoDaysLaterDayOfWeek.equals("일")) {
	        	time++;
	        	
	        }else if(twoDaysLaterDayOfWeek.equals("월")) {
	        	time++;
	        	
	        }else if(twoDaysLaterDayOfWeek.equals("화")) {
	        	time++;
	        }else {
	        	rolex = false;
	        }
		}
        logger.debug("이틀 날짜 : " + twoDaysLaterFormatted);
        logger.debug("이틀 요일 : " + twoDaysLaterDayOfWeek);
        
		
		// 최근 본 상품 ****************************************************
        Cookie[] cookies = req.getCookies();
		List<String> prodDatas = new ArrayList<>();
		String imgLoad = prod.getProdCate1() + "/" + prod.getProdCate2() + "/" +  prod.getThumb2();
		
		// 최근 본 상품 불러오기 
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals("lateView")) {
					String value = cookie.getValue();
					String[] prods = value.split("DONTWORRYBEHAPPY");
					logger.debug("value : " + value);
					prodDatas.addAll(Arrays.asList(prods));
				}
			}
		}
		logger.debug("prodDatas1 : " + prodDatas);
		// 최근 상품 추가
		if(prodNo != null) {
			int cnt = 0;
			for(int i=0 ; i<prodDatas.size() ; i+=2) {
				if(prodNo.equals(prodDatas.get(i))) {
					logger.debug("count : " + cnt);
					logger.debug("prodNo : " + prodNo);
					logger.debug("data : " + prodDatas.get(i));
					// 중복데이터 제거
					prodDatas.remove(i+1);
					prodDatas.remove(i);
					break;
				}
			}
			prodDatas.add(prodNo);
			prodDatas.add(imgLoad);
			logger.debug("prodDatas2 : " + prodDatas);
			
			// 최근 본 상품이 5개를 넘어갈 시 가장 오래된 상품 제거
			if(prodDatas.size() > 10) {
				prodDatas.remove(1);
				logger.debug("prodDatas3 : " + prodDatas);
				prodDatas.remove(0);
				logger.debug("prodDatas4 : " + prodDatas);
			}
		}
		logger.debug("prodDatas5 : " + prodDatas);
		
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
		
		// 쿠키에 상품 저장
		String prodCookie = String.join("DONTWORRYBEHAPPY", prodDatas);
		logger.debug("prodCookie : " + prodCookie);
		Cookie lateView = new Cookie("lateView", prodCookie);
		lateView.setPath(req.getContextPath());
		lateView.setMaxAge(60*60*24);
		resp.addCookie(lateView);
		
		
        
        // req.setAttribute 영역 ********************************************
        req.setAttribute("day", twoDaysLaterFormatted);
        req.setAttribute("week", twoDaysLaterDayOfWeek);
        
		req.setAttribute("cate1", cate1);
		req.setAttribute("cate2", cate2);
		req.setAttribute("no", prod.getProdNo());
		req.setAttribute("pg", pg);
		
		req.setAttribute("prod",   prod);
		req.setAttribute("cate",   cate);
		req.setAttribute("review", review);
		
		req.setAttribute("currentPage", currentPage);
		req.setAttribute("lastPageNum", lastPageNum);
		req.setAttribute("pageGroupStart", result[0]);
		req.setAttribute("pageGroupEnd", result[1]);
		
		req.setAttribute("latelyProduct", latelyProduct);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/product/view.jsp");
		dispatcher.forward(req, resp);
	}
}
