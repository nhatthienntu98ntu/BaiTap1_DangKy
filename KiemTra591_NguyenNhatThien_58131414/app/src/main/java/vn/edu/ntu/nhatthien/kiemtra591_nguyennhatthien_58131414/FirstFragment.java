package vn.edu.ntu.nhatthien.kiemtra591_nguyennhatthien_58131414;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import vn.edu.ntu.nhatthien.Controller.ISanPhamController;
import vn.edu.ntu.nhatthien.Model.SanPham;

public class FirstFragment extends Fragment {
    NavController navController;
    ISanPhamController iSanPhamController;


    EditText edtNgay, edtMuaVao, edtBanRa;
    Spinner spnLoai;
    ImageView imgNgay;
    Button btnThem, btnXemDanhSach;

    String loai;
    String[] dsLoai;
    ArrayAdapter<String> arrayLoai;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        edtNgay = view.findViewById(R.id.edtNgay);
        spnLoai = view.findViewById(R.id.spnLoai);
        edtMuaVao = view.findViewById(R.id.edtMuaVao);
        edtBanRa = view.findViewById(R.id.edtBanRa);
        imgNgay = view.findViewById(R.id.imgNgay);
        btnThem = view.findViewById(R.id.btnThem);
        btnXemDanhSach = view.findViewById(R.id.btnXemDanhSach);
        iSanPhamController = ((MainActivity) getActivity()).iSanPhamController;


        dsLoai = getResources().getStringArray(R.array.loai);
        arrayLoai = new ArrayAdapter<>(getActivity(), android.R.layout.simple_expandable_list_item_1, dsLoai);
        spnLoai.setAdapter(arrayLoai);
        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        spnLoai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                loai = arrayLoai.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        imgNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChonNgay();
            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(iSanPhamController.add(new SanPham(edtNgay.getText().toString(), loai, edtMuaVao.getText().toString(), edtBanRa.getText().toString()))){
                    Toast.makeText(getActivity(), "Đã thêm!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity(), "Không thêm được!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btnXemDanhSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_firstFragment_to_secondFragment);
            }
        });

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        navController = NavHostFragment.findNavController(this);
        ((MainActivity) getActivity()).navController = navController;

    }

    private void ChonNgay(){
        final Calendar calendar = Calendar.getInstance();
        int nam = calendar.get(Calendar.YEAR);
        int thang = calendar.get(Calendar.MONTH);
        int ngay = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth);
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyy");
                edtNgay.setText(dateFormat.format(calendar.getTime()));
            }
        }, nam, thang,ngay);
        datePickerDialog.show();
    }
}
