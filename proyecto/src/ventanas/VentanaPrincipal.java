package ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JCalendar;

import clases.BaseDatos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class VentanaPrincipal extends JFrame {

	private JPanel contentPane;
	private JFrame ventanaActual, ventanaAnterior, ventanaSiguiente;
	private JCalendar calendario;
	
	private Connection con;
	private BaseDatos bd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 500, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.WHITE);

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		//ventanaActual = this;
		//ventanaAnterior = new VentanaInicioSesion();
		
		JPanel pCentral = new JPanel();
		contentPane.add(pCentral, BorderLayout.CENTER);
		pCentral.setLayout(new GridLayout(1, 2, 0, 0));
		
		JPanel pCentralIzq = new JPanel();
		pCentral.add(pCentralIzq);
		
		JPanel pIzqArriba = new JPanel();
		pCentralIzq.add(pIzqArriba);
		ImageIcon imagen1 = new ImageIcon("imagenes/imagenventanaInicioSesion.png");
		JLabel pIzqImagen = new JLabel(imagen1);
		pIzqArriba.add(pIzqImagen);
		
		JPanel pIzqAbajo = new JPanel();
		pCentralIzq.add(pIzqAbajo);
		pIzqAbajo.setLayout(new GridLayout(5, 1, 0, 0));
		
		JButton btnAgenda = new JButton("AGENDA");
		pIzqAbajo.add(btnAgenda);
		
		JLabel lblEspacio1 = new JLabel("");
		pIzqAbajo.add(lblEspacio1);
		
		JButton btnContactos = new JButton("CONTACTOS");
		pIzqAbajo.add(btnContactos);
		
		JLabel lblEspacio2 = new JLabel("");
		pIzqAbajo.add(lblEspacio2);
		
		JButton btnCerrar = new JButton("CERRAR");
		pIzqAbajo.add(btnCerrar);
		
		JPanel pCentralDrch = new JPanel();
		pCentral.add(pCentralDrch);
		pCentralDrch.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel pDrchArriba = new JPanel();
		pCentralDrch.add(pDrchArriba);
		
		JLabel lblCalendario = new JLabel("CALENDARIO");
		pDrchArriba.add(lblCalendario);
		
		JPanel pDrchAbajo = new JPanel();
		pCentralDrch.add(pDrchAbajo);
		
		JCalendar calendario = new JCalendar();
		pDrchAbajo.add(calendario);
		
		JPanel eventos = new JPanel();
		pDrchAbajo.add(eventos);
		
		/*btnCerrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Cerrando sesión...", "REGISTRADO", JOptionPane.INFORMATION_MESSAGE);
				ventanaAnterior.setVisible(true);
				ventanaActual.dispose();
			}
		});*/
	}

}