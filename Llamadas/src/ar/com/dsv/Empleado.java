package ar.com.dsv;

public class Empleado {

	public static final Integer EMPLEADO = 1;
	public static final Integer SUPERVISOR = 2;
	public static final Integer DIRECTOR = 3;
	
	private Integer id;
	private String nombre;
	private Integer tipo;
	private boolean ocupado = false;
	
	
	public Empleado(Integer id, String nombre, Integer tipo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.tipo = tipo;		
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getTipo() {
		return tipo;
	}
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
	public boolean isOcupado() {
		return ocupado;
	}
	public void setOcupado(boolean ocupado) {
		this.ocupado = ocupado;
	}
	
	
	
	
}
