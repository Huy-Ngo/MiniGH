package vn.edu.usth.minigh

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import vn.edu.usth.minigh.api.Issue
import vn.edu.usth.minigh.fragments.IssuePrFragment
import java.util.ArrayList

/**
 * A simple [Fragment] subclass.
 * Use the [IssueFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class IssueFragment : Fragment {
    private var nStatus: String? = null
    private var nNumber = 0

    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2 = 0
    private var mParam3:ArrayList<Issue>? = null
    constructor() {
        // Required empty public constructor
    }

    private fun generateForm(i: Int): IssuePrFragment {

        val fragment = IssuePrFragment()
        val args = Bundle()
        args.putString("ghname", mParam3?.elementAt(i)?.repository_url?.substringAfterLast("https://api.github.com/repos/"))
        args.putString("listIP", mParam3?.elementAt(i)?.title)
        args.putString("status", mParam1)
        mParam3?.elementAt(i)?.number?.let { args.putInt("number", it) }
        args.putString("comment url", mParam3?.elementAt(i)?.comments_url)
        fragment.arguments = args
        return fragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = requireArguments().getString(ARG_PARAM1)
            mParam2 = requireArguments().getInt(ARG_PARAM2)
            mParam3 = requireArguments().getParcelableArrayList("data")
        }
        Log.d("check issue data", mParam3.toString())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_issue, container, false)

        for (i in 0 until mParam2) {
            val ip: Fragment = generateForm(i)
            childFragmentManager.beginTransaction().add(R.id.issueTest, ip).commit()
        }
        return view
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment IssueFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String?, param2: Int, param3: ArrayList<Issue>): IssueFragment {
            val fragment = IssueFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putInt(ARG_PARAM2, param2)
            args.putParcelableArrayList("data", param3)
            fragment.arguments = args
            return fragment
        }
    }
}