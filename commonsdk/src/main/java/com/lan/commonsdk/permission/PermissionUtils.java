package com.lan.commonsdk.permission;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import java.util.ArrayList;
import java.util.List;

public class PermissionUtils {
    private PermissionUtils() {
        throw new IllegalThreadStateException("该对象不能实例化");
    }

    public static FragmentActivity getActivity(Object object) {
        if (object instanceof Activity) {
            return (AppCompatActivity) object;
        }
        if (object instanceof Fragment) {
            return ((Fragment) object).getActivity();
        }
        return null;
    }

    public static List<String> checkPermission(Object object, String[] permissions) {
        List<String> noPermissions = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(getActivity(object), permission) !=
                    PackageManager.PERMISSION_GRANTED) {
                noPermissions.add(permission);
            }
        }
        return noPermissions;
    }

    public static void request(Object object, List<String> noPermission, int permissionCode) {
        ActivityCompat.requestPermissions(getActivity(object),
                noPermission.toArray(new String[noPermission.size()]), permissionCode);
    }

    public static void requestPermission(Object object, List<String> noPermission, int permissionCode) {
        PermissionFragment permissionFragment = new PermissionFragment();

        ArrayList<String> list = new ArrayList<>();
        list.addAll(noPermission);

        Bundle bundle = new Bundle();
        bundle.putSerializable("permission", list);
        bundle.putInt("code", permissionCode);
        permissionFragment.setArguments(bundle);
        (getActivity(object)).getSupportFragmentManager().beginTransaction().add(permissionFragment, getActivity(object).getClass().getName()).commit();
    }
}
