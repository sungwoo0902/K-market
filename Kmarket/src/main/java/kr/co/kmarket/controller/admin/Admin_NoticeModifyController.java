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

import kr.co.kmarket.dto.CsDTO;
import kr.co.kmarket.service.CsService;

@WebServlet("/admin/notice/modify.do")
public class Admin_NoticeModifyController extends HttpServlet {
	
	private static final long serialVersionUID = 4487506858897269883L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private CsService service = CsService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		logger.info("doGet()...");
		
		String group = req.getParameter("group");
		String no = req.getParameter("no");
		
		req.setAttribute("board", "write");
		
		// 글 작성시 대분류 리스트 가져오기.
		List<CsDTO> cate1List = service.selectCate1ListWhenGroupChoose(group);
		logger.debug("cate1List : " + cate1List.toString());
		req.setAttribute("cate1List", cate1List);

		CsDTO modify = service.selectBoard(no);	
		req.setAttribute("modify", modify);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/notice/modify.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		logger.info("doPost()...");

			String boardCate2 = req.getParameter("boardCate2");
			String title      = req.getParameter("title");
			String content    = req.getParameter("content");
			String no    = req.getParameter("no");
			logger.debug("boardCate2 : " + boardCate2);
			logger.debug("title      : " + title);
			logger.debug("content    : " + content);
			logger.debug("no    : " + no);
			
			CsDTO dto = new CsDTO();
			dto.setCate1(boardCate2);
			dto.setTitle(title);
			dto.setContent(content);
			dto.setNo(no);
			logger.debug("dto : " + dto);
			
			// 작성한 글 DB 등록
			service.updateNotice(dto);
			
			resp.sendRedirect("/Kmarket/admin/notice/view.do?group=1&no="+no);
	
		}

}

