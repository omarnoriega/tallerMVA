package entity;

public class servicio {
    private String url;
    private String typeService;
    private String service_getInfoFactura;
    private String service_pagarFactura;
    private String service_copensarFactura;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTypeService() {
        return typeService;
    }

    public void setTypeService(String typeService) {
        this.typeService = typeService;
    }

    public String getService_getInfoFactura() {
        return service_getInfoFactura;
    }

    public void setService_InfoFactura(String service_getInfoFactura) {
        this.service_getInfoFactura = service_getInfoFactura;
    }

    public String getService_PagarFactura() {
        return service_pagarFactura;
    }

    public void setService_PagarFactura(String pagarFactura) {
        this.service_pagarFactura = pagarFactura;
    }

    public String getService_CopensarFactura() {
        return service_copensarFactura;
    }

    public void setService_CopensarFactura(String copensarFactura) {
        this.service_copensarFactura = copensarFactura;
    }
    
}
