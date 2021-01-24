package vn.edu.usth.minigh

import kotlinx.coroutines.launch

import android.os.Bundle
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.TextView

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import androidx.lifecycle.lifecycleScope

import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

import vn.edu.usth.minigh.fragments.IssuesListFragment
import vn.edu.usth.minigh.fragments.PRsListFragment
import vn.edu.usth.minigh.fragments.RepoLogFragment
import vn.edu.usth.minigh.fragments.RepoSummaryFragment
import vn.edu.usth.minigh.fragments.RepoTreeFragment
import vn.edu.usth.minigh.api.github
import vn.edu.usth.minigh.api.MinRepo

val TAB_NAMES = arrayOf("Summary", "Tree", "Log", "Issues", "PRs")

class RepoActivity : BaseActivity(R.layout.activity_repo) {
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repo_name = intent.extras!!.getString("repo name")
        val txtToolbar = findViewById<TextView>(R.id.main_text_bar)
        txtToolbar.setText("Repository")

        viewPager = findViewById(R.id.pager)
        viewPager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int = 5
            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    1 -> RepoTreeFragment()
                    2 -> RepoLogFragment()
                    3 -> IssuesListFragment()
                    4 -> PRsListFragment()
                    else -> RepoSummaryFragment()
                }.apply {
                    arguments = bundleOf("repo name" to repo_name)
                }
            }
        }
        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = TAB_NAMES[position]
        }.attach()
    }

    override fun onBackPressed() {
        if (viewPager.getCurrentItem() == 0)
            super.onBackPressed()
        else
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1)
    }

    fun goTo(aclass: Class<*>) = startActivity(Intent(this, aclass))
    fun goToDiscuss(view: View) = goTo(DiscussionActivity::class.java)
}
