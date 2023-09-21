package kr.co.kmarket.controller.cs.notice;

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

@WebServlet("/cs/notice/view.do")
public class ViewController extends HttpServlet {

	private static final long serialVersionUID = 279887611913000009L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private CsService service = CsService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("doGet()...");
		
		String cate1 = req.getParameter("cate1");
		String no    = req.getParameter("no");
		logger.debug("cate1 : " + cate1);
		logger.debug("no    : " + no);
		
		req.setAttribute("cate1", cate1);
		req.setAttribute("board", "view");
		
		CsDTO dto = service.selectBoard(no);
		logger.debug("dto : " + dto);
		req.setAttribute("notice", dto);
		
		if(dto.getGroup() == 1) {
			RequestDispatcher dispatcher = req.getRequestDispatcher("/cs/notice/view.jsp");
			dispatcher.forward(req, resp);
			
		}else {
			resp.sendRedirect("./list.do?cate1="+cate1+"&success=101");
		}
	}
}