package com.example.myfirstapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.myfirstapp.navigation.AppNavHost
import com.example.myfirstapp.screens.register.RegisterScreen
import com.example.myfirstapp.ui.theme.MyFirstAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyFirstAppTheme {
                AppNavHost()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun RegisterPreview(){
    RegisterScreen(navController = rememberNavController())
}
