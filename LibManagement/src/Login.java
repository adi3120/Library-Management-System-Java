import javax.swing.*;
public static void Login() {
	JFrame logFrame=new JFrame("JFrame");
	JLabel userN=new JLabel("Username");
	JLabel userP=new JLabel("Password");
	JTextField userInN=new JTextField();
	JTextField userInP=new JTextField();
	JButton login=new JButton("Login");

		logFrame.setSize(400, 180);
		logFrame.setLayout(null);
		logFrame.setVisible(true);
		logFrame.setLocationRelativeTo(null);
		
		userN.setBounds(30,15, 100, 30);
		userP.setBounds(30,50, 100, 30);
		userInN.setBounds(110,15, 100, 30);
		userInP.setBounds(110,50, 100, 30);
		login.setBounds(130, 90, 80, 25);
		
		logFrame.add(userN);
		logFrame.add(userP);
		logFrame.add(userInN);
		logFrame.add(userInP);
		logFrame.add(login);
		
		logFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
