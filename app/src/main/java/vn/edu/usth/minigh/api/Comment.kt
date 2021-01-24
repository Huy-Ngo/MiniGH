package vn.edu.usth.minigh.api

import org.json.JSONObject

data class Comments(
        val url: String?,
        val body: String?,
        val comment_url: String?,
        val created_at: String?,
        val user: User,
)
