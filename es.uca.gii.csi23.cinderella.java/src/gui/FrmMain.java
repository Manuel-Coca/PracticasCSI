package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmMain {

	private JFrame _frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmMain window = new FrmMain();
					window._frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FrmMain() throws Exception {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		_frame = new JFrame();
		_frame.setBounds(100, 100, 800, 800);
		_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		_frame.setJMenuBar(menuBar);
		
		JMenu mnuNew = new JMenu("Nuevo");
		menuBar.add(mnuNew);
		
		JMenuItem mitNewTrabajador = new JMenuItem("Trabajador");
		mitNewTrabajador.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { ShowInternalFrame(new IfrTrabajador(), 0, 0, 450, 300); } 
		});
		mnuNew.add(mitNewTrabajador);
		
		JMenuItem mitNewTipoTrabajador = new JMenuItem("Tipo Trabajador");
		mitNewTipoTrabajador.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { ShowInternalFrame(new IfrTipoTrabajador(), 0, 0, 450, 300); } 
		});
		mnuNew.add(mitNewTipoTrabajador);
		
		JMenu mnuSearch = new JMenu("Buscar");
		menuBar.add(mnuSearch);
		
		JMenuItem mitSearchTabajador = new JMenuItem("Trabajador");
		mitSearchTabajador.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { ShowInternalFrame(new IfrTrabajadores(getThis()), 300, 300, 450, 500); } 
		});
		mnuSearch.add(mitSearchTabajador);
		
		JMenuItem mitSearchTipoTabajador = new JMenuItem("Tipo Trabajador");
		mitSearchTipoTabajador.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { ShowInternalFrame(new IfrTiposTrabajadores(getThis()), 300, 300, 450, 500); } 
		});
		mnuSearch.add(mitSearchTipoTabajador);
		
		_frame.getContentPane().setLayout(null);
	}

	public void ShowInternalFrame(JInternalFrame ifr, int iX, int iY, int iWidth, int iHeight) {
		ifr.setBounds(iX, iY, iWidth, iHeight);
		_frame.getContentPane().add(ifr); 
		ifr.setVisible(true);
	}
	
	public FrmMain getThis() { return this; }
}
