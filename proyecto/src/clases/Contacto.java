package clases;

public class Contacto {
	private String codEvento;
	private String nombre;
	private String mail;
	private String telf;
	private boolean favorito;
	
	public Contacto() {
		super();
	}
	
	public Contacto(String codEvento, String nombre, String mail, String telf, boolean favorito) {
		super();
		this.codEvento = codEvento;
		this.nombre = nombre;
		this.mail = mail;
		this.telf = telf;
		this.favorito = favorito;
	}
	
	public String getCodEvento() {
		return codEvento;
	}

	public void setCodEvento(String codEvento) {
		this.codEvento = codEvento;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getTelf() {
		return telf;
	}
	public void setTelf(String telf) {
		this.telf = telf;
	}
	public boolean isFavorito() {
		return favorito;
	}
	public void setFavorito(boolean favorito) {
		this.favorito = favorito;
	}
	@Override
	public String toString() {
		return nombre + ", " + mail + ", " + telf + ", " + favorito;
	}
}