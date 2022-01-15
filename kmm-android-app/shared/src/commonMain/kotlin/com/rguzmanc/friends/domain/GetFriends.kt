package com.rguzmanc.friends.domain

import com.rguzmanc.friends.datasource.network.FriendService
import com.rguzmanc.friends.datasource.network.toFriend
import com.rguzmanc.friends.domain.model.Friend
import com.rguzmanc.friends.helper.CommonFlow
import com.rguzmanc.friends.helper.asCommonFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class GetFriends(
    private val friendsService: FriendService
) {

    fun execute() : CommonFlow<DataState> = flow {
        try {
            val friends = friendsService
                .getFriends()
                .filter {
                    !it.name.isNullOrEmpty() || !it.lastName.isNullOrEmpty()
                }
                .map {
                    it.toFriend()
                }
            emit(DataState.Success(friends))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(DataState.Error(Exception()))
        }

    }.asCommonFlow()

    sealed class DataState{
        data class Success(val friends: List<Friend>): DataState()
        data class Error(val exception: Exception): DataState()
    }
}

