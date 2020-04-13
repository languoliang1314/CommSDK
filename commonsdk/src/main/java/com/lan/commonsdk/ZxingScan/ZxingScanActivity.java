package com.lan.commonsdk.ZxingScan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.zxing.Result;
import com.lan.commonsdk.R;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ZxingScanActivity extends AppCompatActivity {
    private ZXingScannerView mScannerView;

    private ZXingScannerView.ResultHandler mResultHandler = new ZXingScannerView.ResultHandler() {
        @Override
        public void handleResult(Result result) {
            mScannerView.resumeCameraPreview(mResultHandler); //重新进入扫描二维码
            Intent intent=new Intent();
            intent.putExtra("scanText",result.getText());
            setResult(RESULT_OK,intent);
            finish();

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zxing_scan);
        mScannerView = (ZXingScannerView) findViewById(R.id.scannerView);
        mScannerView.setResultHandler(mResultHandler);
    }
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(mResultHandler);
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }
//    private void toggleFlash() {
//        mFlash = !mFlash;
//        mScannerView.setFlash(mFlash);
//    }

}
