package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DTO.NhapHangDTO;
import com.example.myapplication.R;

import java.util.ArrayList;

public class NhapHangAdapter extends RecyclerView.Adapter<NhapHangAdapter.NhapHangHoloder> {
    Context mContext;
    ArrayList<NhapHangDTO> nhapHangDTOS;
    OnItemClickListner listener;

    public NhapHangAdapter(Context mContext, ArrayList<NhapHangDTO> nhapHangDTOS) {
        this.mContext = mContext;
        this.nhapHangDTOS = nhapHangDTOS;
    }

    public class NhapHangHoloder extends RecyclerView.ViewHolder {
        private TextView maNH, tenNV, time;
        public NhapHangHoloder(@NonNull View itemView) {
            super(itemView);
            maNH = itemView.findViewById(R.id.textView_maNH17);
            tenNV = itemView.findViewById(R.id.textView_tenNV17);
            time = itemView.findViewById(R.id.textView_timestamp17);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
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
    public NhapHangHoloder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_recyclerview_nhaphang, parent, false);
        return new NhapHangHoloder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NhapHangHoloder holder, int position) {
        holder.maNH.setText("PN000" + nhapHangDTOS.get(position).getCOLUM_ID());
        holder.time.setText(nhapHangDTOS.get(position).getCOLUM_TIMESTAMP());
        holder.tenNV.setText(nhapHangDTOS.get(position).getCOLUM_NHANVIEN());
        holder.itemView.setTag(nhapHangDTOS.get(position).getCOLUM_ID());
    }

    @Override
    public int getItemCount() {
        return nhapHangDTOS.size();
    }
    public interface OnItemClickListner {
        void onItemClick(String id, String vitri);
    }
    public  void setOnItemClickListener (OnItemClickListner listener){
        this.listener = listener;
    }
}
