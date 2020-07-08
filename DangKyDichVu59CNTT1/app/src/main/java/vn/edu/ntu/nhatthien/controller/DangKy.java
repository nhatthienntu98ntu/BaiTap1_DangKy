package vn.edu.ntu.nhatthien.controller;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;


public class DangKy extends Application implements IDangKy{
    StringBuilder thongTinDK;
    @Override
    public boolean addDangKy(StringBuilder builder) {
        thongTinDK = builder;
        return true;
    }

    @Override
    public String getDangKy() {
        return thongTinDK.toString();
    }
}
