package vn.edu.usth.minigh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void logout(View view) {
        SessionManagement sessionManagement = new SessionManagement(getApplicationContext());
        sessionManagement.removeSession();
        moveToAuthActivity();
    }

    private void moveToAuthActivity() {
        Intent intent = new Intent(getApplicationContext(), AuthActivity.class);
        startActivity(intent);
    }
}