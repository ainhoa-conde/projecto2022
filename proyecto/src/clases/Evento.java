package clases;

import java.util.Date;

public class Evento {
	
	public static int codigo;
	private String usuario;
	private Date fecha;
	private String nombre;
	private TipoEvento tipo;
	private int duracion;
	private boolean completo;
	
	public Evento() {
		// TODO Auto-generated constructor stub
	}

	public Evento(int codigo,String usuario, Date fecha, String nombre, TipoEvento tipo, int duracion, boolean com) {
		super();
		codigo++;
		this.codigo = codigo;
		this.usuario = usuario;
		this.fecha = fecha;
		this.nombre = nombre;
		this.tipo = tipo;
		this.duracion = duracion;
		this.completo = com;
	}

	
	public boolean isCompleto() {
		return completo;
	}

	public void setCompleto(boolean completo) {
		this.completo = completo;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getFecha() {
		return Utilidades.sdf.format(fecha);
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public TipoEvento getTipo() {
		return tipo;
	}
	public void setTipo(TipoEvento tipo) {
		this.tipo = tipo;
	}

	public int getDuracion() {
		return duracion;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	@Override
	public String toString() {
		return codigo + " " + Utilidades.sdf.format(fecha) + " " + nombre + " " + tipo + " "+ completo;
	}	
}