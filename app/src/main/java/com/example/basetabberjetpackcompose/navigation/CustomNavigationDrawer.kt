package com.example.sidemenupractice2.navigationDrawer

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.basetabberjetpackcompose.navigation.NavigationItemModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomNavigationDrawer(
    drawerState: DrawerState,
    selectedItemIndex: Int,
    onItemSelected: (Int) -> Unit
) {

    val items = listOf(

        NavigationItemModel(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            route = "",
            unSelectedIcon = Icons.Outlined.Home,
        ),
        NavigationItemModel(
            title = "Item List",
            badgecount = 7,
            selectedIcon = Icons.Filled.List,
            route = "",
            unSelectedIcon = Icons.Outlined.List,
        ),
        NavigationItemModel(
            title = "Settings",
            selectedIcon = Icons.Filled.Settings,
            route = "",
            unSelectedIcon = Icons.Outlined.Settings,
        ),
    )

    ModalDrawerSheet (
        //modifier = Modifier.width(300.dp) // 가로 사이즈를 250dp로 설정
        modifier = Modifier.fillMaxWidth(0.75f) // 화면 너비의 3/4를 설정
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        items.forEachIndexed { index, item ->
            NavigationDrawerItem(
                label = {
                    Text(text = item.title)
                },
                selected = index == selectedItemIndex,
                onClick = {
                    onItemSelected(index)
                },
                icon = {
                    Icon(
                        imageVector = if (index == selectedItemIndex) {
                            item.selectedIcon
                        } else {
                            item.unSelectedIcon
                        },
                        contentDescription = item.title
                    )
                },
                badge = {
                    item.badgecount?.let {
                        Text(text = item.badgecount.toString())
                    }
                },
                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
            )
        }
    }
}