package gui;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;

import data.TipoTrabajador;
import data.Trabajador;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class IfrTrabajador extends JInternalFrame {
	
	private Trabajador _trabajador = null;
	private static final long serialVersionUID = 1L;
	private JTextField txtName;
	private JComboBox<TipoTrabajador> cmbTipoTrabajador;

	/**
	 * Create the frame.
	 */
	public IfrTrabajador() {
		setClosable(true);
		setResizable(true);
		setTitle("Trabajador");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblMessage = new JLabel("");
		lblMessage.setForeground(Color.BLUE);
		lblMessage.setBounds(20, 93, 414, 14);
		getContentPane().add(lblMessage);
		
		JLabel lblName = new JLabel("Nombre");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setBounds(185, 52, 46, 14);
		getContentPane().add(lblName);
		
		txtName = new JTextField();
		txtName.setHorizontalAlignment(SwingConstants.LEFT);
		txtName.setBounds(166, 77, 86, 20);
		getContentPane().add(txtName);
		txtName.setColumns(10);
		
		JLabel lblTipoTrabjador = new JLabel("Tipo Trabajador");
		lblTipoTrabjador.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipoTrabjador.setBounds(166, 118, 86, 14);
		getContentPane().add(lblTipoTrabjador);
		
		cmbTipoTrabajador = new JComboBox<TipoTrabajador>();
		try { cmbTipoTrabajador.setModel(new TipoTrabajadorListModel(TipoTrabajador.Search(null))); }
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null, "Ha ocurrido un error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		cmbTipoTrabajador.setBounds(166, 143, 86, 22);
		getContentPane().add(cmbTipoTrabajador);
		
		JButton butSave = new JButton("Guardar");
		butSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(_trabajador == null) _trabajador = new Trabajador(txtName.getText(), (TipoTrabajador)cmbTipoTrabajador.getModel().getSelectedItem());
					else {
						_trabajador.SetNombre(txtName.getText());
						_trabajador.SetTipoTrabajador((TipoTrabajador)cmbTipoTrabajador.getModel().getSelectedItem());
					}
					
					_trabajador.Save();
				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "Ha ocurrido un error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		butSave.setBounds(163, 201, 89, 23);
		getContentPane().add(butSave);
	}
	
	public IfrTrabajador(Trabajador trabajador) {
		this();
		
		if(trabajador == null) throw new IllegalArgumentException("El trabajador no puede ser nulo");
		txtName.setText(trabajador.GetNombre());
		cmbTipoTrabajador.getModel().setSelectedItem(trabajador.GetTipoTrabajador());
		
		_trabajador = trabajador;
	}
}