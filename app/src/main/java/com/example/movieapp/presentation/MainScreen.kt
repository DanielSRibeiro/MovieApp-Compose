package com.example.movieapp.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.movieapp.presentation.navigation.BottomNavigationBar
import com.example.movieapp.presentation.navigation.NavigationGraph
import com.example.movieapp.presentation.navigation.Screens
import com.example.movieapp.presentation.navigation.currentRoute

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    navController: NavHostController
) {
    Scaffold(
        bottomBar = {
            if (currentRoute(navController = navController) != Screens.DetailScreen.route) {
                BottomNavigationBar(navController = navController)
            }
        },
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            NavigationGraph(navController = navController)
        }
    }
}