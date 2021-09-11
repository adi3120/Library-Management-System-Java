
import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Admin {
	static String[] names = { "View Books", "View Users", "View Issued Books", "Issue Book", "Add User", "Add Book",
			"Return Book", "Logout" };
	static JButton viewBooks = null, viewUsers = null, viewIssBooks = null, issueBook = null, addUser = null,
			addBook = null, returnBook = null, logout = null;
	static JButton[] buttons = { viewBooks, viewUsers, viewIssBooks, issueBook, addUser, addBook, returnBook, logout };

	public static void ShowAdminMenu(String name, int id) {
		JLabel labelT = new JLabel("Welcome " + name);
		JLabel label = new JLabel("Select an option");
		labelT.setHorizontalAlignment(JLabel.CENTER);
		label.setHorizontalAlignment(JLabel.CENTER);
		LibMain.frame.add(label);
		LibMain.frame.add(labelT);
		LibMain.frame.setTitle("Admin Functions");

		label.setBounds(280, 70, 175, 30);

		label.setFont(new Font("Arial", Font.BOLD, 20));
		label.setForeground(Color.RED);
		label.setBorder(BorderFactory.createLineBorder(Color.black));

		labelT.setBounds(160, 20, 450, 40);
		labelT.setFont(new Font("Arial", Font.BOLD, 40));
		labelT.setForeground(Color.black);
		labelT.setBorder(BorderFactory.createLineBorder(Color.black));

		int xoff = 0, yoff = 0;
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new JButton(names[i]);
			LibMain.frame.add(buttons[i]);
			buttons[i].setBounds(40 + xoff, 160 + yoff, 150, 25);
			if (xoff >= 510) {
				xoff = 0;
				yoff = 50;
			} else
				xoff += 170;

		}

		for (int i = 0; i < buttons.length; i++) {
			final int j = i;
			buttons[j].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if (j == 0) {
						AdminFunctions.showBooks();
					}
					else if(j==5) 
					{
						AdminFunctions.addBook();
					}
					else if(j==7) 
					{
						AdminFunctions.logout();
					}

				}
			});
		}

	}
}
