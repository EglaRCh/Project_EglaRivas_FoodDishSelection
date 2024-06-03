package com.ebc.projectegla.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ebc.projectegla.views.MainScreen
import com.ebc.projectegla.views.OnboardingScreen


@Composable

fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "onboarding" ) {
        composable("onboarding") {
            OnboardingScreen(navController)
        }
        composable("main") {
            MainScreen()
        }
    }
}