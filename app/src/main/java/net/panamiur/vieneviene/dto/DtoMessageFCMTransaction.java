package net.panamiur.vieneviene.dto;

/**
 * Created by gnu on 16/11/16.
 * Este DTO es usado para la comunicacion FCM. se enviara siempre en la transmicion de mensajes
 */

public class DtoMessageFCMTransaction {

    private int id; // es el que define el tipo de mensaje
    private String obj; // contendrá informacion adicional
    private long lat;
    private long lon;
    private String battery;
    private long time;
    private String hashDevice; //con este has se identificara al dispocitivo emitente del FCM

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

    public long getLat() {
        return lat;
    }

    public DtoMessageFCMTransaction setLat(long lat) {
        this.lat = lat;
        return this;
    }

    public long getLon() {
        return lon;
    }

    public DtoMessageFCMTransaction setLon(long lon) {
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
}
