package clases;

public class Usuario {
	
	private String nombre;
	private String apellido;
	private String mail;
	private String nomUsuario;
	private String contrasenia;

	
	/**
	 * Constructor vacio de la clase Usuario
	 */
	public Usuario() {
		super();
	}

	/**
	 * Constructor con paramentros de la clase Usuario
	 * @param nombre	Nombre del usuario
	 * @param apellido	Apellido del usuario
	 * @param mail		Correo del usuario
	 * @param nomUsuario	"Nickname" del usuario
	 * @param contrasenia	Contraseña del usuario
	 */
	public Usuario(String nombre, String apellido, String mail, String nomUsuario, String contrasenia) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.mail = mail;
		this.nomUsuario = nomUsuario;
		this.contrasenia = contrasenia;
	}

	/**
	 * Obtener el nombre del usuario
	 * @return nombre 	Nombre del usuario
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Confirmar el nombre del usuario
	 * @param nombre 	Nombre del usuario
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Obtener el apellido del usuario
	 * @return apellido 	Apellido del usuario
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * Confirmar el apellido del usuario
	 * @param apellido 	Apellido del usuario
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	/**
	 * Obtener el mail del usuario
	 * @return mail 	Correo del usuario
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * Confirmar el mail del usuario
	 * @param mail 	Correo del usuario
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * Obtener el "nick" del usuario
	 * @return nomUsuario 	"Nickname" del usuario
	 */
	public String getNomUsuario() {
		return nomUsuario;
	}

	/**
	 * Confirmar el "nick" del usuario
	 * @param nomUsuario 	"Nickname" del usuario
	 */
	public void setNomUsuario(String nomUsuario) {
		this.nomUsuario = nomUsuario;
	}

	/**
	 * Obtener la contraseña del usuario
	 * @return contrasenia 	Contraseña del usuario
	 */
	public String getContrasenia() {
		return contrasenia;
	}

	/**
	 * Confirmar la contraseña del usuario
	 * @param contrasenia 	Contraseña del usuario
	 */
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	/**
	 * Crea un string con los datos de un usuario
	 */
	@Override
	public String toString() {
		return "Nombre:" + nombre + ", apellido:" + apellido + ", mail:" + mail + ", nombre usuario:" + nomUsuario
				+ ", contraseña=" + contrasenia;
	}
	
	

}