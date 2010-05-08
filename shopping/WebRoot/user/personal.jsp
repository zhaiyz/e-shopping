<%@ page contentType="text/html;charset=utf-8"%>
<jsp:directive.page import="com.shopping.vo.UserVo"/>
<%@ include file = "header.jsp" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
		<title>E网情深─网上商城</title>	
	</head>
	<body>
	<% 
		UserVo user = new UserVo(); 
		user = (UserVo) request.getAttribute("user"); 
	 %>
		<center>
			<form action="/shopping/user?action=userUpdate" method="post">
				<table>
					<tr>
						<td colspan="3" align="center">用户个人信息</td>
					</tr>
					<tr>
						<td colspan="3">
						<%
							if(request.getParameter("error") != null){
						 %>
						 	<font color = "red"><%= request.getParameter("error")%></font>
						 <%}
						  %>
						</td>
					</tr>
					<tr>
						<td>
							 用户名：
						</td>
						<td>
							<%=user.getUserName() %>
						</td>
					</tr>
					<tr>
						<td>
							新密码：
						</td>
						<td>
							<input type="password" name="password" />
						</td>
					</tr>
					<tr>
						<td>
							确认密码：
						</td>
						<td>
							<input type="password" name="password" />
						</td>
					</tr>
					<tr>
						<td>
							性别：
						</td>
						
						<td>
							<input type="radio" name="gender" value="0" <%if(user.getGender() == 0) {%> checked <%}%>>保密
							<input type="radio" name="gender" value="1" <%if(user.getGender() == 1) {%> checked <%}%>>男
							<input type="radio" name="gender" value="2" <%if(user.getGender() == 2) {%> checked <%}%>>女
						</td>
					</tr>
					<tr>
						<td>
							密码提示
						</td>
						<td>
							<select name="prompt">
								<option value="none">===请选择===
								<option value="你上的大学是什么">你上的大学是什么
								<option value="你的出生地是什么">你的出生地是什么
							</select>
						</td>
					</tr>
					<tr>
						<td>
							密码答案：
						</td>
						<td>
							<input type="text" name="answer" />
						</td>
					</tr>
					<tr>
						<td>
							邮箱：
						</td>
						<td>
							<input type="text" name="email" value="<%=user.getEmail() %>">
						</td>
					</tr>
					<tr>
						<td>
							电话：
						</td>
						<td>
							<input type="text" name="phone" value="<%=user.getPhone() %>">
						</td>
					</tr>
					<tr>
						<td>
							账户余额：
						</td>
						<td>
							<%=user.getBalance() %>元
						</td>
					</tr>
					<tr>
						<td>
							消费额度：
						</td>
						<td>
							<%=user.getPayed() %>元
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
						<td colspan="3">
							<input type="submit" name="submit" value="确认" />
							<input type="reset" name="submit" value="重置" />
						</td>
					</tr>
				</table>
			</form>
		</center>
	</body>
</html>
<%@ include file = "footer.jsp" %>