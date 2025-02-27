package com.example.movieapp.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Details
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.movieapp.util.Constants.MOVIE_DETAIL_ARGUMENTS_KEY

sealed class BottomNavItem(val title: String, val icon: ImageVector, val route: String) {
    data object MoviePopular : BottomNavItem(
        title = "Filmes Populares",
        icon = Icons.Default.Movie,
        route = "movie_popular_screen"
    )

    data object MovieSearch : BottomNavItem(
        title = "Pesquisar",
        icon = Icons.Default.Search,
        route = "movie_search_screen"
    )

    data object MovieFavorite : BottomNavItem(
        title = "Favoritos",
        icon = Icons.Default.Favorite,
        route = "movie_favorite_screen"
    )

    data object MovieDetail : BottomNavItem(
        title = "Detalhes",
        icon = Icons.Default.Details,
        route = "movie_detail_destination?$MOVIE_DETAIL_ARGUMENTS_KEY=" +
                "{$MOVIE_DETAIL_ARGUMENTS_KEY}"
    ) {
        fun passMovieId(movieId: Int) =
            "movie_detail_destination?$MOVIE_DETAIL_ARGUMENTS_KEY=$movieId"
    }
}