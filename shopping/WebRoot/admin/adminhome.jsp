<%@ page contentType="text/html;charset=utf-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
		<title>管理员首页</title>
		<link rel="stylesheet" type="text/css"
			href="../resources/css/ext-all.css" />
		<link rel="stylesheet" type="text/css"
			href="../resources/css/ext-patch.css" />
        <!--
		<script type="text/javascript" src="../js/ext-base.js"></script>
		<script type="text/javascript" src="../js/ext-all.js"></script>
		-->
		<script type="text/javascript" src="../js/ext-base-debug.js"></script>
		<script type="text/javascript" src="../js/ext-all-debug.js"></script>
		
		<script type="text/javascript" src="../js/ext-lang-zh_CN.js"></script>

		<script type="text/javascript" src="../js/SearchField.js"></script>
		<script type="text/javascript" src="../js/tabCloseMenu.js"></script>
		<script type="text/javascript" src="../js/FileUploadField.js"></script>
		<script type="text/javascript">
            Ext.BLANK_IMAGE_URL='../resources/images/default/s.gif';
            
            Ext.QuickTips.init();
            
            Ext.form.Field.prototype.msgTarget='qtip';
                    
            Ext.onReady(function(){
            
                //顶部面板
                var topPanel = new Ext.Panel({
                    region: "north",
                    tbar:[
                        '欢迎你' + '<%=session.getAttribute("adminName") %>',
                        {xtype:"tbseparator"},
                        {text: '退出', handler: function() {
                            Ext.Ajax.request({
                                url: '/shopping/admin?action=logout',
                                method: 'post',
                                success: function(response, options) {
                                    var obj = Ext.util.JSON.decode(response.responseText);
                                    if (obj.success == true) {
                                        document.location = obj.url;
                                    }
                                }
                            });
                        }}
                    ],
                    height: 100
                });
                
                //在左部菜单下面的商品管理树
                var t1 = new Ext.tree.TreePanel({
                    border: false,
                    rootVisible: false,
                    root: new Ext.tree.AsyncTreeNode({
                        text: "商品管理",
                        expanded: true,
                        children: [
                            {id: "category.jsp", text: "商品大类", icon:'../resources/images/Icon_021.ico', leaf: true},
                            {id: "item.jsp", text: "商品小类", icon:'../resources/images/Icon_021.ico', leaf: true},
                            {id: "product.jsp", text: "具体商品", icon:'../resources/images/Icon_021.ico', leaf: true}
                        ]
                    })
                });
                
                //在左部菜单下面的订单管理树
                var t2 = new Ext.tree.TreePanel({
                    border: false,
                    rootVisible: false,
                    root: new Ext.tree.AsyncTreeNode({
                        text: "订单管理",
                        expanded: true,
                        children: [
                            {id: "order.jsp", text: "订单查看", leaf: true},
                            {id: "send.jsp", text: "订单发货", leaf: true}
                        ]
                    })
                });
                
                //在左部菜单下面的充值卡管理
                var t3 = new Ext.tree.TreePanel({
                    border: false,
                    rootVisible: false,
                    root: new Ext.tree.AsyncTreeNode({
                        text: "充值卡管理",
                        expended: true,
                        children: [
                            {id: "card.jsp", text: "充值卡管理", leaf: true},
                            {id: "card2.jsp", text: "充值卡统计", leaf: true}
                        ]
                    })
                });
                
                //营业状况
                var t4 = new Ext.tree.TreePanel({
                    border: false,
                    rootVisible: false,
                    root: new Ext.tree.AsyncTreeNode({
                        text: "收益情况",
                        expended: true,
                        children: [
                            {id: "benefit.jsp", text: "收益情况", leaf: true}
                        ]
                    })
                });
                
                //会员管理
                var t5 = new Ext.tree.TreePanel({
                    border: false,
                    rootVisible: false,
                    root: new Ext.tree.AsyncTreeNode({
                        text: "会员管理",
                        expended: true,
                        children: [
                            {id: "member.jsp", text: "会员管理", leaf: true}
                        ]
                    })
                });
                
                //左部菜单部分
                var leftPanel = new Ext.Panel({
                    title: "菜单",
                    region: "west",
                    width: 180,
                    collapsible: true,
                    layout: "accordion",
                    layoutConfig: {animate:true},
                    items: [
                        {title:'商品管理', items: [t1]},
                        {title:'订单管理', items: [t2]},
                        {title:'充值卡管理', items: [t3]},
                        {title:'营业状况', items: [t4]},
                        {title:'会员管理', items: [t5]}
                    ]
                });
                
                //主面板
                var tabPanel = new Ext.TabPanel({
                    enableTabScroll:true,
                    defaults: {autoScroll:true},
                    region:"center",
                    items:[
                        {title:"首页",closable:false}
                    ],
                    activeTab:0,
                    plugins: new Ext.ux.TabCloseMenu()
                });
                
                //主体显示
                var view = new Ext.Viewport({
                    enableTabScroll: true,
                    layout: "border",
                    items:[
                        topPanel,
                        leftPanel,
                        tabPanel
                    ]
                });
                
                //为t1树的添加事件监听
                t1.addListener("click",function(node){addTab(node);});
                
                //为t2树的添加事件监听
                t2.addListener("click",function(node){addTab(node);});
                
                //为t3树的添加事件监听
                t3.addListener("click",function(node){addTab(node);});
                
                //为t4树的添加事件监听
                t4.addListener("click",function(node){addTab(node);});
                
                //为t5树的添加事件监听
                t5.addListener("click",function(node){addTab(node);});
            
	            //定义方法addTab
	            function addTab(node) {
	                var tab = tabPanel.getComponent(node.id);
	                
	                if(!tab){
	                    tab = tabPanel.add({
	                        id: node.id,
	                        title: node.text,
	                        closable: true,
	                        autoLoad: {url: node.id, scripts: true}
	                    });
	                }
	                
	                tabPanel.setActiveTab(tab);
	            }
	        });
        </script>
	</head>
</html>