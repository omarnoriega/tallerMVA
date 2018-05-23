package com.javainuse.beans;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.javainuse.direcciones.direcciones;


@Path("/")
public class ESBResource {

	public ESBResource() {
	}

	
	@GET
	@Path("/API/getInfoFactura/titular/{titular}/codigo/{codigo}/idFactura/{idFactura}")
	public String informacionFactura(@PathParam("titular") String titular, @PathParam("codigo") String codigo, @PathParam("idFactura") String idFactura) {
		
		String method = "getInfoFactura";
		
		//Armar mensaje en consulta a Routing
		String messageA = direcciones.ROUTING+"/ObtenerServicio/codigoServicio/"+codigo;
		
		//consultar apiServicios
		Client clientA = ClientBuilder.newClient();
        WebTarget targetA = clientA.target(messageA);
        String response = targetA.request(MediaType.TEXT_PLAIN).get(String.class);
        
        //Split
        String[] contenidoMessageRouting = response.split(",");
        String type = contenidoMessageRouting[1];
		String path = contenidoMessageRouting[0];
		String allowed = contenidoMessageRouting[2];
		
		if(path.equals("ERROR : no se encuentra registrado")) {
			return "ERROR - NotFound Service";
		}
		if(!allowed.equals("X")){
			return "ERROR - NotFound Allowed";
		}
		
		//Armar mensaje en consulta de servicios
		String menssajeB = direcciones.API_SERVICIOS+"/type/"+type+"/service/"+method+"/idFactura/"+idFactura+"/valorFactura/0"+path;
		
		//consultar apiServicios
		Client clientB = ClientBuilder.newClient();
        WebTarget targetB = clientB.target(menssajeB);
        return targetB.request(MediaType.TEXT_PLAIN).get(String.class);
		
	}
	@GET
	@Path("/API/pagarFactura/titular/{titular}/codigo/{codigo}/idFactura/{idFactura}/valorFactura/{valorFactura}")
	public String pagarFactura(@PathParam("titular") String titular, @PathParam("codigo") String codigo, @PathParam("idFactura") String idFactura, @PathParam("valorFactura") String valorFactura) {
		
		String method = "pagarFactura";
		
		//Armar mensaje en consulta a Routing
		String messageA = direcciones.ROUTING+"/ObtenerServicio/codigoServicio/"+codigo;
		
		//consultar apiServicios
		Client clientA = ClientBuilder.newClient();
        WebTarget targetA = clientA.target(messageA);
        String response = targetA.request(MediaType.TEXT_PLAIN).get(String.class);
        
        //Split
        String[] contenidoMessageRouting = response.split(",");
        String type = contenidoMessageRouting[1];
		String path = contenidoMessageRouting[0];
		String allowed = contenidoMessageRouting[3];
		
		if(path.equals("ERROR : no se encuentra registrado")) {
			return "ERROR - NotFound Service";
		}
		if(!allowed.equals("X")){
			return "ERROR - NotFound Allowed";
		}
		
		//Armar mensaje en consulta de servicios
		String menssajeB = direcciones.API_SERVICIOS+"/type/"+type+"/service/"+method+"/idFactura/"+idFactura+"/valorFactura/"+valorFactura+path;
		
		//consultar apiServicios
		Client clientB = ClientBuilder.newClient();
        WebTarget targetB = clientB.target(menssajeB);
        return targetB.request(MediaType.TEXT_PLAIN).get(String.class);
		
		
	}
	@GET
	@Path("/API/copensarFactura/titular/{titular}/codigo/{codigo}/idFactura/{idFactura}/valorFactura/{valorFactura}")
	public String copensarFactura(@PathParam("titular") String titular, @PathParam("codigo") String codigo, @PathParam("idFactura") String idFactura, @PathParam("valorFactura") String valorFactura) {
		
		String method = "copensarFactura";
		
		//Armar mensaje en consulta a Routing
		String messageA = direcciones.ROUTING+"/ObtenerServicio/codigoServicio/"+codigo;
				
		//consultar apiServicios
		Client clientA = ClientBuilder.newClient();
		WebTarget targetA = clientA.target(messageA);
		String response = targetA.request(MediaType.TEXT_PLAIN).get(String.class);
		        
		//Split
		String[] contenidoMessageRouting = response.split(",");
		String type = contenidoMessageRouting[1];
		String path = contenidoMessageRouting[0];
		String allowed = contenidoMessageRouting[4];
				
		if(path.equals("ERROR : no se encuentra registrado")) {
			return "ERROR - NotFound Service";
		}
		if(!allowed.equals("X")){
			return "ERROR - NotFound Allowed";
		}
				
		//Armar mensaje en consulta de servicios
		String menssajeB = direcciones.API_SERVICIOS+"/type/"+type+"/service/"+method+"/idFactura/"+idFactura+"/valorFactura/"+valorFactura+path;
				
		//consultar apiServicios
		Client clientB = ClientBuilder.newClient();
		WebTarget targetB = clientB.target(menssajeB);
		return targetB.request(MediaType.TEXT_PLAIN).get(String.class);
		
	}
	
	
	
	@GET
    @Path("/ping")
    public String ping() {
        return "OK ";
    }
	

}