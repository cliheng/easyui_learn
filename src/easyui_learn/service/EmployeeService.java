package easyui_learn.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import easyui_learn.dao.EmployeeDao;
import easyui_learn.pojo.Employee;

@Service
@Transactional
public class EmployeeService {
	
	@Resource
	private EmployeeDao empDao;

	@Transactional(readOnly=true)
	public List<Employee> empPageList(int pageno, int size){
		return empDao.searchEmpPageList(pageno, size);
	}
	
	@Transactional(readOnly=true)
	public Long empPageRecordCount(){
		return empDao.getEmpsRecordCount();
	}
}
