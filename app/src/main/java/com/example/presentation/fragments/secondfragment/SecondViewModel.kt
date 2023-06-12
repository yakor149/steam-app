package com.example.presentation.fragments.secondfragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repositry.Repository
import com.example.data.RetrofitFactory.apiKey
import com.example.domain.SteamUseCase
import kotlinx.coroutines.launch

class SecondViewModel():ViewModel(){
    val avatarUrl : MutableLiveData<String> = MutableLiveData()

    fun getUserAvatar(steamId: String){
        viewModelScope.launch {
            val user = SteamUseCase.getInfo(apiKey, steamId)
            avatarUrl.value = user.response.players[0].avatarfull
        }
    }
}