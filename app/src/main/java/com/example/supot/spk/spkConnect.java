package com.example.supot.spk;

import android.util.Log;
import android.widget.ProgressBar;

import java.io.*;
import java.net.Socket;

public class spkConnect {
    private String ip;
    private int port;

    private Socket s;
    private BufferedReader br;
    private PrintWriter pw;

    private boolean interrupt = false;

    boolean isReady = false;

    public spkConnect(String ip, int port) {
        this.ip = ip;
        this.port = port;
        Log.d("26JanV1", "position sickdata ==>  " + Const.ip+"/"+Const.port);
    }

    public void sendData(String str) {
        try {
            s = new Socket(this.ip, this.port);
            br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            pw = new PrintWriter(s.getOutputStream(), true);
            Log.d("26JanV1", "Connect ==>  " + str);
            pw.println(str);

            br.close();
            pw.close();
            s.close();
        } catch (IOException e) {
            Const.ip = "";
            e.printStackTrace();
        }

    }
}