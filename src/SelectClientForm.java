import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class SelectClientForm extends JDialog {

	private class fiolist
	{
		ClientModel client;

		public fiolist(ClientModel cm)
		{
			client = cm;
		}

		public String toString()
		{
			return client.getFIO();
		}
	}
	
	private final JPanel contentPanel = new JPanel();
	private JList<fiolist> listFIO;
	private ClientModel[] clients;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SelectClientForm dialog = new SelectClientForm();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void updateClientList()
	{
		clients = ClientModel.findClientsAll();
		DefaultListModel<fiolist> listmodel = new DefaultListModel<fiolist>();
		for (ClientModel client : clients)
		{
			listmodel.addElement(new fiolist(client));
		}
		listFIO.setModel(listmodel);
	}

	/**
	 * Create the dialog.
	 */
	public SelectClientForm() {
		setTitle("\u0412\u044B\u0431\u0440\u0430\u0442\u044C \u041A\u043B\u0438\u0435\u043D\u0442\u0430");
		setBounds(100, 100, 421, 368);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JList listFIO = new JList();
		listFIO.setBounds(36, 11, 186, 274);
		contentPanel.add(listFIO);
		
		textField = new JTextField();
		textField.setBounds(232, 34, 86, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JButton button = new JButton("\u041F\u043E\u0438\u0441\u043A");
		button.setBounds(328, 33, 63, 23);
		contentPanel.add(button);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("\u0412\u044B\u0431\u0440\u0430\u0442\u044C");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
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
