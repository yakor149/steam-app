package com.example.data

data class SteamUserResponse(
    val response: SteamUserList
)

data class SteamUserList(
    val players: List<SteamUser>
)