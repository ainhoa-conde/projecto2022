package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class VentanaAgenda extends JFrame{

	private JPanel contentPane;
	private JFrame ventanaActual, ventanaAnterior, ventanaSiguiente;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAgenda frame = new VentanaAgenda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	}
	
	public VentanaAgenda() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 560);
		setTitle("Agenda");
		setIconImage(Toolkit.getDefaultToolkit().getImage("imagenes/ventana_principal.png"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.WHITE);
		
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		//Panel Central: Contenedor para todos los elementos
		JPanel pCentral = new JPanel();
		contentPane.add(pCentral, BorderLayout.CENTER);
		pCentral.setLayout(new GridLayout());
		
		JPanel pCentralIzq = new JPanel();
		pCentral.add(pCentralIzq);
		
		JPanel pCentralDch = new JPanel();
		pCentral.add(pCentralDch);
		
		//Panel Dch. Arriba: Imagen TaskMan
		JPanel pDchArriba = new JPanel();
		pCentralDch.add(pDchArriba);
		ImageIcon imagen = new ImageIcon("imagenes/imagenventanaInicioSesion.png");
		JLabel pDchImagen = new JLabel(imagen);
		pDchArriba.add(pDchImagen);
		
		//Panel Dch. Abajo: Botones "añadir" y "eliminar"
		JPanel pDchAbajo = new JPanel();
		pCentralDch.add(pDchAbajo);
		
		JButton btnAnyadir = new JButton("AÑADIR");
		pDchAbajo.add(btnAnyadir);
		
		JLabel lblEspacio1 = new JLabel("");
		pDchAbajo.add(lblEspacio1);
		
		JButton btnEliminar = new JButton("ELIMIAR");
		pDchAbajo.add(btnEliminar);
		
		
	}
	
}
