package net.panamiur.vieneviene.dto;

/**
 * Created by gnu on 17/11/16.
 */

public class DtoRootDetailOfCarTemp {

    private int id;
    private String hashDevice;
    private String regId;
    private String dateReceived;
    private String modelDevice;

    public int getId() {
        return id;
    }

    public DtoRootDetailOfCarTemp setId(int id) {
        this.id = id;
        return this;
    }

    public String getHashDevice() {
        return hashDevice;
    }

    public DtoRootDetailOfCarTemp setHashDevice(String hashDevice) {
        this.hashDevice = hashDevice;
        return this;
    }

    public String getRegId() {
        return regId;
    }

    public DtoRootDetailOfCarTemp setRegId(String regId) {
        this.regId = regId;
        return this;
    }

    public String getDateReceived() {
        return dateReceived;
    }

    public DtoRootDetailOfCarTemp setDateReceived(String dateReceived) {
        this.dateReceived = dateReceived;
        return this;
    }

    public String getModelDevice() {
        return modelDevice;
    }

    public void setModelDevice(String modelDevice) {
        this.modelDevice = modelDevice;
    }
}
