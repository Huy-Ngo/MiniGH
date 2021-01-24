package vn.edu.usth.minigh.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import vn.edu.usth.minigh.IssueFragment
import vn.edu.usth.minigh.R
import vn.edu.usth.minigh.api.Issue
import vn.edu.usth.minigh.api.github
import java.util.ArrayList

class IssuesListFragment : Fragment(R.layout.fragment_issues_list) {
    var frag: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_issues_list, container, false)
        val sg = view.findViewById<View>(R.id.segmented2) as RadioGroup
        sg.check(R.id.button21)

        val repo_name = requireArguments().getString("repo name")!!

        lifecycleScope.launch {
            val issue = github.issueRepo(repo_name, "open")
            val igOpen:ArrayList<Issue> = issue
            val issueClose = github.issueRepo(repo_name,"closed")
            val igClose:ArrayList<Issue> = issueClose
            addFrag("Open", igOpen.count(), igOpen!!)
            sg.setOnCheckedChangeListener { group, checkedId ->
                when (checkedId) {
                    R.id.button22 -> addFrag("Closed", igClose.count(), igClose!!)
                    else -> addFrag("Open", igOpen.count(), igOpen!!)
                }
            }
        }
        return view
    }

    fun addFrag(txt: String?, number: Int, ig:ArrayList<Issue>) {
        val fm = childFragmentManager
        frag = fm.findFragmentById(R.id.issuesFragment)
        val ft = fm.beginTransaction()
        frag = IssueFragment.newInstance(txt, number, ig)
        ft.replace(R.id.issuesFragment, frag as IssueFragment)
        ft.commit()
    }
}