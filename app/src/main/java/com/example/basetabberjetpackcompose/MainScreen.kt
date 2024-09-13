package com.example.basetabberjetpackcompose

import Home
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.basetabberjetpackcompose.tabbar.BottomTabButtons
import com.example.basetabberjetpackcompose.tabbar_screen.ItemList
import com.example.basetabberjetpackcompose.tabbar_screen.Screens
import com.example.basetabberjetpackcompose.tabbar_screen.Setting
import com.example.sidemenupractice2.navigationDrawer.CustomModalNavigationDrawer
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }

    val navigationController = rememberNavController()
    val context = LocalContext.current
    val selected = remember { mutableStateOf(Icons.Default.Home) }
    val title = remember { mutableStateOf("Home") }

    // selectedTabIndex를 상태로 관리
    val selectedTabIndex = remember { mutableStateOf(0) }
    CustomModalNavigationDrawer(
        drawerState = drawerState,
        //items = items,
        selectedItemIndex = selectedItemIndex,
        onItemSelected = { index ->

            selectedItemIndex = index

            updateTabSelection(index, selectedTabIndex, selected, title, navigationController) // NavController 전달

            Toast.makeText(context, "index = " + index, Toast.LENGTH_SHORT).show()

            scope.launch {
                drawerState.close()
            }
        }
    ) {
        Scaffold(
            topBar = {
                AppCenterTopBar(
                    title = title.value,
                    onMenuClick = {
                        scope.launch {
                            drawerState.open()
                        }
                        showToast(context, "Menu clicked") },
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
                            updateTabSelection(index, selectedTabIndex, selected, title, navigationController) // NavController 전달
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
}

fun updateTabSelection(
    index: Int,
    selectedTabIndex: MutableState<Int>,
    selected: MutableState<ImageVector>,
    title: MutableState<String>,
    navController: NavController // NavController를 전달
) {
    selectedTabIndex.value = index
    selected.value = when (index) {
        0 -> Icons.Default.Home
        1 -> Icons.Default.Search
        2 -> Icons.Default.Settings
        else -> Icons.Default.Home
    }
    title.value = when (index) {
        0 -> "Home"
        1 -> "Item List"
        2 -> "Settings"
        else -> "Home"
    }

    // 페이지 전환
    when (index) {
        0 -> navController.navigate(Screens.Home.screen)
        1 -> navController.navigate(Screens.ItemList.screen)
        2 -> navController.navigate(Screens.Setting.screen)
    }
}

private fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}