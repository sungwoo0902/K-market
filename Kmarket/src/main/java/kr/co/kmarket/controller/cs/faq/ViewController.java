package kr.co.kmarket.controller.cs.faq;

import java.io.IOException;

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

/**
 *	작업시작일 : 2023/09/18
 *	작업종료일 : 2023/09/18
 *	작업자 : 한상민
 *  내용 : cs 페이지 모듈화 및 view페이지 구현
 */

@WebServlet("/cs/faq/view.do")
public class ViewController extends HttpServlet {

	private static final long serialVersionUID = -3406996618704628927L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private CsService service = CsService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("doGet()...");
		
		String cate1 = req.getParameter("cate1");
		String no    = req.getParameter("no");
		String group = "2";
		logger.debug("cate1 : " + cate1);
		logger.debug("no    : " + no);
		
		// cate1_name 조회
		CsDTO cate = service.selectBoard_list(group, cate1);

		req.setAttribute("board", "view");
		req.setAttribute("cate1", cate1);
		req.setAttribute("cate", cate);
		
		CsDTO dto = service.selectBoard(no);
		logger.debug("dto : " + dto);
		req.setAttribute("faq", dto);
		
		if(dto.getGroup() == 2) {
			RequestDispatcher dispatcher = req.getRequestDispatcher("/cs/faq/view.jsp");
			dispatcher.forward(req, resp);
			
		}else {
			resp.sendRedirect("./list.do?cate1="+cate1+"&success=101");
		}
	}
}