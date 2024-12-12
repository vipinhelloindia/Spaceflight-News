package com.spaceflight.news.compose

import kotlinx.serialization.Serializable

@Serializable
object SpaceListScreenNav

@Serializable
data class ArticleDetailScreenNav(val articleId: String)
