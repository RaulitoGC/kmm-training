package com.rguzmanc.friends.datasource.cache

import com.rguzmanc.friends.datasource.network.response.FriendResponse
import com.rguzmanc.friends.domain.model.Friend
import com.squareup.sqldelight.db.SqlDriver
import comrguzmancfriendsdatasourcecache.Friend_Entity

class FriendDatabaseFactory(
    private val driverFactory: DriverFactory
){
    fun createDatabase(): FriendsDataBase {
        return FriendsDataBase(driverFactory.createDriver())
    }
}

expect class DriverFactory {
    fun createDriver(): SqlDriver
}

fun Friend_Entity.toFriend() : Friend {
    return Friend(
        id = id.toInt(),
        name = name,
        lastName = lastName
    )
}

fun FriendResponse.toFriendEntity(): Friend_Entity{
    return Friend_Entity(
        id = id.toLong(),
        name = name.orEmpty(),
        lastName = lastName.orEmpty()
    )
}