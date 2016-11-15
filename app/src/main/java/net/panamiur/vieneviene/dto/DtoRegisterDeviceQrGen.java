package net.panamiur.vieneviene.dto;

/**
 * Created by gnu on 10/11/16.
 */

public class DtoRegisterDeviceQrGen {

    private String hashDevice;
    private String regId;
    private int type;

    public String getHashDevice() {
        return hashDevice;
    }

    public DtoRegisterDeviceQrGen setHashDevice(String hashDevice) {
        this.hashDevice = hashDevice;
        return this;
    }

    public String getRegId() {
        return regId;
    }

    public DtoRegisterDeviceQrGen setRegId(String regId) {
        this.regId = regId;
        return this;
    }

    public int getType() {
        return type;
    }

    public DtoRegisterDeviceQrGen setType(int type) {
        this.type = type;
        return this;
    }
}
