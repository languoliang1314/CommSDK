package com.lan.paysharedsk.callbacks;

public interface IAlPayResultListener {
    void onPaySuccess();
    void onPaying();

    void onPayFail();

    void onPayCancel();

    void onPayConnectError();
}
