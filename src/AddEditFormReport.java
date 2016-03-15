import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class AddEditFormReport extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JDialog thisForm;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddEditFormReport dialog = new AddEditFormReport();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddEditFormReport() {
		setTitle("\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C \u043E\u0442\u0447\u0451\u0442");
		setBounds(100, 100, 450, 316);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel label = new JLabel("\u0414\u0430\u0442\u0430 \u043E\u043F\u0435\u0440\u0430\u0446\u0438\u0438:");
		label.setBounds(98, 41, 144, 14);
		contentPanel.add(label);
		
		textField = new JTextField();
		textField.setBounds(252, 38, 86, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(252, 69, 86, 20);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(252, 100, 86, 20);
		contentPanel.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(252, 131, 86, 20);
		contentPanel.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(252, 162, 86, 20);
		contentPanel.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(252, 193, 86, 20);
		contentPanel.add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("\u041A\u043E\u0434 \u043E\u043F\u0435\u0440\u0430\u0446\u0438\u0438:");
		lblNewLabel.setBounds(98, 72, 144, 14);
		contentPanel.add(lblNewLabel);
		
		JLabel label_1 = new JLabel("\u041F\u0435\u0440\u0435\u043F\u043B\u0430\u0442\u0430:");
		label_1.setBounds(98, 103, 144, 14);
		contentPanel.add(label_1);
		
		JLabel label_2 = new JLabel("\u041E\u043F\u043B\u0430\u0447\u0435\u043D\u043E:");
		label_2.setBounds(98, 134, 144, 14);
		contentPanel.add(label_2);
		
		JLabel label_3 = new JLabel("\u0412\u043E\u0437\u0432\u0440\u0430\u0449\u0435\u043D\u043E:");
		label_3.setBounds(98, 165, 144, 14);
		contentPanel.add(label_3);
		
		JLabel label_4 = new JLabel("\u0421\u0443\u043C\u043C\u0430:");
		label_4.setBounds(98, 196, 144, 14);
		contentPanel.add(label_4);
		thisForm = this;
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
