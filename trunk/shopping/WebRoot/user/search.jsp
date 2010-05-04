<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="java.net.URLEncoder"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg"%>
<%@ include file = "header.jsp" %>
<table width="100%">
    <tr>
        <th>图片</th>
        <th>商品名称</th>
        <th>原价</th>
        <th>会员价</th>
        <th>操作</th>
    </tr>
    <c:if test="${!empty pm.datas}">
        <c:forEach items="${pm.datas}" var="pro">
            <tr align="center">
                <td>
                    <a href="/shopping/product?action=show&id=${pro.proId}">
                        <img src="<%=request.getContextPath()%>/images/${pro.imageUrl}" title="${pro.proDesc}" width="80" height="60" />
                    </a>
                </td>
                <td>${pro.proName}</td>
                <td>${pro.oriPrice}</td>
                <td>${pro.disPrice}</td>
                <td>
                    <a href="/shopping/product?action=show&id=${pro.proId}">详细信息</a>
                       |
                    <a href="/shopping/product?action=buy&id=${pro.proId}&amount=1">加入到购物车</a>
                </td>
            </tr>
        </c:forEach>
    </c:if>
    <c:if test="${empty pm.datas}">
        <tr>
            <td colspan="5">
                  没有相关商品!
            </td>
        </tr>
    </c:if>
</table>
<center>
	<pg:pager url="/shopping/product"
	    items="${pm.total}" maxPageItems="5" maxIndexPages="5"
	    export="currentPageNumber=pageNumber">
	    <pg:param name="action" value="search" />
	    <pg:param name="keyword" value="<%=URLEncoder.encode((String)request.getAttribute("key"),"utf-8")%>"/>
		<pg:first>
			<a href="${pageUrl}">首页</a>
		</pg:first>
		<pg:prev>
			<a href="${pageUrl }">上一页</a>
		</pg:prev>
		<pg:pages>
			<c:choose>
				<c:when test="${currentPageNumber eq pageNumber}">
					<font color="red">${pageNumber }</font>
				</c:when>
				<c:otherwise>
					<a href="${pageUrl }">${pageNumber }</a>
				</c:otherwise>
			</c:choose>
		</pg:pages>
		<pg:next>
			<a href="${pageUrl }">下一页</a>
		</pg:next>
		<pg:last>
			<a href="${pageUrl }">尾页</a>
		</pg:last>
	</pg:pager>
</center>
<%@ include file = "footer.jsp" %>