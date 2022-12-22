package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import clases.BaseDatos;
import clases.Evento;

public class VentanaAgenda extends JFrame{

	private JPanel contentPane;
	private JFrame ventanaActual, ventanaAnterior;
	
	
	private JTable tabla;
	private DefaultTableModel modeloTabla;
	private JScrollPane scrollTabla;
	
	private int fila=-1, columna = -1;
	private String codigo="";
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
		
		/*class MyTableCellRenderer extends JCheckBox implements TableCellRenderer {
		    @Override
		    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		       return this;
		    }

		}*/
		
		modeloTabla = new DefaultTableModel();
		tabla = new JTable(modeloTabla);
		scrollTabla = new JScrollPane(tabla);
		String [] titulos = {"CÓDIGO","NOMBRE","DURACIÓN","ACOMPAÑANTE","COMPLETO"};
		modeloTabla.setColumnIdentifiers(titulos);
		TableColumnModel cm = tabla.getColumnModel();
		
		/*cm.addColumn(new TableColumn(0, 10, new MyTableCellRenderer(), null));
		cm.moveColumn(cm.getColumnCount() - 1, 0);*/
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
		
		tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				if(column == 4) {
					String codigo = (String)modeloTabla.getValueAt(row, 0);
					boolean completo = BaseDatos.getCompleto(VentanaInicioSesion.con, codigo);
					System.out.println(codigo+" "+completo);
					JCheckBox cb = new JCheckBox();
					if(row==fila && column == columna) {
						System.out.println("CAMBIA");
						if(cb.isSelected()) {
							cb.setSelected(false);
							BaseDatos.updateCompleto(VentanaInicioSesion.con,codigo,false);
						}
						else {
							cb.setSelected(true);
							BaseDatos.updateCompleto(VentanaInicioSesion.con,codigo,true);
						}
						
					}
					c = cb;
				}
				
				return c;
			}
		});
		
		tabla.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				fila = tabla.rowAtPoint(e.getPoint());
				columna = tabla.columnAtPoint(e.getPoint());
				codigo = (String)modeloTabla.getValueAt(fila, 0);
				System.out.println("CLICK EN: "+fila+"-"+columna);
				tabla.repaint();
			}
		});
		
	}
	
	private void cargarModelo() {
		ArrayList<Evento> a = BaseDatos.obtenerEventosUsuario(VentanaInicioSesion.con, VentanaInicioSesion.nombre);
		for(Evento e: a) {
			Object [] fila = {e.getCodigo(),e.getNombre(),e.getDuracion(),e.getUsuario(),new JCheckBox()};
			modeloTabla.addRow(fila);
		}
	}
}
