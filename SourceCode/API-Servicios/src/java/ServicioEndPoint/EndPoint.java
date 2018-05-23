package ServicioEndPoint;

import Entity.Factura;
import Entity.Resultado;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import soapResources.Pago;
import soapResources.PagosInerface;
import soapResources.PagosServiceService;
import soapResources.ReferenciaFactura;

@Path("/")
public class EndPoint {
    
    @GET
    @Path("/ping")
    public String ping(){
        return "OK";
    }
    
    @GET
    @Path("/type/{type}/service/{service}/idFactura/{idFactura}/valorFactura/{valorFactura}/path/{path: [a-zA-A0-9:_///-]+}")
    @Produces(MediaType.TEXT_PLAIN)
    public String client(@PathParam("type") String type
            ,@PathParam("service") String service
            ,@PathParam("idFactura") Integer idFactura
            ,@PathParam("valorFactura") Double valorFactura
            ,@PathParam("path") String path){
        //return "OK" + "\ntype: "+type+ "\nservice: "+service+ "\nidFactura: "+idFactura+ "\nvalorFactura: "+valorFactura+ "\npath: "+path;
        String respuesta=null;
        switch(type){
            case "rest": 
                respuesta = consumeRest(service,idFactura,valorFactura,path);
                break;
            case "soap":
                respuesta = consumeSOAP(service,idFactura,valorFactura);
                break;
        }
        return respuesta; 
    }
    
    
    private String consumeRest(String serviceSelected,Integer idFactura,Double valorFactura,String path){
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://"+path+"/"+idFactura);
        
        String respuesta="";
        Factura factura = new Factura();
        Resultado resultado;
        switch(serviceSelected){
            case "getInfoFactura": 
                factura = target.request(MediaType.APPLICATION_JSON).get(Factura.class);
                respuesta = factura.getIdFactura()+","+factura.getValorFactura();
                break;
            case "pagarFactura": 
                factura.setIdFactura(idFactura);
                factura.setValorFactura(valorFactura);
                resultado = target.request(MediaType.APPLICATION_JSON).post(Entity.json(factura), Resultado.class);
                respuesta = resultado.getIdFactura()+","+resultado.getMensaje();
                break;
            case "copensarFactura": 
                resultado = target.request(MediaType.APPLICATION_JSON).delete(Resultado.class);
                respuesta = resultado.getIdFactura()+","+resultado.getMensaje();
                break;
            default: return "Error,Error";
        }
        return respuesta;
    }
    private String consumeSOAP(String serviceSelected,Integer idFactura,Double valorFactura){
       PagosServiceService service = new PagosServiceService();
       final PagosInerface proxy = service.getPagosServicePort();
       
       String respuesta = "";
       ReferenciaFactura referenciaFactura=null;
       Pago pago;
       switch(serviceSelected){
            case "getInfoFactura":
                referenciaFactura = new ReferenciaFactura();
                referenciaFactura.setReferenciaFactura(idFactura.toString());
                respuesta = ""+idFactura+","+proxy.cosultar(referenciaFactura).getTotalPagar();
                break;
            case "pagarFactura": 
                pago = new Pago();
                ReferenciaFactura referenciaFactura1 = new ReferenciaFactura();
                referenciaFactura1.setReferenciaFactura(idFactura.toString());
                pago.setReferenciaFactura(referenciaFactura1);
                pago.setTotalPagar(valorFactura);
                respuesta = ""+idFactura+","+proxy.pagar(pago).getMensaje();
                break;
            case "copensarFactura":
                pago = new Pago();
                ReferenciaFactura referenciaFactura2 = new ReferenciaFactura();
                referenciaFactura2.setReferenciaFactura(idFactura.toString());
                pago.setReferenciaFactura(referenciaFactura2);
                pago.setTotalPagar(valorFactura);
                respuesta = ""+idFactura+","+proxy.compensar(pago).getMensaje();
                break;
       
        }
        return respuesta;
    }

}