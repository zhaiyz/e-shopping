<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file = "header.jsp" %>
<form action="/shopping/order" method="post">
<center>
    <input type="hidden" name="action" value="pay" />
    <table>
        <tr>
            <td>请输入密码:</td>
            <td>
                <input type="password" name="password" />
            </td>
            <td>
                <input type="submit" value="确认" />
            </td>
        </tr>
    </table>
</center>
</form>
<%@ include file = "footer.jsp" %>