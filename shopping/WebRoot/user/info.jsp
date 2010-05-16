<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file = "header.jsp" %>
<form action="/shopping/order" method="post">

<script type="text/javascript">
	var tName=false;
	var tAddress=false;
	var tPostcode=false;
	var tPhone=false;
	function validateName(){
	var name = document.getElementById("name").value;
		var info = document.getElementById("sName");
		if(name=="") {
			info.innerHTML="用户名不能为空";
			info.style.color="red";
			document.getElementById("name").focus();
			tName = false;
			return;
		}else{
			info.innerHTML="";
			tName=true;
		}
	}
	
	function validateAddress(){
	if(!tName){
		return;
	}
	var name = document.getElementById("address").value;
		var info = document.getElementById("sAddress");
		if(name=="") {
			info.innerHTML="地址不能为空";
			info.style.color="red";
			document.getElementById("address").focus();
			tAddress = false;
			return;
		}else{
			info.innerHTML="";
			tAddress=true;
		}
	}
	
	function validatePostCode(){
		if(!tName||!tAddress){
			return;
		}
		var name=document.getElementById("postcode").value;
		var info=document.getElementById("sPostcode");
		if(name==""){
			info.innerHTML="邮编不能为空";
			info.style.color="red";
			document.getElementById("postcode").focus();
			tPostcode=false;
		}else{
			if(isNaN(name)){
				info.innerHTML="请填写正确邮编";
				info.style.clolor="red";
				document.getElementById("postcode").focus();
				tPostcode=false;
			}else{
				info.innerHTML="";
				tPostcode=true;
			}
		}
	}
	
	function validatePhone(){
		if(!tName||!tAddress||!tPostcode){
			return;
		}
		var name=document.getElementById("telphone").value;
		var info=document.getElementById("sPhone");
		if(name==""){
			info.innerHTML="电话不能为空";
			info.style.color="red";
			document.getElementById("telphone").focus();
			tPhone=false;
		}else{
			if(isNaN(name)){
				info.innerHTML="请填写正确电话";
				info.style.clolor="red";
				document.getElementById("telphone").focus();
				tPhone=false;
			}else{
				info.innerHTML="";
				tPhone=true;
			}
		}
	}
</script>

<center>
    <input type="hidden" name="action" value="add">
    <table>
        <tr>
            <td colspan="3" align="center">
                  寄送详细信息
            </td>
        </tr>        
        <tr>
            <td>收货人:</td>
            <td>
                <input type="text" name="name" id="name" onblur="validateName();"/>
            </td>
            <td>
		   		<span  style="color:red">*</span>  <span id="sName"></span>
		    </td>
        </tr>
        <tr>
            <td>收货地址:</td>
            <td><input type="text" name="address" id="address" onblur="validateAddress();" /></td>
            <td>
		   		<span  style="color:red">*</span>  <span id="sAddress"></span>
		    </td>
        </tr>
        <tr>
            <td>邮政编码:</td>
            <td><input type="text" name="postcode" id="postcode" onblur="validatePostCode();" /></td>
            <td>
		   		<span  style="color:red">*</span>  <span id="sPostcode"></span>
		    </td>
        </tr>
        <tr>
            <td>移动电话:</td>
            <td><input type="text" name="telphone" id="telphone" onblur="validatePhone();" /></td>
            <td>
		   		<span  style="color:red">*</span>  <span id="sPhone"></span>
		    </td>
        </tr>
        <tr>
            <td>空闲时间:</td>
            <td colspan="2">
                <input type="radio" name="freetime" value="0" checked />全周
                <input type="radio" name="freetime" value="1" />周一至周五
                <input type="radio" name="freetime" value="2" />周末
            </td>
        </tr>
        <tr>
            <td>付款方式:</td>
            <td colspan="2">
                <input type="radio" name="payment" value="0" checked />先付款后发货
                <input type="radio" name="payment" value="1" />货到付款
            </td>
        </tr>
        <tr>
            <td>邮递方式:</td>
            <td colspan="2">
                <input type="radio" name="post" value="0" checked />平邮10元
                <input type="radio" name="post" value="1" />快递15元
            </td>
        </tr>
        <tr>
            <td colspan="3">
                <input type="submit" value="确认" />
            </td>
        </tr>
    </table>
</center>
</form>
<%@ include file = "footer.jsp" %>