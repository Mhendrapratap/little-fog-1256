package com.masai.dao;
import java.util.List;

import com.masai.dto.Employee;
import com.masai.exceptions.SomethingWentWrong;
import com.masai.dto.Complain;

public interface EmployeeDao {
	
	String registerEmployee(Employee emp) throws SomethingWentWrong;

	public boolean loginEmployee(String username, String password) throws SomethingWentWrong;

	int complainRegister(String type, String descComplain) throws SomethingWentWrong;

	List<Complain> getStatusOfComplain(int complainId) throws SomethingWentWrong;

	List<Complain> getComplainHistory() throws SomethingWentWrong;

	String changePassword(String username, String password, String newpassword) throws SomethingWentWrong;

}
