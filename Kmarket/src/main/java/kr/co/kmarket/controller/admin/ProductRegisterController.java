package kr.co.kmarket.controller.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin/product/list.do")
public class ProductRegisterController extends HttpServlet{

	private static final long serialVersionUID = -8031409640344863676L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String category1 = req.getParameter("category1");
		String category2 = req.getParameter("category2");
		String prodName = req.getParameter("prodName");
		String descript = req.getParameter("descript");
		String company = req.getParameter("company");
		String price = req.getParameter("price");
		String discount = req.getParameter("discount");
		String point = req.getParameter("point");
		String stock = req.getParameter("stock");
		String delivery = req.getParameter("delivery");
		String thumb1 = req.getParameter("thumb1");
		String thumb2 = req.getParameter("thumb2");
		String thumb3 = req.getParameter("thumb3");
		String detail = req.getParameter("detail");
		String status = req.getParameter("status");
		String duty = req.getParameter("duty");
		String receipt = req.getParameter("receipt");
		String bizType = req.getParameter("bizType");
		String origin = req.getParameter("origin");
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/product/register.jsp");
		dispatcher.forward(req, resp);
	}
}
