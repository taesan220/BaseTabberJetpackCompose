@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.basetabberjetpackcompose
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.basetabberjetpackcompose.ui.theme.BottomBarJCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BottomBarJCTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = colorResource(id = R.color.white)
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navigationController = rememberNavController()
    val context = LocalContext.current
    val selected = remember { mutableStateOf(Icons.Default.Home) }
    val title = remember { mutableStateOf("Home") }

    // selectedTabIndex를 상태로 관리
    val selectedTabIndex = remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            AppCenterTopBar(
                title = title.value,
                onMenuClick = { showToast(context, "Menu clicked") },
                onSearchClick = { showToast(context, "Search button pressed") }
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = colorResource(id = R.color.tabbar),
            ) {
                BottomTabButtons(
                    selectedTabIndex = selectedTabIndex.value,
                    onTabSelected = { index ->
                        selectedTabIndex.value = index
                        selected.value = when (index) {
                            0 -> Icons.Default.Home
                            1 -> Icons.Default.Search
                            2 -> Icons.Default.Settings
                            else -> Icons.Default.Home
                        }
                    },
                    navigationController = navigationController,
                    title = title
                )
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navigationController,
            startDestination = Screens.Home.screen,
            Modifier
                .fillMaxSize() // Ensure NavHost fills the available space
                .padding(
                    top = paddingValues.calculateTopPadding(),
                    bottom = paddingValues.calculateBottomPadding(),
                    start = paddingValues.calculateStartPadding(LocalLayoutDirection.current),
                    end = paddingValues.calculateEndPadding(LocalLayoutDirection.current)
                )
        ) {
            composable(Screens.Home.screen) { Home() }
            composable(Screens.ItemList.screen) { ItemList() }
            composable(Screens.Setting.screen) { Setting() }
        }
    }
}

private fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

@Preview
@Composable
fun MyBottomBarPreview() {
    BottomBarJCTheme {
        MainScreen()
    }
}