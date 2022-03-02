package com.example.myapplication.Fragment;

import android.content.Context;
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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

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
import com.example.myapplication.Adapter.NhanVienAdapter;
import com.example.myapplication.DTO.NhanVienDTO;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FragmentNhanVien extends Fragment {
    private RecyclerView recyclerView;
    private List<NhanVienDTO> dsnhanvien;
    private NhanVienDTO nhanVienDTO, nhanVienDTOHT;
    private NhanVienDTO nhanVienDTO1;
    private NhanVienAdapter nhanVienAdapter;
    private LinearLayout linearLayout;
    private String ktChucVu;
    private int position;
    private String idNhanVien;
    private int kt = 0;
    private TextInputLayout textInputLayoutTen, textInputLayoutMK, textInputLayoutTenThem, textInputLayoutMKThem, textInputLayoutXNMKThem;


    public static FragmentNhanVien getInstance(NhanVienDTO nhanVienDTO) {
        FragmentNhanVien fragmentNhanVien = new FragmentNhanVien();
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_nhanvien", nhanVienDTO);
        fragmentNhanVien.setArguments(bundle);
        return fragmentNhanVien;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nhanvien, container, false);
        recyclerView = view.findViewById(R.id.recyclerview_nhanvien);
        linearLayout = view.findViewById(R.id.ln_nhanvien);


        ((MainActivity) getActivity()).setTitle("Nhân viên");
        setHasOptionsMenu(true);


        nhanVienDTO = (NhanVienDTO) getArguments().get("object_nhanvien");

        ktChucVu = nhanVienDTO.getCOLUM_CHUCVU();
        Log.d("ktv", ktChucVu);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                position = viewHolder.getAdapterPosition();
                idNhanVien = dsnhanvien.get(position).getCOLUM_ID();
                if (nhanVienDTO.getCOLUM_TENDANGNHAP().equalsIgnoreCase("admin") && nhanVienDTO.getCOLUM_CHUCVU().equalsIgnoreCase("admin")) {

                    if (nhanVienDTO.getCOLUM_TENDANGNHAP().equalsIgnoreCase(dsnhanvien.get(position).getCOLUM_TENDANGNHAP())) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setTitle("Thông báo").setMessage("Đây là Admin chính thức không thể xóa");
                        builder.setCancelable(true);
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                getAllNhanVien();
                            }
                        });
                        AlertDialog alert = builder.create();
                        alert.show();
                    } else {
                        String idremove = (String) viewHolder.itemView.getTag();
                        deleteNhanVien(idremove);
                        nhanVienAdapter.notifyDataSetChanged();
                        getAllNhanVien();
                    }

                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Thông báo").setMessage("Bạn không có quyền xóa nhân viên này");
                    builder.setCancelable(true);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            getAllNhanVien();
                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                    kt = 1;
                }

                if (kt == 0) {
                    Snackbar snackbar = Snackbar
                            .make(linearLayout, "Đã xóa.", Snackbar.LENGTH_LONG)
                            .setAction("UNDO", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Log.d("vitrinhanvien", idNhanVien);
                                    unRemoveNhanVien(idNhanVien);
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
            }
        }).attachToRecyclerView(recyclerView);

        return view;
    }

    public void onResume() {
        super.onResume();
        getAllNhanVien();

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
                nhanVienAdapter.getFilter().filter(newText);
                return true;
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_:
                if (!nhanVienDTO.getCOLUM_CHUCVU().equalsIgnoreCase("admin")) {
                    Toast.makeText(getActivity(), "Bạn không có quyền thêm nhân viên mới", Toast.LENGTH_SHORT).show();
                } else {
                    dialogThemNhanVien(getActivity(), ktChucVu);
                }

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void dialogThemNhanVien(Context context, String ktChuVuNhanVien) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_them_nhanvien, null);

        textInputLayoutTenThem = view.findViewById(R.id.ed_tendangnhap_dialog);
        textInputLayoutMKThem = view.findViewById(R.id.ed_matkhau_dialog);
        textInputLayoutXNMKThem = view.findViewById(R.id.ed_xacnhanmatkhau_dialog);
        Spinner spinner_chucvuthem = view.findViewById(R.id.spinner_chucvu);
        if (nhanVienDTO.getCOLUM_CHUCVU().equalsIgnoreCase("admin")
                && !nhanVienDTO.getCOLUM_TENDANGNHAP().equalsIgnoreCase("admin")) {
            spinner_chucvuthem.setSelection(1);
            spinner_chucvuthem.setEnabled(false);
        }

        builder.setView(view)
                .setTitle("Thêm nhân viên.")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
