package sheridan.romeroad.caregiver.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

/**
 * Student ID: 991555778
 * Caregiver
 * created by davidromero
 * on 2025-03-24
 **/

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    // State for controlling the drawer
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        scrimColor = Color.Transparent, // Prevents the scrim from appearing when the drawer is closed
        drawerContent = {
            if (drawerState.isOpen) { // Render content only when drawer is open
                DrawerContent(navController, drawerState)
            }
        },
        content = {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                "Welcome Back!",
                                style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold)
                            )
                        },
                        navigationIcon = {
                            IconButton(onClick = {
                                scope.launch {
                                    if (drawerState.isClosed) drawerState.open() else drawerState.close()
                                }
                            }) {
                                Icon(Icons.Default.Menu, contentDescription = "Menu", tint = Color.White)
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Color(0xFF6200EE),
                            titleContentColor = Color.White
                        )
                    )
                }
            ) { innerPadding ->
                HomeContent(navController, innerPadding)
            }
        }
    )
}

@Composable
fun DrawerContent(navController: NavController, drawerState: DrawerState) {
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White) // Solid background for drawer
            .padding(16.dp), // Ensure proper spacing
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            "Navigation",
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
            color = Color(0xFF6200EE) // Accent color for the header
        )

        Divider(color = Color.Gray, thickness = 1.dp)

        DrawerButton("Profile") {
            scope.launch { drawerState.close() }
            navController.navigate("profile")
        }

        DrawerButton("Settings") {
            scope.launch { drawerState.close() }
            navController.navigate("settings") // Replace with actual route
        }

        DrawerButton("Help & Support") {
            scope.launch { drawerState.close() }
            navController.navigate("help_support") // Replace with actual route
        }
    }
}

@Composable
fun DrawerButton(text: String, onClick: () -> Unit) {
    TextButton(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text,
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Medium),
            color = Color.Black
        )
    }
}

@Composable
fun HomeContent(navController: NavController, innerPadding: PaddingValues) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header or Title Section
        item {
            Text(
                "Your Health Dashboard",
                style = TextStyle(fontSize = 28.sp, fontWeight = FontWeight.Bold),
                color = Color(0xFF6200EE),
                modifier = Modifier.padding(top = 8.dp)
            )

            Text(
                "Access your health information and services",
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Medium),
                color = Color.Gray
            )
        }

        // Buttons for Navigation
        items(listOf("Patient Status", "Gemini", "Geofence", "Location", "Medication Reminders", "Video Feeds", "Messages")) { buttonText ->
            HomeScreenButton(
                text = buttonText,
                onClick = {
                    when (buttonText) {
                        "Patient Status" -> navController.navigate("patientStatus")
                        "Gemini" -> navController.navigate("gemini")
                        "Geofence" -> navController.navigate("geofence")
                        "Location" -> navController.navigate("location")
                        "Medication Reminders" -> navController.navigate("medications")
                        "Video Feeds" -> navController.navigate("videos")
                        "Messages" -> navController.navigate("messages")
                    }
                }
            )
        }

        // Logout Button
        item {
            OutlinedButton(
                onClick = {
                    Firebase.auth.signOut()
                    navController.navigate("login") { popUpTo("home") { inclusive = true } }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Red)
            ) {
                Text("Logout", style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold))
            }
        }
    }
}

@Composable
fun HomeScreenButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE), contentColor = Color.White),
        shape = MaterialTheme.shapes.medium
    ) {
        Text(text, style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.SemiBold))
    }
}