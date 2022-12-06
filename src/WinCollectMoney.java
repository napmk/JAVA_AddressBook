import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WinCollectMoney extends JDialog {
	private JTextField tfMoney;
	private JTextField tfEtc;
	private JTextField tfJumin;
	private JTable table;
	private JButton btnInput;
	private JLabel lblTotalMoney;
	private int total;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinCollectMoney dialog = new WinCollectMoney();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public WinCollectMoney() {
		setTitle("회비 입금");
		setBounds(100, 100, 458, 541);
		getContentPane().setLayout(null);
		
		JLabel lblMoney = new JLabel("회비:");
		lblMoney.setBounds(28, 41, 57, 15);
		getContentPane().add(lblMoney);
		
		tfMoney = new JTextField();
		tfMoney.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_F1) {
					String temp = tfMoney.getText();
					temp = temp + "0000";
					tfMoney.setText(temp);
				}else if(e.getKeyCode() == KeyEvent.VK_F2) {
					String temp = tfMoney.getText();
					temp = temp + "000";
					tfMoney.setText(temp);
				}					
			}
		});
		tfMoney.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tfMoney.setSelectionStart(0);
				tfMoney.setSelectionEnd(tfMoney.getText().length());
			}
		});
		tfMoney.setText("0");
		tfMoney.setHorizontalAlignment(SwingConstants.RIGHT);
		tfMoney.setBounds(97, 38, 150, 21);
		getContentPane().add(tfMoney);
		tfMoney.setColumns(10);
		
		JLabel lblEtc = new JLabel("비고:");
		lblEtc.setBounds(28, 90, 57, 15);
		getContentPane().add(lblEtc);
		
		tfEtc = new JTextField();
		tfEtc.setHorizontalAlignment(SwingConstants.LEFT);
		tfEtc.setColumns(10);
		tfEtc.setBounds(97, 87, 323, 21);
		getContentPane().add(tfEtc);
		
		JLabel lblJumin = new JLabel("주민번호:");
		lblJumin.setBounds(28, 137, 57, 15);
		getContentPane().add(lblJumin);
		
		tfJumin = new JTextField();
		tfJumin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					showPersonal();
				}
			}
		});
		tfJumin.setHorizontalAlignment(SwingConstants.LEFT);
		tfJumin.setColumns(10);
		tfJumin.setBounds(97, 134, 186, 21);
		getContentPane().add(tfJumin);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 173, 392, 320);
		getContentPane().add(scrollPane);
		String columnNames[]= {"번호","입금날짜","금액","비고"};
		DefaultTableModel dtm = new DefaultTableModel(columnNames,0);
		
		table = new JTable(dtm);
		scrollPane.setViewportView(table);
		
		btnInput = new JButton("입금");
		btnInput.setEnabled(false);
		btnInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertMoney();
			}
		});
		btnInput.setBounds(323, 133, 97, 23);
		getContentPane().add(btnInput);
		
		lblTotalMoney = new JLabel("\uD68C\uBE44:");
		lblTotalMoney.setForeground(new Color(255, 0, 0));
		lblTotalMoney.setFont(new Font("굴림", Font.BOLD, 20));
		lblTotalMoney.setBounds(259, 27, 180, 39);
		getContentPane().add(lblTotalMoney);

	}

	protected void showPersonal() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = 
					DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/sqlDB",
							"root",
							"12345");
			Statement stmt = con.createStatement();			
			String sql = "";
			sql = "select count(*) from addressTBL where jumin='";
			sql = sql + tfJumin.getText() + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				if(rs.getInt(1)==1) {
					btnInput.setEnabled(true);
					sql = "select * from moneyTBL where jumin='";
					sql = sql + tfJumin.getText() + "'";
					rs = stmt.executeQuery(sql);
					DefaultTableModel dtm = (DefaultTableModel)table.getModel();
					dtm.setRowCount(0);
					int cnt=0;
					total = 0;
					while(rs.next()) {
						String record[] = new String[4];
						record[0] = Integer.toString(++cnt);
						record[1] = rs.getString("mDate");
						record[2] = rs.getString("money");
						record[3] = rs.getString("etc");
						dtm.addRow(record);
						total = total + rs.getInt("money");
					}
					lblTotalMoney.setText("회비:" + total + "원");
				}	
			}
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}			
	}

	protected void insertMoney() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = 
					DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/sqlDB",
							"root",
							"12345");
			Statement stmt = con.createStatement();			
			String sql = "";
			sql = "insert into moneyTBL values(null,'";
			sql = sql + tfJumin.getText() + "', curdate()";
			sql = sql + "," + tfMoney.getText() + ",'";
			sql = sql + tfEtc.getText() + "')";
			System.out.println(sql);
			stmt.executeUpdate(sql);
			
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}			
	}
}
