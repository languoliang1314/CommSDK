package com.lan.commonsdk.permission;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class PermissionFragment extends Fragment {

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        ArrayList<String> permission = (ArrayList<String>) arguments.getSerializable("permission");
        int code = arguments.getInt("code");
        requestPermissions(permission.toArray(new String[permission.size() - 1]), code);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        getFragmentManager().beginTransaction().remove(this).commit();
        PermissionHelp.requestResult(getActivity(), requestCode, permissions);
    }
}
