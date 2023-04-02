package com.masai.dao;

import java.util.List;

import com.masai.dto.Complain;
import com.masai.dto.Engineer;
import com.masai.exceptions.SomethingWentWrong;

public interface HodDao {

	boolean loginHod(String username, String password) throws SomethingWentWrong;

	String registerEngineer(Engineer engineer) throws SomethingWentWrong;

	List<Engineer> getRegisterEngineer() throws SomethingWentWrong;

	String deleteEngineer(String engUsername) throws SomethingWentWrong;

	List<Complain> getListOfAllComplain() throws SomethingWentWrong;

	String assigningProblemToEngineer(int ticketNo, String engUsername) throws SomethingWentWrong;

}
