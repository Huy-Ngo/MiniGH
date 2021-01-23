package vn.edu.usth.minigh.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import vn.edu.usth.minigh.DiscussionActivity;
import vn.edu.usth.minigh.IssueFragment;
import vn.edu.usth.minigh.R;

public class IssuesListFragment extends Fragment {
    Fragment frag;

    public IssuesListFragment() {
        super(R.layout.fragment_issues_list);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_issues_list, container, false);
        RadioGroup sg = (RadioGroup) view.findViewById(R.id.segmented2);
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
                        addFrag("Closed", 5);
                        break;
                }
            }
        });
        return view;
    }

    public void addFrag(String txt, int number){
        FragmentManager fm = getChildFragmentManager();
        frag = fm.findFragmentById(R.id.issuesFragment);
        FragmentTransaction  ft = fm.beginTransaction();
        frag = new IssueFragment(txt, number);
        ft.replace(R.id.issuesFragment, frag);
        ft.commit();
    }

    public void goToIssue(View view) {
        Intent intent = new Intent(getActivity(), DiscussionActivity.class);
        TextView title = (TextView) view.findViewById(R.id.issuePrContent);
        intent.putExtra("title", (String) title.getText());

        TextView description = (TextView) view.findViewById(R.id.issuePrContent);
        intent.putExtra("description",(String) description.getText());
        startActivity(intent);
    }
}
