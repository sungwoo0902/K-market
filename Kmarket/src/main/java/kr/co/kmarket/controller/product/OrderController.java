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
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;

import kr.co.kmarket.dao.MemberDAO;
import kr.co.kmarket.dto.CartDTO;
import kr.co.kmarket.dto.CategoryDTO;
import kr.co.kmarket.dto.MemberDTO;
import kr.co.kmarket.dto.OrderDTO;
import kr.co.kmarket.dto.OrderItemDTO;
import kr.co.kmarket.service.CartService;
import kr.co.kmarket.service.CategoryService;
import kr.co.kmarket.service.MemberService;
import kr.co.kmarket.service.OrderService;

@WebServlet("/product/order.do")
public class OrderController extends HttpServlet{

	private static final long serialVersionUID = -4698136439681533256L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private CartService cartService = CartService.INSTANCE;
	private OrderService ordService = OrderService.INSTANCE;
	private MemberService memService = MemberService.INSTANCE;
	private CategoryService cateService = CategoryService.INSTANCE;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		MemberDTO sessUser = (MemberDTO) session.getAttribute("sessUser");
		String uid = sessUser.getUid();
		
		// 사이드바 영역 ****************************************************
		// 사이드 카테고리(cate1) 불러오기
		List<CategoryDTO> category1 = cateService.selectCate1s();
		req.setAttribute("category1", category1);
		
		// 사이드 카테고리(cate2) 불러오기
		List<CategoryDTO> allCate = cateService.selectAllCate();
		req.setAttribute("allCate", allCate);
		
		int ordNo = ordService.selectLastOrdNo(uid);
		
		logger.debug("uid :"+uid);
		logger.debug("ordNo :"+ordNo);
		
		OrderDTO order = ordService.selectOrder(ordNo, uid);
		MemberDTO member = memService.selectMemRecip(uid);
		// 해당 order의 상품들도 가져온다.
		List<OrderItemDTO> orderItems = ordService.selectOrderItems(uid, ordNo);
		// 현재 주문서의 상품들
		req.setAttribute("orderItems", orderItems);
		// 주문서
		req.setAttribute("order", order);
		// 멤버 정보
		req.setAttribute("member", member);
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/product/order.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int result = 0;
		
		List<CartDTO> carts = new ArrayList<>();
		OrderDTO order = new OrderDTO();
		MemberDTO member = new MemberDTO();
		
		HttpSession session = req.getSession();
		MemberDTO sessUser = (MemberDTO) session.getAttribute("sessUser");
		String uid = sessUser.getUid();
		String[] selectedCartNos = req.getParameterValues("selectedCartNos");
		String ordTotPrice= req.getParameter("ordTotPrice");
		String ordCount= req.getParameter("ordCount");
		String ordPrice= req.getParameter("ordPrice");
		String ordDiscount= req.getParameter("ordDiscount");
		String discount= req.getParameter("discountPercent");
		String ordDelivery= req.getParameter("ordDelivery");
		String savePoint= req.getParameter("savePoint");
		String prodNo = req.getParameter("prodNo");
		int ordComplete = 0;
		int ordPayment = 11;
		
		// 배송지 정보 불러와서 orderDTO에 같이 넣어준다.
		member = memService.selectMemRecip(uid);
		
		logger.debug("ordTotPrice :"+ordTotPrice);
		logger.debug("ordCount :"+ordCount);
		logger.debug("ordPrice :"+ordPrice);
		logger.debug("ordDiscount :"+ordDiscount);
		logger.debug("discount :"+discount);
		logger.debug("ordDelivery :"+ordDelivery);
		logger.debug("savePoint :"+savePoint);
		logger.debug("prodNo :"+prodNo);
		
		order.setOrdUid(uid);
		order.setOrdTotPrice(ordTotPrice);
		order.setOrdCount(ordCount);
		order.setOrdPrice(ordPrice);
		order.setOrdDiscount(ordDiscount);
		order.setOrdDelivery(ordDelivery);
		order.setSavePoint(savePoint);
		order.setOrdComplete(ordComplete);
		order.setRecipName(member.getName());
		order.setRecipHp(member.getHp());
		order.setRecipZip(member.getZip());
		order.setRecipAddr1(member.getAddr1());
		order.setRecipAddr2(member.getAddr2());
		order.setOrdPayment(ordPayment);
		
		if(selectedCartNos != null) {
			logger.debug("here...1");
			// cart창에서 선택한 목록만 불러와서 carts리스트에 담는다.
			for(int i = 0; i < selectedCartNos.length; i++) {
				CartDTO dto = cartService.selectedCart(uid, selectedCartNos[i]);
				carts.add(dto);
				logger.debug("here...2");
			}
			logger.debug("here...3");
			// insertOrder로 uid, count, price, discount, delivery, point, total을 입력한다.
			// order에 입력한다.
			ordService.insertOrder(order, uid);
			// 방금 넣은 order의 ordNo를 가져온다.
			int ordNo = ordService.selectLastOrdNo(uid);
			// 위의 carts를 orderNo에 매치하여 orderItems에 하나씩 반복하여 넣어준다.
			ordService.insertOrderItems(carts, ordNo);
			
			logger.debug("redirect complete");
			
			result = 1;
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("result", result);
			resp.getWriter().print(jsonObject);
		}else {
			logger.debug("here...4");
			// insertOrder로 uid, count, price, discount, delivery, point, total을 입력한다.
			// order에 입력한다.
			ordService.insertOrder(order, uid);
			// 방금 넣은 order의 ordNo를 가져온다.
			int ordNo = ordService.selectLastOrdNo(uid);
			
			OrderItemDTO orderItem = new OrderItemDTO();
			
			orderItem.setOrdNo(ordNo);
			orderItem.setProdNo(prodNo);
			orderItem.setCount(ordCount);
			orderItem.setPrice(ordPrice);
			orderItem.setDiscount(discount);
			orderItem.setPoint(savePoint);
			orderItem.setDelivery(ordDelivery);
			orderItem.setTotal(ordTotPrice);
			
			ordService.insertOrderItem(orderItem, ordNo);
			
			result = 1;
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("result", result);
			resp.getWriter().print(jsonObject);
			logger.debug("here...5");
		}
		
	}
}
