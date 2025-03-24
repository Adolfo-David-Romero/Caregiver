package sheridan.romeroad.caregiver.ui.common

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import sheridan.romeroad.caregiver.ui.auth.LoginScreen
import sheridan.romeroad.caregiver.ui.home.HomeScreen

/**
 * Student ID: 991555778
 * Caregiver
 * created by davidromero
 * on 2025-03-24
 **/
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val isLoggedIn = Firebase.auth.currentUser != null
    val userId = Firebase.auth.currentUser?.uid
    val context = LocalContext.current
    /*View Models*/
    /*View Models*/


    NavHost(navController = navController, startDestination = if (isLoggedIn) "home" else "login") {
        composable("login") {
            LoginScreen(
                onLoginSuccess = { navController.navigate("home") { popUpTo("login") { inclusive = true } } },
                onNavigateToRegister = { navController.navigate("register") }
            )
        }

        composable("home") { HomeScreen(navController) }

    }
}
