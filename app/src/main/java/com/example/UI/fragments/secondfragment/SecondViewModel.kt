package com.example.UI.fragments.secondfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.SteamApiService
import com.example.data.SteamUserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SecondViewModel : ViewModel() {
    private val apiKey = "A342C8A6DAD879B1AA8CE8A93A8B43D1"
    private val steamApiService: SteamApiService = createSteamApiService()

    private val _avatarUrl = MutableLiveData<String>()
    val avatarUrl: LiveData<String> = _avatarUrl

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun getUserInfo(steamId: String) {
        val call = steamApiService.getPlayerSummaries(apiKey, steamId)

        call.enqueue(object : Callback<SteamUserResponse> {
            override fun onResponse(
                call: Call<SteamUserResponse>,
                response: Response<SteamUserResponse>
            ) {
                if (response.isSuccessful) {
                    val userResponse = response.body()
                    if (userResponse != null && userResponse.response.players.isNotEmpty()) {
                        val user = userResponse.response.players[0]
                        _avatarUrl.value = user.avatarfull
                    } else {
                        _error.value = "User not found"
                    }
                } else {
                    _error.value = "Error: ${response.code()}"
                }
            }

            override fun onFailure(call: Call<SteamUserResponse>, t: Throwable) {
                _error.value = "Error: ${t.message}"
            }
        })
    }

    private fun createSteamApiService(): SteamApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.steampowered.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(SteamApiService::class.java)
    }
}