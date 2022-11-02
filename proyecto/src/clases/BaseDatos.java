package clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
			String sql = "CREATE TABLE IF NOT EXISTS Usuario (nombre String, apellido String, mail String, nomUsuario String, contrasenia String)";
			try {
				Statement st = con.createStatement();
				st.executeUpdate(sql);
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public static void insertarUsuario(Connection con, String nombre, String apellido, String mail, String nomUsuario, String contrasenia) {
			String sql = "INSERT INTO Usuario VALUES('"+nombre+"','"+apellido+"','"+mail+"','"+nomUsuario+"','"+contrasenia+"')";
			try {
				Statement st = con.createStatement();
				st.executeUpdate(sql);
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public static boolean buscarUsuario(Connection con, String nomUsuario) {
			String sql = "SELECT * FROM Usuario WHERE nomUsuario='"+nomUsuario+"'";
			boolean usuarioEncontrado = false;
			try {
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql); //no deja crear usuarios
				if(rs.next()) {
					usuarioEncontrado = true;
				}
				rs.close();
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return usuarioEncontrado;
		}
		
		public static Usuario obtenerDatosUsuario (Connection con, String nomUsuario) {
			String sql = "SELECT * FROM Usuario WHERE nomUsuario='"+nomUsuario+"'";
			Usuario u = null;
			try {
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql);
				if(rs.next()) {
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