package clases;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import ventanas.VentanaInicioSesion;

public class BaseDatosTest {
	
	
	@Before
	public void setUp() {
		BaseDatos.initBD("proyecto.db");
		BaseDatos.crearTablas(null);
	}

	@Test
	public void insertarUsuarioTest() {
		
	}
	
	@After
	public void tearDown() {
		BaseDatos.closeBD(null);
	}

}
