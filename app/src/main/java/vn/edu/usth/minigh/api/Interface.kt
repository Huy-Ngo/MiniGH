package vn.edu.usth.minigh.api

import kotlinx.coroutines.GlobalScope
import java.io.File

import okhttp3.Cache
import okhttp3.OkHttpClient

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

import java.util.ArrayList

interface GitHub {
    @GET("/user")
    suspend fun current_user(@Header("Authorization") token: String): User

    @GET("/users/{username}")
    suspend fun user(@Path("username") username: String): User

    @GET("/users/{username}/repos?sort=updated")
    suspend fun repos(@Path("username") username: String): Array<MinRepo>

    @GET("/repos/{repoName}")
    suspend fun repo(@Path("repoName", encoded=true) repoName: String): MinRepo

    @GET("/repos/{repoName}/readme")
    suspend fun readme(@Path("repoName", encoded=true) repoName: String): Readme

    @GET("/search/issues")
    suspend fun issueUser(@Query("q") author:String): IssueOfUser

    @GET("/repos/{repo_name}/issues")
    suspend fun issueRepo(@Path("repo_name", encoded = true) repo_name:String, @Query("state") state:String): ArrayList<Issue>

    @GET("/repos/{repo_name}/pulls")
    suspend fun prRepo(@Path("repo_name", encoded = true) repo_name: String, @Query("state") state:String): ArrayList<Pulls>

    @GET("/search/issues")
    suspend fun prUser(@Query("q") author:String): PrOfUser

    @GET("/{comment_path}")
    suspend fun comment(@Path("comment_path", encoded = true) comment_path:String) :ArrayList<Comments>

    @GET("/repos/{repoName}/branches")
    suspend fun branches(
        @Path("repoName", encoded=true) repoName: String
    ): Array<ShortBranch>

    @GET("/repos/{repoName}/contents/")
    suspend fun contents(
        @Path("repoName", encoded=true) repoName: String,
        @Query("ref") ref: String
    ): Array<Content>
}

val client = OkHttpClient.Builder()  // FIXME: use context.getCacheDir instead
    .cache(Cache(File("/data/data/vn.edu.usth.minigh/cache"), 5 * 1024 * 1024))
    .addInterceptor { chain ->
        chain.proceed(chain.request().newBuilder().header(
            "Cache-Control", "public, max-age=3600").build())  // 1 hour
    }.build()

val github = Retrofit.Builder().baseUrl("https://api.github.com")
    .addConverterFactory(GsonConverterFactory.create()).client(client).build()
    .create(GitHub::class.java)
