package ServicioEndPoint;

import entity.servicio;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;

@Path("/")
public class EndPoint{
    
    @Context
    private ServletContext servletContext;
    
    @GET
    @Path("/ping")
    public String ping(){
        return "OK";
    }
    
    @GET
    @Path("/ObtenerServicio/codigoServicio/{codigoServicio}")
    public String retornarDatosServicio(@PathParam("codigoServicio") String codigoServicio){

        Map<String,servicio> map = (Map<String,servicio>) servletContext.getAttribute("tablaRecursos");
        String respuesta="";
            
        try{
            servicio s = map.get(codigoServicio);
            respuesta = s.getUrl()+","+s.getTypeService()+","+s.getService_getInfoFactura()+","+s.getService_PagarFactura()+","+s.getService_CopensarFactura();      
        }
        catch(Exception e){
            return "ERROR : no se encuentra registrado,x,x,x";
        }
        
        
        //servicio s1 = map.get("A001");
        return respuesta;
    }
    @GET
    @Path("/ObtenerServicios")
    public String retornarServicioTabla(){
        
        String respuesta="";
        
        Map<String,servicio> map = (Map<String,servicio>) servletContext.getAttribute("tablaRecursos");
        String cantidad=map.keySet().toString();
        String[] keys = cantidad.substring(1,cantidad.length()-1).split(",");
        Iterator iterator = map.keySet().iterator();
        
        int poss = 0;
        while(iterator.hasNext()){
            String consultaKey = keys[poss].trim();
            servicio servicioo = map.get(consultaKey);
            respuesta = respuesta +"#"+(poss+1)+ " - Codigo: "+keys[poss]+" - Servicio: "+servicioo.getTypeService()+" - Servicios["+servicioo.getService_getInfoFactura()+","+servicioo.getService_PagarFactura()+","+servicioo.getService_CopensarFactura()+"] - Path: "+servicioo.getUrl()+"<br>";
            poss++;
            iterator.next();
        }
        return respuesta;
    }

    @GET
    @Path("/AdicionarServicio/codigo/{codigo}/type/{type}/InfoFactura/{InfoFactura}/PagarFactura/{PagarFactura}/CopensarFactura/{CopensarFactura}/path/{path: [a-zA-A0-9:_///-]+}")
    public String adicionarServicio(@PathParam("codigo") String codigo
                                   ,@PathParam("type") String type
                                   ,@PathParam("InfoFactura") String InfoFactura
                                   ,@PathParam("PagarFactura") String PagarFactura
                                   ,@PathParam("CopensarFactura") String CopensarFactura
                                   ,@PathParam("path") String path){
        servicio servicio = new servicio();
        servicio.setUrl("/path/localhost:8082/servicios/pagos/v1/payments");
        servicio.setTypeService("soap");
        servicio.setService_CopensarFactura("X");
        servicio.setService_PagarFactura("X");
        servicio.setService_InfoFactura("X");
        Map<String,servicio> map = (Map<String,servicio>) servletContext.getAttribute("tablaRecursos");
        map.put(codigo, servicio);
        return "servicio adicionado correctamente";     
    }
    
    @GET
    @Path("/RemoverServicio/CodigoServicio/{CodigoServicio}")
    public String eliminarServicio(@PathParam("CodigoServicio") String CodigoServicio){
        
        Map<String,servicio> map = (Map<String,servicio>) servletContext.getAttribute("tablaRecursos");
        if((map.remove(CodigoServicio)==null)){
            return "ERROR, No existe el servicio: "+CodigoServicio;
        }
        String respuesta = "Servicio: "+CodigoServicio+" eliminado correctamente";
        return respuesta;
    }
    

    
}
