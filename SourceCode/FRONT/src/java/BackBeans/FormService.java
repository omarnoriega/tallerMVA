package BackBeans;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

@ManagedBean
@SessionScoped
public class FormService implements Serializable{
    
    private String method;
    private String titular;
    private String servicio;
    private String idFactura;
    private String valorFactura;

    private String respuesta;
    
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(String idFactura) {
        this.idFactura = idFactura;
    }

    public String getValorFactura() {
        return valorFactura;
    }

    public void setValorFactura(String valorFactura) {
        this.valorFactura = valorFactura;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
    
    
    
    public void consultar(){
        
        String consulta="";
        switch(method){
            case "getInfoFactura": 
                consulta = "http://localhost:9000/ESB/API/"+method+"/titular/"+titular+"/codigo/"+servicio+"/idFactura/"+idFactura;
                break;
            case "pagarFactura": 
                consulta = "http://localhost:9000/ESB/API/"+method+"/titular/"+titular+"/codigo/"+servicio+"/idFactura/"+idFactura+"/valorFactura/"+valorFactura;
                break;  
            case "copensarFactura":    
                consulta = "http://localhost:9000/ESB/API/"+method+"/titular/"+titular+"/codigo/"+servicio+"/idFactura/"+idFactura+"/valorFactura/"+valorFactura;
                break;
        }
        
        try{
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target(consulta);
            respuesta = target.request(MediaType.TEXT_PLAIN_TYPE).get(String.class);
        }
        catch(Exception e){
            respuesta = "Servicio no encontrado";
        }
    }
    
    @PostConstruct
    public void init(){
        
        
    }
}
