import java.awt.Dialog;
import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JPanel;

import java.awt.FlowLayout;
import java.time.LocalDate;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JTextPane;

import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;

public class MainWindow
{
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

	private JFrame frame;
	private MainWindow MainWin;
	private JPanel panelReports;
	private JTable tableReports;
	private JList<fiolist> listFIO;
	private JPanel panelClients;
	private JLabel statusText;
	private JTable tableClientInfo;
	private ClientModel[] clients;
	private JTextField textField;

	/** Launch the application. */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() {
			public void run()
			{
				try
				{
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/** Create the application. */
	public MainWindow()
	{
		initialize();
	}

	/** Initialize the contents of the frame. */
	private void initialize()
	{
		MainWin = this;
		frame = new JFrame();
		frame.addFocusListener(new FrameFocusListener());
		frame.setResizable(false);
		frame.addWindowListener(new FrameWindowListener());
		frame.setBounds(100, 100, 686, 437);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu menuReports = new JMenu("\u041E\u0442\u0447\u0435\u0442\u044B");
		menuBar.add(menuReports);

		JMenuItem reportsOpen = new JMenuItem(
				"\u041E\u0442\u043A\u0440\u044B\u0442\u044C \u0441\u043F\u0438\u0441\u043E\u043A");
		menuReports.add(reportsOpen);

		JMenuItem reportsCreate = new JMenuItem(
				"\u0421\u043E\u0437\u0434\u0430\u0442\u044C");
		menuReports.add(reportsCreate);

		JMenuItem reportsEdit = new JMenuItem(
				"\u0420\u0435\u0434\u0430\u043A\u0442\u0438\u0440\u043E\u0432\u0430\u0442\u044C");
		menuReports.add(reportsEdit);

		JMenuItem reportsSave = new JMenuItem(
				"\u0421\u043E\u0445\u0440\u0430\u043D\u0438\u0442\u044C");
		menuReports.add(reportsSave);

		JMenuItem reportsDelete = new JMenuItem(
				"\u0423\u0434\u0430\u043B\u0438\u0442\u044C (\u0432\u044B\u0431\u0440.)");
		menuReports.add(reportsDelete);

		JMenuItem reportsDeleteAll = new JMenuItem(
				"\u0423\u0434\u0430\u043B\u0438\u0442\u044C (\u0432\u0441\u0435)");
		menuReports.add(reportsDeleteAll);

		JMenuItem reportsSearch = new JMenuItem(
				"\u041F\u043E\u0438\u0441\u043A");
		menuReports.add(reportsSearch);

		JMenu menuClients = new JMenu(
				"\u041A\u043B\u0438\u0435\u043D\u0442\u044B");
		menuBar.add(menuClients);

		JMenuItem clientsOpen = new JMenuItem(
				"\u041E\u0442\u043A\u0440\u044B\u0442\u044C \u0441\u043F\u0438\u0441\u043E\u043A");
		clientsOpen.addActionListener(new ClientsOpenActionListener());
		menuClients.add(clientsOpen);

		JMenuItem clientsAdd = new JMenuItem(
				"\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C");
		menuClients.add(clientsAdd);

		JMenuItem clientsEdit = new JMenuItem(
				"\u0420\u0435\u0434\u0430\u043A\u0442\u0438\u0440\u043E\u0432\u0430\u0442\u044C");
		menuClients.add(clientsEdit);

		JMenuItem clientsSave = new JMenuItem(
				"\u0421\u043E\u0445\u0440\u0430\u043D\u0438\u0442\u044C");
		menuClients.add(clientsSave);

		JMenuItem clientsDelete = new JMenuItem(
				"\u0423\u0434\u0430\u043B\u0438\u0442\u044C (\u0432\u044B\u0431\u0440.)");
		menuClients.add(clientsDelete);

		JMenuItem clientsDeleteAll = new JMenuItem(
				"\u0423\u0434\u0430\u043B\u0438\u0442\u044C(\u0432\u0441\u0435)");
		menuClients.add(clientsDeleteAll);

		JMenuItem clientsSearch = new JMenuItem(
				"\u041F\u043E\u0438\u0441\u043A");
		menuClients.add(clientsSearch);

		JMenu menuExit = new JMenu("\u0412\u044B\u0445\u043E\u0434");
		menuBar.add(menuExit);

		JMenuItem exitChangeUser = new JMenuItem(
				"\u0421\u043C\u0435\u043D\u0438\u0442\u044C \u043F\u043E\u043B\u044C\u0437\u043E\u0432\u0430\u0442\u0435\u043B\u044F");
		exitChangeUser.addActionListener(new ExitChangeUserActionListener());
		menuExit.add(exitChangeUser);

		JMenuItem exitExit = new JMenuItem("\u0412\u044B\u0445\u043E\u0434");
		menuExit.add(exitExit);
		frame.getContentPane().setLayout(null);

		panelClients = new JPanel();
		panelClients.setBounds(0, 0, 680, 366);
		frame.getContentPane().add(panelClients);

		listFIO = new JList<fiolist>();
		listFIO.setBounds(57, 68, 186, 256);
		listFIO.addListSelectionListener(new ListFIOListSelectionListener());
		panelClients.setLayout(null);
		panelClients.add(listFIO);

		JLabel labelClients = new JLabel(
				"\u0421\u043F\u0438\u0441\u043E\u043A \u043A\u043B\u0438\u0435\u043D\u0442\u043E\u0432");
		labelClients.setBounds(273, 21, 123, 14);
		labelClients.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelClients.add(labelClients);

		JLabel labelFIO = new JLabel("\u0424\u0418\u041E");
		labelFIO.setBounds(130, 51, 46, 14);
		panelClients.add(labelFIO);

		JLabel label = new JLabel(
				"\u0418\u043D\u0444\u043E\u0440\u043C\u0430\u0446\u0438\u044F \u043E \u043A\u043B\u0438\u0435\u043D\u0442\u0435");
		label.setBounds(378, 51, 123, 14);
		panelClients.add(label);

		tableClientInfo = new JTable();
		tableClientInfo.setBounds(253, 67, 369, 257);
		panelClients.add(tableClientInfo);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 329, 680, 35);
		panelClients.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label_1 = new JLabel("\u041F\u043E\u0438\u0441\u043A:");
		label_1.setBounds(10, 11, 46, 14);
		panel_1.add(label_1);
		
		textField = new JTextField();
		textField.setBounds(57, 8, 186, 20);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JButton button = new JButton("\u0418\u0441\u043A\u0430\u0442\u044C \u0434\u0430\u043B\u0435\u0435...");
		button.setBounds(253, 7, 115, 23);
		panel_1.add(button);
		panelClients.setVisible(false);

		panelReports = new JPanel();
		panelReports.setBounds(0, 0, 680, 366);
		frame.getContentPane().add(panelReports);
		panelReports.setLayout(null);

		JLabel labelReports = new JLabel(
				"\u0421\u043F\u0438\u0441\u043E\u043A \u043E\u0442\u0447\u0435\u0442\u043E\u0432");
		labelReports.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelReports.setBounds(278, 26, 110, 14);
		panelReports.add(labelReports);

		tableReports = new JTable();
		tableReports.setRowSelectionAllowed(false);
		tableReports.setBounds(47, 304, 571, -237);
		panelReports.add(tableReports);
		panelReports.setVisible(false);

		JPanel panel = new JPanel();
		panel.setBounds(0, 368, 680, 20);
		frame.getContentPane().add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		statusText = new JLabel(
				"\u0424\u0418\u041E \u043F\u043E\u043B\u044C\u0437\u043E\u0432\u0430\u0442\u0435\u043B\u044F: ");
		statusText.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(statusText);

		JSeparator separator = new JSeparator();
		separator.setForeground(UIManager.getColor("Button.background"));
		panel.add(separator);

		JLabel statusDate = new JLabel(LocalDate.now().toString());
		statusDate.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(statusDate);

	}

	private void callLoginDialog()
	{
		MainWin.frame.setVisible(false);
		LoginDialog ld = new LoginDialog();
		ld.setModalityType(Dialog.ModalityType.DOCUMENT_MODAL);
		ld.setVisible(true);
		if (!ld.getLoginResult()) System.exit(0);
		ld.dispose();
		MainWin.frame.setVisible(true);
	}

	private class FrameWindowListener extends WindowAdapter
	{
		@Override
		public void windowOpened(WindowEvent arg0)
		{
			callLoginDialog();
		}
	}

	private class FrameFocusListener extends FocusAdapter
	{
		@Override
		public void focusGained(FocusEvent arg0)
		{
			if (User.getCurrentUser().getID() != 0)
			{
				MainWin.statusText.setText("ФИО пользователя: "
						+ User.getCurrentUser().getName() + " "
						+ User.getCurrentUser().getLastName());
			}
		}
	}

	private class ExitChangeUserActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			callLoginDialog();
		}
	}

	private class ClientsOpenActionListener implements ActionListener
	{

		public void actionPerformed(ActionEvent arg0)
		{
			clients = ClientModel.findClientsAll();
			DefaultListModel<fiolist> listmodel = new DefaultListModel<fiolist>();
			for (ClientModel client : clients)
			{
				listmodel.addElement(new fiolist(client));
			}
			listFIO.setModel(listmodel);
			panelClients.setVisible(true);
		}
	}

	private class ListFIOListSelectionListener implements ListSelectionListener
	{
		public void valueChanged(ListSelectionEvent e)
		{
			ClientModel cm = listFIO.getSelectedValue().client;
			if (cm != null)
			{
				DefaultTableModel model = (DefaultTableModel) tableClientInfo
						.getModel();
				model.setRowCount(11);
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
				if (t!=null)
				{
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
	}
}