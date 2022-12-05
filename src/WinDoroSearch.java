import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.ScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WinDoroSearch extends JDialog {
	private JTextField tfDoro;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinDoroSearch dialog = new WinDoroSearch();
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
	public WinDoroSearch() {
		setTitle("\uB3C4\uB85C\uBA85 \uAC80\uC0C9");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblDoro = new JLabel("도로명");
		panel.add(lblDoro);
		
		tfDoro = new JTextField();
		tfDoro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					showResult();//검색결과를 테이블에 출력
				}
			}
		});
		panel.add(tfDoro);
		tfDoro.setColumns(10);
		
		JButton btnDoro = new JButton("검색");
		btnDoro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showResult();
			}
		});
		panel.add(btnDoro);
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		
		String columnNames[] = {"번호","도로명","시(도)", "구", "동"};
		DefaultTableModel dtm = new DefaultTableModel(columnNames,0);
		
		table = new JTable(dtm);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
			}
		});
		scrollPane.setViewportView(table);


	}

	protected void showResult() {
		// TODO Auto-generated method stub
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = 
					DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/sqlDB",
							"root",
							"12345");
			Statement stmt = con.createStatement();
			//System.out.println("DB연결완료");
			String sql = "SELECT * FROM addrdbTBL where doro like '";
			sql = sql + tfDoro.getText() + "%'";
			ResultSet rs = stmt.executeQuery(sql);
			int cnt=0;
			String record[] = new String[5];// 추가 
			DefaultTableModel dtm =(DefaultTableModel)table.getModel(); // 기존꺼 삭제 다시부름
			dtm.setRowCount(0);
			while(rs.next()) {
				record[0]= Integer.toString(++cnt);
				for(int i=1; i<record.length; i++)
					record[i]=rs.getString(i);
				dtm.addRow(record);
			}
			
			
			
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	public String getAddress() {

		int row = table.getSelectedRow();
		//System.out.println(row);
		String temp = table.getValueAt(row, 2).toString() + " ";
		temp = temp + table.getValueAt(row, 3).toString() + " ";
		temp = temp + table.getValueAt(row, 4).toString() + "(";
		temp = temp + table.getValueAt(row, 1).toString() + ") ";
		return temp;
		
		
		
	}

}
