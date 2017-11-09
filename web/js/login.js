$(function(){   // 页面元素都加载完成后，执行代码
	
	//加载cookie
	//concole.log(document.cookie);
	var arr = document.cookie.split("=");
	if(arr[0] == "user"){ //　通过身份验证
		showMaster();	// 直接显示主窗体
		$("#mydlg").remove();	// 移除dialog的div
	}else{
		// 创建组件对象名命名
	    var mydialog = $("#mydlg").dialog({
	        width:300,
	        height:220,
	        title:"系统用户登录",
	        closable:false,
	        buttons:[{
	            text:'确定',
	            handler:function(){
	            	$("#loginform").submit();  // 无刷新方式提交
	            }
	        }]
	    });
	    
	    // 表单组件
	    $("#loginform").form({
	    	url:"login",
	    	success:function(json){
	    		// $.parseJSON文本转换成json对象
	    		var result = $.parseJSON(json);
	    		if(result.success == false){
	    			// 消息框组件
	    			$.messager.show({
	    				title:"登录失败",
	    				msg:result.message,
	    				timeout:2000,
	    				showType:"fade"});
	    		}else{
	    			// 销毁登录窗体
	    			mydialog.dialog("destroy");
	                showMaster();   // 打开主窗体
	    		}
	    	}
	    });
	    
	    // 文本框组件
	    $(":text[name=name]").textbox({
	    	iconCls:'icon-man',
	    	iconAlign:'right',
	    	prompt:'请输入用户名',
	    	validType:['email']
	    });
	    // 密码框组件
	    $(":text[name=pwd]").passwordbox({
	    	showEye:true,
	    	prompt:'请输入6~12位密码',
	    	required:true,
	    	validType:['length[6,12]']
	    });
	}
});