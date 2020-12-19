package vn.edu.usth.minigh;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import vn.edu.usth.minigh.BaseActivity;

public class RepoActivity extends BaseActivity {
    public RepoActivity() {
        super(R.layout.activity_repo);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView txt_toolbar = (TextView) findViewById(R.id.main_text_bar);
        txt_toolbar.setText("Repository");
    }

    @Override
    public void clickRepo(View view) {
        recreate();
    }
}
