Ext.ux.TabCloseMenu = function(){ 
    var tabs, menu, ctxItem; 
    this.init = function(tp){ 
        tabs = tp; 
        tabs.on('contextmenu', onContextMenu); 
    }; 
    
 function onContextMenu(ts, item, e){ 
        if(!menu){ // create context menu on first right click 
            menu = new Ext.menu.Menu([{ 
                id: tabs.id + '-close', 
                icon:'../resources/images/default/s.gif',
                text: '关闭标签', 
                handler : function(){ 
                    tabs.remove(ctxItem); 
                } 
            },{ 
                id: tabs.id + '-close-others', 
                icon:'../resources/images/default/s.gif',
                text: '关闭其他标签', 
                handler : function(){ 
                    tabs.items.each(function(item){ 
                        if(item.closable && item != ctxItem){ 
                            tabs.remove(item); 
                        } 
                    }); 
                } 
            },{ 
                id: tabs.id + '-close-all',
                icon:'../resources/images/default/s.gif', 
                text: '关闭全部标签', 
                handler : function(){ 
                    tabs.items.each(function(item){ 
                        if(item.closable){ 
                            tabs.remove(item); 
                        } 
                    }); 
                } 
            }]); 
        } 
        ctxItem = item; 
        var items = menu.items; 
        items.get(tabs.id + '-close').setDisabled(!item.closable); 
        var disableOthers = true; 
        tabs.items.each(function(){ 
            if(this != item && this.closable){ 
                disableOthers = false; 
                return false; 
            } 
        }); 
        items.get(tabs.id + '-close-others').setDisabled(disableOthers); 
        var disableAll = true; 
        tabs.items.each(function(){ 
            if(this.closable){ 
                disableAll = false; 
                return false; 
            } 
        }); 
        items.get(tabs.id + '-close-all').setDisabled(disableAll); 
        menu.showAt(e.getPoint()); 
    } 
};