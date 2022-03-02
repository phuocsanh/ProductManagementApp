package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DTO.HangHoaDTO;
import com.example.myapplication.R;

import java.util.ArrayList;

public class ChiTietLSGDAdapter extends RecyclerView.Adapter<ChiTietLSGDAdapter.ChiTietLSGDHolder> {
    private Context mContext;
    private ArrayList<HangHoaDTO> hangHoaDTOS;

    public ChiTietLSGDAdapter(Context mContext, ArrayList<HangHoaDTO> hangHoaDTOS) {
        this.mContext = mContext;
        this.hangHoaDTOS = hangHoaDTOS;
    }

    public class ChiTietLSGDHolder extends RecyclerView.ViewHolder {
        private TextView tvTenHH, tvMaHH, tvGiaBan, tvTongGia, tvSLMua;

        public ChiTietLSGDHolder(@NonNull View itemView) {
            super(itemView);
            tvTenHH = itemView.findViewById(R.id.textView_tenHangHoa22);
            tvMaHH = itemView.findViewById(R.id.textView_maHangHoa22);
            tvGiaBan = itemView.findViewById(R.id.textView_giaHangHoa22);
            tvTongGia = itemView.findViewById(R.id.textView_tongGiaHangHoa22);
            tvSLMua = itemView.findViewById(R.id.textView_soLuongMuaHangHoa22);

        }
    }

    @NonNull
    @Override
    public ChiTietLSGDHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_recyclerview_chitiet_lsgd, parent, false);
        return new ChiTietLSGDHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChiTietLSGDHolder holder, int position) {
        int x = Integer.parseInt(hangHoaDTOS.get(position).getCOLUMN_GIABAN()) *
                Integer.parseInt(hangHoaDTOS.get(position).getCOLUMN_SOLUONGHD());

        holder.tvTenHH.setText(hangHoaDTOS.get(position).getCOLUMN_NAME());
        holder.tvMaHH.setText(hangHoaDTOS.get(position).getCOLUMN_ID());
        holder.tvGiaBan.setText(hangHoaDTOS.get(position).getCOLUMN_GIABAN());
        holder.tvSLMua.setText(hangHoaDTOS.get(position).getCOLUMN_SOLUONGHD());
        holder.tvTongGia.setText(String.valueOf(x));
    }

    @Override
    public int getItemCount() {
        if (hangHoaDTOS != null) {
            return hangHoaDTOS.size();
        } else return 0;
    }


}
