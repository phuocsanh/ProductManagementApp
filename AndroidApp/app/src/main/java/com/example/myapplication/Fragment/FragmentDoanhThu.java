package com.example.myapplication.Fragment;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.DTO.DoanhThuChartDTO;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class FragmentDoanhThu extends Fragment {
    private Button btnOk;
    private EditText ed_tuNgay, ed_denNgay;
    private int dtyear, dtmonth, dtday;
    private TextView tv_doanhThu, tv_loiNhuan;
    private Calendar lich = Calendar.getInstance();
    private ArrayList<String> listNamDT, listNamLN, listNamPieChart;
    private PieChart pieChart;
    private ArrayList<PieEntry> listpieEntries;
    private Spinner spinnerDtNam;
    private BarChart barChartDoanhTu, barChartLoiNhuan;
    private DoanhThuChartDTO doanhThuChartDTO;
    private ArrayList<DoanhThuChartDTO> doanhThuChartDTOS;
    private ArrayList<String> listNam;
    private ArrayList<BarEntry> barEntries;
    private ArrayList<BarEntry> barEntries1;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");


    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doanhthu, container, false);
        ((MainActivity) getActivity()).setTitle("Thống Kê");
        btnOk = (Button) view.findViewById(R.id.btn_ok);
        ed_tuNgay = (EditText) view.findViewById(R.id.ed_tungay);
        ed_denNgay = (EditText) view.findViewById(R.id.ed_denngay);
        tv_doanhThu = (TextView) view.findViewById(R.id.tv_doanhthu);
        tv_loiNhuan = (TextView) view.findViewById(R.id.tv_loinhuan);
        pieChart = (PieChart) view.findViewById(R.id.piechart);
        spinnerDtNam = (Spinner) view.findViewById(R.id.spiner_nam_doanhthu);
        barChartDoanhTu = (BarChart) view.findViewById(R.id.barchart_doanhthu);

