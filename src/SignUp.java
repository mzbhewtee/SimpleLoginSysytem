
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDate;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class SignUp {

	JFrame signUp;
	private JTextField fname;
	private JTextField lname;
	private JTextField username;
	private JPasswordField password;
	private JPasswordField cpassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp window = new SignUp();
					window.signUp.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SignUp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		signUp = new JFrame();
		signUp.setResizable(false);
		signUp.getContentPane().setBackground(SystemColor.textHighlightText);
		signUp.setBounds(100, 100, 590, 438);
		signUp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		signUp.getContentPane().setLayout(null);

		JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon(SignUp.class.getResource("/images/gut.jpg")));
		logo.setBounds(175, 0, 190, 72);
		signUp.getContentPane().add(logo);

		JLabel lblNewLabel = new JLabel("First Name");
		lblNewLabel.setForeground(new Color(25, 25, 112));
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		lblNewLabel.setBounds(39, 81, 117, 22);
		signUp.getContentPane().add(lblNewLabel);

		fname = new JTextField();
		fname.setForeground(new Color(165, 42, 42));
		fname.setBorder(null);
		fname.setBackground(SystemColor.control);
		fname.setBounds(39, 103, 379, 30);
		signUp.getContentPane().add(fname);
		fname.setColumns(10);

		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setForeground(new Color(25, 25, 112));
		lblLastName.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		lblLastName.setBounds(39, 137, 117, 22);
		signUp.getContentPane().add(lblLastName);

		lname = new JTextField();
		lname.setForeground(new Color(165, 42, 42));
		lname.setColumns(10);
		lname.setBorder(null);
		lname.setBackground(SystemColor.menu);
		lname.setBounds(39, 159, 379, 30);
		signUp.getContentPane().add(lname);

		JLabel lblEmail = new JLabel("Username");
		lblEmail.setForeground(new Color(25, 25, 112));
		lblEmail.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		lblEmail.setBounds(39, 194, 117, 22);
		signUp.getContentPane().add(lblEmail);

		username = new JTextField();
		username.setForeground(new Color(165, 42, 42));
		username.setColumns(10);
		username.setBorder(null);
		username.setBackground(SystemColor.menu);
		username.setBounds(39, 216, 379, 30);
		signUp.getContentPane().add(username);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(new Color(25, 25, 112));
		lblPassword.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		lblPassword.setBounds(39, 250, 117, 22);
		signUp.getContentPane().add(lblPassword);

		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setForeground(new Color(25, 25, 112));
		lblConfirmPassword.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		lblConfirmPassword.setBounds(39, 308, 117, 22);
		signUp.getContentPane().add(lblConfirmPassword);

		JButton btnNewButton = new JButton("Sign Up");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
// 				Get the inputted information
				String Fname = fname.getText();
				String Lname = lname.getText();
				String Username = username.getText();
				String Password = password.getText();
				String CPassword = cpassword.getText();
	
				int year = LocalDate.now().getYear();
				
// 				Check if there are no empty field, if yes continue with the operation
				if (Password.equals(CPassword) && !Fname.isEmpty() && !Lname.isEmpty() && !Password.isEmpty()
						&& !Username.isEmpty()) {
					try {
						
// 						Connect to the database, insert all the information gotten into the database
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_db", "root",
								"");
						PreparedStatement ps = con.prepareStatement("insert into info (username,fname,lname,password,year) values(?,?,?,?,?)");
						ps.setString(1, Username);
						ps.setString(2, Fname);
						ps.setString(3, Lname);
						ps.setString(4, Password);
						ps.setInt(5, year);
						

						ps.executeUpdate();
						con.close();
						
// 						Empty the field afterwards
						fname.setText("");
						lname.setText("");
						username.setText("");
						password.setText("");
						cpassword.setText("");
						
						
						LandingPage lp = new LandingPage();
						lp.landingPage.setVisible(true);
						signUp.dispose();
					} catch (Exception ee) {
						JOptionPane.showMessageDialog(null, ee);
					}
				} else {
// 					Display error if the user omits any field
					JOptionPane.showMessageDialog(null, "Something went wrong, please check your inputs");
				}
			}
		});
		btnNewButton.setBackground(new Color(128, 0, 0));
		btnNewButton.setForeground(SystemColor.textHighlightText);
		btnNewButton.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		btnNewButton.setBounds(460, 330, 101, 42);
		signUp.getContentPane().add(btnNewButton);

		JLabel lblNewLabel_1 = new JLabel("Already have an account?");
		lblNewLabel_1.setForeground(new Color(30, 144, 255));
		lblNewLabel_1.setFont(new Font("Comic Sans MS", Font.ITALIC, 11));
		lblNewLabel_1.setBounds(184, 371, 138, 28);
		signUp.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Login");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
// 				Redirect to the landing page
				LandingPage lp = new LandingPage();
				lp.landingPage.setVisible(true);
				signUp.dispose();
			}
		});
		lblNewLabel_2.setForeground(new Color(128, 0, 0));
		lblNewLabel_2.setFont(new Font("Comic Sans MS", Font.ITALIC, 11));
		lblNewLabel_2.setBounds(319, 371, 46, 28);
		signUp.getContentPane().add(lblNewLabel_2);
		
		password = new JPasswordField();
		password.setBorder(null);
		password.setBackground(SystemColor.menu);
		password.setBounds(39, 277, 379, 30);
		signUp.getContentPane().add(password);
		
		cpassword = new JPasswordField();
		cpassword.setBorder(null);
		cpassword.setBackground(SystemColor.menu);
		cpassword.setBounds(39, 330, 379, 30);
		signUp.getContentPane().add(cpassword);
	}
}
