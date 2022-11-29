package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.BaseDatos;
import clases.Evento;

public class VentanaAgenda extends JFrame{

	private JPanel contentPane;
	private JFrame ventanaActual, ventanaAnterior, ventanaSiguiente;
	
	private Connection con;
	private BaseDatos bd;
	
	
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
		setBounds(100, 100, 1000, 560);
		setTitle("Agenda");
		setIconImage(Toolkit.getDefaultToolkit().getImage("imagenes/ventana_principal.png"));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.WHITE);
		
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		//Panel Central: Contenedor para todos los elementos
		JPanel pCentral = new JPanel();
		contentPane.add(pCentral, BorderLayout.CENTER);
		pCentral.setLayout(new GridLayout());
		
		//Panel Izq: lista de eventos
		JPanel pCentralIzq = new JPanel();
		pCentral.add(pCentralIzq);
		
		JList<Evento> lista = new JList<Evento>();
		DefaultListModel<Evento> modelo = new DefaultListModel<>();
		
		lista.setModel(modelo);
		lista.setSize(500, 500);
		
		
		JPanel pCentralDch = new JPanel();
		pCentral.add(pCentralDch);
		
		//Panel Dch. Arriba: Imagen TaskMan
		JPanel pDchArriba = new JPanel();
		pCentralDch.add(pDchArriba);
		ImageIcon imagen = new ImageIcon("imagenes/imagenventanaInicioSesion.png");
		JLabel pDchImagen = new JLabel(imagen);
		pDchArriba.add(pDchImagen);
		
		//Panel Dch. Abajo: Botones "añadir", "eliminar", "editar" y "cerrar"
		JPanel pDchAbajo = new JPanel();
		pCentralDch.add(pDchAbajo);
		pDchAbajo.setLayout(new GridLayout(7, 1 , 0, 0));
		
		JButton btnAnyadirEvento = new JButton("AÑADIR EVENTO");
		pDchAbajo.add(btnAnyadirEvento);
		
		JLabel lblEspacio1 = new JLabel("");
		pDchAbajo.add(lblEspacio1);
		
		JButton btnEliminarEvento = new JButton("ELIMINAR EVENTO");
		pDchAbajo.add(btnEliminarEvento);
		
		JLabel lblEspacio2 = new JLabel("");
		pDchAbajo.add(lblEspacio2);
		
		JButton btnEditarEvento = new JButton("EDITAR EVENTO");
		pDchAbajo.add(btnEditarEvento);
		
		JLabel lblEspacio3 = new JLabel();
		pDchAbajo.add(lblEspacio3);
		
		JButton btnCerrar = new JButton("CERRAR");
		pDchAbajo.add(btnCerrar);
		
		
		btnAnyadirEvento.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		btnEliminarEvento.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
	
		btnEditarEvento.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		btnCerrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaAnterior = new VentanaPrincipal();
				ventanaActual = VentanaAgenda.this;
				JOptionPane.showMessageDialog(null, "Redirigiendo a la agenda", " ", JOptionPane.INFORMATION_MESSAGE);
				ventanaAnterior.setVisible(true);
				ventanaActual.dispose();
			}
		});
		
	}
	
}
