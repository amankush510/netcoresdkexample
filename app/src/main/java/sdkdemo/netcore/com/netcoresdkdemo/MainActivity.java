package sdkdemo.netcore.com.netcoresdkdemo;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.clevertap.android.sdk.Application;
import com.clevertap.android.sdk.CleverTapAPI;
import com.clevertap.android.sdk.exceptions.CleverTapMetaDataNotFoundException;
import com.clevertap.android.sdk.exceptions.CleverTapPermissionsNotSatisfied;
import com.webengage.sdk.android.Analytics;
import com.webengage.sdk.android.Channel;
import com.webengage.sdk.android.User;
import com.webengage.sdk.android.WebEngage;
import com.webengage.sdk.android.utils.Gender;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import in.netcore.smartechfcm.NetcoreSDK;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "MainActivity";

    private EditText etEmail;
    private EditText etPassword;
    private Button butLogin;


    //Initiationg CleverTap SDK
    CleverTapAPI ct;

    Analytics analytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Initiating Smartech SDK
        NetcoreSDK.register(getApplication(),"2a00011f78e25f77374cc9f085d89eb4", this.getPackageName());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initUI();
        initUIActions();
    }

    private void init(){
        try{
            ct = CleverTapAPI.getInstance(getApplicationContext());
        }catch (CleverTapMetaDataNotFoundException e){
            Log.e(TAG, e.getMessage());
        }catch (CleverTapPermissionsNotSatisfied e){
            Log.e(TAG, e.getMessage());
        }

        //Initiating WebEngage SDK
        analytics = WebEngage.get().analytics();




    }

    private void initUI(){
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        butLogin = findViewById(R.id.but_login);
    }

    private void initUIActions(){
        butLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ct.event.push("Login");
                if(etEmail.getText() != null && etEmail.getText().toString().equalsIgnoreCase("abc@gmail.com")){
                    if(etPassword.getText() != null && etPassword.getText().toString().equalsIgnoreCase("12345678")){
                        setWebEngageuser();
                        NetcoreSDK.login(MainActivity.this, getPackageName());
                        setSmartechProfile();
                        Intent in = new Intent(MainActivity.this, HomeActivity.class);
                        startActivity(in);
                    } else{
                        Toast.makeText(MainActivity.this, "Invalid Credentials" , Toast.LENGTH_SHORT).show();
                    }
                } else{
                    Toast.makeText(MainActivity.this, "Invalid Credentials" , Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setWebEngageuser(){
        User user = WebEngage.get().user();
        user.login("hardcodeduserid");
        user.setEmail(etEmail.getText().toString());
        user.setBirthDate("1992-07-21");
        user.setPhoneNumber("+917900199609");
        user.setGender(Gender.MALE);
        user.setFirstName("Aman");
        user.setLastName("Kush");
        user.setCompany("Netcore");
        user.setOptIn(Channel.PUSH, true);
        user.setOptIn(Channel.IN_APP, true);
        user.setAttribute("Facebook User Name", "aman.emma");
        user.setAttribute("Age", 25);

        Map<String, Object> address = new HashMap<>();
        address.put("Address", "Bhawani Nagar, marol, Andheri East");
        address.put("City","Mumbai");
        address.put("State", "Maharashtra");
        address.put("Country","India");
        address.put("Pincode", "400059");

        user.setAttributes(address);

    }

    private void setSmartechProfile(){
        JSONObject newProfile = new JSONObject();
        try {
            newProfile.put("NAME", "Aman Kush");
            newProfile.put("AGE", 25);
            newProfile.put("MOBILE", "7900199609");
            newProfile.put("EMAIL", "abc@gmail.com");
            newProfile.put("COMPANY", "Netcore");
            newProfile.put("DOB", "1992-07-21");
            NetcoreSDK.profile(this, getPackageName(),newProfile);
        } catch (Exception e){

        }
    }


}
