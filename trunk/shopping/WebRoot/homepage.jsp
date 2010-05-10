<%@ page contentType="text/html;charset=utf-8"%>
<jsp:directive.page import="com.shopping.vo.ProductVo"/>
<%@ include file = "user/header.jsp" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
		<title>E网情深─网上商城</title>
	<style>
		.col_kx { OVERFLOW: hidden; WIDTH: 20px }
	</style>	
	</head>
	<body>
		<%
			List<ProductVo> recommenList = (ArrayList)request.getAttribute("recommenList");
			List<ProductVo> newProductList = (ArrayList)request.getAttribute("newProductList");
		 %>
		 
		 <table>
		 	<tr>
		 		<td>推荐商品</td>
		 	</tr>
		 	<tr>
	<div id="header_demo" class="col_kx">
		 	<td  id="header_demo1" nowrap>
		 	<marquee scrollamount=1 scrolldelay=3 valign=middle behavior="scroll" onmouseover=this.stop() onmouseout=this.start()>
		 	
		 	<%for(int i=0;i<recommenList.size();i++){
		 		ProductVo product = recommenList.get(i);
		 		%>		 			 	
		 		
		 		<a href="/shopping/product?action=show&id=<%=product.getProId()%>" >
	<img src="<%=request.getContextPath()%>/images/<%=product.getImageUrl() %>" title="<%=product.getProDesc() %>" width="120" height="120" border="0" />
		 		</a>
		 		
		 	<% } %>
		 	
		 	</marquee>
		 	</td>
		 	
		 	<td id="header_demo2" nowrap></td>
	</div>
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
    		<tr align="center">
    		<% for(int i=0;i<newProductList.size();i++){
    			ProductVo product = newProductList.get(i);
    		%>
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
                    <a href="/shopping/product?action=buy&id=<%=product.getProId() %> &amount=1">加入到购物车</a>
                </td>
    		<%} %>
    		</tr>
		 </table>
		 
		 
<script>

//滚动头条
//滚动效果
var header_demo = document.getElementById("header_demo");
var header_demo1 = document.getElementById("header_demo1");
var header_demo2 = document.getElementById("header_demo2");
var speed=30; //数值越大滚动速度越慢
header_demo2.innerHTML = header_demo1.innerHTML;

//header_demo2.offsetWidth 总宽度
//alert(header_demo2.offsetWidth +"-"+header_demo.scrollLeft);

function Marquee(){
if(header_demo2.offsetWidth-header_demo.scrollLeft<=0)
header_demo.scrollLeft-=header_demo1.offsetWidth
else{
header_demo.scrollLeft++
}
}
var MyMar = setInterval(Marquee,speed);
header_demo.onmouseover = function(){clearInterval(MyMar)}
header_demo.onmouseout = function(){MyMar = setInterval(Marquee,speed)}
</script>
		 
		 
	</body>
</html>
<%@ include file = "user/footer.jsp" %>