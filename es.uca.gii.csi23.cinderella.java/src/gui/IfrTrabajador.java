package gui;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import data.Trabajador;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IfrTrabajador extends JInternalFrame {
	
	private Trabajador _trabajador = null;
	private static final long serialVersionUID = 1L;
	private JTextField txtName;
	
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
		lblMessage.setBounds(10, 0, 414, 14);
		getContentPane().add(lblMessage);
		
		JLabel lblName = new JLabel("Nombre");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setBounds(186, 83, 46, 14);
		getContentPane().add(lblName);
		
		txtName = new JTextField();
		txtName.setHorizontalAlignment(SwingConstants.LEFT);
		txtName.setBounds(166, 108, 86, 20);
		getContentPane().add(txtName);
		txtName.setColumns(10);
		
		JButton butSave = new JButton("Guardar");
		butSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(_trabajador == null) _trabajador = new Trabajador(txtName.getText());
					else _trabajador.SetNombre(txtName.getText());
					
					_trabajador.Save();
				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "Ha ocurrido un error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		butSave.setBounds(166, 162, 89, 23);
		getContentPane().add(butSave);
	}
}
