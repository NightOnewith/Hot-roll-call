package com.zijin.hot_roll_call.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.zijin.hot_roll_call.R;
import com.zijin.hot_roll_call.dao.UsersDao;

/**
 * Created by yin on 2017/3/26.
 */

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
        SimpleDateFormat formatter = new SimpleDateFormat ("yyyy年MM月dd日 HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());
        String time = formatter.format(curDate);//获取系统当时时间
        UsersDao usersDao = new UsersDao(getApplication());
        usersDao.add("XXX", "000000000", textString, time);
        Toast.makeText(WifiConnectingActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
        textView.setText(textString + time);

    }

    private ArrayList<String> getConnectMac() throws Exception {
        ArrayList<String> connectMacList = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader("/proc/net/arp"));
        String line;
        while ((line = br.readLine()) != null) {
            String[] splitted = line.split(" +");//分割
            if (splitted != null && splitted.length >= 4) {
                String mac = splitted[3];
                connectMacList.add(mac);
            }
        }
//        br.close();
        return connectMacList;

    }
}