//                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        String username = textInputLayoutTenThem.getEditText().getText().toString().trim();
//                        String password = textInputLayoutMKThem.getEditText().getText().toString().trim();
//                        String chucvu = spinner_chucvuthem.getSelectedItem().toString();
//                        if (validate() > 0) {
//                            if (nhanVienDTO.getCOLUM_CHUCVU().equalsIgnoreCase("admin")
//                                    && nhanVienDTO.getCOLUM_TENDANGNHAP().equalsIgnoreCase("admin")) {
//                                addNhanVien(username, password, chucvu);
//                                Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();
//                                getAllNhanVien();
//                            }
//                            if (!nhanVienDTO.getCOLUM_TENDANGNHAP().equalsIgnoreCase("admin")
//                                    && nhanVienDTO.getCOLUM_CHUCVU().equalsIgnoreCase("admin")) {
//                                addNhanVien(username, password, "Nhân Viên");
//                            }
//                        }
//                    }
//                });

                .setPositiveButton("ok",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
        AlertDialog dialog = builder.create();
        dialog.show();
//Overriding the handler immediately after show is probably a better approach than OnShowListener as described below
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean wantToCloseDialog = false;
                String username = textInputLayoutTenThem.getEditText().getText().toString().trim();
                String password = textInputLayoutMKThem.getEditText().getText().toString().trim();
                String chucvu = spinner_chucvuthem.getSelectedItem().toString();
                if (validate() > 0) {
                    if (nhanVienDTO.getCOLUM_CHUCVU().equalsIgnoreCase("admin")
                            && nhanVienDTO.getCOLUM_TENDANGNHAP().equalsIgnoreCase("admin")) {
                        addNhanVien(username, password, chucvu);
                        Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                        getAllNhanVien();
                        wantToCloseDialog = true;
                    }
                    if (!nhanVienDTO.getCOLUM_TENDANGNHAP().equalsIgnoreCase("admin")
                            && nhanVienDTO.getCOLUM_CHUCVU().equalsIgnoreCase("admin")) {
                        addNhanVien(username, password, "Nhân Viên");
                        wantToCloseDialog = true;
                    }
                } else {
                    wantToCloseDialog = false;
                }
                if (wantToCloseDialog)
                    dialog.dismiss();
                //else dialog stays open. Make sure you have an obvious way to close the dialog especially if you set cancellable to false.
            }
        });
