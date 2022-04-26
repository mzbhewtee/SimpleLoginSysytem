import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;

public class decision {

	JFrame gcc;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					decision window = new decision();
					window.gcc.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public decision() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		gcc = new JFrame();
		gcc.setResizable(false);
		gcc.getContentPane().setBackground(SystemColor.window);
		gcc.setBounds(100, 100, 590, 428);
		gcc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gcc.getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		menuBar.setBounds(0, 0, 584, 34);
		gcc.getContentPane().add(menuBar);
		
		
		JLabel lblNewLabel = new JLabel("Congratulations.");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		lblNewLabel.setBounds(68, 109, 460, 24);
		gcc.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Check the admissions tab for your details");
		lblNewLabel_2.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(137, 174, 258, 24);
		gcc.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(decision.class.getResource("/images/gut.jpg")));
		lblNewLabel_4.setBounds(383, 308, 191, 81);
		gcc.getContentPane().add(lblNewLabel_4);
		


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
				lblNewLabel_4.setIcon(new ImageIcon(decision.class.getResource("/images/gut.jpg")));
				lblNewLabel_4.setBounds(383, 308, 191, 81);
				re.getContentPane().add(lblNewLabel_4);
				
				JLabel lblNewLabel_2 = new JLabel("Admission Details");
				lblNewLabel_2.setForeground(new Color(25, 25, 112));
				lblNewLabel_2.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
				lblNewLabel_2.setBounds(201, 25, 182, 44);
				re.getContentPane().add(lblNewLabel_2);
				
				JLabel lblNewLabel_3 = new JLabel("No longer available, check your email for your details");
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
				
				JLabel fname = new JLabel("N/A");
				fname.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
				fname.setBounds(139, 98, 119, 14);
				re.getContentPane().add(fname);
				
				JLabel fname_1 = new JLabel("N/A");
				fname_1.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
				fname_1.setBounds(139, 126, 119, 15);
				re.getContentPane().add(fname_1);
				
				JLabel fname_2 = new JLabel("N/A");
				fname_2.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
				fname_2.setBounds(139, 156, 119, 14);
				re.getContentPane().add(fname_2);
				
				JLabel course = new JLabel("N/A");
				course.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
				course.setBounds(139, 186, 139, 14);
				re.getContentPane().add(course);
				
				JLabel regNo = new JLabel("N/A");
				regNo.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
				regNo.setBounds(203, 215, 119, 14);
				re.getContentPane().add(regNo);

				JLabel lblNewLabel = new JLabel("Congratulations.");
				lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
				lblNewLabel.setBounds(68, 109, 460, 24);
				gcc.getContentPane().add(lblNewLabel);
				
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

	}

	}


