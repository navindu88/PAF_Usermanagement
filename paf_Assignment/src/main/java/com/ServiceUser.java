package com;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ServiceUser {
	//A common method to connect to the DB
			private Connection connect(){ 
							Connection con = null; 							
							try{ 
									Class.forName("com.mysql.jdbc.Driver"); 
	 
									// Provide the correct details: DBServer/DBName, username, password
									con = DriverManager.getConnection(
											"jdbc:mysql://localhost:3306/user_records?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
											"root", "root");
							} 
							catch (Exception e) {
								e.printStackTrace();
								} 
							
							return con; 
				} 
			
			
	public String readUser()
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
			 output = "<table border=\"1\" class=\"table\"><tr>"
			 		+ "<th>UserID</th>"
			 		+ "<th>U_Fname</th>"
			 		+ "<th>U_Lname</th>"
			 		+ "<th>Uemail</th>"
			 		+ "<th>Uaddress</th>"
			 		+ "<th>U_NIC</th>"
			 		+ "<th>U_gender</th>"
			 		+ "<th>U_DoB</th></tr>";
			
			 String query = "select * from serviceuserapi"; 
			 Statement stmt = con.createStatement(); 
			 ResultSet rs = stmt.executeQuery(query); 
			 // iterate through the rows in the result set
			 while (rs.next()) 
			 { 
			 String UserID = Integer.toString(rs.getInt("UserID")); 
			 String U_Fname = rs.getString("U_Fname"); 
			 String U_Lname = rs.getString("U_Lname"); 
			 String Uemail = rs.getString("Uemail");
			 String Uaddress = rs.getString("Uaddres"); 
			 String U_NIC = rs.getString("U_NIC"); 
			 String U_gender = rs.getString("U_gender");
			 String U_DoB = rs.getString("U_DoB");
			 // Add into the html table
			 output += "<tr><td><input id='hidbidUpdate' name='hiduidUpdate' type='hidden' value='"+UserID+"'>"+U_Fname+"</td>"; 
			 output += "<td>" + U_Lname + "</td>"; 
			 output += "<td>" + Uemail + "</td>"; 
			 output += "<td>" + Uaddress + "</td>"; 
			 output += "<td>" + U_NIC + "</td>";
			 output += "<td>" +U_gender + "</td>";
			 output += "<td>" + U_DoB+ "</td>";
			 // buttons
			 output += "<td><input name='btnUpdate' type='button' value='Update' "
					 + "class='btnUpdate btn btn-secondary' data-bid='" + UserID + "'></td>"
					 + "<td><input name='btnRemove' type='button' value='Remove' "
					 + "class='btnRemove btn btn-danger' data-UserID='" +UserID + "'></td></tr>"; 
			 
			 } 
			 con.close(); 
			 // Complete the html table
			 output += "</table>"; 
			 } 
			 
			catch (Exception e) 
			 { 
			 output = "Error while reading the bill."; 
			 System.err.println(e.getMessage()); 
			 } 
			return output; 
			}		
	
public String insertUser(String UserID, String U_Fname,String U_Lname,String Uemail,String Uaddress,String U_NIC,String U_gender,String U_DoB) {
	String output = "";
				
				try
				{ 
					Connection con = connect(); 
					
					if (con == null) 
					{
						return "Error while connecting to the database for inserting."; 
						
					} 
					// create a prepared statement
					
					String query = "insert into user(`UserID`,`U_Fname`,`U_Lname`,`Uemail`,`Uaddress`,`U_NIC`,`U_gender`,`U_DoB`)" + " values ( ?, ?, ?, ?, ?, ?)";
					PreparedStatement preparedStmt = con.prepareStatement(query); 
					// binding values
					preparedStmt.setInt(1, 0);
					preparedStmt.setString(2, U_Fname);
					preparedStmt.setString(3, U_Lname);
					preparedStmt.setString(4, Uemail);
					preparedStmt.setString(5, Uaddress);
					preparedStmt.setString(6, U_NIC);
					preparedStmt.setString(7, U_gender);
					preparedStmt.setString(8, U_DoB);
					// execute the statement

					preparedStmt.execute(); 
					con.close(); 
					
					String newUser = readUser(); 
					output = "{\"status\":\"success\",\"data\":\""+newUser+"\"}"; 
				} 
				
				catch (Exception e) 
				{ 
					output = "{\"status\":\"error\", \"data\":\"Error while inserting the user.\"}"; 
					System.err.println(e.getMessage()); 
				} 
				return output; 
		}
			
	public String updateUser(String UserID, String U_Fname,String U_Lname,String Uemail,String Uaddress,String U_NIC,String U_gender,String U_DoB) {
		String output = "";

				try {
					Connection con = connect();

					if (con == null) {
						return "Error while connecting to the database for updating.";
					}

					// create a prepared statement
					String query = "UPDATE user SET U_Fname=?,U_Lname=?,Uemail=?,Uaddress=?,U_NIC=?,U_gender=?,U_DoB=? WHERE UserID=?";

					PreparedStatement preparedStmt = con.prepareStatement(query);

					// binding values

					preparedStmt.setString(1, U_Fname);
					preparedStmt.setString(2, U_Lname);
					preparedStmt.setString(3, Uemail);
					preparedStmt.setString(4, Uaddress);
					preparedStmt.setString(5, U_NIC);
					preparedStmt.setString(6, U_gender);
					preparedStmt.setString(7, U_DoB);
					preparedStmt.setInt(8, Integer.parseInt(UserID));

					
					// execute the statement
					preparedStmt.execute();
					con.close();

					String newUser = readUser(); 
					output = "{\"status\":\"success\",\"data\":\""+newUser+"\"}"; 
				
				} catch (Exception e) {
					output = "{\"status\":\"error\", \"data\":\"Error while updating the new User.\"}";
					System.err.println(e.getMessage());
				}

				return output;
			}
			
		public String deleteUser(String UserID) {
				String output = "";

				try {
					Connection con = connect();

					if (con == null) {
						return "Error while connecting to the database for deleting.";
					}

					// create a prepared statement
					String query = "delete from user where UserID=?";

					PreparedStatement preparedStmt = con.prepareStatement(query);

					// binding values
					preparedStmt.setInt(1, Integer.parseInt(UserID));

					// execute the statement
					preparedStmt.execute();
					con.close();

					String newUser = readUser(); 
					output = "{\"status\":\"success\",\"data\":\""+newUser+"\"}"; 
				
				} catch (Exception e) {
					output = "{\"status\":\"error\", \"data\":\"Error while delete the user.\"}";
					System.err.println(e.getMessage());
				}

				return output;
			}
			
}