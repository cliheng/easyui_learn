
// 全局变量
var pnlMaster;

// 显示主窗体方法
function showMaster(){
    // body转换布局组件
    pnlMaster = $("body").layout({
        fit:true
    });
    // layout添加3个布局用panel
    pnlMaster.layout("add", {region:"north",height:200});
    pnlMaster.layout("add", {region:"west", width:180});
    pnlMaster.layout("add", {region:"center"});
    // 动态为northPanel添加标题
    var pnlNorth = pnlMaster.layout("panel", "north");
    pnlNorth.append($(".header"));
    // 动态添加左侧工具栏
    var pnlWest = pnlMaster.layout("panel","west");
    pnlWest.append($(".siderbar"));
}

// 切换右侧操作页面
function switchpage(pagename){
	// 找到右侧panel
	pnlCenter = pnlMaster.layout("panel","center");
	// 动态使用外部ｈｔｍｌ页面替换panel内容
	// 只加载页面中body中声明的内容
	pnlCenter.panel({href:"functionpage/" + pagename + ".html",fit:true});
}






