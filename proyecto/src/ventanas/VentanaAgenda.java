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
import java.util.EnumSet;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import clases.BaseDatos;
import clases.Contacto;
import clases.Evento;
import clases.TipoEvento;

public class VentanaAgenda extends JFrame {

	private JPanel contentPane;
	private JFrame ventanaActual, ventanaAnterior;
	private BaseDatos bd;
	
	
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

		//Panel CentralIzq: JTable
		JPanel pCentralIzq = new JPanel();
		pCentral.add(pCentralIzq);
		
				
		modeloTabla = new DefaultTableModel();
		tabla = new JTable(modeloTabla);
		scrollTabla = new JScrollPane(tabla);
		String [] titulos = {"CÓDIGO","NOMBRE","DURACIÓN","ACOMPAÑANTE","COMPLETO"};
		modeloTabla.setColumnIdentifiers(titulos);
		cargarModelo();
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
				//seleccionar fecha en un calendario
				String fecha = ""; //placeholder
				String nombre = JOptionPane.showInputDialog("Introduce el nombre del nuevo evento: ", JOptionPane.QUESTION_MESSAGE);
				Object tipo = JOptionPane.showInputDialog(null, "Elija el tipo de evento", "Nuevo evento", JOptionPane.QUESTION_MESSAGE,
						null, new Object[] {"OCIO", "CLASE"}, "OCIO");
				String duracion = JOptionPane.showInputDialog("Introduce la duración del evento, siguiendo el formato HH:mm: ", JOptionPane.QUESTION_MESSAGE);
				BaseDatos.insertarEvento(BaseDatos.initBD("proyecto.db"), new ArrayList<Contacto>(), VentanaInicioSesion.nombreUsuario(),fecha, nombre,(TipoEvento) tipo, Integer.parseInt(duracion));
				
				JOptionPane.showMessageDialog(null, "El evento se ha creado correctamente", "¡Bien hecho!", JOptionPane.PLAIN_MESSAGE);
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
					  
					BaseDatos.eliminarEvento(VentanaInicioSesion.con, ev[1]);  //seleccionar la posicion dependiendo donde esta el codigo
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
		
		tabla.getModel().addTableModelListener(new TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent e) {
				int fila = e.getFirstRow();
				int col = e.getColumn();
				if(col == 4) {
					String cod = (String) modeloTabla.getValueAt(fila, 0);
					boolean valor = (Boolean) modeloTabla.getValueAt(fila, 4);
					BaseDatos.updateCompleto(VentanaInicioSesion.con, cod, valor);
				}
			}
		});
		
		tabla.setDefaultRenderer(Object.class, (table,value,isSelected,hasFocus,row,column)->{
			JLabel label = new JLabel(value.toString());
			boolean valor = (Boolean) modeloTabla.getValueAt(row, 4);
			if(valor==true) {
				label.setOpaque(true);
				label.setBackground(new Color(112, 219, 147));
			}else {
				label.setOpaque(true);
				label.setBackground(new Color(255, 64, 64));
			}
			return label;
			
		});
		
		tabla.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int col = tabla.getSelectedColumn();
				if(col==4) {
					tabla.repaint();
				}
				
			}
		});
		
		

	}
	
	private void addCheckBox(int col, JTable t) {
		TableColumn tc = t.getColumnModel().getColumn(col);
		tc.setCellEditor(t.getDefaultEditor(Boolean.class));
		tc.setCellRenderer(t.getDefaultRenderer(Boolean.class));
	}
	private void cargarModelo() {
		ArrayList<Evento> a = BaseDatos.obtenerEventosUsuario(VentanaInicioSesion.con, VentanaInicioSesion.nombre);
		int f = 0;
		for(Evento e: a) {
			Object [] fila = {e.getCodigo(),e.getNombre(),e.getDuracion(),e.getUsuario()};
			modeloTabla.addRow(fila);
			addCheckBox(4, tabla);
			modeloTabla.setValueAt(e.isCompleto(), f, 4);
			f++;
		}
	}
}