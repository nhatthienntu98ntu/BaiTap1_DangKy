package vn.edu.ntu.nhatthien.Controller;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import vn.edu.ntu.nhatthien.Model.SanPham;

public class SanPhamController extends Application implements ISanPhamController {
    List<SanPham> dsSP = new ArrayList<>();

    public SanPhamController() {

    }

    @Override
    public boolean add(SanPham sp) {
        if(dsSP.contains(sp)){
            return false;
        }
        dsSP.add(sp);
        return true;
    }

    @Override
    public List<SanPham> getAllSP() {
        return dsSP;
    }
}
