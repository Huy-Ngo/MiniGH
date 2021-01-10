// Base activity with drawer
package vn.edu.usth.minigh

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.TextView

import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout

abstract class BaseActivity(layoutID: Int) : AppCompatActivity(layoutID) {
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        drawerLayout = findViewById(R.id.drawer_layout)

        val follower = findViewById<TextView>(R.id.follower_total)
        follower.setText(Html.fromHtml("<b>"+5+"</b> Follower"))
        val following = findViewById<TextView>(R.id.following_total)
        following.setText((Html.fromHtml("<b>"+5+"</b> Following")))
    }

    override fun onPause() {
        super.onPause()
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        }
    }

    fun redirectActivity(aclass: Class<*>) {
        if (this::class.java == aclass) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            val intent = Intent(this, aclass)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }

    fun clickProfile(view: View) = redirectActivity(ProfileActivity::class.java)
    fun clickIssues(view: View) = redirectActivity(IssueActivity::class.java)
    fun clickPRs(view: View) = redirectActivity(PRActivity::class.java)
    fun openDrawer(view: View) = drawerLayout.openDrawer(GravityCompat.START)

    fun logout(view: View) {
        // TODO: define layout statically
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Log out")
        builder.setMessage("Are you sure you want to log out?")
        builder.setPositiveButton(
            "Yes", DialogInterface.OnClickListener { _, _ ->
                SessionManager(getApplicationContext()).removeSession()
                redirectActivity(AuthActivity::class.java)
            }
        )
        builder.setNegativeButton(
            "No", DialogInterface.OnClickListener { dialog, _ ->
                dialog.dismiss()
            }
        )
        builder.show()
    }
}
