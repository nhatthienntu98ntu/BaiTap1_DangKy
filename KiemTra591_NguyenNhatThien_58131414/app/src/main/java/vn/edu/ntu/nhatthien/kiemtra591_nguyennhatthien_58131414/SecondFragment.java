package vn.edu.ntu.nhatthien.kiemtra591_nguyennhatthien_58131414;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.edu.ntu.nhatthien.Controller.ISanPhamController;
import vn.edu.ntu.nhatthien.Model.SanPham;

public class SecondFragment extends Fragment {
    NavController navController;
    ISanPhamController iSanPhamController;

    RecyclerView rcvDS;
    List<SanPham> dsSP;
    AdapterDS adapterDS;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        rcvDS = view.findViewById(R.id.rcvDS);
        rcvDS.setLayoutManager(new LinearLayoutManager(getActivity()));
        iSanPhamController = ((MainActivity) getActivity()).iSanPhamController;
        dsSP = iSanPhamController.getAllSP();
        adapterDS = new AdapterDS(dsSP);
        rcvDS.setAdapter(adapterDS);
        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        navController = NavHostFragment.findNavController(this);
        ((MainActivity) getActivity()).navController = navController;

    }




    private class Holder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView txtNgay, txtLoai, txtMuaVao, txtBanRa;

        public Holder(@NonNull View itemView) {
            super(itemView);
            txtNgay = this.itemView.findViewById(R.id.txtNgay);
            txtLoai = this.itemView.findViewById(R.id.txtLoai);
            txtMuaVao = this.itemView.findViewById(R.id.txtMuaVao);
            txtBanRa = this.itemView.findViewById(R.id.txtBanRa);
        }

        public void bind(SanPham sp){
            txtNgay.setText(sp.getNgay());
            txtLoai.setText(sp.getLoai());
            txtMuaVao.setText(sp.getMuaVao());
            txtBanRa.setText(sp.getBanRa());
        }

        @Override
        public void onClick(View v) {

        }
    }


    public class AdapterDS extends RecyclerView.Adapter<Holder>{
        List<SanPham> dsSanPham;

        public AdapterDS(List<SanPham> dsSanPham) {
            this.dsSanPham = dsSanPham;
        }

        @NonNull
        @Override
        public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = getLayoutInflater();
            View view = layoutInflater.inflate(R.layout.danhsach,parent , false);
            return new Holder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull Holder holder, int position) {
            holder.bind(dsSanPham.get(position));
        }

        @Override
        public int getItemCount() {
            return dsSanPham.size();
        }
    }



}
