import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AdminFunctions {
//	public static void viewBooks() {
//		Connection connection = SQLUtils.connect("root", "");
//		SQLUtils.displayTable(connection, "BookData");
//		try {
//			connection.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	public static void addBook() {

		JFrame addFrame = new JFrame("Add Book");
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

		addFrame.setSize(400, 400);
		addFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addFrame.setLayout(null);
		addFrame.setVisible(true);
		addFrame.setLocationRelativeTo(null);

		addBOOK.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Connection connection = SQLUtils.connect("root", "");
				lableINPUT[0] = inputs[0].getText();
				lableINPUT[1] = inputs[1].getText();
				lableINPUT[2] = inputs[2].getText();
				lableINPUT[3] = inputs[3].getText();
				// TODO Auto-generated method stub
				SQLUtils.insertToTable(connection, "insert into bookData(name,author,genre,price) values('"
						+ lableINPUT[0] + "','" + lableINPUT[1] + "','" + lableINPUT[2] + "','" + lableINPUT[3] + "')");
				try {
					connection.close();
					System.out.println("Connection closed");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

	}

	public static void logout() {
		int n = JOptionPane.showConfirmDialog(null, "Do you want to logout");
		if (n == JOptionPane.YES_OPTION) {
			LibMain.frame.dispose();
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
					metaData.getColumnName(4),metaData.getColumnName(5) };
			set.last();
			int size = set.getRow();
			set.beforeFirst();

			String[][] data;
			data = new String[size][];
			for (int i = 0; i < size; i++) {
				data[i] = new String[5];
			}

			int i = 0;
			while (set.next()) {
				data[i][0] = String.valueOf(set.getInt("id"));
				data[i][1] = set.getString("Name");
				data[i][2] = set.getString("Author");
				data[i][3] = set.getString("Genre");
				data[i][4] = String.valueOf(set.getFloat("Price"));
				i++;
			}
			JTable bookData = new JTable(data, cols);
			JScrollPane scrollPane = new JScrollPane(bookData);
			scrollPane.setBounds(0, 0, 800, 700);
			LibMain.frame = new JFrame("Book Shelf");
			LibMain.frame.setSize(800, 700);
			LibMain.frame.setSize(800, 700);
			LibMain.frame.setLocationRelativeTo(null);
			LibMain.frame.setVisible(true);
			LibMain.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			LibMain.frame.add(scrollPane);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
