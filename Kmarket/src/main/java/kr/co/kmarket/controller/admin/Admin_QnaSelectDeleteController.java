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

@WebServlet("/admin/qna/seletDelete.do")
public class Admin_QnaSelectDeleteController extends HttpServlet {

    private static final long serialVersionUID = 1232344352L;
    private Logger logger = LoggerFactory.getLogger(Admin_QnaSelectDeleteController.class);
    private CsService service = CsService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] checkBoxArr = req.getParameterValues("checkBoxArr");
        logger.debug("checkBoxArr : " + Arrays.toString(checkBoxArr));
        
        if (checkBoxArr != null && checkBoxArr.length != 0) {
  
	            for (int i = 0; i < checkBoxArr.length; i += 1) {
	                String no = checkBoxArr[i];            
	            logger.debug("no : " + no);
	            
	            service.deleteBoard(no);
            }
        }
    }
}

