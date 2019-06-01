package com.example.supot.spk;


import android.content.Context;
import android.content.SharedPreferences;
import android.icu.text.DecimalFormat;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import com.akexorcist.simpletcp.SimpleTcpClient;
import com.akexorcist.simpletcp.SimpleTcpServer;


/**
 * A simple {@link Fragment} subclass.
 */
public class fmDelay extends Fragment {


    public fmDelay() {
        // Required empty public constructor
    }
    private SeekBar delayBar1,delayBar2,delayBar3,delayBar4;
    private TextView msView1,msView2,msView3,msView4,mView1,mView2,mView3,mView4;
    private Switch swG1,swG2,swG3,swG4;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    String dataOutput = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fm_delay, container, false);
        sp = this.getActivity().getSharedPreferences(Const.sp_channel, Context.MODE_PRIVATE);
        editor = sp.edit();
        initDelayBar(view);
        initswLockdelayBar(view);
        return view;
    }
    private void initDelayBar(View view){
        delayBar1 = (SeekBar) view.findViewById(R.id.delayBar1);
        delayBar2 = (SeekBar) view.findViewById(R.id.delayBar2);
        delayBar3 = (SeekBar) view.findViewById(R.id.delayBar3);
        delayBar4 = (SeekBar) view.findViewById(R.id.delayBar4);

        msView1 = (TextView) view.findViewById(R.id.msView1);
        msView2 = (TextView) view.findViewById(R.id.msView2);
        msView3 = (TextView) view.findViewById(R.id.msView3);
        msView4 = (TextView) view.findViewById(R.id.msView4);

        mView1 = (TextView) view.findViewById(R.id.mView1);
        mView2 = (TextView) view.findViewById(R.id.mView2);
        mView3 = (TextView) view.findViewById(R.id.mView3);
        mView4 = (TextView) view.findViewById(R.id.mView4);
        initsetDelayBar();
        delayBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int value;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                msView1.setText(progress+" ms");
                mView1.setText(String.format("%.2f",progress*0.343)+" m");
                value = progress;
                dataOutput = "delay1/"+progress;
                SimpleTcpClient.send(dataOutput, Const.ip, Const.port);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                editor.putInt(Const.delay_bar_1,value);
                editor.commit();
            }
        });

        delayBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int value;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                msView2.setText(progress+" ms");
                mView2.setText(String.format("%.2f",progress*0.343)+" m");
                value = progress;
                dataOutput = "delay2/"+progress;
                SimpleTcpClient.send(dataOutput, Const.ip, Const.port);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                editor.putInt(Const.delay_bar_2,value);
                editor.commit();
            }
        });

        delayBar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int value;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                msView3.setText(progress+" ms");
                mView3.setText(String.format("%.2f",progress*0.343)+" m");
                value = progress;
                dataOutput = "delay3/"+progress;
                SimpleTcpClient.send(dataOutput, Const.ip, Const.port);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                editor.putInt(Const.delay_bar_3,value);
                editor.commit();
            }
        });

        delayBar4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int value;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                msView4.setText(progress+" ms");
                mView4.setText(String.format("%.2f",progress*0.343)+" m");
                value = progress;
                dataOutput = "delay4/"+progress;
                SimpleTcpClient.send(dataOutput, Const.ip, Const.port);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                editor.putInt(Const.delay_bar_4,value);
                editor.commit();
            }
        });
    }
    private void initswLockdelayBar(View view){
        swG1 = (Switch) view.findViewById(R.id.swG1);
        swG2 = (Switch) view.findViewById(R.id.swG2);
        swG3 = (Switch) view.findViewById(R.id.swG3);
        swG4 = (Switch) view.findViewById(R.id.swG4);
        initsetSwitchDelay();
        swG1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    delayBar1.setEnabled(false);
                }else{
                    delayBar1.setEnabled(true);
                }
                editor.putBoolean(Const.switch_delay_1, isChecked);
                editor.commit();
            }
        });

        swG2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    delayBar2.setEnabled(false);
                }else{
                    delayBar2.setEnabled(true);
                }
                editor.putBoolean(Const.switch_delay_2, isChecked);
                editor.commit();
            }
        });

        swG3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    delayBar3.setEnabled(false);
                }else{
                    delayBar3.setEnabled(true);
                }
                editor.putBoolean(Const.switch_delay_3, isChecked);
                editor.commit();
            }
        });

        swG4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    delayBar4.setEnabled(false);
                }else{
                    delayBar4.setEnabled(true);
                }
                editor.putBoolean(Const.switch_delay_4, isChecked);
                editor.commit();
            }
        });
    }
    private void initsetDelayBar(){

        delayBar1.setProgress(sp.getInt(Const.delay_bar_1,0));
        delayBar2.setProgress(sp.getInt(Const.delay_bar_2,0));
        delayBar3.setProgress(sp.getInt(Const.delay_bar_3,0));
        delayBar4.setProgress(sp.getInt(Const.delay_bar_4,0));

        int saveProgress1 = sp.getInt(Const.delay_bar_1,0);
        int saveProgress2 = sp.getInt(Const.delay_bar_2,0);
        int saveProgress3 = sp.getInt(Const.delay_bar_3,0);
        int saveProgress4 = sp.getInt(Const.delay_bar_4,0);


        msView1.setText(saveProgress1+" ms");
        mView1.setText(String.format("%.2f",saveProgress1*0.343)+" m");
        msView2.setText(saveProgress2+" ms");
        mView2.setText(String.format("%.2f",saveProgress2*0.343)+" m");
        msView3.setText(saveProgress3+" ms");
        mView3.setText(String.format("%.2f",saveProgress3*0.343)+" m");
        msView4.setText(saveProgress4+" ms");
        mView4.setText(String.format("%.2f",saveProgress4*0.343)+" m");
    }
    private void initsetSwitchDelay() {
        swG1.setChecked(sp.getBoolean(Const.switch_delay_1, false));
        swG2.setChecked(sp.getBoolean(Const.switch_delay_2, false));
        swG3.setChecked(sp.getBoolean(Const.switch_delay_3, false));
        swG4.setChecked(sp.getBoolean(Const.switch_delay_4, false));

        if(sp.getBoolean(Const.switch_delay_1, false)==true){
            delayBar1.setEnabled(false);
        }
        if(sp.getBoolean(Const.switch_delay_2, false)==true){
            delayBar2.setEnabled(false);
        }
        if(sp.getBoolean(Const.switch_delay_3, false)==true){
            delayBar3.setEnabled(false);
        }
        if(sp.getBoolean(Const.switch_delay_4, false)==true){
            delayBar4.setEnabled(false);
        }
    }
}
