package com.example.myfirstapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myfirstapp.screens.dashboard.DashboardScreen
import com.example.myfirstapp.screens.login.LoginScreen
import com.example.myfirstapp.screens.products.AddProductsScreen
import com.example.myfirstapp.screens.products.ProductListScreen
import com.example.myfirstapp.screens.products.UpdateProductScreen
import com.example.myfirstapp.screens.profile.ProfileScreen
import com.example.myfirstapp.screens.register.RegisterScreen
import com.example.myfirstapp.screens.settings.SettingsScreen
import com.example.myfirstapp.screens.splashscreen.SplashScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUTE_SPLASH
){
    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = startDestination,
    ){
        composable (ROUTE_LOGIN){
            LoginScreen(navController)
        }
        composable (ROUTE_REGISTER){
            RegisterScreen(navController)
        }
        composable (ROUTE_SPLASH){
            SplashScreen(navController)
        }
        composable (ROUTE_DASHBOARD){
            DashboardScreen(navController)
        }
        composable (ROUTE_ADD_PRODUCTS){
            AddProductsScreen(navController)
        }
        composable (ROUTE_LISTPRODUCTS){
            ProductListScreen(navController)
        }
        composable (ROUTE_UPDATE_PRODUCTS + "/{id}"){ backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: ""
            UpdateProductScreen(navController, id)
        }
        composable (ROUTE_PROFILE){
            ProfileScreen(navController)
        }
        composable (ROUTE_SETTINGS){
            SettingsScreen(navController)
        }
    }
}
