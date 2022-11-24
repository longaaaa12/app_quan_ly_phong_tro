package Model;

import java.io.Serializable;

public class ObjectKhachThue implements Serializable {
    private int IdKhachThue;
    private String HoTen;
    private int SoDienThoai;
    private int Cccd;
    private int SoPhong;

    public ObjectKhachThue() {
    }

    public ObjectKhachThue(int idKhachThue, String hoTen, int soDienThoai, int cccd, int soPhong) {
        IdKhachThue = idKhachThue;
        HoTen = hoTen;
        SoDienThoai = soDienThoai;
        Cccd = cccd;
        SoPhong = soPhong;
    }


    public int getIdKhachThue() {
        return IdKhachThue;
    }

    public void setIdKhachThue(int idKhachThue) {
        IdKhachThue = idKhachThue;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public int getSoDienThoai() {
        return SoDienThoai;
    }

    public void setSoDienThoai(int soDienThoai) {
        SoDienThoai = soDienThoai;
    }

    public int getCccd() {
        return Cccd;
    }

    public void setCccd(int cccd) {
        Cccd = cccd;
    }

    public int getSoPhong() {
        return SoPhong;
    }

    public void setSoPhong(int soPhong) {
        SoPhong = soPhong;
    }
}
