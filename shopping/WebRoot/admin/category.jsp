<%@ page contentType="text/html;charset=utf-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
		<title>管理员首页</title>
	<body>
		<script type="text/javascript">
	        Ext.onReady(function(){
	            //定义store里面的成员
	            var Member = Ext.data.Record.create([
	                {name: 'catId', type: 'int'},
                    {name: 'catName', type: 'string'},
                    {name: 'catDesc', type: 'string'},
                    {name: 'catDatetime', type: 'date' ,dateFormat:"Y-m-d H:i:s"}
                ]);
                
                //定义store
                var store = new Ext.data.JsonStore({
                    url:"/shopping/category?action=list",
                    totalProperty:'total',
                    root:'list',
                    fields:Member,
                    baseParams: {
                        start: 0,
                        limit: 10
                    }
                });
                
                //加载数据
                store.load();
                
                
                //定义一个grid
                var grid = new Ext.grid.GridPanel({
                    renderTo:"grid",
                    title:"商品大类列表",
                    layout: "fit",
                    height: Ext.getBody().getHeight() - 160,
                    columns:[
                        new Ext.grid.RowNumberer({header:'序号',width:35}),
                        {header:"大类名称", dataIndex:"catName"},
                        {id:'catDesc', header:"大类描述", dataIndex:"catDesc"},
                        {
                            header:"创建时间",
                            dataIndex:"catDatetime",
                            width: 130,
                            sortable: true,
                            renderer:Ext.util.Format.dateRenderer('Y-m-d H:i:s')
                        }
                    ],
                    ds:store,
                    sm: new Ext.grid.RowSelectionModel({ singleSelect: true }),
                    autoExpandColumn: 'catDesc',
                    bbar: new Ext.PagingToolbar({
                        pageSize:10,
                        store:store,
                        displayInfo:true,
                        displayMsg:'显示第{0}条到{1}条记录，一共{2}条',
                        emptyMsg:"没有记录"
                    })
                });
                
                //给这个数据框添加事件
                grid.addListener('celldblclick',function(grid, rowIndex, columnIndex, e){
                    var s=grid.getStore();
                    var x=s.getAt(rowIndex);
                    var win = new Ext.Window({
                        title: '商品大类',
                        width: 300,
                        height: 250,
                        modal: true,
                        layout: 'form',
                        bodyStyle: 'padding:10px 10px 10px 10px',
                        labelWidth: 60,
                        items: [
                            new Ext.form.Hidden({
                                fieldLabel: '大类主键',
                                width: 200,
                                name: 'catId',
                                id: 'catId',
                                value: x.get("catId")
                            }),
                            new Ext.form.TextField({
                                fieldLabel: '大类名称',
                                width: 200,
                                allowBlank: false,
                                blankText: '请填写大类名称',
                                emptyText: '商品大类名称',
                                name: 'catName',
                                id: 'catName',
                                value: x.get("catName")
                            }),
                            new Ext.form.TextArea({
                                fieldLabel: '大类说明',
                                width: 200,
                                height:100,
                                allowBlank: false,
                                blankText: '请填写大类描述',
                                emptyText: '商品大类描述',
                                name: 'catDesc',
                                id: 'catDesc',
                                value: x.get("catDesc")
                            }),new Ext.form.TextField({
                                fieldLabel: '创建时间',
                                width: 200,
                                name: 'catDatetime',
                                id: 'catDatetime',
                                readOnly: true,
                                value: x.get("catDatetime").dateFormat('Y-m-d H:i:s')
                            })
                        ],
                        buttons: [{
                            text: '修改',
                            icon: '../resources/images/Icon_007.ico',
                            width: 85,
                            height: 27,
                            handler: function() {
                                Ext.Ajax.request({
                                    url: '/shopping/category?action=update',
                                    params: {
                                        catId: Ext.getCmp('catId').getValue(),
                                        catName: Ext.getCmp('catName').getValue(),
                                        catDesc: Ext.getCmp('catDesc').getValue()
                                    },
                                    method: 'post',
                                    success: function(response, options){
                                        var obj = Ext.util.JSON.decode(response.responseText);
                                        if (obj.success == true) {
                                            Ext.Msg.alert("提示","修改商品大类成功");
                                            store.load();
                                            win.close();
                                        } else {
                                            Ext.Msg.alert("提示","修改商品大类失败");
                                            win.close();
                                        }
                                    },
                                    failure: function(response, options) {
                                        Ext.Msg.alert("提示","修改商品大类失败");
                                        win.close();
                                    }
                                });
                            }
                        },{
                            text: '重置',
                            icon: '../resources/images/Icon_106.ico',
                            width: 85,
                            height: 27,
                            handler: function() {
                                Ext.getCmp('catName').setValue(x.get('catName'));
                                Ext.getCmp('catDesc').setValue(x.get('catDesc'));
                            }
                        },{
                            text: '关闭',
                            icon: '../resources/images/Icon_043.ico',
                            width: 85,
                            height: 27,
                            handler: function(){
                                win.close();
                            }
                        }]
                    });
                    
                    win.show();  
                });
                
	            //商品大类页面的主面板
	            var catPane = new Ext.Panel({
	                renderTo: "panel",
	                items: [
	                ],
	                tbar: [
	                    {text: "刷新", handler: function() {store.load()}},
	                    {xtype:"tbseparator"},
	                    {text: "添加", handler: addCategory},
	                    {xtype:"tbseparator"},
	                    {text: "编辑", handler: editCategory},
	                    {xtype:"tbseparator"},
	                    {text: "删除", handler: delCategory},
	                    {xtype:"tbseparator"},
	                    '大类名称:', ' ',
                        new Ext.ux.form.SearchField({
                          store: store,
                          width: 150
                        })
	                ]
	            });
	            
	            //添加大类方法的实现
	            function addCategory() {
	                var win = new Ext.Window({
                        title: '商品大类',
                        width: 300,
                        height: 250,
                        modal: true,
                        layout: 'form',
                        bodyStyle: 'padding:10px 10px 0px 0px',
                        labelWidth: 60,
                        items: [
                            new Ext.form.TextField({
                                fieldLabel: '大类名称',
                                width: 200,
                                allowBlank: false,
                                blankText: '请填写大类名称',
                                emptyText: '商品大类名称',
                                name: 'catName',
                                id: 'catName'
                            }),
                            new Ext.form.TextArea({
                                fieldLabel: '大类说明',
                                width: 200,
                                height:120,
                                allowBlank: false,
                                blankText: '请填写大类描述',
                                emptyText: '商品大类描述',
                                name: 'catDesc',
                                id: 'catDesc'
                            })
                        ],
                        buttons: [{
                            text: '添加',
                            icon: '../resources/images/Icon_113.ico',
                            width: 85,
                            height: 27,
                            handler: function() {
                                Ext.Ajax.request({
                                    url: '/shopping/category?action=add',
                                    params: {
                                        catName: Ext.getCmp('catName').getValue(),
                                        catDesc: Ext.getCmp('catDesc').getValue()
                                    },
                                    method: 'post',
                                    success: function(response, options) {
                                        var obj = Ext.util.JSON.decode(response.responseText);
                                        if (obj.success == true) { 
                                            Ext.Msg.alert("提示","添加商品大类成功");
                                            store.load();
                                            win.close();
                                        } else {
                                            Ext.Msg.alert("提示","添加商品大类失败");
                                            win.close();
                                        }
                                    },
                                    failure: function(response, options) {
                                        Ext.Msg.alert("提示","添加商品大类失败");
                                        win.close();
                                    }
                                });
                            }
                        },{
                            text: '重置',
                            icon: '../resources/images/Icon_106.ico',
                            width: 85,
                            height: 27,
                            handler: function (){
                                Ext.getCmp('catName').setValue("");
                                Ext.getCmp('catDesc').setValue("");
                            }
                        },{
                            text: '关闭',
                            icon: '../resources/images/Icon_043.ico',
                            width: 85,
                            height: 27,
                            handler: function(){
                                win.close();
                            }
                        }]
                    });
                    
                    win.show(); 
	            }
	            
	            //编辑大类方法的实现
	            function editCategory() {
	                var x = grid.getSelectionModel().getSelected();
                    if (x == null) {
                        Ext.MessageBox.alert('提示', '至少选择一行');
                        return false;
                    } else {
                        //通过recordtoedit 取值了 比如说有id这个直接
                        var win = new Ext.Window({
                            title: '商品大类',
                            width: 300,
                            height: 250,
                            modal: true,
                            layout: 'form',
                            bodyStyle: 'padding:10px 10px 10px 10px',
                            labelWidth: 60,
                            items: [
                                new Ext.form.Hidden({
                                    fieldLabel: '大类主键',
                                    width: 200,
                                    name: 'catId',
                                    id: 'catId',
                                    value: x.get("catId")
                                }),
                                new Ext.form.TextField({
                                    fieldLabel: '大类名称',
                                    width: 200,
                                    allowBlank: false,
	                                blankText: '请填写大类名称',
	                                emptyText: '商品大类名称',
                                    name: 'catName',
                                    id: 'catName',
                                    value: x.get("catName")
                                }),
                                new Ext.form.TextArea({
                                    fieldLabel: '大类说明',
                                    width: 200,
                                    height:100,
                                    allowBlank: false,
	                                blankText: '请填写大类描述',
	                                emptyText: '商品大类描述',
                                    name: 'catDesc',
                                    id: 'catDesc',
                                    value: x.get("catDesc")
                                }),new Ext.form.TextField({
                                    fieldLabel: '创建时间',
                                    width: 200,
                                    name: 'catDatetime',
                                    id: 'catDatetime',
                                    readOnly: true,
                                    value: x.get("catDatetime").dateFormat('Y-m-d H:i:s')
                                })
                            ],
                            buttons: [{
                                text: '修改',
                                icon: '../resources/images/Icon_007.ico',
                                width: 85,
                                height: 27,
                                handler: function() {
                                    Ext.Ajax.request({
                                        url: '/shopping/category?action=update',
                                        params: {
                                            catId: Ext.getCmp('catId').getValue(),
                                            catName: Ext.getCmp('catName').getValue(),
                                            catDesc: Ext.getCmp('catDesc').getValue()
                                        },
                                        method: 'post',
                                        success: function(response, options){
                                            var obj = Ext.util.JSON.decode(response.responseText);
                                            if (obj.success == true) {
                                                Ext.Msg.alert("提示","修改商品大类成功");
                                                store.load();
                                                win.close();
                                            } else {
                                                Ext.Msg.alert("提示","修改商品大类失败");
                                                win.close();
                                            }
                                        },
                                        failure: function(response, options) {
                                            Ext.Msg.alert("提示","修改商品大类失败");
                                            win.close();
                                        }
                                    });
                                }
                            },{
                                text: '重置',
                                icon: '../resources/images/Icon_106.ico',
                                width: 85,
                                height: 27,
                                handler: function() {
                                    Ext.getCmp('catName').setValue(x.get('catName'));
                                    Ext.getCmp('catDesc').setValue(x.get('catDesc'));
                                }
                            },{
                                text: '关闭',
                                icon: '../resources/images/Icon_043.ico',
                                width: 85,
                                height: 27,
                                handler: function(){
                                    win.close();
                                }
                            }]
                        });
                    
                        win.show(); 
                    } 
	            }
	            
	            //删除大类方法的实现
	            function delCategory() {
	                var x = grid.getSelectionModel().getSelected();
                    if (x == null) {
                        Ext.MessageBox.alert('提示', '至少选择一行');
                        return false;
                    } else {
                        Ext.MessageBox.confirm("提示","你确定要删除这个大类吗?",function(btn){
                            if (btn == 'yes') {
                                Ext.Ajax.request({
                                    url: '/shopping/category?action=del',
                                    params: {
                                        catId: x.get("catId")
                                    },
                                    success: function(response, options) {
                                        var obj = Ext.util.JSON.decode(response.responseText);
                                        if (obj.success == true) {
                                            Ext.Msg.alert("提示","删除商品大类成功");
                                            store.load();
                                        } else {
                                            Ext.Msg.alert("提示","删除商品大类失败");
                                        }
                                    },
                                    failure: function(response, options) {
                                        Ext.Msg.alert("提示","删除商品大类失败");
                                    }
                                });
                            }
                        })
                    }
	            }
	        });
	    </script>
		<div id="panel"></div>
		<div id="grid" style="width: 100%, height = 100%"></div>
	</body>
</html>