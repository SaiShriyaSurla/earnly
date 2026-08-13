package com.shriya.earnly.aggregator

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform