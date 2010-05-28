<%@ page contentType="text/html;charset=utf-8"%>
<jsp:directive.page import="com.shopping.vo.UserVo"/>
<%@ include file = "header.jsp" %>
		<%
			UserVo user = new  UserVo();
			user = (UserVo)request.getAttribute("user");
		%>
		<center>
		<form action="/shopping/card?action=show" method="post">
			<table>
				<tr>
					<td align="center" colspan="3">个人账户</td>
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
						充值卡帐号：
					</td>
					<td>
						<input type="text" name="cardNo" />
					</td>
				</tr>
				<tr>
					<td>
						充值卡密码：
					</td>
					<td>
						<input type="password" name="cardPassword" />
					</td>
				</tr>
				<tr>
					<td>
						<input type="submit" name="submit" value="确认" />
					</td>
					<td>
						<input type="reset" name="submit" value="重置" />
					</td>
				</tr>		
			</table>
			</form>
		</center>
<%@ include file = "footer.jsp" %>