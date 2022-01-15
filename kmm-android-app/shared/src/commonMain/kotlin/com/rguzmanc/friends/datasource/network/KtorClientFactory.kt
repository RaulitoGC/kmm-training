package com.rguzmanc.friends.datasource.network

import com.rguzmanc.friends.datasource.network.response.FriendResponse
import com.rguzmanc.friends.domain.model.Friend
import io.ktor.client.*

expect class KtorClientFactory() {
    fun build(): HttpClient
}

fun FriendResponse.toFriend(): Friend {
    return Friend(
        id = id,
        name = name.orEmpty(),
        lastName = lastName.orEmpty()
    )
}
