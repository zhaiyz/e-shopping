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
                    {name: 'userName', type: 'string'},
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
                        {header:"订单号", dataIndex:"orderNum", width:120, renderer: orderNum},
                        {header:"用户名", dataIndex:"userName"},
                        {header:"付款方式", dataIndex:"payment", renderer: payment},
                        {header:"邮递方式", dataIndex:"post", renderer: post},
                        {header:"总费用", dataIndex:"totalPrice", renderer: totalPrice},
                        {header:"订单状态", dataIndex:"orderState", renderer: orderState},
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
                
                //用户名显示，这个有点复杂，我看能不能实现出来
                function userName(value) {
                    var userName;
                    
                    return userName;
                }
                
                //订单号
                function orderNum(value) {
                    return "<font color='red'>TM</font>" + value;
                }
                
                //付款方式
                function payment(value) {
                    if (value == 0) {
                        return "先付款后发货";
                    } else {
                        return "货到付款";
                    }
                }
                
                //邮递方式
                function post(value) {
                    if (value == 0) {
                        return "平邮";
                    } else {
                        return "快递";
                    }
                }
                
                //总价格
                function totalPrice(value) {
                    return value + " 元";
                }
                
                //订单状态
                function orderState(value) {
                    if (value == 0) {
                        return "未发货";
                    } else if (value == 1) {
                        return "已发货";
                    } else {
                        return "已收货";
                    }
                }
                
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
	                    {text: "刷新", handler: function() {orderStore.load()}},
	                    {xtype:"tbseparator"},
	                    {text: "查看", handler: editOrder},
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
                    //把一些数量转换成内容
                        var payment = x.get('payment') == 0 ? '先货款后发贷' : '货到付款';
                        
                        var post = x.get('post') == 0 ? '平邮' : '快递';
                        
                        var orderState;
                        if (x.get('orderState') == 0) {
                            orderState = "未发货";
                        } else if (x.get('orderState') == 1) {
                            orderState = "已发货";
                        } else {
                            orderState = "已收货";
                        }
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
                                readOnly: true,
                                value: 'TM' + x.get("orderNum")
                            }),
                            new Ext.form.TextField({
                                fieldLabel: '用户名',
                                width: 200,
                                name: 'userName',
                                readOnly: true,
                                value: x.get("userName")
                            }),
                            new Ext.form.TextField({
                                fieldLabel: '付款方式',
                                width: 200,
                                name: 'payment',
                                readOnly: true,
                                value: payment
                            }),
                            new Ext.form.TextField({
                                fieldLabel: '邮递方式',
                                width: 200,
                                name: 'post',
                                readOnly: true,
                                value: post
                            }),
                            new Ext.form.TextField({
                                fieldLabel: '总费用',
                                width: 200,
                                readOnly: true,
                                name: 'totalPrice',
                                value: x.get("totalPrice")
                            }),
                            new Ext.form.TextField({
                                fieldLabel: '订单状态',
                                width: 200,
                                readOnly: true,
                                name: 'orderState',
                                value: orderState
                            }),
                            new Ext.form.TextField({
                                fieldLabel: '订货时间',
                                width: 200,
                                readOnly: true,
                                name: 'orderDatetime',
                                readOnly: true,
                                value: x.get("orderDatetime").dateFormat('Y-m-d H:i:s')
                            })
                        ],
                        buttons: [{
                            text: '订单明细',
                            icon: '../resources/images/Icon_001.ico',
                            width: 85,
                            height: 27,
	                        handler: function() {
	                            showInfo(x.get("orderId"));
	                        }
                        },{
                            text: '寄送信息',
                            icon: '../resources/images/Icon_008.ico',
                            width: 85,
                            height: 27,
                            handler: function() {
	                            showContact(x.get("orderId"));
	                        }
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
                        //把一些数量转换成内容
                        var payment = x.get('payment') == 0 ? '先货款后发贷' : '货到付款';
                        
                        var post = x.get('post') == 0 ? '平邮' : '快递';
                        
                        var orderState;
                        if (x.get('orderState') == 0) {
                            orderState = "未发货";
                        } else if (x.get('orderState') == 1) {
                            orderState = "已发货";
                        } else {
                            orderState = "已收货";
                        }
                    
                        var win = new Ext.Window({
	                        title: '订单',
	                        width: 300,
	                        height: 280,
	                        modal: true,
	                        layout: 'form',
	                        bodyStyle: 'padding:10px 10px 10px 10px',
	                        labelWidth: 60,
	                        items: [
	                            new Ext.form.Hidden({
	                                fieldLabel: '订单主键',
	                                name: 'orderId',
	                                readOnly: true,
	                                value: x.get("orderId")
	                            }),
	                            new Ext.form.TextField({
	                                fieldLabel: '订单号',
	                                width: 200,
	                                name: 'orderNum',
	                                readOnly: true,
	                                value: 'TM' + x.get("orderNum")
	                            }),
	                            new Ext.form.TextField({
	                                fieldLabel: '用户名',
	                                width: 200,
	                                name: 'userName',
	                                readOnly: true,
	                                value: x.get("userName")
	                            }),
	                            new Ext.form.TextField({
	                                fieldLabel: '付款方式',
	                                width: 200,
	                                name: 'payment',
	                                readOnly: true,
	                                value: payment
	                            }),
	                            new Ext.form.TextField({
	                                fieldLabel: '邮递方式',
	                                width: 200,
	                                name: 'post',
	                                readOnly: true,
	                                value: post
	                            }),
	                            new Ext.form.TextField({
	                                fieldLabel: '总费用',
	                                width: 200,
	                                readOnly: true,
	                                name: 'totalPrice',
	                                value: x.get("totalPrice")
	                            }),
	                            new Ext.form.TextField({
	                                fieldLabel: '订单状态',
	                                width: 200,
	                                readOnly: true,
	                                name: 'orderState',
	                                value: orderState
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
	                            icon: '../resources/images/Icon_001.ico',
	                            width: 85,
	                            height: 27,
	                            handler: function() {
	                                showInfo(x.get("orderId"));
	                            }
	                        },{
	                            text: '寄送信息',
	                            icon: '../resources/images/Icon_008.ico',
	                            width: 85,
	                            height: 27,
	                            handler: function() {
	                                showContact(x.get("userId"));
	                            }
	                        }]
	                    });
	                    
	                    win.show(); 
                    } 
	            }
	            
	            function showInfo(orderId) {
	                var mem = Ext.data.Record.create([
	                    {name: 'proName', type: 'string'},
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
	                
	                function price(value) {
	                    return value + " 元";
	                }
	                
	                function amount(value) {
	                    return value + " 件";
	                }
                    
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
                                   {id: 'proName', header:"商品名称", dataIndex:"proName"},
                                   {header:"商品数量", dataIndex:"amount", width: 70, renderer: amount},
                                   {id: 'price', header:"商品总价", dataIndex:"price", renderer: price}
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
	                        var freetime;
	                        if (con.freetime == 0) {
	                            freetime = "全周";
	                        } else if (con.freetime == 1) {
	                            freetime = "周一至周五";
	                        } else {
	                            freetime = "周末";
	                        }
	                        var win = new Ext.Window({
	                            title: '寄送信息',
	                            width: 300,
	                            height: 200,
	                            modal: true,
	                            layout: 'form',
	                            bodyStyle: 'padding:10px 10px 10px 10px',
	                            labelWidth: 60,
	                            items:[
	                                new Ext.form.TextField({
		                                fieldLabel: '姓名',
		                                width: 200,
		                                name: 'name',
		                                readOnly: true,
		                                value: con.name
	                                }),
	                                new Ext.form.TextField({
		                                fieldLabel: '地址',
		                                width: 200,
		                                name: 'address',
		                                readOnly: true,
		                                value: con.address
	                                }),
	                                new Ext.form.TextField({
		                                fieldLabel: '邮编',
		                                width: 200,
		                                name: 'postcode',
		                                readOnly: true,
		                                value: con.postcode
	                                }),
	                                new Ext.form.TextField({
		                                fieldLabel: '电话',
		                                width: 200,
		                                name: 'telphone',
		                                readOnly: true,
		                                value: con.telphone
	                                }),
	                                new Ext.form.TextField({
		                                fieldLabel: '空闲时间',
		                                width: 200,
		                                name: 'freetime',
		                                readOnly: true,
		                                value: freetime
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