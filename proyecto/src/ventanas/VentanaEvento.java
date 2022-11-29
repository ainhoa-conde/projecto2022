package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import clases.BaseDatos;

public class VentanaEvento extends JFrame{

	private JPanel contentPane;
	private JTextField txtNombreEvento;
	private JTextField txtFechaEvento;
	private JFrame ventanaActual, ventanaAnterior, ventanaSiguiente;
	
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
		setBounds(100, 100, 1000, 560);
		setTitle("Evento");
		setIconImage(Toolkit.getDefaultToolkit().getImage("imagenes/ventana_principal.png"));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.WHITE);
		
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		
		
		
	}
	
}
