package servlets;

import java.io.File;
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
import java.util.Scanner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.reflect.TypeToken;

import classes.Conexion;

/**
 * Servlet implementation class CosechadorServlet
 */
@WebServlet("/CosechadorServlet")
public class CosechadorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CosechadorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
	        System.out.println(dataFolderPath);
	        String query = "";
	        boolean exists = false;
	        String fecha_desde = "";
	        File[] listOfFiles = folder.listFiles();
	        for (File file : listOfFiles) {
	            if (file.isFile()) {
	            	String oldFileName = file.getName();
	            	System.out.println(oldFileName.split("%")[2].substring(0,10));
	            	System.out.println(formato);
	                if (oldFileName.contains(formato)) {
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
	        System.out.println(query);
	        
	    	String filePath = dataFolderPath + "\\" + fileName;

	    	try {
		    	URLConnection connection = new URL(url + "?" + query).openConnection();
		    	connection.setRequestProperty("Accept-Charset", charset);
		    	InputStream respuesta = connection.getInputStream();
		    	String responseBody = "";
		    	try (Scanner scanner = new Scanner(respuesta)) {
		    	    responseBody = scanner.useDelimiter("\\A").next();
		    	    System.out.println(responseBody);
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
