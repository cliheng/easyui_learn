package easyui_learn.control;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;

import easyui_learn.pojo.Employee;
import easyui_learn.service.EmployeeService;

@Controller
public class EmployeeControl {
	
	@Resource
	private EmployeeService service;

	@RequestMapping(value="/login",produces="application/json;charset=utf-8")
	@ResponseBody
	public String userLogin(String name, String pwd, 
			HttpServletResponse response){
		if(name.equals("admin@123.net") && pwd.equals("123456")){
			//session.setAttribute("user", "admin@123.net");
			Cookie userAuth = new Cookie("user", "admin@123.net");
			//userAuth.setMaxAge(20);  // 长期保存
			response.addCookie(userAuth);
			
			return "{\"success\":true}";
		}else{
			return "{\"success\":false,\"message\":\"用户名或密码错误\"}";
		}
	}

	@RequestMapping(value="/emplist",produces="application/json;charset=utf-8")
	@ResponseBody
	public String employeeList(int page, int rows){
		List<Employee> emps = service.empPageList(page, rows);
		long total = service.empPageRecordCount();
		HashMap result = new HashMap(){{put("rows", emps); put("total",total);}};
		
		JSONArray.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd";
		String json = JSONArray.toJSONString(result,SerializerFeature.WriteDateUseDateFormat);
		return json;
	}
}
