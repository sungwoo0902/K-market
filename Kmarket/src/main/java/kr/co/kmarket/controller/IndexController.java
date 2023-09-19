package kr.co.kmarket.controller;

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

import kr.co.kmarket.dto.CategoryDTO;
import kr.co.kmarket.dto.MemberDTO;
import kr.co.kmarket.service.CategoryService;

@WebServlet(value= {"", "/index.do"})
public class IndexController extends HttpServlet{

	private static final long serialVersionUID = 5783589879373746478L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private CategoryService service = CategoryService.INSTANCE;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		MemberDTO sessUser = (MemberDTO) session.getAttribute("sessUser");
		req.setAttribute("sessUser", sessUser);
		logger.debug("sessUser : " + sessUser);
		
		// 사이드 카테고리(cate1) 불러오기
		List<CategoryDTO> category1 = service.selectCate1s();
		req.setAttribute("category1", category1);
		
		// 사이드 카테고리(cate2) 불러오기
		List<CategoryDTO> allCate = service.selectAllCate();
		req.setAttribute("allCate", allCate);
		
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
		dispatcher.forward(req, resp);
	}
}
