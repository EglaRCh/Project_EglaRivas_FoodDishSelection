package com.ebc.projectegla

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.ebc.projectegla.navigation.AppNavigation




class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                val navController = rememberNavController()
                AppNavigation(navController)                }
            }
        }


