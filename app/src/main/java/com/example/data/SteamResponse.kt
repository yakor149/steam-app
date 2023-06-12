package com.example.data

import com.example.data.models.SteamUser

data class SteamUserResponse(
    val response: SteamUserList
)

data class SteamUserList(
    val players: List<SteamUser>
)