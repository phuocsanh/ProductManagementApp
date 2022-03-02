package com.example.myapplication.Fragment;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Adapter.HangHoaAdapter;
import com.example.myapplication.DTO.HangHoaDTO;
import com.example.myapplication.DTO.NhanVienDTO;
import com.example.myapplication.DTO.NhomHangDTO;
import com.example.myapplication.InsertHangHoaActivity;
import com.example.myapplication.MainActivity;
import com.example.myapplication.UpdateHangHoaActivity;
import com.example.myapplication.R;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FragmentHangHoa extends Fragment {

    private RecyclerView recyclerView;
    private HangHoaDTO hangHoaDTO;
    private ArrayList<HangHoaDTO> dsHangHoa;
    private HangHoaAdapter hangHoaAdapter;
    private NhanVienDTO nhanVienDTO;

    public static FragmentHangHoa getInstance(NhanVienDTO nhanVienDTO) {
        FragmentHangHoa fragmentHangHoa = new FragmentHangHoa();
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_nhanvien", nhanVienDTO);
        fragmentHangHoa.setArguments(bundle);
        return fragmentHangHoa;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hanghoa, container, false);
        recyclerView = view.findViewById(R.id.recyclerview_hangHoa);
        LinearLayout linearLayout = view.findViewById(R.id.linearhh);

        ((MainActivity) getActivity()).setTitle("Hàng hóa");
        setHasOptionsMenu(true);

        nhanVienDTO = (NhanVienDTO) getArguments().get("object_nhanvien");




        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();

                String idHH = dsHangHoa.get(position).getCOLUMN_ID();
                String idremove = (String) viewHolder.itemView.getTag();
                deleteHangHoa(idremove);
                getAllHangHoa();
                Snackbar snackbar = Snackbar
                        .make(linearLayout, "Đã xóa.", Snackbar.LENGTH_LONG)
                        .setAction("UNDO", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                unRemoveHangHoa(idHH);
                                Snackbar snackbar = Snackbar
                                        .make(linearLayout, "Đã hoàn tác.", Snackbar.LENGTH_LONG);
                                snackbar.show();
                            }
                        })
                        .setActionTextColor(Color.RED)
                        .addCallback(new Snackbar.Callback() {
                            @Override
                            public void onDismissed(Snackbar snackbar, int event) {
                            }

                            @Override
                            public void onShown(Snackbar snackbar) {
                            }
                        });
                snackbar.show();


            }


        }).attachToRecyclerView(recyclerView);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getAllHangHoa();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        MenuInflater menuInflater = getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.menu_add, menu);
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

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_:
                Intent intent = new Intent(getActivity(), InsertHangHoaActivity.class);
                intent.putExtra("nhanvien",nhanVienDTO);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void getAllHangHoa() {
        String url = "http://192.168.1.10/website1/hanghoa.php";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        dsHangHoa = new ArrayList<>();
                        dsHangHoa.clear();
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
//                                Log.d("vvv", dsHangHoa.get(i).getCOLUMN_NAME());
                            }
                            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                            recyclerView.setHasFixedSize(true);
                            hangHoaAdapter = new HangHoaAdapter(getActivity(), dsHangHoa);
                            recyclerView.setAdapter(hangHoaAdapter);
                            hangHoaAdapter.setOnItemClickListener(new HangHoaAdapter.OnItemClickListner() {
                                @Override
                                public void onItemClick(String id, String vitri, View view) {
                                    Intent intent = new Intent(getActivity(), UpdateHangHoaActivity.class);
                                    intent.putExtra("hangHoaTaiViTriClick", dsHangHoa.get(Integer.parseInt(vitri)));
                                    intent.putExtra("nhanvien",nhanVienDTO);
                                    startActivity(intent);
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

    private void deleteHangHoa(String idhanghoa) {
        String url = "http://192.168.1.10/website1/deleteHangHoa.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String x = jsonObject.getString("thanhcong");
                    if (x.equalsIgnoreCase("1")) {
                        Log.d("vvv", "delete thanh cong");
                        getAllHangHoa();
                    } else {
                        Log.d("vvv", "delete that bai");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("vvv", error.toString() + " err add");
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();
                param.put("idhanghoa", idhanghoa);
                return param;
            }
        };
        RequestQueue rq = Volley.newRequestQueue(getActivity());
        rq.add(request);
    }

    private void unRemoveHangHoa(String id) {
        String url = "http://192.168.1.10/website1/unRemoveHangHoa.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String x = jsonObject.getString("thanhcong");
                    if (x.equalsIgnoreCase("1")) {
                        Log.d("vvv", "unRemoveHangHoa thanh cong");
                        getAllHangHoa();
                    } else {
                        Log.d("vvv", "unRemoveHangHoa that bai");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("vvv", error.toString() + " err unRemoveHangHoa");
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();
                param.put("idhanghoa", id);
                return param;
            }
        };
        RequestQueue rq = Volley.newRequestQueue(getActivity());
        rq.add(request);
    }
}
