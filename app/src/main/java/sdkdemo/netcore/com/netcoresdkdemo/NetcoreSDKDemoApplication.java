package sdkdemo.netcore.com.netcoresdkdemo;

import android.app.Application;

import com.clevertap.android.sdk.ActivityLifecycleCallback;
import com.google.firebase.iid.FirebaseInstanceId;
import com.webengage.sdk.android.WebEngage;
import com.webengage.sdk.android.WebEngageActivityLifeCycleCallbacks;
import com.webengage.sdk.android.WebEngageConfig;

/**
 * Created by amank on 26-08-2018.
 */

public class NetcoreSDKDemoApplication extends Application {
    @Override
    public void onCreate() {
        ActivityLifecycleCallback.register(this);
        super.onCreate();
        WebEngageConfig webEngageConfig = new WebEngageConfig.Builder()
                .setWebEngageKey("~2024b2d5")
                .setDebugMode(true)
                .build();
        registerActivityLifecycleCallbacks(new WebEngageActivityLifeCycleCallbacks(this, webEngageConfig));
        WebEngage.get().setRegistrationID(FirebaseInstanceId.getInstance().getId());
        WebEngage.registerPushNotificationCallback(new PushNotifCallbackImpl());
        WebEngage.registerInAppNotificationCallback(new InAppNotifCallbackImpl());
    }
}
