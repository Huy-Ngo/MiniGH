package vn.edu.usth.minigh;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import vn.edu.usth.minigh.RepoSummaryFragment;

class RepoPagerAdapter extends FragmentStateAdapter {
    public RepoPagerAdapter(FragmentActivity fa) { super(fa); }

    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0: return new RepoSummaryFragment();
            case 1: return new RepoSummaryFragment();
            case 2: return new RepoSummaryFragment();
            case 3: return new RepoSummaryFragment();
        }
        return null;
    }

    @Override
    public int getItemCount() { return 4; }
}
