package vn.edu.usth.minigh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;

public class AuthActivity extends AppCompatActivity {

    public AuthActivity() {
        super(R.layout.activity_auth);
    }

    @Override
    protected void onStart() {
        super.onStart();
        checkSession();
    }

    private void checkSession() {
        //check if user is logged in
        //if user is logged in --> move to mainActivity
        SessionManagement sessionManagement = new SessionManagement(getApplicationContext());
        int userID = sessionManagement.getSession();

        if(userID != -1) {
            //user id logged in and so move to mainActivity
            moveToProfileActivity();
        }
    }

    public void login(View view) {
        // 1.log in to app and save session of user
        // 2. move to mainActivity

        //1. login and save session
        User user = new User(42069,"meteora");
        SessionManagement sessionManagement = new SessionManagement(getApplicationContext());
        sessionManagement.saveSession(user);

        //2. step
        moveToProfileActivity();
    }

    private void moveToProfileActivity() {
        Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
        startActivity(intent);
    }
}