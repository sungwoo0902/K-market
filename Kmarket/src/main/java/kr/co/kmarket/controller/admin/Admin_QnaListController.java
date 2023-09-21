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

@WebServlet("/admin/qna/list.do")
public class Admin_QnaListController extends HttpServlet {

	private static final long serialVersionUID = 1232344352L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private CsService service = CsService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String pg = req.getParameter("pg");
		String group = req.getParameter("group");
		String cate1 = req.getParameter("cate1");
		String cate2 = req.getParameter("cate2");

		// 현재 페이지 번호
		int currentPage = service.getCurrentPage(pg);
		
		// 전체 게시물 갯수 
		int total = service.selectCountBoard(group, cate1, cate2);
		
		// 마지막 페이지 번호
		int lastPageNum = service.getLastPageNum(total);
		
		// 페이지 그룹 start, end 번호
		int[] result = service.getPageGroupNum(currentPage, lastPageNum);
		
		// 페이지 시작번호
		int pageStartNum = service.getPageStartNum(total, currentPage);
		
		// 시작 인덱스
		int start = service.getStartNum(currentPage);
		
		req.setAttribute("group", group);
		req.setAttribute("cate1", cate1);
		req.setAttribute("cate2", cate2);
		
		logger.debug("group : " + group);
		logger.debug("cate1 : " + cate1);
		logger.debug("cate2 : " + cate2);
		
		req.setAttribute("currentPage", currentPage);
		req.setAttribute("lastPageNum", lastPageNum);
		req.setAttribute("pageGroupStart", result[0]);
		req.setAttribute("pageGroupEnd", result[1]);
		req.setAttribute("pageStartNum", pageStartNum+1);
		
		List<CsDTO> cate1List = service.selectCate1ListWhenGroupChoose(group);
		logger.debug("cate1List : " + cate1List.toString());
		req.setAttribute("cate1List", cate1List);
		
		// 소분류 리스트 가져오기
		List<CsDTO> cate2List = service.selectCate2ListWhenCate1Choose(cate1);
		logger.debug("cate2List : " + cate2List);
		req.setAttribute("cate2List", cate2List);
		
		
		List<CsDTO> qnas = service.selectBoards(group, cate1, cate2, start);
		req.setAttribute("qnas", qnas);
		logger.debug("qnas : " + qnas);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/qna/list.jsp?group" + group + "&cate1" + cate1 + "cate2" + cate2 + "&pg" + pg);
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("doPost()...");
		
		// 대분류 선택시 소분류 가져오기.
		String jsonCate2 = req.getParameter("jsonCate2");

		List<CsDTO> jsonCate3 = service.selectCate2ListWhenCate1Choose(jsonCate2);

		logger.debug("jsonCate2 : " + jsonCate2);
		logger.debug("jsonCate3 : " + jsonCate3.toString());
		
		Gson gson = new Gson();
		String json = gson.toJson(jsonCate3);
		
		JsonObject jsonObject = new JsonObject();
		jsonObject.add("categorys", gson.toJsonTree(jsonCate3));
		
		resp.setContentType("application/json; charset=UTF-8");
		
		resp.getWriter().write(jsonObject.toString());
		// --------------------------------------- json end...


	}
	
}
