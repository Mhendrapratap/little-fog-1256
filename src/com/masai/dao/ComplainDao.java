package com.masai.dao;

import com.masai.exceptions.SomethingWentWrong;

public interface ComplainDao {

	void deleteTicketComplainBook(int ticketNum) throws SomethingWentWrong;

}
