<%@ page contentType="text/html;charset=utf-8"%>
<%@page import="com.shopping.vo.MyOrderVo"%>
<%@ include file = "header.jsp" %>
<center>
<table width="100%">
  <tr>
    <th>订单号</th>
    <th>付款方式</th>
    <th>邮递方式</th>
    <th>总价</th>
    <th>订单时间</th>
    <th>订单状态</th>
    <th>操作</th>
  </tr>
  <%
      List<MyOrderVo> list = new ArrayList<MyOrderVo>();
      list = (List<MyOrderVo>) request.getAttribute("order");
      
      //循环输出
      Iterator<MyOrderVo> iterator = list.iterator();
      while(iterator.hasNext()) {
          MyOrderVo order = new MyOrderVo();
          order = iterator.next();
  %>
  <tr align="center">
    <td>
        <a href="/shopping/info?action=show&id=<%=order.getOrderId()%>">
            <font color="red">TM</font><%=order.getOrderNum()%>
        </a>
    </td>
    <td>
	    <%
	        if(order.getPayment() == 0) {
	    %>
	       先付款后发货
	    <%
	        } else {
	    %>
	       货到付款
	    <%
	        }
	    %>
    </td>
    <td>
        <%
            if(order.getPost() == 0) {
        %>
         平邮
        <%
            } else {
        %>
         快递
        <%
            }
        %>
    </td>
    <td>
        <%=order.getTotalPrice()%>元
    </td>
    <td>
        <%=order.getOrderDatetime()%>
    </td>
    <td>
        <%
            if(order.getOrderState() == 0) {
        %>
         未发货
        <%
            } else if(order.getOrderState() == 1) {
        %>
         已发货
        <%
            }else {
        %>
         已收货
        <%
            }
        %>
    </td>
    <td>
       <a href="/shopping/info?action=show&id=<%=order.getOrderId()%>">订单明细</a>
       |
       <%
           if(order.getOrderState() == 0) {
       %>
        无
       <%
           } else if (order.getOrderState() == 1) {
       %>
       <a href="/shopping/order?action=confirm&id=<%=order.getOrderId()%>">
            确认收货
       </a>
       <%
           } else {
       %>
        无
       <%
           }
       %>
    </td>
  </tr>
  <%
      }
  %>
</table>
<center>
<%@ include file = "footer.jsp" %>