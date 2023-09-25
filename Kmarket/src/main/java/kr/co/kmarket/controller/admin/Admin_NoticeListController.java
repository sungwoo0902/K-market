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

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import kr.co.kmarket.dto.CategoryDTO;
import kr.co.kmarket.dto.CsDTO;
import kr.co.kmarket.service.CsService;
import kr.co.kmarket.service.CategoryService;

@WebServlet("/admin/notice/list.do")
public class Admin_NoticeListController extends HttpServlet {

	private static final long serialVersionUID = 1232344352L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private CsService service = CsService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String pg = req.getParameter("pg");
		String group = req.getParameter("group");
		String cate1 = req.getParameter("cate1");

		// 현재 페이지 번호
		int currentPage = service.getCurrentPage(pg);
		
		// 전체 게시물 갯수 
		int total = service.selectCountBoard(group, cate1, null);
		
		// 마지막 페이지 번호
		int lastPageNum = service.getLastPageNum(total);
		
		// 페이지 그룹 start, end 번호
		int[] result = service.getPageGroupNum(currentPage, lastPageNum);
		
		// 페이지 시작번호
		int pageStartNum = service.getPageStartNum(total, currentPage);
		
		// 시작 인덱스
		int start = service.getStartNum(currentPage);
		
		List<CsDTO> cate1List = service.selectCate1ListWhenGroupChoose(group);
		logger.debug("cate1List : " + cate1List.toString());
		req.setAttribute("cate1List", cate1List);
		
		List<CsDTO> notices = service.selectBoards(group, cate1, null, start);
		
		req.setAttribute("group", group);
		req.setAttribute("cate1", cate1);
		req.setAttribute("notices", notices);
		
		logger.debug("group : " + group);
		logger.debug("cate1 : " + cate1);
		logger.debug("notices : " + notices);
		req.setAttribute("currentPage", currentPage);
		req.setAttribute("lastPageNum", lastPageNum);
		req.setAttribute("pageGroupStart", result[0]);
		req.setAttribute("pageGroupEnd", result[1]);
		req.setAttribute("pageStartNum", pageStartNum+1);

				
		RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/notice/list.jsp?group=" + group + "&pg=" + pg + "&cate1=" + cate1);
		dispatcher.forward(req, resp);

	}
	
	
}
