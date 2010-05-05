<%@ page contentType="text/html;charset=utf-8"%>
<%@page import="com.shopping.vo.OrderInfoVo"%>
<%@page import="com.shopping.vo.ProductVo"%>
<%@ include file = "header.jsp" %>
<center>
    <table width="100%">
        <tr align="center">
            <td>订单号:</td>
            <td colspan="2">
                <font color="red">TM</font><%=(String)request.getAttribute("num")%>
            </td>
        </tr>
        <tr align="center">
            <th>商品名称</th>
            <th>商品单价</th>
            <th>商品数量</th>
            <th>商品总价</th>
        </tr>
        <%
            List<OrderInfoVo> list = new ArrayList<OrderInfoVo>();
            list = (List<OrderInfoVo>) request.getAttribute("info");
            Iterator<OrderInfoVo> iterator = list.iterator();
            
            //总价，要加上邮费的那种
            float total = 0.0f;
            while(iterator.hasNext()) {
                OrderInfoVo info = new OrderInfoVo();
                info = iterator.next();
                
                //根据商品主键查询出商品
                ProductVo product = new ProductVo();
                product = ServiceFactory.getProductServiceInstance().findProductById(info.getProId());                
                
                //计算出总价
                total += info.getPrice();
        %>
            <tr align="center">
                <td><%=product.getProName()%></td>
                <td><%=product.getDisPrice()%></td>
                <td><%=info.getAmount()%></td>
                <td><%=info.getPrice()%></td>
            </tr>
        <%
            }
        %>
        <tr>
            <td align="center">邮递方式:</td>
            <td colspan="2" align="center">
                <%
                    int post = (Integer) request.getAttribute("post");
                    
                    if (post == 0) {
                        total += 10;
                %>
                  平邮10元
                <%
                    } else {
                        total += 15;
                %>
                  快递15元
                <%
                    }
                %>
            </td>
        </tr>
        <tr>
            <td align="center">
                  小结:
            </td>
            <td colspan="2" align="center">
                  共<font color="red"><%=total%></font>元
            </td>
        </tr>
    </table>
</center>
<%@ include file = "footer.jsp" %>