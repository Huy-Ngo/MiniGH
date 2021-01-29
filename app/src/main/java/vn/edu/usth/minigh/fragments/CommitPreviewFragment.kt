package vn.edu.usth.minigh.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import vn.edu.usth.minigh.DiffActivity
import vn.edu.usth.minigh.R

class CommitPreviewFragment : Fragment() {
    private var mRepoName: String? = null
    private var mSHA: String? = null
    private var mCommits: String? = null
    private var mAuthors: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mRepoName = requireArguments().getString(ARG_PARAM1)
            mSHA = requireArguments().getString(ARG_PARAM2)
            mCommits = requireArguments().getString(ARG_PARAM3)
            mAuthors = requireArguments().getString(ARG_PARAM4)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_commit_preview, container, false)
        view.findViewById<TextView>(R.id.commitlog).apply { text = mCommits }
        view.findViewById<TextView>(R.id.commitauthor).apply { text = mAuthors }
        view.setOnClickListener{
            startActivity(
                Intent(requireContext(), DiffActivity::class.java).apply {
                    putExtra("repo name", mRepoName)
                    putExtra("SHA", mSHA)
                }
            )
        }
        return view
    }

    companion object {
        private const val ARG_PARAM1 = "repoName"
        private const val ARG_PARAM2 = "SHA"
        private const val ARG_PARAM3 = "commits"
        private const val ARG_PARAM4 = "authors"
        fun newInstance(param1: String?, param2: String?): CommitPreviewFragment {
            val fragment = CommitPreviewFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, "repoName")
            args.putString(ARG_PARAM2, "SHA")
            args.putString(ARG_PARAM3, "commits")
            args.putString(ARG_PARAM4, "authors")
            fragment.arguments = args
            return fragment
        }
    }
}