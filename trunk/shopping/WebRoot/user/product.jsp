<%@ page contentType="text/html;charset=utf-8"%>
<jsp:directive.page import="com.shopping.vo.ProductVo"/>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file = "header.jsp" %>
<center>
<form action="/shopping/product" method="post">
<input type="hidden" name="action" value="buy">
<input type="hidden" name="id" value="${pro.proId}">

<script type="text/javascript">
	function testisNum(){
	var obj = document.getElementById("amount");
	var s = obj.value;
    if(s!=""){
		if(isNaN(s)){
			alert("商品数量，请输入数字");
			//obj.value=s;
			obj.focus();
        }
    }
}
</script>

<table>
    <tr>
        <td>
              商品名称:
        </td>
        <td>
            ${pro.proName}
            <c:if test="${pro.recommendation == 1}">
                * 推荐
            </c:if>
        </td>
    </tr>
    <tr>
        <td>
              商品图片:
        </td>
        <td>
           <img src="<%=request.getContextPath()%>/images/${pro.imageUrl}" title="${pro.proDesc}" width="360" height="380" />
        </td>
    </tr>
    <tr>
        <td>商品描述:</td>
        <td><textarea readonly="readonly" > ${pro.proDesc}</textarea></td>
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
        <td><input type="text" name="amount" id="amount" value="1" onblur="testisNum();" maxlength="2" /></td>
    </tr>
    <tr>
        <td>操作:</td>
        <td><input type="submit" value="添加到购物车" /></td>
    </tr>
</table>
</form>
</center>
<%@ include file = "footer.jsp" %>