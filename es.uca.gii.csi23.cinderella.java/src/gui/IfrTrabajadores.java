package gui;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import data.Trabajador;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class IfrTrabajadores extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;
	private JTable tabResult;

	/**
	 * Create the frame.
	 */
	public IfrTrabajadores(FrmMain frmMain) {
		setTitle("Trabajadores");
		setBounds(100, 100, 450, 300);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblNombre = new JLabel("Nombre");
		panel.add(lblNombre);
		
		txtNombre = new JTextField();
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		JButton butSearch = new JButton("Buscar");
		butSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try { tabResult.setModel(new TrabajadoresTableModel(Trabajador.Search(txtNombre.getText()))); }
				catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "Ha ocurrido un error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel.add(butSearch);
		
		tabResult = new JTable();
		tabResult.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//Se activa con doble click sobre una fila
				if(e.getClickCount() == 2) {
					int iRow = ((JTable)e.getSource()).getSelectedRow();
					Trabajador trabajador = ((TrabajadoresTableModel)tabResult.getModel()).getData(iRow);
					if(trabajador != null) frmMain.ShowInternalFrame(new IfrTrabajador(trabajador), 10, 27, 450, 300);
				}
			}
		});
		getContentPane().add(tabResult, BorderLayout.CENTER);

	}

}
