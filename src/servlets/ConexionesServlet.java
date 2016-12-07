package servlets;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("action");
		leerJson();
		doGet(request, response);
	}
	
	protected ArrayList<Conexion> leerJson(){
		File f = new File(getServletContext().getRealPath("conexiones.json"));
		Gson gson = new Gson();
		ArrayList<Conexion> conexionList = new ArrayList<Conexion>();
		try{
			BufferedReader br = new BufferedReader(new FileReader(f));
			conexionList = gson.fromJson(br, new TypeToken<List<Conexion>>(){}.getType());
		}catch(Exception e){
			
		}	
		return conexionList;
	}
	
	

}
