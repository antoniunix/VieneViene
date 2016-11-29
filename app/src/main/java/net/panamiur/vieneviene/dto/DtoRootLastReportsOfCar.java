package net.panamiur.vieneviene.dto;

/**
 * Created by gnu on 17/11/16.
 */

public class DtoRootLastReportsOfCar {

    private int id;
    private String hashDevice;
    private String battery;
    private String speed;
    private double lon;
    private double lat;
    private String dateCapture;
    private String nameCar;
    private String color;

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getNameCar() {
        return nameCar;
    }

    public void setNameCar(String nameCar) {
        this.nameCar = nameCar;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public DtoRootLastReportsOfCar setId(int id) {
        this.id = id;
        return this;
    }

    public String getBattery() {
        return battery;
    }

    public DtoRootLastReportsOfCar setBattery(String battery) {
        this.battery = battery;
        return this;
    }

    public double getLon() {
        return lon;
    }

    public DtoRootLastReportsOfCar setLon(double lon) {
        this.lon = lon;
        return this;
    }

    public double getLat() {
        return lat;
    }

    public DtoRootLastReportsOfCar setLat(double lat) {
        this.lat = lat;
        return this;
    }

    public String getDateCapture() {
        return dateCapture;
    }

    public DtoRootLastReportsOfCar setDateCapture(String dateCapture) {
        this.dateCapture = dateCapture;
        return this;
    }

    public String getHashDevice() {
        return hashDevice;
    }

    public DtoRootLastReportsOfCar setHashDevice(String hashDevice) {
        this.hashDevice = hashDevice;
        return this;
    }

    @Override
    public String toString() {
        return "DtoRootLastReportsOfCar{" +
                "id=" + id +
                ", hashDevice='" + hashDevice + '\'' +
                ", battery='" + battery + '\'' +
                ", lon=" + lon +
                ", lat=" + lat +
                ", dateCapture='" + dateCapture + '\'' +
                '}';
    }
}
