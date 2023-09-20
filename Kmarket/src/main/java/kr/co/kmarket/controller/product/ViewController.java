package kr.co.kmarket.controller.product;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
		
		int total = 0;
		
		// 현재 페이지 번호
		int currentPage = prodService.getCurrentPage(pg);
		
		// 전체 게시물 갯수
		total = reviewService.selectReivewCount(prodNo);
		
		// 마지막 페이지 번호
		int lastPageNum = prodService.getLastPageNum(total);
		
		// 페이지 그룹 start, end 번호
		int[] result = prodService.getPageGroupNum(currentPage, lastPageNum);
		
		// 페이지 시작번호
		int pageStartNum = prodService.getPageStartNum(total, currentPage);
		
		// 시작 인덱스
		int start = prodService.getStartNum(currentPage);
		
		
		// 선택한 상품 불러오기 / 선택한 상품 카테고리 NAV 설정
		ProductDTO  prod = prodService.selectProduct(cate1, cate2, prodNo);
		CategoryDTO cate = cateService.selectCate(cate1, cate2);
		logger.debug("prod : " + prod);
		logger.debug("cate : " + cate);
		
		// 선택 상품 리뷰 불러오기
		List<ReviewDTO> review = reviewService.selectReviews(prodNo, start);
		logger.debug("review : " + review);
		logger.debug("currentPage : " + currentPage);
		
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
		req.setAttribute("pageStartNum", pageStartNum+1);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/product/view.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
	}
}
