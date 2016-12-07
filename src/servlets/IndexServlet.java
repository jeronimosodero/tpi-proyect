package servlets;
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

import classes.Metadata;
import classes.OA;
import classes.OAs;
import classes.Request;
import classes.Result;
import classes.ServiceGenerator;
import endpoints.Endpoint;
import retrofit2.Call;
import retrofit2.Response;

public class IndexServlet extends HttpServlet {
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
		String title = req.getParameter("dc.title");
		String author = req.getParameter("dc.contributor.author");
		Endpoint endpoint = ServiceGenerator.createService(Endpoint.class);
		Call<List<Result>> call = endpoint.findByMetadataField(new Request("dc.contributor.author","Pérez, Manuel",null));
		try{
			List<Result> results = call.execute().body();
			ArrayList<OA> oasList = new ArrayList<OA>();
			for(Result result : results){
				Call<List<Metadata>> callMetadata = endpoint.getOA(result.getId());
				List<Metadata> metadatos = callMetadata.execute().body();
				OA oa = new OA();
				oa.setUrl("http://localhost:8080/xmlui/handle/"+result.getHandle());
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
		doGet(req, resp);
	}
}
