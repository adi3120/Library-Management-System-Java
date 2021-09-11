import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AdminFunctions {
	public static void viewBooks() {
		Connection connection = SQLUtils.connect("root", "");
		SQLUtils.displayTable(connection, "BookData");
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

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
}
