package com.rguzmanc.friends

import com.rguzmanc.friends.datasource.cache.toFriend
import com.rguzmanc.friends.datasource.cache.toFriendEntity
import com.rguzmanc.friends.datasource.network.response.FriendResponse
import com.rguzmanc.friends.datasource.network.toFriend
import comrguzmancfriendsdatasourcecache.Friend_Entity
import kotlin.test.Test
import kotlin.test.assertEquals

class MapperTest {

    companion object {
        const val FAKE_ID = 1L
        const val FAKE_NAME = "Dashiell"
        const val FAKE_LAST_NAME = "Guzman"
    }

    @Test
    fun successValidationInMapperFromFriendEntityToFriend() {
        val friendEntity = Friend_Entity(FAKE_ID, FAKE_NAME, FAKE_LAST_NAME)
        val friend = friendEntity.toFriend()
        assertEquals(friend.id, friendEntity.id.toInt())
        assertEquals(friend.name, friendEntity.name)
        assertEquals(friend.lastName, friendEntity.lastName)
    }

    @Test
    fun successValidationInMapperFromFriendResponseToFriendEntity() {
        val friendResponse = FriendResponse(FAKE_ID.toInt(), FAKE_NAME, FAKE_LAST_NAME)
        val friendEntity = friendResponse.toFriendEntity()
        assertEquals(friendEntity.id.toInt(), friendResponse.id)
        assertEquals(friendEntity.name, friendResponse.name)
        assertEquals(friendEntity.lastName, friendResponse.lastName)
    }

    @Test
    fun successValidationInMapperFromFriendResponseToFriend() {
        val friendResponse = FriendResponse(FAKE_ID.toInt(), FAKE_NAME, FAKE_LAST_NAME)
        val friend = friendResponse.toFriend()
        assertEquals(friend.id, friendResponse.id)
        assertEquals(friend.name, friendResponse.name)
        assertEquals(friend.lastName, friendResponse.lastName)
    }
}