package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import clases.BaseDatos;

public class VentanaCrearContacto extends JFrame{

	private JPanel contentPane;
	private JFrame ventanaActual, ventanaAnterior;
	private JTextField txtNombre;
	private JTextField txtMail;
	private JTextField txtTelefono;
	
	private Connection con;
	private BaseDatos bd;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaCrearContacto frame = new VentanaCrearContacto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public VentanaCrearContacto() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 300, 500, 200);
		setTitle("Evento");
		setIconImage(Toolkit.getDefaultToolkit().getImage("imagenes/ventana_principal.png"));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.WHITE);
		
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		//Panel Central
		JPanel pCentral = new JPanel();
		contentPane.add(pCentral, BorderLayout.CENTER);
		pCentral.setLayout(new GridLayout(1, 2, 0, 0));
		
		
		//Panel izquierdo
		JPanel pCentralIzq = new JPanel();
		pCentral.add(pCentralIzq);
		
		JPanel pIzqArriba = new JPanel();
		pCentralIzq.add(pIzqArriba);
		pIzqArriba.setLayout(new GridLayout(7, 1, 0, 0));
		
		JLabel lblNombre = new JLabel("NOMBRE Y APELLIDO: ");
		pIzqArriba.add(lblNombre);
		
		JPanel pIzqCentro = new JPanel();
		pCentralIzq.add(pIzqCentro);
		pIzqCentro.setLayout(new GridLayout(4, 1, 0, 0));
		
		JLabel lblMail = new JLabel("CORREO: ");
		pIzqCentro.add(lblMail);
		
		JPanel pIzqAbajo = new JPanel();
		pCentralIzq.add(pIzqAbajo);
		pIzqAbajo.setLayout(new GridLayout(4, 1, 0, 0));
		
		JLabel lblTelefono = new JLabel("TELEFONO: ");
		pIzqAbajo.add(lblTelefono);
	
		//Panel Derecha
		JPanel pCentralDrch = new JPanel();
		pCentral.add(pCentralDrch);
				
		txtNombre = new JTextField();
		pCentralDrch.add(txtNombre);
		txtNombre.setColumns(10);
				
		txtTelefono = new JTextField();
		pCentralDrch.add(txtTelefono);
		txtTelefono.setColumns(10);
				
		txtMail = new JTextField();
		pCentralDrch.add(txtMail);
		txtMail.setColumns(10);
		
	}
	
}
