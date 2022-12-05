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
		setBounds(100, 100, 283, 222);
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
			btnUpdateMember.setToolTipText("회원정보 변경");
			btnUpdateMember.setIcon(new ImageIcon("images/update.png"));
			contentPanel.add(btnUpdateMember);
		}
		{
			JButton btnDeleteMember = new JButton("");
			btnDeleteMember.setToolTipText("회원 탈퇴");
			btnDeleteMember.setIcon(new ImageIcon("images/delete.png"));
			contentPanel.add(btnDeleteMember);
		}
		{
			JButton btnSearchMember = new JButton("");
			btnSearchMember.setToolTipText("회원 검색");
			btnSearchMember.setIcon(new ImageIcon("images/search.png"));
			contentPanel.add(btnSearchMember);
		}
	}

}
