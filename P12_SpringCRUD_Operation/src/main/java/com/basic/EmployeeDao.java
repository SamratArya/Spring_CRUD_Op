	package com.basic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class EmployeeDao {

	JdbcTemplate template;

	public JdbcTemplate getTemplate() {
		return template;
	}

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public int save(Employee p) {
		String sql = "insert into Employee(empName,salary,designation) values('" + p.getEmpName() + "'," + p.getSalary() + ",'" + p.getDesignation() + "')";
		return template.update(sql);
	}

	public int update(Employee p) {
		String sql = "update Employee set empName='" + p.getEmpName() + "', salary=" + p.getSalary() + ",designation='" + p.getDesignation() + "' where empId=" + p.getEmpId() + "";
		return template.update(sql);
	}

	public int delete(int empId) {
		String sql = "delete from Employee where empId=" + empId + "";
		return template.update(sql);
	}

	public Employee getEmpById(int empId) {
		String sql = "select * from Employee where empId=?";
		return template.queryForObject(sql, new Object[] { empId }, new BeanPropertyRowMapper<Employee>(Employee.class));
	}
	
	public List<Employee> getEmployees(){    
		
		String sql = "select * from Employee";
	    return template.query(sql,new RowMapper<Employee>(){    
	        public Employee mapRow(ResultSet rs, int row) throws SQLException {    
	        	
	            Employee e=new Employee();    
	            e.setEmpId(rs.getInt(1));    
	            e.setEmpName(rs.getString(2));    
	            e.setSalary(rs.getFloat(3));    
	            e.setDesignation(rs.getString(4));    
	            return e;    
	        }    
	    });    
	}  

}
