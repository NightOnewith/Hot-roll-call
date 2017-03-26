package com.zijin.hot_roll_call.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.zijin.hot_roll_call.R;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

public class MainActivity extends AppCompatActivity {

    private Button button_call;
    private Button button_check;


    public static String hostmac; //本机MAC

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        button_call = (Button) findViewById(R.id.button_call);
        button_check = (Button) findViewById(R.id.button_check);
        button_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WifiApActivity.class);
                startActivity(intent);
            }
        });
        button_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "查看历史", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.action_message:
                dialog_message();
                break;
            case R.id.action_about:
                dialog_about();
                break;
            case R.id.action_help:
                dialog_help();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void dialog_message(){
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.dialog_message, (ViewGroup) findViewById(R.id.dialog_message));
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("我的信息");
        builder.setView(layout);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast.makeText(MainActivity.this, "确定", Toast.LENGTH_SHORT).show();
            }
        });
        builder.create().show();
        hostmac = getLocalMacAddress();//获取本机MAC
        TextView tv_mac = (TextView) layout.findViewById(R.id.tv_mac);
        tv_mac.setText(hostmac);
    }

    private void dialog_about(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);  //先得到构造器
        builder.setTitle("关于"); //设置标题
        builder.setMessage("开始“点名”吧！\n\n“一键点名”是一款为大学师生开发的Android小应用，旨在便捷点名。\n\n那么，可以有多便捷呢？\n\n只需一人安装App，开启热点，同学们（android/ios设备）成功连接并断开即完成点名。\n\n开发人员：殷志俊\n\ngithub项目地址：https://github.com/NightOnewith/Hot-roll-call.git"); //设置内容
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() { //设置确定按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); //关闭dialog
                Toast.makeText(MainActivity.this, "确认" + which, Toast.LENGTH_SHORT).show();
            }
        });
        builder.create().show();
    }

    private void dialog_help(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);  //先得到构造器
        builder.setTitle("如何实现点名？"); //设置标题
        builder.setMessage("    第一步：利用“一键点名”创建一个WiFi热点；\n\n    第二步：等待同学们连接这个热点并自动记录（即签到）；\n\n    第三步：结束点名后查看历史看看谁缺席了吧。\n\n提示：\n\n     为了获得更好的体验，请在首次使用阶段收集整理好同学们的移动设备MAC地址并导入“一键点名”；"); //设置内容
        builder.setPositiveButton("我知道了", new DialogInterface.OnClickListener() { //设置确定按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); //关闭dialog
                Toast.makeText(MainActivity.this, "我知道了" + which, Toast.LENGTH_SHORT).show();
            }
        });
        builder.create().show();
    }

    //获取MAC地址（需连接WiFi）
    public String getLocalMacAddress()
    {
        String macSerial = null;
        String str = "";
        try {
            Process pp = Runtime.getRuntime().exec("cat /sys/class/net/wlan0/address");
            InputStreamReader ir = new InputStreamReader(pp.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);

            for (; null != str;){
                str = input.readLine();
                if (str != null){
                    macSerial = str.trim();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  macSerial;
    }

}
