package de.unixwork.imconnect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState

import de.unixwork.imconnect.IMViewModel

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
        },
        floatingActionButton = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            if(currentRoute == IMScreen.Start.name) {
                FloatingActionButton(
                    onClick = { navController.navigate(IMScreen.Connect.name) },
                    shape = RoundedCornerShape(16.dp),
                    containerColor = MaterialTheme.colorScheme.primary
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add")
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = IMScreen.Start.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(IMScreen.Start.name) {
                Column {
                    val testList = listOf("Connection 1", "Connection 2")
                    ConnectionsList(testList)
                }

            }
            composable(IMScreen.Connect.name) {
                ConnectScreen()
            }
        }
    }
}


@Composable
fun ConnectionsList(connections: List<String>) {
    LazyColumn {
        items(connections) { conn ->
            ConnectionItem(name = conn)
        }
    }
}

@Composable
fun ConnectionItem(name: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                onClick = {},
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple() // deprecated: use ripple()
            )
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = name, fontSize = 18.sp)
    }
}

@Composable
fun ConnectScreen(
    navController: NavHostController = rememberNavController(),
    imViewModel: IMViewModel = viewModel()
) {
    var host by remember { mutableStateOf(TextFieldValue("")) }
    var port by remember { mutableStateOf(TextFieldValue(""))}

    Column {
        Text(text = "Host")
        TextField(
            value = host,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { newText ->
                host = newText
            }
        )
        Text(text = "Port")
        TextField(
            value = port,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { newText ->
                port = newText
            }
        )
        Button(onClick = {}) {
            Text(text = "Connect")
        }
    }
}




