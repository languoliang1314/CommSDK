package com.lan.paysharedsk.ali;

public interface IAlPayResultListener {
    void onPaySuccess();
    void onPaying();

    void onPayFail();

    void onPayCancel();

    void onPayConnectError();
}
