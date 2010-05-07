<%@ page contentType="text/html;charset=utf-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
		<title>管理员首页</title>
		<link rel="stylesheet" type="text/css" href="../resources/css/ext-all.css" />
		<link rel="stylesheet" type="text/css" href="../resources/css/ext-patch.css" />
		
		<script type="text/javascript" src="../js/ext-base.js"></script>
		<script type="text/javascript" src="../js/ext-all.js"></script>
		<!--
		<script type="text/javascript" src="../js/ext-base-debug.js"></script>
		<script type="text/javascript" src="../js/ext-all-debug.js"></script>
		-->
		<script type="text/javascript" src="../js/ext-lang-zh_CN.js"></script>
		<script type="text/javascript" src="../js/SearchField.js"></script>
		<script type="text/javascript" src="../js/tabCloseMenu.js"></script>
		<script type="text/javascript">
            Ext.BLANK_IMAGE_URL='../resources/images/default/s.gif';
                    
            Ext.onReady(function(){
            
                //顶部面板
                var topPanel = new Ext.Panel({
                    title: "顶部面板",
                    region: "north",
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
                            {id: "category.jsp", text: "商品大类", icon:'../resources/images/Icon_021.ico', height: 15, leaf: true},
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
                            {id: "order", text: "订单管理", leaf: true}
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
                            {id: "card", text: "充值卡管理", leaf: true}
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
                        {title:'充值卡管理', items: [t3]}
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