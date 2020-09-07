package com.example.liyuchen.ui.epidemic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import java.util.ArrayList;
import java.util.Map;

public class CovidDataFragment extends Fragment {

    private Spinner spinner_global;
    private Spinner spinner_district;
    private Spinner spinner_province;
    private Spinner spinner_country;
    private String global="北京";
    private String place="d1";
    private LineChart linechart;
    private Description description=new Description();
    private ArrayList<Entry> coviddata=new ArrayList<>();
    private LineDataSet linedata;
    private LineData line;

    public CovidDataFragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_covid_data, container, false);
        initspinner(root);
        initlinechart(root);
        return root;
    }

    private void initspinner(View root)
    {
        spinner_global=root.findViewById(R.id.spinner_global);
        spinner_district=root.findViewById(R.id.spinner_district);
        spinner_province=root.findViewById(R.id.spinner_province);
        spinner_country=root.findViewById(R.id.spinner_country);
        spinner_global.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                global=parent.getItemAtPosition(position).toString();
                Toast.makeText(root.getContext(),global,Toast.LENGTH_LONG).show();
                if(global.equals("北京"))
                {
                    spinner_district.setVisibility(View.VISIBLE);
                    spinner_province.setVisibility(View.GONE);
                    spinner_country.setVisibility(View.GONE);
                }
                else if(global.equals("中国"))
                {
                    spinner_district.setVisibility(View.GONE);
                    spinner_province.setVisibility(View.VISIBLE);
                    spinner_country.setVisibility(View.GONE);
                }
                else if(global.equals("全球"))
                {
                    spinner_district.setVisibility(View.GONE);
                    spinner_province.setVisibility(View.GONE);
                    spinner_country.setVisibility(View.VISIBLE);
                }
                linechart.invalidate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                place=parent.getItemAtPosition(position).toString();
                Toast.makeText(root.getContext(),place,Toast.LENGTH_LONG).show();
                linechart.invalidate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_province.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                place=parent.getItemAtPosition(position).toString();
                Toast.makeText(root.getContext(),place,Toast.LENGTH_LONG).show();
                linechart.invalidate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            place=parent.getItemAtPosition(position).toString();
            Toast.makeText(root.getContext(),place,Toast.LENGTH_LONG).show();
            linechart.invalidate();
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
        description.setText(global+"."+place);
        description.setTextSize(20);
        linechart.setDrawGridBackground(true);
        linechart.setDragEnabled(true);
        linechart.setNoDataText("no data found");
//        linechart.setScaleY(1);
        for(int i=1;i<100;i++)
            coviddata.add(new Entry(i,i));
        linedata=new LineDataSet(coviddata,"coviddata");
        line=new LineData(linedata);
        linechart.setData(line);
        linechart.invalidate();
    }
}
