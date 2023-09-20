package kr.co.kmarket.controller.product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;

import kr.co.kmarket.dto.CartDTO;
import kr.co.kmarket.service.CartService;

@WebServlet("/product/order.do")
public class OrderController extends HttpServlet{

	private static final long serialVersionUID = -4698136439681533256L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private CartService cartService = CartService.INSTANCE;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/product/order.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int result = 0;
		
		List<CartDTO> carts = new ArrayList<>();
		
		String uid = req.getParameter("uid");
		String[] selectedCartNos = req.getParameterValues("selectedCartNos");
		
		for(int i = 0; i < selectedCartNos.length; i++) {
			CartDTO dto = cartService.selectedCart(uid, selectedCartNos[i]);
			carts.add(dto);
		}
		logger.debug(uid);
		req.setAttribute("carts", carts);
		
		// insertOrder
		
		resp.sendRedirect("/Kmarket/product/order.do");
		logger.debug("redirect complete");
		/*
		result = 1;
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("result", result);
		resp.getWriter().print(jsonObject);
		*/
	}
}
