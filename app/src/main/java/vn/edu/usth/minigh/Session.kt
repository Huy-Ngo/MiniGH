package vn.edu.usth.minigh

import android.content.Context
import android.content.SharedPreferences

data class User(val id: Int, val name: String)

class SessionManager(context: Context) {
    private val sharedPreferences: SharedPreferences
    private val editor: SharedPreferences.Editor

    init {
        sharedPreferences =
            context.getSharedPreferences("session", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
    }

    fun saveSession(user: User) = editor.putInt("user", user.id).commit()
    fun getSession(): Int = sharedPreferences.getInt("user", -1)
    fun removeSession() = editor.putInt("user", -1).commit()
}
