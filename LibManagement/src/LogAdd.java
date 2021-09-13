import javax.swing.*;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LogAdd {
	public static void login() {
		JFrame logFrame;
		JLabel userN = new JLabel("Username");
		JLabel userP = new JLabel("Password");
		JTextField userInN = new JTextField();
		JPasswordField userInP = new JPasswordField();
		JButton login = new JButton("Login");

		logFrame = LibMain.newJframeWindow("Login", 400, 180, JFrame.DISPOSE_ON_CLOSE);

		userN.setBounds(30, 15, 100, 30);
		userP.setBounds(30, 50, 100, 30);
		userInN.setBounds(110, 15, 100, 30);
		userInP.setBounds(110, 50, 100, 30);
		login.setBounds(130, 90, 80, 25);

		logFrame.add(userN);
		logFrame.add(userP);
		logFrame.add(userInN);
		logFrame.add(userInP);
		logFrame.add(login);

		login.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String uname = userInN.getText();
				String upass = String.valueOf(userInP.getPassword());
				if (uname == "") {
					JOptionPane.showMessageDialog(null, "Please Enter username");
				} else if (upass == "") {
					JOptionPane.showMessageDialog(null, "Please Enter password");
				} else {

					Connection connection = SQLUtils.connect("root", "");
					String st = ("select * from users where username='" + uname + "' and password='" + upass + "'");
					Statement statement;
					try {
						statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
								ResultSet.CONCUR_UPDATABLE);
						ResultSet resultSet = statement.executeQuery(st);
						if (resultSet.next() == false) {
							JOptionPane.showMessageDialog(logFrame, "No user", "Error", JOptionPane.ERROR_MESSAGE);
						} else {
							logFrame.dispose();
							resultSet.beforeFirst();
							resultSet.next();
							String name = resultSet.getString("username");
							String pass = resultSet.getString("password");
							int id = resultSet.getInt("id");
							System.out.println("Enter as : " + name + " , " + pass);
							Admin.ShowAdminMenu(name, id);
							statement.close();
							connection.close();
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

			}
		});
	}

	public static void addUser() {
		JFrame AddUserFrame;
		JLabel userN = new JLabel("Username");
		JLabel userP = new JLabel("Password");
		JLabel userConfP = new JLabel("Confirm Password");
		JTextField userInN = new JTextField();
		JPasswordField userConfInP = new JPasswordField();
		JPasswordField userInP = new JPasswordField();
		JButton addUser = new JButton("Add user");

		AddUserFrame = LibMain.newJframeWindow("Add a new user", 600, 310, JFrame.DISPOSE_ON_CLOSE);

		userN.setBounds(30, 15, 100, 30);
		userP.setBounds(30, 60, 100, 30);
		userConfP.setBounds(30, 105, 150, 30);
		userInN.setBounds(150, 15, 320, 40);
		userInN.setFont(new Font("Arial",Font.PLAIN,20));
		userInP.setBounds(150, 60, 320, 40);
		userInP.setFont(new Font("Arial",Font.PLAIN,20));
		userConfInP.setBounds(150, 105, 320, 40);
		userConfInP.setFont(new Font("Arial",Font.PLAIN,20));
		addUser.setBounds(130, 170, 120, 25);

		AddUserFrame.add(userN);
		AddUserFrame.add(userP);
		AddUserFrame.add(userConfP);
		AddUserFrame.add(userInN);
		AddUserFrame.add(userInP);
		AddUserFrame.add(userConfInP);
		AddUserFrame.add(addUser);

		addUser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String uname = userInN.getText();
				String upass = String.valueOf(userInP.getPassword());
				String cupass = String.valueOf(userConfInP.getPassword());
				if (uname == "") {
					JOptionPane.showMessageDialog(null, "Please Provide username");
				} else if (upass == "") {
					JOptionPane.showMessageDialog(null, "Please Provide password");
				} else if (cupass == "") {
					JOptionPane.showMessageDialog(null, "Please Confirm password");
				} else if (!cupass.equals(upass)) {
					JOptionPane.showMessageDialog(null, "Please Enter password correctly");
				} else {
					Connection connection = SQLUtils.connect("root", "");
					String query = "insert into users(username,password) values('" + uname + "' , '" + upass + "')";
					JOptionPane.showMessageDialog(null,"User Added");
					SQLUtils.insertToTable(connection, query);
					
					AddUserFrame.setVisible(false);
					AddUserFrame.dispose();
					LibMain.mainMenu();
					
					try {
						connection.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}

		});

	}

}
