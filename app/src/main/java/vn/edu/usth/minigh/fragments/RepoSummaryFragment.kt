package vn.edu.usth.minigh.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView

import androidx.fragment.app.Fragment

import io.noties.markwon.Markwon

import vn.edu.usth.minigh.R

class RepoSummaryFragment(full_name: String?) : Fragment(R.layout.fragment_repo_summary) {
    val repo_owner: String
        get() = full_name.split("/")[0]
    val repo_name: String
        get() = full_name.split("/")[1]
    private val README: String = "# Heading 1\n" +
        "## Heading 2\n\n" +
        "Look, this is *emphasized*.\n\n" +
        "And here's some **bold**.\n\n" +
        "Here are unordered list items:\n\n" +
        "* Foo\n* Bar\n* Baz\n\n" +
        "Here are ordered list items:\n\n" +
        "1. Uno\n2. Two\n3. Tres\n4. Four\n"

    override fun onViewCreated(view: View, savedInstanceState: Bundle) {
        val name = view.findViewById<TextView>(R.id.repo_name)
        name.text = this.repo_name
        val readme = view.findViewById<TextView>(R.id.readme)
        Markwon.create(getContext()!!).setMarkdown(readme, README)
    }
}
