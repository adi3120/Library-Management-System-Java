
import javax.swing.*;

public class LibMain {
	static JFrame frame;

	public static void main(String[] args) {
		mainMenu();
		//Admin.ShowAdminMenu("aditya", 1);
	}

	public static void mainMenu() {
		String[] options = { "Login", "Add new user","Exit" };
		frame = new JFrame("Library Management System");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		frame.setLayout(null);
		JLabel background = new JLabel(
				new ImageIcon("C:\\Users\\Dell\\eclipse-workspace\\LibManagement\\src\\resources\\back1.png"));
		background.setBounds(0, 0, 800, 700);
		frame.add(background);
		frame.setSize(799, 699);
		frame.setSize(800, 700);
		frame.setLocationRelativeTo(null);
		int selection = JOptionPane.showOptionDialog(frame, "Welcome to Library Management System", "Select an option",
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

		if (selection == 0) {
			LogAdd.login();
		} else if (selection == 1) {
			LogAdd.addUser();
		}else if(selection==2)
		{
			frame.dispose();
		}

	}

	public static void refresh() {
		frame.setSize(799, 699);
		frame.setSize(800, 700);
	}

}
