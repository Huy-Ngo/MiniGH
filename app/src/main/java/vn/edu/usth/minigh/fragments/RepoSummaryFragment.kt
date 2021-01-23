package vn.edu.usth.minigh.fragments

import kotlinx.coroutines.launch

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.widget.LinearLayout
import android.widget.TextView

import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope

import io.noties.markwon.Markwon

import vn.edu.usth.minigh.R
import vn.edu.usth.minigh.api.github
import vn.edu.usth.minigh.api.MinRepo

class RepoSummaryFragment(val repo_name: String) : Fragment(R.layout.fragment_repo_summary) {
    private val README: String = "# Heading 1\n" +
        "## Heading 2\n\n" +
        "Look, this is *emphasized*.\n\n" +
        "And here's some **bold**.\n\n" +
        "Here are unordered list items:\n\n" +
        "* Foo\n* Bar\n* Baz\n\n" +
        "Here are ordered list items:\n\n" +
        "1. Uno\n2. Two\n3. Tres\n4. Four\n"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val name = view.findViewById<TextView>(R.id.repo_name)
        val parentRepo = view.findViewById<LinearLayout>(R.id.parent_repo)
        val parentRepoName = view.findViewById<TextView>(R.id.parent_repo_name)

        lifecycleScope.launch {
            val repo = github.repo(repo_name)
            name.text = repo.full_name
            if (repo.fork) {
                parentRepoName.text = repo.full_name
            } else {
                parentRepo.visibility = GONE
            }
        }
        val readme = view.findViewById<TextView>(R.id.readme)
        Markwon.create(getContext()!!).setMarkdown(readme, README)
    }
}
