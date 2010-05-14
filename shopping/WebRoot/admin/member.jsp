<%@ page contentType="text/html;charset=utf-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
		<title>管理员首页</title>
	<body>
		<script type="text/javascript">
	        Ext.onReady(function(){
	            var memMember = Ext.data.Record.create([
	                {name: 'userId', type: 'int'},
	                {name: 'userName', type: 'string'},
                    {name: 'payed', type: 'float'},
                    {name: 'email', type: 'string'},
                    {name: 'phone', type: 'string'},
                    {name: 'userState', type: 'int'},
                    {name: 'regDatetime', type: 'date' ,dateFormat:"Y-m-d H:i:s"}
                ]);
                
                var memStore = new Ext.data.JsonStore({
                    url:"/shopping/user?action=all",
                    totalProperty:'total',
                    root:'list',
                    fields: memMember,
                    baseParams: {
                        start: 0,
                        limit: 10
                    }
                });
                
                //加载数据
                memStore.load();
                
                //定义一个itemGrid
                var memGrid = new Ext.grid.GridPanel({
                    renderTo:"memGrid",
                    title:"会员列表",
                    layout: "fit",
                    height: Ext.getBody().getHeight() - 160,
                    columns:[
                        new Ext.grid.RowNumberer({header:'序号',width:35}),
                        {header:"会员名称", dataIndex:"userName"},
                        {header:"消费额", dataIndex:"payed", renderer: payed},
                        {header:"邮箱地址", dataIndex:"email", width:200},
                        {header:"电话", dataIndex:"phone"},
                        {header:"状态", dataIndex:"userState", renderer: userState},
                        {
                            id: 'regDatetime',
                            header:"注册时间",
                            dataIndex:"regDatetime",
                            width: 130,
                            sortable: true,
                            renderer:Ext.util.Format.dateRenderer('Y-m-d H:i:s')
                        }
                    ],
                    ds:memStore,
                    sm: new Ext.grid.RowSelectionModel({ singleSelect: true }),
                    autoExpandColumn: 'regDatetime',
                    bbar: new Ext.PagingToolbar({
                        pageSize:10,
                        store:memStore,
                        displayInfo:true,
                        displayMsg:'显示第{0}条到{1}条记录，一共{2}条',
                        emptyMsg:"没有记录"
                    })
                });
                
                //消费额
                function payed(value) {
                    return value + " 元";
                }
                
                //状态
                function userState(value) {
                    return value == 0 ? "正常" : "禁言";
                }
                
                //给这个数据框添加事件
                memGrid.addListener('celldblclick',function(grid, rowIndex, columnIndex, e){
                    var s=grid.getStore();
                    var x=s.getAt(rowIndex);
                    var state = x.get("userState") == 0 ? "正常" : "禁言";
                    
                    var action = x.get("userState") == 0 ? "禁言" : "解禁";
                    
                    var win = new Ext.Window({
                        title: '会员信息',
                        width: 300,
                        height: 250,
                        modal: true,
                        layout: 'form',
                        bodyStyle: 'padding:10px 10px 10px 10px',
                        labelWidth: 60,
                        items: [
                            new Ext.form.TextField({
                                fieldLabel: '会员名称',
                                width: 200,
                                name: 'userName',
                                id: 'userName',
                                value: x.get("userName")
                            }),
                            new Ext.form.TextField({
                                fieldLabel: '消费额',
                                width: 200,
                                name: 'payed',
                                id: 'payed',
                                value: x.get("payed")
                            }),
                            new Ext.form.TextField({
                                fieldLabel: '邮箱地址',
                                width: 200,
                                name: 'email',
                                id: 'email',
                                value: x.get("email")
                            }),
                            new Ext.form.TextField({
                                fieldLabel: '电话',
                                width: 200,
                                name: 'phone',
                                id: 'phone',
                                value: x.get("phone")
                            }),
                            new Ext.form.TextField({
                                fieldLabel: '状态',
                                width: 200,
                                name: 'userState',
                                id: 'userState',
                                value: state
                            }),
                            new Ext.form.TextField({
                                fieldLabel: '注册时间',
                                width: 200,
                                name: 'regDatetime',
                                id: 'regDatetime',
                                readOnly: true,
                                value: x.get("regDatetime").dateFormat('Y-m-d H:i:s')
                            })
                        ],
                        buttons: [{
                            text: action,
                            icon: '../resources/images/Icon_128.ico',
                            width: 85,
                            height: 27,
                            handler: function() {
                                Ext.Ajax.request({
                                    url: '/shopping/user?action=update',
                                    params: {
                                        userId: x.get("userId")
                                    },
                                    success: function(response, options) {
                                        var obj = Ext.util.JSON.decode(response.responseText);
                                        if (obj.success == true) {
                                            Ext.Msg.alert("提示","修改用户状态成功");
                                            memStore.load();
                                            win.close();
                                        } else {
                                            Ext.Msg.alert("提示","修改用户状态失败");
                                            win.close();
                                        }
                                    },
                                    failure: function(response, options) {
                                        Ext.Msg.alert("提示","修改用户状态失败");
                                        win.close();
                                    }
                                });
                            }
                        }]
                    });
                    
                    win.show();  
                });
	        
	            var memPanel = new Ext.Panel({
	                renderTo: "memPanel",
	                tbar: [
	                    '会员名称:', ' ',
                        new Ext.ux.form.SearchField({
                          store: memStore,
                          width: 150
                        })
	                ]
	            });
	        });
	    </script>
		<div id="memPanel"></div>
		<div id="memGrid"></div>
	</body>
</html>