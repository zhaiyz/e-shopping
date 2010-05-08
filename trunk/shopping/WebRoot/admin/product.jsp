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
	               Ext.Msg.alert("提示","添加商品");
	            }
	            
	            //编辑小类方法
	            function editPro() {
	                Ext.Msg.alert("提示","编辑商品");
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