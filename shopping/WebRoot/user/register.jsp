<%@ page contentType="text/html;charset=utf-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
		<title>E网情深─网上商城</title>
		
<script type="text/javascript">
	var tName = false;
	var tPwd = false;
	var tRpwd = false;
	var tEmial = false;
	function validateName() {
	alert("1");
	var name = document.getElementById("username").value;
		var info = document.getElementById("sName");
		if(name=="") {
			info.innerHTML="用户名不能为空";
			info.style.color="red";
			document.getElementById("username").focus();
			tName = false;
			return;
		}else if(name.length<3||name.length>16) {
			info.innerHTML="用户名长度为3～16个字符";
			info.style.color="red";
			document.getElementById("username").focus();
			tName = false;
			return;
		}else if(/\W/.test(name)) {
			info.innerHTML="由a～z的英文字母、0～9的数字、下划线组成";
			info.style.color="red";
			document.getElementById("username").focus();
			tName = false;
			return;
		}
	   
	function validatePwd() {
		if(!tName) {
			return;
		}
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
		if(!tName||!tPwd) {
			return;
		}
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
		if(!tName||!tPwd||!tRpwd) {
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
		             </tr>
		            <tr>
		               <td>
		                     用户名:
		               </td>
		               <td>
		                   <input type="text" name="username" id="username" onblur="validateName();"/>
		               </td>
		               
		               <td>
		               	<span  style="color:red">*</span>  <span id="sName"></span>
		               </td>
		            </tr>
		            <tr>
		               <td>
		                     密码:
		               </td>
		               <td>
		                   <input type="password" name="password" id="password" onblur="validatePwd();" />
		               </td>
		               
		               <td>
		               <span  style="color:red">*</span><span id="sPwd"></span>
		               </td>
		            </tr>
		            <tr>
		                <td>
		                      重复密码:
		                </td>
		                <td>
		                    <input type="password" name="repassword" id="repassword" onblur="validateRePwd();" />
		                </td>
		                
		                <td>
		                <span  style="color:red">*</span><span id="rPwd"></span>
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
		                    <input type="text" name="email" id="email" onblur="validateEmail();" />
		                </td>
		                
		                <td>
		                <span  style="color:red">*</span><span id="sEmail"></span>
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