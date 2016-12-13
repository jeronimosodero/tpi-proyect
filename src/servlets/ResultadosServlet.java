package servlets;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.Conexion;
import classes.Metadata;
import classes.OA;
import classes.OAs;
import classes.Request;
import classes.Result;
import classes.ServiceGenerator;
import endpoints.Endpoint;
import retrofit2.Call;

/**
 * Servlet implementation class ResultadosServlet
 */
public class ResultadosServlet extends HttpServlet {
	private static final String DC_TITLE = "dc.title";
	private static final String DC_AUTHOR = "dc.contributor.author";
	private static final String DC_DATE = "dc.date.issued";

	private OAs oas;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("oas", oas);
		ServletContext sc = getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher("/resultados.jsp");
		rd.forward(req,resp);
	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		oas = new OAs();
		String key = "";
		String value = "";
		String title = req.getParameter(DC_TITLE);
		String author = req.getParameter(DC_AUTHOR);
		String date = req.getParameter(DC_DATE);
		if(title != null && title.length() > 0){
			key = DC_TITLE;
			value = title;
		}else if( author != null && author.length() > 0){
			key = DC_AUTHOR;
			value = author;
		}else if(date != null && date.length() > 0){
			key = DC_DATE;
			value = date;
		}else{
			doGet(req, resp);
			return;
		}
		List<Conexion> conexiones = ConexionesServlet.leerJson();
		ArrayList<OA> oasList = new ArrayList<OA>();
		for(Conexion conexion : conexiones){
			Endpoint endpoint = ServiceGenerator.createService(Endpoint.class,conexion.getUrl());
			Call<List<Result>> call;
			if(key.equals(DC_TITLE)){
				call = endpoint.findByMetadataField(new Request(key,value,"en_US"));
			}else{
				call = endpoint.findByMetadataField(new Request(key,value,null));
			}
			try{
				List<Result> results = call.execute().body();
				for(Result result : results){
					Call<List<Metadata>> callMetadata = endpoint.getOA(result.getId());
					List<Metadata> metadatos = callMetadata.execute().body();
					OA oa = new OA();
					oa.setUrl(conexion.getUrl()+"/xmlui/handle/"+result.getHandle());
					for(Metadata metadata : metadatos){
						if(metadata.getKey().equals("dc.contributor.author")){
							oa.setAutor(metadata.getValue());
						}else if(metadata.getKey().equals("dc.title")){
							oa.setTitulo(metadata.getValue());
						}else if(metadata.getKey().equals("dc.date.issued")){
							oa.setFecha(metadata.getValue());
						}else if(metadata.getKey().equals("dc.description.abstract")){
							oa.setAbst(metadata.getValue());
						}
					}
					oasList.add(oa);
				}
				oas.setOas(oasList);
			}catch(Exception e){
				e.printStackTrace();
			}	
		}

		doGet(req, resp);
	}

}
