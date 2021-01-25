package vn.edu.usth.minigh

import kotlinx.coroutines.launch

import android.os.Bundle
import android.content.Intent
import android.util.Base64
import android.view.View
import android.widget.TextView

import androidx.lifecycle.lifecycleScope

import com.pddstudio.highlightjs.HighlightJsView

import vn.edu.usth.minigh.api.github

class CodeActivity : BaseActivity(R.layout.activity_code) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val extras = intent.extras!!
        val repo_name = extras.getString("repo name")!!
        val branch = extras.getString("branch")!!
        val path = extras.getString("path")!!
        findViewById<TextView>(R.id.main_text_bar).text = path

        lifecycleScope.launch {
            val file = github.content(repo_name, path, branch)
            findViewById<HighlightJsView>(R.id.code)
                .setSource(String(Base64.decode(file.content, Base64.DEFAULT)))
        }
    }
}
