
public class Conexion {
	private String url,estandar,repositorio;
	private int id;
	
	Conexion(){}

	public Conexion(String url, String estandar, String repositorio, int id) {
		super();
		this.url = url;
		this.estandar = estandar;
		this.repositorio = repositorio;
		this.id = id;
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

	public String getRepositorio() {
		return repositorio;
	}

	public void setRepositorio(String repositorio) {
		this.repositorio = repositorio;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
