import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WinMain extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final JButton btnNewButton = new JButton("");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			WinMain dialog = new WinMain();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public WinMain() {
		setTitle("ICI 동창회");
		setBounds(100, 100, 283, 321);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 2, 5, 5));
		{
			JButton btnInsertMember = new JButton("");
			btnInsertMember.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					WinInsertMember winInsertMember = 
							new WinInsertMember();
					winInsertMember.setModal(true);
					winInsertMember.setVisible(true);
				}
			});
			btnInsertMember.setToolTipText("회원 가입");
			btnInsertMember.setIcon(new ImageIcon("images/addUser.png"));
			contentPanel.add(btnInsertMember);
		}
		{
			JButton btnUpdateMember = new JButton("");
			btnUpdateMember.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					WinSearch winSearch = new WinSearch();
					winSearch.setModal(true);
					winSearch.setVisible(true);
				}
			});
			btnUpdateMember.setToolTipText("회원정보 변경");
			btnUpdateMember.setIcon(new ImageIcon("images/update.png"));
			contentPanel.add(btnUpdateMember);
		}
		{
			JButton btnDeleteMember = new JButton("");
			btnDeleteMember.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					WinSearch winSearch = new WinSearch();
					winSearch.setModal(true);
					winSearch.setVisible(true);
				}
			});
			btnDeleteMember.setToolTipText("회원 탈퇴");
			btnDeleteMember.setIcon(new ImageIcon("images/delete.png"));
			contentPanel.add(btnDeleteMember);
		}
		{
			JButton btnSearchMember = new JButton("");
			btnSearchMember.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					WinSearchAll winSearchAll = 
							new WinSearchAll();
					winSearchAll.setModal(true);
					winSearchAll.setVisible(true);
				}
			});
			btnSearchMember.setToolTipText("회원 검색");
			btnSearchMember.setIcon(new ImageIcon("images/search.png"));
			contentPanel.add(btnSearchMember);
		}
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setIcon(new ImageIcon("images/money.png"));
		contentPanel.add(btnNewButton);
		{
			JButton btnNewButton_1 = new JButton("");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					WinShowMoneyAll winShowMoneyAll = 
							new WinShowMoneyAll();
					winShowMoneyAll.setModal(true);
					winShowMoneyAll.setVisible(true);
				}
			});
			btnNewButton_1.setIcon(new ImageIcon("images/saving.png"));
			contentPanel.add(btnNewButton_1);
		}
	}

}
