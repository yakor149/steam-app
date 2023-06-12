package com.example.data

import com.example.data.interfaces.SteamApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {
    val apiKey = "A342C8A6DAD879B1AA8CE8A93A8B43D1"

    private val START_URL = "https://api.steampowered.com/"
    val steam_Api: SteamApiService = createSteamApiService().create(SteamApiService::class.java)

    private fun createSteamApiService(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        return Retrofit.Builder()
            .baseUrl(START_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}