import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JFormattedTextField;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class AddEditForm extends JDialog {

	
	private final JPanel contentPanel = new JPanel();
	private JTextField tfFIO;
	private JButton okButton;
	private JButton cancelButton;
	private JDialog thisForm;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JLabel label_8;
	private JLabel label_9;
	private JLabel label_10;

	public static void main(String[] args) {
		try {
			AddEditForm dialog = new AddEditForm();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	public boolean Validate()
//	{
//		boolean res = true;
//		if(textField.getText().isEmpty()) return false;
//		if(textField_1.getText().isEmpty()) return false;
//		if(textField_2.getText().isEmpty()) return false;
//		if(textField_3.getText().isEmpty()) return false;
//		if(textField_4.getText().isEmpty()) return false;
//		if(textField_5.getText().isEmpty()) return false;
//		if(textField_6.getText().isEmpty()) return false;
//		if(textField_7.getText().isEmpty()) return false;
//		if(textField_8.getText().isEmpty()) return false;
//		if(textField_9.getText().isEmpty()) return false;
//		return res;
//	}

	public AddEditForm() {
		setTitle("\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C \u043A\u043B\u0438\u0435\u043D\u0442\u0430");
		setModal(true);
		setBounds(100, 100, 450, 537);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		thisForm = this;

		JLabel label_1 = new JLabel("\u0424\u0418\u041E:");
		label_1.setBounds(62, 30, 46, 14);
		contentPanel.add(label_1);

		tfFIO = new JTextField();
		tfFIO.setBounds(112, 27, 271, 20);
		contentPanel.add(tfFIO);
		tfFIO.setColumns(10);

		ButtonGroup btnGroup = new ButtonGroup();

		JRadioButton rbtnFiz = new JRadioButton(
				"\u0424\u0438\u0437\u0438\u0447\u0435\u0441\u043A\u043E\u0435");

		rbtnFiz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				okButton.setVisible(true);
				//thisForm.setBounds(100, 100, 450, 355);
				textField.setEnabled(true);
				textField_1.setEnabled(true);
				textField_2.setEnabled(true);
				textField_3.setEnabled(true);
				textField_4.setEnabled(true);
				textField_5.setEnabled(false);
				textField_6.setEnabled(false);
				textField_7.setEnabled(false);
				textField_8.setEnabled(false);
				textField_9.setEnabled(false);
			}
			
		
		});
		rbtnFiz.setBounds(167, 69, 107, 23);
		contentPanel.add(rbtnFiz);
		btnGroup.add(rbtnFiz);

		JRadioButton rbtnJur = new JRadioButton(
				"\u042E\u0440\u0438\u0434\u0438\u0447\u0435\u0441\u043A\u043E\u0435");
		rbtnJur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				okButton.setVisible(true);
				textField.setEnabled(true);
				textField_1.setEnabled(true);
				textField_2.setEnabled(true);
				textField_3.setEnabled(true);
				textField_4.setEnabled(true);
				textField_5.setEnabled(true);
				textField_6.setEnabled(true);
				textField_7.setEnabled(true);
				textField_8.setEnabled(true);
				textField_9.setEnabled(true);
			}
		});
			
		rbtnJur.setBounds(276, 69, 107, 23);
		contentPanel.add(rbtnJur);
		btnGroup.add(rbtnJur);
		
		JLabel label = new JLabel("\u0414\u0430\u0442\u0430 \u0440\u0435\u0433\u0438\u0441\u0442\u0440\u0430\u0446\u0438\u0438:");
		label.setBounds(62, 128, 144, 14);
		contentPanel.add(label);
		
		textField = new JTextField();
		textField.setEnabled(false);
		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
			//	Validate();
			}
		});
		textField.addInputMethodListener(new InputMethodListener() {
			public void caretPositionChanged(InputMethodEvent arg0) {
			}
			public void inputMethodTextChanged(InputMethodEvent arg0) {
			}
		});
	
		textField.setBounds(216, 125, 86, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JLabel label_2 = new JLabel("\u041E\u041A\u041F\u041E:");
		label_2.setBounds(62, 159, 46, 14);
		contentPanel.add(label_2);
		
		textField_1 = new JTextField();
		textField_1.setEnabled(false);
		textField_1.setBounds(216, 156, 86, 20);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel label_3 = new JLabel("\u0410\u0434\u0440\u0435\u0441:");
		label_3.setBounds(62, 190, 46, 14);
		contentPanel.add(label_3);
		
		textField_2 = new JTextField();
		textField_2.setEnabled(false);
		textField_2.setBounds(216, 187, 167, 20);
		contentPanel.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setEnabled(false);
		textField_3.setBounds(216, 218, 167, 20);
		contentPanel.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setEnabled(false);
		textField_4.setBounds(216, 249, 125, 20);
		contentPanel.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setEnabled(false);
		textField_5.setBounds(216, 280, 167, 20);
		contentPanel.add(textField_5);
		textField_5.setColumns(10);
		
		JLabel label_4 = new JLabel("\u0418\u041D\u041D:");
		label_4.setBounds(62, 221, 46, 14);
		contentPanel.add(label_4);
		
		JLabel label_5 = new JLabel("\u0422\u0435\u043B\u0435\u0444\u043E\u043D:");
		label_5.setBounds(62, 252, 92, 14);
		contentPanel.add(label_5);
		
		JLabel label_6 = new JLabel("\u042E\u0440\u0438\u0434\u0438\u0447\u0435\u0441\u043A\u0438\u0439 \u0430\u0434\u0440\u0435\u0441:");
		label_6.setBounds(62, 283, 144, 14);
		contentPanel.add(label_6);
		
		textField_6 = new JTextField();
		textField_6.setEnabled(false);
		textField_6.setBounds(216, 311, 167, 20);
		contentPanel.add(textField_6);
		textField_6.setColumns(10);
		
		textField_7 = new JTextField();
		textField_7.setEnabled(false);
		textField_7.setBounds(216, 342, 86, 20);
		contentPanel.add(textField_7);
		textField_7.setColumns(10);
		
		textField_8 = new JTextField();
		textField_8.setEnabled(false);
		textField_8.setBounds(216, 373, 86, 20);
		contentPanel.add(textField_8);
		textField_8.setColumns(10);
		
		textField_9 = new JTextField();
		textField_9.setEnabled(false);
		textField_9.setBounds(216, 404, 86, 20);
		contentPanel.add(textField_9);
		textField_9.setColumns(10);
				
		JLabel label_7 = new JLabel("\u0424\u0418\u041E \u0434\u0438\u0440\u0435\u043A\u0442\u043E\u0440\u0430:");
		label_7.setBounds(62, 314, 144, 14);
		contentPanel.add(label_7);
		
		label_8 = new JLabel("\u0418\u041D\u041D \u0434\u0438\u0440\u0435\u043A\u0442\u043E\u0440\u0430:");
		label_8.setBounds(62, 345, 144, 14);
		contentPanel.add(label_8);
		
		label_9 = new JLabel("\u0422\u0435\u043B\u0435\u0444\u043E\u043D \u0434\u0438\u0440\u0435\u043A\u0442\u043E\u0440\u0430:");
		label_9.setBounds(62, 376, 144, 14);
		contentPanel.add(label_9);
		
		label_10 = new JLabel("\u0421\u0443\u043C\u043C\u0430 \u043A\u0430\u043F\u0438\u0442\u0430\u043B\u0430:");
		label_10.setBounds(62, 407, 144, 14);
		contentPanel.add(label_10);
		
		JLabel label_11 = new JLabel("\u041B\u0438\u0446\u043E:");
		label_11.setBounds(62, 73, 61, 14);
		contentPanel.add(label_11);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(rbtnFiz.isSelected())
						{
							if(tfFIO.getText().length()==0 || textField.getText().length()==0 || textField_1.getText().length()==0 ||
									textField_2.getText().length()==0 || textField_3.getText().length()==0 ||
									textField_4.getText().length()==0) JOptionPane.showMessageDialog(null, "Вы заполнили не все поля.");
						}
						
						if(rbtnJur.isSelected())
						{
							if(tfFIO.getText().length()==0 || textField.getText().length()==0 || textField_1.getText().length()==0 || 
									textField_2.getText().length()==0 || textField_3.getText().length()==0 ||
									textField_4.getText().length()==0 || textField_5.getText().length()==0 ||
									textField_6.getText().length()==0 || textField_7.getText().length()==0 ||
									textField_8.getText().length()==0 || textField_9.getText().length()==0) 
								JOptionPane.showMessageDialog(null, "Вы заполнили не все поля.");
						}
						
						ClientModel rm = new ClientModel();
						
						//rm.setRegistrationDate(textField.getText().toString());
						rm.setRevisionNum(Integer.parseInt(textField_1.getText().toString()));
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				okButton.setVisible(false);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("\u041E\u0442\u043C\u0435\u043D\u0430");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						thisForm.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);

			}
		}
	}
}