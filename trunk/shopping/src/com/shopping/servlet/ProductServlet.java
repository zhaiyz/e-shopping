package com.shopping.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.factory.ServiceFactory;
import com.shopping.vo.ProductVo;

public class ProductServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//取得命令参数
        String action = request.getParameter("action");
        
        //定义转发路径
        String path = "";
        
        //根据命令参数进行相应的操作
        if("show".equals(action)) {
        	//取得要显示商品的id
        	int id = Integer.parseInt(request.getParameter("id"));
        	
        	ProductVo product = new ProductVo();
        	product = ServiceFactory.getProductServiceInstance().findProductById(id);
        	
        	request.setAttribute("pro", product);
        	path = "user/product.jsp";
        	
        }
        
        //根据path进行跳转
	    request.getRequestDispatcher(path).forward(request, response);
	}

}
