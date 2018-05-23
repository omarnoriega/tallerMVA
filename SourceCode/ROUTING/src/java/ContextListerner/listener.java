package ContextListerner;

import entity.servicio;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class listener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("******* STARTS *******");
        
        servicio servicio1 = new servicio();
        servicio1.setUrl("/path/localhost:8081/servicios/pagos/v1/payments");
        servicio1.setTypeService("rest");
        servicio1.setService_CopensarFactura("X");
        servicio1.setService_PagarFactura("X");
        servicio1.setService_InfoFactura("X");
        
        servicio servicio2 = new servicio();
        servicio2.setUrl("/path/localhost:8082/servicios/pagos/v1/payments");
        servicio2.setTypeService("soap");
        servicio2.setService_CopensarFactura("X");
        servicio2.setService_PagarFactura("X");
        servicio2.setService_InfoFactura("X");
        
        Map<String,servicio> map = new HashMap<String,servicio>();
        map.put("A001", servicio1);
        map.put("G001", servicio2);
        
        sce.getServletContext().setAttribute("tablaRecursos", map);
        
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("******* ENDS *******");
    }
}
