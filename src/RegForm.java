import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.awt.event.ActionEvent;

public class RegForm extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegForm dialog = new RegForm();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean compare()
	{
		boolean res = true;
		if (textField.getText().isEmpty()) return false;
		if (textField_2.getText().isEmpty()) return false;
		if (textField_3.getText().isEmpty()) return false;
		if (passwordField.getPassword().equals("")) return false;
		return res;
	}

	/**
	 * Create the dialog.
	 */
	
	
	
	public RegForm() {
		setTitle("\u0420\u0435\u0433\u0438\u0441\u0442\u0440\u0430\u0446\u0438\u044F");
		setBounds(100, 100, 422, 290);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel label = new JLabel("\u041B\u043E\u0433\u0438\u043D:");
		label.setBounds(70, 40, 61, 14);
		contentPanel.add(label);
		
		textField = new JTextField();
		textField.setBounds(141, 37, 190, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(141, 99, 190, 20);
		contentPanel.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(141, 130, 190, 20);
		contentPanel.add(textField_3);
		textField_3.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\u041D\u0430\u0447. \u043E\u0442\u0434\u0435\u043B\u0430 \u0417\u041E\u043F\u043B", "\u041D\u0430\u0447. \u043E\u0442\u0434\u0435\u043B\u0430 \u0410\u043D\u0423\u0447", "\u0421\u043E\u0442\u0440. \u043E\u0442\u0434\u0435\u043B\u0430 \u0417\u041E\u043F\u043B", "\u0421\u043E\u0442\u0440. \u043E\u0442\u0434\u0435\u043B\u0430 \u0410\u043D\u0423\u0447", "\u0411\u043E\u0433"}));
		comboBox.setBounds(141, 161, 190, 20);
		contentPanel.add(comboBox);
		
		JLabel label_1 = new JLabel("\u041F\u0430\u0440\u043E\u043B\u044C:");
		label_1.setBounds(70, 71, 73, 14);
		contentPanel.add(label_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(141, 68, 190, 20);
		contentPanel.add(passwordField);
		
		JLabel label_2 = new JLabel("\u0418\u043C\u044F:");
		label_2.setBounds(70, 102, 61, 14);
		contentPanel.add(label_2);
		
		JLabel label_3 = new JLabel("\u0424\u0430\u043C\u0438\u043B\u0438\u044F:");
		label_3.setBounds(70, 133, 110, 14);
		contentPanel.add(label_3);
		
		JLabel label_4 = new JLabel("\u041F\u0440\u0430\u0432\u0430:");
		label_4.setBounds(70, 164, 46, 14);
		contentPanel.add(label_4);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (compare())
						{
							try
							{
								UserModel um = new UserModel();
								um.setLogin(textField.getText().toString());
								um.setName(textField_2.getText().toString());
								um.setLastName(textField_3.getText().toString());
								um.setPassword(passwordField.getPassword().toString());
								um.setSecurityClass((byte)(comboBox.getSelectedIndex()+1));
								um.save();
							}
							catch (Exception e)
							{
								JOptionPane.showMessageDialog(null, e.getMessage());
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Не все поля заполнены.");
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("\u041E\u0442\u043C\u0435\u043D\u0430");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}	
}
