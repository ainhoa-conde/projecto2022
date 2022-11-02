package clases;

public class Usuario {
	
	private String nombre;
	private String apellido;
	private String mail;
	private String nomUsuario;
	private String contrasenia;

	public Usuario() {
		// TODO Auto-generated constructor stub
	}

	public Usuario(String nombre, String apellido, String mail, String nomUsuario, String contrasenia) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.mail = mail;
		this.nomUsuario = nomUsuario;
		this.contrasenia = contrasenia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getNomUsuario() {
		return nomUsuario;
	}

	public void setNomUsuario(String nomUsuario) {
		this.nomUsuario = nomUsuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	@Override
	public String toString() {
		return "Nombre:" + nombre + ", apellido:" + apellido + ", mail:" + mail + ", nombre usuario:" + nomUsuario
				+ ", contraseña=" + contrasenia;
	}
	
	

}