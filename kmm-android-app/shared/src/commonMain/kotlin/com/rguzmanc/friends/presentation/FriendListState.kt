package com.rguzmanc.friends.presentation

import com.rguzmanc.friends.domain.model.Friend

data class FriendListState(
    val friends: List<Friend> = emptyList(),
    val loading: Boolean = false,
){
    constructor() : this(
        friends = emptyList(),
        loading = false
    )
}

