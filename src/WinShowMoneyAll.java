import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class WinShowMoneyAll extends JDialog {
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinShowMoneyAll dialog = new WinShowMoneyAll();
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
	public WinShowMoneyAll() {
		setTitle("전체 회비 내역");
		setBounds(100, 100, 858, 734);
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		String columnNames[]= {"번호","이름","금액","입금날짜","비고"};
		DefaultTableModel dtm = new DefaultTableModel(columnNames,0);
		table = new JTable(dtm);
		
		scrollPane.setViewportView(table);
		
		showMoneyAll();
	}

	private void showMoneyAll() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = 
					DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/sqlDB",
							"root",
							"12345");
			Statement stmt = con.createStatement();			
			String sql = "";
			sql = "select A.name, M.money, M.mDate, M.etc";
			sql = sql + " from moneyTBL M";
			sql = sql + " inner join addressTBL A";
			sql = sql + " on A.jumin = M.jumin";
			System.out.println(sql);
			
			ResultSet rs = stmt.executeQuery(sql);
			
			DefaultTableModel dtm = (DefaultTableModel)table.getModel();
			dtm.setRowCount(0);
			int cnt=0;
			while(rs.next()) {
				String record[] = new String[5];
				record[0] = Integer.toString(++cnt);
				record[1] = rs.getString(1);
				record[2] = rs.getString(2);
				record[3] = rs.getString(3);
				record[4] = rs.getString(4);
				dtm.addRow(record);
			}
											
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}			
	}

}
