import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

public class LibMain {
	static JFrame frame;

	public static void main(String[] args) {
		AdminFunctions.refresh();
		mainMenu();
		Admin.ShowAdminMenu("", 0);
	}

	public static void mainMenu() {
		String[] options = { "Login", "Add new user", "Exit" };

		newFrame("Welcome to the Library", 800, 700);
		frame.setLayout(null);

		JLabel background = new JLabel(
				new ImageIcon("C:\\Users\\Dell\\eclipse-workspace\\LibManagement\\src\\resources\\back1.png"));
		JLabel labelT = new JLabel("Welcome to the Library");
		labelT.setHorizontalAlignment(JLabel.CENTER);
		labelT.setBounds(160, 20, 450, 40);
		labelT.setFont(new Font("Arial", Font.BOLD, 40));
		labelT.setForeground(Color.black);
		labelT.setBorder(BorderFactory.createLineBorder(Color.black));
		frame.add(labelT);

		JLabel made = new JLabel("Made by : Aditya Yadav");
		made.setFont(new Font("Arial", Font.BOLD, 20));
		made.setForeground(new Color(102 ,0 ,153));
		made.setBounds(500, 600, 450, 40);

		frame.add(made);

		background.setBounds(0, 0, 800, 700);
		frame.add(background);
		frame.setSize(799, 699);
		frame.setSize(800, 700);

//		int selection = JOptionPane.showOptionDialog(frame, "Welcome to Library Management System", "Select an option",
//				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
//
//		if (selection == 0) {
//			LogAdd.login();
//		} else if (selection == 1) {
//			LogAdd.addUser();
//		} else if (selection == 2) {
//			frame.setVisible(false);
//			frame.dispose();
//			System.exit(0);
//
//		}

	}

	public static void newFrame(String title, int w, int h) {
		frame = new JFrame(title);
		frame.setSize(w, h);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public static JFrame newJframeWindow(String title, int w, int h, int closeop) {
		JFrame iframe = new JFrame(title);
		iframe.setSize(w, h);
		iframe.setDefaultCloseOperation(closeop);
		iframe.setLayout(null);
		iframe.setVisible(true);
		iframe.setLocationRelativeTo(null);
		return iframe;
	}

}