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
		setBounds(100, 100, 450, 368);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JList listFIO = new JList();
		listFIO.setBounds(57, 68, 186, 256);
		contentPanel.add(listFIO);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
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
				JButton cancelButton = new JButton("Cancel");
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
