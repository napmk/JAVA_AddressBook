import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.PopupMenu;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPopupMenu;
import java.awt.Component;
import javax.swing.JMenuItem;

public class WinAllMoney extends JDialog {
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			WinAllMoney dialog = new WinAllMoney();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public WinAllMoney() {
		setTitle("전체 회비내역");
		setBounds(100, 100, 579, 498);
		getContentPane().setLayout(new BorderLayout());
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		String columnNames[]= {"번호","이름","금액","날짜","비고"};
	    DefaultTableModel dtm = new DefaultTableModel(columnNames,0);
	      
	    
	    table = new JTable(dtm);
	    JPopupMenu popupMenu = new JPopupMenu();//팝업메뉴
	    table.addMouseListener(new MouseAdapter() {
	    	
	    	@Override 
	    	public void mouseReleased(MouseEvent e) { //테이블에 오른쪽 버튼 클릭
	    		 if(e.getButton() == 3) { // 오른쪽버튼
	    			 int row = table.rowAtPoint(e.getPoint()); //행열을 찾아내고
	    			 int col = table.columnAtPoint(e.getPoint());//행열을 찾아내고
	    			 if(!table.isRowSelected(row)) //선택안되었니되었니
	    				 table.changeSelection(row, col,false ,false ); //바꾸어라
	    				// mnUpdateMember.setEnabled(false); // 수정 맴버 비활성화
	    				 popupMenu.show(e.getComponent(),e.getX(), e.getY());
	    		 }
	    	}
	    });
		
		
		
	    JMenuItem mnInsertMember = new JMenuItem("회원정보추가");
	    mnInsertMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinInsertMember winInsertMember = 
						new WinInsertMember();
				winInsertMember.setModal(true);
				winInsertMember.setVisible(true);
				
			}
		});
		popupMenu.add(mnInsertMember);
		
		
		JMenuItem mnUpdateMember = new JMenuItem("회비금액수정");
		mnUpdateMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if(row != -1) {
					String sJumin = table.getValueAt(row, 1).toString();
					setVisible(false);
					WinCollectMoney winCollectMoney = new WinCollectMoney();
					winCollectMoney.setModal(true);
					winCollectMoney.setVisible(true);
					
				}
			}
		});
		popupMenu.add(mnUpdateMember);
		
		JMenuItem mnDeleteMember = new JMenuItem("회원정보삭제");
		mnDeleteMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if(row != -1) {
					String sJumin = table.getValueAt(row, 1).toString();
					setVisible(false);
					WinDeleteMember winDeleteMember = new WinDeleteMember(sJumin);
					winDeleteMember.setModal(true);
					winDeleteMember.setVisible(true);
				}
			}
		});
		popupMenu.add(mnDeleteMember);
		
		
		scrollPane.setViewportView(table);
		
		showResult();
	}

	protected void showResult() {
	      try {
	         Class.forName("com.mysql.cj.jdbc.Driver");
	         Connection con = 
	               DriverManager.getConnection(
	                     "jdbc:mysql://localhost:3306/sqlDB",
	                     "root",
	                     "12345");
	        Statement stmt = con.createStatement();         
	        String sql ="SELECT A.name, M.money, M.mDate, M.etc";  // 테이블 조인하는 방법
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