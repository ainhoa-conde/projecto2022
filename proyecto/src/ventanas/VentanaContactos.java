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
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import clases.BaseDatos;
import clases.Contacto;


public class VentanaContactos extends JFrame{

	private JPanel contentPane;
	private JFrame ventanaActual, ventanaAnterior;
	
	private JTable tabla;
	private DefaultTableModel modeloTabla;
	private JScrollPane scrollTabla;
	
	private int filaSeleccionada = -1;
	
	private ArrayList<Contacto> al;
	
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
		setBounds(100, 100, 1000, 560);
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
		
		//Panel Drch
		JPanel pCentralDrch = new JPanel();
		pCentral.add(pCentralDrch);
				
		//Panel Drch. Arriba: Lista de contactos
		JPanel pDrchArriba = new JPanel();
		pCentralDrch.add(pDrchArriba);
				
		modeloTabla = new DefaultTableModel();
		tabla = new JTable(modeloTabla);
		scrollTabla = new JScrollPane(tabla);
		String [] titulos = {"NOMBRE", "MAIL", "TELÉFONO"};
		modeloTabla.setColumnIdentifiers(titulos);
		cargarModelo();
		pCentralDrch.add(scrollTabla);
		
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
				String nombre = JOptionPane.showInputDialog("Introduce el nombre del nuevo contacto: ", JOptionPane.QUESTION_MESSAGE);
				boolean existeContacto = BaseDatos.buscarContacto(VentanaInicioSesion.con, nombre);
				if(existeContacto) {
					JOptionPane.showMessageDialog(null, "Ya existe un contacto con ese nombre", "ERROR", JOptionPane.ERROR_MESSAGE);
				}else {
					String mail = JOptionPane.showInputDialog("Introduce el mail del contacto: ", JOptionPane.QUESTION_MESSAGE);
					boolean existeMail = BaseDatos.buscarMailContacto(VentanaInicioSesion.con, mail);
					if(existeMail) {
						JOptionPane.showMessageDialog(null, "Ya existe un contacto con ese mail", "ERROR", JOptionPane.ERROR_MESSAGE);
					} else {
						String telefono = JOptionPane.showInputDialog("Introduce el telefono del contacto: ", JOptionPane.QUESTION_MESSAGE);
						boolean existeTelf = BaseDatos.buscartelefonoContacto(VentanaInicioSesion.con, telefono);
						if(existeTelf) {
							JOptionPane.showMessageDialog(null, "Ya existe un contacto con ese teléfono", "ERROR", JOptionPane.ERROR_MESSAGE);
						} else {
							int fav = JOptionPane.showConfirmDialog(null, "¿Quieres añadir este contacto a tus favoritos?");
							String favorito;
							if(JOptionPane.OK_OPTION == fav) {
								favorito = "true";
								BaseDatos.insertarContacto(VentanaInicioSesion.con, VentanaInicioSesion.nombre, nombre, mail, telefono, favorito);
								JOptionPane.showMessageDialog(null, "El contacto se ha creado correctamente", "¡Bien hecho!", JOptionPane.PLAIN_MESSAGE);
							
							} else {
								favorito = "false";
								BaseDatos.insertarContacto(VentanaInicioSesion.con, VentanaInicioSesion.nombre, nombre, mail, telefono, favorito);
								JOptionPane.showMessageDialog(null, "El contacto se ha creado correctamente", "¡Bien hecho!", JOptionPane.PLAIN_MESSAGE);
							
							}
						}
					}
				}
			}
		});

		btnEliminarContacto.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(filaSeleccionada!=-1) {
					String nombre = (String) modeloTabla.getValueAt(filaSeleccionada, 0);
					modeloTabla.removeRow(filaSeleccionada);
					BaseDatos.eliminarContacto(VentanaInicioSesion.con, nombre);
				}
			}
		});
		
		
		btnEditarContacto.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String nombre = JOptionPane.showInputDialog("Introduce el nuevo nombre del nuevo contacto: ", JOptionPane.QUESTION_MESSAGE);
				boolean existeContacto = BaseDatos.buscarContacto(VentanaInicioSesion.con, nombre);
				if(existeContacto) {
					JOptionPane.showMessageDialog(null, "Ya existe un contacto con ese nombre", "ERROR", JOptionPane.ERROR_MESSAGE);
				}else {
					String mail = JOptionPane.showInputDialog("Introduce el mail del contacto: ", JOptionPane.QUESTION_MESSAGE);
					boolean existeMail = BaseDatos.buscarMailContacto(VentanaInicioSesion.con, mail);
					if(existeMail) {
						JOptionPane.showMessageDialog(null, "Ya existe un contacto con ese mail", "ERROR", JOptionPane.ERROR_MESSAGE);
					} else {
						String telefono = JOptionPane.showInputDialog("Introduce el telefono del contacto: ", JOptionPane.QUESTION_MESSAGE);
						boolean existeTelf = BaseDatos.buscartelefonoContacto(VentanaInicioSesion.con, telefono);
						if(existeTelf) {
							JOptionPane.showMessageDialog(null, "Ya existe un contacto con ese teléfono", "ERROR", JOptionPane.ERROR_MESSAGE);
						} else {
							int fav = JOptionPane.showConfirmDialog(null, "¿Quieres añadir este contacto a tus favoritos?");
							String favorito;
							if(JOptionPane.OK_OPTION == fav) {
								favorito = "true";
								BaseDatos.updateContacto(VentanaInicioSesion.con, al.get(filaSeleccionada).getCodigo(), nombre, mail, telefono, favorito);
								JOptionPane.showMessageDialog(null, "El contacto se ha actualizado correctamente", "¡Bien hecho!", JOptionPane.PLAIN_MESSAGE);
							
							} else {
								favorito = "false";
								BaseDatos.updateContacto(VentanaInicioSesion.con, al.get(filaSeleccionada).getCodigo(), nombre, mail, telefono, favorito);
								JOptionPane.showMessageDialog(null, "El contacto se ha actualizado correctamente", "¡Bien hecho!", JOptionPane.PLAIN_MESSAGE);
							
							}
						}
					}
				}
				while(modeloTabla.getRowCount()>0) {
					modeloTabla.removeRow(0);
				}
				cargarModelo();
			}
		});
		
		tabla.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				filaSeleccionada = tabla.getSelectedRow();
			}
		});
		
		tabla.setDefaultRenderer(Object.class, (table,value,isSelected,hasFocus,row,column)->{
			JLabel label = new JLabel(value.toString());
			boolean valor = al.get(row).isFavorito();
			if(valor==true) {
				label.setOpaque(true);
				label.setBackground(new Color(201, 100, 217));
			}
			return label;
			
		});
		
	}
	
	//Función que carga los contactoa a la lista
	private void cargarModelo() {
		al = BaseDatos.obtenerContactosUsuario(VentanaInicioSesion.con, VentanaInicioSesion.nombre);
		for(Contacto c: al) {
			Object [] fila = {c.getNombre(), c.getMail(), c.getTelf()};
			modeloTabla.addRow(fila);
		}
	}
	
}
