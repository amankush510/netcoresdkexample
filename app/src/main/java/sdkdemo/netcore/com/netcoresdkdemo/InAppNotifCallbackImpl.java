package sdkdemo.netcore.com.netcoresdkdemo;

import android.content.Context;
import android.util.Log;

import com.webengage.sdk.android.actions.render.InAppNotificationData;
import com.webengage.sdk.android.callbacks.InAppNotificationCallbacks;

/**
 * Created by amank on 26-08-2018.
 */

public class InAppNotifCallbackImpl implements InAppNotificationCallbacks {
    private static String TAG = "IANI";

    @Override
    public InAppNotificationData onInAppNotificationPrepared(Context context, InAppNotificationData inAppNotificationData) {
        Log.e(TAG, "In App Notification Received");
        return null;
    }

    @Override
    public void onInAppNotificationShown(Context context, InAppNotificationData inAppNotificationData) {
        Log.e(TAG, "In App Notification Shown");
    }

    @Override
    public boolean onInAppNotificationClicked(Context context, InAppNotificationData inAppNotificationData, String s) {
        Log.e(TAG, "In App Notification Clicked");
        return false;
    }

    @Override
    public void onInAppNotificationDismissed(Context context, InAppNotificationData inAppNotificationData) {
        Log.e(TAG, "In App Notification Dismissed");

    }
}
