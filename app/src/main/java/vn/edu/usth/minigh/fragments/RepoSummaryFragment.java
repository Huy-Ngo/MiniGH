package vn.edu.usth.minigh.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import io.noties.markwon.Markwon;

import vn.edu.usth.minigh.R;

public class RepoSummaryFragment extends Fragment {
    private static final String README = "# Heading 1\n"
        + "## Heading 2\n\n"
        + "Look, this is *emphasized*.\n\n"
        + "And here's some **bold**.\n\n"
        + "Here are unordered list items:\n\n"
        + "* Foo\n* Bar\n* Baz\n\n"
        + "Here are ordered list items:\n\n"
        + "1. Uno\n2. Two\n3. Tres\n4. Four\n";

    public RepoSummaryFragment() {
        super(R.layout.fragment_repo_summary);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView readme = view.findViewById(R.id.readme);
        Markwon.create(getActivity()).setMarkdown(readme, README);
    }
}
