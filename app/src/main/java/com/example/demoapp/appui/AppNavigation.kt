package com.example.demoapp.appui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.demoapp.utils.Screens
import com.example.demoapp.viewmodels.DemoSharedViewModel

@Composable
fun AppNavigation(navController:NavHostController){
    val sharedViewModel= hiltViewModel<DemoSharedViewModel>()
    NavHost(
        navController = navController,
        startDestination = Screens.LOGIN.name
    ) {
        composable(Screens.LOGIN.name) {
            DisplayLoginScreen(navController,sharedViewModel)
        }
        composable(Screens.HOME.name) {
            HomeScreen(navController,sharedViewModel)
        }
        composable(Screens.DETAIL.name) {
            DetailScreen(sharedViewModel)
        }
    }
}