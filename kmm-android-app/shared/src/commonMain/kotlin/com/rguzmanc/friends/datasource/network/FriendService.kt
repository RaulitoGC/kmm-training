package com.rguzmanc.friends.datasource.network

import com.rguzmanc.friends.datasource.network.response.FriendListResponse
import com.rguzmanc.friends.datasource.network.response.FriendResponse
import io.ktor.client.*
import io.ktor.client.request.*

class FriendService(
    private val httpClient: HttpClient,
    private val baseUrl: String
) {

    suspend fun getFriends(): List<FriendResponse>{
        return httpClient.get<FriendListResponse>{
            url("$baseUrl/friends")
        }.friends
    }
}