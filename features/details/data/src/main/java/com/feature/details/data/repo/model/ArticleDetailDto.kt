package com.feature.details.data.repo.model

import kotlinx.serialization.Serializable

@Serializable
data class ArticleDetailDto(
    val id: Int,
    val title: String,
    val url: String,
    val image_url: String,
    val news_site: String,
    val summary: String,
    val published_at: String,
    val updated_at: String,
    val featured: Boolean,
    val launches: List<LaunchDto>,
    val events: List<EventDto>
)

@Serializable
data class LaunchDto(
    val launch_id: String,
    val provider: String
)

@Serializable
data class EventDto(
    val event_id: Int,
    val provider: String
)