package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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

public class KhachHangAdapter extends RecyclerView.Adapter<KhachHangAdapter.KhachHangViewHolder> implements Filterable {
    private Context mContext;
    private List<KhachHangDTO> khachHangDTOS;
    private List<KhachHangDTO> khachHangDTOS_Fillter;
    private OnItemClickListner listner;

    public KhachHangAdapter(Context mContext, List<KhachHangDTO> khachHangDTOS) {
        this.mContext = mContext;
        this.khachHangDTOS_Fillter = khachHangDTOS;
        this.khachHangDTOS = khachHangDTOS;
    }

    public class KhachHangViewHolder extends RecyclerView.ViewHolder {
        private TextView idKhachHang, tenKhachHang, Sdt;

        public KhachHangViewHolder(@NonNull View itemView) {
            super(itemView);
            idKhachHang = itemView.findViewById(R.id.textView_maKhachHang7);
            tenKhachHang = itemView.findViewById(R.id.textView_tenKhachHang7);
            Sdt = itemView.findViewById(R.id.textView_sdtKhachHang7);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listner != null && position != RecyclerView.NO_POSITION) {
                        listner.onItemClick(itemView.getTag().toString(), String.valueOf(position));
                    }
                }
            });
        }
    }


    @NonNull
    @Override
    public KhachHangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_recyclerview_khachhang, parent, false);
        return new KhachHangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KhachHangViewHolder holder, int position) {
        holder.idKhachHang.setText("KH000" + khachHangDTOS.get(position).getCOLUM_ID());
        holder.tenKhachHang.setText(khachHangDTOS.get(position).getCOLUM_TENKHACHHANG());
        holder.Sdt.setText(khachHangDTOS.get(position).getCOLUM_SDT());
        holder.itemView.setTag(khachHangDTOS.get(position).getCOLUM_ID());
    }

    @Override
    public int getItemCount() {
        if (khachHangDTOS != null) {
            return khachHangDTOS.size();
        } else return 0;
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        protected FilterResults performFiltering(CharSequence constraint) {
         String tenKH = constraint.toString();
         if(tenKH.isEmpty()){
             khachHangDTOS=khachHangDTOS_Fillter;
         }else {
             List<KhachHangDTO> list = new ArrayList<>();
             for(KhachHangDTO khachHangDTO1: khachHangDTOS_Fillter){
                 if(khachHangDTO1.getCOLUM_TENKHACHHANG().toLowerCase().contains(tenKH.toLowerCase())){
                     list.add(khachHangDTO1);
                 }
             }
             khachHangDTOS = list;
         }
         FilterResults filterResults = new FilterResults();
         filterResults.values = khachHangDTOS;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
           khachHangDTOS = (List<KhachHangDTO>) results.values;
           notifyDataSetChanged();
        }
    };

    public interface OnItemClickListner {
        void onItemClick(String id, String vitri);
    }

    public void setOnItemClickListener(OnItemClickListner listener) {
        this.listner = listener;
    }
}
