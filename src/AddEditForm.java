import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddEditForm {

	private JFrame frmIsota;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddEditForm window = new AddEditForm();
					window.frmIsota.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AddEditForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmIsota = new JFrame();
		frmIsota.setResizable(false);
		frmIsota.setTitle("ISoTA");
		frmIsota.setBounds(100, 100, 420, 165);
		frmIsota.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmIsota.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C \u043A\u043B\u0438\u0435\u043D\u0442\u0430");
		label.setBounds(148, 24, 115, 14);
		frmIsota.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u0424\u0418\u041E:");
		label_1.setBounds(43, 52, 32, 14);
		frmIsota.getContentPane().add(label_1);
		
		textField = new JTextField();
		textField.setBounds(86, 49, 267, 20);
		frmIsota.getContentPane().add(textField);
		textField.setColumns(10);
		
		JRadioButton radioButton = new JRadioButton("\u0424\u0438\u0437\u0438\u0447\u0435\u0441\u043A\u043E\u0435 \u043B\u0438\u0446\u043E");
		radioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmIsota.setBounds(100, 100, 420, 800);
				frmIsota.setResizable(false);				
			}
		});
		radioButton.setBounds(64, 87, 142, 23);
		frmIsota.getContentPane().add(radioButton);
		
		JRadioButton radioButton_1 = new JRadioButton("\u042E\u0440\u0438\u0434\u0438\u0447\u0435\u0441\u043A\u043E\u0435 \u043B\u0438\u0446\u043E");
		radioButton_1.setBounds(208, 87, 145, 23);
		frmIsota.getContentPane().add(radioButton_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(74, 281, 86, 20);
		frmIsota.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		
	}
}
