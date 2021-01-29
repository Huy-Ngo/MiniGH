package vn.edu.usth.minigh.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import vn.edu.usth.minigh.R
import vn.edu.usth.minigh.api.CommitItem
import vn.edu.usth.minigh.api.github

class CommitListFragment : Fragment {
    // TODO: Rename and change types of parameters
    private var mBranch: String? = null
    private var mNoFrag: Int = 0
    private var namerepo: String? = null
    private lateinit var listSHA: ArrayList<CommitItem>

    constructor() {
        // void
    }

    private fun commitViews(poponame: String, sha: String, msg: String, acc: String){
        childFragmentManager.commit{
            add(R.id.commitcontainer, CommitPreviewFragment().apply {
                arguments = bundleOf(
                        "repoName" to poponame,
                        "SHA" to sha,
                        "commits" to msg,
                        "authors" to acc
                )
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mBranch = requireArguments().getString(ARG_PARAM1)
            namerepo = requireArguments().getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_commit_list, container, false)
        lifecycleScope.launch {
            listSHA = github.commits(namerepo.toString(), mBranch.toString())
            mNoFrag = listSHA.size
            for (i in 0 until mNoFrag) {
                commitViews(namerepo.toString(), listSHA?.get(i)?.sha.toString() , listSHA[i].commit.message, listSHA[i].commit.author.name)
                Log.d("shaplz", listSHA?.get(i).sha.toString())
            }
        }
        return view
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private const val ARG_PARAM1 = "branch"
        private const val ARG_PARAM2 = "repoName"
        fun newInstance(param1: String?, param2: String?): CommitListFragment {
            val fragment = CommitListFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}