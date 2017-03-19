package com.zijin.hot_roll_call;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button button_call;
    private Button button_check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        button_call = (Button) findViewById(R.id.button_call);
        button_call.setOnClickListener(this);
        button_check = (Button) findViewById(R.id.button_check);
        button_check.setOnClickListener(this);

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

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.button_call:
                dialog_call();
                break;
            case R.id.button_check:
                Toast.makeText(MainActivity.this, "查看历史", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void dialog_message(){
        final String items[]={"篮球","足球","排球"};
        final boolean selected[]={true,false,true};
        AlertDialog.Builder builder=new AlertDialog.Builder(this);  //先得到构造器
        builder.setTitle("提示"); //设置标题
        builder.setIcon(R.mipmap.ic_launcher);//设置图标，图片id即可
        builder.setMultiChoiceItems(items,selected,new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                // dialog.dismiss();
                Toast.makeText(MainActivity.this, items[which]+isChecked, Toast.LENGTH_SHORT).show();
            }
        });
        builder.setPositiveButton("确定",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast.makeText(MainActivity.this, "确定", Toast.LENGTH_SHORT).show();
                //android会自动根据你选择的改变selected数组的值。
                for (int i=0;i<selected.length;i++){
                    Log.e("hongliang",""+selected[i]);
                }
            }
        });
        builder.create().show();
    }

    private void dialog_about(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);  //先得到构造器
        builder.setTitle("提示"); //设置标题
        builder.setMessage("是否确认退出?"); //设置内容
        builder.setIcon(R.mipmap.ic_launcher);//设置图标，图片id即可
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() { //设置确定按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); //关闭dialog
                Toast.makeText(MainActivity.this, "确认" + which, Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() { //设置取消按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast.makeText(MainActivity.this, "取消" + which, Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNeutralButton("忽略", new DialogInterface.OnClickListener() {//设置忽略按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast.makeText(MainActivity.this, "忽略" + which, Toast.LENGTH_SHORT).show();
            }
        });
        //参数都设置完成了，创建并显示出来
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
        //参数都设置完成了，创建并显示出来
        builder.create().show();
    }

    private void dialog_call(){
        final String items[]={"篮球","足球","排球"};
        final boolean selected[]={true,false,true};
        AlertDialog.Builder builder=new AlertDialog.Builder(this);  //先得到构造器
        builder.setTitle("提示"); //设置标题
        builder.setIcon(R.mipmap.ic_launcher);//设置图标，图片id即可
        builder.setMultiChoiceItems(items,selected,new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                // dialog.dismiss();
                Toast.makeText(MainActivity.this, items[which]+isChecked, Toast.LENGTH_SHORT).show();
            }
        });
        builder.setPositiveButton("确定",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast.makeText(MainActivity.this, "确定", Toast.LENGTH_SHORT).show();
                //android会自动根据你选择的改变selected数组的值。
                for (int i=0;i<selected.length;i++){
                    Log.e("hongliang",""+selected[i]);
                }
            }
        });
        builder.create().show();
    }

}
