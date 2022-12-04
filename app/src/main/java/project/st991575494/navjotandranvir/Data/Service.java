package project.st991575494.navjotandranvir.Data;

import java.io.Serializable;
import java.text.DecimalFormat;

public class Service implements Serializable {

    private String vehicle;
    private String status = "pending";
    private String serviceType;
    private String date;
    private double subtotal;
    private double total;
    private double tax;
    private String additionalNotes;
    private String uid;
    DecimalFormat df = new DecimalFormat("#.##");
    public Service(){

    }
    public Service(String vehicle, String serviceType, String date) {
        this.vehicle = vehicle;
        this.serviceType = serviceType;
        this.date = date;

        if(vehicle.equalsIgnoreCase("SEDAN")){
            setSubtotal(getSubtotal()+60.60);

        }

        if(vehicle.equalsIgnoreCase("SUV")){
            setSubtotal(getSubtotal()+88.60);

        }

        if(vehicle.equalsIgnoreCase("HATCH BACK")){
            setSubtotal(getSubtotal()+30.60);

        }

        if(serviceType.equalsIgnoreCase("DEEP CLEAN")){
            setSubtotal(getSubtotal()+20.60);
        }

        if(serviceType.equalsIgnoreCase("DRY CLEAN")){
            setSubtotal(getSubtotal()+5.60);
        }
        if(serviceType.equalsIgnoreCase("OUTER SHOWER")){
            setSubtotal(getSubtotal()+8.60);
        }
        DecimalFormat df = new DecimalFormat("#.##");
        setTax(subtotal * 0.13);
        setTotal((tax + subtotal));

    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = Double.valueOf(df.format(total));
    }
    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = Double.valueOf(df.format(tax));
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double stotal) {

        this.subtotal = Double.valueOf(df.format(stotal));

    }

    public String getAdditionalNotes() {
        return additionalNotes;
    }

    public void setAdditionalNotes(String additionalNotes) {
        this.additionalNotes = additionalNotes;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
