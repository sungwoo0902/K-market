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

import kr.co.kmarket.dto.CsDTO;
import kr.co.kmarket.service.CsService;

@WebServlet("/admin/index.do")
public class Admin_IndexController extends HttpServlet {

	private static final long serialVersionUID = 1232344352L;
	private CsService service = CsService.INSTANCE;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		List<CsDTO> latests1 = service.selectLatests(1, 5);
		List<CsDTO> latests2 = service.selectLatests(3, 5);
		
		req.setAttribute("latests1", latests1);
		req.setAttribute("latests2", latests2);
		
		logger.debug("latests1" + latests1.toString());
		logger.debug("latests2" + latests2.toString());
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/index.jsp");
		dispatcher.forward(req, resp);
	}
	
}
