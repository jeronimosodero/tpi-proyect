package servlets;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.sparql.engine.http.QueryEngineHTTP;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import classes.Metadata;
import classes.OA;
import classes.OAs;

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
		System.out.println("doget");
		ServletContext sc = getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher("/buscador.jsp");
		
		rd.forward(req,resp);
		System.out.println("doget");
	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		oas = new OAs();
		String busqueda = req.getParameter("busqcampo");
		
		String[] keys = req.getParameterValues("parametros");
		String[] values = req.getParameterValues("campo");
		
		
		FileInputStream fis = new FileInputStream(new File("C:/Users/Jero/Downloads/example.xml"));
		InputStreamReader inputStreamReader = new InputStreamReader((InputStream)fis, "UTF-8");
		BufferedReader br = new BufferedReader(inputStreamReader);
		String line;
		StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) {
		    sb.append(line);
		}
		Document doc = Jsoup.parse(sb.toString(), "UTF-8", Parser.xmlParser());
		//Document doc = Jsoup.connect("https://rdu.unc.edu.ar/oai/request?verb=ListRecords&metadataPrefix=oai_dc").followRedirects(true).get();
		//String query = "record:contains("+ busqueda + ")";
		String query = "record";
		Elements records = doc.select(query);
		for(int i = 0;i<keys.length;i++){
			query = "record:has(" + keys[i] + ":contains(" + values[i] + "))";
			System.out.println(query);
			records = records.select(query);
		}
		
		ArrayList<OA> oasList = new ArrayList<OA>();
		for(Element record: records){
			OA oa = new OA();
			oa.setAbst(record.select("dc|description").text());
			oa.setAutor(record.select("dc|creator").text());
			oa.setFecha(record.select("datestamp").text());
			oa.setTitulo(record.select("dc|title").text());
			oa.setUrl("http://" + record.select("identifier").text().substring(4).replace(":", "/"));
			oasList.add(oa);
		}
		System.out.println(oasList.size());
		oas.setOas(oasList);
		
		/*
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
		*/

		doGet(req, resp);
	}
	
	public ResultSet executeQuery(String queryString) throws Exception {
		 Query query = QueryFactory.create(queryString) ;
		 String service = "";
		 String apikey = "";
		 QueryEngineHTTP qexec = QueryExecutionFactory.createServiceRequest(service, query);
		 qexec.addParam("apikey", apikey);
		 ResultSet results = qexec.execSelect() ;
		 return results;

	}

}
