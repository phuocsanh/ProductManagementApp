package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DTO.KhachHangDTO;
import com.example.myapplication.DTO.NhanVienDTO;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class NhanVienAdapter extends RecyclerView.Adapter<NhanVienAdapter.NhanVienViewHolder> implements Filterable {

    private Context mContext;
    private List<NhanVienDTO> nhanVienDTOS;
    private List<NhanVienDTO> nhanVienDTOS_Fillter;
    private OnItemClickListner listener;


    public NhanVienAdapter(Context mContext, List<NhanVienDTO> nhanVienDTOS) {
        this.mContext = mContext;
        this.nhanVienDTOS_Fillter = nhanVienDTOS;
        this.nhanVienDTOS = nhanVienDTOS;
    }

    public class NhanVienViewHolder extends RecyclerView.ViewHolder {
        private TextView idNhanVien, tenDangNhap, chucVu;

        private NhanVienViewHolder(View itemView) {
            super(itemView);
            idNhanVien = itemView.findViewById(R.id.textView_maNhanVien6);
            tenDangNhap = itemView.findViewById(R.id.textView_tenNhanVien6);
            chucVu = itemView.findViewById(R.id.textView_chucvuNhanVien6);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(itemView.getTag().toString(), String.valueOf(position));
                    }
                }
            });


        }
    }

    @NonNull
    @Override
    public NhanVienViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_recyclerview_nhanvien, parent, false);
        return new NhanVienViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NhanVienViewHolder holder, int position) {

        holder.idNhanVien.setText("NV00" + nhanVienDTOS.get(position).getCOLUM_ID());
        holder.tenDangNhap.setText(nhanVienDTOS.get(position).getCOLUM_TENDANGNHAP());
        holder.chucVu.setText(nhanVienDTOS.get(position).getCOLUM_CHUCVU());

        holder.itemView.setTag(nhanVienDTOS.get(position).getCOLUM_ID());
    }


    @Override
    public int getItemCount() {
        if (nhanVienDTOS != null) {
            return nhanVienDTOS.size();
        } else return 0;
    }
    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        protected FilterResults performFiltering(CharSequence constraint) {
            String tenNV = constraint.toString();
            if(tenNV.isEmpty()){
                nhanVienDTOS=nhanVienDTOS_Fillter;
            }else {
                List<NhanVienDTO> list = new ArrayList<>();
                for(NhanVienDTO nhanVienDTO1: nhanVienDTOS_Fillter){
                    if(nhanVienDTO1.getCOLUM_TENDANGNHAP().toLowerCase().contains(tenNV.toLowerCase())){
                        list.add(nhanVienDTO1);
                    }
                }
                nhanVienDTOS = list;
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = nhanVienDTOS;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            nhanVienDTOS = (List<NhanVienDTO>) results.values;
            notifyDataSetChanged();
        }
    };


    public interface OnItemClickListner {
        void onItemClick(String id, String vitri);
    }

    public void setOnItemClickListener(OnItemClickListner listener) {
        this.listener = listener;
    }


}
