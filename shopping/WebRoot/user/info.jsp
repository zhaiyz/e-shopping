<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file = "header.jsp" %>
<form action="/shopping/order" method="post">
<center>
    <input type="hidden" name="action" value="add">
    <table>
        <tr>
            <td colspan="2" align="center">
                  寄送详细信息
            </td>
        </tr>
        <tr>
            <td>收货人:</td>
            <td><input type="text" name="name" /></td>
        </tr>
        <tr>
            <td>收货地址:</td>
            <td><input type="text" name="address" /></td>
        </tr>
        <tr>
            <td>邮箱编码:</td>
            <td><input type="text" name="postcode" /></td>
        </tr>
        <tr>
            <td>移动电话:</td>
            <td><input type="text" name="telphone" /></td>
        </tr>
        <tr>
            <td>空闲时间:</td>
            <td>
                <input type="radio" name="freetime" value="0" checked />全周
                <input type="radio" name="freetime" value="1" />周一至周五
                <input type="radio" name="freetime" value="2" />周末
            </td>
        </tr>
        <tr>
            <td>付款方式:</td>
            <td>
                <input type="radio" name="payment" value="0" checked />先付款后发货
                <input type="radio" name="payment" value="1" />货到付款
            </td>
        </tr>
        <tr>
            <td>邮递方式:</td>
            <td>
                <input type="radio" name="post" value="0" checked />平邮10元
                <input type="radio" name="post" value="1" />快递15元
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="确认" />
            </td>
        </tr>
    </table>
</center>
</form>
<%@ include file = "footer.jsp" %>