package vn.edu.usth.minigh.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import vn.edu.usth.minigh.R

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FilesChangesFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var filename: String? = null
    private var filediff: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            filename = it.getString("file name")
            filediff = it.getString("load diff")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_files_changes, container, false)
        view.findViewById<TextView>(R.id.filename).text = filename
        view.findViewById<TextView>(R.id.filechange).text = filediff
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                FilesChangesFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}