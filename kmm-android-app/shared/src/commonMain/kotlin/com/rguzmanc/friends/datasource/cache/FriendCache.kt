package com.rguzmanc.friends.datasource.cache

import com.rguzmanc.friends.domain.model.Friend
import comrguzmancfriendsdatasourcecache.Friend_Entity

class FriendCache(private val friendsDataBase: FriendsDataBase) {

    fun insert(friend: Friend){
        friendsDataBase.friendsQueries.insertFriend(
            id = friend.id.toLong(),
            name = friend.name,
            lastName = friend.lastName
        )
    }

    fun getFriends(): List<Friend_Entity>{
        return friendsDataBase.friendsQueries.selectAll().executeAsList()
    }
}