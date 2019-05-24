package com.example.supot.spk;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;
import com.jaygoo.widget.OnRangeChangedListener;
import com.jaygoo.widget.RangeSeekBar;


/**
 * A simple {@link Fragment} subclass.
 */
public class fmCrossover extends Fragment {


    public fmCrossover() {
        // Required empty public constructor
    }

    private CrystalRangeSeekbar crossBar;
    private TextView tvMin;
    private TextView tvMax;
    private RangeSeekBar crossoverBar;
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fm_crossover, container, false);
        sp = this.getActivity().getSharedPreferences(Const.sp_channel, Context.MODE_PRIVATE);
        editor = sp.edit();
        initCrossoverBar(view);
        return view;

    }
    private void initCrossoverBar(View view) {
        tvMin = (TextView) view.findViewById(R.id.tvMin);
        tvMax = (TextView) view.findViewById(R.id.tvMax);
        crossoverBar = (RangeSeekBar)view.findViewById(R.id.crossoverBar);
        crossoverBar.setRange(20,20000);
        crossoverBar.setValue(sp.getInt(Const.crossover_min,20),sp.getInt(Const.crossover_max,20000));
        int minValue = sp.getInt(Const.crossover_min,20);
        int maxValue = sp.getInt(Const.crossover_max,20000);
        tvMin.setText(String.valueOf(minValue+" Hz"));
        tvMax.setText(String.valueOf(maxValue+" Hz"));

        crossoverBar.setOnRangeChangedListener(new OnRangeChangedListener() {
            int Lvalue;
            int Rvalue;
            @Override
            public void onRangeChanged(RangeSeekBar view, float leftValue, float rightValue, boolean isFromUser) {
                tvMin.setText(String.format("%.0f Hz",leftValue));
                tvMax.setText(String.format("%.0f Hz",rightValue));
                Lvalue = (int)leftValue;
                Rvalue = (int)rightValue;
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
}
