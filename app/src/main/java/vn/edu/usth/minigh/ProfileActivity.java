package vn.edu.usth.minigh;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import vn.edu.usth.minigh.BaseActivity;

public class ProfileActivity extends BaseActivity {
    public ProfileActivity() {
        super(R.layout.activity_profile);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView txt_toolbar = (TextView) findViewById(R.id.main_text_bar);
        txt_toolbar.setText("Profile");
    }

    @Override
    public void clickProfile(View view){
        recreate();
    }
}
