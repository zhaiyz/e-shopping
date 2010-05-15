<%@ page contentType="text/html;charset=utf-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
		<title>管理员首页</title>
	<body>
		<script type="text/javascript">
	        Ext.onReady(function(){
	            //定义store里面的成员
	            var cardMember = Ext.data.Record.create([
                    {name: 'cardNo', type: 'string'},
                    {name: 'cardPassword', type: 'string'},
                    {name: 'cardValue', type: 'float'},
                    {name: 'cardFlag', type: 'int'},
                    {name: 'cardDateTime', type: 'date' ,dateFormat:"Y-m-d H:i:s"}
                ]);
                
                //定义store
                var cardStore = new Ext.data.JsonStore({
                    url:"/shopping/card?action=list",
                    totalProperty:'total',
                    root:'list',
                    fields:cardMember,
                    baseParams: {
                        start: 0,
                        limit: 10
                    }
                });
                
                //加载数据
                cardStore.load();
                
                //定义一个grid
                var cardGrid = new Ext.grid.GridPanel({
                    renderTo:"cardGrid",
                    title:"充值卡列表",
                    layout: "fit",
                    height: Ext.getBody().getHeight() - 160,
                    columns:[
                        new Ext.grid.RowNumberer({header:'序号',width:35}),
                        {header:"卡号", dataIndex:"cardNo"},
                        {header:"卡密码", dataIndex:"cardPassword"},
                        {header:"面值", dataIndex:"cardValue", renderer: cardValue},
                        {header:"卡状态", dataIndex:"cardFlag", renderer: cardFlag},
                        {
                            id: 'cardDateTime',
                            header:"制卡时间",
                            dataIndex:"cardDateTime",
                            width: 130,
                            sortable: true,
                            renderer:Ext.util.Format.dateRenderer('Y-m-d H:i:s')
                        }
                    ],
                    ds:cardStore,
                    sm: new Ext.grid.RowSelectionModel({ singleSelect: true }),
                    autoExpandColumn: 'cardDateTime',
                    bbar: new Ext.PagingToolbar({
                        pageSize:10,
                        store:cardStore,
                        displayInfo:true,
                        displayMsg:'显示第{0}条到{1}条记录，一共{2}条',
                        emptyMsg:"没有记录"
                    })
                });
                
                //面值
                function cardValue(value) {
                    return value + " 元";
                }
                
                //卡状态
                function cardFlag(value) {
                    if (value == 0) {
                        return "未使用";
                    } else {
                        return "已使用";
                    }
                }
                
                //给这个数据框添加事件
                cardGrid.addListener('celldblclick',function(grid, rowIndex, columnIndex, e){
                    var s=grid.getStore();
                    var x=s.getAt(rowIndex);
                    var flag = x.get("cardFlag") == 0 ? "未使用" : "已使用";
                    var win = new Ext.Window({
                        title: '充值卡信息',
                        width: 300,
                        height: 180,
                        modal: true,
                        layout: 'form',
                        bodyStyle: 'padding:10px 10px 10px 10px',
                        labelWidth: 60,
                        items: [
                            new Ext.form.TextField({
                                fieldLabel: '卡号',
                                width: 200,
                                name: 'cardNo',
                                readOnly: true,
                                id: 'cardNo',
                                value: x.get("cardNo")
                            }),
                            new Ext.form.TextField({
                                fieldLabel: '卡密码',
                                width: 200,
                                readOnly: true,
                                name: 'cardPassword',
                                id: 'cardPassword',
                                value: x.get("cardPassword")
                            }),
                            new Ext.form.TextField({
                                fieldLabel: '面值',
                                width: 200,
                                readOnly: true,
                                name: 'cardValue',
                                id: 'cardValue',
                                value: x.get("cardValue")
                            }),
                            new Ext.form.TextField({
                                fieldLabel: '卡状态',
                                width: 200,
                                readOnly: true,
                                name: 'cardFlag',
                                id: 'cardFlag',
                                value: flag
                            }),
                            new Ext.form.TextField({
                                fieldLabel: '创建时间',
                                width: 200,
                                readOnly: true,
                                name: 'cardDateTime',
                                id: 'cardDateTime',
                                readOnly: true,
                                value: x.get("cardDateTime").dateFormat('Y-m-d H:i:s')
                            })
                        ]
                    });
                    
                    win.show();  
                });
                
                var cardState = [
                    ['-1', '全部'],
                    ['0', '未使用'],
                    ['1', '已使用']
                ];
                
                //主面板
	            var cardPane = new Ext.Panel({
	                renderTo: "cardPanel",
	                tbar: [
	                    {text: "刷新", handler: function() {cardStore.load()}},
	                    {xtype:"tbseparator"},
	                    {text: "添加", handler: addCard},
	                    {xtype:"tbseparator"},
	                    '卡状态:', ' ',
	                    new Ext.form.ComboBox({
	                        width: 70,
	                        mode: 'local',
	                        readOnly: true,
	                        store: new Ext.data.SimpleStore({
	                            fields: ['value', 'text'],
	                            data: cardState
	                        }),
	                        displayField:'text',
	                        valueField:'value',
	                        emptyText:'请选择',
	                        triggerAction: 'all',
	                        listeners: {
	                            'select': function (combo, record, index){
	                                cardStore.load({params:{state: record.get("value"), start: 0, limit: 10}});
	                            }
	                        }
	                    })
	                ]
	            });
	            
	            function addCard() {
	                var win = new Ext.Window({
                        title: '充值卡',
                        width: 300,
                        height: 180,
                        modal: true,
                        layout: 'form',
                        bodyStyle: 'padding:10px 10px 5px 5px',
                        labelWidth: 60,
                        items: [
                            new Ext.form.TextField({
                                fieldLabel: '充值卡号',
                                width: 200,
                                allowBlank: false,
                                blankText: '请填写卡号',
                                emptyText: '充值卡号',
                                name: 'cardNo',
                                id: 'cardNo'
                            }),
                            new Ext.form.TextField({
                                fieldLabel: '充值密码',
                                width: 200,
                                allowBlank: false,
                                blankText: '请填写密码',
                                emptyText: '充值卡密码',
                                name: 'cardPassword',
                                id: 'cardPassword'
                            }),
                            new Ext.form.NumberField({
                                fieldLabel: '面值',
                                width: 200,
                                allowBlank: false,
                                allowNegative: false,
                                name: 'cardValue',
                                id: 'cardValue'
                            })
                        ],
                        buttons: [{
                            text: '添加',
                            icon: '../resources/images/Icon_113.ico',
                            width: 85,
                            height: 27,
                            handler: function() {
                                Ext.Ajax.request({
                                    url: '/shopping/card?action=add',
                                    params: {
                                        cardNo: Ext.getCmp('cardNo').getValue(),
                                        cardPassword: Ext.getCmp('cardPassword').getValue(),
                                        cardValue: Ext.getCmp('cardValue').getValue()
                                    },
                                    success: function(response, options) {
                                        var obj = Ext.util.JSON.decode(response.responseText);
                                        if (obj.success == true) {
                                            Ext.Msg.alert("提示","添加充值卡成功");
                                            cardStore.load();
                                            win.close();
                                        } else {
                                            Ext.Msg.alert("提示", "添加充值卡失败");
                                            win.close();
                                        }
                                    },
                                    failure: function(response, options) {
                                        Ext.Msg.alert("提示", "添加充值卡失败");
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
                                Ext.getCmp("cardNo").setValue("");
                                Ext.getCmp("cardPassword").setValue("");
                                Ext.getCmp("cardValue").setValue("");
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
	        });
	    </script>
		<div id="cardPanel"></div>
		<div id="cardGrid"></div>
	</body>
</html>