<%@ page contentType="text/html;charset=utf-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
		<title>管理员首页</title>
	<body>
		<script type="text/javascript">
	        Ext.onReady(function(){
	            var benMember = Ext.data.Record.create([
	                {name: 'sales', type: 'float'},
	                {name: 'benefit', type: 'float'}
                ]); 
                
                var benStore = new Ext.data.JsonStore({
                    url:"/shopping/benefit?action=show",
                    root:'list',
                    fields:benMember,
                    baseParams: {
                        state: 0
                    }
                });
                
                //加载数据
                benStore.load();
                
                var benGrid = new Ext.grid.GridPanel({
                    renderTo:"benGrid",
                    layout: "fit",
                    title: '收益状况',
                    height: Ext.getBody().getHeight() - 160,
                    columns:[
                        {header:"营业额", dataIndex:"sales", renderer: price},
                        {header:"总收益", dataIndex:"benefit", renderer: price}
                    ],
                    ds:benStore,
                    sm: new Ext.grid.RowSelectionModel({ singleSelect: true })
                });
                
                function price(value) {
                    return value + " 元";
                }
                
	            var benPanel = new Ext.Panel({
	                renderTo: "benPanel",
	                layout: 'form',
	                tbar: [
	                    '开始时间:', ' ',
	                    new Ext.form.DateField({
	                        format: 'Y-m-d',
	                        emptyText: '开始时间',
	                        name: 'starttime',
	                        readOnly: true,
	                        allowBlank: false,
	                        id: 'starttime'
	                    }),
	                    {xtype:"tbseparator"},
	                    '结束时间:', ' ',
	                    new Ext.form.DateField({
	                        format: 'Y-m-d',
	                        emptyText: '结束时间',
	                        readOnly: true,
	                        allowBlank: false,
	                        name: 'end',
	                        id: 'end'
	                    }),
	                    {xtype:"tbseparator"},
	                    {
	                        text: '查看',
	                        handler: function() {
	                            var start = Ext.getCmp('starttime').getRawValue();
	                            var end = Ext.getCmp('end').getRawValue();
	                            if (start != "" && end != "")
	                                benStore.load({params:{start: start, end: end, state: 1 }});
	                        }
	                    }
	                ]
	            });
            });
        </script>
	    <div id="benPanel"></div>
	    <div id="benGrid"></div>
	</body>
</html>