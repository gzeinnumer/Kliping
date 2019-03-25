package com.gzeinnumer.kliping.presenter;

import android.net.Uri;

public interface I_AddPage {
    void showFileChooser();
    String getPath(Uri uri);
    void requestStoragePermission();
}
