package com.example.supot.spk;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class fmSetspk extends Fragment {


    public fmSetspk() {
        // Required empty public constructor
    }

    private ArrayAdapter adapterIp,adapterNum,adapterlistIN;
    private ArrayList<String> arrayIp,arrayNum,arraylistIN;
    private ListView listSetspk;
    private Button butSetnum,button;
    private Spinner spinIP,spinNum;
    private Context context;
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fm_setspk, container, false);
        sp = this.getActivity().getSharedPreferences(Const.sp_channel, Context.MODE_PRIVATE);
        editor = sp.edit();
        loadData();
        initsetNumIP(view);
        return view;
    }

    private void saveData() {
        Gson gson = new Gson();
        String jsonlistIN = gson.toJson(arraylistIN);
        String jsonIp = gson.toJson(arrayIp);
        String jsonNum = gson.toJson(arrayNum);
        editor.putString(Const.spk_setnumip, jsonlistIN);
        editor.putString(Const.spk_ip, jsonIp);
        editor.putString(Const.spk_number, jsonNum);
        editor.commit();
    }

    private void loadData() {
        Gson gson = new Gson();
        String jsonlistIN = sp.getString(Const.spk_setnumip, null);
        String jsonIp = sp.getString(Const.spk_ip, null);
        String jsonNum = sp.getString(Const.spk_number, null);

        Type type = new TypeToken<ArrayList>(){}.getType();

        arraylistIN = gson.fromJson(jsonlistIN, type);
        arraylistIN = gson.fromJson(jsonIp, type);
        arraylistIN = gson.fromJson(jsonNum, type);

        if (arraylistIN == null) {
            arraylistIN = new ArrayList<>();
        }
        if (arrayIp == null) {
            arrayIp = new ArrayList<>();
            for (int i = 1; i <= 100; i++) {
                arrayIp.add("192.168.1." + i);
            }
        }
        if (arrayNum == null) {
            arrayNum = new ArrayList<>();
            for (int i = 1; i <= 100; i++) {
                arrayNum.add("No."+i);
            }
        }
    }
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public void initsetNumIP(View view){
        listSetspk = (ListView) view.findViewById(R.id.listSetspk);
        butSetnum = (Button) view.findViewById(R.id.butSetnum);
        button = (Button) view.findViewById(R.id.button);
        spinIP = (Spinner) view.findViewById(R.id.spinIP);
        spinNum = (Spinner) view.findViewById(R.id.spinNum);
       // arrayIp = new ArrayList<>();
        /*if(arrayIp == null) {
            for (int i = 1; i <= 100; i++) {
                arrayIp.add("192.168.1." + i);
            }
        }
        //arrayNum = new ArrayList<>();
        if(arrayNum == null) {
            for (int i = 1; i <= 100; i++) {
                arrayNum.add("No."+i);
            }
        }*/
        //arraylistIN = new ArrayList<>();
        adapterIp = new ArrayAdapter <String> (this.context, android.R.layout.simple_spinner_dropdown_item,arrayIp);
        spinIP.setAdapter(adapterIp);
        adapterNum = new ArrayAdapter <String> (this.context, android.R.layout.simple_spinner_dropdown_item,arrayNum);
        spinNum.setAdapter(adapterNum);

        adapterlistIN = new ArrayAdapter<String>(this.context,android.R.layout.simple_list_item_1,arraylistIN);
        listSetspk.setAdapter(adapterlistIN);
        try {
            butSetnum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int posIP = spinIP.getSelectedItemPosition();
                    int posNum = spinNum.getSelectedItemPosition();
                    arraylistIN.add("IP : " + arrayIp.get(posIP) + " ==>> Number is set = " + arrayNum.get(posNum));
                    arrayIp.remove(posIP);
                    arrayNum.remove(posNum);
                    listSetspk.setAdapter(adapterlistIN);
                    spinIP.setAdapter(adapterIp);
                    spinNum.setAdapter(adapterNum);
                    saveData();
                    adapterlistIN.notifyDataSetChanged();
                    adapterIp.notifyDataSetChanged();
                    adapterNum.notifyDataSetChanged();
                }
            });
        }catch (Exception e) {}
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arraylistIN.clear();
                listSetspk.setAdapter(adapterlistIN);
                saveData();
                adapterlistIN.notifyDataSetChanged();
            }
        });
    }
}
