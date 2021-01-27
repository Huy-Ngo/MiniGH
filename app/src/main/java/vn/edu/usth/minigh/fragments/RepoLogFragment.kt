package vn.edu.usth.minigh.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import vn.edu.usth.minigh.R
import vn.edu.usth.minigh.api.CommitItem
import vn.edu.usth.minigh.api.github
import kotlin.collections.ArrayList

class RepoLogFragment : Fragment(R.layout.fragment_repo_log) {
    private lateinit var repo_name: String
    private lateinit var branches: List<String>
    private lateinit var list_sha: List<ArrayList<CommitItem>>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_repo_log, container, false)
        repo_name = requireArguments().getString("repo name")!!
        lifecycleScope.launch {
            val spinner = view.findViewById<View>(R.id.branchSpinner) as Spinner
            branches = github.branches(repo_name).map { it.name }
//            Log.d("Branches no koto", branches.toString())
            list_sha = branches.map { github.commits(repo_name, it) }
            Log.d("shanokoto", list_sha.toString())
            val adapter = ArrayAdapter(requireContext(),
                    android.R.layout.simple_spinner_item, branches)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
            spinner.onItemSelectedListener = object : OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
                    addFrag(branches[position], list_sha[position].size, list_sha[position])
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
        }
        return view
    }

    fun addFrag(branch: String?, number: Int?, sha: ArrayList<CommitItem>) {
        val fm = childFragmentManager
        // val frag = fm.findFragmentById(R.id.commitContent)
        val ft = fm.beginTransaction()
        val frag = CommitListFragment()
        var args = bundleOf("branch" to branch,
                "numoffrag" to number,
                "sha" to sha)
        frag.arguments = args
        ft.replace(R.id.commitContent, frag)
        ft.commit()
    }
}