package com.rguzmanc.friends.di

import com.rguzmanc.friends.datasource.network.FriendService
import com.rguzmanc.friends.datasource.network.KtorClientFactory


class NetworkModule {

    val friendService: FriendService by lazy {
        FriendService(
            httpClient = KtorClientFactory().build(),
            baseUrl = "https://private-908651-friends15.apiary-mock.com/"
        )
    }
}