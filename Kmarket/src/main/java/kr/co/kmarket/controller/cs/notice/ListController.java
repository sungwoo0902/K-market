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
		req.setAttribute("board", "list");
		logger.info("doGet()...");
		String group = "1";

		// 데이터 수신
		String pg    = req.getParameter("pg");
		String cate1 = req.getParameter("cate1");
		String cate2 = req.getParameter("cate2");
		
		req.setAttribute("cate1", cate1);
					
		// 현재 페이지 번호
		int currentPage = service.getCurrentPage(pg);
		
		// 전체 게시물 갯수 
		int total = service.selectCountBoard(group, cate1, cate2);
		
		// 마지막 페이지 번호
		int lastPageNum = service.getLastPageNum(total);
				
		// 페이지 그룹 start, end 번호
		int[] result = service.getPageGroupNum(currentPage, lastPageNum);
		
		// 시작 인덱스
		int start = service.getStartNum(currentPage);
		
		// 글 조회
		List<CsDTO> notice_list = service.selectBoards(group, cate1.equals("1")?null:cate1, cate2, start);
		
		// cate1 이름, 설명 조회
		CsDTO cate = service.selectBoard_list(group, cate1);
		
		String success = req.getParameter("success");
		req.setAttribute("success", success);
		req.setAttribute("notice_lists", notice_list);
		req.setAttribute("cate", cate);
		
		
		req.setAttribute("currentPage", currentPage);
		req.setAttribute("lastPageNum", lastPageNum);
		req.setAttribute("pageGroupStart", result[0]);
		req.setAttribute("pageGroupEnd", result[1]);
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/cs/notice/list.jsp");
		dispatcher.forward(req, resp);
	}
}