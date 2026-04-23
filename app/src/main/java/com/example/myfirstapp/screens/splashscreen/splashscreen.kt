package com.example.myfirstapp.screens.splashscreen

import android.window.SplashScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myfirstapp.R
import com.example.myfirstapp.navigation.ROUTE_LOGIN
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavHostController){
    LaunchedEffect(true) {
        delay(2000)
        navController.navigate(ROUTE_LOGIN)
    }
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White),
        contentAlignment = Alignment.Center
    ){
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.img),
                contentDescription = "logo",
                modifier = Modifier.size(200.dp )
            )
            Text(
                text = "Welcome to my app",
                color = Color.Red,
                fontSize = 24.sp,
            )
        }

    }
}

@Preview
@Composable
fun SplashScreenPreview(){
    SplashScreen(navController = rememberNavController())
}
