package sdkdemo.netcore.com.netcoresdkdemo;

import android.os.Bundle;
import android.util.Log;

import com.clevertap.android.sdk.CleverTapAPI;
import com.clevertap.android.sdk.NotificationInfo;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.webengage.sdk.android.WebEngage;

import java.util.Map;

import in.netcore.smartechfcm.NetcoreSDK;

/**
 * Created by amank on 25-08-2018.
 */

public class FirebaseMessagingServiceDemo extends FirebaseMessagingService {
    private static String TAG = "FMSD";

    @Override
    public void onMessageReceived(RemoteMessage message) {
        super.onMessageReceived(message);
        processCleverTapNotif(message);
        processWebEngageNotif(message);
        processSmartechNotif(message);

    }

    private void processCleverTapNotif(RemoteMessage message){
        try {
            if (message.getData().size() > 0) {
                Bundle extras = new Bundle();
                for (Map.Entry<String, String> entry : message.getData().entrySet()) {
                    extras.putString(entry.getKey(), entry.getValue());
                }

                NotificationInfo info = CleverTapAPI.getNotificationInfo(extras);

                if (info.fromCleverTap) {
                    CleverTapAPI.createNotification(getApplicationContext(), extras);
                } else {
                    // not from CleverTap handle yourself or pass to another provider
                }
            }
        } catch (Throwable t) {
            Log.d("MYFCMLIST", "Error parsing FCM message", t);
        }
    }

    private void processWebEngageNotif(RemoteMessage message){
        Map<String, String> notifData = message.getData();
        if(notifData != null && notifData.get("source") != null && notifData.get("source").equalsIgnoreCase("webengage")){
            WebEngage.get().receive(notifData);
        }
    }

    private void processSmartechNotif(RemoteMessage message){
        boolean pushFromSmartech = NetcoreSDK.handleNotification(this, message);
        if(!pushFromSmartech){
            Log.e(TAG, "Message to be handled by app");
        }
    }
}
