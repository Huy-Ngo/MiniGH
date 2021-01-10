package vn.edu.usth.minigh.api

data class User(
    val login: String,
    val avatar_url: String,
    val name: String?,
    val email: String?,
    val bio: String?,
    val followers: Int,
    val following: Int,
)
