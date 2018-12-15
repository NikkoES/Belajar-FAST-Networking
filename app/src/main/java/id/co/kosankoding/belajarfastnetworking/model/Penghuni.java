package id.co.kosankoding.belajarfastnetworking.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Penghuni {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("no_hp")
    @Expose
    private String noHp;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("fasilitas")
    @Expose
    private String fasilitas;
    @SerializedName("alamat")
    @Expose
    private String alamat;

    public Penghuni(String id, String nama, String noHp, String gender, String status, String fasilitas, String alamat) {
        this.id = id;
        this.nama = nama;
        this.noHp = noHp;
        this.gender = gender;
        this.status = status;
        this.fasilitas = fasilitas;
        this.alamat = alamat;
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getNoHp() {
        return noHp;
    }

    public String getGender() {
        return gender;
    }

    public String getStatus() {
        return status;
    }

    public String getFasilitas() {
        return fasilitas;
    }

    public String getAlamat() {
        return alamat;
    }
}
