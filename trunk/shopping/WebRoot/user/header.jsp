<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="com.shopping.vo.CategoryVo" %>
<%@ page import="com.shopping.vo.ItemVo" %>
<%@ page import="com.shopping.factory.ServiceFactory" %>
<%@ page import="java.util.*" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
        <title>E网情深─网上商城</title>
    </head>
    <body>
        <table width="100%" height="100%" border="1">
            <!-- 头部显示信息开始 -->
            <tr height="10%">
                <td colspan="2" width="35%">
                <%
                    //通过session判断用户是否登录
                    if (session.getAttribute("userName") == null) {
                    //没有登录时
                %>
                    <a href="/shopping/forward?page=login">登录</a>|
                    <a href="/shopping/forward?page=register">注册</a>
                <%
                    } else {
                    //已经登录时
                %>
                      欢迎你&nbsp;<font color="red" size="6"><%=session.getAttribute("userName")%></font>|
                   <a href="/shopping/forward?page=personal">个人信息</a>|
                   <a href="/shopping/order?action=show">订单管理</a>|
                   <a href="/shopping/forward?page=cart">购物车</a>|
                   <a href="/shopping/forward?page=logout">退出</a>
                <%
                    }
                %>
                </td>
                <td align="right" style="vertical-align:bottom">
                    <form action="/shopping/product" method="post">
                        <input type="hidden" name="action" value="search" />
                        <table>
                            <tr>
                                <td>
                                         商品名称:
                                </td>
                                <td>
                                    <input type="text" name="keyword" />
                                </td>
                                <td>
                                    <input type="submit" name="submit" value="搜索" />
                                </td>
                            </tr>
                        </table>
                    </form>
                </td>
            </tr>
            <!-- 头部显示信息结束 -->
            
            <!-- 左侧导航条开始 -->
            <tr>
                <td width="20%" style="vertical-align:top">
                    <%
                        //首先是取得所有的大类
                        List<CategoryVo> listc = new ArrayList<CategoryVo>();
                  //      if(request.getAttribute("category") != null) {
                  //          listc = (List<CategoryVo>)request.getAttribute("category");
                  //      }
                        listc = ServiceFactory.getCategoryServiceInstance().findAllCategory();
                        
                        //循环输出大类
                        Iterator<CategoryVo> iteratorc = listc.iterator();
                        while(iteratorc.hasNext()) {
                            CategoryVo category = new CategoryVo();
                            category = (CategoryVo)iteratorc.next();
                    %>
                    <ul>
                        <%=category.getCatName()%>
                        <%
                            //按大类的ID查询出相应该大类下面的小类们
                            List<ItemVo> listi = new ArrayList<ItemVo>();
                                
                            listi = ServiceFactory.getItemServiceInstance().findItemByCategoryId(category.getCatId());
                            
                            //循环输出相应大类正面的所有小类
                            Iterator<ItemVo> iteratori = listi.iterator();
                            while(iteratori.hasNext()) {
                                ItemVo item = new ItemVo();
                                item = (ItemVo) iteratori.next();
                        %>
                        <li>
                            <a href="/shopping/item?action=list&id=<%=item.getItemId()%>" title="<%=item.getItemDesc()%>">
                                <%=item.getItemName()%>
                            </a>
                        </li>
                        <%  
                            }
                        %>
                    </ul>
                    <%
                        }
                    %>
                </td>
            <!-- 左侧导航条结束 -->
            
            <!-- 正文内容开始 -->
                <td colspan="2" width="80%">