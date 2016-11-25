package net.panamiur.vieneviene.dto;

/**
 * Created by gnu on 16/11/16.
 * Este DTO es usado para la comunicacion FCM. se enviara siempre en la transmicion de mensajes
 */

public class DtoMessageFCMTransaction {

    private int id; // es el que define el tipo de mensaje
    private String obj; // contendrá informacion adicional
    private double lat;
    private double lon;
    private String battery;
    private long time;
    private String hashDevice; //con este has se identificara al dispocitivo emitente del FCM
    private String modelDevice;

    public int getId() {
        return id;
    }

    public DtoMessageFCMTransaction setId(int id) {
        this.id = id;
        return this;
    }

    public String getObj() {
        return obj;
    }

    public DtoMessageFCMTransaction setObj(String obj) {
        this.obj = obj;
        return this;
    }

    public double getLat() {
        return lat;
    }

    public DtoMessageFCMTransaction setLat(double lat) {
        this.lat = lat;
        return this;
    }

    public double getLon() {
        return lon;
    }

    public DtoMessageFCMTransaction setLon(double lon) {
        this.lon = lon;
        return this;
    }

    public String getBattery() {
        return battery;
    }

    public DtoMessageFCMTransaction setBattery(String battery) {
        this.battery = battery;
        return this;
    }

    public long getTime() {
        return time;
    }

    public DtoMessageFCMTransaction setTime(long time) {
        this.time = time;
        return this;
    }

    public String getHashDevice() {
        return hashDevice;
    }

    public DtoMessageFCMTransaction setHashDevice(String hashDevice) {
        this.hashDevice = hashDevice;
        return this;
    }

    public String getModelDevice() {
        return modelDevice;
    }

    public void setModelDevice(String modelDevice) {
        this.modelDevice = modelDevice;
    }
}
