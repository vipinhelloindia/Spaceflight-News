package com.spaceflight.news.compose

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.feature.details.ArticleDetailScreen
import com.feature.listing.SpaceNewsListScreen


@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = SpaceListScreenNav
    ) {
        composable<SpaceListScreenNav> {
            SpaceNewsListScreen { articleId ->
                navController.navigate(ArticleDetailScreenNav(articleId = articleId))
            }
        }

        composable<ArticleDetailScreenNav> { backStackEntry ->
            val args = backStackEntry.toRoute<ArticleDetailScreenNav>()
            ArticleDetailScreen(articleId = args.articleId) {
                navController.popBackStack()
            }
        }
    }
}
