package com.lan.commonsdk.permission;

import android.app.Activity;
import android.os.Build;
import android.util.SparseArray;

import androidx.fragment.app.Fragment;

import java.util.List;

public class PermissionHelp {
    private final static SparseArray<PermissionCallBack> sContainer = new SparseArray<>();

    private Object mObject;
    private int mPermissionCode;
    private String[] mPermissions;


    private PermissionHelp(Object o) {
        this.mObject = o;
    }

    public static PermissionHelp with(Activity activity) {
        return new PermissionHelp(activity);
    }

    public static PermissionHelp with(Fragment fragment) {
        return new PermissionHelp(fragment);
    }

    public PermissionHelp permissionCode(int permissionCode) {
        this.mPermissionCode = permissionCode;
        return this;
    }

    public PermissionHelp permissions(String[] permissions) {
        this.mPermissions = permissions;
        return this;
    }

    public void request(PermissionCallBack permissionReturn) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            //6.0以下系统
            permissionReturn.success();
            return;
        }
        sContainer.put(mPermissionCode, permissionReturn);
        //6.0以上系统 需要动态权限
        List<String> noPermission = PermissionUtils.checkPermission(mObject, mPermissions);
        if (noPermission.size() > 0) {
            //有没有申请的权限
//            PermissionUtils.request(mObject, noPermission, mPermissionCode);
            PermissionUtils.requestPermission(mObject, noPermission, mPermissionCode);
        } else {
            permissionReturn.success();
        }
    }

    /**
     * 处理返回
     *
     * @param object
     * @param requestCode
     * @param permissions
     */
    public static void requestResult(Object object, int requestCode, String[] permissions) {
        if (object instanceof Activity || object instanceof Fragment) {
            PermissionCallBack permissionReturn = sContainer.get(requestCode);
            if (permissionReturn != null) {
                List<String> list = PermissionUtils.checkPermission(object, permissions);
                if (list.size() > 0) {
                    permissionReturn.fail();
                } else {
                    permissionReturn.success();
                }
            }
            sContainer.clear();
        } else {
            throw new IllegalThreadStateException("参数不合法");
        }
    }
}
