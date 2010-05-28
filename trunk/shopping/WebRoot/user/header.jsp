<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="com.shopping.vo.CategoryVo" %>
<%@ page import="com.shopping.vo.ItemVo" %>
<%@ page import="com.shopping.factory.ServiceFactory" %>
<%@ page import="java.util.*" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
        <title>E网情深─网上商城</title>
		<script type="text/javascript">
			function validateKeyword(){
				var keyWord = document.getElementById("keyword").value;
				if(keyWord==""){
					return false;
				}else{
					return true;
				}
			}
		</script>
		<style type="text/css">
			body {
				font: normal 11px auto "Trebuchet MS", Verdana, Arial, Helvetica,
					sans-serif;
				color: #4f6b72;
				background: #E6EAE9;
			}
			
			a {
				color: #c75f3e;
			}
			
			table {
				padding: 0px 0px 0px 0px;
				margin: 0px 0px 0px 0px;
			}
			
			caption {
				padding: 0 0 5px 0;
				font: italic 14px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
				text-align: center;
			}
			
			th {
				color: #4f6b72;
				border-right: 1px solid #C1DAD7;
				border-bottom: 1px solid #C1DAD7;
				border-top: 1px solid #C1DAD7;
				letter-spacing: 2px;
				text-transform: uppercase;
				text-align: center;
				padding: 6px 6px 6px 12px;
				background: #CAE8EA no-repeat;
			}
			
			/*power by www.winshell.cn*/
			th.nobg {
				border-top: 0;
				border-left: 0;
				border-right: 1px solid #C1DAD7;
				background: none;
			}
			
			td {
				border-bottom: 1px solid #C1DAD7;
				background: #fff;
				font-size: 14px;
				padding: 6px 6px 6px 12px;
				margin: 0px 0px 0px 0px;
				color: #4f6b72;
			}
			
			/*power by www.winshell.cn*/
			td.alt {
				background: #F5FAFA;
				color: #797268;
			}
			
			th.spec {
				border-left: 1px solid #C1DAD7;
				border-top: 0;
				background: #fff no-repeat;
				font: bold 10px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
			}
			
			th.specalt {
				border-left: 1px solid #C1DAD7;
				border-top: 0;
				background: #f5fafa no-repeat;
				font: bold 10px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
				color: #797268;
			}
			
			ul {
			    margin: 20px 5px 5px 5px;
			}
			
			li {
			    margin: 10px 3px 3px 3px;
			}
		</style>
    </head>
    <body>
        <table width="100%" height="100%">
            <!-- 头部显示信息开始 -->
            <tr height="10%">
                <td colspan="2" width="55%">
                <%
                    //通过session判断用户是否登录
                    if (session.getAttribute("userName") == null) {
                    //没有登录时
                %>
                	<a href="/shopping/forward?page=index">首页</a>|
                    <a href="/shopping/forward?page=login">登录</a>|
                    <a href="/shopping/forward?page=register">注册</a>
                <%
                    } else {
                    //已经登录时
                %>
                      欢迎你&nbsp;<font color="red" size="6"><%=session.getAttribute("userName")%></font>|
                   <a href="/shopping/forward?page=index">首页</a>|
                   <a href="/shopping/forward?page=personal">个人信息</a>|
                   <a href="/shopping/order?action=show">订单管理</a>|
                   <a href="/shopping/forward?page=cart">购物车</a>|
                   <a href="/shopping/forward?page=account">账户</a>|
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
                                <td style="vertical-align:bottom;border-bottom:none;">
                                         商品名称:
                                </td>
                                <td style="vertical-align:bottom;border-bottom:none;">
                                    <input type="text" name="keyword" id="keyword" />
                                </td>
                                <td style="vertical-align:bottom;border-bottom:none;">
                                    <input type="submit" name="submit" value="搜索" onclick="return validateKeyword();"/>
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
                <td colspan="2" width="80%" style="vertical-align:top">