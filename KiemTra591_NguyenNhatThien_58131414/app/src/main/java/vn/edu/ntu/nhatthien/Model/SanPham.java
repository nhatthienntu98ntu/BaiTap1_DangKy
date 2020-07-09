package vn.edu.ntu.nhatthien.Model;

public class SanPham {
    String ngay, loai, muaVao, banRa;

    public SanPham(String ngay, String loai, String muaVao, String banRa) {
        this.ngay = ngay;
        this.loai = loai;
        this.muaVao = muaVao;
        this.banRa = banRa;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public String getMuaVao() {
        return muaVao;
    }

    public void setMuaVao(String muaVao) {
        this.muaVao = muaVao;
    }

    public String getBanRa() {
        return banRa;
    }

    public void setBanRa(String banRa) {
        this.banRa = banRa;
    }
}
