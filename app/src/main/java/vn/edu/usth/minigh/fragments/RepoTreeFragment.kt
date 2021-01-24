package vn.edu.usth.minigh.fragments

import kotlinx.coroutines.launch

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner

import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.lifecycleScope

import vn.edu.usth.minigh.R
import vn.edu.usth.minigh.api.github
import vn.edu.usth.minigh.api.ShortBranch

class RepoTreeFragment :
    Fragment(R.layout.fragment_tree), OnItemSelectedListener {

    private lateinit var branches: List<String>

    override fun onNothingSelected(parent: AdapterView<*>?) { }
    override fun onItemSelected(parent: AdapterView<*>?, view: View?,
                                position: Int, id: Long) {
        childFragmentManager.commit {
            replace(R.id.tree, if (position == 0) {
                CodeMainFragment()
            } else {
                CodeBranch1Fragment()
            })
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val repo_name = requireArguments().getString("repo name")!!
        lifecycleScope.launch {
            branches = github.branches(repo_name).map { it.name }

            view.findViewById<Spinner>(R.id.branch_spinner).apply {
                onItemSelectedListener = this@RepoTreeFragment
                adapter = ArrayAdapter(
                    context!!,
                    android.R.layout.simple_spinner_item,
                    this@RepoTreeFragment.branches
                ).apply {
                    setDropDownViewResource(
                        android.R.layout.simple_spinner_dropdown_item)
                }
            }
        }
    }
}
