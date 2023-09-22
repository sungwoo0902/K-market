package kr.co.kmarket.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oreilly.servlet.MultipartRequest;

import kr.co.kmarket.dto.CategoryDTO;
import kr.co.kmarket.dto.ProductDTO;
import kr.co.kmarket.service.CategoryService;
import kr.co.kmarket.service.ProductService;

@WebServlet("/admin/product/register.do")
public class Admin_ProductRegisterController extends HttpServlet{

	private static final long serialVersionUID = -8031409640344863676L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
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
		
	
		String cate1 = req.getParameter("cate1");
		String cate2 = req.getParameter("cate2");
		logger.debug("cate1 : " + cate1);
		logger.debug("cate2 : " + cate2);

		
		MultipartRequest mr = prodService.uploadFile(req, cate1, cate2);
		
		String prodCate1	= mr.getParameter("prodCate1");
		String prodCate2	= mr.getParameter("prodCate2");
		String prodName 	= mr.getParameter("prodName");
		String descript 	= mr.getParameter("descript");
		String company 		= mr.getParameter("company");
		String seller		= mr.getParameter("seller");
		String price 		= mr.getParameter("price");
		String discount 	= mr.getParameter("discount");
		String point 		= mr.getParameter("point");
		String stock 		= mr.getParameter("stock");
		String delivery 	= mr.getParameter("delivery");
		String thumb1 		= mr.getOriginalFileName("thumb1");
		String thumb2 		= mr.getOriginalFileName("thumb2");
		String thumb3 		= mr.getOriginalFileName("thumb3");
		String detail 		= mr.getOriginalFileName("detail");
		String status 		= mr.getParameter("status");
		String duty 		= mr.getParameter("duty");
		String receipt 		= mr.getParameter("receipt");
		String bizType 		= mr.getParameter("bizType");
		String origin 		= mr.getParameter("origin");
		String ip			= req.getRemoteAddr();
		
		
		logger.info(prodCate1);
		logger.info(prodCate2);
		logger.info(prodName);
		logger.info(descript);
		logger.info(company);
		logger.info(seller);
		logger.info(price);
		logger.info(discount);
		logger.info(point);
		logger.info(stock);
		logger.info(delivery);
		logger.info(thumb1);
		logger.info(thumb2);
		logger.info(thumb3);
		logger.info(detail);
		logger.info(status);
		logger.info(duty);
		logger.info(receipt);
		logger.info(bizType);
		logger.info(origin);
		logger.info(ip);
		// 파일을 저장할 경로
		String path 		= prodService.getFilePath(req,prodCate1,prodCate2);
		
		ProductDTO dto = new ProductDTO(path);
		
		dto.setProdCate1(prodCate1);
		dto.setProdCate2(prodCate2);
		dto.setProdName(prodName);
		dto.setDescript(descript);
		dto.setCompany(company);
		dto.setSeller(seller);
		dto.setPrice(price);
		dto.setDiscount(discount);
		dto.setPoint(point);
		dto.setStock(stock);
		dto.setDelivery(delivery);
		dto.setThumb1ForRename(thumb1);
		dto.setThumb2ForRename(thumb2);
		dto.setThumb3ForRename(thumb3);
		dto.setDetailForRename(detail);
		dto.setStatus(status);
		dto.setDuty(duty);
		dto.setReceipt(receipt);
		dto.setBizType(bizType);
		dto.setOrigin(origin);
		dto.setIp(ip);
		
		logger.info(dto.getThumb1());
		logger.info(dto.getThumb2());
		logger.info(dto.getThumb3());
		logger.info(dto.getDetail());
		
		prodService.insertProduct(dto);
		
		resp.sendRedirect("./register.do");
	}
}
