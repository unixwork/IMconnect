package de.unixwork.imconnect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import de.unixwork.imconnect.ui.theme.IMconnectTheme

import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

enum class IMScreen() {
    Start,
    Connect,
    Contacts,
    Chat
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IMAppBar(
    canNavigateBack: Boolean = false,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(text = "IMconnect") },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector =  Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "back"
                    )
                }
            }
        }
    )
}

@Composable
fun IMApp(
    navController: NavHostController = rememberNavController(),
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            IMAppBar(
                canNavigateBack = false,
                navigateUp = {}
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = IMScreen.Start.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(IMScreen.Start.name) {
                Column {
                    Text(text = "Start")
                    Button(
                        onClick = {
                            navController.navigate(IMScreen.Connect.name)
                        }
                    ) {
                        Text(text = "Next")
                    }

                }

            }
            composable(IMScreen.Connect.name) {
                ConnectScreen()
            }
        }
    }
}

@Composable
fun ConnectScreen(
    navController: NavHostController = rememberNavController()
) {
    var host by remember { mutableStateOf(TextFieldValue("")) }
    var port by remember { mutableStateOf(TextFieldValue(""))}

    Column {
        Text(text = "Host")
        TextField(
            value = host,
            onValueChange = { newText ->
                host = newText
            }
        )
        Text(text = "Port")
        TextField(
            value = port,
            onValueChange = { newText ->
                port = newText
            }
        )
    }
}


