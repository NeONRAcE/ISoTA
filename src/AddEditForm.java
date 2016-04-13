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

import org.jdatepicker.*;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import org.jdatepicker.util.JDatePickerUtil;
import org.jdatepicker.impl.JDatePanelImpl;

import java.util.Properties;

public class AddEditForm extends JDialog
{
	private ClientModel client;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfFIO;
	private JButton okButton;
	private JButton cancelButton;
	private JDialog thisForm;
	private JTextField tfRegDate;
	private JTextField tfOkpo;
	private JTextField tfAdress;
	private JTextField tfUID;
	private JTextField tfNumber;
	private JTextField tfJurAdress;
	private JTextField tfDirFIO;
	private JTextField tfDirUID;
	private JTextField tfDirNumber;
	private JTextField tfSum;
	private JLabel label_8;
	private JLabel label_9;
	private JLabel label_10;
	private JDatePickerImpl datePicker;
	private JRadioButton rbtnFiz;
	private JRadioButton rbtnJur;
	private AddEditForm dialog;

	public static void main(String[] args)
	{
		try
		{
			AddEditForm dialog = new AddEditForm();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public boolean Validate()
	{
		boolean res = true;
		if (tfRegDate.getText().isEmpty()) return false;
		// if (textField_1.getText().isEmpty()) return false;
		// if (textField_2.getText().isEmpty()) return false;
		// if (textField_3.getText().isEmpty()) return false;
		// if (textField_4.getText().isEmpty()) return false;
		// if (rbtnJur.isSelected())
		// {
		// if (textField_5.getText().isEmpty()) return false;
		// if (textField_6.getText().isEmpty()) return false;
		// if (textField_7.getText().isEmpty()) return false;
		// if (textField_8.getText().isEmpty()) return false;
		// if (textField_9.getText().isEmpty()) return false;
		// }

		return res;
	}

	public void setModel(ClientModel cm)
	{
		this.client = cm;
		tfFIO.setText(cm.getFIO());
		if (cm.getDirectorAdress().equals(""))
		{
			rbtnFiz.setSelected(true);
			FizAct();
		}
		else
		{
			rbtnJur.setSelected(true);
			JurAct();
		}
		Date d = cm.getRegistrationDate();
		String sd = d.toString();
		String[] vals = sd.split("-");
		tfRegDate.setText(vals[2]+"/"+vals[1]+"/"+vals[0]);
		tfOkpo.setText(cm.getRevisionNum().toString());
		tfAdress.setText(cm.getAdress());
		tfUID.setText(cm.getUID().toString());
		tfNumber.setText(cm.getPhoneNumber().toString());
		tfAdress.setText(cm.getAdress());
		// TODO jur
	}

	private void FizAct()
	{
		okButton.setVisible(true);
		// thisForm.setBounds(100, 100, 450, 355);
		tfRegDate.setEnabled(true);
		tfOkpo.setEnabled(true);
		tfAdress.setEnabled(true);
		tfUID.setEnabled(true);
		tfNumber.setEnabled(true);
		tfJurAdress.setEnabled(false);
		tfDirFIO.setEnabled(false);
		tfDirUID.setEnabled(false);
		tfDirNumber.setEnabled(false);
		tfSum.setEnabled(false);
	}

	private void JurAct()
	{
		okButton.setVisible(true);
		tfRegDate.setEnabled(true);
		tfOkpo.setEnabled(true);
		tfAdress.setEnabled(true);
		tfUID.setEnabled(true);
		tfNumber.setEnabled(true);
		tfJurAdress.setEnabled(true);
		tfDirFIO.setEnabled(true);
		tfDirUID.setEnabled(true);
		tfDirNumber.setEnabled(true);
		tfSum.setEnabled(true);
	}

	public AddEditForm()
	{
		dialog = this;
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

		rbtnFiz = new JRadioButton(
				"\u0424\u0438\u0437\u0438\u0447\u0435\u0441\u043A\u043E\u0435");

		rbtnFiz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				FizAct();
			}

		});
		rbtnFiz.setBounds(167, 69, 107, 23);
		contentPanel.add(rbtnFiz);
		btnGroup.add(rbtnFiz);

