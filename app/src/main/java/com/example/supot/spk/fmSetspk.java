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
    private Button butSetnum;
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
        initsetNumIP(view);
        return view;
    }

    private void saveData() {
       /* Gson gson = new Gson();
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
        editor.commit();*/
    }

    private void loadData() {
        /*Gson gson = new Gson();
        String jsonSpk = sp.getString(Const.list_group_spk, null);
        String json1 = sp.getString(Const.list_group_1, null);
        String json2 = sp.getString(Const.list_group_2, null);
        String json3 = sp.getString(Const.list_group_3, null);
        String json4 = sp.getString(Const.list_group_4, null);

        Type type = new TypeToken<ArrayList>(){}.getType();
        arrayIP = gson.fromJson(jsonSpk, type);
        //arrayG1 = gson.fromJson(json1, type);
        //arrayG2 = gson.fromJson(json2, type);



        if (arraySpk.isEmpty()) {
            arraySpk = new ArrayList<>();
        }
        if (arrayG1.isEmpty()) {
            arrayG1 = new ArrayList<>();
        }
        if (arrayG2.isEmpty()) {
            arrayG2 = new ArrayList<>();
        }*/

    }
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public void initsetNumIP(View view){
        listSetspk = (ListView) view.findViewById(R.id.listSetspk);
        butSetnum = (Button) view.findViewById(R.id.butSetnum);
        spinIP = (Spinner) view.findViewById(R.id.spinIP);
        spinNum = (Spinner) view.findViewById(R.id.spinNum);
        arrayIp = new ArrayList<>();
        if(arrayIp.isEmpty()) {
            for (int i = 1; i <= 100; i++) {
                arrayIp.add("192.168.1." + i);
            }
        }
        arrayNum = new ArrayList<>();
        if(arrayNum.isEmpty()) {
            for (int i = 1; i <= 100; i++) {
                arrayNum.add("No."+i);
            }
        }
        arraylistIN = new ArrayList<>();
        adapterIp = new ArrayAdapter <String> (this.context, android.R.layout.simple_spinner_dropdown_item,arrayIp);
        spinIP.setAdapter(adapterIp);
        adapterNum = new ArrayAdapter <String> (this.context, android.R.layout.simple_spinner_dropdown_item,arrayNum);
        spinNum.setAdapter(adapterNum);

        adapterlistIN= new ArrayAdapter<String>(this.context,android.R.layout.simple_list_item_1,arraylistIN);
        listSetspk.setAdapter(adapterlistIN);

                butSetnum.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int posIP = spinIP.getSelectedItemPosition();
                        int posNum = spinNum.getSelectedItemPosition();
                        arraylistIN.add("IP : "+arrayIp.get(posIP)+" ==>> Number is set = "+arrayNum.get(posNum));
                       // int i = arraylistIN.size();
                        String spkno = "SPK"+arrayNum.get(posNum);
                        arrayIp.remove(posIP);
                        arrayNum.remove(posNum);
                        adapterlistIN.notifyDataSetChanged();
                        adapterIp.notifyDataSetChanged();
                        adapterNum.notifyDataSetChanged();
                        editor.putString(Const.spk_number,spkno);
                        //editor.putInt(Const.numberCe,i);
                        editor.commit();
                    }
                });


    }

}
