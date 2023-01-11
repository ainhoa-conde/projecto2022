package clases;

import java.util.ArrayList;

public class TipoEventos {
	private static TipoEventos miTipoEventos = null;
	private ArrayList<String> tipos ;
	private String usuario;

	private TipoEventos() {
		tipos = new ArrayList<>();
		for(TipoEvento te: TipoEvento.values()) {
			tipos.add(te.name());
		}
	}
	
	private TipoEventos (String usuario) {
		super();
		this.usuario = usuario;
	}
	
	public static TipoEventos getTipoEventos() {
		if(miTipoEventos == null) {
			miTipoEventos = new TipoEventos();
		}
		return miTipoEventos;
	}
	
	public void aniadirTipo(String tipo) {
		tipos.add(tipo);
	}
	
	public Object [] getTipos() {
		return tipos.toArray();
	}
	
	public int esta(String tipo) {
		return tipos.indexOf(tipo);
	}
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
}