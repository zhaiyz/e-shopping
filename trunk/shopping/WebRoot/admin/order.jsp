<%@ page contentType="text/html;charset=utf-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
		<title>管理员首页</title>
	<body>
	    <script type="text/javascript">
	        Ext.onReady(function(){
	             //定义orderStore里面的成员
	            var orderMember = Ext.data.Record.create([
	                {name: 'orderId', type: 'int'},
                    {name: 'userId', type: 'int'},
                    {name: 'conId', type: 'int'},
                    {name: 'orderNum', type: 'string'},
                    {name: 'payment', type: 'int'},
                    {name: 'post', type: 'int'},
                    {name: 'totalPrice', type: 'float'},
                    {name: 'orderState', type: 'int'},
                    {name: 'orderDatetime', type: 'date' ,dateFormat:"Y-m-d H:i:s"}
                ]);
                
                //定义orderStore
                var orderStore = new Ext.data.JsonStore({
                    url:"/shopping/order?action=all",
                    root:'list',
                    fields:orderMember,
                    baseParams: {
                        start: 0,
                        limit: 10
                    }
                });
                
                //数据加载
                orderStore.load();
                
                //定义一个数据面板
                var orderGrid = new Ext.grid.GridPanel({
                    renderTo:"orderGrid",
                    title:"订单列表",
                    layout: "fit",
                    height: Ext.getBody().getHeight() - 160,
                    columns:[
                        new Ext.grid.RowNumberer({header:'序号',width:35}),
                        {header:"订单号", dataIndex:"orderNum"},
                        {header:"用户名", dataIndex:"userId"},
                        {header:"付款方式", dataIndex:"payment"},
                        {header:"邮递方式", dataIndex:"post"},
                        {header:"总费用", dataIndex:"totalPrice"},
                        {header:"订单状态", dataIndex:"orderState"},
                        {
                            id: 'orderDatetome',
                            header:"订货时间",
                            dataIndex:"orderDatetime",
                            width: 130,
                            sortable: true,
                            renderer:Ext.util.Format.dateRenderer('Y-m-d H:i:s')
                        }
                    ],
                    ds: orderStore,
                    sm: new Ext.grid.RowSelectionModel({ singleSelect: true }),
                    autoExpandColumn: 'orderDatetome',
                    bbar: new Ext.PagingToolbar({
                        pageSize:10,
                        store:orderStore,
                        displayInfo:true,
                        displayMsg:'显示第{0}条到{1}条记录，一共{2}条',
                        emptyMsg:"没有记录"
                    })
                });
                
                var stateDate = [
                    ['-1', '全部'],
                    ['0', '未发货'],
                    ['1', '已发货'],
                    ['2', '已收货']
                ];
                
                //定义一个面板
                var orderPane = new Ext.Panel({
	                renderTo: "orderPanel",
	                tbar: [
	                    {text: "编辑", handler: editOrder},
	                    {xtype:"tbseparator"},
	                    '订单状态:', ' ',
	                    new Ext.form.ComboBox({
	                        emptyText:'订单状态',
	                        width: 80,
	                        mode: 'local',
	                        readOnly: true,
	                        store: new Ext.data.SimpleStore({
	                            fields: ['value', 'text'],
	                            data: stateDate
	                        }),
	                        displayField:'text',
	                        valueField:'value',
	                        triggerAction: 'all',
	                        listeners: {
	                            'select': function (combo, record, index){
	                                orderStore.load({params:{state: record.get("value"), start: 0, limit: 10}});
	                            }
	                        }
	                    })
	                ]
	            });
	            
	            //给这个数据框添加事件
                orderGrid.addListener('celldblclick',function(grid, rowIndex, columnIndex, e){
                    var s=grid.getStore();
                    var x=s.getAt(rowIndex);
                    var win = new Ext.Window({
                        title: '订单',
                        width: 300,
                        height: 280,
                        modal: true,
                        layout: 'form',
                        bodyStyle: 'padding:10px 10px 10px 10px',
                        labelWidth: 60,
                        items: [
                            new Ext.form.TextField({
                                fieldLabel: '订单号',
                                width: 200,
                                name: 'orderNum',
                                value: x.get("orderNum")
                            }),
                            new Ext.form.TextField({
                                fieldLabel: '用户名',
                                width: 200,
                                name: 'userId',
                                value: x.get("userId")
                            }),
                            new Ext.form.TextField({
                                fieldLabel: '付款方式',
                                width: 200,
                                name: 'payment',
                                value: x.get("payment")
                            }),
                            new Ext.form.TextField({
                                fieldLabel: '邮递方式',
                                width: 200,
                                name: 'post',
                                value: x.get("post")
                            }),
                            new Ext.form.TextField({
                                fieldLabel: '总费用',
                                width: 200,
                                name: 'totalPrice',
                                value: x.get("totalPrice")
                            }),
                            new Ext.form.TextField({
                                fieldLabel: '订单状态',
                                width: 200,
                                name: 'orderState',
                                value: x.get("orderState")
                            }),
                            new Ext.form.TextField({
                                fieldLabel: '订货时间',
                                width: 200,
                                name: 'orderDatetime',
                                readOnly: true,
                                value: x.get("orderDatetime").dateFormat('Y-m-d H:i:s')
                            })
                        ],
                        buttons: [{
                            text: '订单明细',
                            icon: '../resources/images/Icon_007.ico',
                            width: 85,
                            height: 27,
	                        handler: function() {
	                            showInfo(x.get("orderId"));
	                        }
                        },{
                            text: '寄送信息',
                            icon: '../resources/images/Icon_106.ico',
                            width: 85,
                            height: 27,
                            handler: function() {
	                            showContact(x.get("orderId"));
	                        }
                        },{
                            text: '修改状态',
                            icon: '../resources/images/Icon_043.ico',
                            width: 85,
                            height: 27
                        }]
                    });
                    
                    win.show();  
                });
	            
	            //定义编辑订单函数
	            function editOrder() {
	                var x = orderGrid.getSelectionModel().getSelected();
                    if (x == null) {
                        Ext.MessageBox.alert('提示', '至少选择一行');
                        return false;
                    } else {
                        //通过recordtoedit 取值了 比如说有id这个直接
                        var win = new Ext.Window({
	                        title: '订单',
	                        width: 300,
	                        height: 280,
	                        modal: true,
	                        layout: 'form',
	                        bodyStyle: 'padding:10px 10px 10px 10px',
	                        labelWidth: 60,
	                        items: [
	                            new Ext.form.TextField({
	                                fieldLabel: '订单号',
	                                width: 200,
	                                name: 'orderNum',
	                                value: x.get("orderNum")
	                            }),
	                            new Ext.form.TextField({
	                                fieldLabel: '用户名',
	                                width: 200,
	                                name: 'userId',
	                                value: x.get("userId")
	                            }),
	                            new Ext.form.TextField({
	                                fieldLabel: '付款方式',
	                                width: 200,
	                                name: 'payment',
	                                value: x.get("payment")
	                            }),
	                            new Ext.form.TextField({
	                                fieldLabel: '邮递方式',
	                                width: 200,
	                                name: 'post',
	                                value: x.get("post")
	                            }),
	                            new Ext.form.TextField({
	                                fieldLabel: '总费用',
	                                width: 200,
	                                name: 'totalPrice',
	                                value: x.get("totalPrice")
	                            }),
	                            new Ext.form.TextField({
	                                fieldLabel: '订单状态',
	                                width: 200,
	                                name: 'orderState',
	                                value: x.get("orderState")
	                            }),
	                            new Ext.form.TextField({
	                                fieldLabel: '订货时间',
	                                width: 200,
	                                name: 'orderDatetime',
	                                readOnly: true,
	                                value: x.get("orderDatetime").dateFormat('Y-m-d H:i:s')
	                            })
	                        ],
	                        buttons: [{
	                            text: '订单明细',
	                            icon: '../resources/images/Icon_007.ico',
	                            width: 85,
	                            height: 27,
	                            handler: function() {
	                                showInfo(x.get("orderId"));
	                            }
	                        },{
	                            text: '寄送信息',
	                            icon: '../resources/images/Icon_106.ico',
	                            width: 85,
	                            height: 27,
	                            handler: function() {
	                                showContact(x.get("userId"));
	                            }
	                        },{
	                            text: '修改状态',
	                            icon: '../resources/images/Icon_043.ico',
	                            width: 85,
	                            height: 27
	                        }]
	                    });
	                    
	                    win.show(); 
                    } 
	            }
	            
	            function showInfo(orderId) {
	                var mem = Ext.data.Record.create([
	                    {name: 'proId', type: 'int'},
	                    {name: 'amount', type: 'int'},
	                    {name: 'price', type: 'float'}
                    ]); 
                    
                    var s = new Ext.data.JsonStore({
	                    url:"/shopping/info?action=list",
	                    root:'list',
	                    fields: mem,
	                    baseParams: {
	                        orderId: orderId
	                    }
	                });
	                
	                s.load();
                    
	                var win = new Ext.Window({
	                    title: '订单明细',
	                    width: 300,
	                    height: 300,
	                    modal: false,
	                    layout: 'fit',
	                    items: [
	                        new Ext.grid.GridPanel({
	                            columns:[
                                   new Ext.grid.RowNumberer({header:'序号',width:35}),
                                   {header:"商品名称", dataIndex:"proId"},
                                   {header:"商品数量", dataIndex:"amount", width: 70},
                                   {id: 'price', header:"商品总价", dataIndex:"price"}
                                ],
                                ds:s,
                                sm: new Ext.grid.RowSelectionModel({ singleSelect: true }),
                    			autoExpandColumn: 'price'
	                        })
	                    ]
	                });
	                
	                win.show();
	            }
	            
	            function showContact(orderId) {
	                Ext.Ajax.request({
	                    url: '/shopping/contact?action=show',
	                    params: {
	                        orderId: orderId
	                    },
	                    method: 'post',
	                    success: function(response, options) {
	                        var con = Ext.util.JSON.decode(response.responseText).list;
	                        var win = new Ext.Window({
	                            title: '寄送信息',
	                            width: 300,
	                            height: 200,
	                            modal: false,
	                            layout: 'form',
	                            bodyStyle: 'padding:10px 10px 10px 10px',
	                            labelWidth: 60,
	                            items:[
	                                new Ext.form.TextField({
		                                fieldLabel: '姓名',
		                                width: 200,
		                                name: 'name',
		                                value: con.name
	                                }),
	                                new Ext.form.TextField({
		                                fieldLabel: '地址',
		                                width: 200,
		                                name: 'address',
		                                value: con.address
	                                }),
	                                new Ext.form.TextField({
		                                fieldLabel: '邮编',
		                                width: 200,
		                                name: 'postcode',
		                                value: con.postcode
	                                }),
	                                new Ext.form.TextField({
		                                fieldLabel: '电话',
		                                width: 200,
		                                name: 'telphone',
		                                value: con.telphone
	                                }),
	                                new Ext.form.TextField({
		                                fieldLabel: '空闲时间',
		                                width: 200,
		                                name: 'freetime',
		                                value: con.freetime
	                                })
	                            ]
	                        });
	                        
	                        win.show();
	                    }
	                });
	            }
	            
	        });
	    </script>
	    <div id="orderPanel"></div>
	    <div id="orderGrid"></div>
	</body>
</html>