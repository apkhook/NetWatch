package info.noverguo.netwatch.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.tencent.noverguo.hooktest.BuildConfig;

import info.noverguo.netwatch.utils.DLog;

/**
 * Created by noverguo on 2016/5/10.
 */
public class ReloadReceiver extends BroadcastReceiver {
    private static final String ACTION_RELOAD_BLACK = "info.noverguo.netwatch.service.RemoteUrlService.ACTION_RELOAD_BLACK";
    private static final String ACTION_RELOAD_PACKAGE = "info.noverguo.netwatch.service.RemoteUrlService.ACTION_RELOAD_PACKAGE";
    Runnable callback;
    ReloadReceiver(Runnable callback) {
        this.callback = callback;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        if (callback != null) {
            if (BuildConfig.DEBUG) DLog.i("ReloadReceiver.onReceive");
            callback.run();
        }
    }

    public static ReloadReceiver registerReloadBlack(Context context, Runnable callback) {
        ReloadReceiver receiver = new ReloadReceiver(callback);
        context.registerReceiver(receiver, new IntentFilter(ACTION_RELOAD_BLACK + context.getPackageName()));
        return receiver;
    }

    public static ReloadReceiver registerReloadPackage(Context context, Runnable callback) {
        ReloadReceiver receiver = new ReloadReceiver(callback);
        context.registerReceiver(receiver, new IntentFilter(ACTION_RELOAD_PACKAGE + context.getPackageName()));
        return receiver;
    }

    public void unregister(Context context) {
        context.unregisterReceiver(this);
    }


    public static void sendReloadBlack(Context context) {
        context.sendBroadcast(new Intent(ACTION_RELOAD_BLACK + context.getPackageName()));
    }

    public static void sendReloadBlack(Context context, String packageName) {
        context.sendBroadcast(new Intent(ACTION_RELOAD_BLACK + packageName));
    }

    public static void sendReloadPackage(Context context) {
        context.sendBroadcast(new Intent(ACTION_RELOAD_PACKAGE + context.getPackageName()));
    }

}
