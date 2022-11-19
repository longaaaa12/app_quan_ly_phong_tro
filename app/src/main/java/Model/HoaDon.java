package Model;

public class HoaDon {
    private  int IdHoaDon;
    private String Ngay;
    private  int SoDien;
    private  int SoNuoc;
    private  int Tong;
    private String ChiPhiKhac;
    private String GhiChu;
    private int IdPhong;
    private int IdHopDong;

    public HoaDon() {
    }

    public HoaDon(int idHoaDon, String ngay, int soDien, int soNuoc, int tong, String chiPhiKhac, String ghiChu, int idPhong, int idHopDong) {
        IdHoaDon = idHoaDon;
        Ngay = ngay;
        SoDien = soDien;
        SoNuoc = soNuoc;
        Tong = tong;
        ChiPhiKhac = chiPhiKhac;
        GhiChu = ghiChu;
        IdPhong = idPhong;
        IdHopDong = idHopDong;
    }

    public int getIdHoaDon() {
        return IdHoaDon;
    }

    public void setIdHoaDon(int idHoaDon) {
        IdHoaDon = idHoaDon;
    }

    public String getNgay() {
        return Ngay;
    }

    public void setNgay(String ngay) {
        Ngay = ngay;
    }

    public int getSoDien() {
        return SoDien;
    }

    public void setSoDien(int soDien) {
        SoDien = soDien;
    }

    public int getSoNuoc() {
        return SoNuoc;
    }

    public void setSoNuoc(int soNuoc) {
        SoNuoc = soNuoc;
    }

    public int getTong() {
        return Tong;
    }

    public void setTong(int tong) {
        Tong = tong;
    }

    public String getChiPhiKhac() {
        return ChiPhiKhac;
    }

    public void setChiPhiKhac(String chiPhiKhac) {
        ChiPhiKhac = chiPhiKhac;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String ghiChu) {
        GhiChu = ghiChu;
    }

    public int getIdPhong() {
        return IdPhong;
    }

    public void setIdPhong(int idPhong) {
        IdPhong = idPhong;
    }

    public int getIdHopDong() {
        return IdHopDong;
    }

    public void setIdHopDong(int idHopDong) {
        IdHopDong = idHopDong;
    }
}
