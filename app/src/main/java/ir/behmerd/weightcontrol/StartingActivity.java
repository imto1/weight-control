package ir.behmerd.weightcontrol;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;

import com.magnetadservices.sdk.MagnetSDK;


public class StartingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * Preferences preferences = new Preferences(getApplicationContext());
         * String language = preferences.getLanguage();
         * if(!language.equals("system") || language != "system"){
         * if(!language.equals("en") || language != "en") {
         * Locale locale = new Locale(language);
         * Locale.setDefault(locale);
         * Configuration config = new Configuration();
         * config.locale = locale;
         * getBaseContext().getResources().updateConfiguration(config,
         * getBaseContext().getResources().getDisplayMetrics());
         * }
         * }
         */
        setContentView(R.layout.activity_starting);

        try {
            MagnetSDK.initialize(getApplicationContext());
            MagnetSDK.getSettings().setTestMode(false);
        }
        catch(Exception e){
            Log.e("STARTING", "Initiating Magnet SDK failed!", e);
        }

        new CountDownTimer(2000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                startActivity(new Intent(StartingActivity.this,MainActivity.class));
            }
        }.start();
    }

    protected void onStop(){
        super.onStop();
        finish();
    }
}
