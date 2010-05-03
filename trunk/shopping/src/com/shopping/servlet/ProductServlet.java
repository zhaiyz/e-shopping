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
		//ȡ���������
        String action = request.getParameter("action");
        
        //����ת��·��
        String path = "";
        
        //�����������������Ӧ�Ĳ���
        if("show".equals(action)) {
        	//ȡ��Ҫ��ʾ��Ʒ��id
        	int id = Integer.parseInt(request.getParameter("id"));
        	
        	ProductVo product = new ProductVo();
        	product = ServiceFactory.getProductServiceInstance().findProductById(id);
        	
        	request.setAttribute("pro", product);
        	path = "user/product.jsp";
        	
        }
        
        //����path������ת
	    request.getRequestDispatcher(path).forward(request, response);
	}

}