		rbtnJur = new JRadioButton(
				"\u042E\u0440\u0438\u0434\u0438\u0447\u0435\u0441\u043A\u043E\u0435");
		rbtnJur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				JurAct();
			}
		});

		rbtnJur.setBounds(276, 69, 107, 23);
		contentPanel.add(rbtnJur);
		btnGroup.add(rbtnJur);

		JLabel label = new JLabel(
				"\u0414\u0430\u0442\u0430 \u0440\u0435\u0433\u0438\u0441\u0442\u0440\u0430\u0446\u0438\u0438:");
		label.setBounds(62, 128, 144, 14);
		contentPanel.add(label);

		tfRegDate = new JTextField();
		tfRegDate.setEnabled(false);
		tfRegDate.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0)
			{

			}
		});
		tfRegDate.addInputMethodListener(new InputMethodListener() {
			public void caretPositionChanged(InputMethodEvent arg0)
			{
			}

			public void inputMethodTextChanged(InputMethodEvent arg0)
			{
			}
		});

		tfRegDate.setBounds(216, 125, 86, 20);
		contentPanel.add(tfRegDate);
		tfRegDate.setColumns(10);

		JLabel label_2 = new JLabel("\u041E\u041A\u041F\u041E:");
		label_2.setBounds(62, 159, 46, 14);
		contentPanel.add(label_2);

		tfOkpo = new JTextField();
		tfOkpo.setEnabled(false);
		tfOkpo.setBounds(216, 156, 86, 20);
		contentPanel.add(tfOkpo);
		tfOkpo.setColumns(10);

		JLabel label_3 = new JLabel("\u0410\u0434\u0440\u0435\u0441:");
		label_3.setBounds(62, 190, 46, 14);
		contentPanel.add(label_3);

		tfAdress = new JTextField();
		tfAdress.setEnabled(false);
		tfAdress.setBounds(216, 187, 167, 20);
		contentPanel.add(tfAdress);
		tfAdress.setColumns(10);

		tfUID = new JTextField();
		tfUID.setEnabled(false);
		tfUID.setBounds(216, 218, 167, 20);
		contentPanel.add(tfUID);
		tfUID.setColumns(10);

		tfNumber = new JTextField();
		tfNumber.setEnabled(false);
		tfNumber.setBounds(216, 249, 125, 20);
		contentPanel.add(tfNumber);
		tfNumber.setColumns(10);

		tfJurAdress = new JTextField();
		tfJurAdress.setEnabled(false);
		tfJurAdress.setBounds(216, 280, 167, 20);
		contentPanel.add(tfJurAdress);
		tfJurAdress.setColumns(10);

		JLabel label_4 = new JLabel("\u0418\u041D\u041D:");
		label_4.setBounds(62, 221, 46, 14);
		contentPanel.add(label_4);

		JLabel label_5 = new JLabel(
				"\u0422\u0435\u043B\u0435\u0444\u043E\u043D:");
		label_5.setBounds(62, 252, 92, 14);
		contentPanel.add(label_5);

		JLabel label_6 = new JLabel(
				"\u042E\u0440\u0438\u0434\u0438\u0447\u0435\u0441\u043A\u0438\u0439 \u0430\u0434\u0440\u0435\u0441:");
		label_6.setBounds(62, 283, 144, 14);
		contentPanel.add(label_6);

		tfDirFIO = new JTextField();
		tfDirFIO.setEnabled(false);
		tfDirFIO.setBounds(216, 311, 167, 20);
		contentPanel.add(tfDirFIO);
		tfDirFIO.setColumns(10);

		tfDirUID = new JTextField();
		tfDirUID.setEnabled(false);
		tfDirUID.setBounds(216, 342, 86, 20);
		contentPanel.add(tfDirUID);
		tfDirUID.setColumns(10);

		tfDirNumber = new JTextField();
		tfDirNumber.setEnabled(false);
		tfDirNumber.setBounds(216, 373, 86, 20);
		contentPanel.add(tfDirNumber);
		tfDirNumber.setColumns(10);

		tfSum = new JTextField();
		tfSum.setEnabled(false);
		tfSum.setBounds(216, 404, 86, 20);
		contentPanel.add(tfSum);
		tfSum.setColumns(10);

		JLabel label_7 = new JLabel(
				"\u0424\u0418\u041E \u0434\u0438\u0440\u0435\u043A\u0442\u043E\u0440\u0430:");
		label_7.setBounds(62, 314, 144, 14);
		contentPanel.add(label_7);

		label_8 = new JLabel(
				"\u0418\u041D\u041D \u0434\u0438\u0440\u0435\u043A\u0442\u043E\u0440\u0430:");
		label_8.setBounds(62, 345, 144, 14);
		contentPanel.add(label_8);

		label_9 = new JLabel(
				"\u0422\u0435\u043B\u0435\u0444\u043E\u043D \u0434\u0438\u0440\u0435\u043A\u0442\u043E\u0440\u0430:");
		label_9.setBounds(62, 376, 144, 14);
		contentPanel.add(label_9);

		label_10 = new JLabel(
				"\u0421\u0443\u043C\u043C\u0430 \u043A\u0430\u043F\u0438\u0442\u0430\u043B\u0430:");
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
				okButton = new JButton(
						"\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0)
					{
						if (Validate())
						{
							try
							{
								Date d = TextParser.parseDate(
										tfRegDate.getText(), "Дата регистрации");
								Integer okpo = TextParser.parseInteger(
										tfOkpo.getText(), "ОКПО");
								Integer UID = TextParser.parseInteger(
										tfUID.getText(), "ИНН");
								Integer number = TextParser.parseInteger(
										tfNumber.getText(), "Номер");
								Integer dirUID = null;
								Integer dirNumber = null;
								Integer sum = null;
								if (rbtnJur.isSelected())
								{
									dirUID = TextParser.parseInteger(
											tfDirUID.getText(), "ИНН Директора");
									dirNumber = TextParser.parseInteger(
											tfDirNumber.getText(),
											"Телефон Директора");
									sum = TextParser.parseInteger(
											tfSum.getText(), "Сумма капитала");
								}

								ClientModel cm = null;
								if (client != null) cm = client;
								else cm = new ClientModel();
								cm.setFIO(tfFIO.getText().toString());
								cm.setRegistrationDate(d);
								cm.setRevisionNum(okpo);
								cm.setAdress(tfAdress.getText().toString());
								cm.setUID(UID);
								cm.setPhoneNumber(number);
								cm.setDirectorAdress(tfJurAdress.getText()
										.toString());
								if (rbtnJur.isSelected())
								{
									cm.setDirectorFIO(tfDirFIO.getText()
											.toString());
									cm.setDirectorUID(dirUID);
									cm.setDirectorNumber(dirNumber);
									cm.setCapitalSum(sum);
								}
								cm.save();
							}
							catch (Exception e)
							{
								e.printStackTrace();
							}

						}
						else
						{
							JOptionPane.showMessageDialog(null,
									"Не все поля заполнены.");
						}
						dialog.setVisible(false);
					}
					
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				okButton.setVisible(false);
				getRootPane().setDefaultButton(okButton);				
			}
			{
				cancelButton = new JButton(
						"\u041E\u0442\u043C\u0435\u043D\u0430");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0)
					{
						thisForm.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}