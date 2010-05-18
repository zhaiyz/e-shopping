<%@ page contentType="text/html;charset=utf-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
		<title>管理员首页</title>
	<body>
		<script type="text/javascript">
	        Ext.onReady(function(){
	            var benMember = Ext.data.Record.create([
	                {name: 'catId', type: 'int'},
	                {name: 'catName', type: 'string'},
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
                
                var benMember2 = Ext.data.Record.create([
	                {name: 'itemName', type: 'string'},
	                {name: 'sales', type: 'float'},
	                {name: 'benefit', type: 'float'}
                ]); 
                
                var benStore2 = new Ext.data.JsonStore({
                    url:"/shopping/benefit?action=show2",
                    root:'list',
                    fields:benMember2
                });
                
                //加载数据
                benStore2.load();
                
                var benMember3 = Ext.data.Record.create([
	                {name: 'sales', type: 'float'},
	                {name: 'benefit', type: 'float'}
                ]); 
                
                var benStore3 = new Ext.data.JsonStore({
                    url:"/shopping/benefit?action=show3",
                    root:'list',
                    fields:benMember3,
                    baseParams: {
                        state: 0
                    }
                });
                
                //加载数据
                benStore3.load();
                
                var benGrid = new Ext.grid.GridPanel({
                    renderTo:"benGrid",
               //     width: 500,
                    title: '大类营业状况',
                    height: Ext.getBody().getHeight() - 170,
                    columns:[
                        {header:"大类名称", dataIndex:"catName"},
                        {header:"营业额", dataIndex:"sales", renderer: price},
                        {header:"总收益", dataIndex:"benefit", renderer: price}
                    ],
                    ds:benStore,
                    sm: new Ext.grid.RowSelectionModel({ singleSelect: true })
                });
                
                var start;
                var end;
                
                //给这个数据框添加事件
                benGrid.addListener('cellclick',function(grid, rowIndex, columnIndex, e){
                    var s=grid.getStore();
                    var x=s.getAt(rowIndex);
                    benStore2.load({params:{start: start, end: end, catId: x.get("catId")}});
                });
                
                var benGrid2 = new Ext.grid.GridPanel({
                    renderTo:"benGrid2",
                //    width: 500,
                    title: '小类营业状况',
                    height: Ext.getBody().getHeight() - 170,
                    columns:[
                        {header:"小类名称", dataIndex:"itemName"},
                        {header:"营业额", dataIndex:"sales", renderer: price},
                        {header:"总收益", dataIndex:"benefit", renderer: price}
                    ],
                    ds:benStore2,
                    sm: new Ext.grid.RowSelectionModel({ singleSelect: true })
                });
                
                var benGrid3 = new Ext.grid.GridPanel({
                    renderTo:"benGrid3",
                //    width: 500,
                    title: '总体营业状况',
                    height: Ext.getBody().getHeight() - 170,
                    columns:[
                        {header:"营业额", dataIndex:"sales", renderer: price},
                        {header:"总收益", dataIndex:"benefit", renderer: price}
                    ],
                    ds:benStore3,
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
	                            start = Ext.getCmp('starttime').getRawValue();
	                            end = Ext.getCmp('end').getRawValue();
	                            if (start != "" && end != "") {
	                                benStore.load({params:{start: start, end: end, state: 1 }});
	                                benStore2.load({params:{start: start, end: end, state: 1 }});
	                                benStore3.load({params:{start: start, end: end, state: 1 }});
	                            }
	                        }
	                    }
	                ]
	            });
            });
        </script>
	    <div id="benPanel"></div>
	    <table>
	        <tr>
	            <td id="benGrid3"></td>
	            <td id="benGrid"></td>
	            <td id="benGrid2"></td>
	        </tr>
	    </table>
	</body>
</html>