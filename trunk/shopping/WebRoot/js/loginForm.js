Ext.BLANK_IMAGE_URL='resources/images/default/s.gif';

Ext.QuickTips.init();
Ext.form.Field.prototype.msgTarget='side';
            
Ext.onReady(init);

function init() {
	var loginForm = new Ext.FormPanel({
	     width:306,
	     frame:true,
	     method:"POST",
	     labelAlign:"right",
	     onSubmit:Ext.emptyFn,
	     items:[
	        {
	            fieldLabel:"用户名",
	            xtype:"textfield",
	            allowBlank:false,
	            blankText:"用户名不能为空",
	            name:"username",
	            id:"username"
	        },
	        {
	            fieldLabel:"密码",
	            xtype:"textfield",
	            allowBlank:false,
	            blankText:"密码不能为空",
	            inputType:"password",
	            name:"password",
	            id:"password"
	　　},
	        {
	            fieldLabel:"验证码",
	            maxLength:4,
	            width: 70,
	            xtype:"textfield",
	            allowBlank:false,
	            blankText:"验证码不能为空",
	            name:"checkCode",
	            id:"checkCode"
	　　},
	        {
	            fieldLabel:"操作码",
	            xtype:"textfield",
	            allowBlank:false,
	            name:"action",
	            id:"action",
	            value:"login",
	            hidden: true,
                hideLabel:true
	　　}
	　　 ],
	    buttons:[
	        {text:"登录",handler:login,formBind:true},
	        {text:"重置",handler:reset}
	    ]
	});
	
	var win = new Ext.Window({
		title:"会员登录",
		modal:true,
		width:320,
		height:161,
		items:[loginForm]
	});
	
	win.show();
	
	var bd = Ext.getDom('checkCode');
    var bd2 = Ext.get(bd.parentNode);
    bd2.createChild([
	{
	        tag: 'img',
	        id: 'safecode',
	        src: '../checkCode.jsp',
	        align: 'absbottom'
	    }
    ]);
    
	function login() {
	    var f = loginForm.form;
	    if (f.isValid()){
	        f.doAction('submit', {
	            url:'/shopping/user',
	            method:'post',
	            success:function(form, action){
	                Ext.Msg.alert("提示",action.result.info);
	            },
	            failure:function(form, action){
	                Ext.Msg.alert("提示",action.result.info);
	            }
	        });
	    }
	}
	                
	function reset() {
	    loginForm.form.reset();
	}
	
	var view = new Ext.Viewport({
	    enableTabScroll:true,
	    items:[
	        win
	    ]
    });
}