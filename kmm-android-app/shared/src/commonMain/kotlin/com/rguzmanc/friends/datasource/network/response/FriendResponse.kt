package com.rguzmanc.friends.datasource.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FriendListResponse(
    val friends : List<FriendResponse>
)

@Serializable
data class FriendResponse(
    val id: Int,
    @SerialName("first_name") val name: String?,
    @SerialName("last_name") val lastName: String?
)