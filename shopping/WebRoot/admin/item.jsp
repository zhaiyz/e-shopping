<%@ page contentType="text/html;charset=utf-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
		<title>管理员首页</title>
	<body>
		<script type="text/javascript">
	        Ext.onReady(function(){
	            //定义catStore里面的成员
	            var catMember = Ext.data.Record.create([
	                {name: 'itemId', type: 'int'},
	                {name: 'catId', type: 'int'},
                    {name: 'catName', type: 'string'},
                    {name: 'catDesc', type: 'string'},
                    {name: 'catDatetime', type: 'date' ,dateFormat:"Y-m-d H:i:s"}
                ]);
                
                //定义catStore
                var catStore = new Ext.data.JsonStore({
                    url:"/shopping/category?action=all",
                    root:'list',
                    fields:catMember
                });
                
                //加载数据
                catStore.load();
                
                //定义itemStore里面的成员
	            var itemMember = Ext.data.Record.create([
	                {name: 'itemId', type: 'int'},
	                {name: 'catId', type: 'int'},
                    {name: 'itemName', type: 'string'},
                    {name: 'itemDesc', type: 'string'},
                    {name: 'itemDatetime', type: 'date' ,dateFormat:"Y-m-d H:i:s"}
                ]); 
                
                //定义itemStore
                var itemStore = new Ext.data.JsonStore({
                    url:"/shopping/item?action=all",
                    totalProperty:'total',
                    root:'list',
                    fields: itemMember,
                    baseParams: {
                        start: 0,
                        limit: 10
                    }
                });
                
                //加载Item数据
                itemStore.load();
                
                //定义一个itemGrid
                var itemGrid = new Ext.grid.GridPanel({
                    renderTo:"itemGrid",
                    title:"商品小类列表",
                    layout: "fit",
                    height: Ext.getBody().getHeight() - 160,
                    columns:[
                        new Ext.grid.RowNumberer({header:'序号',width:35}),
                        {header:"小类名称", dataIndex:"itemName"},
                        {id:'itemDesc', header:"小类描述", dataIndex:"itemDesc"},
                        {
                            header:"创建时间",
                            dataIndex:"itemDatetime",
                            width: 130,
                            sortable: true,
                            renderer:Ext.util.Format.dateRenderer('Y-m-d H:i:s')
                        }
                    ],
                    ds:itemStore,
                    sm: new Ext.grid.RowSelectionModel({ singleSelect: true }),
                    autoExpandColumn: 'itemDesc',
                    bbar: new Ext.PagingToolbar({
                        pageSize:10,
                        store:itemStore,
                        displayInfo:true,
                        displayMsg:'显示第{0}条到{1}条记录，一共{2}条',
                        emptyMsg:"没有记录"
                    })
                });
                
                //给这个数据框添加事件
                itemGrid.addListener('celldblclick',function(grid, rowIndex, columnIndex, e){
                    var s=grid.getStore();
                    var x=s.getAt(rowIndex);
                    var win = new Ext.Window({
                        title: '商品小类',
                        width: 300,
                        height: 280,
                        modal: true,
                        layout: 'form',
                        bodyStyle: 'padding:10px 10px 10px 10px',
                        labelWidth: 60,
                        items: [
                            new Ext.form.ComboBox({
                                fieldLabel: '所属大类',
	                            width: 150,
	                        	mode: 'remote',
	                        	readOnly: true,
	                        	store: catStore,
	                        	orceSelection: true,
	                        	blankText: '选择大类',
	                        	displayField:'catName',
	                       	 	valueField:'catId',
	                       	 	id: 'catId',
	                        	triggerAction: 'all'
                            }).setValue(x.get("catId")),
                            new Ext.form.TextField({
                                fieldLabel: '小类名称',
                                width: 200,
                                allowBlank: false,
                                blankText: '请填写小类名称',
                                emptyText: '商品小类名称',
                                name: 'itemName',
                                id: 'itemName',
                                value: x.get("itemName")
                            }),
                            new Ext.form.TextArea({
                                fieldLabel: '小类说明',
                                width: 200,
                                height:100,
                                allowBlank: false,
                                blankText: '请填写小类描述',
                                emptyText: '商品大类描述',
                                name: 'itemDesc',
                                id: 'itemDesc',
                                value: x.get("itemDesc")
                            }),new Ext.form.TextField({
                                fieldLabel: '创建时间',
                                width: 200,
                                name: 'itemDatetime',
                                id: 'itemDatetime',
                                readOnly: true,
                                value: x.get("itemDatetime").dateFormat('Y-m-d H:i:s')
                            })
                        ],
                        buttons: [{
                            text: '修改',
                            icon: '../resources/images/Icon_007.ico',
                            width: 85,
                            height: 27,
                            handler: function() {
                                Ext.Ajax.request({
                                    url: '/shopping/item?action=update',
                                    params: {
                                        itemId: x.get("itemId"),
                                        catId: Ext.getCmp("catId").getValue(),
                                        itemName: Ext.getCmp("itemName").getValue(),
                                        itemDesc: Ext.getCmp("itemDesc").getValue()
                                    },
                                    success: function(response, options) {
                                        var obj = Ext.util.JSON.decode(response.responseText);
                                        if (obj.success == true) {
                                            Ext.Msg.alert("提示","添加商品小类成功");
                                            itemStore.load();
                                            win.close();
                                        } else {
                                            Ext.Msg.alert("提示","添加商品小类失败");
                                            win.close();
                                        }
                                    },
                                    failure: function(response, options) {
                                        Ext.Msg.alert("提示","添加商品小类失败");
                                        win.close();
                                    }
                                });
                            }
                        },{
                            text: '重置',
                            icon: '../resources/images/Icon_106.ico',
                            width: 85,
                            height: 27,
                            handler: function(){
                                Ext.getCmp("catId").setValue(x.get("catId"));
                                Ext.getCmp("itemName").setValue(x.get("itemName"));
                                Ext.getCmp("itemDesc").setValue(x.get("itemDesc"));
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
	        
	            //商品小类面板
	            var itemPane = new Ext.Panel({
	                renderTo: "itemPanel",
	                items: [
	                ],
	                tbar: [
	                    {text: "刷新", handler: function() {itemStore.load()}},
	                    {xtype:"tbseparator"},
	                    {text: "添加", handler: addItem},
	                    {xtype:"tbseparator"},
	                    {text: "编辑", handler: editItem},
	                    {xtype:"tbseparator"},
	                    {text: "删除", handler: delItem},
	                    {xtype:"tbseparator"},
	                    '商品大类:', ' ',
	                    new Ext.form.ComboBox({
	                        id: 'catCombo',
	                        emptyText:'请选择个大类',
	                        width: 150,
	                        mode: 'remote',
	                        readOnly: true,
	                        store: catStore,
	                        displayField:'catName',
	                        valueField:'catId',
	                        emptyText:'请选择一个大类',
	                        triggerAction: 'all',
	                        listeners: {
	                            'select': function (combo, record, index){
	                                itemStoreLoad(combo, record, index);
	                            }
	                        }
	                    }),
	                    {xtype:"tbseparator"},
	                    '小类名称:', ' ',
                        new Ext.ux.form.SearchField({
                          store: itemStore,
                          width: 150
                        })
	                ]
	            });
	            
	            //添加小类方法
	            function addItem() {
	                var win = new Ext.Window({
                        title: '商品小类',
                        width: 300,
                        height: 280,
                        modal: true,
                        layout: 'form',
                        bodyStyle: 'padding:10px 10px 0px 0px',
                        labelWidth: 60,
                        items: [
                            new Ext.form.ComboBox({
                                fieldLabel: '商品大类',
		                        emptyText:'请选择个大类',
		                        width: 150,
		                        mode: 'remote',
		                        readOnly: true,
		                        forceSelection: true,
	                        	blankText: '选择大类',
		                        store: catStore,
		                        displayField:'catName',
		                        valueField:'catId',
		                        id: 'catId',
		                        triggerAction: 'all'
	                        }),
                            new Ext.form.TextField({
                                fieldLabel: '小类名称',
                                width: 200,
                                allowBlank: false,
                                blankText: '请填写小类名称',
                                emptyText: '商品小类名称',
                                name: 'itemName',
                                id: 'itemName'
                            }),
                            new Ext.form.TextArea({
                                fieldLabel: '小类说明',
                                width: 200,
                                height:120,
                                allowBlank: false,
                                blankText: '请填写小类描述',
                                emptyText: '商品小类描述',
                                name: 'itemDesc',
                                id: 'itemDesc'
                            })
                        ],
                        buttons: [{
                            text: '添加',
                            icon: '../resources/images/Icon_113.ico',
                            width: 85,
                            height: 27,
                            handler: function() {
                                Ext.Ajax.request({
                                    url: '/shopping/item?action=add',
                                    params: {
                                        catId: Ext.getCmp('catId').getValue(),
                                        itemName: Ext.getCmp('itemName').getValue(),
                                        itemDesc: Ext.getCmp('itemDesc').getValue()
                                    },
                                    success: function(response, options) {
                                        var obj = Ext.util.JSON.decode(response.responseText);
                                        if (obj.success == true) {
                                            Ext.Msg.alert("提示","添加商品小类成功");
                                            itemStore.load();
                                            win.close();
                                        } else {
                                            Ext.Msg.alert("提示", "添加商品小类失败");
                                            win.close();
                                        }
                                    },
                                    failure: function(response, options) {
                                        Ext.Msg.alert("提示", "添加商品小类失败");
                                        win.close();
                                    }
                                });
                            }
                        },{
                            text: '重置',
                            icon: '../resources/images/Icon_106.ico',
                            width: 85,
                            height: 27,
                            handler: function () {
                                Ext.getCmp("catId").setValue("");
                                Ext.getCmp("itemName").setValue("");
                                Ext.getCmp("itemDesc").setValue("");
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
	            
	            //编辑小类方法
	            function editItem() {
	                var x = itemGrid.getSelectionModel().getSelected();
                    if (x == null) {
                        Ext.MessageBox.alert('提示', '至少选择一行');
                        return false;
                    } else {
                        //通过recordtoedit 取值了 比如说有id这个直接
                        var win = new Ext.Window({
                            title: '商品小类',
                            width: 300,
                            height: 280,
                            modal: true,
                            layout: 'form',
                            bodyStyle: 'padding:10px 10px 10px 10px',
                            labelWidth: 60,
                            items: [
                                new Ext.form.ComboBox({
	                                fieldLabel: '所属大类',
		                            width: 150,
		                        	mode: 'remote',
		                        	readOnly: true,
		                        	store: catStore,
		                        	displayField:'catName',
		                       	 	valueField:'catId',
		                        	triggerAction: 'all',
		                        	id: 'catId',
		                        	name: 'catName'
		                        }).setValue(x.get("catId")),
	                            new Ext.form.TextField({
	                                fieldLabel: '小类名称',
	                                width: 200,
	                                allowBlank: false,
	                                blankText: '请填写小类名称',
	                                emptyText: '商品小类名称',
	                                name: 'itemName',
	                                id: 'itemName',
	                                value: x.get("itemName")
	                            }),
	                            new Ext.form.TextArea({
	                                fieldLabel: '小类说明',
	                                width: 200,
	                                height:100,
	                                allowBlank: false,
	                                blankText: '请填写小类描述',
	                                emptyText: '商品小类描述',
	                                name: 'itemDesc',
	                                id: 'itemDesc',
	                                value: x.get("itemDesc")
	                            }),new Ext.form.TextField({
	                                fieldLabel: '创建时间',
	                                width: 200,
	                                name: 'itemDatetime',
	                                readOnly: true,
	                                value: x.get("itemDatetime").dateFormat('Y-m-d H:i:s')
	                            })
	                        ],
                            buttons: [{
                                text: '修改',
                                icon: '../resources/images/Icon_007.ico',
                                width: 85,
                                height: 27,
                                handler: function() {
	                                Ext.Ajax.request({
	                                    url: '/shopping/item?action=update',
	                                    params: {
	                                        itemId: x.get("itemId"),
	                                        catId: Ext.getCmp("catId").getValue(),
	                                        itemName: Ext.getCmp("itemName").getValue(),
	                                        itemDesc: Ext.getCmp("itemDesc").getValue()
	                                    },
	                                    success: function(response, options) {
	                                        var obj = Ext.util.JSON.decode(response.responseText);
	                                        if (obj.success == true) {
	                                            Ext.Msg.alert("提示","添加商品小类成功");
	                                            itemStore.load();
	                                            win.close();
	                                        } else {
	                                            Ext.Msg.alert("提示","添加商品小类失败");
	                                            win.close();
	                                        }
	                                    },
	                                    failure: function(response, options) {
	                                        Ext.Msg.alert("提示","添加商品小类失败");
	                                        win.close();
	                                    }
	                                });
                                }
                            },{
                                text: '重置',
                                icon: '../resources/images/Icon_106.ico',
                                width: 85,
                                height: 27,
                                handler: function(){
	                                Ext.getCmp("catId").setValue(x.get("catId"));
	                                Ext.getCmp("itemName").setValue(x.get("itemName"));
	                                Ext.getCmp("itemDesc").setValue(x.get("itemDesc"));
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
	            
	            //删除小类方法
	            function delItem() {
	                var x = itemGrid.getSelectionModel().getSelected();
                    if (x == null) {
                        Ext.MessageBox.alert('提示', '至少选择一行');
                        return false;
                    } else {
                        Ext.MessageBox.confirm("提示","你确定要删除这个小类吗?",function(btn){
                            if (btn == 'yes') {
                                Ext.Ajax.request({
                                    url: '/shopping/item?action=del',
                                    params: {
                                        itemId: x.get("itemId")
                                    },
                                    success: function(response, options) {
                                        var obj = Ext.util.JSON.decode(response.responseText);
                                        if(obj.success = true) {
                                            Ext.Msg.alert("提示","商品小类删除成功");
                                            itemStore.load();
                                        } else {
                                            Ext.Msg.alert("提示","商品小类删除失败");
                                        }
                                    },
                                    failure: function(response, options) {
                                        Ext.Msg.alert("提示","商品小类删除失败");
                                    }
                                });
                            }
	                    });
	                }
	            }
	            
	            function itemStoreLoad(combo, record,index) {
	                itemStore.load({params:{catId: record.get("catId"), start: 0, limit: 10, query: null }});
	            }
	        });
	    </script>
		<div id="itemPanel"></div>
		<div id="itemGrid"></div>
	</body>
</html>