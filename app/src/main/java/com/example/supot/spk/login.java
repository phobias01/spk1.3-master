package com.example.supot.spk;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class login extends AppCompatActivity {
    private Button butCon,butOff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setSubtitle("LOGIN");
        initView();
    }
    private void initView() {
        butCon = findViewById(R.id.butCon);
        butOff = findViewById(R.id.butOff);
        final EditText editIP = (EditText) findViewById(R.id.editIP);

        butOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Const.ip = null;
                Intent intent = new Intent(login.this,MainActivity.class);
                startActivity(intent);
                //Toast.makeText(getActivity(), "Not Connect", Toast.LENGTH_SHORT).show();
            }
        });
        butCon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Const.ip = editIP.getText().toString();
                Intent intent = new Intent(login.this,MainActivity.class);
                startActivity(intent);
                //Toast.makeText(getActivity(), Const.ip+"/"+Const.port, Toast.LENGTH_SHORT).show();

            }
        });
    }
}
