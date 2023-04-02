package com.masai.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.masai.exceptions.SomethingWentWrong;
import com.masai.utility.*;

public class ComplainDaoImpl implements ComplainDao {

	@Override
	public void deleteTicketComplainBook(int ticketNum) throws SomethingWentWrong {
		Connection conn = null;
		try {
			conn = DBUtils.getConnectionToDatabase();
			String query = "Delete from complainBook where ticketNumber=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, ticketNum);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SomethingWentWrong();
		} finally {
			try {
				DBUtils.closeConnection(conn);
			} catch (SQLException e) {
				throw new SomethingWentWrong();
			}
		}
	}

}
