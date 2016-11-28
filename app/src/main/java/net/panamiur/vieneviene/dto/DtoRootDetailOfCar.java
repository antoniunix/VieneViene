package net.panamiur.vieneviene.dto;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by gnu on 17/11/16.
 */

public class DtoRootDetailOfCar implements Parcelable {
    private int id;
    private String hashDevice;
    private String regId;
    private String nameCar;
    private String color;
    private String phoneNumber;
    private String description;
    private String dateCreate;
    private String modelDevice;
    private long batteryLevel;
    private float sensitiveMovement;

    public float getSensitiveMovement() {
        return sensitiveMovement;
    }

    public void setSensitiveMovement(float sensitiveMovement) {
        this.sensitiveMovement = sensitiveMovement;
    }

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

    public long getBatteryLevel() {
        return batteryLevel;
    }

    public DtoRootDetailOfCar setBatteryLevel(long batteryLevel) {
        this.batteryLevel = batteryLevel;
        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.hashDevice);
        dest.writeString(this.regId);
        dest.writeString(this.nameCar);
        dest.writeString(this.color);
        dest.writeString(this.phoneNumber);
        dest.writeString(this.description);
        dest.writeString(this.dateCreate);
        dest.writeString(this.modelDevice);
        dest.writeLong(this.batteryLevel);
    }

    public DtoRootDetailOfCar() {
    }

    protected DtoRootDetailOfCar(Parcel in) {
        this.id = in.readInt();
        this.hashDevice = in.readString();
        this.regId = in.readString();
        this.nameCar = in.readString();
        this.color = in.readString();
        this.phoneNumber = in.readString();
        this.description = in.readString();
        this.dateCreate = in.readString();
        this.modelDevice = in.readString();
        this.batteryLevel = in.readLong();
    }

    public static final Parcelable.Creator<DtoRootDetailOfCar> CREATOR = new Parcelable.Creator<DtoRootDetailOfCar>() {
        @Override
        public DtoRootDetailOfCar createFromParcel(Parcel source) {
            return new DtoRootDetailOfCar(source);
        }

        @Override
        public DtoRootDetailOfCar[] newArray(int size) {
            return new DtoRootDetailOfCar[size];
        }
    };
}
