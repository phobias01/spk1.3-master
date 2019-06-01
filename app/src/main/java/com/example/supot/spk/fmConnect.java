package com.example.supot.spk;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class fmConnect extends Fragment {


    public fmConnect() {
        // Required empty public constructor
    }
    private Button butCon,butOff;
    private Toolbar toolbar;
    //SharedPreferences sp;
    //SharedPreferences.Editor editor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fm_connect, container, false);
        //sp = this.getActivity().getSharedPreferences(Const.sp_channel, Context.MODE_PRIVATE);
        //editor = sp.edit();
        initView(view);
        return  view;
    }
    private void initView(View view) {
        butCon = view.findViewById(R.id.butCon);
        butOff = view.findViewById(R.id.butOff);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        //editTextIPAddress = findViewById(R.id.edit_text_ip_address);
        final EditText editIP = (EditText)view.findViewById(R.id.editIP);

        butOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Const.ip = null;
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.maincontent,new fmHome());
                ft.commit();
                ((AppCompatActivity) getActivity()).getSupportActionBar().setSubtitle("HOME");
                //Toast.makeText(getActivity(), "Not Connect", Toast.LENGTH_SHORT).show();
            }
        });
        butCon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Const.ip = editIP.getText().toString();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.maincontent,new fmHome());
                ft.commit();
                //editor.putString(Const.ip,editIP.getText().toString());
                //editor.commit();
                ((AppCompatActivity) getActivity()).getSupportActionBar().setSubtitle("HOME");
                //Toast.makeText(getActivity(), Const.ip+"/"+Const.port, Toast.LENGTH_SHORT).show();

            }
        });
    }
}
