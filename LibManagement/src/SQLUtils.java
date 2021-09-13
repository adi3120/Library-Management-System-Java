import java.sql.*;

public class SQLUtils {
	public static Connection connect(String username, String password) {
		String url = "jdbc:mysql://localhost/library";
		Connection con;
		try {
			con = DriverManager.getConnection(url, username, password);
			return con;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void displayTable(Connection connection,String tableName) {
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from "+tableName);
			while (rs.next()) {
				System.out.println(rs.getInt(1) + " , " + rs.getString(2) + " , " + rs.getString(3));
			}
			statement.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public static void resetAutoIncrement(Connection connection, String query) {
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				System.out.println("Max id = " + resultSet.getInt(1));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public static void insertToTable(Connection connection, String query) {
		try {
			Statement statement = connection.createStatement();
			int n = statement.executeUpdate(query);
			System.out.println("Query OK," + n + " rows affected");
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void DeleteTable(Connection connection, String query) {
		try {
			Statement statement = connection.createStatement();
			int n = statement.executeUpdate(query);
			System.out.println("Query OK," + n + " rows affected");
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void DDLQuery(Connection connection, String query) {
		try {
			Statement statement = connection.createStatement();
			statement.execute(query);
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	public static void DMLQuery(Connection connection, String query) {
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(query);
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
}
