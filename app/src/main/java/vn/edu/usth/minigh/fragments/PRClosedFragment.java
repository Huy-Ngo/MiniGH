package vn.edu.usth.minigh.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.edu.usth.minigh.R;

public class PRClosedFragment extends Fragment {

    public PRClosedFragment() {
        super(R.layout.fragment_pr_closed);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pr_closed, container, false);
    }
}
