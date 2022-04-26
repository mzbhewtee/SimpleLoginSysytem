import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

public class gc {

	JFrame List;
	private JTable table;
	private JLabel label;
	private JButton btnNewButton;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gc window = new gc();
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
	public gc() {
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
					String query = "select * from gc";
					PreparedStatement st = con.prepareStatement(query);
					ResultSet rs=st.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
				}
				catch (Exception ee) {
					
				}
			}
		});
		List.getContentPane().setBackground(SystemColor.window);
		List.setBounds(100, 100, 590, 428);
		List.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		List.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 97, 554, 281);
		List.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
//		
		label = new JLabel("Admitted Students");
		label.setForeground(new Color(25, 25, 112));
		label.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		label.setBounds(212, 40, 202, 22);
		List.getContentPane().add(label);
		
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
//		
	}

}
