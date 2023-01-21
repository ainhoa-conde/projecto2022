package clases;

/**
 * 
 * Clase que inicializa la información sobre un contacto
 *
 */

public class Contacto {
	private int codigo;
	private String usuario;
	private String nombre;
	private String mail;
	private String telf;
	private boolean favorito;
	
	
	/**
	 * Constructor vacio de la clase Contacto
	 */
	public Contacto() {
		super();
	}
	
	/**
	 * Constructor con parametros de la clase Contacto
	 * @param codigo	Codigo del contacto
	 * @param usuario	Usuario que ha creado el contacto
	 * @param nombre 	Nombre del contacto
	 * @param mail 		Correo electronico del mail
	 * @param telf		Telefono del contacto
	 * @param favorito	Marcar si es un contacto es favorito
	 */
	public Contacto(int codigo, String usuario, String nombre, String mail, String telf, boolean favorito) {
		super();
		this.codigo = codigo;
		this.usuario = usuario;
		this.nombre = nombre;
		this.mail = mail;
		this.telf = telf;
		this.favorito = favorito;
	}
	
	/**
	 * Obtener el codigo del contacto
	 * @return codigo	Codigo del contacto
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * Confirmar el codigo del contacto
	 * @param codigo	Codigo del contacto
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	/**
	 * Obtener el usuario que ha creado el contacto
	 * @return usuario	"Nickname" del usuario
	 */
	public String getusuario() {
		return usuario;
	}
	
	/**
	 * Confirmar el usuario que ha creado el contacto
	 * @param usuario	"Nickname" del usuario
	 */
	public void setusuario(String usuario) {
		this.usuario = usuario;
	}
	
	/**
	 * Obtener el nombre del contacto
	 * @return nombre	Nombre del contacto
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Confirmar nombre del contacto
	 * @param nombre	Nombre del contacto
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Obtener el mail del contacto
	 * @return mail		Correo del contacto
	 */
	public String getMail() {
		return mail;
	}
	
	/**
	 * Confirmar mail del contacto
	 * @param mail		Correo del contacto
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	/**
	 * Obtener el telefono del contacto
	 * @return telf		Telefono del contacto
	 */
	public String getTelf() {
		return telf;
	}
	
	/**
	 * Confirmar telefono del contacto
	 * @param telf		Telefono del contacto
	 */
	public void setTelf(String telf) {
		this.telf = telf;
	}
	
	/**
	 * Obtener el estado favorito del contacto
	 * @return favorito		Estado del contacto
	 */
	public boolean isFavorito() {
		return favorito;
	}
	
	/**
	 * Confirmar el estado favorito del contacto
	 * @param favorito		Estado del contacto
	 */
	public void setFavorito(boolean favorito) {
		this.favorito = favorito;
	}
	
	/**
	 * Crea un string con los datos de un contacto
	 */
	@Override
	public String toString() {
		return nombre + ", " + mail + ", " + telf + ", " + favorito;
	}
}