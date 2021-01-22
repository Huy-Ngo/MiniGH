package vn.edu.usth.minigh;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import vn.edu.usth.minigh.fragments.IssuePrFragment;
import vn.edu.usth.minigh.fragments.PullRequestFragment;

public class PRActivity extends BaseActivity {
    Fragment frag;
    IssuePrFragment clickFrag;
    public PRActivity() {
        super(R.layout.activity_pr);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView txt_toolbar = (TextView) findViewById(R.id.main_text_bar);
        txt_toolbar.setText("Pull Requests");
        LinearLayout layout = this.findViewById(R.id.prs);
        layout.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.secondaryColor));

        RadioGroup sg = (RadioGroup)findViewById(R.id.segmented2);
        sg.check(R.id.button31);
        addFrag("pr", 5);
        sg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    default:
                        addFrag("pr", 5);
                        break;
                    case R.id.button32:
                        addFrag("Close", 3);
                        break;
                }
            }
        });
    }
    public void addFrag(String txt, int number){
        FragmentManager fm = getSupportFragmentManager();
        frag = fm.findFragmentById(R.id.prsFragment);
        FragmentTransaction ft = fm.beginTransaction();
        frag = new PullRequestFragment(txt, number);
        ft.replace(R.id.prsFragment, frag);
        ft.commit();
    }

    public void goToDiscuss(View view) {
        Intent intent = new Intent(this, DiscussionActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        
        TextView title = (TextView) view.findViewById(R.id.issuePrContent);
        intent.putExtra("title", (String) title.getText());

        TextView description =(TextView) view.findViewById(R.id.issuePrContent);
        intent.putExtra("description", description.getText());
        startActivity(intent);
    }
}
