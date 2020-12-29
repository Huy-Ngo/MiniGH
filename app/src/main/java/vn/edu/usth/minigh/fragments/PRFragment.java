package vn.edu.usth.minigh;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.tabs.TabLayout;

public class PRFragment extends Fragment {

    public PRFragment() {
        super(R.layout.fragment_pr);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pr, container, false);

        PagerAdapter adapter = new HomePagerAdapter(getFragmentManager());
        ViewPager pager = (ViewPager) view.findViewById(R.id.pagerPR);
        pager.setAdapter(adapter);
        pager.setOffscreenPageLimit(2);

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabPR);
        tabLayout.setupWithViewPager(pager);

        return view;
    }

    public class HomePagerAdapter extends FragmentPagerAdapter {
        private String titles[] = new String[] {"Open", "Closed"};
        public HomePagerAdapter(FragmentManager fm){
            super(fm);
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public Fragment getItem(int page) {
            switch (page) {
                case 0: return new PROpenFragment();
                case 1: return new PRClosedFragment();
            }
            return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }
}
