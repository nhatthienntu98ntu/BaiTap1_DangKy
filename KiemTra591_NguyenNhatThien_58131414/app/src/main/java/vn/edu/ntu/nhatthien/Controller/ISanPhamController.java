package vn.edu.ntu.nhatthien.Controller;

import java.util.List;

import vn.edu.ntu.nhatthien.Model.SanPham;

public interface ISanPhamController {
    public boolean add(SanPham sp);
    public List<SanPham> getAllSP();
}
