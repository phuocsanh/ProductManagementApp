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

import java.util.List;

public class TopAdapter extends RecyclerView.Adapter<TopAdapter.TopViewHolder> {
    private Context mContext;
    private List<HangHoaDTO> topDTOList;

    public TopAdapter(Context mContext, List<HangHoaDTO> topDTOList) {
        this.mContext = mContext;
        this.topDTOList = topDTOList;
    }

    public class TopViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_tenhang,tv_soluong;

        private TopViewHolder(View itemView) {
            super(itemView);
            tv_tenhang = itemView.findViewById(R.id.tv_tenhang_top);
            tv_soluong = itemView.findViewById(R.id.tv_soluong_top);
        }
    }

    @NonNull
    @Override
    public TopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_recyclerview_top, parent, false);
        return new TopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopViewHolder holder, int position) {

        holder.tv_tenhang.setText(topDTOList.get(position).getCOLUMN_NAME());
        holder.tv_soluong.setText(topDTOList.get(position).getCOLUMN_SOLUONGHD());

    }

    @Override
    public int getItemCount() {
        if (topDTOList!= null) {
            return topDTOList.size();
        } else return 0;
    }
}
