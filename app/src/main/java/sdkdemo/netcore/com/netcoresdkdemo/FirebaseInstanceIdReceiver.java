package sdkdemo.netcore.com.netcoresdkdemo;

import android.util.Log;

import com.clevertap.android.sdk.CleverTapAPI;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.iid.zzj;
import com.webengage.sdk.android.WebEngage;

/**
 * Created by amank on 25-08-2018.
 */

public class FirebaseInstanceIdReceiver extends FirebaseInstanceIdService {
    private static String TAG =  "FIIR";
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String token = FirebaseInstanceId.getInstance().getToken();
        try {
            CleverTapAPI.getInstance(this).data.pushFcmRegistrationId(token, true);
        }catch (Exception e){
            Log.e(TAG, e.getMessage());
        }
        WebEngage.get().setRegistrationID(token);
        Log.e("FIIR", token);
    }
}
