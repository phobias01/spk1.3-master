package com.example.supot.spk;

import android.util.Log;

public class sendTCP {

    public void sendTCP(String data){

        if(!Const.ip.equalsIgnoreCase(null)){
            Const.spkconnect.sendData(data);
            Log.d("26JanV1", "sendTCP ==>  " + data);
        }

    }

}
