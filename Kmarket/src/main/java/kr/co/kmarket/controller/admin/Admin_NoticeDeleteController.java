package kr.co.kmarket.controller.admin;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.service.CsService;

@WebServlet("/admin/notice/delete.do")
public class Admin_NoticeDeleteController extends HttpServlet {

    private static final long serialVersionUID = 1232344352L;
    private Logger logger = LoggerFactory.getLogger(Admin_NoticeDeleteController.class);
    private CsService service = CsService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String no = req.getParameter("no");
        
	    service.deleteBoard(no);
	    
		RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/notice/list.jsp?group=1&pg=1");
		dispatcher.forward(req, resp);
    }
}
