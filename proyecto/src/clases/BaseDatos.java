package clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BaseDatos {

		/**
		 * Método que realiza la conexión con la base de datos
		 * @param nombreBD : Nombre de la base de datos a la que nos vamos a conectar
		 * @return Devuelve la conexión a la base de datos
		 */
		public static Connection initBD(String nombreBD) {
			Connection con = null;
			try {
				Class.forName("org.sqlite.JDBC");
				con = DriverManager.getConnection("jdbc:sqlite:"+nombreBD);
						
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return con;
		}
		
		public static void closeBD(Connection con) {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		public static void crearTablas(Connection con) {
			String sql1 = "CREATE TABLE IF NOT EXISTS usuario (nombre String, apellido String, mail String, nomUsuario String, contrasenia String)";
			String sql2 = "CREATE TABLE IF NOT EXISTS evento (codigo String, usuario String,  fecha String, nombre String, tipo String, duracion Integer, completo String)";
			String sql3 = "CREATE TABLE IF NOT EXISTS contacto (codEvento String, nombre String, mail String, telf String, favorito String)";
			try (Statement st = con.createStatement();){
				st.executeUpdate(sql1);
				st.executeUpdate(sql2);
				st.executeUpdate(sql3);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public static void insertarUsuario(Connection con, String nombre, String apellido, String mail, String nomUsuario, String contrasenia) {
			String sql = "INSERT INTO usuario VALUES('"+nombre+"','"+apellido+"','"+mail+"','"+nomUsuario+"','"+contrasenia+"')";
			try (Statement st = con.createStatement();){
				st.executeUpdate(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public static void insertarEvento(Connection con, int codigo, String usuario, String fecha, String nombre, String tipo, int duracion, String completo) {
			String sql = "INSERT INTO evento VALUES('"+codigo+"','"+usuario+"','"+fecha+"','"+nombre+"','"+tipo+"','"+duracion+"','"+completo+"')";
			try (Statement st = con.createStatement();){
				st.executeUpdate(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public static void insertarContacto(Connection con, String codEvento, String nombre, String mail, String telf, boolean favorito) {
			String sql = "INSERT INTO evento VALUES('"+codEvento+"','"+nombre+"','"+mail+"','"+telf+"','"+favorito+"')";
			try (Statement st = con.createStatement();){
				st.executeUpdate(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public static boolean buscarUsuario(Connection con, String nomUsuario) {
			String sql = "SELECT * FROM usuario WHERE nomUsuario='"+nomUsuario+"'";
			boolean usuarioEncontrado = false;
			try (Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql);){
				while(rs.next()) {
					usuarioEncontrado = true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return usuarioEncontrado;
		}
		
		public static Usuario obtenerDatosUsuario (Connection con, String nomUsuario) {
			String sql = "SELECT * FROM usuario WHERE nomUsuario='"+nomUsuario+"'";
			Usuario u = null;
			try (Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql);){
				while(rs.next()) {
					String n = rs.getString("nombre");
					String a = rs.getString("apellido");
					String m = rs.getString("mail");
					String nu = rs.getString("nomUsuario");
					String c = rs.getString("contrasenia");
					u = new Usuario(n, a, m, nu, c);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return u;
		}
		
		
		public static ArrayList<Evento> obtenerEventosUsuario (Connection con, String usuario) {
			String sql = "SELECT * FROM evento WHERE usuario='"+usuario+"'";
			ArrayList<Evento> ale = new ArrayList<>();
			try (Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql);){
				while(rs.next()) {
					int c = rs.getInt("codigo");
					String u = rs.getString("usuario");
					String fecha = rs.getString("fecha");
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					Date f = sdf.parse(fecha);
					String n = rs.getString("nombre");
					String t = rs.getString("tipo");
					int d = rs.getInt("duracion");
					String v = rs.getString("completo");
					boolean com = false;
					if(v.equals("true"))
						com = true;
					Evento ev = new Evento(c, u, f, n, TipoEvento.valueOf(t), d,com);
					ale.add(ev);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return ale;
		}
		
		public static ArrayList<Evento> obtenerEventosFechaUsuario (Connection con, String us, String fe) {
			String sql = "SELECT * FROM evento WHERE fecha='"+fe+"' and usuario='"+us+"'";
			ArrayList<Evento> ale = new ArrayList<>();
			try (Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql);){
				while(rs.next()) {
					int c = rs.getInt("codigo");
					String u = rs.getString("usuario");
					String fecha = rs.getString("fecha");
					Date f = Utilidades.sdf.parse(fecha);
					String n = rs.getString("nombre");
					String t = rs.getString("tipo");
					int d = rs.getInt("duracion");
					String v = rs.getString("completo");
					boolean com = false;
					if(v.equals("true"))
						com = true;
					Evento ev = new Evento(c, u, f, n, TipoEvento.valueOf(t), d, com);
					ale.add(ev);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return ale;
		}
		
		public static Contacto obtenerDatosContacto (Connection con, String nombre) {
			String sql = "SELECT * FROM contacto WHERE nombre='"+nombre+"'";
			Contacto c = null;
			try (Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql);){
				while(rs.next()) {
					String ce = rs.getString("codEvento");
					String n = rs.getString("nombre");
					String m = rs.getString("mail");
					String t = rs.getString("telf");
					boolean b = rs.getBoolean("favorito");
					c = new Contacto(ce, n, m, t, b);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return c;
		}
		
		public static ArrayList<Contacto> obtenerContactosUsuario (Connection con, String usuario) {
			String sql = "SELECT * FROM contacto WHERE usuario='"+usuario+"'";
			ArrayList<Contacto> alc = new ArrayList<>();
			try (Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql);){
				while(rs.next()) {
					String ce = rs.getString("codEvento");
					String n = rs.getString("nombre");
					String m = rs.getString("mail");
					String t = rs.getString("telf");
					boolean b = rs.getBoolean("favorito");
					Contacto c = new Contacto(ce, n, m, t, b);
					alc.add(c);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			return alc;
		}
		
		public static void eliminarUsuario(Connection con, String nomUsu) {
			String sentSQL = "DELETE FROM usuario WHERE nomUsuario ='"+nomUsu+"'";
			try {
				Statement stmt = con.createStatement();
				stmt.executeUpdate(sentSQL);
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public static void eliminarContacto(Connection con, String nombre) {
			String sentSQL = "DELETE FROM contacto WHERE nombre ='"+nombre+"'";
			try {
				Statement stmt = con.createStatement();
				stmt.executeUpdate(sentSQL);
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public static void eliminarEvento(Connection con, String codigo) {
			String sentSQL = "DELETE FROM evento WHERE codigo ='"+codigo+"'";
			try {
				Statement stmt = con.createStatement();
				stmt.executeUpdate(sentSQL);
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public static void updateCompleto(Connection con, String codigo, boolean estado) {
			String sentSQL;
			if(estado)
			 sentSQL= "UPDATE evento SET completo='true' WHERE codigo ='"+codigo+"'";
			else
				sentSQL= "UPDATE evento SET completo='false' WHERE codigo ='"+codigo+"'";
			try {
				Statement stmt = con.createStatement();
				stmt.executeUpdate(sentSQL);
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public static boolean getCompleto(Connection con, String codigo) {
			String sentSQL = "SELECT completo FROM evento WHERE codigo='"+codigo+"'";
			boolean completo = false;
			try {
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sentSQL);
				if(rs.next()) {
					String com = rs.getString("completo");
					if(com.equals("true"))
						completo = true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return completo;
		}
}