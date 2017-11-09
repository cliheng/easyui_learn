package easyui_learn.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import easyui_learn.pojo.Employee;

@Repository
public class EmployeeDao {

	@Resource
	private JdbcTemplate template;
	
	// 分页查询方法
	public List<Employee> searchEmpPageList(int pageno, int size){
		String sql = "SELECT `emp_no`,`birth_date`,"
				+ "`first_name`,`last_name` FROM "
				+ "`employees`.`employee` LIMIT " + (pageno - 1) * size
				+ ", " + size;
		
		return template.query(sql, new EmployeeRowMapper());
	}
	
	// 计算总记录方法
	public Long getEmpsRecordCount(){
		String sql = "select count(emp_no) from employees";
		return template.queryForObject(sql, Long.class);
	}
	
	// 通过ResultSet把记录转换成实体对象
	class EmployeeRowMapper implements RowMapper<Employee> {

		@Override
		public Employee mapRow(ResultSet rs, int index) throws SQLException {
			Employee emp = new Employee();
			emp.setNo(rs.getInt("emp_no"));
			emp.setFirstName(rs.getString("first_name"));
			emp.setLastName(rs.getString("last_name"));
			emp.setBirth(rs.getDate("birth_date"));
			return emp;
		}
		
	}
}
