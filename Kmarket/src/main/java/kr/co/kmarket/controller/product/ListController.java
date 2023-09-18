package kr.co.kmarket.controller.product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.dto.ProductDTO;
import kr.co.kmarket.service.ProductService;

@WebServlet("/product/list.do")
public class ListController extends HttpServlet{

	private static final long serialVersionUID = -2740152939344565075L;
	private ProductService prodService = ProductService.INSTANCE;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String success = req.getParameter("success");
		String cate1 = req.getParameter("cate1");
		String cate2 = req.getParameter("cate2");
		String type = req.getParameter("type");
		String pg = req.getParameter("pg");
		
		int total = 0;
		// 현재 페이지 번호
		int currentPage = prodService.getCurrentPage(pg);
		
		// 전체 게시물 갯수
		total = prodService.selectCountProductsByCate2(cate1, cate2);
		
		
		logger.info("selectCoutProductsByCate1 total : "+ total);
		
		// 마지막 페이지 번호
		int lastPageNum = prodService.getLastPageNum(total);
		
		logger.info("selectCoutProductsByCate1 lastPageNum : "+ lastPageNum);
		
		// 페이지 그룹 start, end 번호
		int[] result = prodService.getPageGroupNum(currentPage, lastPageNum);
		
		// 페이지 시작번호
		int pageStartNum = prodService.getPageStartNum(total, currentPage);
		
		// 시작 인덱스
		int start = prodService.getStartNum(currentPage);
		
		// 현재 페이지 게시물 조회
		List<ProductDTO> products = new ArrayList<>();
		products = prodService.selectProductsByCate2(cate1, cate2, start);
		
		req.setAttribute("success", success);
		req.setAttribute("products", products);
		req.setAttribute("total", total);
		req.setAttribute("cate1", cate1);
		req.setAttribute("cate2", cate2);
		
		req.setAttribute("currentPage", currentPage);
		req.setAttribute("lastPageNum", lastPageNum);
		req.setAttribute("pageGroupStart", result[0]);
		req.setAttribute("pageGroupEnd", result[1]);
		req.setAttribute("pageStartNum", pageStartNum+1);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/product/list.jsp?pg="+pg);
		dispatcher.forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
	}
	
}
