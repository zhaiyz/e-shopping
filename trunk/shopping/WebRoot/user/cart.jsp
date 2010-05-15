<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.shopping.factory.ServiceFactory" %>
<%@ page import="com.shopping.vo.CartVo" %>
<%@ page import="com.shopping.vo.ProductVo" %>
<%@ page import="java.util.*" %>
<%@ include file = "header.jsp" %>

<script type="text/javascript">
<!--
var isNum=false;
function testisNum(id){
	var obj = document.getElementById(id);
	var s = obj.value;
    if(s!=""){
		if(isNaN(s)){
			alert("商品数量，请输入数字");
			//obj.value=s;
			obj.focus();
			isNum=false;
        }else{
        	isNum=true;
        }
    }
}
//-->
</script>
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
            
            Iterator<CartVo> iterator = list.iterator();
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
            <td>
                <input type="text" name="<%=cart.getCartId()%>" id="<%=cart.getCartId()%>" onkeyup="testisNum(<%=cart.getCartId()%>);" value="<%=cart.getProAmount()%>" size="5" maxlength="2" />
            </td>
            <td><%=product.getDisPrice()%>元</td>
            <td><%=total%>元</td>
            <td><%=cart.getAddDatetime()%></td>
            <td>
                <a href="/shopping/cart?action=delete&id=<%=cart.getCartId()%>">
                      删除
                </a>
            </td>
        </tr>
        <%
            }
        %>
        <tr>
            <td colspan="3" align="center">
                <input type="submit" value="更新购物车" />
            </td>
            
            <td colspan="3" align="right">
                  总价格为:<%=allTotal%>元
            </td>
        </tr>
        <tr>
            <td colspan="6" align="center">
                <a href="/shopping/forward?page=order" onclick="return isAllNum();" >
                      确认购买
                </a>
            </td>
        </tr>
    </table>
</form>
<%@ include file = "footer.jsp" %>