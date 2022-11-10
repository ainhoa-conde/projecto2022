package clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
			String sql1 = "CREATE TABLE IF NOT EXISTS Usuario (nombre String, apellido String, mail String, nomUsuario String, contrasenia String)";
			String sql2 = "CREATE TABLE IF NOT EXISTS Evento (usuario String, contacto ArrayList<Contacto>, fecha String, nombre String, tipo TipoEvento, duracion Integer)";
			try (Statement st = con.createStatement();){
				st.executeUpdate(sql1);
				st.executeUpdate(sql2); //no crea tabla
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public static void insertarUsuario(Connection con, String nombre, String apellido, String mail, String nomUsuario, String contrasenia) {
			String sql = "INSERT INTO Usuario VALUES('"+nombre+"','"+apellido+"','"+mail+"','"+nomUsuario+"','"+contrasenia+"')";
			try (Statement st = con.createStatement();){
				st.executeUpdate(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public static void insertarEvento(Connection con, ArrayList<Contacto> contacto,String usuario, String fecha, String nombre, TipoEvento tipo, int duracion) {
			String sql = "INSERT INTO Evento VALUES('"+contacto+"','"+usuario+"','"+fecha+"','"+nombre+"','"+tipo+"','"+duracion+"')";
			try (Statement st = con.createStatement();){
				st.executeUpdate(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public static boolean buscarUsuario(Connection con, String nomUsuario) {
			String sql = "SELECT * FROM Usuario WHERE nomUsuario='"+nomUsuario+"'";
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
			String sql = "SELECT * FROM Usuario WHERE nomUsuario='"+nomUsuario+"'";
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
		
		public static Evento obtenerDatosEvento (Connection con, String nombre) {
			String sql = "SELECT * FROM Evento WHERE nombre='"+nombre+"'";
			Evento ev = null;
			try (Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql);){
				while(rs.next()) {
					ArrayList<Contacto> c = (ArrayList<Contacto>) rs.getArray("contacto");
					String u = rs.getString("usuario");
					String f = rs.getString("fecha");
					String n = rs.getString("nombre");
					//TipoEvento t = (TipoEvento)rs.getString("tipo");
					int d = rs.getInt("duracion");
					//ev = new Evento(c, u, f, n, t, d);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return ev;
		}
		
		/*public static void eliminarUsuario(Connection con, String nomUsu) {
			String sentSQL = "DELETE FROM alumno WHERE nomUsu ='"+nomUsu+"'";
			try {
				Statement stmt = con.createStatement();
				stmt.executeUpdate(sentSQL);
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/

}