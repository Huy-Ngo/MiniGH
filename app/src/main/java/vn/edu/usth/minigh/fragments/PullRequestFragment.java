package vn.edu.usth.minigh.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import vn.edu.usth.minigh.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PullRequestFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PullRequestFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String nStatus;
    private int nNumber;
    public PullRequestFragment() {
        // Required empty public constructor
    }
    public PullRequestFragment(String status, int number){
        this.nStatus = status;
        this.nNumber = number;
    }
    private IssuePrFragment generateForm(int i){
        IssuePrFragment fragment = new IssuePrFragment();
        String[] ghname = new String[] {"GHname/GHREPOname #1", "GHname/GHREPOname #2",
                "GHname/GHREPOname #3", "GHname/GHREPOname #4", "GHname/GHREPOname #5"};
        String[] issuelist = new String[] {"Better name", "Initial design", "Make all function, activities, fragments and many things",
                "Find bugs/ errors", "Finish project"};
        Bundle args = new Bundle();
        args.putString("ghname", ghname[i]);
        args.putString("listIP", issuelist[i]);
        args.putString("status", nStatus);
        fragment.setArguments(args);
        return fragment;
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PullRequestFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PullRequestFragment newInstance(String param1, String param2) {
        PullRequestFragment fragment = new PullRequestFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pull_request, container, false);
        for (int i = 0 ; i< nNumber; i++){
            Fragment ip = this.generateForm(i);
            getChildFragmentManager().beginTransaction().add(R.id.prTest, ip).commit();
        }
        return view;
    }
}