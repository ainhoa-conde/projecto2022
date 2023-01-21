package clases;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import ventanas.VentanaInicioSesion;

public class BaseDatosTest {
	Connection con ;
	
	/*Abrir conexion con la base de datos y creacion de las tablas
	 */
	@Before
	public void setUp() {
		con = BaseDatos.initBD("proyecto.db");
		BaseDatos.crearTablas(con);
	}
	
	/*Tests de la clase eventos
	 * 1. Comprobar que al crear un usuario no tiene ningun evento
	 * 2. Al insertar 5 eventos, comprobar que el usuario tiene el numero correcto de eventos
	 * 3. Asegurar que el mayor codigo mayor de los eventos se ha actualizado
	 * 4. Comprobar que la operacion para actualizar el estado funciona
	 * 5. Comprobar que los eventos se eliminan correctamente
	 */
	@Test
	public void eventosTest() {
		
		BaseDatos.insertarUsuario(con, "Ane", "Conde", "ane@gmail.com","ane", "123");
		assertEquals(BaseDatos.obtenerEventosUsuario(con, "ane").size(), 0);
		
		int codigo = BaseDatos.getMaxCodigoEvento(con);
		
		for(int i = 1; i <= 5; i++) {
			BaseDatos.insertarEvento(con, "ane", "2023/01/01", "evento"+i, "tipo", 1, "false");
		}
		
		assertEquals(BaseDatos.obtenerEventosUsuario(con, "ane").size(), 5);
		assertEquals(BaseDatos.getMaxCodigoEvento(con), codigo + 5);
		
		BaseDatos.updateCompleto(con, codigo + 1, true);
		assertTrue(BaseDatos.getCompleto(con, codigo + 1));
		assertFalse(BaseDatos.getCompleto(con, codigo + 2));
		
		BaseDatos.eliminarEvento(con, codigo + 5);
		assertEquals(BaseDatos.obtenerEventosUsuario(con, "ane").size(), 4);
		
		BaseDatos.eliminarUsuario(con, "ane");
	}
	
	/*Tests de la clase contactos
	 * 1. Comprobar que al crear un usuario no tiene ningun contacto
	 * 2. Al insertar 5 contactos, comprobar que el usuario tiene el numero correcto de contactos
	 * 3. Asegurar que el mayor codigo mayor de los contactos se ha actualizado
	 * 4. Comprobar que los contactos se eliminan correctamente
	 */
	@Test
	public void contactosTest() {
		BaseDatos.insertarUsuario(con, "Ane", "Conde", "ane@gmail.com","ane", "123");
		assertEquals(BaseDatos.obtenerContactosUsuario(con, "ane").size(), 0);
		
		int codigo = BaseDatos.getMaxCodigoContacto(con);
		for(int i = 1; i <= 5; i++) {
			BaseDatos.insertarContacto(con, "ane", "contacto"+i, "mail@mail.es", "111111111", "false");
		}
		
		assertEquals(BaseDatos.obtenerContactosUsuario(con, "ane").size(), 5);
		assertEquals(BaseDatos.getMaxCodigoContacto(con), codigo + 5);
		
		BaseDatos.eliminarContacto(con, "contacto1");
		assertEquals(BaseDatos.obtenerContactosUsuario(con, "ane").size(), 4);
		
		BaseDatos.eliminarUsuario(con, "ane");
	}
	
	/*Cerrar conexion con la base de datos
	 */
	@After
	public void tearDown() {
		BaseDatos.closeBD(con);
	}

}
