package com.masai.dao;

import java.util.List;
import com.masai.dto.Complain;
import com.masai.exceptions.SomethingWentWrong;

public interface EngineerDao {

	public boolean loginEngineer(String EngUserName, String EngPass) throws SomethingWentWrong;

	List<Complain> getListOfProblemAssignByHod() throws SomethingWentWrong;

	String changeStatusOfComplain(int ticketNum, String status) throws SomethingWentWrong;

	List<Complain> getListOfProblemAttendByEngineer() throws SomethingWentWrong;

	String changePassword(String username, String password, String newpassword) throws SomethingWentWrong;

}
