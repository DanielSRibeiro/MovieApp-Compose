package com.example.movieapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.movieapp.presentation.screen.movie_popular_feature.MoviePopularScreen
import com.example.movieapp.presentation.screen.movie_popular_feature.MoviePopularViewModel
import com.example.movieapp.presentation.screen.search_movie_feature.MovieSearchEvent
import com.example.movieapp.presentation.screen.search_movie_feature.MovieSearchScreen
import com.example.movieapp.presentation.screen.search_movie_feature.MovieSearchViewModel

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.MoviePopular.route
    ) {
        composable(BottomNavItem.MoviePopular.route) {
            val viewModel: MoviePopularViewModel = hiltViewModel()
            val uiState = viewModel.uiState

            MoviePopularScreen(
                uiState = uiState,
                navigateToDetailMovie = {

                }
            )
        }
        composable(BottomNavItem.MovieSearch.route) {
            val viewModel: MovieSearchViewModel = hiltViewModel()
            val uiSate = viewModel.uiState
            val onEvent: (MovieSearchEvent) -> Unit = viewModel::event
            val onFetch: (String) -> Unit = viewModel::fetch

            MovieSearchScreen(
                uiState = uiSate,
                onEvent = onEvent,
                onFetch = onFetch,
                navigateToDetailMovie = {

                }
            )
        }
        composable(BottomNavItem.MovieFavorite.route) {

        }
    }
}