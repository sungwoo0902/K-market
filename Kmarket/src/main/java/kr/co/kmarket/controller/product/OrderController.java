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

import kr.co.kmarket.dao.MemberDAO;
import kr.co.kmarket.dto.CartDTO;
import kr.co.kmarket.dto.MemberDTO;
import kr.co.kmarket.dto.OrderDTO;
import kr.co.kmarket.service.CartService;
import kr.co.kmarket.service.MemberService;
import kr.co.kmarket.service.OrderService;

@WebServlet("/product/order.do")
public class OrderController extends HttpServlet{

	private static final long serialVersionUID = -4698136439681533256L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private CartService cartService = CartService.INSTANCE;
	private OrderService ordService = OrderService.INSTANCE;
	private MemberService memService = MemberService.INSTANCE;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/product/order.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int result = 0;
		
		List<CartDTO> carts = new ArrayList<>();
		OrderDTO order = new OrderDTO();
		MemberDTO member = new MemberDTO();
		
		String uid = req.getParameter("uid");
		String[] selectedCartNos = req.getParameterValues("selectedCartNos");
		String ordTotPrice= req.getParameter("ordTotPrice");
		String ordCount= req.getParameter("ordCount");
		String ordPrice= req.getParameter("ordPrice");
		String ordDiscount= req.getParameter("ordDiscount");
		String ordDelivery= req.getParameter("ordDelivery");
		String savePoint= req.getParameter("savePoint");
		int ordComplete = 0;
		
		order.setOrdUid(uid);
		order.setOrdTotPrice(ordTotPrice);
		order.setOrdCount(ordCount);
		order.setOrdPrice(ordPrice);
		order.setOrdDiscount(ordDiscount);
		order.setOrdDelivery(ordDelivery);
		order.setSavePoint(savePoint);
		order.setOrdComplete(ordComplete);
		
		for(int i = 0; i < selectedCartNos.length; i++) {
			CartDTO dto = cartService.selectedCart(uid, selectedCartNos[i]);
			carts.add(dto);
		}
		logger.debug(uid);
		req.setAttribute("carts", carts);
		
		// insertOrder로 uid, count, price, discount, delivery, point, total을 입력한다.
		ordService.insertOrder(order);
		
		/*  2023/09/21 01:00 현재 recipName default Value 오류. 배송지 정보까지 넣어야됨  */
		
		// insertOrder를 할 때 complete 값을 넣어 결제 상태를 표시해준다.
		// 위의 carts를 orderNo에 매치하여 orderItems에 하나씩 반복하여 넣어준다.
		// 최종적으로 selectOrder를 실행하여 주문서 출력
		
		
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
