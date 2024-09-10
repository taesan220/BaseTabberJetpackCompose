package com.example.basetabberjetpackcompose.tabbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.basetabberjetpackcompose.R
import com.google.android.material.tabs.TabItem

data class TabItem(val title: String, val icon: ImageVector)

@Composable
fun BottomTabBarWithButtons(
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit,
    navigationController: NavController,
    title: MutableState<String>
) {
    BottomAppBar(
        containerColor = colorResource(id = R.color.tabbar),
    ) {
        BottomTabButtons(
            selectedTabIndex = selectedTabIndex,
            onTabSelected = onTabSelected,
            navigationController = navigationController,
            title = title
        )
    }
}

@Composable
fun BottomTabButtons(
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit,
    navigationController: NavController,
    title: MutableState<String>
) {
    // Define the tab items
    val tabItems = listOf(
        TabItem("Home", Icons.Default.Home),
        TabItem("Search", Icons.Default.Search),
        TabItem("Settings", Icons.Default.Settings)
    )

    // Create a row of tab buttons
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        tabItems.forEachIndexed { index, tabItem ->
            NavigationBarItem(
                icon = { Icon(imageVector = tabItem.icon, contentDescription = tabItem.title) },
                label = { Text(text = tabItem.title) },
                selected = selectedTabIndex == index,
                onClick = {
                    onTabSelected(index)
                },
                modifier = Modifier.weight(1f)
            )
        }
    }
}