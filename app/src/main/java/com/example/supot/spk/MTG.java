package com.example.supot.spk;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class MTG extends AppCompatActivity {

    private Button butBack;
    private Switch swG1,swG2,swG3,swG4;
    private SeekBar BarG1,BarG2,BarG3,BarG4;
    private TextView tvG1,tvG2,tvG3,tvG4;
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mtg);
        sp = getSharedPreferences(Const.sp_channel, Context.MODE_PRIVATE);
        editor = sp.edit();
        initbarGroup();
        butBack = (Button) findViewById(R.id.butBack);
        butBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fmeq = new fmEQ();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.maincontent,fmeq);
                ft.commit();
                Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
                toolbar.setTitle("EQUALIZER");
            }
        });
    }
    private void initbarGroup(){
        BarG1 = (SeekBar) findViewById(R.id.BarG1);
        BarG2 = (SeekBar) findViewById(R.id.BarG2);
        BarG3 = (SeekBar) findViewById(R.id.BarG3);
        BarG4 = (SeekBar) findViewById(R.id.BarG4);

        tvG1 = (TextView) findViewById(R.id.tvG1);
        tvG2 = (TextView) findViewById(R.id.tvG2);
        tvG3 = (TextView) findViewById(R.id.tvG3);
        tvG4 = (TextView) findViewById(R.id.tvG4);

        initgroupSet();

        BarG1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int value;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                value = progress;
                tvG1.setText(String.format("G1 : "+(progress-80)+" dB"));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                editor.putInt(Const.group_value_1,value);
                editor.commit();
            }
        });
        BarG2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int value;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                value = progress;
                tvG2.setText(String.format("G2 : "+(progress-80)+" dB"));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                editor.putInt(Const.group_value_2,value);
                editor.commit();
            }
        });
        BarG3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int value;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                value = progress;
                tvG3.setText(String.format("G3 : "+(progress-80)+" dB"));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                editor.putInt(Const.group_value_3,value);
                editor.commit();
            }
        });
        BarG4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int value;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                value = progress;
                tvG4.setText(String.format("G4 : "+(progress-80)+" dB"));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                editor.putInt(Const.group_value_4,value);
                editor.commit();
            }
        });
    }
    private void initgroupSet(){
        BarG1.setMax(80);
        BarG2.setMax(80);
        BarG3.setMax(80);
        BarG4.setMax(80);

        BarG1.setProgress(sp.getInt(Const.group_value_1,80));
        BarG2.setProgress(sp.getInt(Const.group_value_2,80));
        BarG3.setProgress(sp.getInt(Const.group_value_3,80));
        BarG4.setProgress(sp.getInt(Const.group_value_4,80));

        int saveProgress1 = sp.getInt(Const.group_value_1,40)-80;
        int saveProgress2 = sp.getInt(Const.group_value_2,40)-80;
        int saveProgress3 = sp.getInt(Const.group_value_3,40)-80;
        int saveProgress4 = sp.getInt(Const.group_value_4,40)-80;

        tvG1.setText(String.valueOf("G1 : "+saveProgress1+" dB"));
        tvG2.setText(String.valueOf("G2 : "+saveProgress2+" dB"));
        tvG3.setText(String.valueOf("G3 : "+saveProgress3+" dB"));
        tvG4.setText(String.valueOf("G4 : "+saveProgress4+" dB"));

    }
}
