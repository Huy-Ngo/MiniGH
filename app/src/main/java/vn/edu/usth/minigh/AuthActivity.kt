package vn.edu.usth.minigh

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.text.format.Time
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import org.json.JSONTokener
import vn.edu.usth.minigh.api.GithubConstants
import java.io.OutputStreamWriter
import java.net.URL
import java.util.concurrent.TimeUnit
import javax.net.ssl.HttpsURLConnection
import kotlin.String as String1

class AuthActivity : AppCompatActivity(R.layout.activity_auth) {
    lateinit var githubAuthURLFull: String1
    lateinit var githubdialog: Dialog

    var id = ""
    var displayName = ""

    fun login(view: View) {
        val state = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis())
        githubAuthURLFull =
                GithubConstants.AUTH_URL + "?client_id=" + GithubConstants.CLIENT_ID + "&scope=" + GithubConstants.SCOPE + "&redirect_uri=" + GithubConstants.REDIRECT_URI + "&state=" + state

        setupGithubWebviewDialog(githubAuthURLFull)
    }

    // Show Github login page in a dialog
    @SuppressLint("SetJavaScriptEnabled")
    fun setupGithubWebviewDialog(url: String1) {
        githubdialog = Dialog(this)
        val webView = WebView(this)
        webView.isVerticalScrollBarEnabled = false
        webView.isHorizontalScrollBarEnabled = false
        webView.webViewClient = GithubWebViewClient()
        webView.settings.javaScriptEnabled = true
        webView.loadUrl(url)
        githubdialog.setContentView(webView)
        githubdialog.show()
}

    // A client to know about WebView navigations
    // For API 21 and above
    @Suppress("OverridingDeprecatedMember")
    inner class GithubWebViewClient : WebViewClient() {
        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
        ): Boolean {
            if (request!!.url.toString().startsWith(GithubConstants.REDIRECT_URI)) {
                handleUrl(request.url.toString())

                // Close the dialog after getting the authorization code
                if (request.url.toString().contains("code=")) {
                    githubdialog.dismiss()
                }
                return true
            }
            return false
        }

        // For API 19 and below
        override fun shouldOverrideUrlLoading(view: WebView, url: String1): Boolean {
            if (url.startsWith(GithubConstants.REDIRECT_URI)) {
                handleUrl(url)

                // Close the dialog after getting the authorization code
                if (url.contains("?code=")) {
                    githubdialog.dismiss()
                }
                return true
            }
            return false
        }

        // Check webview url for access token code or error
        private fun handleUrl(url: String1) {
            val uri = Uri.parse(url)
            if (url.contains("code")) {
                val githubCode = uri.getQueryParameter("code") ?: ""
                requestForAccessToken(githubCode)
            }
        }
    }


    fun requestForAccessToken(code: String1) {
        val grantType = "authorization_code"

        val postParams =
                "grant_type=" + grantType + "&code=" + code + "&redirect_uri=" + GithubConstants.REDIRECT_URI + "&client_id=" + GithubConstants.CLIENT_ID + "&client_secret=" + GithubConstants.CLIENT_SECRET
        GlobalScope.launch(Dispatchers.Default) {
            val url = URL(GithubConstants.TOKEN_URL)
            val httpsURLConnection =
                    withContext(Dispatchers.IO) { url.openConnection() as HttpsURLConnection }
            httpsURLConnection.requestMethod = "POST"
            httpsURLConnection.setRequestProperty(
                    "Accept",
                    "application/json"
            )
            httpsURLConnection.doInput = true
            httpsURLConnection.doOutput = true
            val outputStreamWriter = OutputStreamWriter(httpsURLConnection.outputStream)
            withContext(Dispatchers.IO) {
                outputStreamWriter.write(postParams)
                outputStreamWriter.flush()
            }
            val response = httpsURLConnection.inputStream.bufferedReader()
                    .use { it.readText() }  // defaults to UTF-8
            withContext(Dispatchers.Main) {
                val jsonObject = JSONTokener(response).nextValue() as JSONObject

                val accessToken = jsonObject.getString("access_token") //The access token

                // Get user's id, first name, last name, profile pic url
                fetchGithubUserProfile(accessToken)
            }
        }
    }

    fun fetchGithubUserProfile(token: kotlin.String) {
        GlobalScope.launch(Dispatchers.Default) {
            val tokenURLFull =
                    "https://api.github.com/user"

            val url = URL(tokenURLFull)
            val httpsURLConnection =
                    withContext(Dispatchers.IO) { url.openConnection() as HttpsURLConnection }
            httpsURLConnection.requestMethod = "GET"
            httpsURLConnection.setRequestProperty("Authorization", "Bearer $token")
            httpsURLConnection.doInput = true
            httpsURLConnection.doOutput = false
            val response = httpsURLConnection.inputStream.bufferedReader()
                    .use { it.readText() }  // defaults to UTF-8
            val jsonObject = JSONTokener(response).nextValue() as JSONObject
            Log.i("GitHub Access Token: ", token)
            var accessToken = token

            // GitHub Id
            val githubId = jsonObject.getInt("id")
            Log.i("GitHub Id: ", githubId.toString())
            id = githubId.toString()

            // GitHub Display Name
            val githubDisplayName = jsonObject.getString("login")
            Log.i("GitHub Display Name: ", githubDisplayName)
            displayName = githubDisplayName

            // Save Token
            TokenManager(getApplicationContext()).saveToken(accessToken)

            // Save session
            SessionManager(getApplicationContext()).saveSession(
                User(id.toInt(), displayName)
            )

            startActivity(Intent(applicationContext, ProfileActivity::class.java))
        }
    }

    fun signUp(view: View) {
        val browser = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/join"))
        startActivity(browser)
    }
}
