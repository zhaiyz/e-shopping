<%@ page contentType="text/html;charset=utf-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
		<title>E网情深─网上商城</title>
	</head>
	<body>
		<center>
		    <form action="/shopping/user?action=register" method="post">
		        <table>
		            <tr>
		                <td colspan="3" align="center">用户注册</td>
		            </tr>
		            <tr>
		                <td colspan="3">
		                <%
		                    if (request.getParameter("error") != null) {
		                %>
		                    <font color="red"><%=request.getParameter("error")%></font>
		                <%
		                    }
		                %>
		                </td>
		            <tr>
		               <td>
		                     用户名:
		               </td>
		               <td>
		                   <input type="text" name="username" />
		               </td>
		               <td>
		               </td>
		            </tr>
		            <tr>
		               <td>
		                     密码:
		               </td>
		               <td>
		                   <input type="password" name="password" />
		               </td>
		               <td>
		               </td>
		            </tr>
		            <tr>
		                <td>
		                      重复密码:
		                </td>
		                <td>
		                    <input type="password" name="repassword" />
		                </td>
		                <td>
		                </td>
		            </tr>
		            <tr>
		                <td>
		                       性别:
		                </td>
		                <td>
		                    <input type="radio" name="gender" value="0" checked="true">保密
		                    <input type="radio" name="gender" value="1">男
		                    <input type="radio" name="gender" value="2">女
		                </td>
		                <td>
		                </td>
		            </tr>
		            <tr>
		                <td>
		                      密码提示:
		                </td>
		                <td>
		                    <select name="propmt">
		                        <option value="none">===请选择===
		                        <option value="你上的大学是什么">你上的大学是什么
		                        <option value="你的出生地是什么">你的出生地是什么
		                    </select>
		                </td>
		                <td>
		                </td>
		            </tr>
		            <tr>
		                <td>
		                       密码答案:
		                </td>
		                <td>
		                    <input type="text" name="answer" />
		                </td>
		                <td>
		                </td>
		            </tr>
		            <tr>
		                <td>
		                       邮箱:
		                </td>
		                <td>
		                    <input type="text" name="email">
		                </td>
		                <td>
		                </td>
		            </tr>
		            <tr>
		                <td>
		                       电话:
		                </td>
		                <td>
		                    <input type="text" name="phone" />
		                </td>
		                <td>
		                </td>
		            </tr>
		            <tr>
		                <td colspan="3">
		                    <input type="submit" name="submit" value="注册" />
		                    <input type="reset" name="submit" value="重置" />
		                </td>
		            </tr>
		        </table>
		    </form>
		</center>
	</body>
</html>