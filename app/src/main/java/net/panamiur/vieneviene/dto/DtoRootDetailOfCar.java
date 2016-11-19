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
    private String modelDevice;

    public int getId() {
        return id;
    }

    public DtoRootDetailOfCar setId(int id) {
        this.id = id;
        return this;
    }

    public String getHashDevice() {
        return hashDevice;
    }

    public DtoRootDetailOfCar setHashDevice(String hashDevice) {
        this.hashDevice = hashDevice;
        return this;
    }

    public String getRegId() {
        return regId;
    }

    public DtoRootDetailOfCar setRegId(String regId) {
        this.regId = regId;
        return this;
    }

    public String getNameCar() {
        return nameCar;
    }

    public DtoRootDetailOfCar setNameCar(String nameCar) {
        this.nameCar = nameCar;
        return this;
    }

    public String getColor() {
        return color;
    }

    public DtoRootDetailOfCar setColor(String color) {
        this.color = color;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public DtoRootDetailOfCar setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public DtoRootDetailOfCar setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public DtoRootDetailOfCar setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
        return this;
    }

    public String getModelDevice() {
        return modelDevice;
    }

    public DtoRootDetailOfCar setModelDevice(String modelDevice) {
        this.modelDevice = modelDevice;
        return this;
    }
}
