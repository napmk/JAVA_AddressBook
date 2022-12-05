import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class WinInsertMember extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfName;
	private JTextField tfJumin1;
	private JPasswordField tfJumin2;
	private JTextField tfTel;
	private JTextField tfAddress;
	private JTextField tfCompany;
	private JComboBox cbGYear;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			WinInsertMember dialog = new WinInsertMember();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public WinInsertMember() {
		setTitle("동문회원가입");
		setBounds(100, 100, 463, 381);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblname = new JLabel("\uC774\uB984");
		lblname.setBounds(26, 38, 57, 15);
		contentPanel.add(lblname);
		
		tfName = new JTextField();
		tfName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					tfJumin1.requestFocus();
				}
			}
		});
		tfName.setBounds(95, 35, 170, 21);
		contentPanel.add(tfName);
		tfName.setColumns(10);
		
		JLabel lblname_1 = new JLabel("\uC8FC\uBBFC\uBC88\uD638");
		lblname_1.setBounds(26, 78, 57, 15);
		contentPanel.add(lblname_1);
		
		tfJumin1 = new JTextField();
		tfJumin1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					tfJumin2.requestFocus();
				}
			}
		});
		tfJumin1.setColumns(10);
		tfJumin1.setBounds(95, 75, 73, 21);
		contentPanel.add(tfJumin1);
		
		tfJumin2 = new JPasswordField();
		tfJumin2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					tfTel.requestFocus();
				}
				
			}
		});
		tfJumin2.setBounds(192, 75, 73, 21);
		contentPanel.add(tfJumin2);
		
		JLabel lblTel = new JLabel("전화번호");
		lblTel.setBounds(26, 119, 57, 15);
		contentPanel.add(lblTel);
		
		tfTel = new JTextField();
		tfTel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					String mobileNumber = tfTel.getText();
					if(tfTel.getText().length()==8) {
						tfTel.setText("010" + mobileNumber);
						tfCompany.requestFocus();
					}else {
						tfTel.requestFocus();
						tfTel.setSelectionStart(0);
						tfTel.setSelectionEnd(mobileNumber.length());
				  }
			   }
			}
		});
		tfTel.setColumns(10);
		tfTel.setBounds(95, 116, 170, 21);
		contentPanel.add(tfTel);
		
		JLabel lblNewLabel = new JLabel("-");
		lblNewLabel.setBounds(176, 78, 14, 15);
		contentPanel.add(lblNewLabel);
		
		JLabel lblAddress = new JLabel("주소");
		lblAddress.setBounds(26, 205, 57, 15);
		contentPanel.add(lblAddress);
		
		tfAddress = new JTextField();
		tfAddress.setColumns(10);
		tfAddress.setBounds(95, 202, 258, 21);
		contentPanel.add(tfAddress);
		
		JButton btnNewButton = new JButton("도로명주소 검색");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinDoroSearch winDoroSearch = new WinDoroSearch();
				winDoroSearch.setModal(true);
				winDoroSearch.setVisible(true);
				String sAddr = winDoroSearch.getAddress();
				tfAddress.setText(sAddr);
				tfAddress.requestFocus();
			}
		});
		btnNewButton.setBounds(355, 201, 80, 23);
		contentPanel.add(btnNewButton);
		
		JLabel lblPic = new JLabel("");
		lblPic.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2) { //마우스더블클릭시 
					JFileChooser chooser = new JFileChooser();
	      			FileNameExtensionFilter filter = new FileNameExtensionFilter("이미지파일", "jpg", "png", "gif");
	      			chooser.setFileFilter(filter);
	      			chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	      			int ret = chooser.showOpenDialog(null);
	      			if(ret == JFileChooser.APPROVE_OPTION) {
	      				String picPath = chooser.getSelectedFile().getPath();
	      				picPath = picPath.replaceAll("\\\\", "/"); // sql에서 // 부분 가지고 오기
	      				ImageIcon image = new ImageIcon(picPath);
	      				Image picImage = image.getImage();
	      				picImage = picImage.getScaledInstance(100, 120, Image.SCALE_SMOOTH);
	      				ImageIcon image2 = new ImageIcon(picImage);
	      				lblPic.setIcon(image2);
	      				System.out.println(picPath);
	      				
	      			
	      			}
				}
			}
		});
		lblPic.setToolTipText("\uB354\uBE14\uD074\uB9AD\uD558\uC5EC \uC0AC\uC9C4 \uC120\uD0DD\uD558\uC138\uC694");
		lblPic.setBackground(new Color(255, 255, 0));
		lblPic.setBounds(313, 22, 100, 120);
		lblPic.setOpaque(true);
		contentPanel.add(lblPic);
		
		JLabel lblCompany = new JLabel("회사");
		lblCompany.setBounds(26, 161, 57, 15);
		contentPanel.add(lblCompany);
		
		tfCompany = new JTextField();
		tfCompany.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					cbGYear.requestFocus();
				}
			}
		});
		tfCompany.setColumns(10);
		tfCompany.setBounds(95, 158, 116, 21);
		contentPanel.add(tfCompany);
		
		JLabel lblGYear = new JLabel("졸업년도");
		lblGYear.setBounds(244, 161, 57, 15);
		contentPanel.add(lblGYear);
		
		cbGYear = new JComboBox();
		cbGYear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tfAddress.requestFocus();
				
			}
		});
		cbGYear.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					tfAddress.requestFocus();
				}
			}
		});
		cbGYear.setBounds(313, 157, 98, 23);
		contentPanel.add(cbGYear);
		
		Calendar calendar = Calendar.getInstance();
		int curYear = calendar.get(Calendar.YEAR); // 달력에서 올해 값을 curYear에 넣기
		for(int year = curYear-100; year<=curYear; year++) //curYear에 100을 빼주기
			cbGYear.addItem(year);
		cbGYear.setSelectedItem(curYear); //올해 자동선택되게
		
		JButton btnInsert = new JButton("회원가입");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InsertMember();
			}

			
			
			private void InsertMember() {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = 
							DriverManager.getConnection(
									"jdbc:mysql://localhost:3306/sqlDB",
									"root",
									"12345");
					Statement stmt = con.createStatement();
					String sql = "insert into addresstbl(name, jumin, mobile, company, gYear, address, pic ) values('";
					sql = sql + tfName.getText() + "','";
				 	sql = sql + tfJumin1.getText() + tfJumin2.getPassword() + "','";
				 	sql = sql + tfTel.getText() + "','";
				 	sql = sql + tfCompany.getText() + "','";
				 	sql = sql + cbGYear.getSelectedItem() + "','";
				 	sql = sql + tfAddress.getText() + "','";
				 	sql = sql + lblPic.getIcon() + "')";
//					
					//System.out.println(sql);
					if(stmt.executeUpdate(sql) > 0)
						JOptionPane.showMessageDialog(null, "정상 입력 완료");
					else
						JOptionPane.showMessageDialog(null, "오류입니다");
					
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnInsert.setBounds(95, 275, 124, 35);
		contentPanel.add(btnInsert);
		
		JButton btnCancle = new JButton("취소");
		btnCancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancle.setBounds(222, 275, 124, 35);
		contentPanel.add(btnCancle);
	}
}
