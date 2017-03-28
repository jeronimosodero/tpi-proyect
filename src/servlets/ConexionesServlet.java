package servlets;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.function.Predicate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import classes.Conexion;
import classes.Conexiones;

/**
 * Servlet implementation class ConexionesServlet
 */
@WebServlet("/ConexionesServlet")
public class ConexionesServlet extends HttpServlet {
	private Conexiones conexiones;
	private ArrayList<Conexion> conexionesList = new ArrayList<Conexion>();
    public ConexionesServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		conexiones = new Conexiones();
		conexionesList = leerJson();
		conexiones.setConexiones(conexionesList);
		request.setAttribute("cxs", conexiones);
		request.setAttribute("fecha", ultimaFecha());
		ServletContext sc = getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher("/conexiones.jsp");
		rd.forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		conexionesList = leerJson();
		int maxId = 0;
		if (conexionesList.size() > 0){
		maxId = conexionesList.get(conexionesList.size()-1).getId() + 1;
		} else {
			maxId = 1;
		}
		String accion = (String) request.getParameter("boton");
		if(accion != null){
			if (accion.equals("guardar")){
				String nombre = request.getParameter("nombre");
				String url = request.getParameter("url");
				String estandar = request.getParameter("estandar");
				Conexion con = new Conexion(maxId,nombre,url,estandar);
				conexionesList.add(con);
			}else{
			 	int index = Integer.valueOf(accion);
				Predicate<Conexion> pred = c -> c.getId() == index;
				conexionesList.removeIf(pred);
			}
		}
		escribirJson(conexionesList);
		conexionesList = leerJson();
		cosechar();
		doGet(request, response);
	}
	
	static protected ArrayList<Conexion> leerJson() throws IOException{
		
		String tmpPath = System.getProperty("java.io.tmpdir") + "/conexiones.json";
		//File f = new File(getServletContext().getRealPath("conexiones.json"));
		File f = new File(tmpPath);
		Gson gson = new Gson();
		
		ArrayList<Conexion> conexionList = new ArrayList<Conexion>();
		try{
			BufferedReader br = new BufferedReader(new FileReader(f));
			conexionList = gson.fromJson(br, new TypeToken<List<Conexion>>(){}.getType());
		}catch(Exception e){
		}
		return conexionList;
	}
	
	protected void escribirJson(ArrayList<Conexion> conexionList) throws IOException{
		File oldtemp = new File(System.getProperty("java.io.tmpdir") + "/conexiones.json");
		oldtemp.delete();
		
		Gson gson = new Gson();
		
		File newtemp = new File(System.getProperty("java.io.tmpdir") + "/conexiones.json");    
        String json = gson.toJson(conexionesList, new TypeToken<List<Conexion>>(){}.getType());       
         try {
        	 FileWriter f2 = new FileWriter(newtemp, false);
        	 f2.write(json);
             f2.close();
         } catch (IOException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         }   
	}
	
	private void cosechar() throws IOException{
		 ArrayList<Conexion> conexiones = ConexionesServlet.leerJson();
		    for (Conexion con: conexiones){
		    	String path = getServletContext().getRealPath("");   	
		    	String url = con.getUrl();
		    	String charset = java.nio.charset.StandardCharsets.UTF_8.name();
		    	String verbo = "ListRecords";
		    	String formato = con.getEstandar().trim();
		    	String conexion = con.getNombre().trim();

		    	Date fecha_ahora = new Date();
		    	SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
		        String fecha = dt.format(fecha_ahora);
		        String dataFolderPath = path + "data";
		        String fileName = conexion + "%" + formato + "%" + fecha + ".xml";	        
		        File folder = new File(dataFolderPath);
		        folder.mkdirs();
		        String query = "";
		        boolean exists = false;
		        String fecha_desde = "";
		        File[] listOfFiles = folder.listFiles();
		        for (File file : listOfFiles) {
		            if (file.isFile()) {
		            	String oldFileName = file.getName();
		                if (oldFileName.contains(formato) && oldFileName.contains(conexion)) {
		                	exists = true;
		                	fecha_desde = oldFileName.split("%")[2].substring(0,10);
		                }
		            }
		        }
		        
		        if (exists) {
		        	query = String.format("verb=%s&metadataPrefix=%s&from=%s", 
		        		    URLEncoder.encode(verbo, charset), 
		        		    URLEncoder.encode(formato, charset),
		        		    URLEncoder.encode(fecha_desde, charset));
	            }
	            else{
	    	    	query = String.format("verb=%s&metadataPrefix=%s", 
	    	    			URLEncoder.encode(verbo, charset), 
	    		    	    URLEncoder.encode(formato, charset)
	    		    	    );
	            }
		        
		        
		    	String filePath = dataFolderPath + "\\" + fileName;

		    	try {
			    	URLConnection connection = new URL(url + "?" + query).openConnection();
			    	connection.setRequestProperty("Accept-Charset", charset);
			    	InputStream respuesta = connection.getInputStream();
			    	String responseBody = "";
			    	try (Scanner scanner = new Scanner(respuesta)) {
			    	    responseBody = scanner.useDelimiter("\\A").next();
			    	}
			    	System.out.println(filePath);
					File f1 = new File(filePath);    
					try {
			        	 FileWriter f2 = new FileWriter(f1, false);
			        	 f2.write(responseBody);
			             f2.close();
			         } catch (IOException e) {
			             // TODO Auto-generated catch block
			             e.printStackTrace();
			         }
		    	}
		    	catch (ConnectException e){
		    		System.out.println("Connection Timeout");
		    	}

		    }
	}
	
	private String ultimaFecha(){
    	String path = getServletContext().getRealPath("");   	
    	String dataFolderPath = path + "data";
    	File folder = new File(dataFolderPath);
    	if(folder.exists()){
        	String fecha_desde = "";
            Long date = folder.lastModified();
            Date date2=new Date(date);
            SimpleDateFormat df2 = new SimpleDateFormat("EEEE d 'de' MMMM 'de' yyyy", new Locale("es", "ES"));
            String dateText = df2.format(date2);	
            return dateText;
    	}else{
    		return null;
    	}

        
	}
	
}
