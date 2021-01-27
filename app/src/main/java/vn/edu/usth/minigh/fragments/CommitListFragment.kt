package vn.edu.usth.minigh.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import vn.edu.usth.minigh.R
import vn.edu.usth.minigh.api.CommitItem

class CommitListFragment : Fragment {
    // TODO: Rename and change types of parameters
    private var mBranch: String? = null
    private var mNoFrag: Int = 0
    private var mSHA: ArrayList<CommitItem>? = null

    constructor() {}

    private fun generateForm(i: Int): CommitPreviewFragment {
        val fragment = CommitPreviewFragment()
        val args = Bundle()
//        Log.d("SHA LOG", mSHA.toString())
        args.putString("commits", mSHA?.get(i)?.commit?.message)
        args.putString("authors", mSHA?.get(i)?.commit?.author?.name)
//        Log.d("getcommits", mSHA?.get(i)?.commit?.message + " bruhbruh " + mSHA?.get(i)?.commit?.author?.name)
        fragment.arguments = args
        return fragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mBranch = requireArguments().getString(ARG_PARAM1)
            mNoFrag = requireArguments().getInt(ARG_PARAM2)
            mSHA = requireArguments().getParcelableArrayList("sha")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_commit_list, container, false)
        for (i in 0 until mNoFrag) {
            val commititem: Fragment = generateForm(i)
            childFragmentManager.beginTransaction().add(R.id.commitcontainer, commititem).commit()
        }
        return view
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private const val ARG_PARAM1 = "branch"
        private const val ARG_PARAM2 = "numoffrag"
        private const val ARG_PARAM3 = "sha"
        fun newInstance(param1: String?, param2: String?, param3: ArrayList<CommitItem>): CommitListFragment {
            val fragment = CommitListFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            args.putParcelableArrayList(ARG_PARAM3, param3)
            fragment.arguments = args
            return fragment
        }
    }
}