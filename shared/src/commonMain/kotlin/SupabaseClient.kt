package com.shriya.earnly.aggregator

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

object SupabaseClient {
    private const val SUPABASE_URL = "https://syzfeybuldopbhmzbbak.supabase.co"
    private const val SUPABASE_KEY = "sb_publishable_KHvmSLz5jM2PtLOt85Zs3g_fAHqCy4w"

    private val httpClient = HttpClient(OkHttp) {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }

    suspend fun fetchGigs(): List<GigListing> {
        return try {
            val response = httpClient.get("$SUPABASE_URL/rest/v1/processed_gigs") {
                header("apikey", SUPABASE_KEY)
                header("Content-Type", "application/json")
                header("Prefer", "return=representation")
            }

            println("✅ Supabase Status: ${response.status}")
            response.body()
        } catch (e: Exception) {
            println("❌ Error: ${e.message}")
            emptyList()
        }
    }
}