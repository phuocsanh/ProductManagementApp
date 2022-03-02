package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.DTO.HangHoaDTO;
import com.example.myapplication.DTO.KhachHangDTO;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class HangHoaAdapter extends RecyclerView.Adapter<HangHoaAdapter.HangHoaViewHolder> implements Filterable {

    private Context mContext;
    private ArrayList<HangHoaDTO> hangHoaDTOS;
    private OnItemClickListner listener;

    private ArrayList<HangHoaDTO> hangHoaDTOSFull;

    public HangHoaAdapter(Context mContext, ArrayList<HangHoaDTO> hangHoaDTOS) {
        this.mContext = mContext;
        this.hangHoaDTOS = hangHoaDTOS;

        this.hangHoaDTOSFull = hangHoaDTOS;
    }

    public class HangHoaViewHolder extends RecyclerView.ViewHolder {
        private TextView idHangHoa, tenHangHoa, tonKho, giaHangHoa;
        private ImageView imageView;

        private HangHoaViewHolder(View itemView) {
            super(itemView);
            idHangHoa = itemView.findViewById(R.id.textView_IdHangHoa);
            tenHangHoa = itemView.findViewById(R.id.textView_tenHangHoa);
            tonKho = itemView.findViewById(R.id.textView_tonKho);
            giaHangHoa = itemView.findViewById(R.id.textView_giaHangHoa);
            imageView = itemView.findViewById(R.id.imageView_hinhHangHoa);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(itemView.getTag().toString(), String.valueOf(position), itemView);
                    }
                }
            });

        }
    }

    @NonNull
    @Override
    public HangHoaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_recyclerview_hanghoa, parent, false);
        return new HangHoaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HangHoaViewHolder holder, int position) {
        String id = hangHoaDTOS.get(position).getCOLUMN_ID();
        String tenHang = hangHoaDTOS.get(position).getCOLUMN_NAME();
        String hinhAnh = hangHoaDTOS.get(position).getCOLUMN_HINHDAIDIEN();
        String tonKho = hangHoaDTOS.get(position).getCOLUMN_TONKHO();
        String gia = hangHoaDTOS.get(position).getCOLUMN_GIABAN();

/*        byte[] decodedString = Base64.decode(hinhAnh, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        holder.imageView.setImageBitmap(decodedByte);*/

        Glide.with(mContext).load("http://192.168.1.10/website1/hinhsanpham/" +
                hinhAnh).into(holder.imageView);
        holder.idHangHoa.setText("SP000" + id);
        holder.tenHangHoa.setText(tenHang);
        holder.giaHangHoa.setText(gia);
        holder.tonKho.setText(tonKho);
        holder.itemView.setTag(id);
    }

    @Override
    public int getItemCount() {
        if (hangHoaDTOS != null) {
            return hangHoaDTOS.size();
        } else return 0;
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        protected FilterResults performFiltering(CharSequence constraint) {
            String tenHH = constraint.toString();
            if(tenHH.isEmpty()){
                hangHoaDTOS=hangHoaDTOSFull;
            }else {
                ArrayList<HangHoaDTO> list = new ArrayList<>();
                for(HangHoaDTO hangHoaDTO1: hangHoaDTOSFull){
                    if(hangHoaDTO1.getCOLUMN_NAME().toLowerCase().contains(tenHH.toLowerCase())){
                        list.add(hangHoaDTO1);
                    }
                }
               hangHoaDTOS =  list;
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values =hangHoaDTOS;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            hangHoaDTOS = (ArrayList<HangHoaDTO>) results.values;
            notifyDataSetChanged();
        }
    };

    public interface OnItemClickListner {
        void onItemClick(String id, String vitri, View view);
    }

    public void setOnItemClickListener(OnItemClickListner listener) {
        this.listener = listener;
    }
}
