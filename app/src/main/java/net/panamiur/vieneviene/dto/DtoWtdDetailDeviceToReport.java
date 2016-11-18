package net.panamiur.vieneviene.dto;

/**
 * Created by gnu on 17/11/16.
 */

public class DtoWtdDetailDeviceToReport {

    private int id;
    private String hashDevice;
    private String regId;

    public int getId() {
        return id;
    }

    public DtoWtdDetailDeviceToReport setId(int id) {
        this.id = id;
        return this;
    }

    public String getHashDevice() {
        return hashDevice;
    }

    public DtoWtdDetailDeviceToReport setHashDevice(String hashDevice) {
        this.hashDevice = hashDevice;
        return this;
    }

    public String getRegId() {
        return regId;
    }

    public DtoWtdDetailDeviceToReport setRegId(String regId) {
        this.regId = regId;
        return this;
    }
}
