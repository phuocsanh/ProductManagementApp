package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.DTO.HangHoaDTO;
import com.example.myapplication.DTO.NhapHangDTO;
import com.example.myapplication.R;

import java.util.ArrayList;

public class AddNhapHangAdapter extends RecyclerView.Adapter<AddNhapHangAdapter.NhapHangViewHolder> {
    Context mContext;
    ArrayList<HangHoaDTO> hangHoaDTOS;

    public AddNhapHangAdapter(Context mContext,ArrayList<HangHoaDTO> hangHoaDTOS) {
        this.mContext = mContext;
        this.hangHoaDTOS = hangHoaDTOS;
    }
    public class NhapHangViewHolder extends RecyclerView.ViewHolder {
        private ImageView hinhanh;
        private TextView tenHangHoa,tonKho;

        public NhapHangViewHolder(@NonNull View itemView) {
            super(itemView);
            hinhanh = itemView.findViewById(R.id.imageView_anhdaidien20);
            tenHangHoa = itemView.findViewById(R.id.textView_tenHangHoa20);
            tonKho = itemView.findViewById(R.id.textView_tonKho20);

        }
    }

    @NonNull
    @Override
    public NhapHangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_recyclerview_addnhaphang, parent, false);
        return new NhapHangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NhapHangViewHolder holder, int position) {
        String hinhAnh = hangHoaDTOS.get(position).getCOLUMN_HINHDAIDIEN();

        Glide.with(mContext).load("http://192.168.1.10/website1/hinhsanpham/" +
                hinhAnh).into(holder.hinhanh);
        holder.tenHangHoa.setText(hangHoaDTOS.get(position).getCOLUMN_NAME());
        holder.tonKho.setText(hangHoaDTOS.get(position).getCOLUMN_TONKHO());

    }

    @Override
    public int getItemCount() {
        return hangHoaDTOS.size();
    }


}
