<%@ page contentType="text/html;charset=utf-8"%>
<jsp:directive.page import="com.shopping.vo.ProductVo"/>
<%@ include file = "user/header.jsp" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
		<title>E网情深─网上商城</title>	
	</head>
	<body>
		<%
			List<ProductVo> recommenList = (ArrayList)request.getAttribute("recommenList");
			List<ProductVo> newProductList = (ArrayList)request.getAttribute("newProductList");
		 %>
		 
		 <table width="100%">
		 	<tr>
		 		<td>推荐商品</td>
		 	</tr>
		 	<tr>
			 	<td style="border:1px solid blue;" align="center">
				 	<div id="demo">
						<div id="indemo">
							<div id="demo1">
						 	<%
						 	    for(int i=0;i<recommenList.size();i++){
						 		    ProductVo product = recommenList.get(i);
						    %>		 			 	
						 		
						 		<a href="/shopping/product?action=show&id=<%=product.getProId()%>" >
					                <img src="<%=request.getContextPath()%>/images/<%=product.getImageUrl() %>" title="<%=product.getProDesc() %>" width="120" height="120" border="0" />
						 		</a>
						 		
						 	<%
						 	    }
						 	%>		 	
						 	</div>
					 		<div id="demo2"></div>
					 	</div>
				 	</div>
				 	<script>
						<!--
						var speed=10; //数字越大速度越慢
						var tab=document.getElementById("demo");
						var tab1=document.getElementById("demo1");
						var tab2=document.getElementById("demo2");
						tab2.innerHTML=tab1.innerHTML;
						function Marquee(){
							if(tab2.offsetWidth-tab.scrollLeft<=0)
								tab.scrollLeft-=tab1.offsetWidth
							else{
								tab.scrollLeft++;
							}
						}
						var MyMar=setInterval(Marquee,speed);
						tab.onmouseover=function() {clearInterval(MyMar)};
						tab.onmouseout=function() {MyMar=setInterval(Marquee,speed)};
						-->
					</script>
			 	</td>	
		 	</tr>
		 </table>
	
		 <table width="100%">
		 	<tr>
		 		<td>新品上架</td>
		 	</tr>
		 	<tr>
        <th>图片</th>
        <th>商品名称</th>
        <th>原价</th>
        <th>会员价</th>
        <th>操作</th>
    		</tr>
    		<% for(int i=0;i<newProductList.size();i++){
    			ProductVo product = newProductList.get(i);
    		%>
    		<tr align="center">
    			<td>
                    <a href="/shopping/product?action=show&id=<%=product.getProId() %>">
                        <img src="<%=request.getContextPath()%>/images/<%=product.getImageUrl() %>" title="<%=product.getProDesc() %>" width="80" height="60" />
                    </a>
                </td>
                <td><%=product.getProName() %></td>
                <td><%=product.getPurPrice() %></td>
                <td><%=product.getDisPrice() %></td>
                <td>
                    <a href="/shopping/product?action=show&id=<%=product.getProId() %>">详细信息</a>
                       |
                    <a href="/shopping/product?action=buy&id=<%=product.getProId() %>&amount=1">加入到购物车</a>
                </td>
    		</tr>
    		<%} %>
		 </table>		 
	</body>
</html>
<%@ include file = "user/footer.jsp" %>