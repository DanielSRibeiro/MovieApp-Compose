package com.example.movieapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.movieapp.presentation.screen.movie_detail_feature.MovieDetailScreen
import com.example.movieapp.presentation.screen.movie_detail_feature.MovieDetailViewModel
import com.example.movieapp.presentation.screen.movie_favorite_feature.MovieFavoriteScreen
import com.example.movieapp.presentation.screen.movie_favorite_feature.MovieFavoriteViewModel
import com.example.movieapp.presentation.screen.movie_popular_feature.MoviePopularScreen
import com.example.movieapp.presentation.screen.movie_popular_feature.MoviePopularViewModel
import com.example.movieapp.presentation.screen.search_movie_feature.MovieSearchEvent
import com.example.movieapp.presentation.screen.search_movie_feature.MovieSearchScreen
import com.example.movieapp.presentation.screen.search_movie_feature.MovieSearchViewModel
import com.example.movieapp.util.Constants

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
                    navController.navigate(BottomNavItem.MovieDetail.passMovieId(movieId = it))
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
                    navController.navigate(BottomNavItem.MovieDetail.passMovieId(movieId = it))
                }
            )
        }
        composable(BottomNavItem.MovieFavorite.route) {
            val viewModel: MovieFavoriteViewModel = hiltViewModel()
            val uiState = viewModel.uiState

            MovieFavoriteScreen(
                uiState = uiState,
                navigateToDetail = {
                    navController.navigate(BottomNavItem.MovieDetail.passMovieId(movieId = it))
                }
            )
        }

        composable(
            route = BottomNavItem.MovieDetail.route,
            arguments = listOf(
                navArgument(Constants.MOVIE_DETAIL_ARGUMENTS_KEY) {
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ) {
            val viewModel: MovieDetailViewModel = hiltViewModel()
            val uiState = viewModel.uiState
            val favorite = viewModel::favorite
            val checkFavorite = viewModel::checkedFavorite
            val getMovieDetail = viewModel::getMovieDetail

            MovieDetailScreen(
                id = it.arguments?.getInt(Constants.MOVIE_DETAIL_ARGUMENTS_KEY),
                uiState = uiState,
                onAddFavorite = favorite,
                checkFavorite = checkFavorite,
                getMovieDetail = getMovieDetail
            )
        }
    }
}