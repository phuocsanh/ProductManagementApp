package com.example.myapplication.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Adapter.HangHoaAdapter;
import com.example.myapplication.DTO.HangHoaDTO;
import com.example.myapplication.DTO.NhanVienDTO;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.UpdateHangHoaActivity;
import com.example.myapplication.XuatHangActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FragmentXuatHang extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<HangHoaDTO> dsHangHoa;
    private HangHoaDTO hangHoaDTO;
    private HangHoaAdapter hangHoaAdapter;
    private ArrayList<HangHoaDTO> guiListHangHoa;
    Button buttonXong, buttonChonLai;
    private ArrayList<View> views;
    private NhanVienDTO nhanVienDTO;

    public static FragmentXuatHang getInstance(NhanVienDTO nhanVienDTO) {
        FragmentXuatHang fragmentXuatHang = new FragmentXuatHang();
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_nhanvien", nhanVienDTO);
        fragmentXuatHang.setArguments(bundle);
        return fragmentXuatHang;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_xuathang, container, false);
        buttonChonLai = view.findViewById(R.id.button_chonlai);
        buttonXong = view.findViewById(R.id.button_xong);

        ((MainActivity) getActivity()).setTitle("Chọn hàng bán");
        setHasOptionsMenu(true);
        recyclerView = view.findViewById(R.id.recyclerview_chonHangXuat);

        nhanVienDTO = (NhanVienDTO) getArguments().get("object_nhanvien");
        guiListHangHoa = new ArrayList<>();
        views = new ArrayList<>();

        buttonChonLai.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                deleteHangHoaSelected();
            }
        });

        buttonXong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), XuatHangActivity.class);
                intent.putExtra("dsHangCanXuat", guiListHangHoa);
                intent.putExtra("object_nhanvien", nhanVienDTO);

                startActivity(intent);
                deleteHangHoaSelected();
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getAllHangHoa();
    }

    //---------------------------------------------------------
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        MenuInflater menuInflater = getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.toolbar_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                hangHoaAdapter.getFilter().filter(newText);
                return true;
            }
        });

        menuItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                // khi bam nut search
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                // khi back nut search
                deleteHangHoaSelected();
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                Log.d("vvv", "search");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //---------------------------------------------------------
    private void getAllHangHoa() {
        String url = "http://192.168.1.10/website1/hanghoa.php";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        dsHangHoa = new ArrayList<>();
                        try {
                            JSONArray jsonArray = response.getJSONArray("hanghoa");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                hangHoaDTO = new HangHoaDTO();
                                JSONObject hit = jsonArray.getJSONObject(i);
                                String entry = "";
                                entry = hit.getString("idhanghoa");
                                hangHoaDTO.setCOLUMN_ID(entry);
                                entry = hit.getString("idloaihang");
                                hangHoaDTO.setCOLUMN_LOAIHANG(entry);
                                entry = hit.getString("tenhanghoa");
                                hangHoaDTO.setCOLUMN_NAME(entry);
                                entry = hit.getString("vitri");
                                hangHoaDTO.setCOLUMN_VITRI(entry);
                                entry = hit.getString("hinhanh");
                                hangHoaDTO.setCOLUMN_HINHDAIDIEN(entry);
                                entry = hit.getString("soluong");
                                hangHoaDTO.setCOLUMN_TONKHO(entry);
                                entry = hit.getString("giaban");
                                hangHoaDTO.setCOLUMN_GIABAN(entry);
                                entry = hit.getString("giavon");
                                hangHoaDTO.setCOLUMN_GIAVON(entry);
                                entry = hit.getString("dinhmuctonmin");
                                hangHoaDTO.setCOLUMN_DINHMUCTON_MIN(entry);
                                entry = hit.getString("dinhmuctonmax");
                                hangHoaDTO.setCOLUMN_DINHMUCTON_MAX(entry);
                                entry = hit.getString("ghichu");
                                hangHoaDTO.setCOLUMN_GHICHU(entry);

                                dsHangHoa.add(hangHoaDTO);
                            }
                            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                            recyclerView.setHasFixedSize(true);
                            hangHoaAdapter = new HangHoaAdapter(getActivity(), dsHangHoa);
                            recyclerView.setAdapter(hangHoaAdapter);

                            hangHoaAdapter.setOnItemClickListener(new HangHoaAdapter.OnItemClickListner() {
                                @SuppressLint("ResourceAsColor")
                                @Override
                                public void onItemClick(String id, String vitri, View view) {
                                    if (view.getTag().toString().equalsIgnoreCase("-1")) {
                                        view.setBackgroundResource(R.color.white);
                                        view.setTag(-2);
                                        views.remove(view);
                                        guiListHangHoa.remove(dsHangHoa.get(Integer.parseInt(vitri)));
                                    } else {
                                        view.setBackgroundResource(R.color.xanhnhat);
                                        view.setTag(-1);
                                        guiListHangHoa.add(dsHangHoa.get(Integer.parseInt(vitri)));
                                        views.add(view);
                                    }
                                }
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.d("vvv", error.toString());
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(request);
    }

    private void deleteHangHoaSelected() {
        for (int i = 0; i < views.size(); i++) {
            views.get(i).setBackgroundResource(R.color.white);
            views.get(i).setTag(-2);
        }
        views.clear();
        guiListHangHoa.clear();
    }

}
