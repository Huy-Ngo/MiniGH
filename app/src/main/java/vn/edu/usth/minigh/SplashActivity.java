package vn.edu.usth.minigh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onStart() {
        super.onStart();
        final SessionManagement sessionManagement = new SessionManagement(getApplicationContext());
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                int userID = sessionManagement.getSession();
                if(userID != -1) redirectActivity(ProfileActivity.class);
                else redirectActivity(AuthActivity.class);
            }
        }, 2000);
    }

    public void redirectActivity(Class aclass) {
        Intent intent = new Intent(this, aclass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}