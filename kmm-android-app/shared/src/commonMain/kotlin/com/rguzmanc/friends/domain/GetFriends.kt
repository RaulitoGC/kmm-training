package com.rguzmanc.friends.domain

import com.rguzmanc.friends.datasource.cache.FriendCache
import com.rguzmanc.friends.datasource.cache.toFriend
import com.rguzmanc.friends.datasource.cache.toFriendEntity
import com.rguzmanc.friends.datasource.network.FriendService
import com.rguzmanc.friends.datasource.network.toFriend
import com.rguzmanc.friends.domain.model.Friend
import com.rguzmanc.friends.helper.CommonFlow
import com.rguzmanc.friends.helper.DataState
import com.rguzmanc.friends.helper.asCommonFlow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetFriends(
    private val friendsService: FriendService,
    private val friendCache: FriendCache
) {

    fun execute(): CommonFlow<DataState<List<Friend>>> = flow {
        try {

            // local
            val localFriends = friendCache.getFriends().map {
                it.toFriend()
            }.sortedBy { it.id }
            emit(DataState.data(data = localFriends))

            // Remote
            val remoteFriends = friendsService.getFriends()
            remoteFriends.forEach { remoteFriend ->
                friendCache.insert(remoteFriend.toFriend())
            }

            val totalFriends = localFriends + remoteFriends.map { it.toFriend() }

            val friends = totalFriends
                .distinctBy { it.id }
                .sortedBy { it.id }

            emit(DataState.data(data = friends))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(DataState.error(message = "error getting friends"))
        }

    }.asCommonFlow()
}

