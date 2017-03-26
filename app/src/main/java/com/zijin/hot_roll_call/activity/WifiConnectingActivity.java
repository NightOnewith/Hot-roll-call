package com.zijin.hot_roll_call.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.zijin.hot_roll_call.R;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Created by yin on 2017/3/26.
 */
public class WifiConnectingActivity extends AppCompatActivity {

    private TextView textView;

    StringBuilder resultList;
    ArrayList<String> connectedMac;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connecting);

        textView = (TextView) findViewById(R.id.textview);

        try {
            connectedMac = getConnectMac();
        } catch (Exception e) {
            e.printStackTrace();
        }
        resultList = new StringBuilder();
        for (String mac : connectedMac) {
            resultList.append(mac);
            resultList.append("\n");
            try {
                connectedMac = getConnectMac();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String textString = resultList.toString();
        textView.setText(textString);

    }

    private ArrayList<String> getConnectMac() throws Exception {
        ArrayList<String> connectMacList = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader("/proc/net/arp"));
        String line;
        while ((line = br.readLine()) != null) {
            String[] splitted = line.split(" +");
            if (splitted != null && splitted.length >= 4) {
                String mac = splitted[3];
                connectMacList.add(mac);
            }
        }
        return connectMacList;
    }
}
