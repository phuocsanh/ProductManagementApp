package com.example.myapplication.Fragment;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
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
import com.example.myapplication.Adapter.KhachHangAdapter;
import com.example.myapplication.DTO.KhachHangDTO;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FragmentKhachHang extends Fragment {
    private RecyclerView recyclerView;
    private KhachHangAdapter adapter;
    private ArrayList<KhachHangDTO> khachHangDTOS;
    private KhachHangDTO khachHangDTO;
    private LinearLayout linearLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_khachhang, container, false);
        recyclerView = view.findViewById(R.id.recyclerview_khachhang);
        linearLayout = view.findViewById(R.id.ln_khachHang);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        ((MainActivity) getActivity()).setTitle("Khách hàng");
        setHasOptionsMenu(true);
        getAllKhachHang();
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                String tenKH = khachHangDTOS.get(position).getCOLUM_TENKHACHHANG();
                String idKH = khachHangDTOS.get(position).getCOLUM_ID();
                String idremove = (String) viewHolder.itemView.getTag();
                deleteKhachHang(idremove);
                getAllKhachHang();

                Snackbar snackbar = Snackbar
                        .make(linearLayout, "Đã xóa.", Snackbar.LENGTH_LONG)
                        .setAction("UNDO", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                unRemoveKhachHang(idKH);
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
//    ---------------------------------------------------------

    @Override
    public void onResume() {
        super.onResume();
        getAllKhachHang();
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
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);

                return false;
            }


        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_:
                dialogThemKhachHang();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void getAllKhachHang() {
        String url = "http://192.168.1.10/website1/khachhang.php";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        khachHangDTOS = new ArrayList<>();
                        try {
                            JSONArray jsonArray = response.getJSONArray("khachhang");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                khachHangDTO = new KhachHangDTO();
                                JSONObject hit = jsonArray.getJSONObject(i);
                                String entry = "";
                                entry = hit.getString("makhachhang");
                                khachHangDTO.setCOLUM_ID(entry);
                                entry = hit.getString("tenkhachhang");
                                khachHangDTO.setCOLUM_TENKHACHHANG(entry);
                                entry = hit.getString("sdtkhachhang");
                                khachHangDTO.setCOLUM_SDT(entry);
                                khachHangDTOS.add(khachHangDTO);
                            }

                            adapter = new KhachHangAdapter(getActivity(), khachHangDTOS);
                            recyclerView.setAdapter(adapter);
                            adapter.setOnItemClickListener(new KhachHangAdapter.OnItemClickListner() {
                                @Override
                                public void onItemClick(String id, String vitri) {
                                    dialog_updateKhachHang(khachHangDTOS.get(Integer.parseInt(vitri)));
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


    private void addKhachHang(String ten, String sdt) {
        String url = "http://192.168.1.10/website1/addKhachHang.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String x = jsonObject.getString("thanhcong");
                    if (x.equalsIgnoreCase("1")) {
                        Log.d("vvv", "add thanh cong");
                        getAllKhachHang();
                    } else {
                        Log.d("vvv", "add that bai");
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
                param.put("tenkhachhang", ten);
                param.put("sdtkhachhang", sdt);
                return param;
            }
        };
        RequestQueue rq = Volley.newRequestQueue(getActivity());
        rq.add(request);
    }

    private void updateKhachHang(String id, String ten, String sdt) {
        String url = "http://192.168.1.10/website1/updateKhachHang.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String x = jsonObject.getString("thanhcong");
                    if (x.equalsIgnoreCase("1")) {
                        Log.d("vvv", "update thanh cong");
                        getAllKhachHang();
                    } else {
                        Log.d("vvv", "update khong thanh cong");
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
                param.put("makhachhang", id);
                param.put("tenkhachhang", ten);
                param.put("sdtkhachhang", sdt);
                return param;
            }
        };
        RequestQueue rq = Volley.newRequestQueue(getActivity());
        rq.add(request);
    }

    private void dialogThemKhachHang() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_them_khachhang, null);
        TextInputLayout textInputLayoutTen = view.findViewById(R.id.ed_tenkhachhang_dialog);
        TextInputLayout textInputLayoutMK = view.findViewById(R.id.ed_sdt_dialog);
        builder.setView(view)
                .setTitle("Thêm khách hàng")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String username = textInputLayoutTen.getEditText().getText().toString().trim();
                        String SDT = textInputLayoutMK.getEditText().getText().toString().trim();
                        addKhachHang(username, SDT);
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }

    private void dialog_updateKhachHang(KhachHangDTO khachHangDTO) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_them_khachhang, null);

        TextInputLayout textInputLayoutTen = view.findViewById(R.id.ed_tenkhachhang_dialog);
        TextInputLayout textInputLayoutMK = view.findViewById(R.id.ed_sdt_dialog);
        textInputLayoutTen.getEditText().setText(khachHangDTO.getCOLUM_TENKHACHHANG());
        textInputLayoutMK.getEditText().setText(khachHangDTO.getCOLUM_SDT());
        builder.setView(view)
                .setTitle("Chỉnh sửa khách hàng")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String username = textInputLayoutTen.getEditText().getText().toString().trim();
                        String SDT = textInputLayoutMK.getEditText().getText().toString().trim();
                        updateKhachHang(khachHangDTO.getCOLUM_ID(), username, SDT);
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void deleteKhachHang(String makhachhang) {
        String url = "http://192.168.1.10/website1/deleteKhachHang.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String x = jsonObject.getString("thanhcong");
                    if (x.equalsIgnoreCase("1")) {
                        Log.d("vvv", "delete thanh cong");
                        getAllKhachHang();
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
                param.put("makhachhang", makhachhang);
                return param;
            }
        };
        RequestQueue rq = Volley.newRequestQueue(getActivity());
        rq.add(request);
    }

    private void unRemoveKhachHang(String id) {
        String url = "http://192.168.1.10/website1/unRemoveKhachHang.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String x = jsonObject.getString("thanhcong");
                    if (x.equalsIgnoreCase("1")) {
                        Log.d("vvv", "unRemoveHangHoa thanh cong");
                        getAllKhachHang();
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
                param.put("makhachhang", id);
                return param;
            }
        };
        RequestQueue rq = Volley.newRequestQueue(getActivity());
        rq.add(request);
    }
}
