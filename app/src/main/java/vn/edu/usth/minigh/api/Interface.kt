package vn.edu.usth.minigh.api

import java.io.File

import okhttp3.Cache
import okhttp3.OkHttpClient

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHub {
    @GET("/users/{username}")
    suspend fun user(@Path("username") username: String): User

    @GET("/users/{username}/repos?sort=updated")
    suspend fun repos(@Path("username") username: String): Array<MinRepo>
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
