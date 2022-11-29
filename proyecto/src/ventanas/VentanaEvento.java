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

public class VentanaEvento extends JFrame{

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtFecha;
	private JFrame ventanaActual, ventanaAnterior;
	
	private Connection com;
	private BaseDatos bd;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaEvento frame = new VentanaEvento();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public VentanaEvento() {
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
		
		//Panel Izq
		JPanel pCentralIzq = new JPanel();
		pCentral.add(pCentralIzq);
		
		JLabel lblNombreEvento = new JLabel("AÑADE UN TITULO: ");
		pCentralIzq.add(lblNombreEvento);
		
		txtNombre = new JTextField();
		pCentralIzq.add(txtNombre);
		txtNombre.setColumns(10);
		
		
		//Panel Drch
		JPanel pCentralDrch = new JPanel();
		pCentral.add(pCentralDrch);
		
		//Panel Drch Arriba
		JPanel pDrchArriba = new JPanel();
		pCentralDrch.add(pDrchArriba);
		pDrchArriba.setLayout(new GridLayout(7, 1, 0, 0));
		
		JLabel lblFecha = new JLabel("AÑADE UNA FECHA: ");
		pCentralDrch.add(lblFecha);
		
		txtFecha = new JTextField();
		pCentralDrch.add(txtFecha);
		txtFecha.setColumns(10);
		
		//Panel Drch Abajo
		JPanel pDrchAbajo = new JPanel();
		pCentralDrch.add(pDrchAbajo);
		pDrchAbajo.setLayout(new GridLayout(1, 1, 0, 0));
		
		JButton btnConfirmar = new JButton("CONFIRMAR");
		pDrchAbajo.add(btnConfirmar);
		
		btnConfirmar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaAnterior = VentanaEvento.this;
				ventanaActual = new VentanaAgenda();
				ventanaActual.setVisible(true);
				ventanaAnterior.dispose();
			}
		});
		
		
	}
	
}
