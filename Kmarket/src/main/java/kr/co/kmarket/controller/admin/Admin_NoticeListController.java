package kr.co.kmarket.controller.admin;

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
import kr.co.kmarket.dto.CsDTO;
import kr.co.kmarket.service.CsService;
import kr.co.kmarket.service.CategoryService;

@WebServlet("/admin/notice/list.do")
public class Admin_NoticeListController extends HttpServlet {

	private static final long serialVersionUID = 1232344352L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private CsService service = CsService.INSTANCE;
	private CategoryService cateService = CategoryService.INSTANCE;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String boardCate2 = req.getParameter("boardCate2");
		
		List<CategoryDTO> cate1s = cateService.selectCate1s();
		List<CategoryDTO> cate2s = cateService.selectCate2s(boardCate2);
		//List<CsDTO> notices = service.selectBoards();
		
		req.setAttribute("cate1s", cate1s);
		req.setAttribute("cate2s", cate2s);
		//req.setAttribute("notices", notices);
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/notice/list.jsp");
		dispatcher.forward(req, resp);
	}
	
}
