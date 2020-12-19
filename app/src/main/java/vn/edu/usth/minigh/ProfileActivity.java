package vn.edu.usth.minigh;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ProfileActivity extends BaseActivity {
    public ProfileActivity() {
        super(R.layout.activity_profile);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView txt_toolbar = (TextView) findViewById(R.id.main_text_bar);
        txt_toolbar.setText("Profile");
        LinearLayout layout = this.findViewById(R.id.profile);
        layout.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.secondaryColor));
    }
}
