package vn.edu.ntu.nhatthien.dangkydichvu59cntt1;

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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import vn.edu.ntu.nhatthien.controller.IDangKy;

public class Fragment_Main extends Fragment{
    NavController navController;
    Button btnDangKy;
    EditText edtName, edtNgaySinh, edtSDT, edtDiaChi;
    ImageView imgNgaySinh;
    IDangKy iDangKy;
    RadioGroup rdog;
    StringBuilder builder;
    Spinner spinner;
    String dichVu;
    RadioButton thanhToan;
    ArrayAdapter<String> dichVuAdapter;
    String[] arrayDichVu;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        btnDangKy = view.findViewById(R.id.btnDangKy);
        edtName = view.findViewById(R.id.edtName);
        edtNgaySinh = view.findViewById(R.id.edtNgaySinh);
        edtSDT = view.findViewById(R.id.edtSDT);
        edtDiaChi = view.findViewById(R.id.edtDiaChi);
        imgNgaySinh = view.findViewById(R.id.imgNgaySinh);
        rdog = view.findViewById(R.id.rdog);
        spinner = view.findViewById(R.id.spinner);
        arrayDichVu = getResources().getStringArray(R.array.dichvu);
        dichVuAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_expandable_list_item_1, arrayDichVu);
        spinner.setAdapter(dichVuAdapter);
        return view;
    }



    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imgNgaySinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChonNgay();
            }
        });


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dichVu = dichVuAdapter.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iDangKy = (IDangKy) ((MainActivity) getActivity()).iDangKy;
                int id = rdog.getCheckedRadioButtonId();
                thanhToan = getActivity().findViewById(id);
                builder = new StringBuilder();
                if(edtName.getText().toString().isEmpty() || edtNgaySinh.getText().toString().isEmpty() || edtSDT.getText().toString().isEmpty() || edtDiaChi.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Các trường không được để trống!", Toast.LENGTH_SHORT).show();
                }
                else{
                    builder.append("Chúc mừng khách hàng: ")
                            .append(edtName.getText())
                            .append("\n")
                            .append("Sinh ngày: ")
                            .append(edtNgaySinh.getText())
                            .append("\n")
                            .append("Đã đăng ký thành công dịch vụ: ")
                            .append(dichVu)
                            .append("\n")
                            .append("Hình thức thanh toán: ")
                            .append(thanhToan.getText())
                            .append("\n")
                            .append("Chúng tôi sẽ liên lạc với bạn theo số điện thoại: ")
                            .append(edtSDT.getText());
                    if(iDangKy.addDangKy(builder)){
                        Toast.makeText(getActivity(), builder.toString(), Toast.LENGTH_SHORT).show();
                        navController.navigate(R.id.fragment_Results_Registration);
                    }else {
                        Toast.makeText(getActivity(), "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                    }
                }

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
                edtNgaySinh.setText(dateFormat.format(calendar.getTime()));
            }
        }, nam, thang,ngay);
        datePickerDialog.show();
    }

}
