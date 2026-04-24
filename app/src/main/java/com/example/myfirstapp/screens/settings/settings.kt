package com.example.myfirstapp.screens.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myfirstapp.R
import com.example.myfirstapp.data.AuthViewModel
import com.example.myfirstapp.navigation.ROUTE_PROFILE
import com.example.myfirstapp.ui.theme.MyFirstAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController: NavHostController) {
    val context = LocalContext.current
    val authViewModel = remember { AuthViewModel(navController, context) }

    var notificationsEnabled by remember { mutableStateOf(true) }
    var darkModeEnabled by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.settings_label)) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { authViewModel.logout() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                            contentDescription = stringResource(R.string.logout_desc)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            SettingsSectionTitle(title = stringResource(R.string.account_section))
            
            ListItem(
                headlineContent = { Text(stringResource(R.string.profile_setting)) },
                leadingContent = {
                    Icon(Icons.Default.Person, contentDescription = null)
                },
                modifier = Modifier.clickable {
                    navController.navigate(ROUTE_PROFILE)
                }
            )
            
            HorizontalDivider()
            
            SettingsSectionTitle(title = "App Preferences")
            
            ListItem(
                headlineContent = { Text(stringResource(R.string.notifications_setting)) },
                leadingContent = {
                    Icon(Icons.Default.Notifications, contentDescription = null)
                },
                trailingContent = {
                    Switch(
                        checked = notificationsEnabled,
                        onCheckedChange = { notificationsEnabled = it }
                    )
                }
            )
            
            ListItem(
                headlineContent = { Text(stringResource(R.string.dark_mode_setting)) },
                leadingContent = {
                    Icon(Icons.Default.Info, contentDescription = null) // Using Info as placeholder for theme icon if not found
                },
                trailingContent = {
                    Switch(
                        checked = darkModeEnabled,
                        onCheckedChange = { darkModeEnabled = it }
                    )
                }
            )
            
            HorizontalDivider()
            
            SettingsSectionTitle(title = stringResource(R.string.about_section))
            
            ListItem(
                headlineContent = { Text("App Version") },
                supportingContent = { Text(stringResource(R.string.version_text)) },
                leadingContent = {
                    Icon(Icons.Default.Info, contentDescription = null)
                }
            )
        }
    }
}

@Composable
fun SettingsSectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.labelLarge,
        color = MaterialTheme.colorScheme.primary,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun SettingsPreview() {
    MyFirstAppTheme {
        SettingsScreen(navController = rememberNavController())
    }
}
