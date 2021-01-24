package vn.edu.usth.minigh.fragments

import kotlinx.coroutines.launch

import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.LinearLayout.LayoutParams
import android.widget.LinearLayout.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout.LayoutParams.WRAP_CONTENT

import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope

import io.noties.markwon.Markwon

import vn.edu.usth.minigh.R
import vn.edu.usth.minigh.api.github
import vn.edu.usth.minigh.api.MinRepo

class RepoSummaryFragment() : Fragment(R.layout.fragment_repo_summary) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val name = view.findViewById<TextView>(R.id.repo_name)
        val parentRepo = view.findViewById<LinearLayout>(R.id.parent_repo)
        val parentRepoName = view.findViewById<TextView>(R.id.parent_repo_name)
        val description = view.findViewById<TextView>(R.id.repo_description)
        val starCount = view.findViewById<TextView>(R.id.star_count)
        val watchCount = view.findViewById<TextView>(R.id.watch_count)
        val forkCount = view.findViewById<TextView>(R.id.fork_count)
        val readmeName = view.findViewById<TextView>(R.id.readme_fname)
        val readmeView = view.findViewById<TextView>(R.id.readme)
        val repo_name = requireArguments().getString("repo name")!!

        lifecycleScope.launch {
            val repo = github.repo(repo_name)
            name.text = repo.full_name
            if (repo.fork) {
                parentRepoName.text = repo.parent!!.full_name
            } else {
                parentRepo.visibility = GONE
            }
            repo.description?.also {
                description.text = it
            } ?: run {
                description.visibility = GONE
            }
            starCount.text = "${repo.stargazers_count} Star" 
            watchCount.text = "${repo.watchers_count} Watch"
            forkCount.text = "${repo.forks_count} Fork"
            try {
                val readme = github.readme(repo_name)
                readmeName.text = readme.name
                if (readme.encoding == "base64") {
                    val README = String(Base64.decode(readme.content, Base64.DEFAULT))
                    Markwon.create(getContext()!!).setMarkdown(readmeView, README)
                } else {
                    readmeView.text = "Sorry, this file's encoding is not supported."
                }
            } catch (e: Exception) {
                readmeView.text = "This repo doesn't have a README"
            }
            val params = LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
            readmeView.layoutParams = params
        }
    }
}
