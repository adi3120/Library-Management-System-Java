
import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Admin {
	static String[] names = { "View Books", "View Users", "View Issued Books", "Issue Book", "Add User", "Add Book",
			"Return Book", "Returned Books", "Logout" };
	static JButton viewBooks = null, viewUsers = null, viewIssBooks = null, issueBook = null, addUser = null,
			addBook = null, returnBook = null, viewRetBooks = null, logout = null;
	static JButton[] buttons = { viewBooks, viewUsers, viewIssBooks, issueBook, addUser, addBook, returnBook,
			viewRetBooks, logout };

	public static void ShowAdminMenu(String name, int id) {

		JLabel label = new JLabel("Select an option");

		label.setHorizontalAlignment(JLabel.CENTER);

		LibMain.frame.add(label);
		LibMain.frame.setTitle("Admin Functions");
		LibMain.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		label.setBounds(280, 70, 175, 30);

		label.setFont(new Font("Arial", Font.BOLD, 20));
		label.setForeground(Color.RED);
		label.setBorder(BorderFactory.createLineBorder(Color.black));

		//JButton ResetInc = new JButton();
		int xoff = 0, yoff = 0;

		for (int i = 0; i < buttons.length; i++) {
			if (i == buttons.length - 1) {
				buttons[i] = new JButton();
				buttons[i].setIcon(
						new ImageIcon("C:\\Users\\Dell\\eclipse-workspace\\LibManagement\\src\\resources\\logout.jpg"));
				// ResetInc.setIcon(new ImageIcon(
				// "C:\\Users\\Dell\\eclipse-workspace\\LibManagement\\src\\resources\\refresh.png"));
				//LibMain.frame.add(ResetInc);
				// ResetInc.setBounds(700, 60, 50, 54);

				LibMain.frame.add(buttons[i]);

				buttons[i].setBounds(700, 10, 50, 54);
				break;
			}
			buttons[i] = new JButton(names[i]);
			LibMain.frame.add(buttons[i]);
			buttons[i].setBounds(20 + xoff, 190 + yoff, 180, 45);
			buttons[i].setFont(new Font("Arial", Font.BOLD, 16));
			if (xoff >= 510) {
				xoff = 0;
				yoff = 70;
			} else
				xoff += 190;

		}
//		ResetInc.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				AdminFunctions.refresh();
//
//			}
//		});
		for (int i = 0; i < buttons.length; i++) {
			final int j = i;
			buttons[j].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if (j == 0) {
						AdminFunctions.showBooks();
					} else if (j == 1) {
						AdminFunctions.viewUsers();
					} else if (j == 2) {
						AdminFunctions.viewIssuedBooks();
					} else if (j == 3) {
						AdminFunctions.issueBook();
					} else if (j == 4) {
						LogAdd.addUser();
					} else if (j == 5) {
						AdminFunctions.addBook();
					} else if (j == 6) {
						AdminFunctions.returnBook();
					} else if (j == 7) {
						AdminFunctions.viewReturnedBooks();
					} else if (j == 8) {
						AdminFunctions.logout();
					}

				}
			});
		}

	}
}
