package id.co.kosankoding.belajarfastnetworking.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponsePenghuni {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<Penghuni> data;

    public String getStatus() {
        return status;
    }

    public List<Penghuni> getData() {
        return data;
    }
}
