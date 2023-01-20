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
	VentanaInicioSesion vi = new VentanaInicioSesion();
	
	@Before
	public void setUp() {
		con = BaseDatos.initBD("proyecto.db");
	}
	
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
	
	@After
	public void tearDown() {
		BaseDatos.closeBD(con);
	}

}
