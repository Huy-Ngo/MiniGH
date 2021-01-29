package vn.edu.usth.minigh.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import vn.edu.usth.minigh.R
import vn.edu.usth.minigh.api.github

class RepoLogFragment : Fragment(R.layout.fragment_repo_log) {
    private lateinit var repo_name: String
    private lateinit var branches: List<String>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_repo_log, container, false)
        repo_name = requireArguments().getString("repo name")!!
        lifecycleScope.launch {
            val spinner = view.findViewById<View>(R.id.branchSpinner) as Spinner
            branches = github.branches(repo_name).map { it.name }
            val adapter = ArrayAdapter(requireContext(),
                    android.R.layout.simple_spinner_item, branches)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
            spinner.onItemSelectedListener = object : OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
                    switchFrag(branches[position], repo_name)
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
        }
        return view
    }

    fun switchFrag(branch: String?, reponame: String?) {
        childFragmentManager.commit{
            replace(R.id.commitContent, CommitListFragment().apply {
                arguments = bundleOf(
                        "branch" to branch,
                        "repoName" to reponame
                )
            })
        }
    }
}