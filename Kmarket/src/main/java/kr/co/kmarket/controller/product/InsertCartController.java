package kr.co.kmarket.controller.product;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;

import kr.co.kmarket.dto.CartDTO;
import kr.co.kmarket.dto.MemberDTO;
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
		
		HttpSession session = req.getSession();
		MemberDTO sessUser = (MemberDTO) session.getAttribute("sessUser");
		String uid = sessUser.getUid();
		String prodNo = req.getParameter("prodNo");
		String inputCount = req.getParameter("inputCount");
		String price = req.getParameter("price");
		String discount = req.getParameter("discount");
		String point = req.getParameter("point");
		String delivery = req.getParameter("delivery");
		String totalPrice = req.getParameter("totalPrice");
		String cartResult = req.getParameter("cartResult");
		//String target = req.getParameter("target");
		
		logger.debug(cartResult);
		
		dto.setUid(uid);
		dto.setProdNo(prodNo);
		dto.setCount(inputCount);
		dto.setCartPrice(price);
		dto.setDiscount(discount);
		dto.setPoint(point);
		dto.setDelivery(delivery);
		dto.setTotal(totalPrice);
		
		result = cartService.selectCountCart(uid, prodNo);
		
		// cartResult는 view.jsp에서 보내는 값. 최초로 실행할 때는 null로 설정됨
		// selectCart를 실행하여 나온 값을 result라는 json 데이터로 설정하여 view.jsp로 보냄
		// (장바구니 버튼 눌렀을 때 최초 한번 실행)
		if(cartResult == null) {
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("result", result);
			resp.getWriter().print(jsonObject);
			logger.debug("result 전송 성공");
		// 위에서 result라는 json 데이터를 보내자마자 cartResult라는 json데이터가 1 또는 0으로 설정됨
		}else if(cartResult != null) {
			// (클라이언트가 장바구니에 상품이 있는데 추가하겠다고 한 상태)
			// 받아온 cartResult의 값이 1이라면 updataCart로 해당 상품을 장바구니에 추가
			if(result == 1 & cartResult.equals("1")) {
				cartService.updateCart(dto);
				logger.debug("here...3");
				logger.debug("updateCart 성공");
			// (클라이언트가 장바구니에 상품 수량 추가 거절)
			// 받아온 cartResult의 값이 0이라면 return으로 아무것도 실행하지 않는다.
			}else if(result == 1 & cartResult.equals("0")) {
				logger.debug("here...4");
				return;
			}
		}
	
		logger.debug("here...5");
		
		// 해당 상품이 장바구니에 없다면 insertCart 실행
		if(result == 0) {
			cartService.insertCart(dto);
			logger.debug("here...6");
		}
		logger.debug(dto.toString());
	}
}
