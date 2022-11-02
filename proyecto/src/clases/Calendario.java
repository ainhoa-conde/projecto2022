package clases;

import java.util.ArrayList;

public class Calendario {
	
	private Usuario usuario;
	private ArrayList<Evento> evento = new ArrayList<Evento>();
	
	public Calendario() {
		// TODO Auto-generated constructor stub
	}

	public Calendario(Usuario usuario, ArrayList<Evento> evento) {
		super();
		this.usuario = usuario;
		this.evento = evento;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public ArrayList<Evento> getEvento() {
		return evento;
	}
	public void setEvento(ArrayList<Evento> evento) {
		this.evento = evento;
	}


	@Override
	public String toString() {
		return "Calendario [usuario=" + usuario + ", evento=" + evento + "]";
	}

	
}
