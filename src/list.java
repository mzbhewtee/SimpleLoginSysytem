import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class list {

	JFrame List;
	private JTable table_1;
	private JLabel lblNewLabel;
	private JScrollPane scrollPane;
	private JButton btnNewButton;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					list window = new list();
					window.List.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	
	

	/**
	 * Create the application.
	 */
	public list() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		List = new JFrame();
		List.setResizable(false);
		List.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				
				try {
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_db", "root",
							"");
					String query = "select * from cs";
					PreparedStatement st = con.prepareStatement(query);
					ResultSet rs=st.executeQuery();
					
					table_1.setModel(DbUtils.resultSetToTableModel(rs));
					
				}
				catch (Exception ee) {
					
				}
			}
		});
		List.getContentPane().setBackground(SystemColor.window);
		List.setBounds(100, 100, 590, 428);
		List.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		List.getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 97, 554, 281);
		List.getContentPane().add(scrollPane);
		
		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		
		lblNewLabel = new JLabel("Admitted Students");
		lblNewLabel.setForeground(new Color(25, 25, 112));
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		lblNewLabel.setBounds(212, 40, 202, 22);
		List.getContentPane().add(lblNewLabel);
		
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
		List.getContentPane().add(btnNewButton);
		
	
		
	}
}
;