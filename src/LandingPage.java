// Import necessary libraries

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Button;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class LandingPage {

	JFrame landingPage;
	private JTextField username;
	private JPasswordField password;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LandingPage window = new LandingPage();
					window.landingPage.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LandingPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		landingPage = new JFrame();
		landingPage.setResizable(false);
		landingPage.getContentPane().setBackground(SystemColor.text);
		landingPage.setBounds(100, 100, 590, 428);
		landingPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		landingPage.getContentPane().setLayout(null);
		
		JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon(LandingPage.class.getResource("/images/gut.jpg")));
		logo.setBounds(192, 34, 190, 81);
		landingPage.getContentPane().add(logo);
		
		username = new JTextField();
		username.setForeground(new Color(165, 42, 42));
		username.setBackground(SystemColor.control);
		username.setBorder(null);
		username.setBounds(128, 176, 308, 34);
		landingPage.getContentPane().add(username);
		username.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("username");
		lblNewLabel_1.setForeground(new Color(25, 25, 112));
		lblNewLabel_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setBounds(128, 152, 90, 22);
		landingPage.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("password");
		lblNewLabel_1_1.setForeground(new Color(25, 25, 112));
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(128, 221, 90, 22);
		landingPage.getContentPane().add(lblNewLabel_1_1);
		
		JButton login = new JButton("Login");
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
// 				Get the username and password inputted
				String Username = username.getText();
				String Password = password.getText();
				
				try {
					
// 					Connect to the database and crosscheck if the username and password matches
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_db", "root",
							"");
					PreparedStatement st = (PreparedStatement)con.prepareStatement("Select username, password from info where username=? and password=?");
					st.setString(1, Username);
					st.setString(2, Password);
					ResultSet rs = st.executeQuery();
					
