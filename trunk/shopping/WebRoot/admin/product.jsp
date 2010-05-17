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
                    },
                    getGroupState: Ext.emptyFn
                });
                
                //加载Item数据
                pStore.load();
                
                var gridView = new Ext.grid.GroupingView({
				    getRowClass : function (record, index) {
				        if(!record){
				            return '';
				        }
				        if( record.data.stock <=20 ){
				            return 'x-grid-record-red';
				        } else if (record.data.stock > 20 && record.data.stock <=50 ) {
				            return 'x-grid-record-yellow';
				        } else {
				            return '';
				        }
				    },
				    groupTextTpl: '{text} ({[values.rs.length]} {[values.rs.length > 1 ? "Items" : "Item"]})'
				});
                
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
                        {header:"商品进价", dataIndex:"purPrice", renderer: price},
                        {header:"商品原价", dataIndex:"oriPrice", renderer: price},
                        {header:"商品会员价", dataIndex:"disPrice", renderer: price},
                        {header:"商品库存", dataIndex:"stock", renderer: stock},
                        {header:"商品售量", dataIndex:"sales", renderer: stock},
                        {header:"是否推荐", dataIndex:"recommendation", renderer: recommend},
                        {
                            header:"创建时间",
                            dataIndex:"proDatetime",
                            width: 130,
                            sortable: true,
                            renderer:Ext.util.Format.dateRenderer('Y-m-d H:i:s')
                        }
                    ],
                    ds:pStore,
                    viewConfig:/*BEGIN*/ { 
                        forceFit: true 
                    }/*END*/, 
                    view: gridView,
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
                    var flag = x.get("recommendation") == 0 ? false : true;
	                    var win = new Ext.Window({
	                        title: '商品信息',
	                        width: 300,
	                        height: 570,
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
		                        	triggerAction: 'all',
		                        	name: 'itemId',
		                        	id: 'itemId'
	                            }).setValue(x.get("itemId")),
	                            new Ext.form.TextField({
	                                fieldLabel: '商品名称',
	                                width: 200,
	                                allowBlank: false,
	                                blankText: '请填写商品名称',
	                                emptyText: '商品商品名称',
	                                name: 'proName',
	                                id: 'proName',
	                                value: x.get("proName")
	                            }),
	                            new Ext.form.Label({
	                                fieldLabel: '商品图片'
	                            }),
	                            new Ext.BoxComponent({
	                                id: 'image',
	                                xtype: 'box',
	                                width: 260,
	                                height: 100,
	                                style: 'margin: 0px 0px 5px 5px',
	                                autoEl: {
	                                    tag: 'img',
	                                    src: '../images/' + x.get("imageUrl")
	                                }
	                            }),
	                            new Ext.form.TextArea({
	                                fieldLabel: '商品介绍',
	                                width: 200,
	                                height:100,
	                                allowBlank: false,
	                                blankText: '请填写大类描述',
	                                emptyText: '商品大类描述',
	                                name: 'proDesc',
	                                id: 'proDesc',
	                                value: x.get("proDesc")
	                            }),
	                            new Ext.form.NumberField({
	                                fieldLabel: '商品进价',
	                                width: 50,
	                                name: 'purPrice',
	                                id: 'purPrice',
	                                allowBlank: false,
	                                allowNegative: false,
	                                value: x.get("purPrice")
	                            }),
	                            new Ext.form.NumberField({
	                                fieldLabel: '商品原价',
	                                width: 50,
	                                name: 'oriPrice',
	                                id: 'oriPrice',
	                                allowBlank: false,
	                                allowNegative: false,
	                                value: x.get("oriPrice")
	                            }),
	                            new Ext.form.NumberField({
	                                fieldLabel: '会员价',
	                                width: 50,
	                                allowBlank: false,
	                                allowNegative: false,
	                                name: 'disPrice',
	                                id: 'disPrice',
	                                value: x.get("disPrice")
	                            }),
	                            new Ext.form.NumberField({
	                                fieldLabel: '商品库存',
	                                width: 50,
	                                name: 'stock',
	                                allowBlank: false,
	                                allowDecimals:false,
	                                allowNegative: false,
	                                id: 'stock',
	                                value: x.get("stock")
	                            }),
	                            new Ext.form.TextField({
	                                fieldLabel: '商品售量',
	                                width: 50,
	                                name: 'sales',
	                                id: 'sales',
	                                allowBlank: false,
	                                allowDecimals:false,
	                                allowNegative: false,
	                                readOnly: true,
	                                value: x.get("sales")
	                            }),
	                            new Ext.form.Checkbox({
	                                fieldLabel: '是否推荐',
	                                boxLabel: '推荐',
	                                checked: flag,
	                                id: 'recommendation',
	                                name: 'recommendation'
	                            }),
	                            new Ext.form.TextField({
	                                fieldLabel: '上架时间',
	                                width: 200,
	                                name: 'proDatetime',
	                                id: 'proDatetiem',
	                                readOnly: true,
	                                value: x.get("proDatetime").dateFormat('Y-m-d H:i:s')
	                            })
	                        ],
	                        buttons: [{
	                            text: '修改',
	                            icon: '../resources/images/Icon_007.ico',
	                            width: 85,
	                            height: 27,
	                            handler: function() {
	                                Ext.Ajax.request({
	                                    url: '/shopping/product?action=update',
	                                    params: {
	                                        proId: x.get("proId"),
	                                        itemId: Ext.getCmp('itemId').getValue(),
	                                        proName: Ext.getCmp('proName').getValue(),
	                                        proDesc: Ext.getCmp('proDesc').getValue(),
	                                        purPrice: Ext.getCmp('purPrice').getValue(),
	                                        oriPrice: Ext.getCmp('oriPrice').getValue(),
	                                        disPrice: Ext.getCmp('disPrice').getValue(),
	                                        stock: Ext.getCmp('stock').getValue(),
	                                        recommendation: Ext.getCmp('recommendation').getValue()
	                                    },
	                                    method: 'post',
	                                    success: function(response, options) {
	                                        var obj = Ext.util.JSON.decode(response.responseText);
	                                        if (obj.success == true) {
	                                            Ext.Msg.alert("提示", "修改商品信息成功");
	                                            pStore.load();
	                                            win.close();
	                                        } else {
	                                            Ext.Msg.alsert("提示", "修改商品信息失败");
	                                            win.close();
	                                        }
	                                    },
	                                    failure: function(response, options) {
	                                        Ext.Msg.alsert("提示", "修改商品信息失败");
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
	                                Ext.getCmp('itemId').setValue(x.get('itemId'));
	                                Ext.getCmp('proName').setValue(x.get('proName'));
	                                Ext.getCmp('proDesc').setValue(x.get('proDesc'));
	                                Ext.getCmp('purPrice').setValue(x.get('purPrice'));
	                                Ext.getCmp('oriPrice').setValue(x.get('oriPrice'));
	                                Ext.getCmp('disPrice').setValue(x.get('disPrice'));
	                                Ext.getCmp('stock').setValue(x.get('stock'));
	                                Ext.getCmp('recommendation').setValue(flag);
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
                
	            //商品面板
	            var proPane = new Ext.Panel({
	                renderTo: "proPanel",
	                tbar: [
	                    {text: "刷新", handler: function() {pStore.load()}},
	                    {xtype:"tbseparator"},
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
	                var addForm = new Ext.form.FormPanel({
                                labelWidth: 60,
		                        fileUpload: true,
		                        method:"POST",
		                        labelAlign:"right",
		                        items: [{
		                            fieldLabel: "商品小类",
		                            xtype: "combo",
		                            mode: 'remote',
		                        	readOnly: true,
		                        	store: iStore,
		                        	displayField:'itemName',
		                       	 	valueField:'itemId',
		                        	triggerAction: 'all',
		                        	name: 'itemId',
		                        	id: 'itemId'
		                        },{
		                            fieldLabel:"商品名称",
		                            xtype:"textfield",
		                            width: 180,
		                            allowBlank:false,
		                            blankText:"商品名称不能为空",
		                            name:"proName",
		                            id:"proName"
		                        },{
		                            fieldLabel:"商品图片",
		                            xtype:"textfield",
		                            allowBlank:false,
		                            inputType: 'file',
		                            name:"imageUrl",
		                            id:"imageUrl"
		                        },{
		                            fieldLabel:"商品描述",
		                            xtype:"textarea",
		                            width: 180,
		                            allowBlank:false,
		                            blankText:"商品描述不能为空",
		                            name:"proDesc",
		                            id:"proDesc"
		                        },{
		                            fieldLabel:"商品进价",
		                            xtype:"numberfield",
		                            width: 50,
		                            allowBlank:false,
		                            allowNegative: false,
		                            blankText:"商品进价不能为空",
		                            name:"purPrice",
		                            id:"purPrice"
		                        },{
		                            fieldLabel:"商品原价",
		                            xtype:"numberfield",
		                            width: 50,
		                            allowNegative: false,
		                            allowBlank:false,
		                            blankText:"商品原价不能为空",
		                            name:"oriPrice",
		                            id:"oriPrice"
		                        },{
		                            fieldLabel:"会员价",
		                            xtype:"numberfield",
		                            width: 50,
		                            allowNegative: false,
		                            allowBlank:false,
		                            blankText:"会员价不能为空",
		                            name:"disPrice",
		                            id:"disPrice"
		                        },{
		                            fieldLabel:"库存",
		                            xtype:"numberfield",
		                            width: 50,
		                            allowDecimals:false,
	                                allowNegative: false,
		                            allowBlank:false,
		                            blankText:"库存",
		                            name:"stock",
		                            id:"stock"
		                        },{
		                            fieldLabel:"是否推荐",
		                            xtype:"checkbox",
		                            fieldLabel: '是否推荐',
	                                boxLabel: '推荐',
	                                name: 'recommendation',
	                                id: 'recommendation'
		                        }],
		                        buttons: [{
		                            text: "添加",
		                            handler: function() {
		                                if (addForm.form.isValid()) {
							                addForm.form.doAction('submit',{
								                url: '/shopping/product?action=add',
								                method: 'post',
								                success: function(response, options) {
								                //下面的代码有问题，是浏览器兼容的问题，我搞了一下午了没搞出来，我晕啊
								                //    var obj = Ext.util.JSON.decode(response.responseText);
								                //    if (obj.success == true) {
								                         Ext.Msg.alert("提示","商品添加成功");
								                         pStore.load();
								                         win.close();
								                //    } else {
								               //         Ext.Msg.alert("提示","商品添加失败");
								               //         win.close();
								               //  }
								                },
								                failure: function(response, options) {
								                    Ext.Msg.alert("提示","商品添加失败");
								                    win.close();
								                }
							               });
							            }
		                            },
		                            formBind:true
		                        },{
		                            text: "重置",
		                            handler: function() {
		                                addForm.form.reset();
		                            }
		                        }]
                    		});
	               var win = new Ext.Window({
                        title: '商品信息',
                        width: 300,
                        height: 350,
                        modal: true,
                        bodyStyle: 'padding:10px 10px 10px 10px',
                        items: [
                            addForm
                        ]
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
                        var flag = x.get("recommendation") == 0 ? false : true;
	                    var win = new Ext.Window({
	                        title: '商品信息',
	                        width: 300,
	                        height: 570,
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
		                        	triggerAction: 'all',
		                        	name: 'itemId',
		                        	id: 'itemId'
	                            }).setValue(x.get("itemId")),
	                            new Ext.form.TextField({
	                                fieldLabel: '商品名称',
	                                width: 200,
	                                allowBlank: false,
	                                blankText: '请填写商品名称',
                                    emptyText: '商品商品名称',
	                                name: 'proName',
	                                id: 'proName',
	                                value: x.get("proName")
	                            }),
	                            new Ext.form.Label({
	                                fieldLabel: '商品图片'
	                            }),
	                            new Ext.BoxComponent({
	                                id: 'image',
	                                xtype: 'box',
	                                width: 260,
	                                height: 100,
	                                style: 'margin: 0px 0px 5px 5px',
	                                autoEl: {
	                                    tag: 'img',
	                                    src: '../images/' + x.get("imageUrl")
	                                }
	                            }),
	                            new Ext.form.TextArea({
	                                fieldLabel: '商品介绍',
	                                width: 200,
	                                height:100,
	                                name: 'proDesc',
	                                id: 'proDesc',
	                                allowBlank: false,
	                                blankText: '请填写商品介绍',
                                    emptyText: '商品商品介绍',
	                                value: x.get("proDesc")
	                            }),
	                            new Ext.form.NumberField({
	                                fieldLabel: '商品进价',
	                                width: 50,
	                                name: 'purPrice',
	                                id: 'purPrice',
	                                allowNegative: false,
	                                value: x.get("purPrice")
	                            }),
	                            new Ext.form.NumberField({
	                                fieldLabel: '商品原价',
	                                width: 50,
	                                name: 'oriPrice',
	                                id: 'oriPrice',
	                                allowNegative: false,
	                                value: x.get("oriPrice")
	                            }),
	                            new Ext.form.NumberField({
	                                fieldLabel: '会员价',
	                                width: 50,
	                                name: 'disPrice',
	                                id: 'disPrice',
	                                allowNegative: false,
	                                value: x.get("disPrice")
	                            }),
	                            new Ext.form.NumberField({
	                                fieldLabel: '商品库存',
	                                width: 50,
	                                name: 'stock',
	                                id: 'stock',
	                                allowDecimals:false,
	                                allowNegative: false,
	                                value: x.get("stock")
	                            }),
	                            new Ext.form.NumberField({
	                                fieldLabel: '商品售量',
	                                width: 50,
	                                name: 'sales',
	                                id: 'sales',
	                                allowDecimals:false,
	                                allowNegative: false,
	                                readOnly: true,
	                                value: x.get("sales")
	                            }),
	                            new Ext.form.Checkbox({
	                                fieldLabel: '是否推荐',
	                                boxLabel: '推荐',
	                                checked: flag,
	                                id: 'recommendation',
	                                name: 'recommendation'
	                            }),
	                            new Ext.form.TextField({
	                                fieldLabel: '上架时间',
	                                width: 200,
	                                name: 'proDatetime',
	                                id: 'proDatetiem',
	                                readOnly: true,
	                                value: x.get("proDatetime").dateFormat('Y-m-d H:i:s')
	                            })
	                        ],
	                        buttons: [{
	                            text: '修改',
	                            icon: '../resources/images/Icon_007.ico',
	                            width: 85,
	                            height: 27,
	                            handler: function() {
	                                Ext.Ajax.request({
	                                    url: '/shopping/product?action=update',
	                                    params: {
	                                        proId: x.get("proId"),
	                                        itemId: Ext.getCmp('itemId').getValue(),
	                                        proName: Ext.getCmp('proName').getValue(),
	                                        proDesc: Ext.getCmp('proDesc').getValue(),
	                                        purPrice: Ext.getCmp('purPrice').getValue(),
	                                        oriPrice: Ext.getCmp('oriPrice').getValue(),
	                                        disPrice: Ext.getCmp('disPrice').getValue(),
	                                        stock: Ext.getCmp('stock').getValue(),
	                                        recommendation: Ext.getCmp('recommendation').getValue()
	                                    },
	                                    method: 'post',
	                                    success: function(response, options) {
	                                        var obj = Ext.util.JSON.decode(response.responseText);
	                                        if (obj.success == true) {
	                                            Ext.Msg.alert("提示", "修改商品信息成功");
	                                            pStore.load();
	                                            win.close();
	                                        } else {
	                                            Ext.Msg.alsert("提示", "修改商品信息失败");
	                                            win.close();
	                                        }
	                                    },
	                                    failure: function(response, options) {
	                                        Ext.Msg.alsert("提示", "修改商品信息失败");
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
	                                Ext.getCmp('itemId').setValue(x.get('itemId'));
	                                Ext.getCmp('proName').setValue(x.get('proName'));
	                                Ext.getCmp('proDesc').setValue(x.get('proDesc'));
	                                Ext.getCmp('purPrice').setValue(x.get('purPrice'));
	                                Ext.getCmp('oriPrice').setValue(x.get('oriPrice'));
	                                Ext.getCmp('disPrice').setValue(x.get('disPrice'));
	                                Ext.getCmp('stock').setValue(x.get('stock'));
	                                Ext.getCmp('recommendation').setValue(flag);
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
	            function delPro() {
	                var x = proGrid.getSelectionModel().getSelected();
                    if (x == null) {
                        Ext.MessageBox.alert('提示', '至少选择一行');
                        return false;
                    } else {
                        Ext.MessageBox.confirm("提示","你确定要删除这个商品吗?",function(btn){
                            if (btn == 'yes') {
                                Ext.Ajax.request({
                                    url: '/shopping/product?action=del',
                                    params: {
                                        proId: x.get("proId")
                                    },
                                    success: function(response, options) {
                                        var obj = Ext.util.JSON.decode(response.responseText);
                                        if(obj.success == true) {
                                            Ext.Msg.alert("提示","商品删除成功");
                                            pStore.load();
                                        } else {
                                            Ext.Msg.alert("提示","商品删除失败");
                                        }
                                    },
                                    failure: function(response, options) {
                                        Ext.Msg.alert("提示","商品删除失败");
                                    }
                                });
                            }
	                    });
                    }
	            }
	            
	            function proStoreLoad(combo, record,index) {
	                pStore.load({params:{itemId: record.get("itemId"), start: 0, limit: 10, query: null }});
	            }
	            
	            function price(value) {
	                return value + " 元";
	            }
	            
	            function stock(value) {
	                return value + " 件";
	            }
	            
	            function recommend(value) {
	                if (value == 0){
	                    return "否"; 
	                } else {
	                    return "是";
	                }
	            }
	        });
	    </script>
	    <div id="proPanel"></div>
	    <div id="proGrid"></div>
	</body>
</html>