package com.lan.commonmodulesdk.generators;

import com.lan.annotations.WXPayEntryGenerator;
import com.lan.paysharedsk.wechat.WXSignInActivity;

@WXPayEntryGenerator(packageName = "com.lan.commonmodulesdk",
        supperClassName = WXSignInActivity.class)
public interface WXSignInEntry {
}
