package com.rguzmanc.friends.helper

const val STRING_LENGTH = 12
private val charPool : List<Char> = ('a'..'z') + ('A'..'Z')

val randomString : String
    get() = (1..STRING_LENGTH)
    .map { i -> kotlin.random.Random.nextInt(0, charPool.size) }
    .map(charPool::get)
    .joinToString("");