package kr.co.kmarket.controller.product;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import kr.co.kmarket.dto.CartDTO;
import kr.co.kmarket.service.CartService;

@WebServlet("/product/insertCart.do")
public class InsertCartController extends HttpServlet{

	private static final long serialVersionUID = 3658446645325437045L;
	private CartService cartService = CartService.INSTANCE;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int result = 0;
		CartDTO dto = new CartDTO();
		
		String uid = req.getParameter("uid");
		String prodNo = req.getParameter("prodNo");
		String inputCount = req.getParameter("inputCount");
		String price = req.getParameter("price");
		String discount = req.getParameter("discount");
		String point = req.getParameter("point");
		String delivery = req.getParameter("delivery");
		String totalPrice = req.getParameter("totalPrice");
		//String target = req.getParameter("target");
		
		result = cartService.selectCart(uid, prodNo);
		
		logger.debug("prodNo : " + prodNo);
		logger.debug("uid : " + uid);
		
		dto.setUid(uid);
		dto.setProdNo(prodNo);
		dto.setCount(inputCount);
		dto.setCartPrice(price);
		dto.setDiscount(discount);
		dto.setPoint(point);
		dto.setDelivery(delivery);
		dto.setTotal(totalPrice);
		
		System.out.println("result :"+result);
		
		logger.debug(dto.toString());
		if(result == 0) {
			cartService.insertCart(dto);
		}else if(result == 1) {
			cartService.updateCart(dto);
			
		}
		
	}
}
