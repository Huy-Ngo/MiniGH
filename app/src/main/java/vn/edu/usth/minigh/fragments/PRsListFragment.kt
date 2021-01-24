package vn.edu.usth.minigh.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import vn.edu.usth.minigh.R
import vn.edu.usth.minigh.api.Issue
import vn.edu.usth.minigh.api.Pulls
import vn.edu.usth.minigh.api.github
import java.util.ArrayList

class PRsListFragment : Fragment(R.layout.fragment_prs_list) {
    var frag: Fragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_prs_list, container, false)
        val sg = view.findViewById<View>(R.id.segmented2) as RadioGroup
        sg.check(R.id.button31)
        val repo_name = requireArguments().getString("repo name")!!

        lifecycleScope.launch {
            val getOpen = github.prRepo(repo_name, "open")
            val prOpen: ArrayList<Pulls> = getOpen
            val getClose = github.prRepo(repo_name,"closed")
            val prClose: ArrayList<Pulls> = getClose
            addFrag("pr", prOpen.count(), prOpen!!)
            sg.setOnCheckedChangeListener { group, checkedId ->
                when (checkedId) {
                    R.id.button32 -> addFrag("Closed", prClose.count(), prClose!!)
                    else -> addFrag("pr", prOpen.count(), prOpen!!)
                }
            }
        }
        return view
    }

    fun addFrag(txt: String?, number: Int, pr: ArrayList<Pulls>) {
        val fm = childFragmentManager
        frag = fm.findFragmentById(R.id.prsFragment)
        val ft = fm.beginTransaction()
        frag = PullRequestFragment.newInstance(txt, number, pr)
        ft.replace(R.id.prsFragment, frag as PullRequestFragment)
        ft.commit()
    }
}