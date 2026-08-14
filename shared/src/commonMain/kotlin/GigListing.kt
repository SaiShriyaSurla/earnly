package com.shriya.earnly.aggregator

import kotlinx.serialization.Serializable

@Serializable
data class GigListing(
    val id: String,
    val title: String,
    val source_platform: String,
    val raw_payout: String,
    val payout_numeric: Double,
    val external_apply_url: String,
    val ai_summary: String,
    val required_skills: List<String>,
    val estimated_hours: Int,
    val competition_tier: String,
    val is_premium_locked: Boolean,
    val published_at: String
)