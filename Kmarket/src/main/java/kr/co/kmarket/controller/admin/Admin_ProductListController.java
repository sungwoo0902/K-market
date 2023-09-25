package kr.co.kmarket.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.dto.MemberDTO;
import kr.co.kmarket.dto.ProductDTO;
import kr.co.kmarket.service.ProductService;

@WebServlet("/admin/product/list.do")
public class Admin_ProductListController extends HttpServlet {

	private static final long serialVersionUID = 1232344352L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private ProductService service = ProductService.INSTANCE;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		MemberDTO sessUser = (MemberDTO) session.getAttribute("sessUser");
		String seller = sessUser.getUid();
		int level = sessUser.getLevel();
		String search = req.getParameter("search");
		String searchCategory = req.getParameter("searchCategory");
		String pg    = req.getParameter("pg");
		
		req.setAttribute("sessUser", sessUser);
		req.setAttribute("seller", seller);
		req.setAttribute("search", search);
		req.setAttribute("searchCategory", searchCategory);
			
		if (level != 7 && seller != null && searchCategory != null) {
			
				switch (searchCategory) {
				
				case "search1": {
					
					// 현재 페이지 번호
					int currentPage = service.getCurrentPage(pg);
					
					// 전체 게시물 갯수 
					int total = service.selectCountProductsSearch1(seller, search);
					
					// 마지막 페이지 번호
					int lastPageNum = service.getLastPageNum(total);
					
					// 페이지 그룹 start, end 번호
					int[] result = service.getPageGroupNum(currentPage, lastPageNum);
					
					// 페이지 시작번호
					int pageStartNum = service.getPageStartNum(total, currentPage);
					
					// 시작 인덱스
					int start = service.getStartNum(currentPage);
				
					List<ProductDTO> products = service.selectProductsSearch1(start, seller, search);
					req.setAttribute("products", products);
					
					req.setAttribute("currentPage", currentPage);
					req.setAttribute("lastPageNum", lastPageNum);
					req.setAttribute("pageGroupStart", result[0]);
					req.setAttribute("pageGroupEnd", result[1]);
					req.setAttribute("pageStartNum", pageStartNum+1);
					
					RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/product/list.jsp?pg=" + pg);
					dispatcher.forward(req, resp);
					
					break;
				}
					
					
				case "search2": {
					
					// 현재 페이지 번호
					int currentPage = service.getCurrentPage(pg);
					
					// 전체 게시물 갯수 
					int total = service.selectCountProductsSearch2(seller, search);
					
					// 마지막 페이지 번호
					int lastPageNum = service.getLastPageNum(total);
					
					// 페이지 그룹 start, end 번호
					int[] result = service.getPageGroupNum(currentPage, lastPageNum);
					
					// 페이지 시작번호
					int pageStartNum = service.getPageStartNum(total, currentPage);
					
					// 시작 인덱스
					int start = service.getStartNum(currentPage);
					
					List<ProductDTO> products = service.selectProductsSearch2(start, seller, search);
					req.setAttribute("products", products);
					
					req.setAttribute("currentPage", currentPage);
					req.setAttribute("lastPageNum", lastPageNum);
					req.setAttribute("pageGroupStart", result[0]);
					req.setAttribute("pageGroupEnd", result[1]);
					req.setAttribute("pageStartNum", pageStartNum+1);
					
					RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/product/list.jsp?pg=" + pg);
					dispatcher.forward(req, resp);
					
					break;
				}
					
					
				case "search3": {
					
					// 현재 페이지 번호
					int currentPage = service.getCurrentPage(pg);
					
					// 전체 게시물 갯수 
					int total = service.selectCountProductsSearch3(seller, search);
					
					// 마지막 페이지 번호
					int lastPageNum = service.getLastPageNum(total);
					
					// 페이지 그룹 start, end 번호
					int[] result = service.getPageGroupNum(currentPage, lastPageNum);
					
					// 페이지 시작번호
					int pageStartNum = service.getPageStartNum(total, currentPage);
					
					// 시작 인덱스
					int start = service.getStartNum(currentPage);
					
					List<ProductDTO> products = service.selectProductsSearch3(start, seller, search);
					req.setAttribute("products", products);
					
					req.setAttribute("currentPage", currentPage);
					req.setAttribute("lastPageNum", lastPageNum);
					req.setAttribute("pageGroupStart", result[0]);
					req.setAttribute("pageGroupEnd", result[1]);
					req.setAttribute("pageStartNum", pageStartNum+1);
					
					RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/product/list.jsp?pg=" + pg);
					dispatcher.forward(req, resp);
					
					break;
				}
					
					
				case "search4": {
					
					// 현재 페이지 번호
					int currentPage = service.getCurrentPage(pg);
					
					// 전체 게시물 갯수 
					int total = service.selectCountProductsSearch4(seller, search);
					
					// 마지막 페이지 번호
					int lastPageNum = service.getLastPageNum(total);
					
					// 페이지 그룹 start, end 번호
					int[] result = service.getPageGroupNum(currentPage, lastPageNum);
					
					// 페이지 시작번호
					int pageStartNum = service.getPageStartNum(total, currentPage);
					
					// 시작 인덱스
					int start = service.getStartNum(currentPage);
					
					List<ProductDTO> products = service.selectProductsSearch4(start, seller, search);
					req.setAttribute("products", products);
					
					req.setAttribute("currentPage", currentPage);
					req.setAttribute("lastPageNum", lastPageNum);
					req.setAttribute("pageGroupStart", result[0]);
					req.setAttribute("pageGroupEnd", result[1]);
					req.setAttribute("pageStartNum", pageStartNum+1);
					
					RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/product/list.jsp?pg=" + pg);
					dispatcher.forward(req, resp);
					
					break;
				}
		
			}
				
		} else if(level == 7 && searchCategory != null) { 
			
				switch (searchCategory) {
				
				case "search1": {
					
					// 현재 페이지 번호
					int currentPage = service.getCurrentPage(pg);
					
					// 전체 게시물 갯수 
					int total = service.selectCountProductsAdminSearch1(search);
					
					// 마지막 페이지 번호
					int lastPageNum = service.getLastPageNum(total);
					
					// 페이지 그룹 start, end 번호
					int[] result = service.getPageGroupNum(currentPage, lastPageNum);
					
					// 페이지 시작번호
					int pageStartNum = service.getPageStartNum(total, currentPage);
					
					// 시작 인덱스
					int start = service.getStartNum(currentPage);
				
					List<ProductDTO> products = service.selectProductsAdminSearch1(start, search);
					req.setAttribute("products", products);
					
					req.setAttribute("currentPage", currentPage);
					req.setAttribute("lastPageNum", lastPageNum);
					req.setAttribute("pageGroupStart", result[0]);
					req.setAttribute("pageGroupEnd", result[1]);
					req.setAttribute("pageStartNum", pageStartNum+1);
					
					RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/product/list.jsp?pg=" + pg);
					dispatcher.forward(req, resp);
					
					break;
				}
					
					
				case "search2": {
					
					// 현재 페이지 번호
					int currentPage = service.getCurrentPage(pg);
					
					// 전체 게시물 갯수 
					int total = service.selectCountProductsAdminSearch2(search);
					
					// 마지막 페이지 번호
					int lastPageNum = service.getLastPageNum(total);
					
					// 페이지 그룹 start, end 번호
					int[] result = service.getPageGroupNum(currentPage, lastPageNum);
					
					// 페이지 시작번호
					int pageStartNum = service.getPageStartNum(total, currentPage);
					
					// 시작 인덱스
					int start = service.getStartNum(currentPage);
					
					List<ProductDTO> products = service.selectProductsAdminSearch2(start, search);
					req.setAttribute("products", products);
					
					req.setAttribute("currentPage", currentPage);
					req.setAttribute("lastPageNum", lastPageNum);
					req.setAttribute("pageGroupStart", result[0]);
					req.setAttribute("pageGroupEnd", result[1]);
					req.setAttribute("pageStartNum", pageStartNum+1);
					
					RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/product/list.jsp?pg=" + pg);
					dispatcher.forward(req, resp);
					
					break;
				}
					
					
				case "search3": {
					
					// 현재 페이지 번호
					int currentPage = service.getCurrentPage(pg);
					
					// 전체 게시물 갯수 
					int total = service.selectCountProductsAdminSearch3(search);
					
					// 마지막 페이지 번호
					int lastPageNum = service.getLastPageNum(total);
					
					// 페이지 그룹 start, end 번호
					int[] result = service.getPageGroupNum(currentPage, lastPageNum);
					
					// 페이지 시작번호
					int pageStartNum = service.getPageStartNum(total, currentPage);
					
					// 시작 인덱스
					int start = service.getStartNum(currentPage);
					
					List<ProductDTO> products = service.selectProductsAdminSearch3(start, search);
					req.setAttribute("products", products);
					
					req.setAttribute("currentPage", currentPage);
					req.setAttribute("lastPageNum", lastPageNum);
					req.setAttribute("pageGroupStart", result[0]);
					req.setAttribute("pageGroupEnd", result[1]);
					req.setAttribute("pageStartNum", pageStartNum+1);
					
					RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/product/list.jsp?pg=" + pg);
					dispatcher.forward(req, resp);
					
					break;
				}
					
					
				case "search4": {
					
					// 현재 페이지 번호
					int currentPage = service.getCurrentPage(pg);
					
					// 전체 게시물 갯수 
					int total = service.selectCountProductsAdminSearch4(search);
					
					// 마지막 페이지 번호
					int lastPageNum = service.getLastPageNum(total);
					
					// 페이지 그룹 start, end 번호
					int[] result = service.getPageGroupNum(currentPage, lastPageNum);
					
					// 페이지 시작번호
					int pageStartNum = service.getPageStartNum(total, currentPage);
					
					// 시작 인덱스
					int start = service.getStartNum(currentPage);
					
					List<ProductDTO> products = service.selectProductsAdminSearch4(start, search);
					req.setAttribute("products", products);
					
					req.setAttribute("currentPage", currentPage);
					req.setAttribute("lastPageNum", lastPageNum);
					req.setAttribute("pageGroupStart", result[0]);
					req.setAttribute("pageGroupEnd", result[1]);
					req.setAttribute("pageStartNum", pageStartNum+1);
					
					RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/product/list.jsp?pg=" + pg);
					dispatcher.forward(req, resp);
					
					break;
				}
			}	
		} else if(searchCategory == null) {
		// 현재 페이지 검색 게시물 조회
			
		// 현재 페이지 번호
		int currentPage = service.getCurrentPage(pg);
		
		// 전체 게시물 갯수 
		int total = service.selectCountProductsAll(seller, level);
		
		// 마지막 페이지 번호
		int lastPageNum = service.getLastPageNum(total);
		
		// 페이지 그룹 start, end 번호
		int[] result = service.getPageGroupNum(currentPage, lastPageNum);
		
		// 페이지 시작번호
		int pageStartNum = service.getPageStartNum(total, currentPage);
		
		// 시작 인덱스
		int start = service.getStartNum(currentPage);
		logger.debug("here3");
		
		// 현재 페이지 게시물 조회	
		List<ProductDTO> products = service.selectProductsAll(start, seller, level);
		req.setAttribute("products", products);
		
		logger.debug("here4");
		req.setAttribute("currentPage", currentPage);
		req.setAttribute("lastPageNum", lastPageNum);
		req.setAttribute("pageGroupStart", result[0]);
		req.setAttribute("pageGroupEnd", result[1]);
		req.setAttribute("pageStartNum", pageStartNum+1);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/product/list.jsp?pg=" + pg);
		dispatcher.forward(req, resp);
		logger.debug("here5");
		}
	}		
}


		
	

