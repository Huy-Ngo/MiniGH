package vn.edu.usth.minigh

import android.content.Context
import android.content.SharedPreferences

data class Token(val Token: String)

class TokenManager(context: Context) {
    private val sharedPreferences: SharedPreferences
    private val editor: SharedPreferences.Editor

    init {
        sharedPreferences =
            context.getSharedPreferences("Token", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
    }

    fun saveToken(token: String) = editor.putString("token", token).commit()
    fun getToken(): String? = sharedPreferences.getString("token", "")
    fun removeToken() = editor.putString("token", "").commit()
}
