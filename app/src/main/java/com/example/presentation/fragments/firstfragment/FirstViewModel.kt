package com.example.presentation.fragments.firstfragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repositry.Repository
import com.example.data.RetrofitFactory.apiKey
import com.example.data.SteamUserResponse
import com.example.domain.SteamUseCase
import kotlinx.coroutines.launch

class FirstViewModel ():ViewModel(){
    val userInfo : MutableLiveData<SteamUserResponse> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()

    fun getUsersInfo(steamId: String){
        viewModelScope.launch {
            isLoading.postValue(true)
            val user = SteamUseCase.getInfo(apiKey, steamId)
            userInfo.postValue(user)
            isLoading.postValue(false)
        }
    }
}