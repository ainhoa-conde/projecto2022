package clases;

import java.util.ArrayList;
import java.util.Date;

public class Evento {
	
	private String codigo;
	private String usuario;
	private Date fecha;
	private String nombre;
	private TipoEvento tipo;
	private int duracion;
	
	public Evento() {
		// TODO Auto-generated constructor stub
	}

	public Evento(String codigo,String usuario, Date fecha, String nombre, TipoEvento tipo, int duracion) {
		super();
		this.codigo = codigo;
		this.usuario = usuario;
		this.fecha = fecha;
		this.nombre = nombre;
		this.tipo = tipo;
		this.duracion = duracion;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Date getFecha() {
		return fecha;
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
		return codigo + " " + Utilidades.sdf.format(fecha) + " " + nombre + " " + tipo;
	}	
}