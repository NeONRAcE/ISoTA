import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JTextField;


import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JTable;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ListSelectionEvent;

public class SelectClientForm extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JList<ClientModel> listFIO;
	private JTextField textField;
	private JTable tblClientInfo;
	private ClientModel curClient;
	private SelectClientForm thisFrame;

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
		ClientModel[] clients = ClientModel.findClientsAll();
		DefaultListModel<ClientModel> listmodel = new DefaultListModel<ClientModel>();
		for (ClientModel client : clients)
		{
			listmodel.addElement(client);
		}
		listFIO.setModel(listmodel);
	}
	
	public ClientModel getClientModel()
	{
		return this.curClient;
	}

	/**
	 * Create the dialog.
	 */
	public SelectClientForm() {
		thisFrame = this;
		setTitle("\u0412\u044B\u0431\u0440\u0430\u0442\u044C \u041A\u043B\u0438\u0435\u043D\u0442\u0430");
		setBounds(100, 100, 445, 368);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		listFIO = new JList<ClientModel>();
		listFIO.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				ClientModel cm = listFIO.getSelectedValue();
				if (cm != null)
				{
					DefaultTableModel model = (DefaultTableModel) tblClientInfo
							.getModel();
					model.setRowCount(6);
					model.setColumnCount(2);
					model.setValueAt("Номер инспекции", 0, 0);
					model.setValueAt(cm.getRevisionNum(), 0, 1);
					model.setValueAt("Дата регистрации", 1, 0);
					model.setValueAt(cm.getRegistrationDate(), 1, 1);
					model.setValueAt("Адрес", 2, 0);
					model.setValueAt(cm.getAdress(), 2, 1);
					model.setValueAt("ФИО", 3, 0);
					model.setValueAt(cm.getFIO(), 3, 1);
					model.setValueAt("ИНН", 4, 0);
					model.setValueAt(cm.getUID(), 4, 1);
					model.setValueAt("Телефон", 5, 0);
					model.setValueAt(cm.getPhoneNumber(), 5, 1);
					String t = cm.getDirectorFIO();
					if (!t.equals("null"))
					{
						model.setRowCount(11);
						model.setValueAt("ФИО директора", 6, 0);
						model.setValueAt(cm.getDirectorFIO(), 6, 1);
						model.setValueAt("ИНН директора", 7, 0);
						model.setValueAt(cm.getDirectorUID(), 7, 1);
						model.setValueAt("Юридический адрес", 8, 0);
						model.setValueAt(cm.getDirectorAdress(), 8, 1);
						model.setValueAt("Номер директора", 9, 0);
						model.setValueAt(cm.getDirectorNumber(), 9, 1);
						model.setValueAt("Cумма капитала", 10, 0);
						model.setValueAt(cm.getCapitalSum(), 10, 1);

					}
				}
			}
		});
		listFIO.setBounds(10, 11, 200, 274);
		updateClientList();
		contentPanel.add(listFIO);
		
		textField = new JTextField();
		textField.setBounds(220, 9, 115, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JButton button = new JButton("\u041F\u043E\u0438\u0441\u043A");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String str = textField.getText();
				DefaultListModel<ClientModel> dlm = (DefaultListModel<ClientModel>) listFIO.getModel();
				for(int i=0; i<dlm.getSize(); i++)
				{
					ClientModel cm = dlm.getElementAt(i);
					if (cm.getFIO().contains(str))
					{
						listFIO.setSelectedIndex(i);
					}
				}
			}
		});
		button.setBounds(345, 8, 75, 23);
		contentPanel.add(button);
		
		tblClientInfo = new JTable();
		tblClientInfo.setBounds(220, 40, 200, 245);
		contentPanel.add(tblClientInfo);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("\u0412\u044B\u0431\u0440\u0430\u0442\u044C");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						curClient = listFIO.getSelectedValue();
						thisFrame.setVisible(false);
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
						thisFrame.setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
