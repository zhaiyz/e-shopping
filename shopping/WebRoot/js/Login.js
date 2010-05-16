Ext.BLANK_IMAGE_URL='../resources/images/default/s.gif';
            
Ext.form.Field.prototype.msgTarget='qtip';

var login = function() {
	Ext.QuickTips.init();
	Ext.lib.Ajax.defaultPostHeader += ";charset=utf-8";
	Ext.state.Manager.setProvider(new Ext.state.CookieProvider());

	// 实现具体的功能

	var form = new Ext.FormPanel( {
		defaultType : 'textfield',
		labelWidth : 60,
		style : 'background:#ffffff;padding:25px 35px 30px 16px;',
		region : "center",
		defaults : {
			border : false,
			allowBlank : false,
			msgTarget : 'side',
			blankText : '该字段不允许为空'
		},
		waitMsgTarget : true,
		items : [
				{
					fieldLabel : '登录帐号',
					name : 'user',
					regex:/^[0-9a-zA-Z]{2,6}$/,
					regexText:'只能为两到六位的大小写字母。'
				},
				{
					fieldLabel : '登录密码',
					name : 'pass',
					inputType : 'password',
					regex:/^.{4,}$/,
					regexText:'长度不能少于4位'
				},
				{
					xtype : 'panel',
					layout : 'column',
					items : [
							{
								width : 130,
								layout : 'form',
								border : false,
								items : [{
									fieldLabel : '效 验 码',
									name : 'checkcode',
									xtype : 'textfield',
									allowBlank : false,
									msgTarget : 'side',
									blankText : '该字段不允许为空',
									anchor : '98%'
								}]
							},
							{   xtype : 'checkCode',
								src : '../checkCode.jsp',
								width : 60,
								height : 20,
								handler : true
							}
					]
				}],
		buttons : [ {
			text : '登陆',
			handler : function() {
				form.getForm().submit( {
					success : function(f, a) {
						document.location=a.result.url;
					},
					failure: function(f, a) {
						Ext.Msg.alert("登录出错", a.result.error);
					},
					url : '/shopping/admin?action=login',
					waitMsg : '正在提交，请稍等...'
				});
			}
		}, {
			text : '取消',
			handler : function() {
				form.getForm().reset();
			}
		}]

	});

	var panel = new Ext.Panel( {
		renderTo : 'loginpanel',
		layout : "border",
		width : 525,
		height : 290,
		defaults : {
			border : false
		},
		items : [ {
			region : "north",
			height : 56,
			html : '<img src="../resources/images/head.gif"/>'
		}, {
			region : "south",
			height : 56,
			html : '<img src="../resources/images/foot.gif"/>'
		}, {
			region : "west",
			width : 253,
			html : '<img src="../resources/images/left.gif"/>'
		}, form]
	});

	Ext.get('loginpanel').setStyle('position', 'absolute')
			.center(Ext.getBody());

};

Ext.onReady(login);