//        barChartLoiNhuan = (BarChart) view.findViewById(R.id.barchart_loinhuan);
//        spinnerLnNam = (Spinner) view.findViewById(R.id.spiner_nam_loinhuan);

        setDoanhThuHienTai();
        getDsNam();


        ed_tuNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                dtyear = calendar.get(Calendar.YEAR);
                dtmonth = calendar.get(Calendar.MONTH);
                dtday = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), 0,
                        mDateTuNgay, dtyear, dtmonth, dtday);
                datePickerDialog.show();

            }
        });
        ed_denNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                dtyear = calendar.get(Calendar.YEAR);
                dtmonth = calendar.get(Calendar.MONTH);
                dtday = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), 0,
                        mDateDenNgay, dtyear, dtmonth, dtday);
                datePickerDialog.show();

            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tuNgay = ed_tuNgay.getText().toString().trim();
                String denNgay = ed_denNgay.getText().toString().trim();
                getDoanhThuVaLoiNhuan(tuNgay, denNgay);
            }
        });

        return view;
    }

    private void getDoanhThuVaLoiNhuan(String tuNgay, String denNgay) {
        String url = "http://192.168.1.10/website1/getDoanhThuLoiNhuan.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                listpieEntries = new ArrayList<>();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String x = jsonObject.getString("thanhcong");
                    if (x.equals("1")) {
                        JSONArray jsonArray = jsonObject.getJSONArray("doanhthuvaloinhuan");
                        JSONObject jsonObject1 = jsonArray.getJSONObject(0);
                        String doanhthu = jsonObject1.getString("doanhthu");
                        String loinhuan = jsonObject1.getString("loinhuan");

                        tv_doanhThu.setText(doanhthu);
                        tv_loiNhuan.setText(loinhuan);

                        float doanhthu_thang = Float.parseFloat(doanhthu);
                        float loinhuan_thang = Float.parseFloat(loinhuan);

                        listpieEntries.add(new PieEntry(doanhthu_thang, "Doanh Thu"));
                        listpieEntries.add(new PieEntry(loinhuan_thang, "Lợi Nhuận"));
                        setPieChart(listpieEntries);
                    } else {
                        Toast.makeText(getActivity(), "Không có dữ liệu Thống Kê", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();
                param.put("tuNgay", tuNgay);
                param.put("denNgay", denNgay);
                return param;
            }
        };
        RequestQueue rq = Volley.newRequestQueue(getActivity());
        rq.add(request);
    }

    private void setDoanhThuHienTai() {
        String laynam = String.valueOf(lich.get(Calendar.YEAR));
        String laythang = String.valueOf((lich.get(Calendar.MONTH)) + 1);
        String layngay = String.valueOf(lich.get(Calendar.DAY_OF_MONTH));
        if (Integer.parseInt(laythang) < 10) {
            laythang = "0" + laythang;
        }
        if (Integer.parseInt(layngay) < 10) {
            layngay = "0" + layngay;
        }
        String tuNgay = laynam + "-" + laythang + "-" + "01";
        String denNgay = laynam + "-" + laythang + "-" + layngay;
        ed_tuNgay.setText(tuNgay);
        ed_denNgay.setText(denNgay);
        getDoanhThuVaLoiNhuan(tuNgay, denNgay);
    }

    private void setBarDoanhThu(ArrayList<BarEntry> entries) {
        if (entries.size() > 0) {
            BarDataSet barDataSet = new BarDataSet(entries, "Doanh Thu");
            barDataSet.setColor(Color.BLUE);
            barDataSet.setValueTextColor(Color.BLACK);
            BarData barData = new BarData(barDataSet);
            barChartDoanhTu.setData(barData);
            barChartDoanhTu.setScaleEnabled(true);
            barChartDoanhTu.setPinchZoom(true);
            barChartDoanhTu.animateY(1500);
            barChartDoanhTu.setDrawGridBackground(true);
            barChartDoanhTu.getDescription().setText("Doanh Thu Theo Năm");
            barChartDoanhTu.invalidate();
        }

    }

   /* private void setBarLoiNhuan(ArrayList<BarEntry> entries) {
        if (entries.size() > 0) {
            BarDataSet barDataSet1 = new BarDataSet(entries, "Lợi Nhuận");
            barDataSet1.setColor(Color.GREEN);
            barDataSet1.setValueTextColor(Color.BLACK);
            BarData barData = new BarData(barDataSet1);
            barChartLoiNhuan.setData(barData);
            barChartLoiNhuan.setScaleEnabled(true);
            barChartLoiNhuan.setPinchZoom(true);
            barChartLoiNhuan.animateY(1500);
            barChartLoiNhuan.setDrawGridBackground(true);
            barChartLoiNhuan.getDescription().setText("Lợi Nhuận Theo Năm");
            barChartLoiNhuan.invalidate();
        }
    }
*/
    private void setPieChart(ArrayList<PieEntry> pieEntries) {
        if (pieEntries.size() > 0) {
            PieDataSet pieDataSet = new PieDataSet(pieEntries, "Doanh thu và lợi nhuận");
            pieDataSet.setColors(Color.BLUE, Color.GREEN);
            pieDataSet.setValueTextColor(Color.BLACK);
            pieDataSet.setValueTextSize(22f);
            PieData pieData = new PieData(pieDataSet);
            pieChart.setData(pieData);
            pieChart.getDescription().setEnabled(true);
            pieChart.invalidate();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Thông báo").setMessage("Thời gian bạn vừa chọn không có hàng hóa nào được bán");
            builder.setCancelable(true);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        }
    }

    private void getDoanhThuChart(String namDT) {
        String url = "http://192.168.1.10/website1/getDoanhThuChart.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                doanhThuChartDTOS = new ArrayList<>();
                doanhThuChartDTOS.clear();
                barEntries = new ArrayList<>();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("doanhthuchart");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        doanhThuChartDTO = new DoanhThuChartDTO();
                        JSONObject hit = jsonArray.getJSONObject(i);
                        int entry1 = Integer.parseInt(hit.getString("thang"));
                        float entry2 = Float.parseFloat((hit.getString("doanhthu")));
                        doanhThuChartDTO.setCOLUMN_THANG(entry1);
                        doanhThuChartDTO.setCOLUMN_DOANHTHU(entry2);
                        doanhThuChartDTOS.add(doanhThuChartDTO);

                    }

                    for (int i = 0; i < doanhThuChartDTOS.size(); i++) {
                        barEntries.add(new BarEntry(doanhThuChartDTOS.get(i).getCOLUMN_THANG(), doanhThuChartDTOS.get(i).getCOLUMN_DOANHTHU()));
                        Log.d("vvv", doanhThuChartDTOS.get(i).getCOLUMN_THANG() + "");
                    }

                    setBarDoanhThu(barEntries);

                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();
                param.put("nam", namDT);
                return param;
            }
        };
        RequestQueue rq = Volley.newRequestQueue(getActivity());
        rq.add(request);
    }

    /*private void getLoiNhuanChart(String namLn) {
        String url = "http://192.168.1.10/website1/getLoiNhuanChart.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
              ArrayList<DoanhThuChartDTO>  doanhThuChartDTOS2 = new ArrayList<>();

                barEntries1 = new ArrayList<>();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String x = jsonObject.getString("thanhcong");
                    if (x.equals("1")) {
                        JSONArray jsonArray = jsonObject.getJSONArray("loinhuanchart");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            DoanhThuChartDTO  doanhThuChartDTO2 = new DoanhThuChartDTO();
                            JSONObject hit = jsonArray.getJSONObject(i);
                            float entry1;
                            float entry2;
                            entry1 = Float.parseFloat(hit.getString("thang"));
                            doanhThuChartDTO2.setCOLUMN_THANG(entry1);
                            entry2 = Float.parseFloat((hit.getString("loinhuan")));
                            doanhThuChartDTO2.setCOLUMN_DOANHTHU(entry2);
                            doanhThuChartDTOS2.add(doanhThuChartDTO2);
                        }
                        for (DoanhThuChartDTO ln : doanhThuChartDTOS2) {
                            barEntries1.add(new BarEntry(ln.getCOLUMN_THANG(), ln.getCOLUMN_DOANHTHU()));
                        }
                        setBarLoiNhuan(barEntries1);

                    } else {
                        Toast.makeText(getActivity(), "Không có dữ liệu", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();
                param.put("nam", namLn);

                return param;
            }
        };
        RequestQueue rq = Volley.newRequestQueue(getActivity());
        rq.add(request);
    }*/

    private void getDsNam() {
        String url = "http://192.168.1.10/website1/getDsNam.php";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        listNam = new ArrayList<>();
                        try {
                            JSONArray jsonArray = response.getJSONArray("listnam");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);
                                listNam.add(hit.getString("nam"));
                            }
                            setSpinnerAdapter(listNam);

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
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);
    }

    DatePickerDialog.OnDateSetListener mDateTuNgay = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthofyear, int dayOfMonth) {
            dtyear = year;
            dtmonth = monthofyear;
            dtday = dayOfMonth;
            GregorianCalendar gregorianCalendar = new GregorianCalendar(dtyear, dtmonth, dtday);
            ed_tuNgay.setText(simpleDateFormat.format(gregorianCalendar.getTime()));
        }
    };

    DatePickerDialog.OnDateSetListener mDateDenNgay = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthofyear, int dayOfMonth) {
            dtyear = year;
            dtmonth = monthofyear;
            dtday = dayOfMonth;
            GregorianCalendar gregorianCalendar = new GregorianCalendar(dtyear, dtmonth, dtday);
            ed_denNgay.setText(simpleDateFormat.format(gregorianCalendar.getTime()));
        }
    };

    private void setSpinnerAdapter(ArrayList<String> list) {
        ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, list);
        spinnerDtNam.setAdapter(spinnerArrayAdapter);
        spinnerDtNam.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                getDoanhThuChart(spinnerDtNam.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
      /*  ArrayAdapter spinnerArrayAdapter2 = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, list);
        spinnerLnNam.setAdapter(spinnerArrayAdapter2);
        spinnerLnNam.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                getLoiNhuanChart(spinnerLnNam.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });*/
    }
}
