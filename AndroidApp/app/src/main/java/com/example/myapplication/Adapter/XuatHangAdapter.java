package com.example.myapplication.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.DTO.HangHoaDTO;
import com.example.myapplication.R;

import java.util.ArrayList;

public class XuatHangAdapter extends RecyclerView.Adapter<XuatHangAdapter.XuatHangViewHolder> {

    private Context mContext;
    private ArrayList<HangHoaDTO> hangHoaDTOS;
    private OnItemClickListner listener;
    int x = 1;

    public XuatHangAdapter(Context mContext, ArrayList<HangHoaDTO> hangHoaDTOS) {
        this.mContext = mContext;
        this.hangHoaDTOS = hangHoaDTOS;
    }

    public class XuatHangViewHolder extends RecyclerView.ViewHolder {
        private TextView tenHangHoa, giaHangHoa;
        private ImageView imageView;
        private ImageButton imageView_add, imageView_remove;
        private EditText soluong;

        private XuatHangViewHolder(View itemView) {
            super(itemView);
            tenHangHoa = itemView.findViewById(R.id.textView_tenHangHoa4);
            giaHangHoa = itemView.findViewById(R.id.textView_giaHangHoa4);
            imageView = itemView.findViewById(R.id.imageView_hinhHangHoa4);
            soluong = itemView.findViewById(R.id.edittext_soluong4);
            imageView_add = itemView.findViewById(R.id.imageView_add4);
            imageView_remove = itemView.findViewById(R.id.imageView_remove4);

            imageView_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    x = Integer.parseInt(hangHoaDTOS.get(getAdapterPosition()).getCOLUMN_SOLUONGHD());
                    int y = Integer.parseInt(hangHoaDTOS.get(getAdapterPosition()).getCOLUMN_TONKHO());
                    if (x >= y) {
                        Toast.makeText(mContext, "Tồn kho không đủ", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    x++;
//                    Log.d("vvv", hangHoaDTOS.get(getAdapterPosition()).getCOLUMN_SOLUONGHD());
                    soluong.setText(x + "");
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(itemView.getTag().toString(), String.valueOf(position), itemView, x + "");
                    }
                }
            });

            imageView_remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    x = Integer.parseInt(hangHoaDTOS.get(getAdapterPosition()).getCOLUMN_SOLUONGHD());
                    if (x <= 1) {
                        Toast.makeText(mContext, "Không thể ít hơn 1", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    x--;
                    soluong.setText(x + "");
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(itemView.getTag().toString(), String.valueOf(position), itemView, x + "");
                    }
                }
            });

        }
    }

    @NonNull
    @Override
    public XuatHangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_recyclerview_xuathang, parent, false);
        return new XuatHangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull XuatHangViewHolder holder, int position) {
        String id = hangHoaDTOS.get(position).getCOLUMN_ID();
        String tenHang = hangHoaDTOS.get(position).getCOLUMN_NAME();
        String hinhAnh = hangHoaDTOS.get(position).getCOLUMN_HINHDAIDIEN();
        String gia = hangHoaDTOS.get(position).getCOLUMN_GIABAN();
        String sl = hangHoaDTOS.get(position).getCOLUMN_SOLUONGHD();

        Glide.with(mContext).load("http://192.168.1.10/website1/hinhsanpham/" +
                hinhAnh).into(holder.imageView);
        holder.tenHangHoa.setText(tenHang);
        holder.giaHangHoa.setText(gia);
        holder.soluong.setText(sl);

        holder.itemView.setTag(id);

    }

    @Override
    public int getItemCount() {
        return hangHoaDTOS.size();
    }

    public interface OnItemClickListner {
        void onItemClick(String id, String vitri, View view, String sl);
    }

    public void setOnItemClickListener(OnItemClickListner listener)
    {
        this.listener = listener;
    }


}
