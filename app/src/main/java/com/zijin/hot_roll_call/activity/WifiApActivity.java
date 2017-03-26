package com.zijin.hot_roll_call.activity;

import com.zijin.hot_roll_call.R;
import com.zijin.hot_roll_call.utils.WifiAPUtil;
import com.zijin.hot_roll_call.utils.WifiAPUtil.WifiSecurityType;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class WifiApActivity extends Activity {
    private String TAG = "WifiApActivity";
    public final static boolean DEBUG = true;
    private Button mBtStartWifiAp,mBtStopWifiAp,check_device;
    private EditText mWifiSsid,mWifiPassword;
    private TextView mWifiApState;
    private WifiSecurityType mWifiType = WifiSecurityType.WIFICIPHER_NOPASS;

    private Handler mHandler = new Handler(){
        public void handleMessage(Message msg) {
            if(DEBUG) Log.i(TAG, "WifiApActivity message.what="+msg.what);
            switch (msg.what) {
                case WifiAPUtil.MESSAGE_AP_STATE_ENABLED:
                    String ssid = WifiAPUtil.getInstance(WifiApActivity.this).getValidApSsid();
                    String pw = WifiAPUtil.getInstance(WifiApActivity.this).getValidPassword();
                    int security = WifiAPUtil.getInstance(WifiApActivity.this).getValidSecurity();
                    mWifiApState.setText("wifi热点开启成功"+"\n"
                            +"SSID = "+ssid+"\n"
                            +"Password = "+pw +"\n");
                    Intent intent = new Intent(WifiApActivity.this, WifiConnectingActivity.class);
                    startActivity(intent);
                    break;
                case WifiAPUtil.MESSAGE_AP_STATE_FAILED:
                    mWifiApState.setText("wifi热点关闭");
                    break;
                default:
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);
        WifiAPUtil.getInstance(getApplicationContext());
        WifiAPUtil.getInstance(this).regitsterHandler(mHandler);

        mBtStartWifiAp = (Button) findViewById(R.id.bt_start_wifiap);
        mWifiSsid = (EditText) findViewById(R.id.et_ssid);
        mWifiPassword = (EditText) findViewById(R.id.et_password);
        mWifiApState = (TextView)findViewById(R.id.tv_state);
        mBtStopWifiAp = (Button) findViewById(R.id.bt_stop_wifiap);
        check_device = (Button) findViewById(R.id.check_device);

        mBtStartWifiAp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                String ssid = mWifiSsid.getText().toString();
                String password = mWifiPassword.getText().toString();
                if(DEBUG)Log.d(TAG, "ssid = "+ssid +"password = "+password);
                if(null == ssid || "".equals(ssid)){
                    Toast.makeText(WifiApActivity.this, "请输入ssid", Toast.LENGTH_SHORT).show();
                    return;
                }
                mWifiApState.setText("正在开启");
                WifiAPUtil.getInstance(WifiApActivity.this)
                        .turnOnWifiAp(ssid, password, mWifiType);

            }
        });

        mBtStopWifiAp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WifiAPUtil.getInstance(WifiApActivity.this).closeWifiAp();
            }
        });

        check_device.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WifiApActivity.this, WifiConnectingActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(DEBUG) Log.i(TAG, "WifiApActivity onBackPressed");
        finish();
    }
    @Override
    protected void onStop() {
        super.onStop();
        if(DEBUG) Log.i(TAG, "WifiApActivity onStop");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(DEBUG) Log.i(TAG, "WifiApActivity onDestroy");
        WifiAPUtil.getInstance(this).unregitsterHandler();
    }
}
