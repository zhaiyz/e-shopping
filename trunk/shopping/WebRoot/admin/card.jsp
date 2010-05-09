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
                    {name: 'cardDatetime', type: 'date' ,dateFormat:"Y-m-d H:i:s"}
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
                        {header:"面值", dataIndex:"cardValue"},
                        {header:"卡状态", dataIndex:"cardFlag"},
                        {
                            id: 'cardDatetime',
                            header:"制卡时间",
                            dataIndex:"cardDatetime",
                            width: 130,
                            sortable: true,
                            renderer:Ext.util.Format.dateRenderer('Y-m-d H:i:s')
                        }
                    ],
                    ds:cardStore,
                    sm: new Ext.grid.RowSelectionModel({ singleSelect: true }),
                    autoExpandColumn: 'cardDatetime',
                    bbar: new Ext.PagingToolbar({
                        pageSize:10,
                        store:cardStore,
                        displayInfo:true,
                        displayMsg:'显示第{0}条到{1}条记录，一共{2}条',
                        emptyMsg:"没有记录"
                    })
                });
                
                var cardState = [
                    ['-1', '全部'],
                    ['0', '未使用'],
                    ['1', '已使用']
                ];
                
                var cardValue = [
                    ['-1', '全部'],
                    ['0', '50'],
                    ['1', '100'],
                    ['2', '200']
                ];
                
                //主面板
	            var cardPane = new Ext.Panel({
	                renderTo: "cardPanel",
	                tbar: [
	                    {text: "添加"},
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
	                        triggerAction: 'all'
	                    }),
	                    '面值:', ' ',
	                    new Ext.form.ComboBox({
	                        emptyText:'请选择',
	                        width: 70,
	                        mode: 'local',
	                        readOnly: true,
	                        store: new Ext.data.SimpleStore({
	                            fields: ['value', 'text'],
	                            data: cardValue
	                        }),
	                        displayField:'text',
	                        valueField:'value',
	                        triggerAction: 'all'
	                    }),
	                ]
	            });
	        });
	    </script>
		<div id="cardPanel"></div>
		<div id="cardGrid"></div>
	</body>
</html>