// 					If it does dispose the landing page and move to another page
					if(rs.next()) {
						landingPage.dispose();
						
						JFrame reg = new JFrame();
						reg.setResizable(false);
						reg.getContentPane().setBackground(Color.WHITE);
						reg.setBounds(100, 100, 590, 428);
						reg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						reg.getContentPane().setLayout(null);
						
						JTextField note = new JTextField();
						note.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
						note.setText("N.B: Your admission is solely dependent of your grade");
						note.setForeground(new Color(128, 0, 0));
						note.setBorder(null);
						note.setBounds(10, 358, 302, 20);
						reg.getContentPane().add(note);
						note.setColumns(10);
						
						JTextField grade = new JTextField();
						grade.setBorder(new LineBorder(new Color(171, 173, 179), 1, true));
						grade.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
						grade.setBounds(232, 213, 193, 44);
						reg.getContentPane().add(grade);
						grade.setColumns(10);
						
						JButton submit = new JButton("Submit");
						submit.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								try {
									
// 									After the student inputs the grade, select the information associated with the username and password
									Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_db", "root",
											"");
//									PreparedStatement st = (PreparedStatement)con.prepareStatement("Select * from info");
									Statement st = con.createStatement();
//									String query="Select * from info where username='"+username+"'and password='"+password+"'";
									
									
									ResultSet rs=st.executeQuery("select * from info where username='"+Username+"'and password='"+Password+"'");	
									
// 									If the task is successful check the grade for the next task
									if(rs.next()) {
										try{
											String Grade = grade.getText();
								            Integer number = Integer.valueOf(Grade);
											
// 											If the grade inputted is negative or greater than 20, display an error message to the student
								            if(number<0 | number>20) {
								            	JOptionPane.showMessageDialog(null, "Invalid Input");
								            }
											
// 											If the student does not meet the requirement, display an automated message designated to that particular student that they are not admitted
								            else if(number<12 && number>0) {
								            	String FirstName = rs.getString("fname");
												String SecondName = rs.getString("lname");
												
												reg.dispose();
												JFrame rej = new JFrame();
												rej.setResizable(false);
												rej.getContentPane().setBackground(SystemColor.window);
												rej.setBounds(100, 100, 590, 428);
												rej.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
												rej.getContentPane().setLayout(null);
												
												JLabel lblNewLabel_3 = new JLabel("Dear "+FirstName+",");
												lblNewLabel_3.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
												lblNewLabel_3.setBounds(68, 112, 140, 18);
												rej.getContentPane().add(lblNewLabel_3);
												
												JLabel lblNewLabel = new JLabel("Thank you for considering to study at The African Leadership University.");
												lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
												lblNewLabel.setBounds(68, 141, 460, 24);
												rej.getContentPane().add(lblNewLabel);
												
												JLabel lblNewLabel_1 = new JLabel("We are sorry to inform you that we won't be considering you application,");
												lblNewLabel_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
												lblNewLabel_1.setBounds(68, 161, 444, 24);
												rej.getContentPane().add(lblNewLabel_1);
												
												JLabel lblNewLabel_2 = new JLabel("as your grade is below our minimum requirement. We wish you luck!");
												lblNewLabel_2.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
												lblNewLabel_2.setBounds(68, 182, 406, 24);
												rej.getContentPane().add(lblNewLabel_2);
												
												JLabel lblNewLabel_4 = new JLabel("");
												lblNewLabel_4.setIcon(new ImageIcon("/images/gut.jpg"));
												lblNewLabel_4.setBounds(383, 308, 191, 81);
												rej.getContentPane().add(lblNewLabel_4);
												rej.setVisible(true);
								            }
// 											If the student meets the requiremnets, dispose the current page and redirect the student to a page with dashboard
								            else if(number>11 && number<15) {
								            	String Course = "Business Studies";
								            	String FirstName = rs.getString("fname");
												String SecondName = rs.getString("lname");
												int ID = rs.getInt("id");
												int year = rs.getInt("year");
										    
// 										    Create a roll number for the student the current year method and the automated id generator 
												String RegNo = year+"/"+ID;
												
												reg.dispose();
								            		JFrame acc = new JFrame();
								        		acc.setResizable(false);
								        		acc.getContentPane().setBackground(SystemColor.window);
								        		acc.setBounds(100, 100, 590, 428);
								        		acc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
								        		acc.getContentPane().setLayout(null);
								        		
								        		
								        		JLabel lblNewLabel = new JLabel("Congratulations.");
								        		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
								        		lblNewLabel.setBounds(68, 109, 460, 24);
								        		acc.getContentPane().add(lblNewLabel);
								        		
								        		JLabel lblNewLabel_2 = new JLabel("Check the admissions tab for your details");
								        		lblNewLabel_2.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
								        		lblNewLabel_2.setBounds(137, 174, 258, 24);
								        		acc.getContentPane().add(lblNewLabel_2);
								        		
								        		JLabel lblNewLabel_4 = new JLabel("");
								        		lblNewLabel_4.setIcon(new ImageIcon(LandingPage.class.getResource("/images/gut.jpg")));
								        		lblNewLabel_4.setBounds(383, 308, 191, 81);
								        		acc.getContentPane().add(lblNewLabel_4);
								        		
// 											Create a menu bar that will contain the Student profile and admitted student information
								        		JMenuBar menuBar = new JMenuBar();
								        		menuBar.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
								        		menuBar.setBounds(0, 0, 584, 34);
								        		acc.getContentPane().add(menuBar);
								        		
// 											in the profile section, display the student info and the department they belong
								        		JMenu mnNewMenu_1 = new JMenu("Profile");
								        		mnNewMenu_1.setForeground(new Color(128, 0, 0));
								        		mnNewMenu_1.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
								        		menuBar.add(mnNewMenu_1);
								        		
								        		JMenuItem admin = new JMenuItem("Admission Decision");
								        		admin.addActionListener(new ActionListener() {
								        			public void actionPerformed(ActionEvent e) {
								        				
								        				JFrame re = new JFrame();
								        				re.setResizable(false);
								        				re.getContentPane().setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
								        				re.getContentPane().setBackground(SystemColor.window);
								        				re.setBounds(100, 100, 590, 428);
								        				re.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
								        				re.getContentPane().setLayout(null);
								        				re.setVisible(true);
								        				
								        				JLabel lblNewLabel_4 = new JLabel("");
								        				lblNewLabel_4.setIcon(new ImageIcon(LandingPage.class.getResource("/images/gut.jpg")));
								        				lblNewLabel_4.setBounds(383, 308, 191, 81);
								        				re.getContentPane().add(lblNewLabel_4);
								        				
								        				JLabel lblNewLabel_2 = new JLabel("Admission Details");
								        				lblNewLabel_2.setForeground(new Color(25, 25, 112));
								        				lblNewLabel_2.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
								        				lblNewLabel_2.setBounds(201, 25, 182, 44);
								        				re.getContentPane().add(lblNewLabel_2);
								        				
								        				JLabel lblNewLabel_3 = new JLabel("Congratulations once again on your admission.");
								        				lblNewLabel_3.setForeground(new Color(128, 0, 0));
								        				lblNewLabel_3.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
								        				lblNewLabel_3.setBounds(139, 249, 354, 25);
								        				re.getContentPane().add(lblNewLabel_3);
								        				
								        				JLabel Fname = new JLabel("First Name:");
								        				Fname.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
								        				Fname.setBounds(56, 96, 113, 19);
								        				re.getContentPane().add(Fname);
								        				
								        				JLabel lname = new JLabel("Last Name:");
								        				lname.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
								        				lname.setBounds(56, 126, 113, 19);
								        				re.getContentPane().add(lname);
								        				
								        				JLabel lblGrade = new JLabel("Grade:");
								        				lblGrade.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
								        				lblGrade.setBounds(56, 156, 113, 19);
								        				re.getContentPane().add(lblGrade);
								        				
								        				JLabel lblCourse = new JLabel("Course:");
								        				lblCourse.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
								        				lblCourse.setBounds(56, 184, 113, 19);
								        				re.getContentPane().add(lblCourse);
								        				
								        				JLabel lblRegistrationNumber = new JLabel("Registration Number:");
								        				lblRegistrationNumber.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
								        				lblRegistrationNumber.setBounds(56, 215, 176, 19);
								        				re.getContentPane().add(lblRegistrationNumber);
								        				
								        				JLabel fname = new JLabel(FirstName);
								        				fname.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
								        				fname.setBounds(139, 98, 119, 14);
								        				re.getContentPane().add(fname);
								        				
								        				JLabel fname_1 = new JLabel(SecondName);
								        				fname_1.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
								        				fname_1.setBounds(139, 126, 119, 15);
								        				re.getContentPane().add(fname_1);
								        				
								        				JLabel fname_2 = new JLabel(Grade);
								        				fname_2.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
								        				fname_2.setBounds(139, 156, 119, 14);
								        				re.getContentPane().add(fname_2);
								        				
								        				JLabel course = new JLabel(Course);
								        				course.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
								        				course.setBounds(139, 186, 139, 14);
								        				re.getContentPane().add(course);
								        				
								        				JLabel regNo = new JLabel(year+"/"+ID);
								        				regNo.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
								        				regNo.setBounds(203, 215, 119, 14);
								        				re.getContentPane().add(regNo);

								        				JLabel lblNewLabel = new JLabel("Congratulations.");
								        				lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
								        				lblNewLabel.setBounds(68, 109, 460, 24);
								        				acc.getContentPane().add(lblNewLabel);
								        				
								        				btnNewButton = new JButton("Back");
								        				btnNewButton.addActionListener(new ActionListener() {
								        					public void actionPerformed(ActionEvent e) {
								        						decision lp = new decision();
								        						lp.gcc.setVisible(true);
								        					}
								        				});
								        				btnNewButton.setForeground(new Color(128, 0, 0));
								        				btnNewButton.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
								        				btnNewButton.setBounds(475, 11, 89, 23);
								        				re.getContentPane().add(btnNewButton);
								        				
								        				try {
// 														Store the student information into their designated department according to their grade
								    						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_db", "root",
								    								"");
								    						PreparedStatement ps = con.prepareStatement("insert into bs (FirstName,LastName,RegistrationNumber) values(?,?,?)");
								    						ps.setString(1, FirstName);
								    						ps.setString(2, SecondName);
								    						ps.setString(3, RegNo);
								    						
								    						

								    						ps.executeUpdate();
								    						con.close();
								    						
								    						
								    					} catch (Exception ee) {
								    						JOptionPane.showMessageDialog(null, "Student Already Exists, Previous Grdae will be retained!!!");
								    					}
								        				
								        			}
								        		});
								        		
// 										    In the Programs menu, create tab for all the department
								        		JMenu mnNewMenu = new JMenu("Programs");
								        		mnNewMenu.setForeground(new Color(128, 0, 0));
								        		mnNewMenu.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
								        		menuBar.add(mnNewMenu);
								        		
								        		JMenuItem mntmNewMenuItem = new JMenuItem("Computer Science");
								        		mntmNewMenuItem.addActionListener(new ActionListener() {
								        			public void actionPerformed(ActionEvent e) {
								        				
// 													If the user clicked computer science, redirect them to List page/file
								        				list ls = new list();
								        				ls.List.setVisible(true);
								        				
								        			}
								        		});
								        		mntmNewMenuItem.setForeground(new Color(25, 25, 112));
								        		mntmNewMenuItem.setFont(new Font("Comic Sans MS", Font.ITALIC, 15));
								        		mnNewMenu.add(mntmNewMenuItem);
								        		
								        		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Global Challenges");
								        		mntmNewMenuItem_1.addActionListener(new ActionListener() {
								        			public void actionPerformed(ActionEvent e) {
								        				
// 													If the user clicked global challenges, redirect them to gc page/file
								        				gc ls = new gc();
								        				ls.List.setVisible(true);
								        			}
								        		});
								        		mntmNewMenuItem_1.setForeground(new Color(25, 25, 112));
								        		mntmNewMenuItem_1.setFont(new Font("Comic Sans MS", Font.ITALIC, 15));
								        		mnNewMenu.add(mntmNewMenuItem_1);
								        		
								        		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Business Study");
								        		mntmNewMenuItem_2.addActionListener(new ActionListener() {
								        			public void actionPerformed(ActionEvent e) {
								        				
// 													If the user clicked business studies, redirect them to bs page/file
								        				bs ls = new bs();
								        				ls.List.setVisible(true);
								        			}
								        		});
								        		mntmNewMenuItem_2.setForeground(new Color(25, 25, 112));
								        		mntmNewMenuItem_2.setFont(new Font("Comic Sans MS", Font.ITALIC, 15));
								        		mnNewMenu.add(mntmNewMenuItem_2);
								        		
								        		
								        		admin.setForeground(new Color(25, 25, 112));
								        		admin.setFont(new Font("Comic Sans MS", Font.ITALIC, 15));
								        		mnNewMenu_1.add(admin);
								        		acc.setVisible(true);
								            }
								            
								            else if(number>14 && number<18) {
								            	String Course = "Global Challenges";
								            	String FirstName = rs.getString("fname");
												String SecondName = rs.getString("lname");
												int ID = rs.getInt("id");
												int year = rs.getInt("year");
												String RegNo = year+"/"+ID;
												
												reg.dispose();
								            	JFrame acc = new JFrame();
								        		acc.setResizable(false);
								        		acc.getContentPane().setBackground(SystemColor.window);
								        		acc.setBounds(100, 100, 590, 428);
								        		acc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
								        		acc.getContentPane().setLayout(null);
								        		
								        		
								        		JLabel lblNewLabel = new JLabel("Congratulations.");
								        		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
								        		lblNewLabel.setBounds(68, 109, 460, 24);
								        		acc.getContentPane().add(lblNewLabel);
								        		
								        		JLabel lblNewLabel_2 = new JLabel("Check the admissions tab for your details");
								        		lblNewLabel_2.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
								        		lblNewLabel_2.setBounds(137, 174, 258, 24);
								        		acc.getContentPane().add(lblNewLabel_2);
								        		
								        		JLabel lblNewLabel_4 = new JLabel("");
								        		lblNewLabel_4.setIcon(new ImageIcon(LandingPage.class.getResource("/images/gut.jpg")));
								        		lblNewLabel_4.setBounds(383, 308, 191, 81);
								        		acc.getContentPane().add(lblNewLabel_4);
								        		

								        		JMenuBar menuBar = new JMenuBar();
								        		menuBar.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
								        		menuBar.setBounds(0, 0, 584, 34);
								        		acc.getContentPane().add(menuBar);
								        		

								        		JMenu mnNewMenu_1 = new JMenu("Profile");
								        		mnNewMenu_1.setForeground(new Color(128, 0, 0));
								        		mnNewMenu_1.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
								        		menuBar.add(mnNewMenu_1);
								        		
								        		JMenuItem admin = new JMenuItem("Admission Decision");
								        		admin.addActionListener(new ActionListener() {
								        			public void actionPerformed(ActionEvent e) {
								        				
								        				JFrame re = new JFrame();
								        				re.setResizable(false);
								        				re.getContentPane().setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
								        				re.getContentPane().setBackground(SystemColor.window);
								        				re.setBounds(100, 100, 590, 428);
								        				re.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
								        				re.getContentPane().setLayout(null);
								        				re.setVisible(true);
								        				
								        				JLabel lblNewLabel_4 = new JLabel("");
								        				lblNewLabel_4.setIcon(new ImageIcon(LandingPage.class.getResource("/images/gut.jpg")));
								        				lblNewLabel_4.setBounds(383, 308, 191, 81);
								        				re.getContentPane().add(lblNewLabel_4);
								        				
								        				JLabel lblNewLabel_2 = new JLabel("Admission Details");
								        				lblNewLabel_2.setForeground(new Color(25, 25, 112));
								        				lblNewLabel_2.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
								        				lblNewLabel_2.setBounds(201, 25, 182, 44);
								        				re.getContentPane().add(lblNewLabel_2);
								        				
								        				JLabel lblNewLabel_3 = new JLabel("Congratulations once again on your admission.");
								        				lblNewLabel_3.setForeground(new Color(128, 0, 0));
								        				lblNewLabel_3.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
								        				lblNewLabel_3.setBounds(139, 249, 354, 25);
								        				re.getContentPane().add(lblNewLabel_3);
								        				
								        				JLabel Fname = new JLabel("First Name:");
								        				Fname.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
								        				Fname.setBounds(56, 96, 113, 19);
								        				re.getContentPane().add(Fname);
								        				
								        				JLabel lname = new JLabel("Last Name:");
								        				lname.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
								        				lname.setBounds(56, 126, 113, 19);
								        				re.getContentPane().add(lname);
								        				
								        				JLabel lblGrade = new JLabel("Grade:");
								        				lblGrade.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
								        				lblGrade.setBounds(56, 156, 113, 19);
								        				re.getContentPane().add(lblGrade);
								        				
								        				JLabel lblCourse = new JLabel("Course:");
								        				lblCourse.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
								        				lblCourse.setBounds(56, 184, 113, 19);
								        				re.getContentPane().add(lblCourse);
								        				
								        				JLabel lblRegistrationNumber = new JLabel("Registration Number:");
								        				lblRegistrationNumber.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
								        				lblRegistrationNumber.setBounds(56, 215, 176, 19);
								        				re.getContentPane().add(lblRegistrationNumber);
								        				
								        				JLabel fname = new JLabel(FirstName);
								        				fname.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
								        				fname.setBounds(139, 98, 119, 14);
								        				re.getContentPane().add(fname);
								        				
								        				JLabel fname_1 = new JLabel(SecondName);
								        				fname_1.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
								        				fname_1.setBounds(139, 126, 119, 15);
								        				re.getContentPane().add(fname_1);
								        				
								        				JLabel fname_2 = new JLabel(Grade);
								        				fname_2.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
								        				fname_2.setBounds(139, 156, 119, 14);
								        				re.getContentPane().add(fname_2);
								        				
								        				JLabel course = new JLabel(Course);
								        				course.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
								        				course.setBounds(139, 186, 139, 14);
								        				re.getContentPane().add(course);
								        				
								        				JLabel regNo = new JLabel(year+"/"+ID);
								        				regNo.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
								        				regNo.setBounds(203, 215, 119, 14);
								        				re.getContentPane().add(regNo);

								        				JLabel lblNewLabel = new JLabel("Congratulations.");
								        				lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
								        				lblNewLabel.setBounds(68, 109, 460, 24);
								        				acc.getContentPane().add(lblNewLabel);
								        				
								        				btnNewButton = new JButton("Back");
								        				btnNewButton.addActionListener(new ActionListener() {
								        					public void actionPerformed(ActionEvent e) {
								        						decision lp = new decision();
								        						lp.gcc.setVisible(true);
								        					}
								        				});
								        				btnNewButton.setForeground(new Color(128, 0, 0));
								        				btnNewButton.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
								        				btnNewButton.setBounds(475, 11, 89, 23);
								        				re.getContentPane().add(btnNewButton);
								        				
								        				try {
								    						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_db", "root",
								    								"");
								    						PreparedStatement ps = con.prepareStatement("insert into gc (FirstName,LastName,RegistrationNumber) values(?,?,?)");
								    						ps.setString(1, FirstName);
								    						ps.setString(2, SecondName);
								    						ps.setString(3, RegNo);
								    						
								    						

								    						ps.executeUpdate();
								    						con.close();
								    						
								    						
								    					} catch (Exception ee) {
								    						JOptionPane.showMessageDialog(null, "Student Already Exists, Previous Grdae will be retained!!!");
								    					}
								        				
								        			}
								        		});
								        		
								        		JMenu mnNewMenu = new JMenu("Programs");
								        		mnNewMenu.setForeground(new Color(128, 0, 0));
								        		mnNewMenu.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
								        		menuBar.add(mnNewMenu);
								        		
								        		JMenuItem mntmNewMenuItem = new JMenuItem("Computer Science");
								        		mntmNewMenuItem.addActionListener(new ActionListener() {
								        			public void actionPerformed(ActionEvent e) {
								        				
								        				list ls = new list();
								        				ls.List.setVisible(true);
								        				
								        			}
								        		});
								        		mntmNewMenuItem.setForeground(new Color(25, 25, 112));
								        		mntmNewMenuItem.setFont(new Font("Comic Sans MS", Font.ITALIC, 15));
								        		mnNewMenu.add(mntmNewMenuItem);
								        		
								        		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Global Challenges");
								        		mntmNewMenuItem_1.addActionListener(new ActionListener() {
								        			public void actionPerformed(ActionEvent e) {
								        				
								        				gc ls = new gc();
								        				ls.List.setVisible(true);
								        			}
								        		});
								        		mntmNewMenuItem_1.setForeground(new Color(25, 25, 112));
								        		mntmNewMenuItem_1.setFont(new Font("Comic Sans MS", Font.ITALIC, 15));
								        		mnNewMenu.add(mntmNewMenuItem_1);
								        		
								        		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Business Study");
								        		mntmNewMenuItem_2.addActionListener(new ActionListener() {
								        			public void actionPerformed(ActionEvent e) {
								        				
								        				bs ls = new bs();
								        				ls.List.setVisible(true);
								        			}
								        		});
								        		mntmNewMenuItem_2.setForeground(new Color(25, 25, 112));
								        		mntmNewMenuItem_2.setFont(new Font("Comic Sans MS", Font.ITALIC, 15));
								        		mnNewMenu.add(mntmNewMenuItem_2);
								        		
								        		admin.setForeground(new Color(25, 25, 112));
								        		admin.setFont(new Font("Comic Sans MS", Font.ITALIC, 15));
								        		mnNewMenu_1.add(admin);
								        		acc.setVisible(true);
								            }
								            else if(number>17 && number<21) {
								            	String Course = "Computer Science";
								            	String FirstName = rs.getString("fname");
												String SecondName = rs.getString("lname");
												int ID = rs.getInt("id");
												int year = rs.getInt("year");
												String RegNo =year+"/"+ID;
												
												reg.dispose();
								            	JFrame acc = new JFrame();
								        		acc.setResizable(false);
								        		acc.getContentPane().setBackground(SystemColor.window);
								        		acc.setBounds(100, 100, 590, 428);
								        		acc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
								        		acc.getContentPane().setLayout(null);
								        		
								        		
								        		JLabel lblNewLabel = new JLabel("Congratulations.");
								        		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
								        		lblNewLabel.setBounds(68, 109, 460, 24);
								        		acc.getContentPane().add(lblNewLabel);
								        		
								        		JLabel lblNewLabel_2 = new JLabel("Check the admissions tab for your details");
								        		lblNewLabel_2.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
								        		lblNewLabel_2.setBounds(137, 174, 258, 24);
								        		acc.getContentPane().add(lblNewLabel_2);
								        		
								        		JLabel lblNewLabel_4 = new JLabel("");
								        		lblNewLabel_4.setIcon(new ImageIcon(LandingPage.class.getResource("/images/gut.jpg")));
								        		lblNewLabel_4.setBounds(383, 308, 191, 81);
								        		acc.getContentPane().add(lblNewLabel_4);
								        		

								        		JMenuBar menuBar = new JMenuBar();
								        		menuBar.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
								        		menuBar.setBounds(0, 0, 584, 34);
								        		acc.getContentPane().add(menuBar);
								        		
								        		
								        		JMenu mnNewMenu_1 = new JMenu("Profile");
								        		mnNewMenu_1.setForeground(new Color(128, 0, 0));
								        		mnNewMenu_1.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
								        		menuBar.add(mnNewMenu_1);
								        		
								        		JMenuItem admin = new JMenuItem("Admission Decision");
								        		admin.addActionListener(new ActionListener() {
								        			public void actionPerformed(ActionEvent e) {
								        				
								        				JFrame re = new JFrame();
								        				re.setResizable(false);
								        				re.getContentPane().setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
								        				re.getContentPane().setBackground(SystemColor.window);
								        				re.setBounds(100, 100, 590, 428);
								        				re.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
								        				re.getContentPane().setLayout(null);
								        				re.setVisible(true);
								        				
								        				JLabel lblNewLabel_4 = new JLabel("");
								        				lblNewLabel_4.setIcon(new ImageIcon(LandingPage.class.getResource("/images/gut.jpg")));
								        				lblNewLabel_4.setBounds(383, 308, 191, 81);
								        				re.getContentPane().add(lblNewLabel_4);
								        				
								        				JLabel lblNewLabel_2 = new JLabel("Admission Details");
								        				lblNewLabel_2.setForeground(new Color(25, 25, 112));
								        				lblNewLabel_2.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
								        				lblNewLabel_2.setBounds(201, 25, 182, 44);
								        				re.getContentPane().add(lblNewLabel_2);
								        				
								        				JLabel lblNewLabel_3 = new JLabel("Congratulations once again on your admission.");
								        				lblNewLabel_3.setForeground(new Color(128, 0, 0));
								        				lblNewLabel_3.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
								        				lblNewLabel_3.setBounds(139, 249, 354, 25);
								        				re.getContentPane().add(lblNewLabel_3);
								        				
								        				JLabel Fname = new JLabel("First Name:");
								        				Fname.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
								        				Fname.setBounds(56, 96, 113, 19);
								        				re.getContentPane().add(Fname);
								        				
								        				JLabel lname = new JLabel("Last Name:");
								        				lname.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
								        				lname.setBounds(56, 126, 113, 19);
								        				re.getContentPane().add(lname);
								        				
								        				JLabel lblGrade = new JLabel("Grade:");
								        				lblGrade.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
								        				lblGrade.setBounds(56, 156, 113, 19);
								        				re.getContentPane().add(lblGrade);
								        				
								        				JLabel lblCourse = new JLabel("Course:");
								        				lblCourse.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
								        				lblCourse.setBounds(56, 184, 113, 19);
								        				re.getContentPane().add(lblCourse);
								        				
								        				JLabel lblRegistrationNumber = new JLabel("Registration Number:");
								        				lblRegistrationNumber.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
								        				lblRegistrationNumber.setBounds(56, 215, 176, 19);
								        				re.getContentPane().add(lblRegistrationNumber);
								        				
								        				JLabel fname = new JLabel(FirstName);
								        				fname.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
								        				fname.setBounds(139, 98, 119, 14);
								        				re.getContentPane().add(fname);
								        				
								        				JLabel fname_1 = new JLabel(SecondName);
								        				fname_1.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
								        				fname_1.setBounds(139, 126, 119, 15);
								        				re.getContentPane().add(fname_1);
								        				
								        				JLabel fname_2 = new JLabel(Grade);
								        				fname_2.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
								        				fname_2.setBounds(139, 156, 119, 14);
								        				re.getContentPane().add(fname_2);
								        				
								        				JLabel course = new JLabel(Course);
								        				course.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
								        				course.setBounds(139, 186, 139, 14);
								        				re.getContentPane().add(course);
								        				
								        				JLabel regNo = new JLabel(year+"/"+ID);
								        				regNo.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
								        				regNo.setBounds(203, 215, 119, 14);
								        				re.getContentPane().add(regNo);

								        				JLabel lblNewLabel = new JLabel("Congratulations.");
								        				lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
								        				lblNewLabel.setBounds(68, 109, 460, 24);
								        				acc.getContentPane().add(lblNewLabel);
								        				
								        				btnNewButton = new JButton("Back");
								        				btnNewButton.addActionListener(new ActionListener() {
								        					public void actionPerformed(ActionEvent e) {
								        						decision lp = new decision();
								        						lp.gcc.setVisible(true);
								        					}
								        				});
								        				btnNewButton.setForeground(new Color(128, 0, 0));
								        				btnNewButton.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
								        				btnNewButton.setBounds(475, 11, 89, 23);
								        				re.getContentPane().add(btnNewButton);
								        				
								        				try {
								    						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_db", "root",
								    								"");
								    						PreparedStatement ps = con.prepareStatement("insert into cs (FirstName,LastName,RegistrationNumber) values(?,?,?)");
								    						ps.setString(1, FirstName);
								    						ps.setString(2, SecondName);
								    						ps.setString(3, RegNo);
								    						
								    						ps.executeUpdate();
								    						con.close();
								    						
								    						
								    					} catch (Exception ee) {
								    						JOptionPane.showMessageDialog(null, "Student Already Exists, Previous Grdae will be retained!!!");
								    					}
								        				
								        			}
								        		});
								        		
								        		JMenu mnNewMenu = new JMenu("Programs");
								        		mnNewMenu.setForeground(new Color(128, 0, 0));
								        		mnNewMenu.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
								        		menuBar.add(mnNewMenu);
								        		
								        		JMenuItem mntmNewMenuItem = new JMenuItem("Computer Science");
								        		mntmNewMenuItem.addActionListener(new ActionListener() {
								        			public void actionPerformed(ActionEvent e) {
								        				
								        				list ls = new list();
								        				ls.List.setVisible(true);
								        				
								        			}
								        		});
								        		mntmNewMenuItem.setForeground(new Color(25, 25, 112));
								        		mntmNewMenuItem.setFont(new Font("Comic Sans MS", Font.ITALIC, 15));
								        		mnNewMenu.add(mntmNewMenuItem);
								        		
								        		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Global Challenges");
								        		mntmNewMenuItem_1.addActionListener(new ActionListener() {
								        			public void actionPerformed(ActionEvent e) {
								        				
								        				gc ls = new gc();
								        				ls.List.setVisible(true);
								        			}
								        		});
								        		mntmNewMenuItem_1.setForeground(new Color(25, 25, 112));
								        		mntmNewMenuItem_1.setFont(new Font("Comic Sans MS", Font.ITALIC, 15));
								        		mnNewMenu.add(mntmNewMenuItem_1);
								        		
								        		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Business Study");
								        		mntmNewMenuItem_2.addActionListener(new ActionListener() {
								        			public void actionPerformed(ActionEvent e) {
								        				
								        				bs ls = new bs();
								        				ls.List.setVisible(true);
								        			}
								        		});
								        		mntmNewMenuItem_2.setForeground(new Color(25, 25, 112));
								        		mntmNewMenuItem_2.setFont(new Font("Comic Sans MS", Font.ITALIC, 15));
								        		mnNewMenu.add(mntmNewMenuItem_2);
								        		
								        		
								        		admin.setForeground(new Color(25, 25, 112));
								        		admin.setFont(new Font("Comic Sans MS", Font.ITALIC, 15));
								        		mnNewMenu_1.add(admin);
								        		acc.setVisible(true);
								            }
								        }
								        catch (NumberFormatException ex){
								            JOptionPane.showMessageDialog(null, "Please input a digit!");
								        }
										grade.setText(null);
									}
																	
								}
								catch(Exception ee){
									JOptionPane.showMessageDialog(null,ee);
									System.out.println(ee);
								}
								
							
							}
						});
						submit.setBorder(new LineBorder(new Color(0, 0, 0), 0, true));
						submit.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
						submit.setBackground(new Color(128, 0, 0));
						submit.setForeground(SystemColor.textHighlightText);
						submit.setBounds(423, 299, 100, 44);
						reg.getContentPane().add(submit);
						
						JLabel heading = new JLabel("Registration Portal");
						heading.setForeground(new Color(25, 25, 112));
						heading.setHorizontalAlignment(SwingConstants.CENTER);
						heading.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
						heading.setBounds(74, 22, 404, 63);
						reg.getContentPane().add(heading);
						
						JLabel lblNewLabel = new JLabel("For your admission to be considered, Kindly select your grade/score in the options below.");
						lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
						lblNewLabel.setBounds(34, 153, 530, 35);
						reg.getContentPane().add(lblNewLabel);
						
						JLabel lblNewLabel_1 = new JLabel("Score/Grade");
						lblNewLabel_1.setBorder(new LineBorder(new Color(192, 192, 192), 1, true));
						lblNewLabel_1.setBackground(new Color(169, 169, 169));
						lblNewLabel_1.setForeground(new Color(25, 25, 112));
						lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
						lblNewLabel_1.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
						lblNewLabel_1.setBounds(141, 213, 92, 44);
						reg.getContentPane().add(lblNewLabel_1);
						reg.setVisible(true);
					}
					
					
					else {
						JOptionPane.showMessageDialog(null, "wrong username and password");
					}
				}
				catch(Exception ee){
					ee.printStackTrace();
				}
			}
		});
		
		login.setBackground(new Color(128, 0, 0));
		login.setForeground(new Color(248, 248, 255));
		login.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		login.setBounds(337, 290, 101, 42);
		landingPage.getContentPane().add(login);
		
		JLabel lblNewLabel_2 = new JLabel("Don't have an account yet?");
		lblNewLabel_2.setForeground(new Color(30, 144, 255));
		lblNewLabel_2.setFont(new Font("Comic Sans MS", Font.ITALIC, 11));
		lblNewLabel_2.setBounds(181, 346, 149, 31);
		landingPage.getContentPane().add(lblNewLabel_2);
		
// 		Create a sign up page
		JLabel SignUp = new JLabel("Sign Up");
		SignUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
// 				When the button is clicked, redirect to signup page then dispose the landing page
				SignUp su = new SignUp();
				su.signUp.setVisible(true);
				landingPage.dispose();
			}
		});
		SignUp.setForeground(new Color(128, 0, 0));
		SignUp.setFont(new Font("Comic Sans MS", Font.ITALIC, 11));
		SignUp.setBounds(325, 346, 46, 30);
		landingPage.getContentPane().add(SignUp);
		
		password = new JPasswordField();
		password.setBorder(null);
		password.setBackground(SystemColor.menu);
		password.setBounds(128, 248, 308, 31);
		landingPage.getContentPane().add(password);
	}
}
