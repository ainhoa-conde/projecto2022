package clases;

public class Contacto {
	private String nombre;
	private String mail;
	private String telf;
	private boolean favorito;
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