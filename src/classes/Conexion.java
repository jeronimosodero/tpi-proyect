package classes;

public class Conexion {
	private int id;
	private String nombre, url, estandar;
	
	
	Conexion(){}


	public Conexion(int id, String nombre, String url, String estandar) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.url = url;
		this.estandar = estandar;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getEstandar() {
		return estandar;
	}


	public void setEstandar(String estandar) {
		this.estandar = estandar;
	}

	
}
