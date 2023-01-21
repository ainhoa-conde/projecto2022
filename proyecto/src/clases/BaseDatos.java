package clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseDatos {
	
	private static Logger logger = Logger.getLogger("BaseDatos");

		/**
		 * Método que realiza la conexión con la base de datos
		 * @param nombreBD : Nombre de la base de datos a la que nos vamos a conectar
		 * @return Devuelve la conexión a la base de datos
		 */
		public static Connection initBD(String nombreBD) {
			Connection con = null;
			try {
				logger.log(Level.INFO, "Carga de librería org.sqlite.JDBC");
				Class.forName("org.sqlite.JDBC");
				logger.log(Level.INFO, "Abriendo conexión con " + nombreBD);
				con = DriverManager.getConnection("jdbc:sqlite:"+nombreBD);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				logger.log(Level.SEVERE, "Excepción", e);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.log(Level.SEVERE, "Excepcón", e);
			}
			
			return con;
		}
		
		/** Cierra la base de datos
		 * @param con	Conexion abierta de la base de datos
		 */
		
		public static void closeBD(Connection con) {
			if(con!=null) {
				try {
					logger.log(Level.INFO, "Cerrando conexión");
					con.close();
				} catch (SQLException e) {
					logger.log(Level.SEVERE, "Excepción", e);
				}
			}
		}
		
		/**
		 * Crea las tablas de la base de datos si no existen todavia.
		 * @param con	Conexion creada con la base de datos.
		 */
		public static void crearTablas(Connection con) {
			String sql1 = "CREATE TABLE IF NOT EXISTS usuario (nombre String, apellido String, mail String, nomUsuario String, contrasenia String)";
			String sql2 = "CREATE TABLE IF NOT EXISTS evento (codigo String, usuario String,  fecha String, nombre String, tipo String, duracion Integer, completo String)";
			String sql3 = "CREATE TABLE IF NOT EXISTS contacto (codigo String, usuario String, nombre String, mail String, telf String, favorito String)";
			String sql4 = "CREATE TABLE IF NOT EXISTS tipo (nombre String, usuario String)";
			try (Statement st = con.createStatement();){
				logger.log(Level.INFO, "Statement: " + sql1);
				st.executeUpdate(sql1);
				
				logger.log(Level.INFO, "Statement: " + sql2);
				st.executeUpdate(sql2);
				
				logger.log(Level.INFO, "Statement: " + sql3);
				st.executeUpdate(sql3);
				
				logger.log(Level.INFO, "Statement: " + sql4);
				st.executeUpdate(sql4);
			} catch (SQLException e) {
				logger.log(Level.SEVERE, "Excepción", e);
			}
		}
		
		/**
		 * Consigue el mayor codigo entre los eventos de la base de datos.
		 * @param con	Conexion con la base de datos
		 * @return max	Codigo mayor de los eventos
		 */
		public static int getMaxCodigoEvento(Connection con) {
			String sql = "SELECT MAX(codigo) FROM evento";
			logger.log(Level.INFO, "Statement: " + sql);
			int max = 0;
			try (Statement st = con.createStatement();){
				ResultSet rs = st.executeQuery(sql);
				if(rs.next()) {
					max = rs.getInt(1);
				}
			} catch (SQLException e) {
				logger.log(Level.SEVERE, "Excepción", e);
			}
			return max;
		}
		
		
		/**
		 * Consigue el mayor codigo entre los contactos de la base de datos.
		 * @param con	Conexion con la base de datos
		 * @return max	Codigo mayor de los contactos
		 */
		public static int getMaxCodigoContacto(Connection con) {
			String sql = "SELECT MAX(codigo) FROM contacto";
			logger.log(Level.INFO, "Statement: " + sql);
			int max = 0;
			try (Statement st = con.createStatement();){
				ResultSet rs = st.executeQuery(sql);
				if(rs.next()) {
					max = rs.getInt(1);
				}
			} catch (SQLException e) {
				logger.log(Level.SEVERE, "Excepción", e);
			}
			return max;
		}
		
		/**
		 * Añade un usuario a la tabla Usuario, usando la sentencia INSERT de SQL desde los datos introducidos por el usuario
		 * @param con	Conexion a la base de datos
		 * @param nombre	Nombre del usuario
		 * @param apellido	Apellido del usuario
		 * @param mail		Direccion de correo electronico del usuario
		 * @param nomUsuario	"Nickname" del usuario
		 * @param contrasenia	Contraseña para acceder a la cuenta
		 */
		
		public static void insertarUsuario(Connection con, String nombre, String apellido, String mail, String nomUsuario, String contrasenia) {
			String sql = "INSERT INTO usuario VALUES('"+nombre+"','"+apellido+"','"+mail+"','"+nomUsuario+"','"+contrasenia+"')";
			logger.log(Level.INFO, "Statement: " + sql);
			try (Statement st = con.createStatement();){
				st.executeUpdate(sql);
			} catch (SQLException e) {
				logger.log(Level.SEVERE, "Excepción", e);
			}
		}
		
		
		/**
		 * Añade un evento a la tabla Evento, usando la sentencia INSERT de SQL desde los datos introducidos por el usuario
		 * @param con	Conexion con la base de datos
		 * @param usuario	"Nickname" del usuario que crea el evento
		 * @param fecha		Fecha en la que ocurre el evento
		 * @param nombre	Nombre del evento
		 * @param tipo		Tipo de evento
		 * @param duracion	Duracion del evento
		 * @param completo	Marcador para saber si el evento se ha completado o no
		 */
		
		public static void insertarEvento(Connection con ,String usuario, String fecha, String nombre, String tipo, int duracion, String completo) {
			int codigo = getMaxCodigoEvento(con) + 1;
			String sql = "INSERT INTO evento VALUES('"+codigo+"','"+usuario+"','"+fecha+"','"+nombre+"','"+tipo+"','"+duracion+"','"+completo+"')";
			logger.log(Level.INFO, "Statement: " + sql);
			try (Statement st = con.createStatement();){
				st.executeUpdate(sql);
			} catch (SQLException e) {
				logger.log(Level.SEVERE, "Excepción", e);
			}
		}
		
		/**
		 * Añade un contacto a la tabla Contacto, usando la sentencia INSERT de SQL desde los datos introducidos por el usuario
		 * @param con	Conexion con la base de datos
		 * @param usuario	"Nickname" del usuario que crea el contacto
		 * @param nombre	Nombre del contacto
		 * @param mail		Correo electronico del contacto
		 * @param telf		Telefono del contacto
		 * @param favorito	Marcar si es un contacto favorito o no
		 */
		
		public static void insertarContacto(Connection con, String usuario, String nombre, String mail, String telf, String favorito) {
			int codigo = getMaxCodigoContacto(con) + 1;
			String sql = "INSERT INTO contacto VALUES('"+codigo+"','"+usuario+"','"+nombre+"','"+mail+"','"+telf+"','"+favorito+"')";
			logger.log(Level.INFO, "Statement: " + sql);
			try (Statement st = con.createStatement();){
				st.executeUpdate(sql);
			} catch (SQLException e) {
				logger.log(Level.SEVERE, "Excepción", e);
			}
		}
		
		/**
		 * Añade un tipo de evento a la tabla Tipo, usando la sentencia INSERT de SQL desde los datos introducidos por el usuario
		 * @param con	Conexion con la base de datos
		 * @param nombre	Nombre del tipo de evento a añadir
		 * @param usuario	"Nickname" del usuario que crea el tipo de evento
		 */
		
		public static void insertarTipoEvento(Connection con, String nombre, String usuario) {
			String sql = "INSERT INTO tipo VALUES('"+nombre+"','"+usuario+"')";
			logger.log(Level.INFO, "Statement: " + sql);
			try (Statement st = con.createStatement();){
				st.executeUpdate(sql);
			} catch (SQLException e) {
				logger.log(Level.SEVERE, "Excepción", e);
			}
		}
		
		/**
		 * Busca un usuario en la tabla Usuario, usando la sentencia SELECT de SQL y el "nickname" o nombre de usuario
		 * @param con	Conexion con la base de datos
		 * @param nomUsuario	"Nickname" del usuario
		 * @return usuarioEncontrado	True si se ha encontrado un usuario con ese nick, si no false
		 */
		
		public static boolean buscarUsuario(Connection con, String nomUsuario) {
			String sql = "SELECT * FROM usuario WHERE nomUsuario='"+nomUsuario+"'";
			logger.log(Level.INFO, "Statement: " + sql);
			boolean usuarioEncontrado = false;
			try (Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql);){
				while(rs.next()) {
					usuarioEncontrado = true;
				}
			} catch (SQLException e) {
				logger.log(Level.SEVERE, "Excepción", e);
			}
			return usuarioEncontrado;
		}
		
		
		/**
		 * Busca un usuario en la tabla Usuario, usando la sentencia SELECT de SQL y el correo del usuario
		 * @param con	Conexion con la base de datos
		 * @param mail	Correo electronico del usuario
		 * @return mailEncontrado True si se ha encontrado un usuario con ese correo, si no false
		 */
		
		public static boolean buscarMailUsuario(Connection con, String mail) {
			String sql = "SELECT * FROM usuario WHERE mail='"+mail+"'";
			logger.log(Level.INFO, "Statement: " + sql);
			boolean mailEncontrado = false;
			try (Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql);){
				while(rs.next()) {
					mailEncontrado = true;
				}
			} catch (SQLException e) {
				logger.log(Level.SEVERE, "Excepción", e);
			}
			return mailEncontrado;
		}
		
		/**
		 * Busca un contacto en la tabla Contacto, usando la sentencia SELECT de SQL y el telefono del contacto
		 * @param con	Conexion con la base de datos
		 * @param telf	Numero de telefono del contacto
		 * @return telfEncontrado	True si se ha encontrado un contacto con ese telefono, si no false
		 */
		
		public static boolean buscartelefonoContacto(Connection con, String telf) {
			String sql = "SELECT * FROM contacto WHERE telf='"+telf+"'";
			logger.log(Level.INFO, "Statement: " + sql);
			boolean telfEncontrado = false;
			try (Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql);){
				while(rs.next()) {
					telfEncontrado = true;
				}
			} catch (SQLException e) {
				logger.log(Level.SEVERE, "Excepción", e);
			}
			return telfEncontrado;
		}
		
		/**
		 * Obten un usuario de la tabla Usuario, usando la sentencia SELECT de SQL y el "nickname" o nombre del usuario
		 * @param con	Conexion con la base de datos
		 * @param nomUsuario	"Nickname" del usuario
		 * @return u	Nuevo usuario con los datos de la tabla, con el mismo "nick" que el introducido
		 */
		
		public static Usuario obtenerDatosUsuario (Connection con, String nomUsuario) {
			String sql = "SELECT * FROM usuario WHERE nomUsuario='"+nomUsuario+"'";
			logger.log(Level.INFO, "Statement: " + sql);
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
				logger.log(Level.SEVERE, "Excepción", e);
			}
			
			return u;
		}
		
		
		/**
		 * Devuelve una lista de eventos desde la tabla Evento, usando la sentencia SELECT de SQL y el usuario que ha creado los eventos
		 * @param con	Conexion con la base de datos
		 * @param usuario	"Nickname" del usuario que ha creado los eventos
		 * @return ale	ArrayList con todos los eventos de un usuario
		 */
		
		public static ArrayList<Evento> obtenerEventosUsuario (Connection con, String usuario) {
			String sql = "SELECT * FROM evento WHERE usuario='"+usuario+"' ORDER BY fecha";
			logger.log(Level.INFO, "Statement: " + sql);
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
					Evento ev = new Evento(c, u, f, n, t, d,com);
					ale.add(ev);
				}
			} catch (SQLException e) {
				logger.log(Level.SEVERE, "Excepción", e);
			} catch (ParseException e) {
				logger.log(Level.SEVERE, "Excepción", e);
			}
			
			return ale;
		}
		
		/**
		 * Devuelve una lista de eventos desde la tabla Evento, usando la sentencia SELECT de SQL y el usuario que ha creado los eventos
		 * @param con	Conexion con la base de datos
		 * @param usuario	"Nickname" del usuario que ha creado los eventos
		 * @return ale	ArrayList con todos los eventos de un usuario
		 */
		
		public static ArrayList<Evento> obtenerEventosFechaUsuario (Connection con, String us, String fe) {
			String sql = "SELECT * FROM evento WHERE fecha='"+fe+"' and usuario='"+us+"'";
			logger.log(Level.INFO, "Statement: " + sql);
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
					Evento ev = new Evento(c, u, f, n, t, d, com);
					ale.add(ev);
				}
			} catch (SQLException e) {
				logger.log(Level.SEVERE, "Excepción", e);
			} catch (ParseException e) {
				logger.log(Level.SEVERE, "Excepción", e);
			}
			return ale;
		}
		
		
		/**
		 * Devuelve un contacto desde la tabla Contacto, usando la sentencia SELECT de SQL y el nombre del contacto
		 * @param con	Conexion con la base de datos
		 * @param nombre	Nombre del contacto
		 * @return c	Contacto creado con los datos de la base de datos
		 */
		
		public static Contacto obtenerDatosContacto (Connection con, String nombre) {
			String sql = "SELECT * FROM contacto WHERE nombre='"+nombre+"'";
			logger.log(Level.INFO, "Statement: " + sql);
			Contacto c = null;
			try (Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql);){
				while(rs.next()) {
					int cod = rs.getInt("codigo");
					String u = rs.getString("usuario");
					String n = rs.getString("nombre");
					String m = rs.getString("mail");
					String t = rs.getString("telf");
					String b = rs.getString("favorito");
					boolean fav = false;
					if(b.equals("true"))
						fav = true;
					c = new Contacto(cod, u, n, m, t, fav);
				}
			} catch (SQLException e) {
				logger.log(Level.SEVERE, "Excepción", e);
			}
			
			return c;
		}
		
		
		/**
		 * Devuelve una lista de contactos de la tabla Contacto, usando la sentencia SELECT de SQL y el nombre de usuario que los ha creado
		 * @param con	Conexion con la base de datos
		 * @param usuario	Nombre del usuario que ha creado los contacto
		 * @return	alc 	ArrayList con todos los contactos de un usuario
		 */
		public static ArrayList<Contacto> obtenerContactosUsuario (Connection con, String usuario) {
			String sql = "SELECT * FROM contacto WHERE usuario='"+usuario+"'";
			logger.log(Level.INFO, "Statement: " + sql);
			ArrayList<Contacto> alc = new ArrayList<>();
			try (Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql);){
				while(rs.next()) {
					int cod = rs.getInt("codigo");
					String u = rs.getString("usuario");
					String n = rs.getString("nombre");
					String m = rs.getString("mail");
					String t = rs.getString("telf");
					String b = rs.getString("favorito");
					boolean fav = false;
					if(b.equals("true"))
						fav = true;
					Contacto c = new Contacto(cod, u, n, m, t, fav);
					alc.add(c);
				}
				
			} catch (SQLException e) {
				logger.log(Level.SEVERE, "Excepción", e);
			} 
			return alc;
		}
		
		
		/**
		 * Devuelve una lista de todos los tipos de evento creados por el usuario, usando la sentencia SELECT de SQL y el nombre de usuario que los ha creado
		 * @param con	Conexion con la base de datos
		 * @param usuario	"Nickname" del usuario que ha creado los eventos
		 * @return ate 		ArrayList con todos los tipos de evento creados por el usuario
		 */
		
		public static ArrayList<String> obtenerTodosTipoEventoUsuario(Connection con, String usuario) {
			String sql = "SELECT * FROM tipo WHERE usuario = '"+usuario+"'";
			logger.log(Level.INFO, "Statement: " + sql);
			ArrayList<String> ate = new ArrayList<>();
			try (Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql);) {
				while(rs.next()) {
					String nombre = rs.getString("nombre");
					ate.add(nombre);
				}
				
			} catch (SQLException e) {
				logger.log(Level.SEVERE, "Excepción", e);
			}
			return ate;
		}
		
		
		/**
		 * Elimina un usuario de la tabla Usuario, usando la sentencia DELETE de SQL y el "nickname" del usuario
		 * @param con	Conexion con la base de datos
		 * @param nomUsu	"Nickname" del usuario
		 */
		public static void eliminarUsuario(Connection con, String nomUsu) {
			String sentSQL = "DELETE FROM usuario WHERE nomUsuario ='"+nomUsu+"'";
			logger.log(Level.INFO, "Statement: " + sentSQL);
			try {
				ArrayList<Evento> lEventos = obtenerEventosUsuario(con, nomUsu);
				ArrayList<Contacto> lContactos = obtenerContactosUsuario(con, nomUsu);
				
				for(Evento e: lEventos) {
					eliminarEvento(con, e.getCodigo());
				}
				for(Contacto c: lContactos) {
					eliminarContacto(con, c.getNombre());
				}
				Statement stmt = con.createStatement();
				stmt.executeUpdate(sentSQL);
				stmt.close();
			} catch (SQLException e) {
				logger.log(Level.SEVERE, "Excepción", e);
			}
		}
		
		
		/**
		 * Elimina un contacto de la tabla Contacto, usando la sentencia DELETE de SQL y el nombre del contacto
		 * @param con	Conexion con la base de datos
		 * @param nombre	Nombre del contacto
		 */
		public static void eliminarContacto(Connection con, String nombre) {
			String sentSQL = "DELETE FROM contacto WHERE nombre ='"+nombre+"'";
			logger.log(Level.INFO, "Statement: " + sentSQL);
			try {
				Statement stmt = con.createStatement();
				stmt.executeUpdate(sentSQL);
				stmt.close();
			} catch (SQLException e) {
				logger.log(Level.SEVERE, "Excepción", e);
			}
		}
		
		
		/**
		 * Elimina un evento de la tabla Evento, usando la sentencia DELETE de SQL y el codigo del evento
		 * @param con	Conexion con la base de datos
		 * @param codigo	Codigo del evento
		 */
		public static void eliminarEvento(Connection con, int codigo) {
			String sentSQL = "DELETE FROM evento WHERE codigo ="+codigo;
			logger.log(Level.INFO, "Statement: " + sentSQL);
			try {
				Statement stmt = con.createStatement();
				stmt.executeUpdate(sentSQL);
				stmt.close();
			} catch (SQLException e) {
				logger.log(Level.SEVERE, "Excepción", e);
			}
		}
		
		
		/**
		 * Actualiza los valores de una de las filas de la tabla Evento, usando la funcion UPDATE de SQL y los datos introducidos por el usuario
		 * @param con	Conexion con la base de datos
		 * @param codigo	Codigo del evento
		 * @param fecha		Fecha del evento
		 * @param nombre	Nombre del evento
		 * @param tipo		Tipo de evento
		 * @param duracion	Duracion del evento
		 */
		public static void updateEvento(Connection con, String codigo, String fecha, String nombre, String tipo, int duracion) {
			String sentSQL = "UPDATE evento SET fecha = '"+fecha+"', nombre = '"+nombre+"', tipo = '"+tipo+"', duracion = "+duracion+" WHERE codigo ='"+codigo+"'";
			logger.log(Level.INFO, "Statement: " + sentSQL);
			try {
				Statement st = con.createStatement();
				st.executeUpdate(sentSQL);
				st.close();
			} catch (SQLException e) {
				logger.log(Level.SEVERE, "Excepción", e); 
			}
		}
		
		
		/**
		 * Actualiza los valores de una de las filas de la tabla Contacto, usando la funcion UPDATE de SQL y los datos introducidos por el usuario
		 * @param con	Conexion con la base de datos
		 * @param codigo	Codigo del contacto
		 * @param usuario	"Nickname" del usuario que ha creado el contacto
		 * @param nombre	Nombre del contacto
		 * @param mail		Correo electronico del contacto
		 * @param telf		Numero de telefono del contacto
		 * @param favorito	Boolean para indicar si el contacto es favorito o no
		 */
		public static void updateContacto(Connection con, int codigo, String usuario, String nombre, String mail, String telf, String favorito) {
			String sentSQL = "UPDATE contacto SET nombre = '"+nombre+"', mail = '"+mail+"', telf = '"+telf+"', favorito = '"+favorito+"' WHERE codigo ="+codigo+" AND usuario = '"+usuario+"'";
			logger.log(Level.INFO, "Statement: " + sentSQL);
			try {
				Statement st = con.createStatement();
				st.executeUpdate(sentSQL);
				st.close();
			} catch (SQLException e) {
				logger.log(Level.SEVERE, "Excepción", e);
			}
		}
		
		
		/**
		 * Actualiza el estado de un evento en la tabla Evento, usando la sentencia UPDATE de SQL y el codigo del evento
		 * @param con	Conexion con la base de datos
		 * @param codigo	Codigo del evento
		 * @param estado	Boolean para indicar si el evento se ha completado o no
		 */
		public static void updateCompleto(Connection con, int codigo, boolean estado) {
			String sentSQL;
			if(estado) {
			 sentSQL= "UPDATE evento SET completo='true' WHERE codigo ="+codigo;
			 logger.log(Level.INFO, "Statement: " + sentSQL);
			}
			else {
				sentSQL= "UPDATE evento SET completo='false' WHERE codigo ="+codigo;
				logger.log(Level.INFO, "Statement: " + sentSQL);
			}
				
			try {
				Statement stmt = con.createStatement();
				stmt.executeUpdate(sentSQL);
				stmt.close();
			} catch (SQLException e) {
				logger.log(Level.SEVERE, "Excepción", e);
			}
		}
		
		
		/**
		 * Devuelve el estado de un evento de la tabla Evento, usando la sentencia SELECT de SQL y el codigo del evento
		 * @param con	Conexion con la base de datos
		 * @param codigo	Codigo del evento
		 * @return completo	Boolean para indicar si el evento ha sido completado o no
		 */
		public static boolean getCompleto(Connection con, int codigo) {
			String sentSQL = "SELECT completo FROM evento WHERE codigo="+codigo;
			logger.log(Level.INFO, "Statement: " + sentSQL);
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
				logger.log(Level.SEVERE, "Excepción", e);
			}
			return completo;
		}
}