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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fm_home, container, false);
        sp = this.getActivity().getSharedPreferences(Const.sp_channel, Context.MODE_PRIVATE);
        editor = sp.edit();
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
        arraySpk = new ArrayList<>();
        arrayG1 = new ArrayList<>();
        arrayG2 = new ArrayList<>();
        arrayG3 = new ArrayList<>();
        arrayG4 = new ArrayList<>();

        //Set<String> setSpk = new HashSet<String>();
        for (int i = 0; i < 50; i++) {
            arraySpk.add("Spk NO." + i);
            //setSpk.add("Spk NO." + i);
        }

        adapterSpk = new ArrayAdapter<String>(this.context,android.R.layout.simple_list_item_single_choice,arraySpk);
        listSpk.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listSpk.setAdapter(adapterSpk);

        adapterG1= new ArrayAdapter<String>(this.context,android.R.layout.simple_list_item_single_choice,arrayG1);
        adapterG2= new ArrayAdapter<String>(this.context,android.R.layout.simple_list_item_single_choice,arrayG2);
        adapterG3= new ArrayAdapter<String>(this.context,android.R.layout.simple_list_item_single_choice,arrayG3);
        adapterG4= new ArrayAdapter<String>(this.context,android.R.layout.simple_list_item_single_choice,arrayG4);

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
                                adapterSpk.remove(arraySpk.get(i));

                            }
                        }
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
                                adapterSpk.remove(arraySpk.get(i));
                            }
                        }
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
                                adapterSpk.remove(arraySpk.get(i));
                            }
                        }
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
                                adapterSpk.remove(arraySpk.get(i));
                            }
                        }
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
                                adapterG1.remove(arrayG1.get(i));
                            }
                        }
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
                        checkedItemPositions.clear();
                        adapterSpk.notifyDataSetChanged();
                        adapterG4.notifyDataSetChanged();
                    }
                });
            }
        });
    }
}
