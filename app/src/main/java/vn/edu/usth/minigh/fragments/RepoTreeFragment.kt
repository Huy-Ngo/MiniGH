package vn.edu.usth.minigh.fragments

import android.os.Bundle

import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace

import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner

import vn.edu.usth.minigh.R

class RepoTreeFragment :
    Fragment(R.layout.fragment_tree), OnItemSelectedListener {

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
        view.findViewById<Spinner>(R.id.branch_spinner).apply {
            onItemSelectedListener = this@RepoTreeFragment
            adapter = ArrayAdapter.createFromResource(
                context!!,
                R.array.branchDropdown,
                android.R.layout.simple_spinner_item
            ).apply {
                setDropDownViewResource(
                    android.R.layout.simple_spinner_dropdown_item)
            }
        }
    }
}
