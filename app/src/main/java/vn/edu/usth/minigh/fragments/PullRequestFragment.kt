package vn.edu.usth.minigh.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import vn.edu.usth.minigh.R
import vn.edu.usth.minigh.api.Pulls
import java.util.ArrayList

/**
 * A simple [Fragment] subclass.
 * Use the [PullRequestFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PullRequestFragment : Fragment {
    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: Int = 0
    private var mParam3: ArrayList<Pulls>? = null
    private var nStatus: String? = null
    private var nNumber = 0

    constructor() {
        // Required empty public constructor
    }

    constructor(status: String?, number: Int) {
        nStatus = status
        nNumber = number
    }

    private fun generateForm(i: Int): IssuePrFragment {
        val fragment = IssuePrFragment()
        val args = Bundle()
        val finalName: String? = mParam3?.elementAt(i)?.url?.substringAfterLast("https://api.github.com/repos/")
        val secondfinalName:String? = finalName?.substringBefore("/issue")
        args.putString("ghname", secondfinalName?.substringBefore("/pulls"))
        args.putString("listIP", mParam3?.elementAt(i)?.title)
        args.putString("status", nStatus)
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
            mParam3 = requireArguments().getParcelableArrayList("prData")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_pull_request, container, false)
        for (i in 0 until mParam2) {
            val ip: Fragment = generateForm(i)
            childFragmentManager.beginTransaction().add(R.id.prTest, ip).commit()
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
         * @return A new instance of fragment PullRequestFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String?, param2: Int?, param3: ArrayList<Pulls>): PullRequestFragment {
            val fragment = PullRequestFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            if (param2 != null) {
                args.putInt(ARG_PARAM2, param2)
            }
            args.putParcelableArrayList("prData", param3)
            fragment.arguments = args
            return fragment
        }
    }
}