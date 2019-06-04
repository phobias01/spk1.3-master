package com.example.supot.spk;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.akexorcist.simpletcp.SimpleTcpClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;


/**
 * A simple {@link Fragment} subclass.
 */
public class fmHome extends Fragment {


    public fmHome() {
        // Required empty public constructor
    }
    private TextView tvMaster;
    private SeekBar masterBar;
    private Context context;
    private ListView listSpk,listG1,listG2,listG3,listG4;
    private ArrayList<String> arraySpk,arrayG1,arrayG2,arrayG3,arrayG4;
    private ArrayAdapter adapterSpk,adapterG1,adapterG2,adapterG3,adapterG4;
    private Button butExport,butG1,butG2,butG3,butG4;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    private String dataOutput = null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fm_home, container, false);
        sp = this.getActivity().getSharedPreferences(Const.sp_channel, Context.MODE_PRIVATE);
        editor = sp.edit();
        loadData();
        initmasterBar(view);
        initmanegeGroup(view);
        return view;
    }

    public void initmasterBar(View view){
        masterBar = (SeekBar) view.findViewById(R.id.masterBar);
        tvMaster = (TextView) view.findViewById(R.id.tvMaster);
        int saveProgress = sp.getInt(Const.master_eq_slide,40)-80;
        tvMaster.setText(String.valueOf("MASTER : "+saveProgress+" dB"));
        masterBar.setMax(80);
        masterBar.setProgress(40);
        masterBar.setProgress(sp.getInt(Const.master_eq_slide,40));
        masterBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            float progressChanged;
            int value;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChanged = progress-80;
                value = progress;
                tvMaster.setText(String.format("MASTER : %.0f dB",progressChanged));
                dataOutput = String.format("eqm/%.0f",progressChanged);
                SimpleTcpClient.send(dataOutput,Const.ip,Const.port);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                editor.putInt(Const.master_eq_slide,value);
                editor.commit();

            }
        });

    }

    private void saveData() {
        Gson gson = new Gson();
        String jsonSpk = gson.toJson(arraySpk);
        String json1 = gson.toJson(arrayG1);
        String json2 = gson.toJson(arrayG2);
        String json3 = gson.toJson(arrayG3);
        String json4 = gson.toJson(arrayG4);
        editor.putString(Const.list_group_spk, jsonSpk);
        editor.putString(Const.list_group_1, json1);
        editor.putString(Const.list_group_2, json2);
        editor.putString(Const.list_group_3, json3);
        editor.putString(Const.list_group_4, json4);
        editor.commit();
    }

    private void loadData() {
        Gson gson = new Gson();
        String jsonSpk = sp.getString(Const.list_group_spk, null);
        String json1 = sp.getString(Const.list_group_1, null);
        String json2 = sp.getString(Const.list_group_2, null);
        String json3 = sp.getString(Const.list_group_3, null);
        String json4 = sp.getString(Const.list_group_4, null);

        Type type = new TypeToken<ArrayList>(){}.getType();
        arraySpk = gson.fromJson(jsonSpk, type);
        arrayG1 = gson.fromJson(json1, type);
        arrayG2 = gson.fromJson(json2, type);
        arrayG3 = gson.fromJson(json3, type);
        arrayG4 = gson.fromJson(json4, type);

        if (arraySpk == null) {
            arraySpk = new ArrayList<>();
        }
        if (arrayG1 == null) {
            arrayG1 = new ArrayList<>();
        }
        if (arrayG2 == null) {
            arrayG2 = new ArrayList<>();
        }
        if (arrayG3 == null) {
            arrayG3 = new ArrayList<>();
        }
        if (arrayG4 == null) {
            arrayG4 = new ArrayList<>();
        }
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }
    public void initmanegeGroup (View view){
        listSpk = (ListView) view.findViewById(R.id.listSpk);
        listG1 = (ListView) view.findViewById(R.id.listG1);
        listG2 = (ListView) view.findViewById(R.id.listG2);
        listG3 = (ListView) view.findViewById(R.id.listG3);
        listG4 = (ListView) view.findViewById(R.id.listG4);
        butExport = (Button) view.findViewById(R.id.butExport);
        butG1 = (Button) view.findViewById(R.id.butG1);
        butG2 = (Button) view.findViewById(R.id.butG2);
        butG3 = (Button) view.findViewById(R.id.butG3);
        butG4 = (Button) view.findViewById(R.id.butG4);
        //arraySpk = new ArrayList<>();

        for (int i = 1; i <= 50; i++) {
            arraySpk.add("Spk NO." + i);
        }
        adapterSpk = new ArrayAdapter<String>(this.context,android.R.layout.simple_list_item_single_choice,arraySpk);
        listSpk.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listSpk.setAdapter(adapterSpk);
        adapterG1= new ArrayAdapter<String>(this.context,android.R.layout.simple_list_item_single_choice,arrayG1);
        listG1.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listG1.setAdapter(adapterG1);
        adapterG2= new ArrayAdapter<String>(this.context,android.R.layout.simple_list_item_single_choice,arrayG2);
        listG2.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listG2.setAdapter(adapterG2);
        adapterG3= new ArrayAdapter<String>(this.context,android.R.layout.simple_list_item_single_choice,arrayG3);
        listG3.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listG3.setAdapter(adapterG3);
        adapterG4= new ArrayAdapter<String>(this.context,android.R.layout.simple_list_item_single_choice,arrayG4);
        listG4.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listG4.setAdapter(adapterG4);
        listSpk.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                butG1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SparseBooleanArray checkedItemPositions = listSpk.getCheckedItemPositions();
                        int itemCount = adapterSpk.getCount();
                        for(int i=itemCount-1; i >= 0; i--){
                            if(checkedItemPositions.get(i)){
                                arrayG1.add(arraySpk.get(i));
                                listG1.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                                listG1.setAdapter(adapterG1);
                                arraySpk.remove(arraySpk.get(i));
                            }
                        }
                        saveData();
                        checkedItemPositions.clear();
                        adapterSpk.notifyDataSetChanged();
                        adapterG1.notifyDataSetChanged();
                    }
                });
                butG2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SparseBooleanArray checkedItemPositions = listSpk.getCheckedItemPositions();
                        int itemCount = adapterSpk.getCount();

                        for(int i=itemCount-1; i >= 0; i--){
                            if(checkedItemPositions.get(i)){
                                arrayG2.add(arraySpk.get(i));
                                listG2.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                                listG2.setAdapter(adapterG2);
                                arraySpk.remove(arraySpk.get(i));
                            }
                        }
                        saveData();
                        checkedItemPositions.clear();
                        adapterSpk.notifyDataSetChanged();
                        adapterG2.notifyDataSetChanged();
                    }
                });
                butG3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SparseBooleanArray checkedItemPositions = listSpk.getCheckedItemPositions();
                        int itemCount = adapterSpk.getCount();

                        for(int i=itemCount-1; i >= 0; i--){
                            if(checkedItemPositions.get(i)){
                                arrayG3.add(arraySpk.get(i));
                                listG3.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                                listG3.setAdapter(adapterG3);
                                arraySpk.remove(arraySpk.get(i));
                            }
                        }
                        saveData();
                        checkedItemPositions.clear();
                        adapterSpk.notifyDataSetChanged();
                        adapterG3.notifyDataSetChanged();
                    }
                });
                butG4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SparseBooleanArray checkedItemPositions = listSpk.getCheckedItemPositions();
                        int itemCount = adapterSpk.getCount();

                        for(int i=itemCount-1; i >= 0; i--){
                            if(checkedItemPositions.get(i)){
                                arrayG4.add(arraySpk.get(i));
                                listG4.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                                listG4.setAdapter(adapterG4);
                                arraySpk.remove(arraySpk.get(i));
                            }
                        }
                        saveData();
                        checkedItemPositions.clear();
                        adapterSpk.notifyDataSetChanged();
                        adapterG4.notifyDataSetChanged();
                    }
                });
            }
        });
        listG1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                butExport.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SparseBooleanArray checkedItemPositions = listG1.getCheckedItemPositions();
                        int itemCount = adapterG1.getCount();

                        for(int i=itemCount-1; i >= 0; i--){
                            if(checkedItemPositions.get(i)){
                                arraySpk.add(arrayG1.get(i));
                                listSpk.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                                listSpk.setAdapter(adapterSpk);
                                arrayG1.remove(arrayG1.get(i));
                            }
                        }
                        saveData();
                        checkedItemPositions.clear();
                        adapterSpk.notifyDataSetChanged();
                        adapterG1.notifyDataSetChanged();
                    }
                });
            }
        });
        listG2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                butExport.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SparseBooleanArray checkedItemPositions = listG2.getCheckedItemPositions();
                        int itemCount = adapterG2.getCount();

                        for(int i=itemCount-1; i >= 0; i--){
                            if(checkedItemPositions.get(i)){
                                arraySpk.add(arrayG2.get(i));
                                listSpk.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                                listSpk.setAdapter(adapterSpk);
                                adapterG2.remove(arrayG2.get(i));
                            }
                        }
                        saveData();
                        checkedItemPositions.clear();
                        adapterSpk.notifyDataSetChanged();
                        adapterG2.notifyDataSetChanged();
                    }
                });
            }
        });
        listG3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                butExport.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SparseBooleanArray checkedItemPositions = listG3.getCheckedItemPositions();
                        int itemCount = adapterG3.getCount();

                        for(int i=itemCount-1; i >= 0; i--){
                            if(checkedItemPositions.get(i)){
                                arraySpk.add(arrayG3.get(i));
                                listSpk.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                                listSpk.setAdapter(adapterSpk);
                                adapterG3.remove(arrayG3.get(i));
                            }
                        }
                        saveData();
                        checkedItemPositions.clear();
                        adapterSpk.notifyDataSetChanged();
                        adapterG3.notifyDataSetChanged();
                    }
                });
            }
        });
        listG4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                butExport.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SparseBooleanArray checkedItemPositions = listG4.getCheckedItemPositions();
                        int itemCount = adapterG4.getCount();

                        for(int i=itemCount-1; i >= 0; i--){
                            if(checkedItemPositions.get(i)){
                                arraySpk.add(arrayG4.get(i));
                                listSpk.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                                listSpk.setAdapter(adapterSpk);
                                adapterG4.remove(arrayG4.get(i));
                            }
                        }
                        saveData();
                        checkedItemPositions.clear();
                        adapterSpk.notifyDataSetChanged();
                        adapterG4.notifyDataSetChanged();
                    }
                });
            }
        });
    }
}
