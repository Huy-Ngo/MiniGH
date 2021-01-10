package vn.edu.usth.minigh.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHub {
    @GET("/users/{username}")
    suspend fun user(@Path("username") username: String): User

    @GET("/users/{username}/repos")
    suspend fun repos(@Path("username") username: String): Array<MinRepo>
}

val github = Retrofit.Builder().baseUrl("https://api.github.com")
    .addConverterFactory(GsonConverterFactory.create()).build()
    .create(GitHub::class.java)
