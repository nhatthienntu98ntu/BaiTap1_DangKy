package vn.edu.ntu.nhatthien.dangkydichvu59cntt1;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import vn.edu.ntu.nhatthien.controller.IDangKy;

public class Fragment_Results_Registration extends Fragment {
    NavController navController;
    Button btnThoat;
    TextView txtThongTinKhachHang;
    IDangKy iDangKy;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_results_registration, container, false);
        btnThoat = view.findViewById(R.id.btnThoat);
        txtThongTinKhachHang = view.findViewById(R.id.txtThongTinKhachHang);
        addView(view);
        return view;
    }

    private void addView(View v){
        iDangKy = ((MainActivity) getActivity()).iDangKy;
        txtThongTinKhachHang.setText(iDangKy.getDangKy());
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.fragment_Main);
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        navController = NavHostFragment.findNavController(this);
        ((MainActivity) getActivity()).navController = navController;
    }
}
