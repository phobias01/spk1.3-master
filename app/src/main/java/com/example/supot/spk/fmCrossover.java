package com.example.supot.spk;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;


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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fm_crossover, container, false);
        initCrossoverBar(view);
        return view;

    }
    private void initCrossoverBar(View view) {
        crossBar = (CrystalRangeSeekbar) view.findViewById(R.id.crossBar);
        crossBar.setMinValue(20);
        crossBar.setMaxValue(20000);

        tvMin = (TextView) view.findViewById(R.id.tvMin);
        tvMax = (TextView) view.findViewById(R.id.tvMax);

        crossBar.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                tvMin.setText(String.valueOf(minValue)+" Hz");
                tvMax.setText(String.valueOf(maxValue)+" Hz");
            }
        });

    }
}
