package clases;

import java.util.Date;

/**
 * 
 * Clase que inicializa los datos de un evento
 *
 */

public class Evento  {
	
	public int codigo;
	private String usuario;
	private Date fecha;
	private String nombre;
	private String tipo;
	private int duracion;
	private boolean completo;
	
	
	/**
	 * Constructor vacio de la clase Evento
	 */
	public Evento() {
		super();
	}

	/**
	 * Constructor con parametros de la clase Evento
	 * @param codigo	Codigo del evento
	 * @param usuario	"Nickname" del usuario que ha creado el evento
	 * @param fecha		Fecha del evento
	 * @param nombre	Nombre del evento
	 * @param tipo		Tipo de evento
	 * @param duracion	Duracion del evento
	 * @param com		Estado completado
	 */
	public Evento(int codigo,String usuario, Date fecha, String nombre, String tipo, int duracion, boolean com) {
		super();
		this.codigo = codigo;
		this.usuario = usuario;
		this.fecha = fecha;
		this.nombre = nombre;
		this.tipo = tipo;
		this.duracion = duracion;
		this.completo = com;
	}

	
	/**
	 * Obtener el estado completo del evento
	 * @return completo		Estado del evento
	 */
	public boolean isCompleto() {
		return completo;
	}

	/**
	 * Confirmar el estado completo del evento
	 * @param completo		Estado del evento
	 */
	public void setCompleto(boolean completo) {
		this.completo = completo;
	}

	/**
	 * Obtener el codigo del evento
	 * @return codigo 	Codigo del evento
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * Confirmar el codigo del evento
	 * @param codigo	Codigo del evento
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	/**
	 * Obtener el "nick" del creador del evento
	 * @return usuario 	"Nickname" del creador del evento
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * Confirmar el "nick" del creador del evento
	 * @param usuario 	"Nickname" del creador del evento
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	
	/**
	 * Obtener la fecha del evento
	 * @return fecha 	Fecha del evento
	 */
	public String getFecha() {
		return Utilidades.sdf.format(fecha);
	}
	
	/**
	 * Confirmar la fecha del evento
	 * @param fecha 	Fecha del evento
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * Obtener el nombre del evento
	 * @return nombre 	Nombre del evento
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Confirmar el nombre del evento
	 * @param nombre 	Nombre del evento
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	/**
	 * Obtener el tipo del evento
	 * @return tipo 	Tipo del evento
	 */
	public String getTipo() {
		return tipo;
	}
	
	/**
	 * Confirmar el tipo del evento
	 * @param tipo 	Tipo del evento
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * Obtener la duracion del evento
	 * @return duracion 	Duracion del evento
	 */
	public int getDuracion() {
		return duracion;
	}
	
	/**
	 * Confirmar la duracion del evento
	 * @param duracion 	Duracion del evento
	 */
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	
	/**
	 * Crea un string con los datos de un evento
	 */
	@Override
	public String toString() {
		return Utilidades.sdf.format(fecha) + " - " + nombre + " - " + tipo;
	}	
}