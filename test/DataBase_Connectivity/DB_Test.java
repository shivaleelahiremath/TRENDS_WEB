package DataBase_Connectivity;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import org.testng.annotations.Test;

public class DB_Test {
	//This URL is based on your IP address: Should be defined as jdbc:mysql://host:port/database name
//	String dbUrl = "jdbc:mysql://ec2-184-73-201-240.compute-1.amazonaws.com:1433/TX_TangoCentralISD"; 
	String dbUrl = "jdbc:mysql://184.73.201.240:1433/TX_TangoCentralISD"; 
	String dbUrl2 = "jdbc:oracle://localhost:1521/TX_TangoCentralISD";  
	String dbUrl3 = "jdbc:mysql://localhost:5000/TX_TangoCentralISD";  
//	ec2-184-73-166-201.compute-1.amazonaws.com
	String userName= "farrier_reader";
	String pwd = "AajTak123";
	String dbClass = "com.mysql.jdbc.Driver";
//	String dbClass = "oracle.jdbc.driver.OracleDriver";
	Statement stmt;
	Connection connection;
	
	@Test
	public void CreateDB() throws ClassNotFoundException, SQLException {
		try{		
		Class.forName(dbClass);
        System.out.println("Connecting to Database...");
        connection = DriverManager.getConnection(dbUrl, userName, pwd);
		if(connection!=null) {
            System.out.println("Connected to the database...");
        }
		}catch (SQLException e) {
            e.printStackTrace();	
        }catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
	    stmt = connection.createStatement();
	}
	
	@Test
	public void test() {	
		try{		
			String query = "select * from TX_TangoCentralISD.dbo.staff";
			//Get the contents of userinfo table from DB
			ResultSet res = stmt.executeQuery(query);

			// Print the result untill all the records are printed
			// res.next() returns true if there is any next record else returns false
			while(res.next()) {
				System.out.print(res.getString(1));			
				System.out.print("\t" + res.getString(2));			
				System.out.print("\t" + res.getString(3));			
				System.out.println("\t" + res.getString(4));			
			}			
		}catch(Exception e) {			
			e.printStackTrace();
		} 
	}
}
