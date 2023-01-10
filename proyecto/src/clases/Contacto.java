package clases;

public class Contacto {
	private int codigo;
	private String usuario;
	private String nombre;
	private String mail;
	private String telf;
	private boolean favorito;
	
	public Contacto() {
		super();
	}
	
	public Contacto(int codigo, String usuario, String nombre, String mail, String telf, boolean favorito) {
		super();
		this.codigo = codigo;
		this.usuario = usuario;
		this.nombre = nombre;
		this.mail = mail;
		this.telf = telf;
		this.favorito = favorito;
	}
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public String getusuario() {
		return usuario;
	}
	public void setusuario(String usuario) {
		this.usuario = usuario;
	
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