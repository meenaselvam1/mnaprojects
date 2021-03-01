package tryit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JOptionPane;

/* The TechTest class reads Questions in the Java subject, from the derby database, populates 
 * the questions in Java Objects, displays the questions. The format of the User Interface 
 * is defined in the View class. This class delegates the display to the View class. 
 */
public class TechTest implements Runnable{
	static Vector<String>  questionSet = new Vector<>();
	static Vector<String> answerSet = new Vector<>();
	static int numberOfQuestions = 0;
	static int questionNum = 0;
	
	private String sqlQuery = "Select * from Questions";
	private String sqlQueryCreateTable = "create table Questions" + 
			"(question VARCHAR(200) NOT NULL, " +
			"answer VARCHAR(2) NOT NULL )";
	private String sqlQueryInsertTable = "insert into Questions values ('Interface can have static methods defined','T'),\n"
			+ " ('Interface cannot have defender methods defined','F'),('Abstract class can have some methods implemented','T'),\n"
			+ " ('You can not force garbage collection but you can request it','T'),('Abstract class can be instantiated','F'),\n"
			+ " ('Constructors can return a value','F'),('Casting cannot be performed between objects of different types','T')";
	private String sqlQueryDeleteTable = "drop table Questions";
	
	public static void main(String[] args) {
		Runnable techtest = new TechTest();
		Thread techTestThread = new Thread(techtest, 
				       "TechTest Thread");
		techTestThread.start();
	}
	public void run() {

		try (Connection conn = DriverManager.getConnection( 
                "jdbc:derby://localhost:1527/JavaQuiz");
             Statement stmt = conn.createStatement()) {
			 stmt.executeUpdate(sqlQueryCreateTable);
		     stmt.executeUpdate(sqlQueryInsertTable);
		     int i=0;
				
             ResultSet rs = stmt.executeQuery(sqlQuery);
			
			while (rs.next()) {
				questionSet.add(rs.getString(1));
				numberOfQuestions++;
				answerSet.add(rs.getString(2));
				i++;
			}
			stmt.executeUpdate(sqlQueryDeleteTable);
			try {
				if (stmt != null)
					stmt.close();
			}
			catch (Exception e) {}
			try {
				if (conn != null)
					conn.close();
			}
			catch (Exception e) {}
		} catch (SQLException se) {
			System.out.println("SQL Error: " + se.getMessage() +
					           ", the db error code is " + se.getErrorCode());
		} catch (Exception e) {
			e.printStackTrace();
		} 
		if (!questionSet.isEmpty()) { 
			View view = new View(); 
			view.display((String) questionSet.get((questionNum)));
		}
		else
			JOptionPane.showMessageDialog(null,"Questions are missing. Check the database of questions", "Error",JOptionPane.PLAIN_MESSAGE);
	}	
	
}

