package com.rguzmanc.friends.domain

import com.rguzmanc.friends.datasource.network.FriendService
import com.rguzmanc.friends.datasource.network.toFriend
import com.rguzmanc.friends.domain.model.Friend
import com.rguzmanc.friends.helper.CommonFlow
import com.rguzmanc.friends.helper.DataState
import com.rguzmanc.friends.helper.asCommonFlow
import kotlinx.coroutines.flow.flow

class GetFriends(
    private val friendsService: FriendService
) {

    fun execute(): CommonFlow<DataState<List<Friend>>> = flow {
        try {
            val friends = friendsService
                .getFriends()
                .filter {
                    !it.name.isNullOrEmpty() || !it.lastName.isNullOrEmpty()
                }
                .map {
                    it.toFriend()
                }
            emit(DataState.data(data = friends))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(DataState.error(message = "error getting friends"))
        }

    }.asCommonFlow()
}

