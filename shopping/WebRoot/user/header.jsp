<%@ page contentType="text/html;charset=utf-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
        <title>E网情深─网上商城</title>
    </head>
    <body>
        <table width="100%" height="100%" border="1">
            <!-- 头部显示信息开始 -->
            <tr height="10%">
                <td colspan="2">
                <%
                    //通过session判断用户是否登录
                    if (session.getAttribute("userName") == null) {
                    //没有登录时
                %>
                    <a href="user/login.jsp">登录</a>|
                    <a href="user/register.jsp">注册</a>
                <%
                    } else {
                    //已经登录时
                %>
                      欢迎你<font color="red"><%=session.getAttribute("userName")%></font>|
                   <a href="user/logout.jsp">退出</a>
                <%
                    }
                %>
                    
                </td>
            </tr>
            <!-- 头部显示信息结束 -->
            
            <!-- 左侧导航条开始 -->
            <tr>
                <td width="20%">
                      左侧导航条
                </td>
            <!-- 左侧导航条结束 -->
            
            <!-- 正文内容开始 -->
                <td width="80%">