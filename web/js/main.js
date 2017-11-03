/**
 * Created by chenliheng on 2017/11/3.
 */
$(function(){   // 页面元素都加载完成后，执行代码
    // 创建组件对象名命名
    var mydialog = $("#mydlg").dialog({
        width:300,
        height:220,
        title:"动态创建dialog",
        closable:false,
        buttons:[{
            text:'Save',
            handler:function(){
                mydialog.dialog({title:"内容已保存"});
            }
        },{
            text:'Close',
            handler:function(){
                // 调用dialog方法
                mydialog.dialog("destroy");

                showMaster();   // 打开主窗体
            }
        }]
    });
});

// 显示主窗体方法
function showMaster(){
    // body转换布局组件
    var pnlMaster = $("body").layout({
        fit:true
    });
    // layout添加3个布局用panel
    pnlMaster.layout("add", {region:"north",height:200});
    pnlMaster.layout("add", {region:"west", width:180});
    pnlMaster.layout("add", {region:"center"});
    // 动态为northPanel添加标题
    var pnlNorth = pnlMaster.layout("panel", "north");
    pnlNorth.append("<h1>XXX系统</h1>");
}