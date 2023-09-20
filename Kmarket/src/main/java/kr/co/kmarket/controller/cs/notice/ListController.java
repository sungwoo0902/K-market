package kr.co.kmarket.controller.cs.notice;

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

import kr.co.kmarket.dto.CsDTO;
import kr.co.kmarket.service.CsService;

@WebServlet("/cs/notice/list.do")
public class ListController extends HttpServlet {

	private static final long serialVersionUID = -479618598042292094L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private CsService service = CsService.INSTANCE;

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 데이터 수신
		String pg = req.getParameter("pg");
		String group = req.getParameter("group");
		String cate1 = req.getParameter("cate1");
		String cate2 = req.getParameter("cate2");
					
		// 현재 페이지 번호
		int currentPage = service.getCurrentPage(pg);
		logger.debug("currentPage :" +currentPage);			
		
		// 전체 게시물 갯수 
		int total = service.selectCountBoard("1", cate1);
		logger.debug("total :" +total);
		
		// 마지막 페이지 번호
		int lastPageNum = service.getLastPageNum(total);
		logger.debug("lastPageNum :" +lastPageNum);
				
		// 페이지 그룹 start, end 번호
		int[] result = service.getPageGroupNum(currentPage, lastPageNum);
		logger.debug("start :" +result[0]);
		logger.debug("result :" +result[1]);
		
		// 페이지 시작번호
		int pageStartNum = service.getPageStartNum(total, currentPage);
		logger.debug("pageStartNum :" +pageStartNum);
					
		// 시작 인덱스
		int start = service.getStartNum(currentPage);
		logger.debug("start :" + start);
		
		// 글 조회
		List<CsDTO> article_notice_list = service.selectBoards("1", null, null, start);
		
		// cate1 이름, 설명 조회
		CsDTO notice_name_dis = service.selectBoard_list("1", cate1);
		
		req.setAttribute("board", "list");
		
		String success = req.getParameter("success");
		req.setAttribute("success", success);
		req.setAttribute("articles_notice_lists", article_notice_list);
		req.setAttribute("notice_name_dis", notice_name_dis);
		
		
		req.setAttribute("currentPage", currentPage);
		req.setAttribute("lastPageNum", lastPageNum);
		req.setAttribute("pageGroupStart", result[0]);
		req.setAttribute("pageGroupEnd", result[1]);
		req.setAttribute("pageStartNum", pageStartNum+1);
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/cs/notice/list.jsp");
		dispatcher.forward(req, resp);
	}
}