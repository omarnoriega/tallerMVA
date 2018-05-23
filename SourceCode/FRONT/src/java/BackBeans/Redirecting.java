package BackBeans;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class Redirecting {
    
    private String message = "redirecting..."; 

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    @PostConstruct
    public void redirectAction(){
        
        System.out.println("redirecting");
        FacesContext facesContext = FacesContext.getCurrentInstance();
        
            try {
                facesContext.getExternalContext().redirect("/FRONT/faces/TestServices.xhtml");
            } catch (IOException ex) {
                
            }

    }
}
