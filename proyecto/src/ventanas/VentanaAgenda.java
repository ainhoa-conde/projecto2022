package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import clases.BaseDatos;

public class VentanaAgenda extends JFrame{

	private JPanel contentPane;
	private JFrame ventanaActual, ventanaAnterior;
	
	private Connection con;
	
	private JTable tabla;
	private DefaultTableModel modeloTabla;
	private JScrollPane scrollTabla;
	
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
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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

		//Panel CentralIzq: JTable eventos
		JPanel pCentralIzq = new JPanel();
		pCentral.add(pCentralIzq);
		
		modeloTabla = new DefaultTableModel();
		tabla = new JTable(modeloTabla);
		scrollTabla = new JScrollPane(tabla);
		pCentralIzq.add(scrollTabla);
		
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
		
		//Eventos
		
		btnAnyadirEvento.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaAnterior = VentanaAgenda.this;
				ventanaActual = new VentanaEvento();
				ventanaActual.setVisible(true);
				ventanaAnterior.dispose();
				}
		});
		
		
		btnEliminarEvento.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JLabel aviso = new JLabel("Haz click en el evento que quieres eliminar");
				pCentral.add(aviso);
				//hacer que se actualice la ventana
				
				
				
			}
		});
		
		btnEliminarEvento.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2) {
					String evento = getComponentAt(getMousePosition()).toString();
					String[] ev = evento.split("");  //depende como aparezca el evento cambiamos la separacion y la posicion del nombre
					//Joptionpane confirmacion y meter la llamada a eliminarevento en el if
					  
					BaseDatos.eliminarEvento(con, ev[1]);  //seleccionar la posicion dependiendo donde esta el codigo
				}
				
			}
		});
	
		btnEditarEvento.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		
		btnCerrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaAnterior = new VentanaPrincipal();
				ventanaActual = VentanaAgenda.this;
				ventanaAnterior.setVisible(true);
				ventanaActual.dispose();
			}
		});
		
	}
}
