package com.rguzmanc.friends

import com.rguzmanc.friends.datasource.network.KtorClientFactory
import io.ktor.client.engine.*
import io.ktor.client.engine.android.*
import org.junit.Assert.assertTrue
import org.junit.Test

class AndroidEngineTest {

    @Test
    fun `verify Android engine for http client`() {
        val ktorClientFactory = KtorClientFactory().build()
        assertTrue( ktorClientFactory.engine is AndroidClientEngine)
    }
}