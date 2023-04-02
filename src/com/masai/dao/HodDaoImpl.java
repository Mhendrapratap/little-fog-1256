package com.masai.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.masai.dto.Complain;
import com.masai.dto.ComplainImpl;
import com.masai.dto.Engineer;
import com.masai.dto.EngineerImpl;
import com.masai.exceptions.SomethingWentWrong;
import com.masai.utility.*;

public class HodDaoImpl implements HodDao {
	@Override
	public boolean loginHod(String username, String password) throws SomethingWentWrong {
		Connection conn = null;

		try {
			conn = DBUtils.getConnectionToDatabase();
			PreparedStatement ps = conn.prepareStatement("select * from hod where empUserName = ? AND empPassword = ?");

			ps.setString(1, username);
			ps.setString(2, password);

			ResultSet x = ps.executeQuery();

			if (x.next()) {
				return true;
			}
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
		return false;
	}

	@Override
	public String registerEngineer(Engineer engineer) throws SomethingWentWrong{
		Connection conn = null;
		String message = "Data may be incorrect....Please try again";

		try {
			conn = DBUtils.getConnectionToDatabase();
			PreparedStatement ps = conn.prepareStatement(
					"INSERT into Engineer(EngName,EngUserName,EngPassword,EngCategory) values(?,?,?,?)");

			ps.setString(1, engineer.getEngName());
			ps.setString(2, engineer.getEngUserName());
			ps.setString(3, engineer.getEngPass());
			ps.setString(4, engineer.getEngCategory());

			if (ps.executeUpdate() > 0) {
				message = "Engineer Registered Succesfully";
			}

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

		return message;
	}

	@Override
	public List<Engineer> getRegisterEngineer() throws SomethingWentWrong {
		Connection conn = null;
		List<Engineer> engineers = new ArrayList<>();

		try {
			conn = DBUtils.getConnectionToDatabase();
			PreparedStatement ps = conn.prepareStatement("SELECT * from engineer");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int eid = rs.getInt("EngId");
				String ename = rs.getString("EngName");
				String eusername = rs.getString("EngUserName");
				String epassword = rs.getString("EngPassword");
				String ecategory = rs.getString("EngCategory");
				Engineer engineer = new EngineerImpl(eid, ename, eusername, epassword, ecategory);
				engineers.add(engineer);
			}
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
		return engineers;
	}

	@Override
	public String deleteEngineer(String engUsername) throws SomethingWentWrong {
		Connection conn = null;
		String message = "No";

		try {
			conn = DBUtils.getConnectionToDatabase();
			PreparedStatement ps = conn.prepareStatement("DELETE from Engineer where engUsername = ?");

			ps.setString(1, engUsername);
			if (ps.executeUpdate() > 0) {
				message = "Yes";
			}
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
		return message;
	}

	@Override
	public List<Complain> getListOfAllComplain() throws SomethingWentWrong {
		Connection conn = null;
		List<Complain> lsComp = new ArrayList<>();
		try {
			conn = DBUtils.getConnectionToDatabase();
			String query = "select * from ComplainBook";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int sNo = rs.getInt("sno");
				int tNum = rs.getInt("TicketNumber");
				String type = rs.getString("type");
				String cDesc = rs.getString("description");
				String cStatus = rs.getString("StatusOfComplain");
				String cAssignEngg = rs.getString("AssignEngineer");
				String cByEmployee = rs.getString("ComplainByEmployee");
				Complain comp = new ComplainImpl(sNo, tNum, type, cDesc, cStatus, cAssignEngg, cByEmployee);
				lsComp.add(comp);
			}
			if (!rs.next()) {
				System.out.println("No Complain record found");
			}
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
		return lsComp;
	}

	@Override
	public String assigningProblemToEngineer(int ticketNo, String engUsername) throws SomethingWentWrong {
		Connection conn = null;
		String message = "Unable to assign engineer " + engUsername + " with ticket number: " + ticketNo;

		try {
			conn = DBUtils.getConnectionToDatabase();
			String query = "UPDATE complainBook SET AssignEngineer = ? WHERE TicketNumber=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, engUsername);
			ps.setInt(2, ticketNo);
			if (ps.executeUpdate() > 0) {
				message = "Engineer " + engUsername + " assigned to ticket number: " + ticketNo + " sucessfully";
			}
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
		return message;
	}

}
