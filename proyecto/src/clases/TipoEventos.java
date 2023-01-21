package clases;

import java.util.ArrayList;

public class TipoEventos {
	private static TipoEventos miTipoEventos = null;
	private ArrayList<String> tipos ;
	private String usuario;

	/**
	 * Constructor que añade por defecto los valores de la clase TipoEvento a un ArrayList
	 */
	private TipoEventos() {
		tipos = new ArrayList<>();
		for(TipoEvento te: TipoEvento.values()) {
			tipos.add(te.name());
		}
	}
	
	/**
	 * Constructor que inicializa el nombre de usuario
	 * @param usuario	"Nickname" del usuario
	 */
	private TipoEventos (String usuario) {
		super();
		this.usuario = usuario;
	}
	
	/**
	 * Devuelve los tipos de evento de un usuario
	 * @return miTipoEvento		Tipos de eventos del usuario
	 */
	public static TipoEventos getTipoEventos() {
		if(miTipoEventos == null) {
			miTipoEventos = new TipoEventos();
		}
		return miTipoEventos;
	}
	
	/**
	 * Añade un tipo de evento a la lista
	 * @param tipo		Tipo de evento añadido
	 */
	public void aniadirTipo(String tipo) {
		tipos.add(tipo);
	}
	
	/**
	 * Devuelve los tipos de eventos en forma de array
	 * @return tipos	Tipos de eventos en array
	 */
	public Object [] getTipos() {
		return tipos.toArray();
	}
	
	/**
	 * Devuelve el la posicion del evento si este se encuentra en el array
	 * @param tipo	Tipo de evento a buscar
	 * @return 		Posicion del evento en el array
	 */
	public int esta(String tipo) {
		return tipos.indexOf(tipo);
	}
	
	/**
	 * Devuelve el nombre del usuario que ha creado el tipo de evento
	 * @return usuario	"Nickname" del usuario
	 */
	public String getUsuario() {
		return usuario;
	}
	
	/**
	 * Confirma el nombre del usuario que ha creado el tipo de evento
	 * @param usuario	"Nickname" el usuario
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
}