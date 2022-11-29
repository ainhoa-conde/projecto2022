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
		public Connection initBD(String nombreBD) {
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
		
		public void closeBD(Connection con) {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		public void crearTablas(Connection con) {
			String sql1 = "CREATE TABLE IF NOT EXISTS Usuario (nombre String, apellido String, mail String, nomUsuario String, contrasenia String)";
			String sql2 = "CREATE TABLE IF NOT EXISTS Evento (codigo String, usuario String,  fecha String, nombre String, tipo String, duracion Integer)";
			String sql3 = "CREATE TABLE IF NOT EXISTS Contacto (codEvento String, nombre String, mail String, telf String, favorito Boolean)";
			try (Statement st = con.createStatement();){
				st.executeUpdate(sql1);
				st.executeUpdate(sql2);
				st.executeUpdate(sql3);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void insertarUsuario(Connection con, String nombre, String apellido, String mail, String nomUsuario, String contrasenia) {
			String sql = "INSERT INTO Usuario VALUES('"+nombre+"','"+apellido+"','"+mail+"','"+nomUsuario+"','"+contrasenia+"')";
			try (Statement st = con.createStatement();){
				st.executeUpdate(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void insertarEvento(Connection con, ArrayList<Contacto> contacto,String usuario, String fecha, String nombre, TipoEvento tipo, int duracion) {
			String sql = "INSERT INTO Evento VALUES('"+contacto+"','"+usuario+"','"+fecha+"','"+nombre+"','"+tipo+"','"+duracion+"')";
			try (Statement st = con.createStatement();){
				st.executeUpdate(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void insertarContacto(Connection con, String codEvento, String nombre, String mail, String telf, boolean favorito) {
			String sql = "INSERT INTO Evento VALUES('"+codEvento+"','"+nombre+"','"+mail+"','"+telf+"','"+favorito+"')";
			try (Statement st = con.createStatement();){
				st.executeUpdate(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public boolean buscarUsuario(Connection con, String nomUsuario) {
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
		
		public Usuario obtenerDatosUsuario (Connection con, String nomUsuario) {
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
		
		
		public Evento obtenerDatosEvento (Connection con, String codigo) {
			String sql = "SELECT * FROM Evento WHERE codigo='"+codigo+"'";
			Evento ev = null;
			try (Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql);){
				while(rs.next()) {
					String c = rs.getString("codigo");
					String u = rs.getString("usuario");
					String fecha = rs.getString("fecha");
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					Date f = sdf.parse(fecha);
					String n = rs.getString("nombre");
					String t = rs.getString("tipo");
					int d = rs.getInt("duracion");
					if(t.equals(TipoEvento.CLASE)) {
						TipoEvento te = TipoEvento.CLASE;
						ev = new Evento(c, u, f, n, te, d);
					} else if(t.equals(TipoEvento.OCIO)) {
						TipoEvento te = TipoEvento.OCIO;
						ev = new Evento(c, u, f, n, te, d);
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return ev;
		}
		
		public Contacto obtenerDatosContacto (Connection con, String nombre) {
			String sql = "SELECT * FROM Contacto WHERE nombre='"+nombre+"'";
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
		
		public void eliminarUsuario(Connection con, String nomUsu) {
			String sentSQL = "DELETE FROM Usuario WHERE nomUsu ='"+nomUsu+"'";
			try {
				Statement stmt = con.createStatement();
				stmt.executeUpdate(sentSQL);
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void eliminarContacto(Connection con, String nombre) {
			String sentSQL = "DELETE FROM Contacto WHERE nombre ='"+nombre+"'";
			try {
				Statement stmt = con.createStatement();
				stmt.executeUpdate(sentSQL);
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void eliminarEvento(Connection con, String codigo) {
			String sentSQL = "DELETE FROM Evento WHERE codigo ='"+codigo+"'";
			try {
				Statement stmt = con.createStatement();
				stmt.executeUpdate(sentSQL);
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}