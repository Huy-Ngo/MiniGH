package vn.edu.usth.minigh

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

import android.content.Intent
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope

class SplashActivity : AppCompatActivity(R.layout.activity_splash) {
    override fun onStart() {
        super.onStart()

        lifecycleScope.launch {
            delay(2000)
            val manager = SessionManager(getApplicationContext())
            val intent = if (manager.getSession() == -1) {
                Intent(this@SplashActivity, AuthActivity::class.java)
            } else {
                Intent(this@SplashActivity, ProfileActivity::class.java)
            }
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }
}
