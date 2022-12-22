package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.BaseDatos;

public class VentanaContactos extends JFrame{

	private JPanel contentPane;
	private JFrame ventanaActual, ventanaAnterior, ventanaSiguiente;
	
	private Connection con;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaContactos frame = new VentanaContactos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public VentanaContactos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 560);
		setTitle("Contactos");
		setIconImage(Toolkit.getDefaultToolkit().getImage("imagenes/ventana_principal.png"));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.WHITE);
		
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		//Panel Central: Contenedor para todos lo elementos
		JPanel pCentral = new JPanel();
		contentPane.add(pCentral, BorderLayout.CENTER);
		pCentral.setLayout(new GridLayout(1, 2, 0, 0));
		
		JPanel pCentralIzq = new JPanel();
		pCentral.add(pCentralIzq);
		
		//Panel Izq. Arriba: Imagen de TaskMan
		JPanel pIzqArriba = new JPanel();
		pCentralIzq.add(pIzqArriba);
		ImageIcon imagen = new ImageIcon("imagenes/imagenventanaInicioSesion.png");
		JLabel pnlIzqImagen = new JLabel(imagen);
		pIzqArriba.add(pnlIzqImagen);
		
		//Panel Izq. Abajo: Botones "añadir", "eliminar", "editar" y "cerrar"
		JPanel pIzqAbajo = new JPanel();
		pCentralIzq.add(pIzqAbajo);
		pIzqAbajo.setLayout(new GridLayout(7, 1, 0, 0));
		
		JButton btnAnyadirContacto = new JButton("AÑADIR CONTACTO");
		pIzqAbajo.add(btnAnyadirContacto);
		
		JLabel lblEspacio1 = new JLabel("");
		pIzqAbajo.add(lblEspacio1);
		
		JButton btnEliminarContacto = new JButton("ELIMINAR CONTACTO");
		pIzqAbajo.add(btnEliminarContacto);
		
		JLabel lblEspacio2 = new JLabel();
		pIzqAbajo.add(lblEspacio2);
		
		JButton btnEditarContacto = new JButton("EDITAR CONTACTO");
		pIzqAbajo.add(btnEditarContacto);
		
		JLabel lblEspacio3 = new JLabel();
		pIzqAbajo.add(lblEspacio3);
		
		JButton btnCerrar = new JButton("CERRAR");
		pIzqAbajo.add(btnCerrar);
		
		//Eventos
		btnCerrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaAnterior = new VentanaPrincipal();
				ventanaActual = VentanaContactos.this;
				ventanaAnterior.setVisible(true);
				ventanaActual.dispose();
			}
		});
		
		btnAnyadirContacto.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaSiguiente = new VentanaCrearContacto();
				ventanaActual = VentanaContactos.this;
				ventanaSiguiente.setVisible(true);
				ventanaActual.dispose();
				
			}
		});

		btnEliminarContacto.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		btnEditarContacto.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		//Panel Drch
		JPanel pCentralDrch = new JPanel();
		pCentral.add(pCentralDrch);
		
		//Panel Drch. Arriba: Contactos
		JPanel pDrchArriba = new JPanel();
		pCentralDrch.add(pDrchArriba);
		pDrchArriba.setLayout(new GridLayout(7, 1, 0, 2));
		
		//Panel Drch. Abajo: Favoritos
		JPanel pDrchAbajo = new JPanel();
		pCentralDrch.add(pDrchAbajo);
		pDrchAbajo.setLayout(new GridLayout(7, 1, 0 , 1));
		
	}
	
}
