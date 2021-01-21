package com.bridgelabz.employeepayrollapp.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.employeepayrollapp.dto.EmployeePayrollDTO;
import com.bridgelabz.employeepayrollapp.exceptions.EmployeePayrollException;
import com.bridgelabz.employeepayrollapp.model.EmployeePayrollData;
import com.bridgelabz.employeepayrollapp.repository.EmployeePayrollRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeePayrollService implements IEmployeePayrollService{
	
	@Autowired
	private EmployeePayrollRepository employeeRepository;
	
	private List<EmployeePayrollData> employeePayrollList = new ArrayList<>();

	public List<EmployeePayrollData> getEmployeePayrollData() {
		// TODO Auto-generated method stub
		return employeePayrollList;
	}

	public EmployeePayrollData getEmployeePayrollDataById(int empId) {
		// TODO Auto-generated method stub
		return employeePayrollList.stream()
				.filter(empData -> empData.getEmployeeId() == empId)
				.findFirst()
				.orElseThrow(() ->new EmployeePayrollException("Employee Not Found"));
	}

	public EmployeePayrollData createEmployeePayrollData(EmployeePayrollDTO empPayrollDTO) {
		// TODO Auto-generated method stub
		EmployeePayrollData empData = null;
		empData = new EmployeePayrollData(empPayrollDTO);
		employeePayrollList.add(empData);
		log.debug("Emp Data: "+empData.toString());
		return employeeRepository.save(empData);
	}

	public EmployeePayrollData updateEmployeePayrollData(int empId,EmployeePayrollDTO empPayrollDTO) {
		// TODO Auto-generated method stub
		EmployeePayrollData empData = this.getEmployeePayrollDataById(empId);
		empData.setName(empPayrollDTO.name);
		empData.setSalary(empPayrollDTO.salary);
		empData.setGender(empPayrollDTO.gender);
		empData.setStartDate(empPayrollDTO.startDate);
		empData.setProfilePic(empPayrollDTO.profilePic);
		empData.setNote(empPayrollDTO.note);
		empData.setDepartments(empPayrollDTO.departments);
		employeePayrollList.set(empId-1, empData);
		return employeeRepository.save(empData);
	}

	public void deleteEmployeePayrollData(int empId) {
		// TODO Auto-generated method stub
		employeePayrollList.remove(empId-1);
	}
	
}
