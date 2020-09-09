package com.example.liyuchen.ui.epidemic;

import android.opengl.Matrix;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.liyuchen.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.security.KeyStore;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class CovidDataFragment extends Fragment {

    private Spinner spinner_district;
    private Spinner spinner_province;
    private Spinner spinner_country;
    private List<String> string_country=new ArrayList<>();
    private List<String> string_province=new ArrayList<>();
    private List<String> string_district=new ArrayList<>();
    private ArrayAdapter<String> adapter_country;
    private ArrayAdapter<String> adapter_province;
    private ArrayAdapter<String> adapter_district;
    private String country="中国";
    private String province="北京";
    private String district="海淀";
    private LineChart linechart;
    private Description description=new Description();
    private ArrayList<Entry> confirmed_data=new ArrayList<>();
    private ArrayList<Entry> cured_data=new ArrayList<>();
    private ArrayList<Entry> dead_data=new ArrayList<>();
    private LineDataSet confirmed_linedata;
    private LineDataSet cured_linedata;
    private LineDataSet dead_linedata;
    private LineData line;

    public CovidDataFragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_covid_data, container, false);
        string_country=CovidData.getRegionInfo();
        Refresh.refresh(() -> {
            //TODO: do nothing
        });
        initspinner(root);
        initlinechart(root);
        return root;
    }

    private void initspinner(View root)
    {
        spinner_district=root.findViewById(R.id.spinner_district);
        spinner_province=root.findViewById(R.id.spinner_province);
        spinner_country=root.findViewById(R.id.spinner_country);
        adapter_country=new ArrayAdapter<String>(root.getContext(), android.R.layout.simple_spinner_item, string_country);
        adapter_country.setDropDownViewResource(android.R.layout.simple_spinner_item);
        adapter_province=new ArrayAdapter<String>(root.getContext(), android.R.layout.simple_spinner_item, string_province);
        adapter_province.setDropDownViewResource(android.R.layout.simple_spinner_item);
        adapter_district=new ArrayAdapter<String>(root.getContext(), android.R.layout.simple_spinner_item, string_district);
        adapter_district.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner_country.setAdapter(adapter_country);
        spinner_province.setAdapter(adapter_province);
        spinner_district.setAdapter(adapter_district);
        spinner_country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                country=spinner_country.getSelectedItem().toString();
                string_province=CovidData.getRegionInfo(country);
                initspinner(root);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_province.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                province=spinner_province.getSelectedItem().toString();
                string_district=CovidData.getRegionInfo(country,province);
                initspinner(root);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                district=spinner_district.getSelectedItem().toString();
                description.setText(country+"."+province+"."+district);
                getcoviddata();
                linechart.invalidate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void chooseplace()
    {

    }

    private void getcoviddata()
    {
        DateFormat df=DateFormat.getDateInstance();
        List<Entry> temp_data=CovidData.getCovidData("confirmed", country, province, district);
        for(int i=0;i<temp_data.size();i++)
            confirmed_data.add(new Entry(i,temp_data.get(i).getY()));
        temp_data=CovidData.getCovidData("cured", country, province, district);
        for(int i=0;i<temp_data.size();i++)
            cured_data.add(new Entry(i,temp_data.get(i).getY()));
        temp_data=CovidData.getCovidData("dead", country, province, district);
        for(int i=0;i<temp_data.size();i++)
            dead_data.add(new Entry(i,temp_data.get(i).getY()));
    }

    private void initlinechart(View root)
    {
        linechart=root.findViewById(R.id.linechart_covid);
        linechart.setDescription(description);
        description.setText(country+"."+province+"."+district);
        description.setTextSize(20);
        linechart.setTouchEnabled(true);
        linechart.setDragEnabled(true);
        linechart.setNoDataText("no data found");
        linechart.setScaleEnabled(false);
        linechart.getXAxis().setGranularity(1.0f);
        linechart.getAxisLeft().setGranularity(1.0f);
        linechart.getAxisRight().setGranularity(1.0f);
        linechart.zoom(10f,1f,1f,1f);
        for(int i=0;i<100;i++)
        {
            confirmed_data.add(new Entry(i,i*3));
            cured_data.add(new Entry(i,i*2));
            dead_data.add(new Entry(i,i));
        }
        confirmed_linedata=new LineDataSet(confirmed_data,"confirmed");
        cured_linedata=new LineDataSet(cured_data,"cured");
        dead_linedata=new LineDataSet(dead_data,"dead");
        line=new LineData();
        line.addDataSet(confirmed_linedata);
        line.addDataSet(cured_linedata);
        line.addDataSet(dead_linedata);
        line.setValueTextSize(10);
        linechart.setData(line);
        linechart.invalidate();
    }
}
