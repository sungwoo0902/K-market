package kr.co.kmarket.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.kmarket.dto.CategoryDTO;
import kr.co.kmarket.dto.ProductDTO;
import kr.co.kmarket.service.CategoryService;
import kr.co.kmarket.service.ProductService;

@WebServlet("/admin/product/register.do")
public class ProductRegisterController extends HttpServlet{

	private static final long serialVersionUID = -8031409640344863676L;
	private CategoryService cateService = CategoryService.INSTANCE;
	private ProductService prodService = ProductService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<CategoryDTO> cate1s = cateService.selectCate1s();
		
		req.setAttribute("cate1s", cate1s);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/product/register.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String prodCate1	= req.getParameter("prodCate1");
		String prodCate2	= req.getParameter("prodCate2");
		String prodName 	= req.getParameter("prodName");
		String descript 	= req.getParameter("descript");
		String company 		= req.getParameter("company");
		String seller		= req.getParameter("seller");
		String price 		= req.getParameter("price");
		String discount 	= req.getParameter("discount");
		String point 		= req.getParameter("point");
		String stock 		= req.getParameter("stock");
		String delivery 	= req.getParameter("delivery");
		String thumb1 		= req.getParameter("thumb1");
		String thumb2 		= req.getParameter("thumb2");
		String thumb3 		= req.getParameter("thumb3");
		String detail 		= req.getParameter("detail");
		String status 		= req.getParameter("status");
		String duty 		= req.getParameter("duty");
		String receipt 		= req.getParameter("receipt");
		String bizType 		= req.getParameter("bizType");
		String origin 		= req.getParameter("origin");
		
		ProductDTO dto = new ProductDTO();
		
		dto.setProdName(prodName);
		dto.setDescript(descript);
		dto.setCompany(company);
		dto.setSeller(seller);
		dto.setPrice(price);
		dto.setDiscount(discount);
		dto.setPoint(point);
		dto.setStock(stock);
		dto.setDelivery(delivery);
		dto.setThumb1(thumb1);
		dto.setThumb2(thumb2);
		dto.setThumb3(thumb3);
		dto.setDetail(detail);
		dto.setStatus(status);
		dto.setDuty(duty);
		dto.setReceipt(receipt);
		dto.setBizType(bizType);
		dto.setOrigin(origin);
		
		prodService.insertProduct(dto);
		
	}
}
