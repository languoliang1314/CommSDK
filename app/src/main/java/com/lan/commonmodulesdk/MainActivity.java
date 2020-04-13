package com.lan.commonmodulesdk;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.Result;
import com.lan.commonsdk.ZxingScan.ZxingScanActivity;
import com.lan.commonsdk.permission.PermissionCallBack;
import com.lan.commonsdk.permission.PermissionHelp;
import com.lan.commonsdk.permission.PermissionUtils;
import com.lan.paysharedsk.pay.FastPay;
import com.lan.paysharedsk.pay.IAlPayResultListener;

import me.dm7.barcodescanner.zxing.ZXingScannerView;


public class MainActivity extends AppCompatActivity {
    public final static int CAMERA_CODE = 11;
    private boolean mFlash;
    private ZXingScannerView mScannerView;
    private String info="app_id=2015052600090779&biz_content=%7B%22timeout_express%22%3A%2230m%22%2C%22seller_id%22%3A%22%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22total_amount%22%3A%220.02%22%2C%22subject%22%3A%221%22%2C%22body%22%3A%22%E6%88%91%E6%98%AF%E6%B5%8B%E8%AF%95%E6%95%B0%E6%8D%AE%22%2C%22out_trade_no%22%3A%22314VYGIAGG7ZOYY%22%7D&charset=utf-8&method=alipay.trade.app.pay&sign_type=RSA2&timestamp=2016-08-15%2012%3A12%3A15&version=1.0&sign=MsbylYkCzlfYLy9PeRwUUIg9nZPeN9SfXPNavUCroGKR5Kqvx0nEnd3eRmKxJuthNUx4ERCXe552EV9PfwexqW%2B1wbKOdYtDIb4%2B7PL3Pc94RZL0zKaWcaY3tSL89%2FuAVUsQuFqEJdhIukuKygrXucvejOUgTCfoUdwTi7z%2BZzQ%3D";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PermissionHelp.with(this)
                .permissionCode(CAMERA_CODE)
                .permissions(new String[]{Manifest.permission.CAMERA})
                .request(new PermissionCallBack() {
                    @Override
                    public void success() {
                        initView();
                    }

                    @Override
                    public void fail() {

                    }
                });

    }
    private void initView(){
        findViewById(R.id.sanc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, ZxingScanActivity.class);
                //startActivityForResult(intent,101);
                FastPay.create(MainActivity.this)
                        .setPayResultListener(new IAlPayResultListener() {
                            @Override
                            public void onPaySuccess() {

                            }

                            @Override
                            public void onPaying() {

                            }

                            @Override
                            public void onPayFail() {

                            }

                            @Override
                            public void onPayCancel() {

                            }

                            @Override
                            public void onPayConnectError() {

                            }
                        })
                        .alPay(info);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            switch (requestCode){
                case 101:
                    Toast.makeText(getApplicationContext(),data.getStringExtra("scanText"),Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}
