package vn.edu.usth.minigh;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayoutMediator;

import vn.edu.usth.minigh.BaseActivity;
import vn.edu.usth.minigh.RepoPagerAdapter;

public class RepoActivity extends BaseActivity {
    private static final String[] TAB_NAMES = {
        "miniGH", "Code", "Issues", "Commit", "PRs"};

    private ViewPager2 viewPager;
    private FragmentStateAdapter pagerAdapter;

    public RepoActivity() {
        super(R.layout.activity_repo);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView txt_toolbar = (TextView) findViewById(R.id.main_text_bar);
        txt_toolbar.setText("Repository");

//        LinearLayout layout = findViewById(R.id.repo);
//        layout.setBackgroundColor(
//            getApplicationContext().getResources()
//            .getColor(R.color.secondaryColor));

        viewPager = findViewById(R.id.pager);
        pagerAdapter = new RepoPagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);
        // TODO: make this offscreen page limit smaller (fixing issues and PRs not showing properly)
        viewPager.setOffscreenPageLimit(100);
        new TabLayoutMediator(
            findViewById(R.id.tab_layout), viewPager,
            (tab, position) -> tab.setText(TAB_NAMES[position])).attach();
    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0)
            super.onBackPressed();
        else
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
    }
}
