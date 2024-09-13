package com.example.basetabberjetpackcompose.tabbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.basetabberjetpackcompose.R
import com.example.basetabberjetpackcompose.tabbar_screen.Screens

@Composable
fun BottomTabButtons(
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit,
    navigationController: NavHostController,
    title: MutableState<String>
) {
    Row(
        modifier = Modifier.fillMaxWidth(), // Ensure the Row takes up the full width
        horizontalArrangement = Arrangement.SpaceBetween // Space buttons evenly across the width
    ) {
        // Home Button
        IconButton(
            onClick = {
                onTabSelected(0)
                navigationController.navigate(Screens.Home.screen) {
                    popUpTo(navigationController.graph.startDestinationId)
                    launchSingleTop = true
                }
                title.value = "Home"
            },
            modifier = Modifier
                .weight(1f) // Each IconButton takes up equal space
                .fillMaxHeight() // IconButton height matches BottomAppBar height
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth() // Column fills the entire IconButton
            ) {
                val iconResId = if (selectedTabIndex == 0) {
                    R.drawable.icon_home
                } else {
                    R.drawable.icon_untapped_home
                }
                Image(
                    painter = painterResource(id = iconResId),
                    contentDescription = null,
                    modifier = Modifier.size(26.dp)
                )
                Text(
                    text = "Home",
                    fontSize = 12.sp,
                    color = if (selectedTabIndex == 0) Color.White else colorResource(id = R.color.colour_tabbar_title)

                )
            }
        }

        // Item List Button
        IconButton(
            onClick = {
                onTabSelected(1)
                navigationController.navigate(Screens.ItemList.screen) {
                    popUpTo(navigationController.graph.startDestinationId)
                    launchSingleTop = true
                }
                title.value = "Item List"
            },
            modifier = Modifier
                .weight(1f) // Each IconButton takes up equal space
                .fillMaxHeight() // IconButton height matches BottomAppBar height
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth() // Column fills the entire IconButton
            ) {
                val iconResId = if (selectedTabIndex == 1) {
                    R.drawable.icon_list
                } else {
                    R.drawable.icon_untapped_list
                }
                Image(
                    painter = painterResource(id = iconResId),
                    contentDescription = null,
                    modifier = Modifier.size(26.dp)
                )
                Text(
                    text = "Item List",
                    fontSize = 12.sp,
                    color = if (selectedTabIndex == 1) Color.White else colorResource(id = R.color.colour_tabbar_title)

                )
            }
        }

        // Settings Button
        IconButton(
            onClick = {
                onTabSelected(2)
                navigationController.navigate(Screens.Setting.screen) {
                    popUpTo(navigationController.graph.startDestinationId)
                    launchSingleTop = true
                }
                title.value = "Setting"
            },
            modifier = Modifier
                .weight(1f) // Each IconButton takes up equal space
                .fillMaxHeight() // IconButton height matches BottomAppBar height
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxHeight() // Column fills the entire IconButton
            ) {
                val iconResId = if (selectedTabIndex == 2) {
                    R.drawable.icon_setting
                } else {
                    R.drawable.icon_untapped_setting
                }
                Image(
                    painter = painterResource(id = iconResId),
                    contentDescription = null,
                    modifier = Modifier.size(26.dp)
                )
                Text(
                    text = "Setting",
                    fontSize = 12.sp,
                    color = if (selectedTabIndex == 2) Color.White else colorResource(id = R.color.colour_tabbar_title)
                )
            }
        }
    }
}