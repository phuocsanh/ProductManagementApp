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

public class AddKiemKhoAdapter extends RecyclerView.Adapter<AddKiemKhoAdapter.AddKiemKhoViewHolder> {
    private Context mContext;
    private ArrayList<HangHoaDTO> hangHoaDTOS;
    private OnItemClickListner listner;

    public AddKiemKhoAdapter(Context mContext, ArrayList<HangHoaDTO> hangHoaDTOS) {
        this.mContext = mContext;
        this.hangHoaDTOS = hangHoaDTOS;
    }

    class AddKiemKhoViewHolder extends RecyclerView.ViewHolder {
        private TextView tenHangHoa, maHangHoa, tonKho, thucTe, lech;

        public AddKiemKhoViewHolder(@NonNull View itemView) {
            super(itemView);
            tenHangHoa = itemView.findViewById(R.id.textView_tenHangHoa12);
            maHangHoa = itemView.findViewById(R.id.textView_IdHangHoa12);
            tonKho = itemView.findViewById(R.id.textView_tonKho12);
            thucTe = itemView.findViewById(R.id.textView_thucTe12);
            lech = itemView.findViewById(R.id.textView_lech12);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
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
    public AddKiemKhoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_recyclerview_addkiemkho, parent, false);
        return new AddKiemKhoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddKiemKhoViewHolder holder, int position) {
        holder.tenHangHoa.setText(hangHoaDTOS.get(position).getCOLUMN_NAME());
        holder.maHangHoa.setText("SP000" + hangHoaDTOS.get(position).getCOLUMN_ID());
        holder.tonKho.setText(hangHoaDTOS.get(position).getCOLUMN_TONKHO());
        holder.thucTe.setText(hangHoaDTOS.get(position).getCOLUMN_THUCTE());
        holder.lech.setText(String.valueOf(Integer.parseInt(hangHoaDTOS.get(position).getCOLUMN_THUCTE()) - Integer.parseInt(hangHoaDTOS.get(position).getCOLUMN_TONKHO())));
        holder.itemView.setTag(hangHoaDTOS.get(position).getCOLUMN_ID());
    }

    @Override
    public int getItemCount() {
        return hangHoaDTOS.size();
    }

    public interface OnItemClickListner {
        void onItemClick(String id, String vitri);
    }
    public void setOnItemClickListener(OnItemClickListner listner){
        this.listner =listner;
    }
}
