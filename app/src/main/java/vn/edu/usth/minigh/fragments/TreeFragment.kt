package vn.edu.usth.minigh.fragments

import kotlinx.coroutines.launch

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.LinearLayout
import android.widget.LinearLayout.LayoutParams
import android.widget.LinearLayout.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout.LayoutParams.WRAP_CONTENT
import android.widget.TextView

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope

import info.androidhive.fontawesome.FontCache
import info.androidhive.fontawesome.FontTextView

import vn.edu.usth.minigh.CodeActivity
import vn.edu.usth.minigh.R
import vn.edu.usth.minigh.api.github

class TreeNodeFragment : Fragment(R.layout.fragment_tree_node) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val args = requireArguments()
        val name = args.getString("name")!!
        val isDir = args.getString("type") == "dir"
        view.findViewById<TextView>(R.id.content_name).text = name

        val contentType = view.findViewById<FontTextView>(R.id.content_type)
        // TODO: handle symlinks and git submodules
        if (isDir) {
            contentType.text = getString(R.string.fa_folder)
            contentType.setTypeface(FontCache.get(
                contentType.context, "fa-solid-900.ttf"))
        } else {
            contentType.text = getString(R.string.fa_file)
        }

        view.setOnClickListener {
            val branch = args.getString("branch")!!
            val dirName = args.getString("path")!!
            val path = if (dirName == "") {
                name
            } else if (name == "..") {
                // https://github.com/square/retrofit/issues/3080
                val i = dirName.indexOfLast { it == '/' }
                if (i > 0) dirName.substring(0, i) else ""
            } else {
                "$dirName/$name"
            }
            if (isDir) {
                (parentFragment!!.parentFragment!! as RepoTreeFragment)
                    .openFolder(path, branch)
            } else {
                val intent = Intent(activity, CodeActivity::class.java)
                intent.putExtra("repo name", args.getString("repo name")!!)
                intent.putExtra("branch", branch)
                intent.putExtra("path", path)
                activity!!.startActivity(intent)
            }
        }
    }
}

class TreeFragment : Fragment(R.layout.fragment_tree) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val args = requireArguments()
        val repo_name = args.getString("repo name")!!
        val branch = args.getString("branch")!!
        val path = args.getString("path")!!
        lifecycleScope.launch {
            childFragmentManager.commit {
                if (path != "") {
                    add<TreeNodeFragment>(R.id.node_list, args = bundleOf(
                        "repo name" to repo_name, "branch" to branch,
                        "path" to path, "name" to "..", "type" to "dir"))
                }
                github.contents(repo_name, path, branch).sortedBy { it.type }.map {
                    add<TreeNodeFragment>(R.id.node_list, args = bundleOf(
                        "repo name" to repo_name, "branch" to branch,
                        "path" to path, "name" to it.name, "type" to it.type))
                }
                // FIXME: this interferes with the one on summary tab
                (view as LinearLayout).layoutParams =
                    LayoutParams(MATCH_PARENT, WRAP_CONTENT)
            }
        }
    }
}
