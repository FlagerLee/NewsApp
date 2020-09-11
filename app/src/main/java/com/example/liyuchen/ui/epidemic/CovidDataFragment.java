package com.example.liyuchen.ui.epidemic;

import android.opengl.Matrix;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

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
    private String country="";
    private String province="";
    private String district="";
    private LineChart linechart;
    private Description description=new Description();
    private ArrayList<Entry> confirmed_data=new ArrayList<>();
    private ArrayList<Entry> cured_data=new ArrayList<>();
    private ArrayList<Entry> dead_data=new ArrayList<>();
    private LineDataSet confirmed_linedata;
    private LineDataSet cured_linedata;
    private LineDataSet dead_linedata;
    private LineData line;
    private XAxisFormat xAxisFormat;

    private boolean finish = false;

    public CovidDataFragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_covid_data, container, false);
        CovidData.getRegionInfo(list -> {
            string_country = list;
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    initspinner(root);
                }
            });
        });
        Refresh.refresh();
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

                CovidData.getRegionInfo(country, (list -> {
                    try {
                        string_province = list;
                        string_district.clear();
                        adapter_province = new ArrayAdapter<String>(root.getContext(), android.R.layout.simple_spinner_item, string_province);
                        adapter_province.setDropDownViewResource(android.R.layout.simple_spinner_item);
                        string_district = new ArrayList<>();
                        adapter_district = new ArrayAdapter<String>(root.getContext(), android.R.layout.simple_spinner_item, string_district);
                        adapter_district.setDropDownViewResource(android.R.layout.simple_spinner_item);

                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                spinner_province.setAdapter(adapter_province);
                                spinner_district.setAdapter(adapter_district);
                                description.setText(country);
                                linechart.invalidate();
                            }
                        });


                    }
                    catch (Exception e) {
                        String err = e.toString();
                    }
                }));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_province.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                province=spinner_province.getSelectedItem().toString();

                CovidData.getRegionInfo(country, province, (list -> {
                    string_district = list;
                    adapter_district=new ArrayAdapter<String>(root.getContext(), android.R.layout.simple_spinner_item, string_district);
                    adapter_district.setDropDownViewResource(android.R.layout.simple_spinner_item);

                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            spinner_district.setAdapter(adapter_district);
                            description.setText(country+"."+province);
                            linechart.invalidate();
                        }
                    });
                }));
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

                CovidData.getCovidData(country, province, district, ((confirmedEntry, curedEntry, deadEntry) -> {

                    confirmed_linedata = new LineDataSet((ArrayList<Entry>) confirmedEntry, "confirmed");
                    cured_linedata = new LineDataSet((ArrayList<Entry>) curedEntry, "cured");
                    dead_linedata = new LineDataSet((ArrayList<Entry>) deadEntry, "dead");

                    confirmed_linedata.setColors(ColorTemplate.rgb("c0392b"));
                    confirmed_linedata.setDrawCircles(false);
                    confirmed_linedata.setDrawValues(false);
                    cured_linedata.setColors(ColorTemplate.rgb("2ecc71"));
                    cured_linedata.setDrawCircles(false);
                    cured_linedata.setDrawValues(false);
                    dead_linedata.setColors(ColorTemplate.rgb("2d3436"));
                    dead_linedata.setDrawCircles(false);
                    dead_linedata.setDrawValues(false);

                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            linechart.clear();
                            line=new LineData();
                            line.addDataSet(confirmed_linedata);
                            line.addDataSet(cured_linedata);
                            line.addDataSet(dead_linedata);
                            line.setValueTextSize(10);
                            linechart.setData(line);
                            linechart.animateX(1000);
                            linechart.invalidate();

                        }
                    });
                }));


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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
        linechart.setScaleEnabled(true);
        linechart.getXAxis().setGranularity(1.0f);
        linechart.getAxisLeft().setGranularity(1.0f);
        linechart.getAxisRight().setGranularity(1.0f);
        linechart.zoom(1f,1f,1f,1f);
        confirmed_linedata=new LineDataSet(confirmed_data,"confirmed");
        cured_linedata=new LineDataSet(cured_data,"cured");
        dead_linedata=new LineDataSet(dead_data,"dead");
        confirmed_linedata.setColors(0xc0392b);
        cured_linedata.setColors(0x2ecc71);
        dead_linedata.setColors(0x2d3436);
        confirmed_linedata.setColors(ColorTemplate.rgb("c0392b"));
        cured_linedata.setColors(ColorTemplate.rgb("2ecc71"));
        dead_linedata.setColors(ColorTemplate.rgb("2d3436"));
        line=new LineData();
        line.addDataSet(confirmed_linedata);
        line.addDataSet(cured_linedata);
        line.addDataSet(dead_linedata);
        line.setValueTextSize(10);
        linechart.setData(line);
        linechart.invalidate();
    }
}

class XAxisFormat extends ValueFormatter {
    private List<String> xAxis=new ArrayList<>();

    public XAxisFormat(List<String> xAxis)
    {
        this.xAxis=xAxis;
    }
    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return  xAxis.get((int)value);
    }
}