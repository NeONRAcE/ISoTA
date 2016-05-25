import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import javax.swing.ComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

import com.sun.org.apache.bcel.internal.generic.LUSHR;

public class AddEditFormReport extends JDialog
{

	private final JPanel contentPanel = new JPanel();
	private JDialog thisForm;
	private JCustomTextField tfOperDate;
	private JTextField tfOperCode;
	private JTextField tfOverpayment;
	private JTextField tfPaid;
	private JTextField tfReturned;
	private JTextField tfSum;
	private ReportModel report;
	private JTextField tfClient;
	private ClientModel curClient;

	/** Launch the application. */
	public static void main(String[] args)
	{
		try
		{
			AddEditFormReport dialog = new AddEditFormReport();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public boolean validateReports()
	{
		boolean res = true;
		if (tfOperCode.getText().isEmpty()) return false;
		return res;
	}

	/** Create the dialog. */
	public AddEditFormReport()
	{
		setTitle("\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C \u043E\u0442\u0447\u0451\u0442");
		setBounds(100, 100, 358, 326);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel label = new JLabel(
				"\u0414\u0430\u0442\u0430 \u043E\u043F\u0435\u0440\u0430\u0446\u0438\u0438:");
		label.setHorizontalAlignment(SwingConstants.TRAILING);
		label.setBounds(10, 29, 103, 14);
		contentPanel.add(label);

		tfOperDate = new JCustomTextField();
		tfOperDate.setPlaceholder("24/03/2014");
		tfOperDate.setBounds(123, 29, 107, 20);
		contentPanel.add(tfOperDate);
		tfOperDate.setColumns(10);

		tfOperCode = new JTextField();
		tfOperCode.setBounds(123, 60, 107, 20);
		contentPanel.add(tfOperCode);
		tfOperCode.setColumns(10);

		tfOverpayment = new JTextField();
		tfOverpayment.setBounds(123, 91, 107, 20);
		contentPanel.add(tfOverpayment);
		tfOverpayment.setColumns(10);

		tfPaid = new JTextField();
		tfPaid.setBounds(123, 122, 107, 20);
		contentPanel.add(tfPaid);
		tfPaid.setColumns(10);

		tfReturned = new JTextField();
		tfReturned.setBounds(123, 153, 107, 20);
		contentPanel.add(tfReturned);
		tfReturned.setColumns(10);

		tfSum = new JTextField();
		tfSum.setBounds(123, 184, 107, 20);
		contentPanel.add(tfSum);
		tfSum.setColumns(10);

		JLabel lblNewLabel = new JLabel(
				"\u041A\u043E\u0434 \u043E\u043F\u0435\u0440\u0430\u0446\u0438\u0438:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setBounds(10, 60, 103, 14);
		contentPanel.add(lblNewLabel);

		JLabel label_1 = new JLabel(
				"\u041F\u0435\u0440\u0435\u043F\u043B\u0430\u0442\u0430:");
		label_1.setHorizontalAlignment(SwingConstants.TRAILING);
		label_1.setBounds(10, 91, 103, 14);
		contentPanel.add(label_1);

		JLabel label_2 = new JLabel(
				"\u041E\u043F\u043B\u0430\u0447\u0435\u043D\u043E:");
		label_2.setHorizontalAlignment(SwingConstants.TRAILING);
		label_2.setBounds(10, 122, 103, 14);
		contentPanel.add(label_2);

		JLabel label_3 = new JLabel(
				"\u0412\u043E\u0437\u0432\u0440\u0430\u0449\u0435\u043D\u043E:");
		label_3.setHorizontalAlignment(SwingConstants.TRAILING);
		label_3.setBounds(10, 153, 103, 14);
		contentPanel.add(label_3);

		JLabel label_4 = new JLabel("\u0421\u0443\u043C\u043C\u0430:");
		label_4.setHorizontalAlignment(SwingConstants.TRAILING);
		label_4.setBounds(10, 184, 103, 14);
		contentPanel.add(label_4);
		
		JLabel label_5 = new JLabel("\u0424\u0418\u041E \u043A\u043B\u0438\u0435\u043D\u0442\u0430:");
		label_5.setHorizontalAlignment(SwingConstants.TRAILING);
		label_5.setBounds(10, 218, 103, 14);
		contentPanel.add(label_5);
		
		tfClient = new JTextField();
		tfClient.setEditable(false);
		tfClient.setBounds(123, 215, 70, 20);
		contentPanel.add(tfClient);
		tfClient.setColumns(10);
		
		JButton btnOpenClientDialog = new JButton("...");
		btnOpenClientDialog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SelectClientForm scf = new SelectClientForm();
				scf.setAlwaysOnTop(true);
				scf.setModal(true);
				scf.setVisible(true);
				ClientModel cm = scf.getClientModel();
				if (scf != null)
				{
					curClient = cm;
					tfClient.setText(curClient.toString());
				}
			}
		});
		btnOpenClientDialog.setBounds(203, 214, 27, 23);
		contentPanel.add(btnOpenClientDialog);
		
		thisForm = this;
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0)
					{
						if (validateReports())
						{
							try
							{
								Date d = TextParser.parseDate(
										tfOperDate.getText(), "Дата операции");
								Float overpayment = TextParser.parseFloat(
										tfOverpayment.getText(), "Переплата");
								Float paid = TextParser.parseFloat(
										tfPaid.getText(), "Оплачено");
								Float returned = TextParser.parseFloat(
										tfReturned.getText(), "Возвращено");
								Float sum = TextParser.parseFloat(
										tfSum.getText(), "Сумма");

								if (report == null) report = new ReportModel();
								report.setOperationDate(d);
								report.setKod(tfOperCode.getText().toString());
								report.setOverpayment(overpayment);
								report.setPaid(paid);
								report.setReturned(returned);
								report.setSum(sum);
								report.setClientID(curClient.getID());
								report.save();
								thisForm.dispose();
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
						//thisForm.setVisible(false);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("\u041E\u0442\u043C\u0435\u043D\u0430");
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
	
	void setModel(ReportModel rm)
	{
		this.report = rm;
		Date d = report.getOperationDate();
		String[] sdv = d.toString().split("-");
		tfOperDate.setText(sdv[2]+"/"+sdv[1]+"/"+sdv[0]);
		tfOperCode.setText(report.getKod());
		tfOverpayment.setText(report.getOverpayment().toString());
		tfPaid.setText(report.getPaid().toString());
		tfReturned.setText(report.getReturned().toString());
		tfSum.setText(report.getSum().toString());
		curClient = ClientModel.findClient(report.getClientID());
		tfClient.setText(curClient.toString());
	}
}
