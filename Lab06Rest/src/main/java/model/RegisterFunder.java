package model;
import java.sql.*;
public class RegisterFunder
{ 		//A common method to connect to the DB
	private Connection connect()
	{
		Connection con = null;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			
			//Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf13.2", "root", "");
		}
		catch (Exception e)
		{e.printStackTrace();}
		return con;
	}
	
	public String insertItem(String userName, String userPassword, String userCode, String userEmail, String userPhone)
	{
		String output = "";
		
		try
		{
			Connection con = connect();
			
			if (con == null)
			{
				return "Error while connecting to the database for inserting.";
			}
			
			// create a prepared statement
			String query = " insert into funders (`id`,`userName`,`userPassword`,`userCode`,`userEmail`, `userPhone`)"
					+ " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, userName);
			preparedStmt.setString(3, userPassword);
			preparedStmt.setString(4, userCode);
			preparedStmt.setString(5, userEmail);
			preparedStmt.setString(6, userPhone);
			
			
			// execute the statement
			preparedStmt.execute(); 
			con.close(); 
			output = "Inserted successfully"; 
		} 
		catch (Exception e) 
		{ 
			 output = "Error while inserting the item."; 
			 System.err.println(e.getMessage()); 
		} 
			 return output; 
	} 

	public String readItems() 
	{ 
		String output = "";
			 
		try
		{ 
			 Connection con = connect(); 
			 
			 if (con == null) 
			 {
				 return "Error while connecting to the database for reading."; 
			 } 
			 
			 // Prepare the html table to be displayed
			 output = "<table border='1'><tr><th>User Name</th><th>User Password</th>" +
			 "<th>User Code</th>" + 
			 "<th>User Email</th>" +
			 "<th>User Phone</th>" +
			 "<th>Update</th><th>Remove</th></tr>"; 
			 
			 String query = "select * from funders"; 
			 Statement stmt = con.createStatement(); 
			 ResultSet rs = stmt.executeQuery(query); 
			 
			 
			 // iterate through the rows in the result set
			 while (rs.next()) 
			 { 
				 String id = Integer.toString(rs.getInt("id")); 
				 String userName = rs.getString("userName"); 
				 String userPassword = rs.getString("userPassword"); 
				 String userCode = rs.getString("userCode"); 
				 String userEmail = rs.getString("userEmail"); 
				 String userPhone = rs.getString("userPhone"); 
				 
				 // Add into the html table
				 output += "<tr><td>" + userName + "</td>"; 
				 output += "<td>" + userPassword + "</td>"; 
				 output += "<td>" + userName + "</td>"; 
				 output += "<td>" + userEmail + "</td>"; 
				 output += "<td>" + userPhone + "</td>";
				 
				 // buttons
				 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
						 + "<td><form method='post' action=''>" + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
						 + "<input name='id' type='hidden' value='" + id 
						 + "'>" + "</form></td></tr>"; 
			 } 
			 
			 con.close();
			 
			 // Complete the html table
			 output += "</table>"; 
		} 
		catch (Exception e) 
		{ 
			 output = "Error while reading the items."; 
			 System.err.println(e.getMessage()); 
		} 
		return output; 
			 
	} 
	
	public String updateItem(String id, String userName, String userPassword, String userCode, String userEmail, String userPhone)
	{ 
		String output = "";
		
		try
		{ 
			 Connection con = connect();
			 
			 if (con == null) 
			 {
				 return "Error while connecting to the database for updating."; 
			 } 
			
			 // create a prepared statement
			 String query = "UPDATE funders SET userName=?,userPassword=?,userCode=?, userEmail=?, userPhone=? WHERE id=?"; 
			 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 
			 // binding values
			 preparedStmt.setString(1, userName); 
			 preparedStmt.setString(2, userPassword); 
			 preparedStmt.setString(3, userCode); 
			 preparedStmt.setString(4, userEmail); 
			 preparedStmt.setString(5, userPhone); 
			 preparedStmt.setInt(6, Integer.parseInt(id)); 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 output = "Updated successfully"; 
		} 
		catch (Exception e) 
		{ 
			 output = "Error while updating the item."; 
			 System.err.println(e.getMessage()); 
		}
		
		return output; 
	} 
			
	public String deleteItem(String id) 
	{ 
		String output = ""; 
		
		try
		{ 
			 Connection con = connect(); 
			 
			 if (con == null) 
			 {
				 return "Error while connecting to the database for deleting."; 
			 }
			 
			 // create a prepared statement
			 String query = "delete from funders where id=?"; 
			 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(id)); 
			 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close();
			 
			 output = "Deleted successfully"; 
		} 
		catch (Exception e) 
		{ 
			 output = "Error while deleting the item."; 
			 System.err.println(e.getMessage()); 
		} 
		return output; 
	} 
} 
