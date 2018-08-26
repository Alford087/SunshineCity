package com.cc.sunshine.ui.base;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;


public abstract class PermissionCheckActivity extends BaseFragmentActivity {
    private String[] permissions;

    protected final int REQUEST_PERMISSION = 9999;

    public abstract String[] registerPermission();

    public abstract void onPermissionsResult(boolean result, String denyPermission);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.permissions = registerPermission();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkPermissionsWithGranted();
        }
    }


    private List<String> getDeniedPerms() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return new ArrayList<>(0);
        }
        //获得批量请求但被禁止的权限列表
        List<String> deniedPerms = new ArrayList<>();
        for (int i = 0; permissions != null && i < permissions.length; i++) {
            if (PackageManager.PERMISSION_GRANTED != checkSelfPermission(permissions[i])) {
                deniedPerms.add(permissions[i]);
            }
        }
        return deniedPerms;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void checkPermissionsWithGranted() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            onPermissionsResult(true, null);
            return;
        }
        //进行批量请求
        List<String> deniedPerms = getDeniedPerms();
        int denyPermNum = deniedPerms.size();
        if (denyPermNum != 0) {
            requestPermissions(deniedPerms.toArray(new String[denyPermNum]), REQUEST_PERMISSION);
        } else {
            onPermissionsResult(true, null);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION) {
            if (grantResults.length == 0) {
                return;
            }
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] < 0) {
                    onPermissionsResult(false, permissions[i]);
                    return;
                }
            }
            onPermissionsResult(true, null);
        }
    }

}
