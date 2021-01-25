/* Activity for user profile
 * Copyright (C) 2020  Ngô Ngọc Đức Huy
 * This file is part of MiniGH.
 *
 * MiniGH is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MiniGH is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with MiniGH.  If not, see <https://www.gnu.org/licenses/>.
 */
package vn.edu.usth.minigh

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import coil.load
import kotlinx.coroutines.launch
import vn.edu.usth.minigh.api.github
import vn.edu.usth.minigh.fragments.RepoListFragment

class ProfileActivity : BaseActivity(R.layout.activity_profile) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val token = TokenManager(applicationContext).getToken()
        Toast.makeText(applicationContext, token, Toast.LENGTH_SHORT).show()
        if (token == null || token == "") {
            val logout = Intent(this, AuthActivity::class.java)
            startActivity(logout)
        }


        lifecycleScope.launch {
            val user = github.current_user("Bearer $token")
            val login = intent.getStringExtra("login") ?: user.login
            val drawerTab = findViewById<LinearLayout>(R.id.profile)
            drawerTab.setBackgroundColor(getColor(R.color.secondaryColor))

            findViewById<TextView>(R.id.main_text_bar).text = login
            findViewById<TextView>(R.id.user_login).text = login
            if (savedInstanceState == null) {
                val args = bundleOf("owner" to login)
                supportFragmentManager.commit {
                    setReorderingAllowed(true)
                    add<RepoListFragment>(R.id.repo_list, args = args)
                }
            }
            user.bio?.also {
                findViewById<TextView>(R.id.user_bio).text = it
            } ?: run {
                findViewById<View>(R.id.user_bio_container).visibility = GONE
            }
            user.email?.also {
                findViewById<TextView>(R.id.user_email).text = it
            } ?: run {
                findViewById<View>(R.id.user_email_container).visibility = GONE
            }

            findViewById<TextView>(R.id.user_name).text = user.name
            findViewById<TextView>(R.id.user_followers).text =
                "${user.followers} followers"
            findViewById<TextView>(R.id.user_following).text =
                "${user.following} following"
            findViewById<ImageView>(R.id.user_avatar).load(user.avatar_url)
            findViewById<TextView>(R.id.repo_header).text = "${user.public_repos} Repositories"
        }
    }

    fun goToRepo(view: View) {
        val repoName = (view.findViewById<TextView>(R.id.repo_name_text)).text as String
        val intent = Intent(this, RepoActivity::class.java)
        intent.putExtra("repo name", repoName)
        startActivity(intent)
    }
}
