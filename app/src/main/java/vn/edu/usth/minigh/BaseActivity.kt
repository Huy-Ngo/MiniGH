// Base activity with drawer
package vn.edu.usth.minigh

import android.app.AlertDialog
import android.content.Context
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
import kotlinx.coroutines.launch
import vn.edu.usth.minigh.api.github
import java.net.CookieManager

abstract class BaseActivity(layoutID: Int) : AppCompatActivity(layoutID) {
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        drawerLayout = findViewById(R.id.drawer_layout)
        val token = TokenManager(applicationContext).getToken()

        val avatar = findViewById<ImageView>(R.id.avatar)
        val username = findViewById<TextView>(R.id.nav_username)
        val follower = findViewById<TextView>(R.id.follower_total)
        val following = findViewById<TextView>(R.id.following_total)

        lifecycleScope.launch {
            val user = github.current_user("Bearer $token") // TODO: Replace this with login
            username.text = user.login
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
            startActivity(Intent(this, aclass))
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
                SessionManager(applicationContext).removeSession()
                TokenManager(applicationContext).removeToken()
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