//        AlertDialog alert = builder.create();
//        alert.show();

    }

    // Sửa thông tin nhân viên
    public void openDialog_sua(NhanVienDTO nhanVienDTO1, String ktChucVu) {
        if (ktChucVu.equalsIgnoreCase("Admin")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            LayoutInflater inflater = getActivity().getLayoutInflater();
            View view = inflater.inflate(R.layout.dialog_sua_nhanvien, null);
            textInputLayoutMK = view.findViewById(R.id.ed_mk_dialog_sua);
            textInputLayoutTen = view.findViewById(R.id.ed_tendangnhap_dialog_sua);
            Spinner spinner_chucvusua = view.findViewById(R.id.spinner_chucvu_dialog_sua);
            textInputLayoutTen.getEditText().setText(nhanVienDTO1.getCOLUM_TENDANGNHAP());
            textInputLayoutMK.getEditText().setText(nhanVienDTO1.getCOLUM_MATKHAU());
            if (nhanVienDTO1.getCOLUM_CHUCVU().equalsIgnoreCase("Admin")
            ) {
                spinner_chucvusua.setSelection(0);
            } else {
                spinner_chucvusua.setSelection(1);
            }
            if (nhanVienDTO.getCOLUM_CHUCVU().equalsIgnoreCase("admin")
                    && !nhanVienDTO.getCOLUM_TENDANGNHAP().equalsIgnoreCase("admin")) {
                spinner_chucvusua.setSelection(1);
                spinner_chucvusua.setEnabled(false);
                if(nhanVienDTO1.getCOLUM_TENDANGNHAP().equalsIgnoreCase(nhanVienDTO.getCOLUM_TENDANGNHAP())){
                    spinner_chucvusua.setSelection(0);
                    spinner_chucvusua.setEnabled(false);
                }
            }

            if (nhanVienDTO1.getCOLUM_CHUCVU().equalsIgnoreCase("admin")
                    && nhanVienDTO1.getCOLUM_TENDANGNHAP().equalsIgnoreCase("admin")) {
                spinner_chucvusua.setSelection(0);
                spinner_chucvusua.setEnabled(false);
                textInputLayoutTen.setEnabled(false);
                textInputLayoutMK.setEnabled(false);

            }

            builder.setView(view)
                    .setTitle("Sửa nhân viên.")
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    })
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            if (validate_suanhanvien() > 0) {
                                String tenDangNhap = textInputLayoutTen.getEditText().getText().toString().trim();
                                String chucvu = spinner_chucvusua.getSelectedItem().toString();
                                String mk = textInputLayoutMK.getEditText().getText().toString().trim();
                                updateNhanVien(nhanVienDTO1.getCOLUM_ID(), tenDangNhap, chucvu, mk);
                                getAllNhanVien();

                            }
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        } else {
            Log.d("vvv", "Chỉ Admin mới được sửa thông tin");
            Toast.makeText(getActivity(), "Chỉ Admin mới được sửa thông tin", Toast.LENGTH_SHORT).show();
        }
    }

    // Update du liệu lên database
    private void updateNhanVien(String id, String tendangnhap, String chucvu, String mk) {
        String url = "http://192.168.1.10/website1/updateNhanVien.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String x = jsonObject.getString("thanhcong");
                    if (x.equalsIgnoreCase("1")) {
                        Log.d("vvv", "update thanh cong");
                        getAllNhanVien();
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
                param.put("manhanvien", id);
                param.put("tendangnhap", tendangnhap);
                param.put("chucvu", chucvu);
                param.put("matkhau", mk);
                return param;
            }
        };
        RequestQueue rq = Volley.newRequestQueue(getActivity());
        rq.add(request);
    }

    // Add dữ liệu lên database
    private void addNhanVien(String tendangnhap, String matKhau, String chucVu) {
        String url = "http://192.168.1.10/website1/addNhanVien.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String x = jsonObject.getString("thanhcong");
                    if (x.equalsIgnoreCase("1")) {
                        Log.d("vvv", "add thanh cong");
                        getAllNhanVien();
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
                param.put("tendangnhap", tendangnhap);
                param.put("matkhau", matKhau);
                param.put("chucvu", chucVu);
                return param;

            }
        };
        RequestQueue rq = Volley.newRequestQueue(getActivity());
        rq.add(request);
    }

    private void getAllNhanVien() {
        String url = "http://192.168.1.10/website1/nhanvien.php";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        dsnhanvien = new ArrayList<>();
                        try {
                            JSONArray jsonArray = response.getJSONArray("nhanvien");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                nhanVienDTO1 = new NhanVienDTO();
                                JSONObject hit = jsonArray.getJSONObject(i);
                                String entry = "";
                                entry = hit.getString("manhanvien");
                                nhanVienDTO1.setCOLUM_ID(entry);
                                entry = hit.getString("tendangnhap");
                                nhanVienDTO1.setCOLUM_TENDANGNHAP(entry);
                                entry = hit.getString("matkhau");
                                nhanVienDTO1.setCOLUM_MATKHAU(entry);
                                entry = hit.getString("chucvu");
                                nhanVienDTO1.setCOLUM_CHUCVU(entry);

                                dsnhanvien.add(nhanVienDTO1);
//                                Log.d("vvv", dsHangHoa.get(i).getCOLUMN_NAME());
                            }
                            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                            recyclerView.setHasFixedSize(true);
                            nhanVienAdapter = new NhanVienAdapter(getActivity(), dsnhanvien);
                            nhanVienAdapter.notifyDataSetChanged();
                            recyclerView.setAdapter(nhanVienAdapter);

                            nhanVienAdapter.setOnItemClickListener(new NhanVienAdapter.OnItemClickListner() {
                                @Override
                                public void onItemClick(String id, String vitri) {

                                    nhanVienDTOHT = dsnhanvien.get(Integer.parseInt(vitri));
                                    if (nhanVienDTO.getCOLUM_CHUCVU().equalsIgnoreCase("admin") &&
                                            nhanVienDTO.getCOLUM_TENDANGNHAP().equalsIgnoreCase("admin")) {
                                        openDialog_sua(dsnhanvien.get(Integer.parseInt(vitri)), ktChucVu);
                                    } else if (!nhanVienDTO.getCOLUM_TENDANGNHAP().equalsIgnoreCase("admin")
                                            && nhanVienDTO.getCOLUM_CHUCVU().equalsIgnoreCase("admin")) {

                                        if (!nhanVienDTO.getCOLUM_ID().equalsIgnoreCase(nhanVienDTOHT.getCOLUM_ID())
                                                && nhanVienDTO.getCOLUM_CHUCVU().equalsIgnoreCase(nhanVienDTOHT.getCOLUM_CHUCVU())) {
                                            Toast.makeText(getActivity(), "Bạn Không Thể Sửa Thông tin Admin khác", Toast.LENGTH_SHORT).show();
                                        } else {
                                            openDialog_sua(dsnhanvien.get(Integer.parseInt(vitri)), ktChucVu);
                                        }
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

    // Check lỗi khi thêm nhân viên
    public int validate() {
        int check = 1;
        if (textInputLayoutTenThem.getEditText().getText().length() == 0 || textInputLayoutMKThem.getEditText().getText().length() == 0
                || textInputLayoutXNMKThem.getEditText().getText().length() == 0) {
            Toast.makeText(getActivity(), "Bạn chưa nhập đầy đủ thông tin! ", Toast.LENGTH_SHORT).show();
            check = -1;
        }

        if (!textInputLayoutMKThem.getEditText().getText().toString().equals(textInputLayoutXNMKThem.getEditText().getText().toString())) {
            Toast.makeText(getActivity(), "Mật Khẩu không trùng khớp!", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        for (int i = 0; i < dsnhanvien.size(); i++) {
            if (textInputLayoutTenThem.getEditText().getText().toString().equals(dsnhanvien.get(i).getCOLUM_TENDANGNHAP())) {
                check = -1;
                Toast.makeText(getActivity(), "Tên đăng nhập đã tồn tại!", Toast.LENGTH_SHORT).show();
                break;
            }
        }

        return check;
    }

    //    //Check lỗi khi sửa nhân viên
//    public boolean validate_sua(String x) {
//        String ten = nhanVienDTO.getCOLUM_TENDANGNHAP();
//        if (x.length() == 0) {
//            return false;
//        } else if (ten.equals(x)) {
//            return true;
//        } else {
//            for (int i = 0; i < dsnhanvien.size(); i++) {
//                if (x.equals(dsnhanvien.get(i).getCOLUM_TENDANGNHAP())) {
//                    Toast.makeText(getActivity(), "Tên đăng nhập đã tồn tại", Toast.LENGTH_SHORT).show();
//                    break;
//                }
//            }
//            return false;
//        }
//    }
    public int validate_suanhanvien() {
        int check = 1;
        String ten = nhanVienDTOHT.getCOLUM_TENDANGNHAP();

        if (textInputLayoutTen.getEditText().getText().length() == 0) {
            Toast.makeText(getActivity(), "Bạn chưa nhập tên thay đổi không thành công", Toast.LENGTH_LONG).show();
            check = -1;
        } else if (ten.equals(textInputLayoutTen.getEditText().getText().toString())) {
            check = 1;
        } else {
            for (int i = 0; i < dsnhanvien.size(); i++) {
                if (textInputLayoutTen.getEditText().getText().toString().equals(dsnhanvien.get(i).getCOLUM_TENDANGNHAP())) {
                    check = -1;
                    Toast.makeText(getActivity(), "Tên đăng nhập đã tồn tại", Toast.LENGTH_SHORT).show();
                    break;
                }
            }
        }

//        if (nhanVienDTOHT.getCOLUM_TENDANGNHAP().equalsIgnoreCase("admin")) {
//            Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
//            textInputLayoutTen.setEnabled(false);
//            textInputLayoutTen.setClickable(false);
//            check = -1;
//        }

        return check;
    }

    private void deleteNhanVien(String manhanvien) {
        String url = "http://192.168.1.10/website1/deleteNhanVien.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String x = jsonObject.getString("thanhcong");
                    if (x.equalsIgnoreCase("1")) {
                        Log.d("vvv", "delete thanh cong");
                        getAllNhanVien();
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
                param.put("manhanvien", manhanvien);
                return param;
            }
        };
        RequestQueue rq = Volley.newRequestQueue(getActivity());
        rq.add(request);
    }

    private void unRemoveNhanVien(String id) {
        String url = "http://192.168.1.10/website1/unRemoveNhanVien.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String x = jsonObject.getString("thanhcong");
                    if (x.equalsIgnoreCase("1")) {
                        Log.d("vvv", "unRemoveHangHoa thanh cong");
                        getAllNhanVien();
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
                param.put("manhanvien", id);
                return param;
            }
        };
        RequestQueue rq = Volley.newRequestQueue(getActivity());
        rq.add(request);
    }
}
