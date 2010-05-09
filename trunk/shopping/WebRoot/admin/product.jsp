<%@ page contentType="text/html;charset=utf-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
		<title>管理员首页</title>
	<body>
	    <script type="text/javascript">
	        Ext.onReady(function(){
	            //定义iStore里面的成员
	            var iMember = Ext.data.Record.create([
	                {name: 'itemId', type: 'int'},
	                {name: 'catId', type: 'int'},
                    {name: 'itemName', type: 'string'},
                    {name: 'itemDesc', type: 'string'},
                    {name: 'itemDatetime', type: 'date' ,dateFormat:"Y-m-d H:i:s"}
                ]); 
                
                //定义iStore
                var iStore = new Ext.data.JsonStore({
                    url:"/shopping/item?action=display",
                    root:'list',
                    fields:iMember
                });
                
                //加载数据
                iStore.load();
                
                //定义pStore里面的成员
	            var pMember = Ext.data.Record.create([
	                {name: 'proId', type: 'int'},
	                {name: 'itemId', type: 'int'},
                    {name: 'proName', type: 'string'},
                    {name: 'imageUrl', type: 'string'},
                    {name: 'proDesc', type: 'string'},
                    {name: 'purPrice', type: 'float'},
                    {name: 'oriPrice', type: 'float'},
                    {name: 'disPrice', type: 'float'},
                    {name: 'stock', type: 'int'},
                    {name: 'sales', type: 'int'},
                    {name: 'recommendation', type: 'int'},
                    {name: 'proDatetime', type: 'date', dateFormat:"Y-m-d H:i:s"}
                ]); 
                
                //定义pStore
                var pStore = new Ext.data.JsonStore({
                    url:"/shopping/product?action=all",
                    totalProperty:'total',
                    root:'list',
                    fields: pMember,
                    baseParams: {
                        start: 0,
                        limit: 10
                    }
                });
                
                //加载Item数据
                pStore.load();
                
                //定义一个itemGrid
                var proGrid = new Ext.grid.GridPanel({
                    renderTo:"proGrid",
                    title:"商品列表",
                    layout: "fit",
                    height: Ext.getBody().getHeight() - 160,
                    columns:[
                        new Ext.grid.RowNumberer({header:'序号',width:35}),
                        {header:"商品名称", dataIndex:"proName"},
                        {id: 'proDesc', header:"商品描述", dataIndex:"proDesc"},
                        {header:"商品进价", dataIndex:"purPrice"},
                        {header:"商品原价", dataIndex:"oriPrice"},
                        {header:"商品会员价", dataIndex:"disPrice"},
                        {header:"商品库存", dataIndex:"stock"},
                        {header:"商品售量", dataIndex:"sales"},
                        {header:"是否推荐", dataIndex:"recommendation"},
                        {
                            header:"创建时间",
                            dataIndex:"proDatetime",
                            width: 130,
                            sortable: true,
                            renderer:Ext.util.Format.dateRenderer('Y-m-d H:i:s')
                        }
                    ],
                    ds:pStore,
                    sm: new Ext.grid.RowSelectionModel({ singleSelect: true }),
                    autoExpandColumn: 'proDesc',
                    bbar: new Ext.PagingToolbar({
                        pageSize:10,
                        store:pStore,
                        displayInfo:true,
                        displayMsg:'显示第{0}条到{1}条记录，一共{2}条',
                        emptyMsg:"没有记录"
                    })
                });
                
                //给这个数据框添加事件
                proGrid.addListener('celldblclick',function(grid, rowIndex, columnIndex, e){
                    var s=grid.getStore();
                    var x=s.getAt(rowIndex);
                    var win = new Ext.Window({
                        title: '商品信息',
                        width: 300,
                        height: 470,
                        modal: true,
                        layout: 'form',
                        bodyStyle: 'padding:10px 10px 10px 10px',
                        labelWidth: 60,
                        items: [
                            new Ext.form.ComboBox({
                                fieldLabel: '所属小类',
	                            width: 150,
	                        	mode: 'remote',
	                        	readOnly: true,
	                        	store: iStore,
	                        	displayField:'itemName',
	                       	 	valueField:'itemId',
	                        	triggerAction: 'all'
                            }).setValue(x.get("itemId")),
                            new Ext.form.TextField({
                                fieldLabel: '商品名称',
                                width: 200,
                                name: 'proName',
                                value: x.get("proName")
                            }),
                            new Ext.form.TextField({
                                fieldLabel: '商品图片',
                                width: 200,
                                name: 'imageUrl',
                                value: x.get("imageUrl")
                            }),
                            new Ext.form.TextArea({
                                fieldLabel: '商品介绍',
                                width: 200,
                                height:100,
                                name: 'proDesc',
                                value: x.get("proName")
                            }),
                            new Ext.form.TextField({
                                fieldLabel: '商品进价',
                                width: 200,
                                name: 'purPrice',
                                value: x.get("purPrice")
                            }),
                            new Ext.form.TextField({
                                fieldLabel: '商品原价',
                                width: 200,
                                name: 'oriPrice',
                                value: x.get("oriPrice")
                            }),
                            new Ext.form.TextField({
                                fieldLabel: '商品会员价',
                                width: 200,
                                name: 'disPrice',
                                value: x.get("disPrice")
                            }),
                            new Ext.form.TextField({
                                fieldLabel: '商品库存',
                                width: 200,
                                name: 'stock',
                                value: x.get("stock")
                            }),
                            new Ext.form.TextField({
                                fieldLabel: '商品售量',
                                width: 200,
                                name: 'sales',
                                value: x.get("sales")
                            }),
                            new Ext.form.TextField({
                                fieldLabel: '是否推荐',
                                width: 200,
                                name: 'recommendation',
                                value: x.get("recommendation")
                            }),
                            new Ext.form.TextField({
                                fieldLabel: '上架时间',
                                width: 200,
                                name: 'proDatetime',
                                readOnly: true,
                                value: x.get("proDatetime").dateFormat('Y-m-d H:i:s')
                            })
                        ],
                        buttons: [{
                            text: '修改',
                            icon: '../resources/images/Icon_007.ico',
                            width: 85,
                            height: 27
                        },{
                            text: '重置',
                            icon: '../resources/images/Icon_106.ico',
                            width: 85,
                            height: 27
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
                
	            //商品面板
	            var proPane = new Ext.Panel({
	                renderTo: "proPanel",
	                tbar: [
	                    {text: "添加", handler: addPro},
	                    {xtype:"tbseparator"},
	                    {text: "编辑", handler: editPro},
	                    {xtype:"tbseparator"},
	                    {text: "删除", handler: delPro},
	                    {xtype:"tbseparator"},
	                    '商品小类:', ' ',
	                    new Ext.form.ComboBox({
	                        emptyText:'请选择个小类',
	                        width: 150,
	                        mode: 'remote',
	                        readOnly: true,
	                        store: iStore,
	                        displayField:'itemName',
	                        valueField:'itemId',
	                        emptyText:'请选择一个小类',
	                        triggerAction: 'all',
	                        listeners: {
	                            'select': function (combo, record, index){
	                                proStoreLoad(combo, record, index);
	                            }
	                        }
	                    }),
	                    {xtype:"tbseparator"},
	                    '商品名称:', ' ',
                        new Ext.ux.form.SearchField({
                          store: pStore,
                          width: 150
                        })
	                ]
	            });
	            
	            //添加小类方法
	            function addPro() {
	               var win = new Ext.Window({
                        title: '商品信息',
                        width: 300,
                        height: 470,
                        modal: true,
                        layout: 'form',
                        bodyStyle: 'padding:10px 10px 10px 10px',
                        labelWidth: 60,
                        items: [
                            new Ext.form.ComboBox({
                                fieldLabel: '所属小类',
	                            width: 150,
	                        	mode: 'remote',
	                        	readOnly: true,
	                        	store: iStore,
	                        	displayField:'itemName',
	                       	 	valueField:'itemId',
	                        	triggerAction: 'all'
                            }),
                            new Ext.form.TextField({
                                fieldLabel: '商品名称',
                                width: 200,
                                name: 'proName'
                            }),
                            new Ext.form.TextField({
                                fieldLabel: '商品图片',
                                width: 200,
                                name: 'imageUrl'
                            }),
                            new Ext.form.TextArea({
                                fieldLabel: '商品介绍',
                                width: 200,
                                height:100,
                                name: 'proDesc'
                            }),
                            new Ext.form.TextField({
                                fieldLabel: '商品进价',
                                width: 200,
                                name: 'purPrice'
                            }),
                            new Ext.form.TextField({
                                fieldLabel: '商品原价',
                                width: 200,
                                name: 'oriPrice'
                            }),
                            new Ext.form.TextField({
                                fieldLabel: '会员价',
                                width: 200,
                                name: 'disPrice'
                            }),
                            new Ext.form.TextField({
                                fieldLabel: '商品库存',
                                width: 200,
                                name: 'stock'
                            }),
                            new Ext.form.TextField({
                                fieldLabel: '商品售量',
                                width: 200,
                                name: 'sales'
                            }),
                            new Ext.form.TextField({
                                fieldLabel: '是否推荐',
                                width: 200,
                                name: 'recommendation'
                            })
                        ],
                        buttons: [{
                            text: '添加',
                            icon: '../resources/images/Icon_113.ico',
                            width: 85,
                            height: 27
                        },{
                            text: '重置',
                            icon: '../resources/images/Icon_106.ico',
                            width: 85,
                            height: 27
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
	            function editPro() {
	                var x = proGrid.getSelectionModel().getSelected();
                    if (x == null) {
                        Ext.MessageBox.alert('提示', '至少选择一行');
                        return false;
                    } else {
                        //通过recordtoedit 取值了 比如说有id这个直接
                        var win = new Ext.Window({
                            title: '商品大类',
                            width: 300,
                            height: 470,
                            modal: true,
                            layout: 'form',
                            bodyStyle: 'padding:10px 10px 10px 10px',
                            labelWidth: 60,
                            items: [
                                new Ext.form.ComboBox({
	                                fieldLabel: '所属小类',
		                            width: 150,
		                        	mode: 'remote',
		                        	readOnly: true,
		                        	store: iStore,
		                        	displayField:'itemName',
		                       	 	valueField:'itemId',
		                        	triggerAction: 'all'
	                            }).setValue(x.get("itemId")),
	                            new Ext.form.TextField({
	                                fieldLabel: '商品名称',
	                                width: 200,
	                                name: 'proName',
	                                value: x.get("proName")
	                            }),
	                            new Ext.form.TextField({
	                                fieldLabel: '商品图片',
	                                width: 200,
	                                name: 'imageUrl',
	                                value: x.get("imageUrl")
	                            }),
	                            new Ext.form.TextArea({
	                                fieldLabel: '商品介绍',
	                                width: 200,
	                                height:100,
	                                name: 'proDesc',
	                                value: x.get("proName")
	                            }),
	                            new Ext.form.TextField({
	                                fieldLabel: '商品进价',
	                                width: 200,
	                                name: 'purPrice',
	                                value: x.get("purPrice")
	                            }),
	                            new Ext.form.TextField({
	                                fieldLabel: '商品原价',
	                                width: 200,
	                                name: 'oriPrice',
	                                value: x.get("oriPrice")
	                            }),
	                            new Ext.form.TextField({
	                                fieldLabel: '商品会员价',
	                                width: 200,
	                                name: 'disPrice',
	                                value: x.get("disPrice")
	                            }),
	                            new Ext.form.TextField({
	                                fieldLabel: '商品库存',
	                                width: 200,
	                                name: 'stock',
	                                value: x.get("stock")
	                            }),
	                            new Ext.form.TextField({
	                                fieldLabel: '商品售量',
	                                width: 200,
	                                name: 'sales',
	                                value: x.get("sales")
	                            }),
	                            new Ext.form.TextField({
	                                fieldLabel: '是否推荐',
	                                width: 200,
	                                name: 'recommendation',
	                                value: x.get("recommendation")
	                            }),
	                            new Ext.form.TextField({
	                                fieldLabel: '上架时间',
	                                width: 200,
	                                name: 'proDatetime',
	                                readOnly: true,
	                                value: x.get("proDatetime").dateFormat('Y-m-d H:i:s')
	                            })
	                        ],
	                        buttons: [{
	                            text: '修改',
	                            icon: '../resources/images/Icon_007.ico',
	                            width: 85,
	                            height: 27
	                        },{
	                            text: '重置',
	                            icon: '../resources/images/Icon_106.ico',
	                            width: 85,
	                            height: 27
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
	            function delPro() {
	                Ext.Msg.alert("提示","删除商品");
	            }
	            
	            function proStoreLoad(combo, record,index) {
	                pStore.load({params:{itemId: record.get("itemId"), start: 0, limit: 10, query: null }});
	            }
	        });
	    </script>
	    <div id="proPanel"></div>
	    <div id="proGrid"></div>
	</body>
</html>