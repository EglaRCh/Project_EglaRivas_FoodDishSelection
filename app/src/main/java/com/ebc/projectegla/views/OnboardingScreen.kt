package com.ebc.projectegla.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ebc.projectegla.ui.theme.Orange40

@Composable
fun OnboardingScreen(navController: NavController) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text="¿Qué se te antoja para comer?",
            color = Color.White,
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            modifier = Modifier
                .padding(bottom = 32.dp)
                .background(Color.DarkGray)
                .padding(16.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = {navController.navigate("main")},
            colors = ButtonDefaults.buttonColors(containerColor = Orange40)
            ) {
            Text(text ="EMPEZAR")
        }
    }
    
}