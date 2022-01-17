package com.rguzmanc.friends.di

import com.rguzmanc.friends.datasource.cache.DriverFactory
import com.rguzmanc.friends.datasource.cache.FriendCache
import com.rguzmanc.friends.datasource.cache.FriendDatabaseFactory

class CacheModule {

    val friendCache: FriendCache by lazy {
        val driverFactory = DriverFactory()
        val dataBase = FriendDatabaseFactory(driverFactory = driverFactory).createDatabase()
        return@lazy FriendCache(dataBase)
    }
}