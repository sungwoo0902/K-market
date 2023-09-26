package kr.co.kmarket.controller.admin;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.service.CsService;
import kr.co.kmarket.service.ProductService;

@WebServlet("/admin/product/selcteDelete.do")
public class Admin_ProductSelectDeleteController extends HttpServlet {

    private static final long serialVersionUID = 1232344352L;
    private Logger logger = LoggerFactory.getLogger(Admin_ProductSelectDeleteController.class);
    private ProductService service = ProductService.INSTANCE;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] checkBoxArr = req.getParameterValues("checkBoxArr");
        
        if (checkBoxArr != null && checkBoxArr.length % 2 == 0) {
         
            for (int i = 0; i < checkBoxArr.length; i += 2) {
                String seller = checkBoxArr[i];
                String prodNo = checkBoxArr[i + 1];
                
                service.deleteProduct(seller, prodNo);

            }
        }
    }
}
