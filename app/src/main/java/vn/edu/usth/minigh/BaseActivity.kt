// Base activity with drawer
package vn.edu.usth.minigh

import kotlinx.coroutines.launch

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.TextView

import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.lifecycleScope

import coil.load

import vn.edu.usth.minigh.api.github

abstract class BaseActivity(layoutID: Int) : AppCompatActivity(layoutID) {
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        drawerLayout = findViewById(R.id.drawer_layout)

        val avatar = findViewById<ImageView>(R.id.avatar)
        val username = findViewById<TextView>(R.id.nav_username)
        val follower = findViewById<TextView>(R.id.follower_total)
        val following = findViewById<TextView>(R.id.following_total)

        lifecycleScope.launch {
            val user = github.user("Huy-Ngo") // TODO: Replace this with login
            username.text = "Huy-Ngo"
            follower.text = Html.fromHtml("<b>${user.followers}</b> Follower")
            following.text = (Html.fromHtml("<b>${user.following}</b> Following"))
            avatar.load(user.avatar_url)
        }
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
