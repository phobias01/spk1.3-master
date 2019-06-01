package com.example.supot.spk;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.akexorcist.simpletcp.SimpleTcpClient;
import com.jaygoo.widget.OnRangeChangedListener;
import com.jaygoo.widget.RangeSeekBar;


/**
 * A simple {@link Fragment} subclass.
 */
public class fmCrossover extends Fragment {


    public fmCrossover() {
        // Required empty public constructor
    }

    private EditText editMin,editMax;
    private TextView tvMin;
    private TextView tvMax;
    private RangeSeekBar crossoverBar;
    private Button butSet;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    String dataOutput1 = null;
    private String dataOutput2 = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fm_crossover, container, false);
        sp = this.getActivity().getSharedPreferences(Const.sp_channel, Context.MODE_PRIVATE);
        editor = sp.edit();
        tvMin = (TextView) view.findViewById(R.id.tvMin);
        tvMax = (TextView) view.findViewById(R.id.tvMax);
        crossoverBar = (RangeSeekBar)view.findViewById(R.id.crossoverBar);
        initCrossoverBar(view);
        setVolumeCrossover(view);
        return view;

    }
    private void initCrossoverBar(View view) {
        crossoverBar.setRange(20,20000);
        crossoverBar.setValue(sp.getInt(Const.crossover_min,50),sp.getInt(Const.crossover_max,500));
        int minValue = sp.getInt(Const.crossover_min,20);
        int maxValue = sp.getInt(Const.crossover_max,20000);
        tvMin.setText(String.valueOf(minValue+" Hz"));
        tvMax.setText(String.valueOf(maxValue+" Hz"));

        crossoverBar.setOnRangeChangedListener(new OnRangeChangedListener() {
            int Lvalue=0;
            int Rvalue=0;
            @Override
            public void onRangeChanged(RangeSeekBar view, float leftValue, float rightValue, boolean isFromUser) {
                tvMin.setText(String.format("%.0f Hz",leftValue));
                tvMax.setText(String.format("%.0f Hz",rightValue));
               // Lvalue = (int)leftValue;
              //  Rvalue = (int)rightValue;
                dataOutput1 = String.format("CrossoverMin/%.0f",leftValue);
                dataOutput2 = String.format("CrossoverMax/%.0f",rightValue);
                if(Lvalue!=(int)leftValue) {
                    Lvalue = (int)leftValue;
                    SimpleTcpClient.send(dataOutput1, Const.ip, Const.port);
                    //Lvalue = (int)leftValue;
                }
                if(Rvalue!=(int)rightValue) {
                    Rvalue = (int)rightValue;
                    SimpleTcpClient.send(dataOutput2, Const.ip, Const.port);
                    //Rvalue = (int)rightValue;
                }
            }

            @Override
            public void onStartTrackingTouch(RangeSeekBar view, boolean isLeft) {

            }

            @Override
            public void onStopTrackingTouch(RangeSeekBar view, boolean isLeft) {
                editor.putInt(Const.crossover_min,Lvalue);
                editor.putInt(Const.crossover_max,Rvalue);
                editor.commit();
            }
        });
    }

    private void setVolumeCrossover (View view) {
        editMin = (EditText)view.findViewById(R.id.editMin);
        editMax = (EditText)view.findViewById(R.id.editMax);
        butSet = (Button) view.findViewById(R.id.butSet);
        butSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String min = editMin.getText().toString();
                    String max = editMax.getText().toString();
                    int valueMin = Integer.valueOf(min);
                    int valueMax = Integer.valueOf(max);
                    if(!min.equals(null)){
                        tvMin.setText(min+" Hz");
                        editor.putInt(Const.set_crossover_min,valueMin);
                        editor.commit();
                     }
                    if(!max.equals(null)){
                        tvMax.setText(max+" Hz");
                        editor.putInt(Const.set_crossover_max,valueMax);
                        editor.commit();
                    }
                    //Log.d("26JanV1", "position sickdata ==>  " + valueMin);
                    //Log.d("26JanV1", "position sickdata ==>  " + valueMax);
                    int cmin = sp.getInt(Const.crossover_min,50);
                    int cmax = sp.getInt(Const.crossover_max,500);
                    crossoverBar.setValue(sp.getInt(Const.set_crossover_min,cmin),sp.getInt(Const.set_crossover_max,cmax));
                    SimpleTcpClient.send("CrossoverMin/"+String.valueOf(cmin), Const.ip, Const.port);
                    SimpleTcpClient.send("CrossoverMax/"+String.valueOf(cmax), Const.ip, Const.port);
                }catch (Exception e) {Toast.makeText(getActivity(),"Please enter a value.", Toast.LENGTH_SHORT).show();}

            }
        });

    }
}
