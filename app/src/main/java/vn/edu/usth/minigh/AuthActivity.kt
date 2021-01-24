package vn.edu.usth.minigh

import androidx.appcompat.app.AppCompatActivity

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager

class AuthActivity : AppCompatActivity(R.layout.activity_auth) {
    fun login(view: View) {
        SessionManager(getApplicationContext())
            .saveSession(User(42069, "meteora"))

        startActivity(Intent(getApplicationContext(),
                             ProfileActivity::class.java))
    }

    fun signUp(view: View) {
        val browser = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/join"))
        startActivity(browser)
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (getCurrentFocus() != null) {
            val imm = getSystemService(
                Context.INPUT_METHOD_SERVICE
            ) as InputMethodManager
            imm.hideSoftInputFromWindow(getCurrentFocus()!!.getWindowToken(), 0)
        }
        return super.dispatchTouchEvent(ev)
    }
}
