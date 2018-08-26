package sdkdemo.netcore.com.netcoresdkdemo;

import android.content.Context;
import android.util.Log;

import com.webengage.sdk.android.actions.render.PushNotificationData;
import com.webengage.sdk.android.callbacks.PushNotificationCallbacks;

/**
 * Created by amank on 26-08-2018.
 */

public class PushNotifCallbackImpl implements PushNotificationCallbacks {
    private static String TAG = "PNCI";

    @Override
    public PushNotificationData onPushNotificationReceived(Context context, PushNotificationData pushNotificationData) {
        Log.e(TAG, "Push Notification Received");
        return null;
    }

    @Override
    public void onPushNotificationShown(Context context, PushNotificationData pushNotificationData) {
        Log.e(TAG, "Push Notification Shown");
    }

    @Override
    public boolean onPushNotificationClicked(Context context, PushNotificationData pushNotificationData) {
        Log.e(TAG, "Push Notification Clicked");
        return false;
    }

    @Override
    public void onPushNotificationDismissed(Context context, PushNotificationData pushNotificationData) {
        Log.e(TAG, "Push Notification Dismissed");
    }

    @Override
    public boolean onPushNotificationActionClicked(Context context, PushNotificationData pushNotificationData, String s) {
        Log.e(TAG, "Push Notification Action Clicked");
        return false;
    }
}
