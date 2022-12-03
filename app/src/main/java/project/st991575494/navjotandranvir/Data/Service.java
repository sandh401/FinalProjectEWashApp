package project.st991575494.navjotandranvir.Data;

import java.io.Serializable;

public class Service implements Serializable {

    private String vehicle;
    private String serviceType;
    private String date;
    private double subtotal;
    private double total;
    private double tax;
    private String additionalNotes;
    private String uid;

    public Service(String vehicle, String serviceType, String date) {
        this.vehicle = vehicle;
        this.serviceType = serviceType;
        this.date = date;

        if(vehicle.equals("SEDAN")){
            subtotal = 60.60;

        }

        if(vehicle.equals("SUV")){
            subtotal = 88.60;

        }

        if(vehicle.equals("HATCH BACK")){
            subtotal = 30.60;

        }

        if(serviceType.equals("DEEP CLEAN")){
            subtotal += 20.60;
        }

        if(serviceType.equals("DRY CLEAN")){
            subtotal += 15.60;
        }
        if(serviceType.equals("OUTER SHOWER")){
            subtotal += 8.60;
        }

        tax = subtotal * 0.13;
        total = tax + subtotal;

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
        this.total = total;
    }
    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double stotal) {
        this.subtotal = stotal;
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
}
