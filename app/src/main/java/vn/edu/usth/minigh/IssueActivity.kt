package vn.edu.usth.minigh

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import vn.edu.usth.minigh.api.GitHub
import vn.edu.usth.minigh.api.Issue
import vn.edu.usth.minigh.api.github

class IssueActivity : BaseActivity(R.layout.activity_issue) {
    var frag: Fragment? = null
    var number: Int = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val txt_toolbar = findViewById<View>(R.id.main_text_bar) as TextView
        txt_toolbar.text = "Issues"
        val layout = findViewById<LinearLayout>(R.id.issues)
        layout.setBackgroundColor(applicationContext.resources.getColor(R.color.secondaryColor))
        val sg = findViewById<View>(R.id.segmented2) as RadioGroup
        sg.check(R.id.button21)

        val token = TokenManager(applicationContext).getToken()

        lifecycleScope.launch {
            Log.d("User_token", token.toString())

//            val user_token = github.current_user("Bearer $token")
//            val issue = github.issueUser("author:"+"Huy-Ngo"+" state:open is:issue")
            val issueOpen = github.issue_user("Bearer $token", "open")
            val igOpen: java.util.ArrayList<Issue> = issueOpen
//            val issueClose = github.issueUser("author:"+"Huy-Ngo"+" state:closed is:issue")
            val issueClose = github.issue_user("Bearer $token", "closed")
            val igClose: java.util.ArrayList<Issue> = issueClose
            addFrag("Open", igOpen.count(), igOpen!!)
            sg.setOnCheckedChangeListener { group, checkedId ->
                when (checkedId) {
                    R.id.button22 -> addFrag("Closed", igClose.count(), igClose!!)
                    else -> addFrag("Open", igOpen.count(), igOpen!!)
                }
            }
        }
    }

    fun addFrag(txt: String?, number: Int, ig:java.util.ArrayList<Issue>) {
        val fm = supportFragmentManager
        frag = fm.findFragmentById(R.id.issuesFragment)
        val ft = fm.beginTransaction()
        frag = IssueFragment.newInstance(txt, number, ig)
        ft.replace(R.id.issuesFragment, frag as IssueFragment)
        ft.commit()
    }

    fun goToDiscuss(view: View) {
        val intent = Intent(this, DiscussionActivity::class.java)
        //        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        val title = view.findViewById<View>(R.id.issuePrGhname) as TextView
        intent.putExtra("title", title.text as String)
        val description = view.findViewById<View>(R.id.issuePrContent) as TextView
        intent.putExtra("description", description.text as String)

        val urlComment = view.findViewById<View>(R.id.issuesPrFrame) as LinearLayout
        intent.putExtra("comment url", urlComment.tag as String)
        startActivity(intent)
    }
}
