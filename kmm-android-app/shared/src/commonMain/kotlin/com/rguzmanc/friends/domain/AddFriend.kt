package com.rguzmanc.friends.domain

import com.rguzmanc.friends.datasource.cache.FriendCache
import com.rguzmanc.friends.datasource.cache.toFriend
import com.rguzmanc.friends.datasource.network.toFriend
import com.rguzmanc.friends.domain.model.Friend
import com.rguzmanc.friends.helper.CommonFlow
import com.rguzmanc.friends.helper.DataState
import com.rguzmanc.friends.helper.asCommonFlow
import com.rguzmanc.friends.helper.randomString
import kotlinx.coroutines.flow.flow
import kotlin.random.Random

class AddFriend(private val friendCache: FriendCache) {

    fun execute(): CommonFlow<DataState<Friend>> = flow {
        try {
            // local
            val friend = Friend(
                id = Random.nextInt(10, 100),
                name = randomString,
                lastName = randomString
            )
            friendCache.insert(friend)
            emit(DataState.data(data = friend))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(DataState.error(message = "error getting friends"))
        }

    }.asCommonFlow()
}