package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;
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
		setBounds(500, 300, 300, 250);
		setTitle("EVENTO");
		setIconImage(Toolkit.getDefaultToolkit().getImage("imagenes/ventana_principal.png"));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.WHITE);
		
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		//Panel Central
		JPanel pCentral = new JPanel();
		contentPane.add(pCentral, BorderLayout.CENTER);
		pCentral.setLayout(new GridLayout(8, 2, 0, 0));
		
		JLabel lblNombre = new JLabel("NOMBRE Y APELLIDO: ");
		pCentral.add(lblNombre);
		
		txtNombre = new JTextField();
		pCentral.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblMail = new JLabel("CORREO: ");
		pCentral.add(lblMail);
		
		txtTelefono = new JTextField();
		pCentral.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		JLabel lblTelefono = new JLabel("TELEFONO: ");
		pCentral.add(lblTelefono);
	
		txtMail = new JTextField();
		pCentral.add(txtMail);
		txtMail.setColumns(10);
		
		JPanel pSur = new JPanel();
		contentPane.add(pSur, BorderLayout.SOUTH);
		pSur.setLayout(new GridLayout(1, 1, 0, 0));
		
		JButton btnConfirmar = new JButton("CONFIRMAR");
		pSur.add(btnConfirmar);
		
		JButton btnCerrar = new JButton("CERRAR");
		pSur.add(btnCerrar);
				
		btnConfirmar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		btnCerrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaAnterior = VentanaCrearContacto.this;
				ventanaActual = new VentanaContactos();
				ventanaActual.setVisible(true);
				ventanaAnterior.dispose();
			}
		});
				
		
				
		
		
	}
	
}
