<%@ page contentType="text/html;charset=utf-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
		<title>E网情深─网上商城</title>
	</head>
	<body>
		<center>
		    <%
		        if (request.getAttribute("error") != null) {
		        //出现错误的时候
		    %>
		        <font color="red"><%=request.getAttribute("error")%></font>
		    <%
		        }
		    %>
			<form action="/shopping/user" method="POST">
				<input type="hidden" name="action" value="login" />
				<table>
					<tr>
						<td colspan="2">
							会员登录
						</td>
					</tr>
					<tr>
						<td>
							用户名:
						</td>
						<td>
							<input type="text" name="username" />
						</td>
					</tr>
					<tr>
						<td>
							密码:
						</td>
						<td>
							<input type="password" name="password" />
						</td>
					</tr>
					<tr>
						<td>
							验证码:
						</td>
						<td>
							<input type="text" name="checkCode" />
							<img src="<%=request.getContextPath()%>/checkCode.jsp" />
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="submit" value="登录" />
							<input type="reset" value="重置" />
						</td>
					</tr>
				</table>
			</form>
		</center>
	</body>
</html>