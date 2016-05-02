import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class AddEditFormReport extends JDialog
{

	private final JPanel contentPanel = new JPanel();
	private JDialog thisForm;
	private JTextField tfOperDate;
	private JTextField tfOperCode;
	private JTextField tfOverpayment;
	private JTextField tfPaid;
	private JTextField tfReturned;
	private JTextField tfSum;
	private ReportModel report;

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
		setBounds(100, 100, 374, 316);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel label = new JLabel(
				"\u0414\u0430\u0442\u0430 \u043E\u043F\u0435\u0440\u0430\u0446\u0438\u0438:");
		label.setBounds(74, 41, 144, 14);
		contentPanel.add(label);

		tfOperDate = new JTextField();
		tfOperDate.setBounds(192, 41, 86, 20);
		contentPanel.add(tfOperDate);
		tfOperDate.setColumns(10);

		tfOperCode = new JTextField();
		tfOperCode.setBounds(192, 72, 86, 20);
		contentPanel.add(tfOperCode);
		tfOperCode.setColumns(10);

		tfOverpayment = new JTextField();
		tfOverpayment.setBounds(192, 103, 86, 20);
		contentPanel.add(tfOverpayment);
		tfOverpayment.setColumns(10);

		tfPaid = new JTextField();
		tfPaid.setBounds(192, 134, 86, 20);
		contentPanel.add(tfPaid);
		tfPaid.setColumns(10);

		tfReturned = new JTextField();
		tfReturned.setBounds(192, 165, 86, 20);
		contentPanel.add(tfReturned);
		tfReturned.setColumns(10);

		tfSum = new JTextField();
		tfSum.setBounds(192, 196, 86, 20);
		contentPanel.add(tfSum);
		tfSum.setColumns(10);

		JLabel lblNewLabel = new JLabel(
				"\u041A\u043E\u0434 \u043E\u043F\u0435\u0440\u0430\u0446\u0438\u0438:");
		lblNewLabel.setBounds(74, 72, 144, 14);
		contentPanel.add(lblNewLabel);

		JLabel label_1 = new JLabel(
				"\u041F\u0435\u0440\u0435\u043F\u043B\u0430\u0442\u0430:");
		label_1.setBounds(74, 103, 144, 14);
		contentPanel.add(label_1);

		JLabel label_2 = new JLabel(
				"\u041E\u043F\u043B\u0430\u0447\u0435\u043D\u043E:");
		label_2.setBounds(74, 134, 144, 14);
		contentPanel.add(label_2);

		JLabel label_3 = new JLabel(
				"\u0412\u043E\u0437\u0432\u0440\u0430\u0449\u0435\u043D\u043E:");
		label_3.setBounds(74, 165, 144, 14);
		contentPanel.add(label_3);

		JLabel label_4 = new JLabel("\u0421\u0443\u043C\u043C\u0430:");
		label_4.setBounds(74, 196, 144, 14);
		contentPanel.add(label_4);
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
								report.save();
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
						thisForm.setVisible(false);
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
	}
}
