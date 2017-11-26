package co.aoscp.musicx;

import android.app.Application;
import android.os.Build;

import co.aoscp.musicx.appshortcuts.DynamicShortcutManager;

public class AoscpApp extends Application {
    public static final String TAG = AoscpApp.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();

        //Set up dynamic shortcuts
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            new DynamicShortcutManager(this).initDynamicShortcuts();
        }
    }
}
