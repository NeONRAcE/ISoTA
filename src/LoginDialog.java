import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class LoginDialog extends JDialog
{

	private final JPanel contentPanel = new JPanel();
	private LoginDialog LogDiag;
	private JTextField tfLogin;
	private JPasswordField pfPass;
	private boolean loginresult;

	/** Launch the application. */
	public static void main(String[] args)
	{
		try
		{
			LoginDialog dialog = new LoginDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/** Create the dialog. */
	public LoginDialog()
	{
		setTitle("ISoTA: \u0410\u0432\u0442\u043E\u0440\u0438\u0437\u0430\u0446\u0438\u044F");
		LogDiag = this;
		loginresult = false;
		
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblAuthorization = new JLabel("\u0410\u0432\u0442\u043E\u0440\u0438\u0437\u0430\u0446\u0438\u044F");
		lblAuthorization.setHorizontalAlignment(SwingConstants.CENTER);
		lblAuthorization.setBounds(172, 38, 87, 14);
		contentPanel.add(lblAuthorization);

		JLabel lblLogin = new JLabel("\u041B\u043E\u0433\u0438\u043D");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setBounds(172, 83, 87, 14);
		contentPanel.add(lblLogin);

		tfLogin = new JTextField();
		tfLogin.setBounds(172, 108, 86, 20);
		contentPanel.add(tfLogin);
		tfLogin.setColumns(10);

		JLabel lblPassword = new JLabel("\u041F\u0430\u0440\u043E\u043B\u044C");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setBounds(172, 139, 87, 14);
		contentPanel.add(lblPassword);

		pfPass = new JPasswordField();
		pfPass.setBounds(172, 164, 87, 20);
		contentPanel.add(pfPass);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("\u0412\u043E\u0439\u0442\u0438");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
					}
				});
				okButton.addMouseListener(new OkButtonMouseListener());
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("\u0412\u044B\u0445\u043E\u0434");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.addMouseListener(new CancelButtonMouseListener());
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		LogDiag.setVisible(false);
	}
	
	public boolean getLoginResult()
	{
		return this.loginresult;
	}
	
	private class OkButtonMouseListener extends MouseAdapter
	{
		@Override
		public void mouseClicked(MouseEvent arg0)
		{
			loginresult = false;
			String user = tfLogin.getText();
			String pass = new String(pfPass.getPassword());
			
			if (getUserFromDB(user,pass)) loginresult = true;
			else loginresult = false;
			
			if (loginresult) LogDiag.setVisible(false);
		}
	}
	
	private class CancelButtonMouseListener extends MouseAdapter
	{
		@Override
		public void mouseClicked(MouseEvent arg0)
		{
			loginresult = false;
			LogDiag.setVisible(false);
		}
	}
	
	private boolean getUserFromDB(String user,String pass)
	{
		boolean res = false;
		User.setCurrentUser(new UserModel());
		UserModel um = UserModel.findUser(user, pass);
		if (um.getID()==0)
		{
			JOptionPane.showMessageDialog(null, "Пользователь не найден.");
			return res;
		}
		if (um.getLogin()!=user)
		{
			JOptionPane.showMessageDialog(null, "Неверный логин.");
			return res;
		}
		if (um.getLogin()==user && um.getPassword()!=pass)
		{
			JOptionPane.showMessageDialog(null, "Неверный пароль.");
			return res;
		}
		User.setCurrentUser(um);
		if (um.getID()!=0) res=true;
		return res;
	}
}