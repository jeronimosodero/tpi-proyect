package servlets;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
		ServletContext sc = getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher("/conexiones.jsp");
		rd.forward(request,response);
		System.out.println(System.getProperty("java.io.tmpdir"));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		conexionesList = leerJson();
		int maxId = 0;
		if (conexionesList.size() > 0){
		maxId = conexionesList.get(conexionesList.size()-1).getId() + 1;
		} else {
			maxId = 1;
		}
		String accion = request.getParameter("boton");
		if (accion.equals("guardar")){
			String dir = request.getParameter("dir");
			String est = request.getParameter("est");
			String rep = request.getParameter("rep");
			String len = request.getParameter("len");
			Conexion con = new Conexion(dir,est,rep, maxId, len);
			conexionesList.add(con);
		} else {
		 	int index = Integer.valueOf(accion);
			Predicate<Conexion> pred = c -> c.getId() == index;
			conexionesList.removeIf(pred);
		} 
		System.out.println(conexionesList);
		escribirJson(conexionesList);
		conexionesList = leerJson();
		doGet(request, response);
	}
	
	protected ArrayList<Conexion> leerJson() throws IOException{
		
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
	
}
