import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddEditForm extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfFIO;
	private JButton okButton;
	private JButton cancelButton;
	private JDialog thisForm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddEditForm dialog = new AddEditForm();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddEditForm() {
		setModal(true);
		setBounds(100, 100, 450, 366);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		thisForm = this;

		JLabel label = new JLabel(
				"\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C \u043A\u043B\u0438\u0435\u043D\u0442\u0430");
		label.setBounds(169, 23, 108, 14);
		contentPanel.add(label);

		JLabel label_1 = new JLabel("\u0424\u0418\u041E:");
		label_1.setBounds(62, 63, 46, 14);
		contentPanel.add(label_1);

		tfFIO = new JTextField();
		tfFIO.setBounds(112, 60, 239, 20);
		contentPanel.add(tfFIO);
		tfFIO.setColumns(10);

		ButtonGroup btnGroup = new ButtonGroup();

		JRadioButton rbtnFiz = new JRadioButton(
				"\u0424\u0438\u0437\u0438\u0447\u0435\u0441\u043A\u043E\u0435 \u043B\u0438\u0446\u043E");

		rbtnFiz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				okButton.setVisible(true);
				thisForm.setBounds(100, 100, 450, 400);
			}
		});
		rbtnFiz.setBounds(72, 102, 143, 23);
		contentPanel.add(rbtnFiz);
		btnGroup.add(rbtnFiz);

		JRadioButton rbtnJur = new JRadioButton(
				"\u042E\u0440\u0438\u0434\u0438\u0447\u0435\u0441\u043A\u043E\u0435 \u043B\u0438\u0446\u043E");
		rbtnJur.setBounds(217, 102, 144, 23);
		contentPanel.add(rbtnJur);
		btnGroup.add(rbtnJur);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				okButton.setVisible(false);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);

			}
		}
	}
}
