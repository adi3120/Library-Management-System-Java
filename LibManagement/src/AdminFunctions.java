import javax.swing.*;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AdminFunctions {
	static JTable bookData = new JTable();
	static JScrollPane scrollPane = new JScrollPane();

	public static void makeATableBoii(String[][] data, String[] cols, String title) {
		bookData = new JTable(data, cols);
		scrollPane = new JScrollPane(bookData);
		bookData.setFont(new Font("Arial", Font.BOLD, 16));
		bookData.setRowHeight(30);
		scrollPane.setBounds(0, 0, 800, 700);
		LibMain.newFrame(title, 800, 700);
		LibMain.frame.add(scrollPane);
	}

	public static void addBook() {

		JFrame addFrame;
		addFrame = LibMain.newJframeWindow("Add Book", 400, 400, JFrame.DISPOSE_ON_CLOSE);
		JLabel name, auth, gen, price;
		JTextField nameIN = null, authIN = null, genIN = null, priceIN = null;
		JButton addBOOK = new JButton("Add Book");
		addFrame.add(addBOOK);
		addBOOK.setBounds(20, 300, 120, 30);

		name = new JLabel("Enter Book's Name");
		auth = new JLabel("Enter Book's Author");
		gen = new JLabel("Enter Book's Genre");
		price = new JLabel("Enter Book's Price");

		JLabel[] lables = { name, auth, gen, price };
		String[] lableINPUT = new String[lables.length];
		JTextField[] inputs = { nameIN, authIN, genIN, priceIN };

		for (int i = 0; i < inputs.length; i++) {
			inputs[i] = new JTextField();

			addFrame.add(inputs[i]);
		}

		int yoff = 0;
		for (int i = 0; i < lables.length; i++) {

			lables[i].setBounds(20, 40 + yoff, 120, 20);
			addFrame.add(lables[i]);
			inputs[i].setBounds(150, 40 + yoff, 120, 20);
			yoff += 60;
		}

		addBOOK.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				lableINPUT[0] = inputs[0].getText();
				lableINPUT[1] = inputs[1].getText();
				lableINPUT[2] = inputs[2].getText();
				lableINPUT[3] = inputs[3].getText();
				boolean allow = true;
				for (int i = 0; i < 4; i++) {
					if (lableINPUT[i].isEmpty()) {
						allow = false;
						JOptionPane.showMessageDialog(null, "Please don't leave any field blank", "Error",
								JOptionPane.ERROR_MESSAGE);
						break;
					}
				}
				if (allow) {
					Connection connection = SQLUtils.connect("root", "");

					SQLUtils.insertToTable(connection,
							"insert into bookData(name,author,genre,price) values('" + lableINPUT[0] + "','"
									+ lableINPUT[1] + "','" + lableINPUT[2] + "','" + lableINPUT[3] + "')");
					JOptionPane.showMessageDialog(null, "Book added to database", "Success",
							JOptionPane.INFORMATION_MESSAGE);
					try {
						connection.close();
						System.out.println("Connection closed");
					} catch (SQLException e1) {

						e1.printStackTrace();
					}
				}
			}
		});

	}

	public static void logout() {
		int n = JOptionPane.showConfirmDialog(null, "Do you want to logout");
		if (n == JOptionPane.YES_OPTION) {
			LibMain.frame.dispose();
			LibMain.frame.setVisible(false);
			LibMain.mainMenu();
		}

	}

	public static void showBooks() {

		Connection connection = SQLUtils.connect("root", "");
		try {
			Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet set = statement.executeQuery("select * from bookdata");
			ResultSetMetaData metaData = set.getMetaData();
			String[] cols = { metaData.getColumnName(1), metaData.getColumnName(2), metaData.getColumnName(3),
					metaData.getColumnName(4), metaData.getColumnName(5), metaData.getColumnName(6) };

			set.last();
			int size = set.getRow();
			set.beforeFirst();

			String[][] data;
			data = new String[size][];
			for (int i = 0; i < size; i++) {
				data[i] = new String[6];
			}

			int i = 0;
			while (set.next()) {
				data[i][0] = String.valueOf(set.getInt("id"));
				data[i][1] = set.getString("Name");
				data[i][2] = set.getString("Author");
				data[i][3] = set.getString("Genre");
				data[i][4] = String.valueOf(set.getFloat("Price"));
				data[i][5] = String.valueOf(set.getInt("Issued"));
				i++;
			}
			connection.close();
			makeATableBoii(data, cols, "Book Shelf");

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public static void viewUsers() {

		Connection connection = SQLUtils.connect("root", "");
		try {
			Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet set = statement.executeQuery("select * from users");
			ResultSetMetaData metaData = set.getMetaData();
			String[] cols = { metaData.getColumnName(1), metaData.getColumnName(2) };
			set.last();
			int size = set.getRow();
			set.beforeFirst();

			String[][] data;
			data = new String[size][];
			for (int i = 0; i < size; i++) {
				data[i] = new String[2];
			}

			int i = 0;
			while (set.next()) {
				data[i][0] = String.valueOf(set.getInt("id"));
				data[i][1] = set.getString("username");
				i++;
			}
			connection.close();
			makeATableBoii(data, cols, "Users");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void viewIssuedBooks() {
		Connection connection = SQLUtils.connect("root", "");
		try {
			Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet set = statement.executeQuery("select * from issuedBookdata");
			ResultSetMetaData metaData = set.getMetaData();

			String[] cols = { metaData.getColumnName(1), metaData.getColumnName(2), metaData.getColumnName(3),
					metaData.getColumnName(4), metaData.getColumnName(5), metaData.getColumnName(6) };

			set.last();
			int size = set.getRow();
			set.beforeFirst();

			String[][] data;
			data = new String[size][];
			for (int i = 0; i < size; i++) {
				data[i] = new String[6];
			}

			int i = 0;
			while (set.next()) {
				data[i][0] = String.valueOf(set.getInt("s_no"));
				data[i][1] = String.valueOf(set.getInt("Book_id"));
				data[i][2] = set.getString("Book_name");
				data[i][3] = set.getString("Issuer_roll_no");
				data[i][4] = set.getString("Issuer_name");
				data[i][5] = String.valueOf(set.getDate("Issue_date"));
				i++;
			}
			connection.close();
			makeATableBoii(data, cols, "Issued Books");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void issueBook() {
		JFrame issueFrame;
		issueFrame = LibMain.newJframeWindow("Issue Book", 400, 400, JFrame.DISPOSE_ON_CLOSE);
		JLabel id, Uroll, Uname, iDate;
		JTextField idIN = null, UrollIN = null, UnameIN = null, idateIN = null;
		JButton issue = new JButton("Issue Book");
		issueFrame.add(issue);
		issue.setBounds(20, 300, 120, 30);

		id = new JLabel("Book Id");
		Uroll = new JLabel("issuer roll no");
		Uname = new JLabel("issuer Name");
		iDate = new JLabel("Date of issue");

		JLabel[] lables = { id, Uroll, Uname, iDate };
		String[] lableINPUT = new String[lables.length];
		JTextField[] inputs = { idIN, UrollIN, UnameIN, idateIN };

		for (int i = 0; i < inputs.length; i++) {
			inputs[i] = new JTextField();

			issueFrame.add(inputs[i]);
		}

		int yoff = 0;
		for (int i = 0; i < lables.length; i++) {

			lables[i].setBounds(20, 40 + yoff, 120, 20);
			issueFrame.add(lables[i]);
			inputs[i].setBounds(150, 40 + yoff, 120, 20);
			yoff += 60;
		}

		issue.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Connection connection = SQLUtils.connect("root", "");
				for (int i = 0; i < inputs.length; i++)
					lableINPUT[i] = inputs[i].getText();

				try {
					Statement statement = connection.createStatement();
					ResultSet set = statement.executeQuery("Select * from bookdata where id=" + lableINPUT[0]);

					if (set.next() == false) {
						JOptionPane.showMessageDialog(null, "No book found as per given data", "Error",
								JOptionPane.ERROR_MESSAGE);
					} else {
						String Bname = set.getString("Name");
						int issue = set.getInt("Issued");
						SQLUtils.insertToTable(connection,
								"insert into issuedbookData(book_id,Book_Name,issuer_roll_no,issuer_name,issue_date) values('"
										+ lableINPUT[0] + "','" + Bname + "','" + lableINPUT[1] + "','" + lableINPUT[2]
										+ "','" + lableINPUT[3] + "')");
						JOptionPane.showMessageDialog(null, "Book Issued To user");
						issue++;
						SQLUtils.insertToTable(connection,
								"update bookdata set issued =" + issue + " where id=" + lableINPUT[0]);
						System.out.println(
								"insert into bookdata(issued) values(" + issue + ") where id=" + lableINPUT[0]);
						connection.close();
						System.out.println("Connection closed");
						statement.close();
					}
				} catch (SQLException e1) {

					e1.printStackTrace();
				}
			}
		});
	}

	public static void returnBook() {
		JFrame returnFrame;
		returnFrame = LibMain.newJframeWindow("Return Book", 400, 400, JFrame.DISPOSE_ON_CLOSE);
		JLabel id, Uroll, rDate;
		JTextField idIN = null, rdateIN = null, UrollIN = null;
		JButton returnBut = new JButton("Return Book");
		returnFrame.add(returnBut);
		returnBut.setBounds(20, 300, 120, 30);

		id = new JLabel("Book Id");
		Uroll = new JLabel("Issuer roll no");
		rDate = new JLabel("Date of return");

		JLabel[] lables = { id, Uroll, rDate };
		String[] lableINPUT = new String[lables.length];
		JTextField[] inputs = { idIN, UrollIN, rdateIN };

		for (int i = 0; i < inputs.length; i++) {
			inputs[i] = new JTextField();

			returnFrame.add(inputs[i]);
		}

		int yoff = 0;
		for (int i = 0; i < lables.length; i++) {

			lables[i].setBounds(20, 40 + yoff, 120, 20);
			returnFrame.add(lables[i]);
			inputs[i].setBounds(150, 40 + yoff, 120, 20);
			yoff += 60;
		}

		returnBut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Connection connection = SQLUtils.connect("root", "");
				for (int i = 0; i < inputs.length; i++)
					lableINPUT[i] = inputs[i].getText();

				try {
					Statement statement = connection.createStatement();
					ResultSet set = statement.executeQuery("Select * from issuedbookdata where book_id=" + lableINPUT[0]
							+ " and Issuer_roll_no='" + lableINPUT[1] + "'");

					if (set.next() == false) {
						JOptionPane.showMessageDialog(null, "No book found as per given data", "Error",
								JOptionPane.ERROR_MESSAGE);
					} else {
						SQLUtils.insertToTable(connection,
								"insert into returnedbookData(Book_id,issuer_roll_no,issue_date,return_date) values('"
										+ lableINPUT[0] + "','" + lableINPUT[1] + "','" + set.getDate("issue_date")
										+ "','" + lableINPUT[2] + "')");
						statement.executeUpdate("delete from issuedbookdata where book_id=" + set.getInt("Book_id"));
						JOptionPane.showMessageDialog(null, "Book returned");
						connection.close();
						System.out.println("Connection closed");
						statement.close();
					}
				} catch (SQLException e1) {

					e1.printStackTrace();
				}
			}
		});

	}

	public static void viewReturnedBooks() {
		Connection connection = SQLUtils.connect("root", "");
		try {
			Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet set = statement.executeQuery("select * from returnedBookdata");
			ResultSetMetaData metaData = set.getMetaData();

			String[] cols = { metaData.getColumnName(1), metaData.getColumnName(2), metaData.getColumnName(3),
					metaData.getColumnName(4), metaData.getColumnName(5) };

			set.last();
			int size = set.getRow();
			set.beforeFirst();

			String[][] data;
			data = new String[size][];
			for (int i = 0; i < size; i++) {
				data[i] = new String[6];
			}

			int i = 0;
			while (set.next()) {
				data[i][0] = String.valueOf(set.getInt("s_no"));
				data[i][1] = String.valueOf(set.getInt("Book_id"));
				data[i][2] = set.getString("Issuer_roll_no");
				data[i][3] = String.valueOf(set.getDate("Issue_date"));
				data[i][4] = String.valueOf(set.getDate("Return_date"));
				i++;
			}
			connection.close();

			makeATableBoii(data, cols, "Returned Books");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void refresh() {
		String t1 = "bookdata";
		String t2 = "issuedbookdata";
		String t3 = "returnedbookdata";
		String[] ts = { t1, t2, t3 };
		Connection connection = SQLUtils.connect("root", "");
		try {
			CallableStatement statement=connection.prepareCall("{call refreshBookData()}");
			statement.executeQuery();
			statement=connection.prepareCall("{call refreshIssuedBookData()}");
			statement.executeQuery();
			statement=connection.prepareCall("{call refreshReturnedBookData()}");
			statement.executeQuery();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
