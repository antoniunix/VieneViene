package net.panamiur.vieneviene.dto;

/**
 * Created by gnu on 17/11/16.
 */

public class DtoRootDetailOfCarTemp {

    private int id;
    private String hashDevice;
    private String regId;
    private String dateReceived;

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

    public String getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(String dateReceived) {
        this.dateReceived = dateReceived;
    }
}
