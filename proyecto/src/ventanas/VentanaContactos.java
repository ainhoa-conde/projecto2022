package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.sql.Connection;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.BaseDatos;

public class VentanaContactos extends JFrame{

	private JPanel contentPane;
	private JFrame ventanaActual, ventanaAnterior, ventanaSiguiente;
	
	private Connection con;
	private BaseDatos bd;
	
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
		
		JButton btnAnyadir = new JButton("AÑADIR CONTACTO");
		pIzqAbajo.add(btnAnyadir);
		
		JLabel lblEspacio1 = new JLabel("");
		pIzqAbajo.add(lblEspacio1);
		
		JButton btnEliminar = new JButton("ELIMINAR CONTACTO");
		pIzqAbajo.add(btnEliminar);
		
		JLabel lblEspacio2 = new JLabel();
		pIzqAbajo.add(lblEspacio2);
		
		JButton btnEditar = new JButton("EDITAR CONTACTO");
		pIzqAbajo.add(btnEditar);
		
		JLabel lblEspacio3 = new JLabel();
		pIzqAbajo.add(lblEspacio3);
		
		JButton btnCerrar = new JButton("CERRAR");
		pIzqAbajo.add(btnCerrar);
		
		//Panel Drch
		
		
		
	}
	
}
