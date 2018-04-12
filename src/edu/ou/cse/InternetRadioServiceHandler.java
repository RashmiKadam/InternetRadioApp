package edu.ou.cse;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.sparql.engine.http.QueryEngineHTTP;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

/**
 * Servlet implementation class InternetRadioServiceHandler
 */
@WebServlet("/InternetRadioServiceHandler")
public class InternetRadioServiceHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VOICENAME="kevin16";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InternetRadioServiceHandler() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Voice voice;
		String state = request.getParameter("state");
		String country = null;
		String gemstone = null;
		String incomerank = null;
		String governor = null;
		String highestpoint = null;
		String lowestpoint = null;
		String densityrank = null;
		String largestmetro = null;
		ArrayList senators = new ArrayList();
		ArrayList nicknames = new ArrayList();
		String website = null;
		
		VoiceManager vm = VoiceManager.getInstance();
		voice=vm.getVoice(VOICENAME);
	    voice.allocate();
	    
		System.out.println(state);
		String query1 ="SELECT ?country " + 
			       " WHERE { " + 
			       " {  <http://dbpedia.org/resource/" + state + "> <http://dbpedia.org/ontology/country>  ?country } " +
			     " }";
		Query query = QueryFactory.create(query1);
		try ( QueryExecution qexec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query) ) {
	           // Set the DBpedia specific timeout.
	           ((QueryEngineHTTP)qexec).addParam("timeout", "10000") ;
	           // Execute.
	           ResultSet rs = qexec.execSelect();
	           while (rs.hasNext()) {
		   		    org.apache.jena.query.QuerySolution row = rs.nextSolution();
		   		    RDFNode node = row.get("country");
		   		    
		   		    if(node.isResource()){
		   		    	country = ((Resource) node).getLocalName(); 
		   		    } else if(node.isLiteral()){
		   		    	country = node.asLiteral().getString();
		   		    }
		   		    country = country.replace("_", "");
		   		    System.out.println(country);
	           }
	    } catch (Exception e) {
	           e.printStackTrace();
	    } 
		
		String query2 ="SELECT ?nickname " + 
			       " WHERE { " + 
			       " {  <http://dbpedia.org/resource/" + state + "> <http://dbpedia.org/property/nickname>  ?nickname } " +
			     " }";
		query = QueryFactory.create(query2);
		try ( QueryExecution qexec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query) ) {
	           // Set the DBpedia specific timeout.
	           ((QueryEngineHTTP)qexec).addParam("timeout", "10000") ;
	           // Execute.
	           ResultSet rs = qexec.execSelect();
	           while (rs.hasNext()) {
		   		    org.apache.jena.query.QuerySolution row = rs.nextSolution();
		   		    String nickname = "";
		   		 RDFNode node = row.get("nickname");
		   		    if(node.isResource()){
		   		    	nickname = ((Resource) node).getLocalName(); 
		   		    } else if(node.isLiteral()){
		   		    	nickname = node.asLiteral().getString();
		   		    }
		   		    String[] words = nickname.split(",");
		   		    for(String str : words){
		   		    	str = str.replace("\"", "");
		   		    	System.out.println(str);
			   		    nicknames.add(str);
		   		    }
	           }
	    } catch (Exception e) {
	           e.printStackTrace();
	    } 
		
		String query3 ="SELECT ?gemstone " + 
			       " WHERE { " + 
			       " {  <http://dbpedia.org/resource/" + state + "> <http://dbpedia.org/property/gemstone>  ?gemstone } " +
			     " }";
		query = QueryFactory.create(query3);
		try ( QueryExecution qexec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query) ) {
	           // Set the DBpedia specific timeout.
	           ((QueryEngineHTTP)qexec).addParam("timeout", "10000") ;
	           // Execute.
	           ResultSet rs = qexec.execSelect();
	           while (rs.hasNext()) {
		   		    org.apache.jena.query.QuerySolution row = rs.nextSolution();
		   		    RDFNode node = row.get("gemstone");
		   		    if(node.isResource()){
		   		    	gemstone = ((Resource) node).getLocalName(); 
		   		    } else if(node.isLiteral()){
		   		    	gemstone = node.asLiteral().getString();
		   		    }
		   		    gemstone = gemstone.replace("_", "");
		   		    System.out.println(gemstone);
	           }
	     } catch (Exception e) {
	           e.printStackTrace();
	     } 
		String query4 ="SELECT ?incomerank " + 
			       " WHERE { " + 
			       " {  <http://dbpedia.org/resource/" + state + "> <http://dbpedia.org/property/incomerank>  ?incomerank } " +
			     " }";
		query = QueryFactory.create(query4);
		try ( QueryExecution qexec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query) ) {
		     // Set the DBpedia specific timeout.
		     ((QueryEngineHTTP)qexec).addParam("timeout", "10000") ;
		     // Execute.
		     ResultSet rs = qexec.execSelect();
		     while (rs.hasNext()) {
				    org.apache.jena.query.QuerySolution row = rs.nextSolution();
				    RDFNode node = row.get("incomerank");
		   		    if(node.isResource()){
		   		    	incomerank = ((Resource) node).getLocalName(); 
		   		    } else if(node.isLiteral()){
		   		    	incomerank = node.asLiteral().getString();
		   		    }
				    System.out.println(incomerank);
		     }
		 } catch (Exception e) {
		     e.printStackTrace();
		 }
		String query5 ="SELECT ?governor " + 
			       " WHERE { " + 
			       " {  <http://dbpedia.org/resource/" + state + "> <http://dbpedia.org/property/governor>  ?governor } " +
			     " }";
		query = QueryFactory.create(query5);
		try ( QueryExecution qexec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query) ) {
		     // Set the DBpedia specific timeout.
		     ((QueryEngineHTTP)qexec).addParam("timeout", "10000") ;
		     // Execute.
		     ResultSet rs = qexec.execSelect();
		     while (rs.hasNext()) {
				    org.apache.jena.query.QuerySolution row = rs.nextSolution();
				    RDFNode node = row.get("governor");
		   		    if(node.isResource()){
		   		    	governor = ((Resource) node).getLocalName(); 
		   		    } else if(node.isLiteral()){
		   		    	governor = node.asLiteral().getString();
		   		    }
		   		    governor = governor.replace("_", "");
				    System.out.println(governor);
			}
		 } catch (Exception e) {
		     e.printStackTrace();
		 } 
		String query6 ="SELECT ?highestpoint ?lowestpoint " + 
			       " WHERE { " + 
			       " {  <http://dbpedia.org/resource/" + state + "> <http://dbpedia.org/property/highestpoint>  ?highestpoint; <http://dbpedia.org/property/lowestpoint> ?lowestpoint  } " +
			     " }";
		query = QueryFactory.create(query6);
		try ( QueryExecution qexec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query) ) {
		     // Set the DBpedia specific timeout.
		     ((QueryEngineHTTP)qexec).addParam("timeout", "10000") ;
		     // Execute.
		     ResultSet rs = qexec.execSelect();
		     while (rs.hasNext()) {
				    org.apache.jena.query.QuerySolution row = rs.nextSolution();
				    RDFNode node = row.get("highestpoint");
		   		    if(node.isResource()){
		   		    	highestpoint = ((Resource) node).getLocalName(); 
		   		    } else if(node.isLiteral()){
		   		    	highestpoint = node.asLiteral().getString();
		   		    }
		   		    highestpoint = highestpoint.replace("_", "");
				    System.out.println(highestpoint);
				    node = row.get("lowestpoint");
		   		    if(node.isResource()){
		   		    	lowestpoint = ((Resource) node).getLocalName(); 
		   		    } else if(node.isLiteral()){
		   		    	lowestpoint = node.asLiteral().getString();
		   		    }
		   		    lowestpoint = lowestpoint.replace("_", "");
				    System.out.println(lowestpoint);
			}
		 } catch (Exception e) {
		     e.printStackTrace();
		 } 
		String query7 ="SELECT ?densityrank " + 
			       " WHERE { " + 
			       " {  <http://dbpedia.org/resource/" + state + "> <http://dbpedia.org/property/densityrank>  ?densityrank } " +
			     " }";
		query = QueryFactory.create(query7);
		try ( QueryExecution qexec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query) ) {
		     // Set the DBpedia specific timeout.
		     ((QueryEngineHTTP)qexec).addParam("timeout", "10000") ;
		     // Execute.
		     ResultSet rs = qexec.execSelect();
		     while (rs.hasNext()) {
				    org.apache.jena.query.QuerySolution row = rs.nextSolution();
				    RDFNode node = row.get("densityrank");
		   		    if(node.isResource()){
		   		    	densityrank = ((Resource) node).getLocalName(); 
		   		    } else if(node.isLiteral()){
		   		    	densityrank = node.asLiteral().getString();
		   		    }
				    System.out.println(densityrank);
			}
		 } catch (Exception e) {
		     e.printStackTrace();
		 } 
		String query8 ="SELECT ?largestmetro " + 
			       " WHERE { " + 
			       " {  <http://dbpedia.org/resource/" + state + "> <http://dbpedia.org/property/largestmetro>  ?largestmetro } " +
			     " }";
		query = QueryFactory.create(query8);
		try ( QueryExecution qexec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query) ) {
		     // Set the DBpedia specific timeout.
		     ((QueryEngineHTTP)qexec).addParam("timeout", "10000") ;
		     // Execute.
		     ResultSet rs = qexec.execSelect();
		     while (rs.hasNext()) {
				    org.apache.jena.query.QuerySolution row = rs.nextSolution();
				    RDFNode node = row.get("largestmetro");
		   		    if(node.isResource()){
		   		    	largestmetro = ((Resource) node).getLocalName(); 
		   		    } else if(node.isLiteral()){
		   		    	largestmetro = node.asLiteral().getString();
		   		    }
		   		    largestmetro = largestmetro.replace("_", "");
				    System.out.println(largestmetro);
			}
		 } catch (Exception e) {
		     e.printStackTrace();
		 } 
		String query9 ="SELECT ?value " + 
			       " WHERE { " + 
			       " {  <http://dbpedia.org/resource/" + state + "> <http://dbpedia.org/property/senators>  ?value } " +
			     " } ORDER BY ?value";
		query = QueryFactory.create(query9);
		try ( QueryExecution qexec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query) ) {
		     // Set the DBpedia specific timeout.
		     ((QueryEngineHTTP)qexec).addParam("timeout", "10000") ;
		     // Execute.
		     ResultSet rs = qexec.execSelect();
		    // String senator = "";
		     while (rs.hasNext()) {
				    org.apache.jena.query.QuerySolution row = rs.nextSolution();
				    RDFNode node = row.get("value");
				    String senator = "";
		   		    if(node.isResource()){
		   		    	senator = ((Resource) node).getLocalName();
		   		    	senator = senator.replace("_", "");
		   		    	System.out.println(senator);
		   		    	senators.add(senator); 
		   		    } else if(node.isLiteral()){
		   		    	senator = node.asLiteral().getString();
		   		    	senator = senator.replace("_", "");
		   		    	System.out.println(senator);
		   		    	senators.add(senator);
		   		    }
			}
		 } catch (Exception e) {
		     e.printStackTrace();
		 } 
		String query10 ="SELECT ?website " + 
			       " WHERE { " + 
			       " {  <http://dbpedia.org/resource/" + state + "> <http://dbpedia.org/property/website>  ?website } " +
			     " }";
		query = QueryFactory.create(query10);
		try ( QueryExecution qexec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query) ) {
		     // Set the DBpedia specific timeout.
		     ((QueryEngineHTTP)qexec).addParam("timeout", "10000") ;
		     // Execute.
		     ResultSet rs = qexec.execSelect();
		     while (rs.hasNext()) {
				    org.apache.jena.query.QuerySolution row = rs.nextSolution();
				    RDFNode node = row.get("website");
		   		    if(node.isResource()){
		   		    	website = ((Resource) node).getLocalName(); 
		   		    	website = website.replace("_", "");
		   		    } else if(node.isLiteral()){
		   		    	website = node.asLiteral().getString();
		   		    	website = website.replace("_", "");
		   		    }
				    System.out.println(website);
			}
		 } catch (Exception e) {
		     e.printStackTrace();
		 } 
		voice.speak(state + "is one of the state located in " + country + ". The nicknames of "+ state + " are " );
			for (int i=0; i<nicknames.size();i++)
	        {   
	       	 voice.speak(nicknames.get(i).toString());
	        }
		voice.speak( ". The largest metro of" + state + "is " + largestmetro + ". The " + state + " has a density rank of" + densityrank + "and an income rank of " + incomerank 
        		  + ". The " + state + "has elevation of highest point at " + highestpoint + " and lowest point at " + lowestpoint + "." + governor + " is the"+ state + "governor." 
        		  + " And senators are");
             for (int i=0; i<senators.size();i++)
             {   
            	 voice.speak(senators.get(i).toString());
             }
        voice.speak(state + "has many natural resources and one of them is " + gemstone + " gemstone. More information can be found at the state's website "
            		  + website + "Thank you for listening.");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}