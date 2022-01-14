package com.rguzmanc.friends

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}