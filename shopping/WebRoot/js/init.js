Ext.BLANK_IMAGE_URL='resources/images/default/s.gif';

Ext.QuickTips.init();
Ext.form.Field.prototype.msgTarget='side';
            
Ext.onReady(init);

var topPanel = new Ext.Panel({
    region:"north",
    height:100,
    tbar:[
        {pressed:false,text:'登录',handler:toLoginPage},
        {xtype:"tbseparator"},
        {pressed:false,text:'注册'}
    ]
});

var leftPanel = new Ext.Panel({
    title:"菜单",
    region:"west",
    width:180,
    collapsible:true,
    layout:"accordion",
    layoutConfig:{animate:true}
});

var tabPanel = new Ext.TabPanel({
    enableTabScroll:true,
    defaults: {autoScroll:true},
    region:"center",
    items:[
        {title:"首页",closable:false}
    ],
    activeTab:0,
    plugins: new Ext.ux.TabCloseMenu()
});

var view = new Ext.Viewport({
    enableTabScroll:true,
    layout:"border",
    items:[
        topPanel,
        leftPanel,
        tabPanel
    ]
});
            
function init(){
    view.show();
}

function toLoginPage() {
	window.location.href = "user/login.jsp";
}