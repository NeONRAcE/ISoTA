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
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.Vector;

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
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.UIManager;
import javax.swing.border.LineBorder;

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

	private Integer searchPos = 0;
	private JFrame frmIsota;
	private MainWindow MainWin;
	private JPanel panelReports;
	private JTable tableReports;
	private JList<fiolist> listFIO;
	private JPanel panelClients;
	private JLabel statusText;
	private JTable tableClientInfo;
	private ClientModel[] clients;
	private JTextField tfFIO;
	private JMenuItem reportsOpen;
	private JMenuItem reportsCreate;
	private JMenuItem reportsEdit;
	private JMenuItem reportsSave;
	private JMenuItem reportsDelete;
	private JMenuItem reportsDeleteAll;
	private JMenuItem reportsSearch;
	private JMenuItem clientsOpen;
	private JMenuItem clientsAdd;
	private JMenuItem clientsEdit;
	private JMenuItem clientsSave;
	private JMenuItem clientsDelete;
	private JMenuItem clientsDeleteAll;
	private JMenuItem clientsSearch;
	private JPanel panel_1;
	private JMenu menu;
	private JTextField tfSearch;

	/** Launch the application. */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() {
			public void run()
			{
				try
				{
					MainWindow window = new MainWindow();
					window.frmIsota.setVisible(true);
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
		frmIsota = new JFrame();
		frmIsota.setTitle("ISoTA");
		frmIsota.addFocusListener(new FrameFocusListener());
		frmIsota.setResizable(false);
		frmIsota.addWindowListener(new FrameWindowListener());
		frmIsota.setBounds(100, 100, 686, 437);
		frmIsota.setLocationRelativeTo(null);
		frmIsota.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmIsota.setJMenuBar(menuBar);

		JMenu menuReports = new JMenu("\u041E\u0442\u0447\u0435\u0442\u044B");
		menuBar.add(menuReports);

		reportsOpen = new JMenuItem(
				"\u041E\u0442\u043A\u0440\u044B\u0442\u044C \u0441\u043F\u0438\u0441\u043E\u043A");
		reportsOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				panelClients.setVisible(false);
				panelReports.setVisible(true);
				try
				{
					tableReports.setModel(ReportModel.findReportsAllTM());
					((DefaultTableModel) tableReports.getModel())
							.fireTableDataChanged();
					JTableHeader th = tableReports.getTableHeader();
					th.setEnabled(true);
					tableReports.getColumnModel().getColumn(0).setMinWidth(0);
					tableReports.getColumnModel().getColumn(0).setMaxWidth(0);
				}
				catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		menuReports.add(reportsOpen);

		reportsCreate = new JMenuItem(
				"\u0421\u043E\u0437\u0434\u0430\u0442\u044C");
		reportsCreate.addActionListener(new ReportsCreateActionListener());
		menuReports.add(reportsCreate);

		reportsEdit = new JMenuItem(
				"\u0420\u0435\u0434\u0430\u043A\u0442\u0438\u0440\u043E\u0432\u0430\u0442\u044C");
		reportsEdit.addActionListener(new ReportsEditActionListener());
		menuReports.add(reportsEdit);

		reportsSave = new JMenuItem(
				"\u0421\u043E\u0445\u0440\u0430\u043D\u0438\u0442\u044C");
		menuReports.add(reportsSave);

		reportsDelete = new JMenuItem(
				"\u0423\u0434\u0430\u043B\u0438\u0442\u044C (\u0432\u044B\u0431\u0440.)");
		reportsDelete.addActionListener(new ReportsDeleteActionListener());
		menuReports.add(reportsDelete);

		reportsDeleteAll = new JMenuItem(
				"\u0423\u0434\u0430\u043B\u0438\u0442\u044C (\u0432\u0441\u0435)");
		reportsDeleteAll
				.addActionListener(new ReportsDeleteAllActionListener());
		menuReports.add(reportsDeleteAll);

		reportsSearch = new JMenuItem("\u041F\u043E\u0438\u0441\u043A");
		reportsSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				panel_1.setVisible(true);
			}
		});
		menuReports.add(reportsSearch);

		JMenu menuClients = new JMenu(
				"\u041A\u043B\u0438\u0435\u043D\u0442\u044B");
		menuBar.add(menuClients);

		clientsOpen = new JMenuItem(
				"\u041E\u0442\u043A\u0440\u044B\u0442\u044C \u0441\u043F\u0438\u0441\u043E\u043A");
		clientsOpen.addActionListener(new ClientsOpenActionListener());
		menuClients.add(clientsOpen);

		clientsAdd = new JMenuItem(
				"\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C");
		clientsAdd.addActionListener(new ClientsAddActionListener());
		menuClients.add(clientsAdd);

		clientsEdit = new JMenuItem(
				"\u0420\u0435\u0434\u0430\u043A\u0442\u0438\u0440\u043E\u0432\u0430\u0442\u044C");
		clientsEdit.addActionListener(new ClientsEditActionListener());
		menuClients.add(clientsEdit);

		clientsSave = new JMenuItem(
				"\u0421\u043E\u0445\u0440\u0430\u043D\u0438\u0442\u044C");
		menuClients.add(clientsSave);

		clientsDelete = new JMenuItem(
				"\u0423\u0434\u0430\u043B\u0438\u0442\u044C (\u0432\u044B\u0431\u0440.)");
		clientsDelete.addActionListener(new ClientsDeleteActionListener());
		menuClients.add(clientsDelete);

		clientsDeleteAll = new JMenuItem(
				"\u0423\u0434\u0430\u043B\u0438\u0442\u044C(\u0432\u0441\u0435)");
		clientsDeleteAll
				.addActionListener(new ClientsDeleteAllActionListener());
		menuClients.add(clientsDeleteAll);

		clientsSearch = new JMenuItem("\u041F\u043E\u0438\u0441\u043A");
		menuClients.add(clientsSearch);

		JMenu menuExit = new JMenu("\u0412\u044B\u0445\u043E\u0434");
		menuBar.add(menuExit);

		JMenuItem exitChangeUser = new JMenuItem(
				"\u0421\u043C\u0435\u043D\u0438\u0442\u044C \u043F\u043E\u043B\u044C\u0437\u043E\u0432\u0430\u0442\u0435\u043B\u044F");
		exitChangeUser.addActionListener(new ExitChangeUserActionListener());
		menuExit.add(exitChangeUser);

		JMenuItem exitExit = new JMenuItem("\u0412\u044B\u0445\u043E\u0434");
		menuExit.add(exitExit);

		menu = new JMenu(
				"\u0410\u0434\u043C\u0438\u043D. \u043F\u0430\u043D\u0435\u043B\u044C");
		menuBar.add(menu);

		JMenuItem menuItem = new JMenuItem(
				"\u0420\u0435\u0433\u0438\u0441\u0442\u0440\u0430\u0446\u0438\u044F");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				RegForm rf = new RegForm();
				rf.setModal(true);
				rf.setVisible(true);
			}
		});
		menu.add(menuItem);
		frmIsota.getContentPane().setLayout(null);
		
				panelReports = new JPanel();
				panelReports.setBounds(0, 0, 680, 366);
				frmIsota.getContentPane().add(panelReports);
				panelReports.setLayout(null);
				
						JLabel labelReports = new JLabel(
								"\u0421\u043F\u0438\u0441\u043E\u043A \u043E\u0442\u0447\u0435\u0442\u043E\u0432");
						labelReports.setFont(new Font("Tahoma", Font.PLAIN, 14));
						labelReports.setBounds(278, 26, 110, 14);
						panelReports.add(labelReports);
						
								tableReports = new JTable();
								tableReports.setBorder(new LineBorder(new Color(0, 0, 0)));
								tableReports.setRowSelectionAllowed(false);
								tableReports.setBounds(52, 51, 571, 237);
								panelReports.add(tableReports);
								
										tfSearch = new JTextField();
										tfSearch.setBounds(52, 299, 86, 20);
										panelReports.add(tfSearch);
										tfSearch.setColumns(10);
										
												JButton btnSearch = new JButton("Искать...");
												btnSearch.addActionListener(new BtnSearchActionListener());
												btnSearch.setBounds(148, 299, 89, 23);
												panelReports.add(btnSearch);

		panelClients = new JPanel();
		panelClients.setBounds(0, 0, 680, 366);
		frmIsota.getContentPane().add(panelClients);

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

		panel_1 = new JPanel();
		panel_1.setBounds(0, 329, 680, 35);
		panelClients.add(panel_1);
		panel_1.setLayout(null);
		panel_1.setVisible(false);

		JLabel label_1 = new JLabel("\u041F\u043E\u0438\u0441\u043A:");
		label_1.setBounds(10, 11, 46, 14);
		panel_1.add(label_1);

		tfFIO = new JTextField();
		tfFIO.setBounds(57, 8, 186, 20);
		panel_1.add(tfFIO);
		tfFIO.setColumns(10);

		JButton button = new JButton(
				"\u0418\u0441\u043A\u0430\u0442\u044C \u0434\u0430\u043B\u0435\u0435...");
		button.addActionListener(new ButtonActionListener());
		button.setBounds(253, 7, 115, 23);
		panel_1.add(button);

		JPanel panel = new JPanel();
		panel.setBounds(0, 368, 680, 20);
		frmIsota.getContentPane().add(panel);
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
		MainWin.frmIsota.setVisible(false);
		LoginDialog ld = new LoginDialog();
		ld.setModalityType(Dialog.ModalityType.DOCUMENT_MODAL);
		ld.setVisible(true);
		if (!ld.getLoginResult()) System.exit(0);
		ld.dispose();
		MainWin.frmIsota.setVisible(true);
	}

	private void setSecurityRights()
	{
		byte sclass = User.getCurrentUser().getSecurityClass();
		if (sclass == 3) // сотрудник анал.
		{
			reportsCreate.setEnabled(false);
			reportsDelete.setEnabled(false);
			reportsDeleteAll.setEnabled(false);
			reportsEdit.setEnabled(false);
			reportsSave.setEnabled(false);
			clientsAdd.setEnabled(false);
			clientsDelete.setEnabled(false);
			clientsDeleteAll.setEnabled(false);
			clientsEdit.setEnabled(false);
			clientsSave.setEnabled(false);
			menu.setVisible(false);
		}

		if (sclass == 2) // нач анал
		{
			reportsCreate.setEnabled(false);
			reportsDelete.setEnabled(false);
			reportsDeleteAll.setEnabled(false);
			reportsEdit.setEnabled(false);
			reportsSave.setEnabled(false);
			menu.setVisible(false);
		}

		if (sclass == 1 || sclass == 4)
		{
			menu.setVisible(false);
		}
	}

	private class FrameWindowListener extends WindowAdapter
	{
		@Override
		public void windowOpened(WindowEvent arg0)
		{
			callLoginDialog();
			setSecurityRights();
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
			updateClientList();
		}
	}

	private class ExitChangeUserActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			callLoginDialog();
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
		panelClients.setVisible(true);
	}

	private class ClientsOpenActionListener implements ActionListener
	{

		public void actionPerformed(ActionEvent arg0)
		{
			panelClients.setVisible(true);
			panelReports.setVisible(false);
			updateClientList();
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
				if (t != null)
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

	private class ButtonActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			// clients = ClientModel.findClientsAll();
			clients = ClientModel.findClientsByName(tfFIO.getText());
			DefaultListModel<fiolist> listmodel = new DefaultListModel<fiolist>();
			for (ClientModel client : clients)
			{
				listmodel.addElement(new fiolist(client));
			}
			listFIO.setModel(listmodel);
			panelClients.setVisible(true);
		}
	}

	private class ClientsEditActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			ClientModel cm = listFIO.getSelectedValue().client;
			AddEditForm aef = new AddEditForm();
			aef.setModel(cm);
			aef.setModal(true);
			aef.setVisible(true);
		}
	}

	private class ClientsAddActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			AddEditForm aef = new AddEditForm();
			aef.setModal(true);
			aef.setVisible(true);
		}
	}

	private class BtnSearchActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			searchPos = reportLiveSearch(
					tableReports.getSelectedRow()
							* tableReports.getColumnCount()
							+ tableReports.getSelectedColumn(),
					tfSearch.getText());
			// tableReports.changeSelection(rowIndex, columnIndex, toggle,
			// extend);
		}
	}

	private class ReportsCreateActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			AddEditFormReport aefr = new AddEditFormReport();
			aefr.setModal(true);
			aefr.setVisible(true);
		}
	}

	private class ReportsEditActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			AddEditFormReport aefr = new AddEditFormReport();
			int line = tableReports.getSelectedRow();
			if (line == -1)
			{
				JOptionPane.showMessageDialog(null, "Выберите строку.");
				return;
			}
			ReportModel rm = new ReportModel();
			rm.setID((Long) tableReports.getValueAt(line, 0));
			// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			rm.setOperationDate((Date) tableReports.getValueAt(line, 1));
			rm.setKod((String) tableReports.getValueAt(line, 2));
			rm.setSum((Float) tableReports.getValueAt(line, 3));
			rm.setPaid((Float) tableReports.getValueAt(line, 4));
			rm.setReturned((Float) tableReports.getValueAt(line, 5));
			rm.setOverpayment((Float) tableReports.getValueAt(line, 6));
			aefr.setModel(rm);
			aefr.setModal(true);
			aefr.setVisible(true);
		}
	}

	private class ReportsDeleteActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			int line = tableReports.getSelectedRow();
			if (line == -1)
			{
				JOptionPane.showMessageDialog(null, "Выберите строку.");
				return;
			}
			ReportModel rm = new ReportModel();
			rm.setID((Long) tableReports.getValueAt(line, 0));
			// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			rm.setOperationDate((Date) tableReports.getValueAt(line, 1));
			rm.setKod((String) tableReports.getValueAt(line, 2));
			rm.setSum((Float) tableReports.getValueAt(line, 3));
			rm.setPaid((Float) tableReports.getValueAt(line, 4));
			rm.setReturned((Float) tableReports.getValueAt(line, 5));
			rm.setOverpayment((Float) tableReports.getValueAt(line, 6));
			rm.delete();
		}
	}

	private class ReportsDeleteAllActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			ReportModel.deleteAll();
		}
	}

	private class ClientsDeleteActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			fiolist index = listFIO.getSelectedValue();
			if (index == null)
				JOptionPane.showMessageDialog(null, "Выберите элемент");
			ClientModel cm = index.client;
			cm.delete();
		}
	}

	private class ClientsDeleteAllActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			ClientModel.deleteAll();
		}
	}

	
	private Integer reportLiveSearch(Integer p, String s)
	{
		TableModel dtm = tableReports.getModel();
		int curpos = 0;
		for (int i = 1; i < dtm.getRowCount(); i++)
		{
			for (int j = 0; j < dtm.getColumnCount(); j++)
			{
				curpos = i * dtm.getColumnCount() + j;
				if (p < curpos)
				{
					Object o = dtm.getValueAt(i, j);
					String so = o.toString();
					if (so.contains(s))
					{
						tableReports.setCellSelectionEnabled(true);
						tableReports.changeSelection(i, j, false, false);
						return curpos;
					}
				}
			}
		}
		return p;
	}
}