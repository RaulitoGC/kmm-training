package com.rguzmanc.friends

import com.rguzmanc.friends.datasource.network.KtorClientFactory
import io.ktor.client.engine.*
import io.ktor.client.engine.ios.*

import kotlin.test.Test
import kotlin.test.assertTrue

class IosGreetingTest {

    @Test
    fun `validateiOSEngineConfig`() {
        val ktorClientFactory = KtorClientFactory().build()
        assertTrue( ktorClientFactory.engineConfig is IosClientEngineConfig)
    }
}