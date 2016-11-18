package net.panamiur.vieneviene.dto;

/**
 * Created by gnu on 17/11/16.
 */

public class DtoRootDetailOfCar {
    private int id;
    private String hashDevice;
    private String regId;
    private String nameCar;
    private String color;
    private String phoneNumber;
    private String description;
    private String dateCreate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHashDevice() {
        return hashDevice;
    }

    public void setHashDevice(String hashDevice) {
        this.hashDevice = hashDevice;
    }

    public String getRegId() {
        return regId;
    }

    public void setRegId(String regId) {
        this.regId = regId;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }
}
