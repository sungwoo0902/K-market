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

import kr.co.kmarket.dto.CartDTO;
import kr.co.kmarket.dto.CategoryDTO;
import kr.co.kmarket.dto.MemberDTO;
import kr.co.kmarket.dto.ProductDTO;
import kr.co.kmarket.service.CartService;
import kr.co.kmarket.service.CategoryService;
import kr.co.kmarket.service.ProductService;

@WebServlet("/product/cart.do")
public class CartController extends HttpServlet{

	private static final long serialVersionUID = 346919740737893000L;
	private ProductService prodService = ProductService.INSTANCE;
	private CartService cartService = CartService.INSTANCE;
	private CategoryService cateService = CategoryService.INSTANCE;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<CartDTO> carts = new ArrayList<>();
		
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
		
		/*
		if(sessUser.uid.equal) {
			
		}
		*/
		
		carts = cartService.selectCarts(uid);
		
		req.setAttribute("carts", carts);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/product/cart.jsp");
		dispatcher.forward(req, resp);
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int deleteResult = 0;
		
		HttpSession session = req.getSession();
		MemberDTO sessUser = (MemberDTO) session.getAttribute("sessUser");
		String uid = sessUser.getUid();
		
		String user = req.getParameter("uid");
		
		if(!uid.equals(user)) {
			return;
		}
		
		String[] selectedCartNos = req.getParameterValues("selectedCartNos");
		logger.debug("cart here1...");
		
		for(String no : selectedCartNos) {
			System.out.println(no);
		}
		
		
		if(selectedCartNos != null && selectedCartNos.length > 0) {
			int deleteLength = selectedCartNos.length;
			logger.debug("cart here2...");
			cartService.deleteCart(uid, selectedCartNos, deleteLength);
			deleteResult = 1;
		}
		
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("deleteResult", deleteResult);
		resp.getWriter().print(jsonObject);
		
		logger.debug("cart here5...");
	}
	
}
