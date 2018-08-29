package sdkdemo.netcore.com.netcoresdkdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.clevertap.android.sdk.CleverTapAPI;
import com.clevertap.android.sdk.exceptions.CleverTapMetaDataNotFoundException;
import com.clevertap.android.sdk.exceptions.CleverTapPermissionsNotSatisfied;
import com.webengage.sdk.android.Analytics;
import com.webengage.sdk.android.WebEngage;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import in.netcore.smartechfcm.NetcoreSDK;

public class HomeActivity extends AppCompatActivity {
    private static String TAG = "MainActivity";

    private Button butCleverTap;
    private Button butWebengage;
    private Button butSmartech;


    //Initiationg CleverTap SDK
    CleverTapAPI ct;

    //Initialising Webengage analytics
    Analytics analytics;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
        initUI();
        initUIActions();
    }

    @Override
    protected void onStart() {
        super.onStart();
        trackScreenNavigated();
    }

    private void init(){
        try{
            ct = CleverTapAPI.getInstance(getApplicationContext());
        }catch (CleverTapMetaDataNotFoundException e){
            Log.e(TAG, e.getMessage());
        }catch (CleverTapPermissionsNotSatisfied e){
            Log.e(TAG, e.getMessage());
        }

        analytics = WebEngage.get().analytics();
    }

    private void initUI(){
        butCleverTap = findViewById(R.id.but_clevertap);
        butWebengage = findViewById(R.id.but_webengage);
        butSmartech = findViewById(R.id.but_smartech);

    }

    private void initUIActions(){
        butCleverTap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("TestEvent", "Click");
                map.put("Description", "This is awesome");

                ct.event.push("Click", map);
            }
        });
        butWebengage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> commentDetailsMap = new HashMap<>();
                commentDetailsMap.put("post_id", 123);
                commentDetailsMap.put("post_name", "WebEngage!!");
                commentDetailsMap.put("post_by", "abc@gmail.com");
                commentDetailsMap.put("comment", "adsdsdafsadasda");
                commentDetailsMap.put("comment_by", "def@gmail.com");

                analytics.track("Comments", commentDetailsMap);
            }
        });
        butSmartech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recordSmartechEvent();
            }
        });
    }

    private void recordSmartechEvent(){

        JSONObject payload = new JSONObject();
        try{
            payload.put("NAME", "CLICK");
            payload.put("VALUE", "Smartech Event");
        } catch (Exception e){

        }
        NetcoreSDK.track( this, getPackageName(),101 ,payload.toString());
    }

    private void trackScreenNavigated(){
        analytics.screenNavigated("Home");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                // Single menu item is selected do something
                // Ex: launching new activity/screen or show alert message
                ct.event.push("Logout");
                Toast.makeText(HomeActivity.this, "Logged out successfully", Toast.LENGTH_SHORT).show();
                finish();
                return true;
            default:
                return false;
        }
    }
}
