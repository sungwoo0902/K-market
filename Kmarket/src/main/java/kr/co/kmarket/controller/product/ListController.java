package kr.co.kmarket.controller.product;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.kmarket.dto.ProductDTO;
import kr.co.kmarket.service.ProductService;

@WebServlet("/product/list.do")
public class ListController extends HttpServlet{

	private static final long serialVersionUID = -2740152939344565075L;
	private ProductService prodService = ProductService.INSTANCE;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int total = 0;
		
		String success = req.getParameter("success");
		String cate1 = req.getParameter("cate1");
		String cate2 = req.getParameter("cate2");
		String type = req.getParameter("type");
		String pg = req.getParameter("pg");
		
		
		// 현재 페이지 번호
		int currentPage = prodService.getCurrentPage(pg);
		
		// 전체 게시물 갯수
		if(cate1 != null) {
			total = prodService.selectCountProductsByCate1(cate1);
		}
		total = prodService.selectCountProductsAll();
		
		// 마지막 페이지 번호
		int lastPageNum = prodService.getLastPageNum(total);
		
		// 페이지 그룹 start, end 번호
		int[] result = prodService.getPageGroupNum(currentPage, lastPageNum);
		
		// 페이지 시작번호
		int pageStartNum = prodService.getPageStartNum(total, currentPage);
		
		// 시작 인덱스
		int start = prodService.getStartNum(currentPage);
		
		// 현재 페이지 게시물 조회
		List<ProductDTO> products = prodService.selectProductsByCate1(cate1, start);
		
		req.setAttribute("success", success);
		req.setAttribute("produts", products);
		req.setAttribute("total", total);
		
		req.setAttribute("currentPage", currentPage);
		req.setAttribute("lastPageNum", lastPageNum);
		req.setAttribute("pageGroupStart", result[0]);
		req.setAttribute("pageGroupEnd", result[1]);
		req.setAttribute("pageStartNum", pageStartNum+1);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/product/list.jsp");
		dispatcher.forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
	}
	
}
