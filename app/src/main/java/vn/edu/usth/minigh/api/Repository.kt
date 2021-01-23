package vn.edu.usth.minigh.api

data class License(val spdx_id: String)

data class MinRepo(
    val full_name: String,
    val description: String?,
    val fork: Boolean,
    val language: String?,
    val forks_count: Int,
    val stargazers_count: Int,
    val watchers_count: Int,
    val license: License?,
    val parent: MinRepo?
)
