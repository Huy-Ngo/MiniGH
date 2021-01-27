package vn.edu.usth.minigh.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import vn.edu.usth.minigh.R

class CommitPreviewFragment : Fragment() {
    private var mCommits: String? = null
    private var mAuthors: String? = null
    private var mBranches: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mCommits = requireArguments().getString(ARG_PARAM1)
            mAuthors = requireArguments().getString(ARG_PARAM2)
            mBranches = requireArguments().getString(ARG_PARAM3)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_commit_preview, container, false)
        val log = view.findViewById<TextView>(R.id.commitlog)
        log.text = mCommits
        val ath = view.findViewById<TextView>(R.id.commitauthor)
        ath.text = mAuthors
        return view
    }

    companion object {
        private const val ARG_PARAM1 = "commits"
        private const val ARG_PARAM2 = "authors"
        private const val ARG_PARAM3 = "branches"
        fun newInstance(param1: String?, param2: String?): CommitPreviewFragment {
            val fragment = CommitPreviewFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, "commits")
            args.putString(ARG_PARAM2, "authors")
            args.putString(ARG_PARAM3, "branches")
            fragment.arguments = args
            return fragment
        }
    }
}