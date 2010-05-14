<%@ page contentType="text/html;charset=utf-8"%>
<jsp:directive.page import="com.shopping.vo.UserVo"/>
<%@ include file = "header.jsp" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
		<title>E网情深─网上商城</title>
		
<script type="text/javascript">
	var tPwd = false;
	var tRpwd = false;
	var tEmial = false;
	function validatePwd() {
		var pwd = document.getElementById("password").value;
		var info = document.getElementById("sPwd");
		if(pwd=="") {
			info.innerHTML="密码不能为空";
			info.style.color="red";
			document.getElementById("password").focus();
			tPwd = false;
			return;
		}else if(pwd.length<3||pwd.length>16) {
			info.innerHTML="密码长度为3～16个字符";
			info.style.color="red";
			document.getElementById("password").focus();
			tPwd = false;
			return;
		}else if(/\W/.test(pwd)) {
			info.innerHTML="由a～z的英文字母、0～9的数字、下划线组成";
			info.style.color="red";
			document.getElementById("password").focus();
			tPwd = false;
			return;
		}else {
			info.innerHTML="";
			tPwd = true;
		}
	}
	function validateRePwd() {
		var pwd = document.getElementById("password").value;
		var rPwd = document.getElementById("repassword").value;
		var info = document.getElementById("rPwd");
		if(pwd!=rPwd) {
			info.innerHTML="两次输入的密码不一致";
			info.style.color="red";
			document.getElementById("repassword").focus();
			tRpwd = false;
			return;
		}else {
			info.innerHTML="";
			tRpwd = true;
		}
	}
	
	function validateEmail() {
		if(!tPwd||!tRpwd) {
		 return;
		}
		var email = document.getElementById("email").value;
		var info = document.getElementById("sEmail");
		var rex = /^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
		if(email=="") {
			info.innerHTML = "请输入你的邮箱地址";
			info.style.color="red";
			document.getElementById("email").focus();
			tEmail = false;
			return;
		}
		else if(!rex.test(email)) {
			info.innerHTML = "请检查你的邮箱地址";
			info.style.color="red";
			document.getElementById("email").focus();
			tEmail = false;
			return;
		}else {
			info.innerHTML = "";
			tEmail = true;
		}
	}
</script>

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
							<input type="password" name="password" id="password" onblur="validatePwd();" />
						</td>
						<td>
		               		<span id="sPwd"></span>
		               </td>
					</tr>
					<tr>
						<td>
							确认密码：
						</td>
						<td>
							<input type="password" name="password" id="repassword" onblur="validateRePwd();" />
						</td>
						<td>
							<span id="rPwd"></span>
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
							<input type="text" name="email" id="email" value="<%=user.getEmail() %>" onblur="validateEmail();" />
						</td>
						<td>
		                <span id="sEmail"></span>
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