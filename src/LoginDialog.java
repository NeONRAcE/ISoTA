import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
		LogDiag = this;
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblAuthorization = new JLabel("Authorization");
		lblAuthorization.setBounds(160, 38, 87, 14);
		contentPanel.add(lblAuthorization);

		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(170, 63, 46, 14);
		contentPanel.add(lblLogin);

		tfLogin = new JTextField();
		tfLogin.setBounds(160, 88, 86, 20);
		contentPanel.add(tfLogin);
		tfLogin.setColumns(10);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(170, 130, 46, 14);
		contentPanel.add(lblPassword);

		pfPass = new JPasswordField();
		pfPass.setBounds(160, 156, 87, 20);
		contentPanel.add(pfPass);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT)); // TODO test commit
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addMouseListener(new OkButtonMouseListener());
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addMouseListener(new CancelButtonMouseListener());
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
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
			String user = tfLogin.getText();
			String pass = new String(pfPass.getPassword());
			
			if (getUserFromDB(user,pass)) loginresult = true;
			else loginresult = false;
			
			LogDiag.setVisible(false);
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
		//TODO get user from DB
		return true;
	}
}