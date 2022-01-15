package com.rguzmanc.friends.android.util

import java.util.concurrent.atomic.AtomicBoolean

data class OneTimeEvent<T>(val payload: T? = null) {
    private val isConsumed = AtomicBoolean(false)

    internal fun getValue(): T? =
        if (isConsumed.compareAndSet(false, true)) payload
        else null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as OneTimeEvent<*>

        if (payload != other.payload) return false
        if (isConsumed.get() != other.isConsumed.get()) return false

        return true
    }

    override fun hashCode(): Int {
        var result = payload?.hashCode() ?: 0
        result = 31 * result + isConsumed.hashCode()
        return result
    }


}

fun <T> T.toOneTimeEvent() =
    OneTimeEvent(this)

/**
 * Allows you to consume the [OneTimeEvent.payload] of the event only once,
 * as it will be marked as consumed on access.
 */

fun <T> OneTimeEvent<T>?.consumeOnce(block: (T) -> Unit) {
    this?.getValue()?.let { block(it) }
}