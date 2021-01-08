package vn.edu.usth.minigh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class IssueActivity extends BaseActivity {
    Fragment frag;

    public IssueActivity() {
        super(R.layout.activity_issue);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView txt_toolbar = (TextView) findViewById(R.id.main_text_bar);
        txt_toolbar.setText("Issues");
        LinearLayout layout = this.findViewById(R.id.issues);
        layout.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.secondaryColor));

        RadioGroup sg = (RadioGroup)findViewById(R.id.segmented2);
        sg.check(R.id.button21);
        addFrag("Open", 3);
        sg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    default:
                        addFrag("Open", 3);
                        break;
                    case R.id.button22:
                        addFrag("Close", 5);
                        break;
                }
            }
        });

    }
    public void addFrag(String txt, int number){
        FragmentManager fm = getSupportFragmentManager();
        frag = fm.findFragmentById(R.id.issuesFragment);
        FragmentTransaction ft = fm.beginTransaction();
        frag = new vn.edu.usth.minigh.IssueFragment(txt, number);
        ft.replace(R.id.issuesFragment, frag);
        ft.commit();
    }

    public void goToIssue(View view) {
        Intent intent = new Intent(this, IssueDiscussionActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
