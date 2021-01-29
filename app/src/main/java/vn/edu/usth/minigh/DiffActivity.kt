package vn.edu.usth.minigh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import vn.edu.usth.minigh.api.CommitContent
import vn.edu.usth.minigh.api.github
import vn.edu.usth.minigh.fragments.FilesChangesFragment

class DiffActivity : AppCompatActivity() {
    private lateinit var list_files: List<CommitContent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_axx)
        val repo: String = intent.getStringExtra("repo name")!!
        val sha: String = intent.getStringExtra("SHA")!!
        findViewById<TextView>(R.id.main_text_bar).text = "Files changes"
        lifecycleScope.launch {
            list_files = listOf(github.commitchanges(repo, sha))
            for(i in 0 until list_files[0].files.size)
                diffViews(list_files[0].files[i].filename, list_files[0].files[i].patch)
        }
    }

    fun diffViews(filename: String, diff: String){
        supportFragmentManager.commit {
            add(R.id.diff_frame, FilesChangesFragment().apply {
                arguments = bundleOf(
                        "file name" to filename,
                        "load diff" to diff
                )
            })
        }
    }
}