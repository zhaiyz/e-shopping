<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file = "header.jsp" %>
<center>
<form action="/shopping/product" method="post">
<input type="hidden" name="action" value="buy">
<input type="hidden" name="id" value="${pro.proId}">
<table>
    <tr>
        <td>
              商品名称:
        </td>
        <td>
            ${pro.proName}
        </td>
    </tr>
    <tr>
        <td>
              商品图片:
        </td>
        <td>
           <img src="<%=request.getContextPath()%>/images/${pro.imageUrl}" title="${pro.proDesc}" />
        </td>
    </tr>
    <tr>
        <td>商品描述:</td>
        <td><textarea> ${pro.proDesc}</textarea></td>
    </tr>
    <tr>
        <td>库存量:</td>
        <td>${pro.stock}</td>
    </tr>
    <tr>
        <td>销售量:</td>
        <td>${pro.sales}</td>
    </tr>
    <tr>
        <td>原价:</td>
        <td>${pro.oriPrice}</td>
    </tr>
    <tr>
        <td>会员价</td>
        <td>${pro.disPrice}</td>
    </tr>
    <tr>
        <td>购买数量:</td>
        <td><input type="text" name="amount" /></td>
    </tr>
    <tr>
        <td>操作:</td>
        <td><input type="submit" value="添加到购物车" /></td>
    </tr>
</table>
</form>
</center>
<%@ include file = "footer.jsp" %>