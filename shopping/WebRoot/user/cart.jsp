<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.shopping.factory.ServiceFactory" %>
<%@ page import="com.shopping.vo.CartVo" %>
<%@ page import="com.shopping.vo.ProductVo" %>
<%@ page import="java.util.*" %>
<%@ include file = "header.jsp" %>
<form action="/shopping/cart?action=update" method="post">
    <table width="100%">
        <tr>
            <th>商品名称</th>
            <th>数量</th>
            <th>单价</th>
            <th>总价</th>
            <th>添加时间</th>
            <th>操作</th>
        </tr>
        <%
            List<CartVo> list = new ArrayList<CartVo>();
            list = (List<CartVo>)request.getAttribute("cart");
            float allTotal = 0.0f;
            
            Iterator iterator = list.iterator();
            while(iterator.hasNext()) {
                CartVo cart = new CartVo();
                cart = (CartVo)iterator.next();
                ProductVo product = new ProductVo();
                product = ServiceFactory.getCartServiceInstance().findProductByCatId(cart.getProId());
                float total = product.getDisPrice() * cart.getProAmount();
                allTotal += total;
        %>
        <tr align="center">
            <td><%=product.getProName()%></td>
            <td><%=cart.getProAmount()%></td>
            <td><%=product.getDisPrice()%></td>
            <td><%=total%></td>
            <td><%=cart.getAddDatetime()%></td>
            <td>
                  删除
            </td>
        </tr>
        <%
            }
        %>
        <tr>
            <td colspan="6" align="right">
                  总价格为:<%=allTotal%>
            </td>
        </tr>
    </table>
</form>
<%@ include file = "footer.jsp" %>