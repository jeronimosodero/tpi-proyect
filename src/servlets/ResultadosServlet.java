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
		
		
		String path = getServletContext().getRealPath("");
		String dataFolderPath = path + "data";
		File folder = new File(dataFolderPath);
        folder.mkdirs();
        File[] listOfFiles = folder.listFiles();
		ArrayList<OA> oasList = new ArrayList<OA>();
        for (File file : listOfFiles) {
    		FileInputStream fis = new FileInputStream(file);
    		InputStreamReader inputStreamReader = new InputStreamReader((InputStream)fis, "UTF-8");
    		BufferedReader br = new BufferedReader(inputStreamReader);
    		String line;
    		StringBuilder sb = new StringBuilder();
    		while ((line = br.readLine()) != null) {
    		    sb.append(line);
    		}
    		Document doc = Jsoup.parse(sb.toString(), "UTF-8", Parser.xmlParser());
    		Elements records;
    		if(busqueda == null){
        		records = doc.select("record");
        		for(int i = 0;i<keys.length;i++){
        			String query = "record:has(" + keys[i] + ":contains(" + values[i] + "))";
        			System.out.println(query);
        			records = records.select(query);
        		}	
    		}else{
        		String query = "record:contains("+ busqueda + ")";
        		records = doc.select(query);
    		}
    		for(Element record: records){
    			OA oa = new OA();
    			if(file.getName().contains("dc")){
        			oa.setAbst(record.select("dc|description").text());
        			oa.setAutor(record.select("dc|creator").text());
        			oa.setFecha(record.select("datestamp").text());
        			oa.setTitulo(record.select("dc|title").text());
        			oa.setUrl("http://" + record.select("identifier").text().substring(4).replace(":", "/"));	
    			}else{
        			oa.setAbst(record.select("mods|abstract").text());
        			oa.setAutor(record.select("mods|name").text());
        			oa.setFecha(record.select("datestamp").text());
        			oa.setTitulo(record.select("mods|title").text());
        			oa.setUrl("http://" + record.select("identifier").text().substring(4).replace(":", "/"));	
    			}

    			oasList.add(oa);
    		}
        }
		System.out.println(oasList.size());
		oas.setOas(oasList);
		
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
