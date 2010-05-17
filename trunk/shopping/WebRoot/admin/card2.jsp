<%@ page contentType="text/html;charset=utf-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
		<title>管理员首页</title>
	<body>
		<script type="text/javascript">
	        Ext.onReady(function(){
	            //定义store里面的成员
	            var cMember = Ext.data.Record.create([
                    {name: 'state', type: 'int'},
                    {name: 'number', type: 'int'},
                    {name: 'value', type: 'float'}
                ]);
                
                //定义store
                var cStore = new Ext.data.JsonStore({
                    url:"/shopping/card?action=analysis",
                    totalProperty:'total',
                    root:'list',
                    fields:cMember
                });
                
                //加载数据
                cStore.load();
                
                //定义一个grid
                var cGrid = new Ext.grid.GridPanel({
                    renderTo:"cGrid",
                    title:"充值卡列表",
                    layout: "fit",
                    height: Ext.getBody().getHeight() - 130,
                    columns:[
                        new Ext.grid.RowNumberer({header:'序号',width:35}),
                        {header:"卡状态", dataIndex:"state", renderer: state},
                        {header:"张数", dataIndex:"number", renderer: number},
                        {header:"总金额", dataIndex:"value", renderer: cardValue}
                    ],
                    ds:cStore,
                    sm: new Ext.grid.RowSelectionModel({ singleSelect: true }),
                    bbar: new Ext.PagingToolbar({
                        pageSize:10,
                        store:cStore,
                        displayInfo:true
                    })
                });
                
                function number(value) {
                    return value + " 张";
                }
                
                function state(value) {
                    if (value == 0) {
                        return "未使用";
                    } else {
                        return "已使用";
                    }
                }
                
                //面值
                function cardValue(value) {
                    return value + " 元";
                }
	        });
	    </script>
		<div id="cGrid"></div>
	</body>
</html>