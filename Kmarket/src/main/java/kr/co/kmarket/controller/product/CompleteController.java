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

import kr.co.kmarket.dto.CategoryDTO;
import kr.co.kmarket.dto.MemberDTO;
import kr.co.kmarket.dto.OrderDTO;
import kr.co.kmarket.dto.OrderItemDTO;
import kr.co.kmarket.service.CategoryService;
import kr.co.kmarket.service.MemberService;
import kr.co.kmarket.service.OrderService;

@WebServlet("/product/complete.do")
public class CompleteController extends HttpServlet{

	private static final long serialVersionUID = -2103618121798017076L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private OrderService ordService = OrderService.INSTANCE;
	private MemberService memService = MemberService.INSTANCE;
	private CategoryService cateService = CategoryService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<OrderItemDTO> orderItems = new ArrayList<>();
		OrderDTO order = new OrderDTO();
		
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
		
		orderItems = ordService.selectOrderItems(uid, ordNo);
		order = ordService.selectOrder(ordNo, uid);
		
		order.setOrdPaymentName(order.getOrdPayment());
		
		int savePoint = order.getSavePoint();
		
		if(order.getOrdComplete() == 2) {
			memService.plusMemberPoint(savePoint, uid);
		}
		
		req.setAttribute("orderItems", orderItems);
		req.setAttribute("order", order);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/product/complete.jsp");
		dispatcher.forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int result = 0;
		OrderDTO order = new OrderDTO();
		
		HttpSession session = req.getSession();
		MemberDTO sessUser = (MemberDTO) session.getAttribute("sessUser");
		String uid = sessUser.getUid();
		
		String usedPoint = req.getParameter("usedPoint");
		String completeTotPrice = req.getParameter("completeTotPrice");
		String recipName = req.getParameter("recipName");
		String recipHp = req.getParameter("recipHp");
		String recipZip = req.getParameter("recipZip");
		String recipAddr1 = req.getParameter("recipAddr1");
		String recipAddr2 = req.getParameter("recipAddr2");
		String ordCompletePayment = req.getParameter("ordCompletePayment");
		String ordComplete = req.getParameter("ordComplete");
		String savePoint = req.getParameter("savePoint");
		
		logger.debug("ordCompletePayment : "+ ordCompletePayment);
		
		int ordNo = ordService.selectLastOrdNo(uid);
		
		order.setUsedPoint(usedPoint);
		order.setOrdTotPrice(completeTotPrice);
		order.setRecipName(recipName);
		order.setRecipHp(recipHp);
		order.setRecipZip(recipZip);
		order.setRecipAddr1(recipAddr1);
		order.setRecipAddr2(recipAddr2);
		order.setOrdPayment(ordCompletePayment);
		order.setOrdComplete(ordComplete);
		
		ordService.updateOrder(order, ordNo);
		
		if(!usedPoint.equals("0")) {
			memService.minusMemberPoint(usedPoint, uid);
		}
		
		if(ordComplete.equals("1")) {
			result = 1;
		}else if(ordComplete.equals("2")) {
			ordService.insertPoint(uid, ordNo, savePoint);
			result = 2;
		}
		
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("result", result);
		resp.getWriter().print(jsonObject);
	}
}
