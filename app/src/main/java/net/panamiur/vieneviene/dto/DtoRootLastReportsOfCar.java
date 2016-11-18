package net.panamiur.vieneviene.dto;

/**
 * Created by gnu on 17/11/16.
 */

public class DtoRootLastReportsOfCar {

    private int id;
    private String hashDevice;
    private String battery;
    private double lon;
    private double lat;
    private String dateCapture;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBattery() {
        return battery;
    }

    public void setBattery(String battery) {
        this.battery = battery;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getDateCapture() {
        return dateCapture;
    }

    public void setDateCapture(String dateCapture) {
        this.dateCapture = dateCapture;
    }

    public String getHashDevice() {
        return hashDevice;
    }

    public void setHashDevice(String hashDevice) {
        this.hashDevice = hashDevice;
    }
}
