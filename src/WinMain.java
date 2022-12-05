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
		setTitle("ICI \uC8FC\uC18C\uB85D");
		setBounds(100, 100, 419, 424);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 2, 5, 5));
		{
			JButton btnInsertMember = new JButton("");
			btnInsertMember.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					WinInsertMember winInsertMember = new WinInsertMember();
					winInsertMember.setModal(true);
					winInsertMember.setVisible(true);
				}
			});
			btnInsertMember.setToolTipText("회원가입");
			btnInsertMember.setIcon(new ImageIcon("C:\\javawork\\AddressBook\\imgaes\\addUser.png"));
			contentPanel.add(btnInsertMember);
		}
		{
			JButton btnUpdateMember = new JButton("");
			btnUpdateMember.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnUpdateMember.setToolTipText("\uC5C5\uB370\uC774\uD2B8");
			btnUpdateMember.setIcon(new ImageIcon("C:\\javawork\\AddressBook\\imgaes\\showUser.png"));
			contentPanel.add(btnUpdateMember);
		}
		{
			JButton btnDeleteMember = new JButton("");
			btnDeleteMember.setToolTipText("\uD68C\uC6D0\uC0AD\uC81C");
			btnDeleteMember.setIcon(new ImageIcon("C:\\javawork\\AddressBook\\imgaes\\delUser.png"));
			contentPanel.add(btnDeleteMember);
		}
		{
			JButton btnSearchMember = new JButton("");
			btnSearchMember.setToolTipText("\uAC80\uC0C9\uD558\uAE30");
			btnSearchMember.setIcon(new ImageIcon("C:\\javawork\\AddressBook\\imgaes\\search.png"));
			contentPanel.add(btnSearchMember);
		}
	}

}
