package com.basic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class EmployeeController {

	@Autowired
	EmployeeDao dao;
	Employee emp = new Employee();

	@RequestMapping("/empform")
	@ResponseStatus(value=HttpStatus.OK)
	public String showForm(Model model) {
		model.addAttribute("command", emp);

		return "empform";
	}

	/*
	 * It saves object into database. The @ModelAttribute puts request data into
	 * model object. You need to mention RequestMethod.POST method because default
	 * request is GET
	 */

	@RequestMapping(value = "/save", method = {RequestMethod.GET , RequestMethod.POST})
	public String save(@ModelAttribute("emp") Employee emp) {
		dao.save(emp);
		return "redirect:/viewemp";
	}
	
	
	@RequestMapping("/viewemp")
	public String view(Model model) {
		List<Employee> list = dao.getEmployees();
		model.addAttribute("list", list);
		return "viewemp";
	}

	/*
	 * It displays object data into form for the given id. The @PathVariable puts
	 * URL data into variable.
	 */
	
	@RequestMapping("/editemp/{empId}")
	public String edit(@PathVariable int empId, Model m) {
		Employee emp = dao.getEmpById(empId);
		m.addAttribute("command", emp);
		return "empeditform";
	}

	/* It updates model object. */
	
	@RequestMapping(value = "/editsave", method = {RequestMethod.GET , RequestMethod.POST})
	public String editsave(@ModelAttribute("emp") Employee emp) {
		dao.update(emp);
		return "redirect:/viewemp";
	}

	/* It deletes record for the given id in URL and redirects to /viewemp */
	
	@RequestMapping(value = "/deleteemp/{empId}", method = {RequestMethod.GET , RequestMethod.POST})
	public String delete(@PathVariable int empId) {
		dao.delete(empId);
		return "redirect:/viewemp";
	}

}
