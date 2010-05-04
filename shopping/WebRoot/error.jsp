<%@ page contentType="text/html;charset=utf-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
		<title>出错啦!</title>
	</head>
	<body>
		<center>
		    出错啦!!!<br>
		    <%
		        if (request.getAttribute("op_error") != null) {
		        //出现错误的时候
		    %>
		        <font color="red"><%=request.getAttribute("op_error")%></font>
		    <%
		        }
		    %>
		</center>
	</body>
</html>