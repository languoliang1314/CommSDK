package com.lan.commonmodulesdk.generators;

import com.lan.annotations.WXPayEntryGenerator;
import com.lan.paysharedsk.wechat.WXPayActivity;

@WXPayEntryGenerator(packageName = "com.lan.commonmodulesdk",
        supperClassName = WXPayActivity.class)
public interface WXPayEntry {
}
